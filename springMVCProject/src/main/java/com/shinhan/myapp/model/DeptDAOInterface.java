package com.shinhan.myapp.model;

import java.util.List;

import com.shinhan.myapp.vo.DeptDTO;

public interface DeptDAOInterface {

	// 1. 모든 부서 정보 조회
	public List<DeptDTO> selectAll();

	// 2. 특정 부서 정보 조회(상세보기)
	public DeptDTO selectById(int deptId);

	// 3. 입력
	public int insert(DeptDTO dept);

	// 4. 수정
	public int update(DeptDTO dept);

	// 5. 삭제
	public int delete(int deptId);

}
