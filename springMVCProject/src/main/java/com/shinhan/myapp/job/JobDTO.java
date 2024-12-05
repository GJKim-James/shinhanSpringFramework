package com.shinhan.myapp.job;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class JobDTO {
	
	String job_id;
	String job_title;
	int min_salary;
	int max_salary;

}
