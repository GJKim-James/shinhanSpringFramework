package com.shinhan.myapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

// 여러 가지 형태의 요청 학습
@Controller
public class SampleController3 {
	
	Logger logger = LoggerFactory.getLogger(SampleController3.class);
	
	@RequestMapping(value = {"/second1.do", "/second2.do"}, method = RequestMethod.GET)
	public String f1() {
		return "jsptest/first1";
	}
	
	@RequestMapping(value = {"/second3.do"}, method = RequestMethod.POST)
	public String f2(@RequestParam("userid") String userid2, @RequestParam("userpw") int userpw) {
		logger.info("아이디 : " + userid2);
		logger.info("비밀번호 : " + userpw);
		
		return "jsptest/second3";
	}
	
	// userid가 gjk0635를 가지고 있고, userpw 파라미터가 있어야 하고, email 파라미터는 없어야 한다는 의미.
	@RequestMapping(value = "/second4.do", params = {"userid=gjk0635", "userpw", "!email"})
	public String f3(String userid, String userpw) {
		logger.info("아이디 : " + userid);
		logger.info("비밀번호 : " + userpw);
		
		return "jsptest/second3";
	}

}
