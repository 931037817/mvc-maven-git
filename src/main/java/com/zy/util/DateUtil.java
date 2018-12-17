package com.zy.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	/**
	* 获取当前时间格式为2018-12-07 17:18:00
	* @author: jiangyuzhuang
	* @createTime: 2018年12月7日 下午5:17:51
	* @history:
	* @return String
	*/
	public static String getNowDate(){
		Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");		
		return sdf.format(now);
	}
}
