package com.shinhan.myapp.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.shinhan.myapp.model.DeptService;
import com.shinhan.myapp.vo.DeptDTO;

// �μ��� CRUD �۾� : Controller -> Service -> DAO
// ����� ��û -> DispatcherServlet -> Controller ã��
// component-scan�� ���ؼ� Bean ����
//@RestController // ��û�� �޾Ƽ� ���� �����͸� return; @Controller + @ResponseBody
@Controller // ��û�� �޾Ƽ� ���� �������� return
public class DeptController {
	
	@Autowired
	DeptService deptService;
	
	Logger logger = LoggerFactory.getLogger(DeptController.class);
	
	// �μ� ��� ��ȸ
//	@ResponseBody // Servlet���� request.getWriter().append("aaa");�� ���� ����
	@RequestMapping("/dept/list.do")
	public String selectAll(Model model, HttpServletRequest request) {
		// /dept/insert.do(POST)���� RedirectAttributes ������ ���
		Map<String, ?> map = RequestContextUtils.getInputFlashMap(request);
		if (map != null) {
			String message = (String) map.get("resultMessage");
			logger.info("[���� �޽���] : " + message);
			model.addAttribute("result", message); // deptList.jsp���� ���
		}
		
		List<DeptDTO> deptList = deptService.selectAllService();
		
		model.addAttribute("deptList", deptList);
		
		return "dept/deptList"; // WEB-INF/views/dept/deptlist.jsp
	}
	
	// �μ� �󼼺���
	@GetMapping("/dept/detail.do")
	public String detail(int deptid, Model model) {
		model.addAttribute("deptDTO", deptService.selectById(deptid));
		
		return "dept/deptDetail";
	}
	
	// �󼼺��� �� ����� �����ְ� 1�� �� list�� ����
//	@PostMapping("/dept/detail.do")
//	public String detailPost(@ModelAttribute("deptDTO") DeptDTO deptDTO) {
//		logger.info(deptDTO.toString());
//		
//		// POST ����� �ѱ� ���ڵ� �ʿ� => web.xml Filter ��� �� ����
//		deptService.update(deptDTO);
//		
//		return "dept/result";
//	}
	
	// �μ� �󼼺���(POST)
	@PostMapping("/dept/detail.do")
	public String detailPost(DeptDTO deptDTO, RedirectAttributes reAttr) {
		logger.info(deptDTO.toString());
		
		// POST ����� �ѱ� ���ڵ� �ʿ� => web.xml Filter ��� �� ����
		int result = deptService.update(deptDTO);
		
		String message = "���� �Ǽ� : " + result;
		logger.info(message);
		reAttr.addFlashAttribute("resultMessage", message);
		
		// ���û�ϱ�(Servlet�� response.sendRedirect()�� ������ ����)
		return "redirect:/dept/list.do";
	}
	
	// �μ� ���
	@GetMapping("/dept/insert.do")
	public String insert(DeptDTO deptDTO) {
		return "dept/deptInsert";
	}
	
	// �μ� ���(POST)
	@PostMapping("/dept/insert.do")
	public String insertPost(DeptDTO deptDTO, RedirectAttributes reAttr) {
		int result = deptService.insert(deptDTO);
		
		String message = "�Է� �Ǽ� : " + result;
		logger.info(message);
		// Model ��ü�� �����͸� �����ص� redirect(���û)�̱� ������ Model ������ ��� �Ұ�
		// �׷��� RedirectAttributes�� ����Ѵ�.
		// RedirectAttributes �����͸� �޴� �ʿ����� RequestContextUtils�� ���� ������ ����
		// f1 �Լ�(/dept/list.do)���� ���
		// Flash : �� �� ��� �� �����
		reAttr.addFlashAttribute("resultMessage", message);
		
		return "redirect:/dept/list.do";
	}
	
	// �μ� ����
	@RequestMapping(value = "/dept/delete.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String delete(int deptid, RedirectAttributes reAttr) {
		int result = deptService.delete(deptid);
		
		String message = "���� �Ǽ� : " + result;
		logger.info(message);
		reAttr.addFlashAttribute("resultMessage", message);
		
		return "redirect:/dept/list.do";
	}

}
