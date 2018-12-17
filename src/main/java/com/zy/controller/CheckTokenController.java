package com.zy.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.zy.util.AccessTokenUtil;
import com.zy.util.CheckSignatureUtil;
import com.zy.util.MessageUtil;
import com.zy.util.XMLUtil;

@Controller
@RequestMapping("/signature/")
public class CheckTokenController {

	private static Logger log = LoggerFactory.getLogger(CheckTokenController.class);
	
	private final String TOKEN = "scum";
	
	@Value("${appId}")
    private String appId;
	
	@Value("${appSecret}")
	private String appSecret;
	
	/**
	*
	* 验证签名
	* @author: jiangyuzhuang
	* @createTime: 2018年12月11日 上午10:55:24
	* @history:
	* @param signature
	* @param timestamp
	* @param nonce
	* @param echostr void
	 * @throws IOException 
	*/
	@RequestMapping(value="check",method=RequestMethod.GET)
	public void check(HttpServletRequest request,HttpServletResponse response,String signature,String timestamp,String nonce,String echostr) throws IOException{
		log.info("请求方式为={}",request.getMethod().toUpperCase());
		PrintWriter print;
		 /**
         * 将token、timestamp、nonce三个参数进行字典序排序
         * 并拼接为一个字符串
         */	
        String sortStr = CheckSignatureUtil.sort(TOKEN,timestamp,nonce);
        
        /**
         * 字符串进行shal加密
         */
        String mySignature = CheckSignatureUtil.shal(sortStr);
        /**
         * 校验微信服务器传递过来的签名 和  加密后的字符串是否一致, 若一致则签名通过
         */
        if(!"".equals(signature) && !"".equals(mySignature) && signature.equals(mySignature)){
        	print = response.getWriter();
            print.write(echostr);
            print.flush();
            System.out.println("-----签名校验通过-----");
        }else {
            System.out.println("-----校验签名失败-----");
        }
	}
	
	/**
	* 自动回复消息
	* @author: jiangyuzhuang
	* @createTime: 2018年12月12日 上午9:36:32
	* @history:
	* @param request
	* @param response
	* @throws Exception void
	*/
	@RequestMapping(value="check",method=RequestMethod.POST)
	public void replyMes(HttpServletRequest request,HttpServletResponse response) throws Exception{
		log.info("请求方式为={}",request.getMethod().toUpperCase());
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		PrintWriter out = response.getWriter();
		//将request请求，传到MessageFormatUtil工具类的转换方法中，返回接收到的Map对象
		Document doc = XMLUtil.xmlToDoc(request);
		
		String replyMes = "";
		
		replyMes = MessageUtil.appendReplyMes(doc);
		
        System.out.println(replyMes);
        out.print(replyMes);
        out.close();
	}
	
	/**
	* 获取微信access_token
	* @author: jiangyuzhuang
	* @createTime: 2018年12月6日 上午9:51:55
	* @history:
	* @return String
	*/
	@RequestMapping(value="/getAccessToken",method=RequestMethod.POST,produces="text/html;charset=UTF-8")
    public @ResponseBody String getAccessToken(){
		log.info("微信appId="+appId+"微信appSecret="+appSecret);
		System.out.println("123");
    	String token = AccessTokenUtil.getWeixinAccessToken(appId,appSecret);
    	JSONObject js = new JSONObject();
    	js.put("access_token", token);
    	return js.toString();
    }	
}
