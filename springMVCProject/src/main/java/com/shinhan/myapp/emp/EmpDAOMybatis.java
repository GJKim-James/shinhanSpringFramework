package com.shinhan.myapp.emp;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

// DataSource(DB 연결) ==> SqlSessionFactory(DB, Mapper) ==> SqlSession 생성
// ==> DAO ==> Service ==> Controller

@Slf4j
@Repository("empMybatis")
public class EmpDAOMybatis implements EmpDAOInterface {

	@Autowired
	SqlSession sqlSession;

	// empMapper.xml
	String namespace = "com.shinhan.emp.";

	public List<EmpDTO> selectAll() {
		List<EmpDTO> empList = sqlSession.selectList(namespace + "selectAll");
		log.info("[selectAll] empList 건수 : " + empList.size());

		return empList;
	}

	public int insert(EmpDTO empDTO) {
		int result = sqlSession.insert(namespace + "insert", empDTO);
		log.info("등록 건수 : " + result);
		
		return result;
	}

	public int update(EmpDTO empDTO) {
		int result = sqlSession.update(namespace + "update", empDTO);
		log.info("수정 건수 : " + result);
		
		return result;
	}

	public int delete(int empId) {
		int result = sqlSession.delete(namespace + "delete", empId);
		log.info("삭제 건수 : " + result);
		
		return result;
	}

	public EmpDTO selectById(int empId) {
		EmpDTO empDTO = sqlSession.selectOne(namespace + "selectById", empId);
		log.info("[selectById] empDTO : " + empDTO);

		return empDTO;
	}

	public List<EmpDTO> selectByDeptId(int deptId) {
		List<EmpDTO> empList = sqlSession.selectList(namespace + "selectByDeptId", deptId);
		log.info("[selectByDeptId] empList 건수 : " + empList.size());

		return empList;
	}

	public List<EmpDTO> selectByJobId(String jobId) {
		List<EmpDTO> empList = sqlSession.selectList(namespace + "selectByJobId", jobId);
		log.info("[selectByJobId] empList 건수 : " + empList.size());

		return empList;
	}

	public List<EmpDTO> selectBySalary(int salary) {
		List<EmpDTO> empList = sqlSession.selectList(namespace + "selectBySalary", salary);
		log.info("[selectBySalary] empList 건수 : " + empList.size());

		return empList;
	}

	public List<EmpDTO> selectByCondition(Map<String, Object> map) {
		List<EmpDTO> empList = sqlSession.selectList(namespace + "selectByCondition", map);
		log.info("[selectByCondition] empList 건수 : " + empList.size());

		return empList;
	}

	public List<EmpJoinDTO> selectByJobJoin(String jobId) {
		List<EmpJoinDTO> empList = sqlSession.selectList(namespace + "selectByJobJoin", jobId);
		log.info("empList : " + empList);
		log.info("[selectByJobJoin] empList 건수 : " + empList.size());

		return empList;
	}

	public List<Map<String, Object>> selectByJobJoinMap(String jobId) {
		List<Map<String, Object>> empList = sqlSession.selectList(namespace + "selectByJobJoinMap", jobId);
		log.info("[selectByJobJoinMap] empList 건수 : " + empList.size());

		return empList;
	}
	
	// 여러 부서들의 직원 정보 조회(WHERE DEPARTMENT_ID IN (10, 20, 30))
	public List<EmpDTO> selectByArray(List<Integer> deptArr) {
		List<EmpDTO> empList = sqlSession.selectList(namespace + "selectByArray", deptArr);
		log.info("[selectByArray] empList 건수 : " + empList.size());
		
		return empList;
	}

}
