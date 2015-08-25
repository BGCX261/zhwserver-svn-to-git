package com.user.action;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.user.action.base.BaseAction;
import com.user.config.ConfigKeys;
import com.user.config.SystemConfig;
import com.user.entity.json.Rest;
import com.user.entity.json.RestObject;
import com.user.mapper.RestMapper;

@Controller
@RequestMapping("/rest")
public class RestAction extends BaseAction {

    private static final int SUCCESS_CODE = 0;
    private static final int VALIDATE_ERROR_CODE = 1;
    private static final int KEY_IS_NULL_CODE = 2;
    private static final int KEY_LARGE_ERROR_CODE = 3;
    private static final int VALUE_LARGE_ERROR_CODE = 4;
    private static final String SUCCESS_MESSAGE = "success";
    private static final String VALIDATE_ERROR_MESSAGE = "v is valid";
    private static final String KEY_IS_NULL_MESSAGE = "key is null";
    private static final String KEY_LARGE_ERROR_MESSAGE = "key too large";
    private static final String VALUE_LARGE_ERROR_MESSAGE = "value too large";

    private Logger logger = Logger.getLogger(RestAction.class);

    @Autowired
    private RestMapper mapper;

    /**
     * 返回JSON字符串
     * @param v
     * @param key
     * @param value
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public Object save(String v, String key, String value) {
        logger.info("v : " + v + " ; key : " + key + " , value : " + value);
        if (v == null || !v.equals(SystemConfig.getValue(ConfigKeys.REST_VALIDATE_KEY))) {
            return new RestObject(VALIDATE_ERROR_CODE, VALIDATE_ERROR_MESSAGE);
        }
        if (key == null) {
            return new RestObject(KEY_IS_NULL_CODE, KEY_IS_NULL_MESSAGE);
        } else if (key.length() >= 255) {
            return new RestObject(KEY_LARGE_ERROR_CODE, KEY_LARGE_ERROR_MESSAGE);
        }
        if (value != null && value.length() >= 255) {
            return new RestObject(VALUE_LARGE_ERROR_CODE, VALUE_LARGE_ERROR_MESSAGE);
        }
        mapper.saveDB(new Rest(key, value));
        return new RestObject(SUCCESS_CODE, SUCCESS_MESSAGE);
    }
}
