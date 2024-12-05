package com.shinhan.myapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/jsptest") // class(type) level에서 요청 주소 작성(공통된 경로 사용 시 유용)
public class SampleController2 {
	
	@GetMapping("/first1.do") // function(method) level에서 요청 주소 작성, /jsptest/first1.do로 연결
	public void f1() {
		
	}
	
	@RequestMapping("/first2.do") // /jsptest/first2.do로 연결
	public void f2() {
		
	}
	
	@RequestMapping(value = "/first3.do", method = RequestMethod.GET) // /jsptest/first3.do로 연결
	public void f3() {
		
	}

}
