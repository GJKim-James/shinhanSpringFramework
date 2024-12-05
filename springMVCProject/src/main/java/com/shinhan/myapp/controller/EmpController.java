package com.shinhan.myapp.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.shinhan.myapp.emp.EmpDTO;
import com.shinhan.myapp.emp.EmpService;
import com.shinhan.myapp.job.JobService;
import com.shinhan.myapp.model.DeptService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/emp")
@Slf4j
@RequiredArgsConstructor // final�� ����� ���� @Autowired
public class EmpController {
	
//	@Autowired
//	EmpService empService;
//	
//	@Autowired
//	JobService jobService;
//	
//	@Autowired
//	DeptService deptService;
	
	// final : ���� �� 
	final EmpService empService;
	final JobService jobService;
	final DeptService deptService;
	
	// ���� ��� ��ȸ
	@GetMapping("/list.do")
	public String selectAll(Model model, HttpServletRequest request) {
		Map<String, ?> map = RequestContextUtils.getInputFlashMap(request);
		if (map != null) {
			String message = (String) map.get("resultMessage");
			log.info("[���� �޽���] : " + message);
			model.addAttribute("result", message);
		}
		
		model.addAttribute("jobList", jobService.selectAllJobs());
		model.addAttribute("deptList", deptService.selectAllService());
		
		return "emp/empList";
	}
	
	// empList ���� ��ȸ ajax ó��
	@GetMapping("/list2.do")
	public String selectByCondition(@RequestParam Map<String, Object> map, Model model) {
		log.info("map : " + map);
		
		// ��� ���� üũ �� 'hiredate=1900-01-01'�� ����
		String chkdate = (String) map.get("chkdate");
		if (chkdate.equals("true")) {
			map.put("hiredate", "1900-01-01");
		}
		
		List<EmpDTO> empList = empService.selectByCondition(map);
		log.info("��ȸ�� empList �Ǽ� : " + empList.size());
		model.addAttribute("empList", empList);
		
		// ajax ��� ������ jsp
		return "emp/empListTable";
	}
	
	// ���� ���
	@GetMapping("/insert.do")
	public String insert(Model model) {
		model.addAttribute("jobList", jobService.selectAllJobs());
		model.addAttribute("empList", empService.selectAllService());
		model.addAttribute("deptList", deptService.selectAllService());
		
		return "emp/empInsert";
	}
	
	// ���� ��� POST
	@PostMapping("/insert.do")
	public String insertPost(EmpDTO empDTO, RedirectAttributes reAttr) {
		int result = empService.insertService(empDTO);
		
		String message = "�Է� �Ǽ� : " + result;
		log.info(message);
		reAttr.addFlashAttribute("resultMessage", message);
		
		return "redirect:list.do";
	}
	
	// ���� �󼼺���
	@GetMapping("/detail.do")
	public String detail(int empid, Model model) {
		EmpDTO empDTO = empService.selectByIdService(empid);
		
		model.addAttribute("empDTO", empDTO);
		model.addAttribute("jobList", jobService.selectAllJobs());
		model.addAttribute("empList", empService.selectAllService());
		model.addAttribute("deptList", deptService.selectAllService());
		
		return "emp/empDetail";
	}
	
	// ���� ���� ����
	@PostMapping("/detail.do")
	public String detailPost(EmpDTO empDTO, RedirectAttributes reAttr) {
		int result = empService.updateService(empDTO);
		
		String message = "���� �Ǽ� : " + result;
		log.info(message);
		reAttr.addFlashAttribute("resultMessage", message);
		
		return "redirect:list.do";
	}
	
	// ���� ����
	@GetMapping("/delete.do")
	public String delete(int empid, RedirectAttributes reAttr) {
		int result = empService.deleteService(empid);
		
		String message = "���� �Ǽ� : " + result;
		log.info(message);
		reAttr.addFlashAttribute("resultMessage", message);
		
		return "redirect:list.do";
	}
	
	// ���� ���� POST
	@PostMapping("/delete.do")
	public String deletePost(int empid, RedirectAttributes reAttr) {
		int result = empService.deleteService(empid);
		
		String message = "���� �Ǽ� : " + result;
		log.info(message);
		reAttr.addFlashAttribute("resultMessage", message);
		
		return "redirect:list.do";
	}
	
	// �޿��θ� ��ȸ(Ajax)
	@GetMapping("/listBySalary.do")
	public String listBySalary(int salary, Model model) {
		model.addAttribute("empList", empService.selectBySalary(salary));
		
		return "emp/empListTable";
	}
	
	// ��å���θ� ��ȸ(Ajax)
	@GetMapping("/listByJobId.do")
	public String listByJobId(String job_id, Model model) {
		model.addAttribute("empList", empService.selectByJobId(job_id));
		
		return "emp/empListTable";
	}
	
	// �μ��θ� ��ȸ(Ajax)
	@GetMapping("/listByDeptId.do")
	public String listByDeptId(int department_id, Model model) {
		model.addAttribute("empList", empService.selectByDeptId(department_id));
		
		return "emp/empListTable";
	}
	
	// ��åJoin(List) ��ȸ(Ajax)
	@GetMapping("/listByJobJoin.do")
	public String listByJobJoin(String job_id, Model model) {
		model.addAttribute("empList", empService.selectByJobJoin(job_id));
		
		return "emp/empListTable2";
	}
	
	// ��åJoin(Map) ��ȸ(Ajax)
	@GetMapping("/listByJobJoinMap.do")
	public String listByJobJoinMap(String job_id, Model model) {
		model.addAttribute("empList", empService.selectByJobJoinMap(job_id));
		
		return "emp/empListTable3";
	}
	
	// ���� �μ����� ���� ���� ��ȸ(WHERE DEPARTMENT_ID IN (10, 20, 30))
	// �迭�� �Ķ���ͷ� ���� ���� @RequestParam �ʼ� �Է�, value�� "[]" �Է� �ʼ�
	@GetMapping("/listByArray.do")
	public String listByArray(@RequestParam(value = "deptArr[]") Integer[] arr, Model model) {
		log.info("arr(deptArr[]) : " + Arrays.toString(arr)); // [10, 60, 90]
		
		// �迭�� list�� ����
		model.addAttribute("empList", empService.selectByArray(Arrays.asList(arr)));
		
		return "emp/empListTable";
	}

}
