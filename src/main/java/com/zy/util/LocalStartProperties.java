package com.zy.util;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.zy.model.TestProperties;

@Lazy(false)
@Component
public class LocalStartProperties{

	@Autowired
	TestProperties testProperties;

	public TestProperties getTestProperties() {
		return testProperties;
	}

	public void setTestProperties(TestProperties testProperties) {
		this.testProperties = testProperties;
	}

	public LocalStartProperties() {
		System.err.println("此时testProperties还未被注入"+testProperties);
	}
	
	@PostConstruct
	public void test(){
		System.err.println("此时testProperties被注入"+testProperties.getName());
	}
}
