package com.shinhan.myapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

// ���� ���� ������ ��û �н�
@Controller
public class SampleController3 {
	
	Logger logger = LoggerFactory.getLogger(SampleController3.class);
	
	@RequestMapping(value = {"/second1.do", "/second2.do"}, method = RequestMethod.GET)
	public String f1() {
		return "jsptest/first1";
	}
	
	@RequestMapping(value = {"/second3.do"}, method = RequestMethod.POST)
	public String f2(@RequestParam("userid") String userid2, @RequestParam("userpw") int userpw) {
		logger.info("���̵� : " + userid2);
		logger.info("��й�ȣ : " + userpw);
		
		return "jsptest/second3";
	}
	
	// userid�� gjk0635�� ������ �ְ�, userpw �Ķ���Ͱ� �־�� �ϰ�, email �Ķ���ʹ� ����� �Ѵٴ� �ǹ�.
	@RequestMapping(value = "/second4.do", params = {"userid=gjk0635", "userpw", "!email"})
	public String f3(String userid, String userpw) {
		logger.info("���̵� : " + userid);
		logger.info("��й�ȣ : " + userpw);
		
		return "jsptest/second3";
	}

}
