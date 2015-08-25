package com.user.intecepter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.user.cache.CacheKeys;
import com.user.cache.ehcache.CacheUtil;
import com.user.util.DateUtil;

public class MyInteceptor implements HandlerInterceptor{
	
	private Logger logger = Logger.getLogger(MyInteceptor.class);
	
	/**
	 * 注入缓存工具类
	 */
	protected CacheUtil cache;
	@Resource(name="cacheUtil")
	public void setEhCache(CacheUtil cache) {
	   this.cache = cache;
	}
	
	private ThreadLocal<Long> beginTime = new ThreadLocal<Long>();
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		//登陆判断
		if (!isLogin(request) && !request.getRequestURI().contains("login")) {
			response.sendRedirect(request.getContextPath());
			return false;
		}
		beginTime.set(System.nanoTime());
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		incPV();
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		logger.info("URL: " + request.getRequestURI() + " cost time :" + (System.nanoTime() - beginTime.get())/1000000 + "ms");
		beginTime.remove();
	}
	
	/**
	 * 增加PV值
	 * 粗粒度数字无需加锁，异常安静处理
	 */
	private void incPV() {
		try {
			String todayKey = CacheKeys.PV_KEY + DateUtil.today();
			Object cacheValue = cache.get(todayKey);
			long newValue = cacheValue == null ? 0 : (Long)cacheValue;
			cache.put(todayKey, newValue + 1);
		} catch (Exception e) {
			e.printStackTrace(); 
		} 
	}
	
	/**
	 * 判断是否登陆
	 * @param request
	 * @return
	 */
	private boolean isLogin(HttpServletRequest request) {
		Object sessionUser = request.getSession().getAttribute("username");
		if (sessionUser == null || sessionUser.equals("")) {
			return false;
		}
		return true;
	}
}
