package com.shinhan.myapp.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shinhan.myapp.vo.DeptDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository("deptMybatis")
public class DeptDAOMybatis implements DeptDAOInterface {
	
	// DB ���� ==> DataSource ==> sqlSessionFactory ==> sqlSession
	@Autowired
	SqlSession sqlSession;
	
	// deptMapper.xml
	String namespace = "com.shinhan.dept.";

	// 1. ��� �μ� ���� ��ȸ
	public List<DeptDTO> selectAll() {
		List<DeptDTO> deptList = sqlSession.selectList(namespace + "selectAll");
		log.info("deptList �Ǽ� : " + deptList.size());
		
		return deptList;
	}

	// 2. Ư�� �μ� ���� ��ȸ(�󼼺���)
	public DeptDTO selectById(int deptid) {
		DeptDTO deptDTO = sqlSession.selectOne(namespace + "selectById", deptid);
		log.info("deptDTO : " + deptDTO);

		return deptDTO;
	}

	// 3. �Է�
	public int insert(DeptDTO deptDTO) {
		int result = sqlSession.insert(namespace + "insert", deptDTO);
		log.info("�Է� �Ǽ� : " + result);
		
		return result;
	}

	// 4. ����
	public int update(DeptDTO deptDTO) {
		int result = sqlSession.update(namespace + "update", deptDTO);
		log.info("���� �Ǽ� : " + result);
		
		return result;
	}

	// 5. ����
	public int delete(int deptid) {
		int result = sqlSession.delete(namespace + "delete", deptid);
		log.info("���� �Ǽ� : " + result);
		
		return result;
	}

}
