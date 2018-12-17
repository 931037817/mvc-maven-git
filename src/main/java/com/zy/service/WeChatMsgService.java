package com.zy.service;

public interface WeChatMsgService {

	public boolean sendWeChatMsg(String personId, String personName, String msgContent,String templateId);


}
