package com.jiacer.modules.mybatis.model;

import java.io.Serializable;
import java.util.Date;

public class CourseInstitution implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 课程ID
	 */
	private Integer courseId;
	/**
	 * 机构ID
	 */
	private Integer institutionId;
	/**
	 * 课程状态0--未开课 1--已开课
	 */
	private String status;

	/**
	 * 创建时间
	 */
	private Date    addTime;
	/**
	 * 创建账户
	 */
	private String  addAccount;	
	/**
	 * 修改时间
	 */
	private  Date   modifyTime;	
	/**
	 * 修改人
	 */
	private String  modifyAccount;
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public Integer getInstitutionId() {
		return institutionId;
	}
	public void setInstitutionId(Integer institutionId) {
		this.institutionId = institutionId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public String getAddAccount() {
		return addAccount;
	}
	public void setAddAccount(String addAccount) {
		this.addAccount = addAccount;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getModifyAccount() {
		return modifyAccount;
	}
	public void setModifyAccount(String modifyAccount) {
		this.modifyAccount = modifyAccount;
	}
	
	
}
