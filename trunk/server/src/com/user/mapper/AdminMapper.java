package com.user.mapper;

import java.util.List;
import java.util.Map;

import com.user.entity.auth.Admin;

public interface AdminMapper {
	
    /**
     * 登陆
     * @param userName
     * @param password
     * @return
     */
	List<Admin> login(Map<String, String> condition);
   
}
