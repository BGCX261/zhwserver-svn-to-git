package com.user.service.impl;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.user.entity.User;
import com.user.entity.page.Page;
import com.user.mapper.UserMapper;
import com.user.service.UserService;
import com.user.service.base.BaseService;

@Service("userService")
@Transactional
public class UserServiceImpl extends BaseService implements UserService {

	private Logger logger = Logger.getLogger(UserServiceImpl.class.getName());
	@Autowired
	private UserMapper userMapper;

	public void createUser(User user) {
		logger.debug(user.getUserName() + "===" + user.getUserNo() + "===" + user.getUserStatus());
		userMapper.addUser(user);
	}

	public User getUserById(Integer uId) {
		return userMapper.getUserById(uId);
	}

	public Page loadAll(Page page) {
		page.setTotalRecNum(new Long(userMapper.getCountUsers(page.getCondition())));
		page.setContent(userMapper.loadAll(page.getCondition()));
		return page;
	}

	public void modifyUser(User user) {
		ehcache.get("test");
		userMapper.updateUser(user);

	}

	public void removeUser(User user) {
		userMapper.deleteUser(user);
	}

}
