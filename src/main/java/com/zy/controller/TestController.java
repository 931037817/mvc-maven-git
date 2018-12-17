package com.zy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 验证单例和多利模式
* @desc: mvc-maven
* @author: jiangyuzhuang
* @createTime: 2018年11月19日 上午10:53:34
* @history:
* @version: v1.0
*/
@Controller
@RequestMapping("/test/")
public class TestController {
/*
 * springmvc默认为单例模式，实例化一次，静态变量属于类，增加，实例变量，增加
 *
 * @Scope("prototype")设置为多利模式。实例化多次，静态变量属于类，增加，实例变量重新实例化，初始值
 * 
 * ThreadLocal 
 * */
	private static int a = 0;
	
	private int b = 0;
	
	ThreadLocal<Integer> m = new ThreadLocal<Integer>(){
		
		@Override
		protected Integer initialValue(){
			return 0;
		}
	};
	
	@RequestMapping("one")
	public String testProperty(){
		System.out.println("静态变量："+a++);
		System.out.println("非静态变量："+b++);
		return "";
	}
	
	@RequestMapping("two")
	public String testThreadLocal(){
		System.out.println("threadlocal变量："+(m.get()+1));
		return "";
	}
}
