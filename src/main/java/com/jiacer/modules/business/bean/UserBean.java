package com.jiacer.modules.business.bean;

import java.util.List;

import com.jiacer.modules.common.persistence.ModelSerializable;
import com.jiacer.modules.mybatis.entity.ApplyOrdersEntity;
import com.jiacer.modules.mybatis.entity.UserBaseInfoEntity;
import com.jiacer.modules.mybatis.entity.UserExtendInfoEntity;

/** 
* @ClassName: UserBean 
* @Description: 用户详细实体
* @author 贺章鹏
* @date 2016年11月7日 下午7:22:47 
*  
*/
public class UserBean implements ModelSerializable{
	private static final long serialVersionUID = 1L;
	private UserBaseInfoEntity userInfo;
	private UserExtendInfoEntity userExtend;
	
	private List<ApplyOrdersEntity>  orderList;

	public UserBaseInfoEntity getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserBaseInfoEntity userInfo) {
		this.userInfo = userInfo;
	}

	public UserExtendInfoEntity getUserExtend() {
		return userExtend;
	}

	public void setUserExtend(UserExtendInfoEntity userExtend) {
		this.userExtend = userExtend;
	}

	public List<ApplyOrdersEntity> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<ApplyOrdersEntity> orderList) {
		this.orderList = orderList;
	}
	
}
