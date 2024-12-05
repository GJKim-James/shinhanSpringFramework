package com.shinhan.myapp.job;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository("jobMybatis")
public class JobDAOMybatis implements JobDAOInterface {

	@Autowired
	SqlSession sqlSession;

	// jobMapper.xml
	String namespace = "com.shinhan.job.";

	public List<JobDTO> selectAllJobs() {
		List<JobDTO> jobList = sqlSession.selectList(namespace + "selectAllJobs");
		log.info("jobList °Ç¼ö : " + jobList.size());

		return jobList;
	}

}
