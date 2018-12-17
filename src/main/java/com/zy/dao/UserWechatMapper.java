package com.zy.dao;

import com.zy.model.UserWechat;

public interface UserWechatMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserWechat record);

    int insertSelective(UserWechat record);

    UserWechat selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserWechat record);

    int updateByPrimaryKey(UserWechat record);

	/**
	* 根据personId查询openId
	* @author: jiangyuzhuang
	* @createTime: 2018年12月7日 下午3:42:03
	* @history:
	* @param personId
	* @return String
	*/
	String selectOpenIdByPersonId(String personId);
}