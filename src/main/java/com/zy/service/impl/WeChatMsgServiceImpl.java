package com.zy.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.zy.dao.UserWechatMapper;
import com.zy.service.WeChatMsgService;
import com.zy.util.AccessTokenUtil;
import com.zy.util.SendWechatMsgUtil;
import com.zy.util.ServerPropertiesUtil;

@Service
public class WeChatMsgServiceImpl implements WeChatMsgService {

	@Autowired
	private UserWechatMapper userWechatMapper;

	@Value("${appId}")
	private String appId;
	
	@Value("${appSecret}")
	private String appSecret;
	
	@Override
	public boolean sendWeChatMsg(String personId, String personName, String msgContent,String templateId) {
		boolean result = false;
		
		String token = AccessTokenUtil.getWeixinAccessToken(appId, appSecret);
		String openId = userWechatMapper.selectOpenIdByPersonId(personId);
		
        String wechatTemplate = "";
        String wechatTemplateFirst = "";
        String wechatTemplateProduct = "";
        String wechatTemplateAmount = "";
        String wechatTemplateRemark = "";
        if(openId==null||openId.equals("")){
        	return result;
        }
        switch (templateId){
        case "1":{
        	wechatTemplate = ServerPropertiesUtil.p.getProperty("wx.template1");
        	wechatTemplateFirst = ServerPropertiesUtil.p.getProperty("wx.template.first");
        	wechatTemplateProduct = ServerPropertiesUtil.p.getProperty("wx.template1.product");
        	wechatTemplateAmount = ServerPropertiesUtil.p.getProperty("wx.template1.amount");
        	wechatTemplateRemark = ServerPropertiesUtil.p.getProperty("wx.template1.remark");
        	
        	Map<String,Object> createMsgResult = SendWechatMsgUtil.createWechatMsg(openId,wechatTemplate,"",wechatTemplateFirst,wechatTemplateProduct,wechatTemplateAmount,wechatTemplateRemark);
        	
        	result = SendWechatMsgUtil.sendTemplateMsg(token,createMsgResult);
        	
        	break;
        }
        default:
        	break;
        }
		return result;
	}

}
