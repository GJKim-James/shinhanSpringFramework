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
	
	@ResponseBody // ���� ������ ���� data�� ���� �� ���(JSP �������� ����), response.getWriter().append("")�� ����
	@GetMapping(value = "transfer.do", produces = "text/plain;charset=utf-8")
	public String transfer() {
		accountService.transferService();
		
		return "��ü ���� �Ϸ�"; // �� ���ڵ��� ResponseBody�� ���� ���޵�
	}

}
