package com.shinhan.myapp.controller2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// Spring 3 버전 : @Controller + @ResponseBody
@Controller
@ResponseBody
@RequestMapping("/rest")
public class SampleRestController {
	
	@GetMapping(value = "/test1.do", produces = "text/plain;charset=utf-8")
	public String f1() {
		return "Rest 방식 연습1";
	}

}
