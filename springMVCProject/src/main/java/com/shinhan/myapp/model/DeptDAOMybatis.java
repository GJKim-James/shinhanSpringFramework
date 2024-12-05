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
	
	// DB 정보 ==> DataSource ==> sqlSessionFactory ==> sqlSession
	@Autowired
	SqlSession sqlSession;
	
	// deptMapper.xml
	String namespace = "com.shinhan.dept.";

	// 1. 모든 부서 정보 조회
	public List<DeptDTO> selectAll() {
		List<DeptDTO> deptList = sqlSession.selectList(namespace + "selectAll");
		log.info("deptList 건수 : " + deptList.size());
		
		return deptList;
	}

	// 2. 특정 부서 정보 조회(상세보기)
	public DeptDTO selectById(int deptid) {
		DeptDTO deptDTO = sqlSession.selectOne(namespace + "selectById", deptid);
		log.info("deptDTO : " + deptDTO);

		return deptDTO;
	}

	// 3. 입력
	public int insert(DeptDTO deptDTO) {
		int result = sqlSession.insert(namespace + "insert", deptDTO);
		log.info("입력 건수 : " + result);
		
		return result;
	}

	// 4. 수정
	public int update(DeptDTO deptDTO) {
		int result = sqlSession.update(namespace + "update", deptDTO);
		log.info("수정 건수 : " + result);
		
		return result;
	}

	// 5. 삭제
	public int delete(int deptid) {
		int result = sqlSession.delete(namespace + "delete", deptid);
		log.info("삭제 건수 : " + result);
		
		return result;
	}

}
