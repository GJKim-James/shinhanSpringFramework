package com.shinhan.myapp.controller2;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shinhan.myapp.vo.ParamVO;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/friday")
@Slf4j
public class FridayController {
	
	@RequestMapping("/one.do")
	public void f1() {
		// 페이지만 보여줌
	}
	
	/*
	 * 1. 하나의 input 받기 : String username
	 * 2. VO로 받기 : ParamVO param
	 * 3. Map으로 받기 : @RequestParam Map<String, String> map
	 */
	@GetMapping("/two.do")
	public String f2(@RequestParam(value = "userid", required = false) Integer userid2,
			ParamVO param,
			@RequestParam Map<String, String> map, // Map은 @RequestParam 필수
			String username) {
		// Integer 타입은 null 가능, int 타입은 불가능(required = false 추가 시)
		// required : 파라미터가 반드시 필요한지 여부(default = true)
		log.info("userid : " + userid2);
		log.info("ParamVO : " + param.toString());
		log.info("map : " + map);
		log.info("username : " + username);
		
		return "redirect:/dept/list.do";
	}

}
