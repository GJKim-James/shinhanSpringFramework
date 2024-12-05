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
		// �������� ������
	}
	
	/*
	 * 1. �ϳ��� input �ޱ� : String username
	 * 2. VO�� �ޱ� : ParamVO param
	 * 3. Map���� �ޱ� : @RequestParam Map<String, String> map
	 */
	@GetMapping("/two.do")
	public String f2(@RequestParam(value = "userid", required = false) Integer userid2,
			ParamVO param,
			@RequestParam Map<String, String> map, // Map�� @RequestParam �ʼ�
			String username) {
		// Integer Ÿ���� null ����, int Ÿ���� �Ұ���(required = false �߰� ��)
		// required : �Ķ���Ͱ� �ݵ�� �ʿ����� ����(default = true)
		log.info("userid : " + userid2);
		log.info("ParamVO : " + param.toString());
		log.info("map : " + map);
		log.info("username : " + username);
		
		return "redirect:/dept/list.do";
	}

}
