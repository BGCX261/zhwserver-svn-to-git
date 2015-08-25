package com.user.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

/**
 * 系统初始化servlet
 * @author Administrator
 */
public class InitServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
	public void init(ServletConfig conf) throws ServletException {
		super.init();
		try {
			super.init(conf);
		} catch (ServletException e1) {
			e1.printStackTrace();
		}
		super.startApplication();
	}
}
