package com.shinhan.myapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.slf4j.Slf4j;

// ���� �߻� �� ���������� ó���ϴ� Controller
// 500 : ���� ����
// 404 : URL(��û �ּ�) ����; �������� �ʴ� ������
@ControllerAdvice
@Slf4j
public class ExceptionAdvice {
	
	@ExceptionHandler(Exception.class)
	public String error500(Exception ex, Model model) {
		log.info("���� �߻� Ŭ���� : " + ex.getClass().getName());
		log.info("���� �߻� �޽��� : " + ex.getMessage());
		ex.printStackTrace();
		
		model.addAttribute("message", "Ȩ������ ������~!");
		
		return "error/error500";
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public String error404(HttpServletRequest request) {
		log.info("�߸��� ��û �ּ� : " + request.getRequestURI());
		
		return "error/error404";
	}

}
