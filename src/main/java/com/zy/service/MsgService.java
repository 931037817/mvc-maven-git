package com.zy.service;

import java.util.Map;

public interface MsgService {

	Map<String, Object> sendMsg(String phone,String msgContent);
}
