package com.user.service.auth;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.entity.auth.Admin;
import com.user.entity.auth.Menu;
import com.user.mapper.AdminMapper;
import com.user.mapper.MenuMapper;
import com.user.service.base.BaseService;
import com.user.util.StringUtil;

@Service("menuService")
public class AuthServiceImpl extends BaseService implements AuthService {

	@Autowired
	private MenuMapper menuMapper;
	
	@Autowired
	private AdminMapper adminMapper;

	/**
	 * 得到菜单字符串
	 */
	public String getMenuStr(String contextPath) {
		List<Menu> menus = menuMapper.loadAll();
		if (menus.size() == 0) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for(Menu menu : menus) {
			sb.append("@").append(menu.getId()).append(",").append(menu.getPid()).append(",")
				.append("'" + menu.getName() + "'").append(",").append("'" + contextPath + menu.getLink() + "'").append(",")
				.append("'" + menu.getName() + "'").append(",").append("'contentFrame'");
		}
		String str = sb.toString();
		return str.length() > 0 ? str.substring(1) : "";
	}

	/**
	 * 登陆
	 */
	public boolean login(String userName, String password) {
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("username", userName);
		condition.put("password", StringUtil.MD5(password));
		List<Admin> results = adminMapper.login(condition);
		if (results.size() == 0) {
			return false;
		}
		return true;
	}
}
