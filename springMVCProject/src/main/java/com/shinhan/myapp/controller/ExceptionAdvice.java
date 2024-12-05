package com.shinhan.myapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.slf4j.Slf4j;

// 예외 발생 시 전역적으로 처리하는 Controller
// 500 : 서버 오류
// 404 : URL(요청 주소) 오류; 존재하지 않는 페이지
@ControllerAdvice
@Slf4j
public class ExceptionAdvice {
	
	@ExceptionHandler(Exception.class)
	public String error500(Exception ex, Model model) {
		log.info("예외 발생 클래스 : " + ex.getClass().getName());
		log.info("예외 발생 메시지 : " + ex.getMessage());
		ex.printStackTrace();
		
		model.addAttribute("message", "홈페이지 공사중~!");
		
		return "error/error500";
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public String error404(HttpServletRequest request) {
		log.info("잘못된 요청 주소 : " + request.getRequestURI());
		
		return "error/error404";
	}

}
