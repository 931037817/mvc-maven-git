package com.zy.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.zy.model.MyInfo;
import com.zy.model.User;
import com.zy.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private MyInfo myInfo;
	
	@Value("${appId}")
	private static String appId;
	
	@Value("${my.name}")
	private String myName;
	
	@Value("${my.sex}")
	private String mySex;
	
	@Value("${my.age}")
	private String myAge;
	
	private final static Logger log = LoggerFactory.getLogger(UserController.class);
	
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String index(){
		System.out.println("我的名字："+myName+"我的性别："+mySex+"我的年龄："+myAge);
		return "hello";
	}
	
	@RequestMapping(value="/getMyInfo",method=RequestMethod.GET)
	public String getMyInfo(){
        System.out.println(myInfo.getMyName());
        String myNameVariable = userService.getMyName();
        System.out.println(myNameVariable);
		return "hello";
	}
	
	@RequestMapping(value="/getAllUser",method=RequestMethod.GET)
	public ModelAndView  getAllUser(){
		List<User> userList = userService.getAllUser("getAllUser");
		ModelAndView mv = new ModelAndView();
		mv.addObject("userList",userList);
		mv.setViewName("user/showUser");
		log.info("list={}",userList.toString());
		return mv;
	}
	
	@RequestMapping(value="/addUser",method=RequestMethod.GET)
	public String addUser(String name,String pwd,String age){
		List<User> num = userService.addUser(name,pwd,age);
		return "hello";
	}
	
	@RequestMapping(value="/getOneUser",method=RequestMethod.GET)
	public String getOneUser(String id){
		User user = userService.getOneUser(id);
		log.info("获取的user={}",user.toString());
		return "hello";
	}
	
	@RequestMapping(value="/addOneUser",method=RequestMethod.GET)
	public String addOneUser(String id,String name,String pwd,String age){
		User user = new User();
		user.setId(Integer.parseInt(id));
		user.setAge(Integer.parseInt(age));
		user.setPassword(pwd);
		user.setUserName(name);
		userService.addOneUser(user);
		return "hello";
	}
	
	@RequestMapping(value="/delOneUser",method=RequestMethod.GET)
	public String delOneUser(String id){
		userService.delOneUser(id);
		return "hello";
	}
	
}
