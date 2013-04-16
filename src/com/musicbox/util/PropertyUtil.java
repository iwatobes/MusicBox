package com.musicbox.util;

import java.util.Map;
import java.util.WeakHashMap;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



public class PropertyUtil {

	private static Log	logger	= LogFactory.getLog(PropertyUtil.class);
	
	
	private static Map	table	= new WeakHashMap();

	public PropertyUtil() {
	}

	public static String getPropertyValue(String propertyFileName, String key)
			throws Exception {
		if (StringUtils.isEmpty(key))
			throw new Exception("key is blank");

		String result = null;

		PropertiesConfiguration config = getConfig(propertyFileName);
		result = (String) config.getProperty(key);

		return result;
	}

	public static String[] getPropertyValueArray(String propertyFileName, String key)
			throws Exception {
		if (StringUtils.isEmpty(key))
			throw new Exception("key is blank");

		String[] result = null;
		PropertiesConfiguration config = getConfig(propertyFileName);
		result = (String[]) config.getStringArray(key);

		return result;
	}

	public static String getMessageById(String errId, String[] errParams)
			throws Exception {
		String msg = null;

		msg = getPropertyValue(AppConstants.MUSIC_GIFTBOX_CONFIG, errId);
		if (StringUtils.isNotEmpty(msg)) {
			if (errParams != null) {
				for (int i = 0; i < errParams.length; i++) {
					msg = msg.replace("{" + i + "}", StringUtils.defaultString(errParams[i]));
				}
			}
		}

		return msg;
	}

	private static PropertiesConfiguration getConfig(String propertyFileName)
			throws Exception {
		if (StringUtils.isEmpty(propertyFileName))
			throw new Exception("propertyFileName is blank");

		try {
			PropertiesConfiguration config = (PropertiesConfiguration) table.get(propertyFileName);
			if (config == null) {
				config = new PropertiesConfiguration(propertyFileName);
				table.put(propertyFileName, config);
			}
			return config;
		}
		catch (ConfigurationException e) {
			logger.error(e.getMessage(), e);
			throw new Exception(e.getMessage());
		}
	}
	
	

}
