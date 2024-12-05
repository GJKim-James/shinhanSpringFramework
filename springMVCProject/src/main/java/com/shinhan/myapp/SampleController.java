package com.shinhan.myapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.shinhan.myapp.model.DeptService;

@Controller // ��û�� ���� ������ return �Ǵ� �ٸ� ��û���� ���û
public class SampleController {
	
	@Autowired
	DeptService deptService;
	
	@RequestMapping("/test1.do")
	public String f1(Model dataStore) {
		dataStore.addAttribute("myName", "James");
		dataStore.addAttribute("score", "100");
		
		return "jsptest/test1";
	}
	
	@RequestMapping(value = "/test2.do", method = RequestMethod.GET)
	public String f2(Model model) {
		// f2(), f3(), f4() ����� �ٸ� �� ��� ���� ����
		model.addAttribute("dept", deptService.selectById(60));
		
		return "jsptest/test2";
	}
	
	@RequestMapping(value = "/test3.do", method = RequestMethod.GET)
	public ModelAndView f3() {
		// f2(), f3(), f4() ����� �ٸ� �� ��� ���� ����
		ModelAndView mv = new ModelAndView();
		mv.addObject("dept", deptService.selectById(80));
		mv.setViewName("jsptest/test2");
		
		return mv;
	}
	
	@GetMapping("/jsptest/test2.do")
	public void f4(Model model) {
		// f2(), f3(), f4() ����� �ٸ� �� ��� ���� ����
		model.addAttribute("dept", deptService.selectById(90));
	}

}
