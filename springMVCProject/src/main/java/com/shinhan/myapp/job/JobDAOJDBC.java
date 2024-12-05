package com.shinhan.myapp.job;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import net.firstzone.util.DBUtil;

@Repository
public class JobDAOJDBC implements JobDAOInterface {
	
	@Autowired
	@Qualifier("dataSource")
	DataSource ds;
	
	Connection conn;

	public List<JobDTO> selectAllJobs() {
		String sql = "SELECT * FROM JOBS";

		Statement st = null;
		ResultSet rs = null;
		List<JobDTO> jobList = new ArrayList<JobDTO>();

		try {
			conn = ds.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);

			while (rs.next()) {
				JobDTO jobDTO = makeJobs(rs);

				jobList.add(jobDTO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnet(conn, st, rs);
		}

//			for (jobDTO job : jobList) {
//				System.out.println(job);
//			}

		return jobList;
	}

	private static JobDTO makeJobs(ResultSet rs) throws SQLException {
		JobDTO jobDTO = JobDTO.builder()
				.job_id(rs.getString("JOB_ID"))
				.job_title(rs.getString("JOB_TITLE"))
				.min_salary(rs.getInt("MIN_SALARY"))
				.max_salary(rs.getInt("MAX_SALARY"))
				.build();

		return jobDTO;
	}

}
