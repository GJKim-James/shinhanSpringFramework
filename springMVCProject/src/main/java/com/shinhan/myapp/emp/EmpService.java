package com.shinhan.myapp.emp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class EmpService {
	
	// @Autowired : type이 같으면 injection, 같은 타입이 여러 개 있을 경우 이름이 같으면 injection
	// @Qualifier : 이름으로 injection
	@Autowired
//	@Qualifier("empMybatis")
//	EmpDAOInterface empDAO;
	EmpDAOMybatis empDAO;
	
	public List<EmpDTO> selectAllService() {
		return empDAO.selectAll();
	}

	public EmpDTO selectByIdService(int empId) {
		return empDAO.selectById(empId);
	}
	
	public int insertService(EmpDTO emp) {
		return empDAO.insert(emp);
	}
	
	public int updateService(EmpDTO emp) {
		return empDAO.update(emp);
	}

	public int deleteService(int empId) {
		return empDAO.delete(empId);
	}

	public List<EmpDTO> selectByDeptId(int deptId) {
		return empDAO.selectByDeptId(deptId);
	}
	
	public List<EmpDTO> selectByJobId(String jobId) {
		return empDAO.selectByJobId(jobId);
	}

	public List<EmpDTO> selectBySalary(int salary) {
		return empDAO.selectBySalary(salary);
	}

	public List<EmpDTO> selectByCondition(Map<String, Object> map) {
		return empDAO.selectByCondition(map);
	}
	
	public List<EmpJoinDTO> selectByJobJoin(String jobId) {
		return empDAO.selectByJobJoin(jobId);
	}
	
	public List<Map<String, Object>> selectByJobJoinMap(String jobId) {
		return empDAO.selectByJobJoinMap(jobId);
	}
	
	public List<EmpDTO> selectByArray(List<Integer> deptArr) {
		return empDAO.selectByArray(deptArr);
	}
	
}
