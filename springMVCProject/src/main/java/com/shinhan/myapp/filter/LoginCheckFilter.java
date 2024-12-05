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
 * @WebFilter : Servlet 3버전부터 지원(우리 Servlet 버전은 2.5)
 * 그래서 pom.xml에 javax.servlet-api 3.0.1 dependency 추가
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
		// 요청 수행하기 전
		HttpServletRequest req = (HttpServletRequest) request;
		
		// 요청의 주소 얻어보기
		String contextPath = req.getServletContext().getContextPath(); // /firstzone
		String uri = req.getRequestURI(); // /firstzone/dept/list.do
		uri = uri.substring(contextPath.length());
		log.info("요청 주소 : " + uri);
		
		// 요청 주소가 로그인이면 요청대로 수행하고 로그인이 아니면 로그인 했는지 체크
		if (!uri.equals("/auth/login.do") && !uri.contains("/rest")) {
			HttpSession session = req.getSession();
			MemberDTO memberDTO = (MemberDTO) session.getAttribute("loginMember");
			
			if (memberDTO == null) {
				log.info("로그인 안함");
				
				// 로그인 화면으로 이동
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
