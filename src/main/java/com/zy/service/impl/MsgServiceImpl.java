package com.zy.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.zy.service.MsgService;
import com.zy.util.Sender;

@Service
public class MsgServiceImpl implements MsgService{

	@Override
	public Map<String,Object> sendMsg(String phone, String msgContent) {
		Map<String,Object> result = Sender.sendSMS(phone,msgContent);
		return result;
	}
}
