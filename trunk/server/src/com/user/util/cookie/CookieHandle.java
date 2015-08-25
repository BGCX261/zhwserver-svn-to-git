package com.user.util.cookie;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.user.config.ConfigKeys;
import com.user.config.SystemConfig;

/**
 * cookie操作类
 * @author Administrator
 */
public class CookieHandle {
	/**
	 * 新增cookie
	 * @param httpServletResponse
	 * @param name
	 * @param value
	 * @param maxAge
	 * @param domain
	 * @return
	 */
	public static int addCookie(HttpServletResponse httpServletResponse,
			String name, String value, int maxAge, String domain)  {
		try {
			System.out.println(value);
			Cookie cookie = new Cookie(name, URLEncoder.encode(value, SystemConfig.getValue( ConfigKeys.APPLICATION_CHARSET)));
			cookie.setMaxAge(maxAge);
			cookie.setPath(SystemConfig.getValue(ConfigKeys.COOKIE_PATH));
			if(domain!=null&&!domain.equals("")){
				cookie.setDomain(domain);
			}
			httpServletResponse.addCookie(cookie);
			return 0;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return -1;
		}
	}
	/**
	 * 添加cookie
	 * @param httpServletResponse
	 * @param name cookie名字
	 * @param value cookie值
	 * @param maxAge cookie最长时间
	 * @param domains  
	 * @throws UnsupportedEncodingException
	 */
	public static void addCookie(HttpServletResponse httpServletResponse,
			String name, String value, int maxAge, String[] domains)  {
		try {
			for (int i = 0; i < domains.length; ++i) {
				Cookie cookie = new Cookie(name, URLEncoder.encode(value, SystemConfig.getValue( ConfigKeys.APPLICATION_CHARSET)));
				cookie.setMaxAge(maxAge);
				cookie.setPath(SystemConfig.getValue(ConfigKeys.COOKIE_PATH));
				cookie.setDomain(domains[i]);
				httpServletResponse.addCookie(cookie);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取单个cookie值
	 * @param httpServletRequest
	 * @param key
	 * @param domains
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String getCookie(HttpServletRequest httpServletRequest,
			String key, String domains) {
		try {
			Cookie[] cookies = httpServletRequest.getCookies();
			if (cookies == null)
				return null;
			for (int i = 0; i < cookies.length; ++i)
				if ((cookies[i] != null) && (cookies[i].getName().equals(key))) {
					return URLDecoder.decode(cookies[i].getValue(), SystemConfig.getValue( ConfigKeys.APPLICATION_CHARSET));
				}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获取的所有cookie  返回格式cookieName=cookieValue
	 * @param httpServletRequest
	 * @return List<String>
	 * @throws UnsupportedEncodingException
	 */
	public static List<String> getCookie(HttpServletRequest httpServletRequest)
			throws UnsupportedEncodingException {
		Cookie[] cookies = httpServletRequest.getCookies();
		List<String> list = new ArrayList<String> ();
		if (cookies == null)
			return null;
		for (int i = 0; i < cookies.length; ++i){
			StringBuilder str=new StringBuilder(cookies[i].getName());
			str.append("=");
			str.append(URLDecoder.decode(cookies[i].getValue(),SystemConfig.getValue( ConfigKeys.APPLICATION_CHARSET)));
			list.add(str.toString());
		}
		return list;
	}
	/**
	 * 删除cookie  
	 * @param httpServletResponse
	 * @param httpServletRequest
	 * @param val
	 * @throws UnsupportedEncodingException
	 */
	public static void delCookie(HttpServletResponse httpServletResponse,
			HttpServletRequest httpServletRequest, String val)
			throws UnsupportedEncodingException {
		Cookie[] cookies = httpServletRequest.getCookies();
		if (cookies != null)
			for (int i = 0; i < cookies.length; ++i) {
				String str = cookies[i].getName();
				if (str.equals(val)) {
					cookies[i].setMaxAge(0);
					httpServletResponse.addCookie(cookies[i]);
				}
			}
	}
	
	/**
	 * 
	 *  @describe 删除cookie
	 *  @param httpServletResponse
	 *  @param paramString
	 *  @param domains
	 *  @throws UnsupportedEncodingException
	 */
	public static void delCookie(HttpServletResponse httpServletResponse,
			String paramString, String[] domains)
			throws UnsupportedEncodingException {
		for (int i = 0; i < domains.length; ++i) {
			Cookie cookie = new Cookie(paramString, null);
			cookie.setMaxAge(0);
			cookie.setPath("/");
			cookie.setDomain(domains[i]);
			httpServletResponse.addCookie(cookie);
		}
	}
}
