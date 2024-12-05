package com.shinhan.myapp.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.shinhan.myapp.model.MemberService;
import com.shinhan.myapp.vo.MemberDTO;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/auth")
@Slf4j
public class LoginController {
	
	@Autowired
	MemberService memberService;
	
	Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@GetMapping("/main.do")
	public void mainPage() {
		// ��û URL�� JSP �̸��� �����ϱ� ������ return ��� �ȴ�.
	}
	
	@GetMapping("/login.do")
	public void loginPage(HttpServletRequest request, Model model) {
		Map<String, ?> map = RequestContextUtils.getInputFlashMap(request);
		if (map != null) {
			String message = (String) map.get("resultMessage");
			log.info("[���� �޽���] : " + message);
			model.addAttribute("result", message);
		}
	}
	
	@PostMapping("/login.do")
	public String loginPost(String userid, String userpw, HttpServletRequest request, HttpSession session) {
		// request ����غ���
		logger.info(request.getRemoteAddr() + "���� ��û��");
		
		MemberDTO memberDTO = memberService.loginService(userid, userpw);
		if (memberDTO == null) { // ���̵� �������� ����
			logger.info("���̵� �������� ����");
		} else if (memberDTO.getMember_id().equals("-1")) { // ��й�ȣ ����
			logger.info("��й�ȣ ����");
		} else { // �α��� ����
			// �α��� ���� ���ǿ� ����
			session.setAttribute("loginMember", memberDTO);
			
			// ���� ȭ������ �̵�
			return "redirect:/auth/main.do";
		}
		
		// ���̵� �������� �ʰų� ��й�ȣ ������ ��� �ٽ� �α���
		return "redirect:/auth/login.do";
	}
	
	@GetMapping("/logout.do")
	public String logout(HttpSession session, RedirectAttributes reAttr) {
		session.invalidate();
		
		String message = "�α׾ƿ� �Ǿ����ϴ�.";
		log.info(message);
		reAttr.addFlashAttribute("resultMessage", message);
		
		return "redirect:login.do";
	}

}
