package com.zy.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.zy.service.WeChatMsgService;

@RequestMapping("/wechatMsg")
@Controller
public class WeChatMsgController extends BaseController {
    
	@Autowired
	private WeChatMsgService wechatMsgService;
	
	/**
	* 发送模板消息
	* @author: jiangyuzhuang
	* @createTime: 2018年12月7日 下午3:17:18
	* @history:
	* @param request
	* @return String
	*/
	@RequestMapping(value="/sendTemplatMsg",method=RequestMethod.POST,produces="text/html;charset=UTF-8")
	public @ResponseBody String sendWechatTemplateMsg(HttpServletRequest request){
		String json = getRequestBody(request);
		JSONObject jsonObject = JSONObject.parseObject(json);
		String keyNo = jsonObject.getString("keyNo");
		
		JSONObject js = new JSONObject();
		
		switch (keyNo) {
		case "10001": {
             String personId = jsonObject.getJSONObject("param").getString("personId");
             String personName = jsonObject.getJSONObject("param").getString("personName");
             String msgContent = jsonObject.getJSONObject("param").getString("msgContent");
             String templateId = jsonObject.getJSONObject("param").getString("templateId");
             
             boolean result = wechatMsgService.sendWeChatMsg(personId,personName,msgContent,templateId);
             
             js.put("SUCCESS", result);
             
             break;
 		}
		default:
			js.put("SUCCESS", false);
			js.put("msg", "no such keyNo");
			break;
		}
		return js.toString();
	}
}

