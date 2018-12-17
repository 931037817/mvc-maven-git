package com.zy.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.zy.dao.UserMapper;
import com.zy.model.User;
import com.zy.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	public static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserMapper userMapper;
	
	@Value("${my.name}")
	private String myName;
	
	@Override	
	@Cacheable(value="redisCacheManagerA",key="#key")
	public List<User> getAllUser(String key) {
		System.out.println("进行数据库查询...");
		return userMapper.getAllUser();
	}

	@Override
	public String getMyName() {
		return myName;
	}

	@Override
	@CachePut(value="redisCacheManagerA",key="'getAllUser'")
	public List<User> addUser(String name, String pwd, String age) {
		User user = new User();
		user.setUserName(name);
		user.setPassword(pwd);
		int newAge = age==null?0:Integer.parseInt(age);
		user.setAge(newAge);
		int flag = userMapper.insertSelective(user);
		log.info("修改结果flag={}",flag);
		List<User> alluserList = userMapper.getAllUser();
		return alluserList;
	}

	@Override
	@Cacheable(value="redisCacheManagerA",key="#id")
	public User getOneUser(String id) {
		int sId = Integer.parseInt(id);
		User user = userMapper.selectByPrimaryKey(sId);
		return user;
	}

	@Override
	@Cacheable(value="redisCacheManagerA",key="#user.getId().toString()",condition="#user.getId().toString().length()>2")
	public User addOneUser(User user) {
		userMapper.insert(user);
		User user2 = userMapper.selectByPrimaryKey(user.getId());
		return user2;
	}

	@Override
	@CacheEvict(value="redisCacheManagerA",key="#id",condition="#id.length()>2")
	public void delOneUser(String id) {
		userMapper.deleteByPrimaryKey(Integer.parseInt(id));
	}

}
