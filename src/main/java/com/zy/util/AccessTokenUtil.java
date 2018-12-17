package com.zy.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.zy.client.Cache;
import com.zy.client.CacheManager;

/**
* @desc: 获取微信access_token
* @author: jiangyuzhuang
* @createTime: 2018年12月11日 下午2:09:07
* @history:
* @version: v1.0
*/
public class AccessTokenUtil {

	/**
	* 获取access_token
	* @author: jiangyuzhuang
	* @createTime: 2018年12月5日 下午2:10:06
	* @history:
	* @return String
	*/
	public static String getWeixinAccessToken(String appId,String appSecret){
		Cache cache = CacheManager.getCacheInfo("access_token");
		String token = "";
		if(cache == null || StringUtils.isEmpty(cache.getValue())){
			token = getToken(appId,appSecret);
			
			//设置缓存
			cache = new Cache();
			cache.setKey("access_token");
	        cache.setValue(token);
	        CacheManager.putCache("access_token", cache); //access_token的有效期是7200秒（两小时）,加入缓存不设置过期时间，不可用
		}else{
			if(cache.isExpired()){
				token = getToken(appId,appSecret);
				
				//设置缓存
				cache = new Cache();
				cache.setKey("access_token");
		        cache.setValue(token);
		        CacheManager.putCache("access_token", cache); //access_token的有效期是7200秒（两小时）,加入缓存不设置过期时间，不可用
			}else{
				token = (String) cache.getValue();
			}
		}
		System.out.println("weixin access_token : " + token);
		return token;
	}
	
	/**
	 * 
	 * Description:调用getweixin服务获取token
	 * @return
	 * @Note
	 * Author: chenxi
	 * Date: 2017年12月1日 下午3:14:07
	 * Version: 1.0
	 */
	private static String getToken(String appId,String appSecret){
		HttpClient c = new HttpClient();
		String getTokenUrl ="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appId+"&secret="+appSecret;
		PostMethod method = new PostMethod(getTokenUrl);
		String access_token = "";
		int code;
		try {
			code = c.executeMethod(method);
			if(code == 200){
				byte[] responseBody = method.getResponseBody();
				access_token = new String(responseBody,"utf-8");
              JSONObject jsonObject=JSONObject.parseObject(access_token);
				access_token = jsonObject.getString("access_token");
				System.out.println("查询access_token="+access_token);
			}else{
				System.out.println("获取token失败 ， code : "+code);
			}
		} catch(Exception e) {
			System.out.println("获取token失败 ， exception : "+e.toString());
			e.printStackTrace();
		}
		return access_token;
	}
	
}
