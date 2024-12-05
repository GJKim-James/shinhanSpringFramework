package com.shinhan.myapp.emp;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import net.firstzone.util.DBUtil;
import net.firstzone.util.DateUtil;

// DAO(Data Access Object)
// Service -> DB -> Service
@Repository
public class EmpDAOJDBC implements EmpDAOInterface {
	
	@Autowired
	@Qualifier("dataSource")
	DataSource ds;
	
	Connection conn;
	
	public List<EmpDTO> selectAll() {
		String sql = "SELECT * FROM EMPLOYEES ORDER BY EMPLOYEE_ID";

		Statement st = null;
		ResultSet rs = null;
		List<EmpDTO> empList = new ArrayList<EmpDTO>();

		try {
			conn = ds.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);

			while (rs.next()) {
				EmpDTO emp = makeEmp(rs);

				empList.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnet(conn, st, rs);
		}

//		for (EmpDTO emp : empList) {
//			System.out.println(emp);
//		}

		return empList;
	}

	public int insert(EmpDTO emp) {
		int result = 0;

		String sql = "INSERT INTO EMPLOYEES VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		PreparedStatement st = null;
		try {
			conn = ds.getConnection();
			st = conn.prepareStatement(sql);

			st.setInt(1, emp.getEmployee_id());
			st.setString(2, emp.getFirst_name());
			st.setString(3, emp.getLast_name());
			st.setString(4, emp.getEmail());
			st.setString(5, emp.getPhone_number());
			st.setDate(6, emp.getHire_date());
			st.setString(7, emp.getJob_id());
			st.setDouble(8, emp.getSalary());
			st.setDouble(9, emp.getCommission_pct());
			st.setObject(10, emp.getManager_id() == -1 ? null : emp.getManager_id());
			st.setObject(11, emp.getDepartment_id() == -1 ? null : emp.getDepartment_id());

			result = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnet(conn, st, null);
		}

		return result;
	}

	public int update(EmpDTO emp) {
		int result = 0;

		String sql = "UPDATE EMPLOYEES SET FIRST_NAME = ?, LAST_NAME = ?, EMAIL = ?, " +
				"PHONE_NUMBER = ?, HIRE_DATE = ?, JOB_ID = ?, SALARY = ?, COMMISSION_PCT = ?, " +
				"MANAGER_ID = ?, DEPARTMENT_ID = ? WHERE EMPLOYEE_ID = ?";

		PreparedStatement st = null;
		try {
			conn = ds.getConnection();
			st = conn.prepareStatement(sql);

			st.setInt(11, emp.getEmployee_id());
			st.setString(1, emp.getFirst_name());
			st.setString(2, emp.getLast_name());
			st.setString(3, emp.getEmail());
			st.setString(4, emp.getPhone_number());
			st.setDate(5, emp.getHire_date());
			st.setString(6, emp.getJob_id());
			st.setDouble(7, emp.getSalary());
			st.setDouble(8, emp.getCommission_pct());
			st.setInt(9, emp.getManager_id() == -1 ? null : emp.getManager_id());
			st.setInt(10, emp.getDepartment_id() == -1 ? null : emp.getDepartment_id());

			result = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnet(conn, st, null);
		}

		return result;
	}

	public int delete(int empId) {
		int result = 0;

		String sql = "DELETE FROM EMPLOYEES WHERE EMPLOYEE_ID = ?";

		PreparedStatement st = null;
		
		try {
			conn = ds.getConnection();
			st = conn.prepareStatement(sql);
			
			st.setInt(1, empId);
			
			result = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnet(conn, st, null);
		}
		
		return result;
	}

