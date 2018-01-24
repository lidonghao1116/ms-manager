package com.jiacer.modules.mybatis.model;

import java.io.Serializable;
import java.util.Date;


/**
 * 
 * @ClassName:CourseBaseInfo.java
 * @Description:总控基础课程信息
 * @Author:ticahmfock
 * @Date:Sep 26, 2017 9:13:44 AM
 */
public class CourseBaseInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 课程ID
	 */
	private Integer courseId;
	/**
	 * 发证机构ID
	 */
	private Integer authorityId;
	/**
	 * 课程名称
	 */
	private String  courseName;
	/**
	 * 排序
	 */
	private Integer sortNo;
	/**
	 * 是否可用 1--可用 0--不可用
	 */
	private String  isUsable;	
	/**
	 * 课程状态 01--上架 02--下架
	 */
	private String  status;	
	/**
	 * 总课时
	 */
	private Integer totalHours;
	/**
	 * 考试形式
	 */
	private String  examType;
	/**
	 * 备注
	 */
	private String  remarks;
	/**
	 * 鉴定等级
	 */
	private String  authenticateGrade;	
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
	
	/**
	 * 证书名称
	 */
	private String certName;
	
	public String getCertName() {
		return certName;
	}
	public void setCertName(String certName) {
		this.certName = certName;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public Integer getAuthorityId() {
		return authorityId;
	}
	public void setAuthorityId(Integer authorityId) {
		this.authorityId = authorityId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public Integer getSortNo() {
		return sortNo;
	}
	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}
	public String getIsUsable() {
		return isUsable;
	}
	public void setIsUsable(String isUsable) {
		this.isUsable = isUsable;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getTotalHours() {
		return totalHours;
	}
	public void setTotalHours(Integer totalHours) {
		this.totalHours = totalHours;
	}
	public String getExamType() {
		return examType;
	}
	public void setExamType(String examType) {
		this.examType = examType;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getAuthenticateGrade() {
		return authenticateGrade;
	}
	public void setAuthenticateGrade(String authenticateGrade) {
		this.authenticateGrade = authenticateGrade;
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
	@Override
	public String toString() {
		return "CourseBaseInfo [courseId=" + courseId + ", authorityId=" + authorityId + ", courseName=" + courseName
				+ ", sortNo=" + sortNo + ", isUsable=" + isUsable + ", status=" + status + ", totalHours=" + totalHours
				+ ", examType=" + examType + ", remarks=" + remarks + ", authenticateGrade=" + authenticateGrade
				+ ", addTime=" + addTime + ", addAccount=" + addAccount + ", modifyTime=" + modifyTime
				+ ", modifyAccount=" + modifyAccount + "]";
	}
	
	
}
