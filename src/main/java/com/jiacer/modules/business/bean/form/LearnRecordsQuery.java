package com.jiacer.modules.business.bean.form;

import com.jiacer.modules.common.persistence.ModelSerializable;

/** 
* @ClassName: LearnRecordsQuery 
* @Description: TODO
* @author 贺章鹏
* @date 2016年11月7日 下午3:31:20 
*  
*/
public class LearnRecordsQuery implements ModelSerializable{
	private static final long serialVersionUID = 1L;

	private String userName;//用户姓名
	
	private String classNumber;//班级标号

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getClassNumber() {
		return classNumber;
	}

	public void setClassNumber(String classNumber) {
		this.classNumber = classNumber;
	}
	
}
