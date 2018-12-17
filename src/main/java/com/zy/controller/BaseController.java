package com.zy.controller;

import java.io.BufferedReader;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BaseController {
	
	// 日志..
	protected final Log log = LogFactory.getLog(this.getClass());
	protected final String className = this.getClass().getName();
	
	/**
	 * 获取request中body的值
	 * @Description: TODO
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author Bean Zhou
	 * @date 2016年12月5日
	 */
	public String getRequestBody(HttpServletRequest request){
		String str, wholeStr = "";
		try {
			request.setCharacterEncoding("UTF-8");
			BufferedReader br = request.getReader();
			while((str = br.readLine()) != null){
			wholeStr += str;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return wholeStr;
	}

}
