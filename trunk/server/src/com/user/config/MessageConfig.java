package com.user.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 消息配置文件
 * @author Administrator
 *
 */
public class MessageConfig {
	
	private static final Logger logger = Logger.getLogger(MessageConfig.class);
	
	private static Properties configMaps = new Properties();
	
	private static boolean haveLoaded = false;			//防止被外部程序再调用build
	
	/**
	 * 构建消息配置文件
	 * @param configFile
	 */
	public static void buildConfig(String configFile) {
		if (haveLoaded) {
			return;
		}
		if (configFile == null) {
			throw new InvalidParameterException("defaultConfig could not be null");
		}
		FileInputStream input = null;
		try {
			input = new FileInputStream(configFile);
			configMaps.load(input);
			for(Object key : configMaps.keySet()){
				logger.info("加载message配置键值对:" + key + "----->" + configMaps.getProperty(key.toString()));
			}
			input.close();
		} catch (IOException e) {	
			logger.error(e);
			e.printStackTrace();
		} finally {
			haveLoaded = true;
			try {
				if (input != null) {
					input.close();
				}
			} catch (IOException e) {
				logger.error(e);
			}
		}
	}
	
	public static String getValue(String key) {
		return configMaps.getProperty(key);
	}
 
	public static int getIntValue(String field) {
		return Integer.parseInt(getValue(field));
	}
}
