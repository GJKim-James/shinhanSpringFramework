package com.shinhan.myapp.controller2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shinhan.myapp.emp.EmpDTO;
import com.shinhan.myapp.emp.EmpService;

// Spring 4 버전 : @RestController => @Controller + @ResponseBody
@RestController
@RequestMapping("/rest")
public class EmpRestController {
	
	@Autowired
	EmpService empService;
	
	// produces : 반환되는 데이터 정의, consumes : 들어오는 데이터 정의
//	@GetMapping(value = "/test2.do", produces = "text/plain;charset=utf-8")
//	public String f1() {
//		return "Rest 방식 연습2(Spring 4 버전 @RestController)";
//	}
	
	// 직원 조회(들어온 data는 없음, return data)
	// jackson 라이브러리가 data를 JSON으로 변경해서 return
	@GetMapping(value = "/emplist.do", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<EmpDTO> empList() {
		return empService.selectAllService();
	}
	
	// 상세 보기(URI를 통해 들어온 data 있음, return data)
	@GetMapping(value = "/empdetail.do/{empid}", produces = "application/json")
	public EmpDTO empDetail(@PathVariable int empid) {
		return empService.selectByIdService(empid);
	}
	
	// 상세 보기(들어온 data는 없음, return data는 단순 문자)
	@GetMapping(value = "/empdetail2.do", produces = "text/plain;charset=utf-8")
	public String empDetail2() {
		EmpDTO empDTO = empService.selectByIdService(100);
		
		return "100번 직원 First Name : " + empDTO.getFirst_name();
	}
	
	// 모든 직원 조회, return은 Map<직원 번호, 직원 DTO>
	// { "100": {}, "101": {}, "102": {} }
	@GetMapping(value = "/empmap.do", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<Integer, EmpDTO> empMap() {
		Map<Integer, EmpDTO> map = new HashMap<>();
		
		List<EmpDTO> empList = empService.selectAllService();
		empList.forEach(emp -> {
			map.put(emp.getEmployee_id(), emp);
		});
		
		return map;
	}
	
	// 직원 입력(POST), 들어오는 data 있음, data는 요청 문서의 body로 온다.
	// 주의! : @RequestParam 아님, @RequestBody임
	@PostMapping(value = "/empinsert.do", consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = "text/plain;charset=utf-8")
	public String empInsert(@RequestBody EmpDTO empDTO) {
		int result = empService.insertService(empDTO);
		
		return result > 0 ? "insert 성공" : "insert 실패";
	}
	
	// 직원 정보 수정(PUT)
	@PutMapping(value = "empupdate.do", consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = "text/plain;charset=utf-8")
	public String empUpdate(@RequestBody EmpDTO empDTO) {
		int result = empService.updateService(empDTO);
		
		return result > 0 ? "update 성공" : "update 실패";
	}
	
	// 직원 삭제(DELETE)
	@DeleteMapping(value = "empdelete.do/{empid}", produces = "text/plain;charset=utf-8")
	public String empDelete(@PathVariable int empid) {
		int result = empService.deleteService(empid);
		
		return result > 0 ? "delete 성공" : "delete 실패";
	}

}
