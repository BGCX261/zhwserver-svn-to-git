package com.user.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 验证码校验类
 * @author Administrator
 */
public class VerifyValidator {
	
	public static boolean validate(HttpServletRequest request, String randText) {
		HttpSession session = request.getSession();
		Object sessionValue = session.getAttribute("rand");
		if (sessionValue == null || !sessionValue.toString().equals(randText.toLowerCase())) {
			return false;
		}
		return true;
	}
}
