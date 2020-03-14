package com.chenjw.security.securityweb.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;


@RestController
public class securityController {
	
	//用来获取当前请求的session请求
	private RequestCache requestCache = new HttpSessionRequestCache();
	
	//用来实现界面的跳转
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	/**
	 * @throws IOException 
	 * @Description: 当需要身份认证时跳转到这里
	 * @date: 2020-03-14
	 * @param request
	 * @param response
	 * @return
	 * @throws
	 */
	@RequestMapping("/authentication/request")
	@ResponseStatus(code = HttpStatus.UNAUTHORIZED)  //如果没有跳转，则返回请求401，UNAUTHORIZED就是401
	public String RequireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//获取引发跳转的请求
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		if (savedRequest != null) {
			//得到引发跳转的请求
			String redirectUrl = savedRequest.getRedirectUrl();
			
			//判断跳转请求的url是否为一个页面
			if (redirectUrl.endsWith(".html")) {
				String url = "/mylogin.html";
				redirectStrategy.sendRedirect(request, response, url);
			}
		}
		JSONObject json = new JSONObject();
		json.put("data", "访问的服务需要身份认证，清引导用户到登陆页！");
		return JSONObject.toJSONString(json);
	}
}
