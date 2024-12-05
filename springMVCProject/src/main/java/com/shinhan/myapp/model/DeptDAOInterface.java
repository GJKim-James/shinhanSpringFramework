package com.shinhan.myapp.model;

import java.util.List;

import com.shinhan.myapp.vo.DeptDTO;

public interface DeptDAOInterface {

	// 1. ��� �μ� ���� ��ȸ
	public List<DeptDTO> selectAll();

	// 2. Ư�� �μ� ���� ��ȸ(�󼼺���)
	public DeptDTO selectById(int deptId);

	// 3. �Է�
	public int insert(DeptDTO dept);

	// 4. ����
	public int update(DeptDTO dept);

	// 5. ����
	public int delete(int deptId);

}
