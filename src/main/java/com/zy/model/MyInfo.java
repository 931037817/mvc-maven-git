package com.zy.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MyInfo {

	@Value("${my.name}")
	private String myName;
	
	@Value("${my.sex}")
	private String mySex;
	
	@Value("${my.age}")
	private String myAge;
	
	@Value("${appId}")
	private String appId;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getMyName() {
		return myName;
	}

	public void setMyName(String myName) {
		this.myName = myName;
	}

	public String getMySex() {
		return mySex;
	}

	public void setMySex(String mySex) {
		this.mySex = mySex;
	}

	public String getMyAge() {
		return myAge;
	}

	public void setMyAge(String myAge) {
		this.myAge = myAge;
	}

	
}
