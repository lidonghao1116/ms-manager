package com.jiacer.modules.mybatis.entity;

import java.util.Date;

import com.jiacer.modules.common.persistence.ModelSerializable;

/**
 * 
* @ClassName: ShoolsEntity 
* @Description: 上课时间管理
* @author 贺章鹏
* @date 2016年10月18日 下午12:08:20 
* table - courses_time_template
*
 */
public class CourseInfoBuyRecordEntity implements ModelSerializable{

	private static final long serialVersionUID = 1L;
	
	/**
	 * ID
	 */
	private Integer id;
	
	private int institutionInfoId;
	
	private int stuUserInfoId;
	
	private int courseId;
	
	private int userId;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getInstitutionInfoId() {
		return institutionInfoId;
	}

	public void setInstitutionInfoId(int institutionInfoId) {
		this.institutionInfoId = institutionInfoId;
	}

	public int getStuUserInfoId() {
		return stuUserInfoId;
	}

	public void setStuUserInfoId(int stuUserInfoId) {
		this.stuUserInfoId = stuUserInfoId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	
}
