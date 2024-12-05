package com.shinhan.myapp.emp;

import java.util.List;
import java.util.Map;

public interface EmpDAOInterface {
	
	public List<EmpDTO> selectAll();

	public int insert(EmpDTO empDTO);

	public int update(EmpDTO empDTO);

	public int delete(int empId);

	public EmpDTO selectById(int empId);
	
	public List<EmpDTO> selectByDeptId(int deptId);
	
	public List<EmpDTO> selectByJobId(String jobId);
	
	public List<EmpDTO> selectBySalary(int salary);
	
	public List<EmpDTO> selectByCondition(Map<String, Object> map);
	
	public List<EmpJoinDTO> selectByJobJoin(String jobId);
	
	public List<Map<String, Object>> selectByJobJoinMap(String jobId);

}
