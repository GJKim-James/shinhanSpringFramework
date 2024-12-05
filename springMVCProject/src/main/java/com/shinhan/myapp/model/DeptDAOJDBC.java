package com.shinhan.myapp.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shinhan.myapp.vo.DeptDTO;

import net.firstzone.util.DBUtil;

@Repository("deptDAO") // <bean id="deptDAO" class="com.shinhan.myapp.model"></bean>
public class DeptDAOJDBC implements DeptDAOInterface {
	
	// type�� ������ �ڵ����� Injection �ȴ�.(IoC, DI)
	@Autowired
	@Qualifier("dataSource")
	DataSource ds;
	
	String sqlSelectAll = "SELECT * FROM DEPARTMENTS ORDER BY DEPARTMENT_ID";
	String sqlSelectById = "SELECT * FROM DEPARTMENTS WHERE DEPARTMENT_ID = ?";
	String sqlInsert = "INSERT INTO DEPARTMENTS VALUES(?, ?, ?, ?)";
	String sqlUpdate = "UPDATE DEPARTMENTS SET DEPARTMENT_NAME = ?, MANAGER_ID = ?, LOCATION_ID = ? WHERE DEPARTMENT_ID = ?";
	String sqlDelete = "DELETE FROM DEPARTMENTS WHERE DEPARTMENT_ID = ?";

	Connection conn;
	PreparedStatement st;
	ResultSet rs;
	int result;

	// 1. ��� �μ� ���� ��ȸ
	public List<DeptDTO> selectAll() {
		List<DeptDTO> deptList = new ArrayList<>();

		try {
			conn = ds.getConnection();
			st = conn.prepareStatement(sqlSelectAll);
			rs = st.executeQuery();

			while (rs.next()) {
				DeptDTO dept = makeDept(rs);

				deptList.add(dept);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnet(conn, st, rs);
		}

		return deptList;
	}

	// 2. Ư�� �μ� ���� ��ȸ(�󼼺���)
	public DeptDTO selectById(int deptId) {
		DeptDTO dept = null;

		try {
			conn = ds.getConnection();
			st = conn.prepareStatement(sqlSelectById);
			st.setInt(1, deptId);
			rs = st.executeQuery();

			while (rs.next()) {
				dept = makeDept(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnet(conn, st, rs);;
		}

		return dept;
	}

	// 3. �Է�
	public int insert(DeptDTO dept) {
		int result = 0;
		
		try {
			conn = ds.getConnection();
			st = conn.prepareStatement(sqlInsert);
			st.setInt(1, dept.getDept_id());
			st.setString(2, dept.getDept_name());
			st.setInt(3, dept.getManager_id());
			st.setInt(4, dept.getLocation_id());
			
			result = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnet(conn, st, null);
		}
		
		return result;
	}

	// 4. ����
	public int update(DeptDTO dept) {
		int result = 0;
		
		try {
			conn = ds.getConnection();
			st = conn.prepareStatement(sqlUpdate);
			st.setString(1, dept.getDept_name());
			st.setInt(2, dept.getManager_id());
			st.setInt(3, dept.getLocation_id());
			st.setInt(4, dept.getDept_id());
			
			result = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnet(conn, st, null);
		}
		
		return result;
	}

	// 5. ����
	public int delete(int deptId) {
		int result = 0;
		
		try {
			conn = ds.getConnection();
			conn.setAutoCommit(false); // �ڵ����� Ŀ�� ����; ���� Ŀ���� �������
			st = conn.prepareStatement(sqlDelete);
			st.setInt(1, deptId);
			
			result = st.executeUpdate();
			
			conn.commit(); // Ŀ���� ���� �������; DB�� �ݿ��Ѵ�.
		} catch (SQLException e) {
			try {
				conn.rollback(); // ������ �߻��� ��� �ѹ�; DB�� �۾��� ���� ���
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnet(conn, st, null);
		}
		
		return result;
	}
	
	private DeptDTO makeDept(ResultSet rs) throws SQLException {
		DeptDTO dept = new DeptDTO();

		dept.setDept_id(rs.getInt("DEPARTMENT_ID"));
		dept.setDept_name(rs.getString("DEPARTMENT_NAME"));
		dept.setManager_id(rs.getInt("MANAGER_ID"));
		dept.setLocation_id(rs.getInt("LOCATION_ID"));
		
		return dept;
	}

}
