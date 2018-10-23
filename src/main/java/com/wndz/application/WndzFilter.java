package com.wndz.application;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WndzFilter implements Filter {

	@Override
	public void destroy() {
		System.out.println("CustomFilter过滤器销毁");

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		if (request.getRequestURI().equals("/servletTest")) {
			// 满足条件,chain首先检查还有没有其他有的话就进行下一个过滤器的chain,没的话直接通过servletRequest的请求,处理每一个Servlet时都走一次Filter,到下一个链接,适用于所有servlet
			chain.doFilter(servletRequest, servletResponse);
			return;
		}
		// 不行的原因是浏览器只把servletTest过滤了，跳转到error.html还是不符合过滤条件，就继续进行过滤，然后不符合条件，又跳到error.html，还是不符合就会进入到一个死循环
		// RequestDispatcher
		// dispatch=request.getRequestDispatcher(request.getContextPath()+"/templates/select.html");
		// dispatch.forward(servletRequest,servletResponse);
		// response.sendRedirect(request.getContextPath()+"/templates/error.html");
		//是那个页面就顺利登陆进去，不是那个页面就返回那个页面的意思，并不是是那个页面就登陆进去，不是那个页面就到其他页面这样，这样其他页面也被过滤了，一样进不去进入死循环，错误就跳转其他页面就没必要过滤器了，前面直接跳转或者拦截器就行，过滤器就是要保持唯一性的页面，其他页面也不能去这样
		response.sendRedirect(request.getContextPath() + "/servletTest");
		return;
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("=======初始化过滤器=========");

	}

}
