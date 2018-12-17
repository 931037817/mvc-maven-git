package com.zy.util;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Sender {

	private static final Logger log = LoggerFactory.getLogger(Sender.class);
	
	public static Map<String,Object> sendSMS(String phone,String content){
		Map<String,Object> map=new HashMap<>();
		try {
			String jsonRsp = new MyHttpClient().httpGet(
					"http://sms.1xinxi.cn/asmx/smsservice.aspx",
					msg(content, phone));
			log.info("rsp={}",jsonRsp.toString());
			String [] rsp = jsonRsp.split(",");
			if( rsp!= null && rsp.length > 0  ){
				if( "0".equals( rsp[0] ) ){
					map.put("code","SUCCESS");
                  map.put("msg","短信发送成功!") ;
					log.info("短信发送成功!");
				}else if( "1".equals( rsp[0] ) ){
                  map.put("code","FAILD");
                  map.put("msg","含有敏感词汇");
					log.info("含有敏感词汇!");
				}else if( "2".equals( rsp[0] ) ){
                  map.put("code","FAILD");
                  map.put("msg","余额不足");
                  log.info("余额不足!");
				}else if( "3".equals( rsp[0] ) ){
                  map.put("code","FAILD");
                  map.put("msg","没有号码");
                  log.info("没有号码!");
				}else {
                  map.put("code","FAILD");
                  map.put("msg","短信发送败,状态号" + rsp[0]);
					log.info("短信发送败,状态号：" + rsp[0]);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
	public static Map<String,String> msg(String content, String mobile) 
    {
    	Map<String,String> paramsMap = new TreeMap<String,String>();
    	paramsMap.put("name", "zhoumeili@z-health.cn");
    	paramsMap.put("pwd", "EF9699889B5359F4F158C7B6EDE6");
    	paramsMap.put("content", content);
    	paramsMap.put("mobile", mobile);
    	paramsMap.put("sign", "短信测试");
    	paramsMap.put("type", "pt");
    	paramsMap.put("extno", "1");
        return paramsMap;
    }
}
