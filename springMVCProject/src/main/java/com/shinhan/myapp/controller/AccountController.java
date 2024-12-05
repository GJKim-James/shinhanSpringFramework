package com.shinhan.myapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shinhan.myapp.model.AccountService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/account")
@Slf4j
public class AccountController {
	
	@Autowired
	AccountService accountService;
	
	@ResponseBody // 응답 문서를 만들어서 data를 보낼 때 사용(JSP 페이지가 없음), response.getWriter().append("")와 동일
	@GetMapping(value = "transfer.do", produces = "text/plain;charset=utf-8")
	public String transfer() {
		accountService.transferService();
		
		return "이체 서비스 완료"; // 이 문자들이 ResponseBody로 인해 전달됨
	}

}
