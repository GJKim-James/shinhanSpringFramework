package com.shinhan.myapp.job;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobService {
	
	@Autowired
	JobDAOJDBC jobDAO;

//	JobDAO jobDAO = new JobDAO();

	public List<JobDTO> selectAllJobs() {
		return jobDAO.selectAllJobs();
	}

}
