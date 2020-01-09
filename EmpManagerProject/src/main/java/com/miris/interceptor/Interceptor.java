package com.miris.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class Interceptor extends HandlerInterceptorAdapter{
	private static final Logger logger = LoggerFactory.getLogger(Interceptor.class);
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		logger.info("Session 값 체크 ::");
		HttpSession session = request.getSession();        
        String userId = (String)session.getAttribute("userId");
        
        logger.info("현재 로그인 된 ID : " + userId);
        
        if(userId==null || userId.trim().equals("")){
            logger.info(">> interceptor catch!!! userId is null.. ");            
            session.invalidate();            
            response.sendRedirect(request.getContextPath() + "/login.do");
            
            return false;            
        }
        
        return true;     
	}
	
}
