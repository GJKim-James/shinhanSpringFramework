package com.shinhan.myapp.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shinhan.myapp.vo.MemberDTO;

import lombok.extern.slf4j.Slf4j;

/**
 * @WebFilter : Servlet 3�������� ����(�츮 Servlet ������ 2.5)
 * �׷��� pom.xml�� javax.servlet-api 3.0.1 dependency �߰�
 */
@WebFilter("*.do")
@Slf4j
public class LoginCheckFilter implements Filter {
	
    public LoginCheckFilter() {
        // TODO Auto-generated constructor stub
    }
    
	public void destroy() {
		// TODO Auto-generated method stub
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// ��û �����ϱ� ��
		HttpServletRequest req = (HttpServletRequest) request;
		
		// ��û�� �ּ� ����
		String contextPath = req.getServletContext().getContextPath(); // /firstzone
		String uri = req.getRequestURI(); // /firstzone/dept/list.do
		uri = uri.substring(contextPath.length());
		log.info("��û �ּ� : " + uri);
		
		// ��û �ּҰ� �α����̸� ��û��� �����ϰ� �α����� �ƴϸ� �α��� �ߴ��� üũ
		if (!uri.equals("/auth/login.do") && !uri.contains("/rest")) {
			HttpSession session = req.getSession();
			MemberDTO memberDTO = (MemberDTO) session.getAttribute("loginMember");
			
			if (memberDTO == null) {
				log.info("�α��� ����");
				
				// �α��� ȭ������ �̵�
				HttpServletResponse res = (HttpServletResponse) response;
				res.sendRedirect(contextPath + "/auth/login.do");
				return;
			}
		}
		
		chain.doFilter(request, response);
	}
	
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