	public EmpDTO selectById(int empId) {
		String sql = "SELECT * FROM EMPLOYEES WHERE EMPLOYEE_ID = " + empId;

		Statement st = null;
		ResultSet rs = null;
		EmpDTO emp = null;

		try {
			conn = ds.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);

			while (rs.next()) {
				emp = makeEmp(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnet(conn, st, rs);
		}

		return emp;
	}
	
	public List<EmpDTO> selectByDeptId(int deptId) {
		String sql = "SELECT * FROM EMPLOYEES WHERE DEPARTMENT_ID = ?";

		PreparedStatement st = null;
		ResultSet rs = null;
		List<EmpDTO> empList = new ArrayList<EmpDTO>();

		try {
			conn = ds.getConnection();
			st = conn.prepareStatement(sql);
			st.setInt(1, deptId);
			rs = st.executeQuery();

			while (rs.next()) {
				EmpDTO emp = makeEmp(rs);
				
				empList.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnet(conn, st, rs);
		}

		return empList;
	}
	
	public List<EmpDTO> selectByJobId(String jobId) {
		String sql = "SELECT * FROM EMPLOYEES WHERE JOB_ID = ?";

		PreparedStatement st = null;
		ResultSet rs = null;
		List<EmpDTO> empList = new ArrayList<EmpDTO>();

		try {
			conn = ds.getConnection();
			st = conn.prepareStatement(sql);
			st.setString(1, jobId);
			rs = st.executeQuery();

			while (rs.next()) {
				EmpDTO emp = makeEmp2(rs);
				
				empList.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnet(conn, st, rs);
		}

		return empList;
	}
	
	public List<EmpDTO> selectBySalary(int salary) {
		String sql = "SELECT * FROM EMPLOYEES WHERE SALARY >= ?";

		PreparedStatement st = null;
		ResultSet rs = null;
		List<EmpDTO> empList = new ArrayList<EmpDTO>();

		try {
			conn = ds.getConnection();
			st = conn.prepareStatement(sql);
			st.setInt(1, salary);
			rs = st.executeQuery();

			while (rs.next()) {
				EmpDTO emp = makeEmp2(rs);
				
				empList.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnet(conn, st, rs);
		}

		return empList;
	}
	
	public List<EmpDTO> selectByCondition(Map<String, Object> map) {
		String sql = "SELECT * FROM EMPLOYEES WHERE ('-1' = ? or DEPARTMENT_ID = ?) " +
				"AND ('-1' = ? or JOB_ID = ?) AND SALARY >= ? AND HIRE_DATE >= ?";

		PreparedStatement st = null;
		ResultSet rs = null;
		List<EmpDTO> empList = new ArrayList<EmpDTO>();

		try {
			conn = ds.getConnection();
			st = conn.prepareStatement(sql);
			
			String str_deptid = (String) map.get("deptid");
			int deptid = Integer.parseInt(str_deptid);
			String str_salary = (String) map.get("salary");
			String str_hiredate = (String) map.get("hiredate");
			Date hiredate = DateUtil.convertSqlDate(DateUtil.convertDate(str_hiredate));
			
			st.setInt(1, deptid);
			st.setInt(2, deptid);
			st.setString(3, (String) map.get("jobid"));
			st.setString(4, (String) map.get("jobid"));
			st.setDouble(5, Double.parseDouble(str_salary));
			st.setDate(6, hiredate);
			
			rs = st.executeQuery();

			while (rs.next()) {
				EmpDTO emp = makeEmp2(rs);
				
				empList.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnet(conn, st, rs);
		}

		return empList;
	}

	private static EmpDTO makeEmp(ResultSet rs) throws SQLException {
		EmpDTO emp = new EmpDTO();

		emp.setEmployee_id(rs.getInt("EMPLOYEE_ID"));
		emp.setFirst_name(rs.getString("FIRST_NAME"));
		emp.setLast_name(rs.getString("LAST_NAME"));
		emp.setEmail(rs.getString("EMAIL"));
		emp.setPhone_number(rs.getString("PHONE_NUMBER"));
		emp.setHire_date(rs.getDate("HIRE_DATE"));
		emp.setJob_id(rs.getString("JOB_ID"));
		emp.setSalary(rs.getDouble("SALARY"));
		emp.setCommission_pct(rs.getDouble("COMMISSION_PCT"));
		emp.setManager_id(rs.getInt("MANAGER_ID"));
		emp.setDepartment_id(rs.getInt("DEPARTMENT_ID"));

		return emp;
	}
	
	private static EmpDTO makeEmp2(ResultSet rs) throws SQLException {
		EmpDTO emp = EmpDTO.builder()
				.employee_id(rs.getInt("EMPLOYEE_ID"))
				.first_name(rs.getString("FIRST_NAME"))
				.last_name(rs.getString("LAST_NAME"))
				.email(rs.getString("EMAIL"))
				.phone_number(rs.getString("PHONE_NUMBER"))
				.hire_date(rs.getDate("HIRE_DATE"))
				.job_id(rs.getString("JOB_ID"))
				.salary(rs.getDouble("SALARY"))
				.commission_pct(rs.getDouble("COMMISSION_PCT"))
				.manager_id(rs.getInt("MANAGER_ID"))
				.department_id(rs.getInt("DEPARTMENT_ID"))
				.build();

		return emp;
	}

	@Override
	public List<EmpJoinDTO> selectByJobJoin(String jobId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> selectByJobJoinMap(String jobId) {
		// TODO Auto-generated method stub
		return null;
	}

}
