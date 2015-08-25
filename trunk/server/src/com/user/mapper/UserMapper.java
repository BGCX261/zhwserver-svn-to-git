package com.user.mapper;

import java.util.List;
import java.util.Map;

import com.user.entity.User;

public interface UserMapper {
	
    /**
     * 创建用户
     * @param user 用户
     */
	void addUser(User user);
    /**
     * 查询符合条件的用户
     * @param startIndex 当前页面显示的第一条记录的索引
     * @param fetchSize  页面显示的条数
     * @param helper     条件
     * @return           符合条件的用户
     */
	List<User> loadAll(Map<Object, Object> condition);
    /**
     * 修改用户信息
     * @param user
     */
	void updateUser(User user);
    /**
     * 根据id获取用户
     * @param uId id
     * @return  用户
     */
	User getUserById(Integer userId);
    /**
     * 删除用户
     * @param user
     */
	void deleteUser(User user);
    /**
     * 根据条件获取用户的条数
     * @param helper  条件
     * @return   个数
     */
	Integer getCountUsers(Map<Object, Object> condition);

}
