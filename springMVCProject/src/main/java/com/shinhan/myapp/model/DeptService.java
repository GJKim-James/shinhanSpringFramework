package com.shinhan.myapp.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shinhan.myapp.vo.DeptDTO;

@Service("deptService") // @Component + service ����, �̸� ���� ���ϸ� DeptService => deptService�� �ڵ����� �̸� ����
// <bean id="deptService" class="com.shinhan.myapp.model"></bean>
public class DeptService {
	
	@Autowired // @Autowired : type�� ������ �ڵ����� Injection �Ѵ�. ���� Ÿ���� ���� �� ������ ����.
	@Qualifier("deptMybatis") // @Qualifier : �̸�(@Repository(""))���� Injection �Ѵ�.
	DeptDAOInterface deptDAO;

	// ��� �μ� ���� ��ȸ
	public List<DeptDTO> selectAllService() {
		return deptDAO.selectAll();
	}

	// �μ� ��ȣ �Է��ؼ� �ش� �μ� ���� ��ȸ(�󼼺���)
	public DeptDTO selectById(int deptid) {
		return deptDAO.selectById(deptid);
	}

	// ���ο� �μ� ���� �Է�
	public int insert(DeptDTO deptDTO) {
		return deptDAO.insert(deptDTO);
	}

	// �μ� ���� ����
	public int update(DeptDTO deptDTO) {
		return deptDAO.update(deptDTO);
	}

	// �μ� ���� ����
	public int delete(int deptid) {
		return deptDAO.delete(deptid);
	}

}
