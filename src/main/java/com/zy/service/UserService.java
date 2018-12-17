package com.zy.service;

import java.util.List;

import com.zy.model.User;

public interface UserService {
		
	public List<User> getAllUser(String key);
	
	public User getOneUser(String id);
	
	public String getMyName();
	
	public List<User> addUser(String name,String pwd,String age);
	
	public User addOneUser(User user);
	
	public void delOneUser(String a);
}
