package com.infiniteskill.mvc.interceptors;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.infiniteskill.mvc.HitCounter;

public class GlobalInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private HitCounter counter;

	// It will intercept request before Controller handles it
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		request.setAttribute("currentDate", new Date());
		counter.setHits(counter.getHits() + 1);
		System.out.println(counter.getHits());
		
		return super.preHandle(request, response, handler);
	}
	
}
