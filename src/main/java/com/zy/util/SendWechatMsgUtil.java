package com.zy.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

public class SendWechatMsgUtil {

	private final static Logger log = LoggerFactory.getLogger(SendWechatMsgUtil.class);
	/**
	*
	* 创建发送消息模板（模板 1  测试）
	* @author: jiangyuzhuang
	* @createTime: 2018年12月7日 下午4:33:00
	* @history:
	* @param openId
	* @param wechatTemplate
	* @param url
	* @param wechatTemplateFirst
	* @param wechatTemplateSecond
	* @param wechatTemplateThird
	* @param wechatTemplateFourth
	* @return Map<String,Object>
	*/
	public static Map<String,Object> createWechatMsg(String openId, String wechatTemplate, String url,
			String wechatTemplateFirst, String wechatTemplateProduct, String wechatTemplateAmount, String wechatTemplateRemark){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		
		Map<String,Object> firstMap = new HashMap<String,Object>();
		firstMap.put("value", wechatTemplateFirst);
		firstMap.put("color", "#173177");
		
		Map<String,Object> productMap = new HashMap<String,Object>();
		productMap.put("value", wechatTemplateProduct);
		productMap.put("color", "#173177");
		
		Map<String,Object> timeMap = new HashMap<String,Object>();
		timeMap.put("value", DateUtil.getNowDate());
		timeMap.put("color", "#173177");
		
		Map<String,Object> amountMap = new HashMap<String,Object>();
		amountMap.put("value", wechatTemplateAmount);
		amountMap.put("color", "#173177");
		
		Map<String,Object> remarkMap = new HashMap<String,Object>();
		remarkMap.put("value", wechatTemplateRemark);
		remarkMap.put("color", "#173177");
		
		Map<String,Object> dataMap = new HashMap<String,Object>();
		dataMap.put("first", firstMap);
		dataMap.put("product", productMap);
		dataMap.put("time", timeMap);
		dataMap.put("amount", amountMap);
		dataMap.put("remark", remarkMap);
		
		resultMap.put("touser", openId);
		resultMap.put("template_id", wechatTemplate);
		resultMap.put("url", url);
		resultMap.put("data", dataMap);
		
		return resultMap;
	}

	/**
	 * 
	 * Description:发送微信模板消息
	 * Author: chenxi
	 * Date: 2017年11月28日 下午2:34:13
	 * Version: 1.0
	 */
	public static boolean sendTemplateMsg(String token,Map<String, Object> map){  
        boolean flag=false;  
      
        String sendUrl = ServerPropertiesUtil.p.getProperty("wx.send.template.url");
        sendUrl = sendUrl.replace("ACCESS_TOKEN", token);  
        
        String gsonReturnStr = JSONObject.toJSONString(map);
        
        String rs = sendPost(sendUrl,gsonReturnStr);
        JSONObject jsonResult = JSONObject.parseObject(rs);
        if(jsonResult!=null){  
            int errorCode=jsonResult.getInteger("errcode");  
            String errorMessage=jsonResult.getString("errmsg");  
            log.info("模板消息发送结果errorCode={}",errorCode);
            log.info("模板消息发送结果errorMessage={}",errorMessage);
            if(errorCode==0){
                flag=true;
                log.info("模板消息发送结果={}",String.valueOf(flag));  
            }else{  
            	log.info("模板消息发送结果={}",String.valueOf(flag)); 
                flag=false;  
            }  
        }  
        return flag;  
	}
	
	 public static String sendPost(String url, String param) {
	        PrintWriter out = null;
	        BufferedReader in = null;
	        String result = "";
	        try {
	            URL realUrl = new URL(url);
	            // 打开和URL之间的连接
	            URLConnection conn = realUrl.openConnection();
	            // 设置通用的请求属性
	            conn.setRequestProperty("accept", "*/*");
	            conn.setRequestProperty("content-type", "application/json");
	            
	            conn.setRequestProperty("connection", "Keep-Alive");
	            conn.setRequestProperty("user-agent",
	                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
	            // 发送POST请求必须设置如下两行
	            conn.setDoOutput(true);
	            conn.setDoInput(true);
	            // 获取URLConnection对象对应的输出流
	            out = new PrintWriter(conn.getOutputStream());
	            // 发送请求参数
	            out.print(param);
	            // flush输出流的缓冲
	            out.flush();
	            // 定义BufferedReader输入流来读取URL的响应
	            in = new BufferedReader(
	                    new InputStreamReader(conn.getInputStream()));
	            String line;
	            while ((line = in.readLine()) != null) {
	                result += line;
	            }
	        } catch (Exception e) {
	            System.out.println("发送 POST 请求出现异常！"+e);
	            e.printStackTrace();
	        }
	        //使用finally块来关闭输出流、输入流
	        finally{
	            try{
	                if(out!=null){
	                    out.close();
	                }
	                if(in!=null){
	                    in.close();
	                }
	            }
	            catch(IOException ex){
	                ex.printStackTrace();
	            }
	        }
	        return result;
	    }
	
}
