package com.jiacer.modules.mybatis.entity;

import com.jiacer.modules.business.utils.PartnerUtils;
import com.jiacer.modules.mybatis.model.UserExtendInfo;
import com.jiacer.modules.system.utils.DictionaryUtils;

/**
 * 
* @ClassName: UserExtendInfoEntity 
* @Description: 用户信息扩展表
* @author 贺章鹏
* @date 2016年10月18日 下午12:10:04 
*
 */
public class UserExtendInfoEntity extends UserExtendInfo{

	private static final long serialVersionUID = 1L;
	
	private String contactAddress;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getContactAddress() {
		return contactAddress;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	private String educationName;

	public String getEducationName() {
		return DictionaryUtils.getEducationName(super.getEducation());
	}

	public void setEducationName(String educationName) {
		this.educationName = educationName;
	}
	
}
