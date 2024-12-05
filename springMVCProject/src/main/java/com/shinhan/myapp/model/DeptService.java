package com.shinhan.myapp.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shinhan.myapp.vo.DeptDTO;

@Service("deptService") // @Component + service 역할, 이름 설정 안하면 DeptService => deptService로 자동으로 이름 설정
// <bean id="deptService" class="com.shinhan.myapp.model"></bean>
public class DeptService {
	
	@Autowired // @Autowired : type이 같으면 자동으로 Injection 한다. 같은 타입이 여러 개 있으면 오류.
	@Qualifier("deptMybatis") // @Qualifier : 이름(@Repository(""))으로 Injection 한다.
	DeptDAOInterface deptDAO;

	// 모든 부서 정보 조회
	public List<DeptDTO> selectAllService() {
		return deptDAO.selectAll();
	}

	// 부서 번호 입력해서 해당 부서 정보 조회(상세보기)
	public DeptDTO selectById(int deptid) {
		return deptDAO.selectById(deptid);
	}

	// 새로운 부서 정보 입력
	public int insert(DeptDTO deptDTO) {
		return deptDAO.insert(deptDTO);
	}

	// 부서 정보 수정
	public int update(DeptDTO deptDTO) {
		return deptDAO.update(deptDTO);
	}

	// 부서 정보 삭제
	public int delete(int deptid) {
		return deptDAO.delete(deptid);
	}

}
