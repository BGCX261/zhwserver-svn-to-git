package com.user.service;

import com.user.entity.User;
import com.user.entity.page.Page;

public interface UserService {
	/**
	 * 创建用户
	 * @param user
	 */
	void createUser(User user);
	/**
	 * 删除用户
	 * @param user
	 */
	void removeUser(User user);
	/**
	 * 修改用户
	 * @param user
	 */
	void modifyUser(User user);
	/**
	 * 根据id获取用户
	 * @param uId
	 * @return 用户
	 */
	User getUserById(Integer uId);
	/**
	 * 根据条件封装分页对象
	 * @param page 分页的对象
	 * @param helper 条件
	 * @return
	 */
	Page loadAll(Page page);
}
