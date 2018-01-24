package com.jiacer.modules.mybatis.entity;

import com.jiacer.modules.mybatis.model.UserBaseInfo;

/**
 * 
* @ClassName: UserBaseInfoEntity 
* @Description: 用户基础信息表
* @author 贺章鹏
* @date 2016年10月18日 下午12:09:50 
*
 */
public class UserBaseInfoEntity extends UserBaseInfo{

	private static final long serialVersionUID = 1L;
	
	private Integer couresCount;//课程数
	
	private UserExtendInfoEntity userExtend;

	public Integer getCouresCount() {
		return couresCount;
	}

	public void setCouresCount(Integer couresCount) {
		this.couresCount = couresCount;
	}

	public UserExtendInfoEntity getUserExtend() {
		return userExtend;
	}

	public void setUserExtend(UserExtendInfoEntity userExtend) {
		this.userExtend = userExtend;
	}

}
