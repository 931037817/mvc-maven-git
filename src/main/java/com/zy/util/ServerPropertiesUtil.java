package com.zy.util;

import java.io.InputStream;
import java.util.Properties;

public class ServerPropertiesUtil {
	public static Properties p = new Properties();
	static {
		InputStream inputStream = null;
		try {
			inputStream = ServerPropertiesUtil.class.newInstance().getClass()
					.getClassLoader()
					.getResourceAsStream("wechat.properties");
			p.load(inputStream);

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}
}
