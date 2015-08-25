package com.user.servlet;

import javax.servlet.http.HttpServlet;
import org.apache.log4j.Logger;
import com.user.config.ConfigKeys;
import com.user.config.MessageConfig;
import com.user.config.SystemConfig;

/**
 * 系统BASESERVLET
 * @author Administrator
 *
 */
public class BaseServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = Logger.getLogger(BaseServlet.class);
       
    protected void startApplication() {
    	try {
    		logger.info("application initing.....");
        	String configFile = this.getServletConfig().getInitParameter(ConfigKeys.INITCONFIG_KEY);
        	String appPath = this.getServletContext().getRealPath("/");
        	SystemConfig.buildConfig(appPath, appPath + "WEB-INF\\classes\\" + configFile);
        	MessageConfig.buildConfig(appPath + "WEB-INF\\classes\\message.properties");
		} catch (Exception e) {
			logger.error("加载启动参数失败 " + e);
			e.printStackTrace();
		}
    }
    
    
}
