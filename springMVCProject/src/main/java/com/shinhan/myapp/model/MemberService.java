package com.shinhan.myapp.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinhan.myapp.vo.MemberDTO;

@Service // '@Component + Service' ¿ªÇÒ
public class MemberService {
	
	@Autowired
	MemberDAO memberDAO;
	
	public MemberDTO loginService(String id, String pw) {
		return memberDAO.login(id, pw);
	}

}
