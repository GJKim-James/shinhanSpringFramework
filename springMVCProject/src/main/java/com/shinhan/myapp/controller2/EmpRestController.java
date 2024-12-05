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

// Spring 4 ���� : @RestController => @Controller + @ResponseBody
@RestController
@RequestMapping("/rest")
public class EmpRestController {
	
	@Autowired
	EmpService empService;
	
	// produces : ��ȯ�Ǵ� ������ ����, consumes : ������ ������ ����
//	@GetMapping(value = "/test2.do", produces = "text/plain;charset=utf-8")
//	public String f1() {
//		return "Rest ��� ����2(Spring 4 ���� @RestController)";
//	}
	
	// ���� ��ȸ(���� data�� ����, return data)
	// jackson ���̺귯���� data�� JSON���� �����ؼ� return
	@GetMapping(value = "/emplist.do", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<EmpDTO> empList() {
		return empService.selectAllService();
	}
	
	// �� ����(URI�� ���� ���� data ����, return data)
	@GetMapping(value = "/empdetail.do/{empid}", produces = "application/json")
	public EmpDTO empDetail(@PathVariable int empid) {
		return empService.selectByIdService(empid);
	}
	
	// �� ����(���� data�� ����, return data�� �ܼ� ����)
	@GetMapping(value = "/empdetail2.do", produces = "text/plain;charset=utf-8")
	public String empDetail2() {
		EmpDTO empDTO = empService.selectByIdService(100);
		
		return "100�� ���� First Name : " + empDTO.getFirst_name();
	}
	
	// ��� ���� ��ȸ, return�� Map<���� ��ȣ, ���� DTO>
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
	
	// ���� �Է�(POST), ������ data ����, data�� ��û ������ body�� �´�.
	// ����! : @RequestParam �ƴ�, @RequestBody��
	@PostMapping(value = "/empinsert.do", consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = "text/plain;charset=utf-8")
	public String empInsert(@RequestBody EmpDTO empDTO) {
		int result = empService.insertService(empDTO);
		
		return result > 0 ? "insert ����" : "insert ����";
	}
	
	// ���� ���� ����(PUT)
	@PutMapping(value = "empupdate.do", consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = "text/plain;charset=utf-8")
	public String empUpdate(@RequestBody EmpDTO empDTO) {
		int result = empService.updateService(empDTO);
		
		return result > 0 ? "update ����" : "update ����";
	}
	
	// ���� ����(DELETE)
	@DeleteMapping(value = "empdelete.do/{empid}", produces = "text/plain;charset=utf-8")
	public String empDelete(@PathVariable int empid) {
		int result = empService.deleteService(empid);
		
		return result > 0 ? "delete ����" : "delete ����";
	}

}
