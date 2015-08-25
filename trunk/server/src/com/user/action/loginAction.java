package com.user.action;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.user.action.base.BaseAction;
import com.user.config.MessageConfig;
import com.user.service.auth.AuthService;
import com.user.util.VerifyValidator;

@Controller
@RequestMapping("/admin")
public class loginAction extends BaseAction {
	
	@Autowired
	private AuthService authService;
	
	/**
	 * 登陆方法
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/login")
	public String login(Model model, HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//验证码出错
		if (!VerifyValidator.validate(request, request.getParameter("rand"))) {
			model.addAttribute("errormsg", MessageConfig.getValue("verify_error"));
			return "index";
		}
		if (!authService.login(username, password)) {
			model.addAttribute("errormsg", MessageConfig.getValue("login_error"));
			return "index";
		}
		request.getSession().setAttribute("username", username);
		model.addAttribute("menustr", authService.getMenuStr(request.getContextPath()));
		return "main";
	}
	
	/**
	 * 退出系统
	 * @param request
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().removeAttribute("username");
		return "index";
	}
}
