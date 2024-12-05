package com.shinhan.myapp.emp;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// DTO : Data Transfer Object
// VO : Value Object
@Getter
@Setter
@ToString
@Builder // 異붽? ?떆 @NoArgsConstructor, @AllArgsConstructor ?씠寃껊룄 ?븘?슂
@NoArgsConstructor
@AllArgsConstructor
public class EmpDTO {
	
	// 기본형 데이터 타입은 null setting 불가
	// 기본형 데이터 타입을 Wrapper 클래스로 변경(int => Integer, double => Double)
	Integer employee_id;
	String first_name;
	String last_name;
	String email;
	String phone_number;
	Date hire_date;
	String job_id;
	Double salary;
	Double commission_pct;
	int manager_id;
	int department_id;

}
