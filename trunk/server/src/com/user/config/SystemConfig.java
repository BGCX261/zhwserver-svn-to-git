package com.user.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 系统属性配置
 * @author Administrator
 *
 */
public class SystemConfig {
	
	private static final Logger logger = Logger.getLogger(SystemConfig.class);
	
	private static Properties configMaps = new Properties();
	
	private static boolean haveLoaded = false;			//防止被外部程序再调用build
	
	/**
	 * 构建系统配置信息
	 * @param appPath
	 * @param configFile
	 */
	public static void buildConfig(String appPath, String configFile) {
		if (haveLoaded) {
			return;
		}
		if (configFile == null) {
			throw new InvalidParameterException("defaultConfig could not be null");
		}
		reset();
		configMaps.put(ConfigKeys.APPLICATION_PATH, appPath);
		loadConfig(configFile);
		haveLoaded = true;
	}
	
	/**
	 * 加载到property
	 * @param configFile
	 */
	private static void loadConfig(String configFile) {
		FileInputStream input = null;
		try {
			input = new FileInputStream(configFile);
			configMaps.load(input);
			for(Object key : configMaps.keySet()){
				logger.info("加载配置键值对:" + key + "----->" + configMaps.getProperty(key.toString()));
			}
			input.close();
		} catch (IOException e) {	
			logger.error(e);
			e.printStackTrace();
		} finally {
			try {
				if (input != null) {
					input.close();
				}
			} catch (IOException e) {
				logger.error(e);
			}
		}
	}
	
	private static void reset() {
		configMaps.clear();
	}

	public static String getValue(String key) {
		return configMaps.getProperty(key);
	}
 
	public static int getIntValue(String field) {
		return Integer.parseInt(getValue(field));
	}
	 
	public static boolean getBoolValue(String field) {
		return "true".equals(getValue(field));
	}

	public static String getApplicationPath() {
		return getValue(ConfigKeys.APPLICATION_PATH);
	}

	public static String getContextPath() {
		return "";
	}
}
