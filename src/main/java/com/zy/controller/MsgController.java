package com.zy.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zy.service.MsgService;

@Controller
@RequestMapping("/msg")
public class MsgController {

	@Autowired
	private MsgService msgService;
	
	@RequestMapping(value="/sendMsg",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String sendMsg(String phone,String msgContent){
		Map<String,Object> result = msgService.sendMsg(phone,msgContent);
		return result.toString();
	}
}
