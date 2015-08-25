package com.user.mapper;

import java.util.List;
import com.user.entity.User;
import com.user.entity.json.Rest;

public interface RestMapper {
	
    /**
     * 将这对KEY/VALUE存起来
     * @param key
     * @param value
     */
	void saveDB(Rest test);
    /**
     * 将所有的KEY/VALUE展示出来
     * @param condition
     * @return
     */
	List<User> loadAll();

}
