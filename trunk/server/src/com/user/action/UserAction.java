package com.user.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.user.action.base.BaseAction;
import com.user.entity.User;
import com.user.entity.page.Page;
import com.user.service.UserService;

@Controller
@RequestMapping("/user")
public class UserAction extends BaseAction {

    @Autowired
    private UserService userService;

    /**
     * 创建用户输入
     * @return
     */
    @RequestMapping("/input")
    public String input() {
        return "user/input_user";
    }

    /**
     * 创建用户
     * @return
     */
    @RequestMapping("/add")
    public String create(User user) {
        userService.createUser(user);
        return "redirect:list.do";
    }

    /**
     * 根据id获取用户
     * @return 修改视图
     */
    @RequestMapping("/{userId}")
    public String preModify(@PathVariable("userId") int userId, Model model) {
        User user = userService.getUserById(userId);
        model.addAttribute("user", user);
        return "user/edit_user";
    }

    /**
     * 更新用户信息
     * @return 用户列表
     */
    @RequestMapping("/modify")
    public String modify(User user) {
        userService.modifyUser(user);
        return "redirect:list.do";
    }

    /**
     * 注销用户
     * @return 用户列表
     */
    @RequestMapping("/delete")
    public String remove(User user) {
        userService.removeUser(user);
        return "redirect:list.do";
    }

    /**
     * 用户列表
     * @return
     */
    @RequestMapping("/list")
    public String list(Model model, Page page, User queryUser) {
        if (queryUser != null) {
            page.setCondition(queryUser);
        }
        super.processPage(page);
        page = userService.loadAll(page);
        model.addAttribute("page", page);
        model.addAttribute("queryUser", queryUser);
        return "user/list_user";
    }
}
