package com.jiacer.modules.business.bean.form;

import java.util.Date;

import com.jiacer.modules.common.persistence.ModelSerializable;

/**
 * @ClassName: UsersQuery
 * @Description: TODO
 * @author 贺章鹏
 * @date 2016年11月11日 下午2:25:32
 * 
 */
public class UsersQuery implements ModelSerializable {

	private static final long serialVersionUID = 1L;

	private String userName;

	private String sourceType;

	private String sourceTypeSec;

	private Date startDate;

	private Date endDate;
	private String mobile;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public String getSourceTypeSec() {
		return sourceTypeSec;
	}

	public void setSourceTypeSec(String sourceTypeSec) {
		this.sourceTypeSec = sourceTypeSec;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
