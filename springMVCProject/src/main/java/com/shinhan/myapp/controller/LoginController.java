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
		// 요청 URL과 JSP 이름이 동일하기 때문에 return 없어도 된다.
	}
	
	@GetMapping("/login.do")
	public void loginPage(HttpServletRequest request, Model model) {
		Map<String, ?> map = RequestContextUtils.getInputFlashMap(request);
		if (map != null) {
			String message = (String) map.get("resultMessage");
			log.info("[받은 메시지] : " + message);
			model.addAttribute("result", message);
		}
	}
	
	@PostMapping("/login.do")
	public String loginPost(String userid, String userpw, HttpServletRequest request, HttpSession session) {
		// request 사용해보기
		logger.info(request.getRemoteAddr() + "에서 요청함");
		
		MemberDTO memberDTO = memberService.loginService(userid, userpw);
		if (memberDTO == null) { // 아이디 존재하지 않음
			logger.info("아이디가 존재하지 않음");
		} else if (memberDTO.getMember_id().equals("-1")) { // 비밀번호 오류
			logger.info("비밀번호 오류");
		} else { // 로그인 성공
			// 로그인 정보 세션에 저장
			session.setAttribute("loginMember", memberDTO);
			
			// 메인 화면으로 이동
			return "redirect:/auth/main.do";
		}
		
		// 아이디 존재하지 않거나 비밀번호 오류일 경우 다시 로그인
		return "redirect:/auth/login.do";
	}
	
	@GetMapping("/logout.do")
	public String logout(HttpSession session, RedirectAttributes reAttr) {
		session.invalidate();
		
		String message = "로그아웃 되었습니다.";
		log.info(message);
		reAttr.addFlashAttribute("resultMessage", message);
		
		return "redirect:login.do";
	}

}
