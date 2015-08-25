package com.user.service.auth;

/**
 * 权限相关操作
 * @author Administrator
 */
public interface AuthService {
	
	/**
	 * 得到菜单字符串
	 * @return
	 */
	public String getMenuStr(String contextPath);
	
	/**
	 * 登陆
	 * @param userName
	 * @param password
	 * @return
	 */
	public boolean login(String userName, String password);
}
