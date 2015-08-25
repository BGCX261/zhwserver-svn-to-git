package com.user.mapper;

import java.util.List;
import com.user.entity.auth.Menu;

public interface MenuMapper {
	
    /**
     * 查询所有URL
     * @return
     */
	List<Menu> loadAll();
   
}
