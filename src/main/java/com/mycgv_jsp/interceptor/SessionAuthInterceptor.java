package com.mycgv_jsp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SessionAuthInterceptor extends HandlerInterceptorAdapter{
	/*
	 * preHandle : Controller�� �����ϱ� ���� ����Ǵ� �޼ҵ�
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
	    throws Exception {
		//Ŭ���̾�Ʈ(������)�� ��û Ȯ�� - ���� ��ü ��������
		HttpSession session = request.getSession();
		
		//sid ���� Ȯ���ϱ�
		String sid = (String)session.getAttribute("sid");
		if(sid == null) {
			//�α����� �ȵǾ� �ִ� �����̹Ƿ� �α��������� �̵�
			response.sendRedirect("/mycgv_jsp/login.do");
			return false;
			
		}
		
		return true;
	}
	
}
