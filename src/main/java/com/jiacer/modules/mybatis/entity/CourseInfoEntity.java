package com.jiacer.modules.mybatis.entity;

import java.math.BigDecimal;
import java.util.Date;
import com.jiacer.modules.mybatis.model.CoursePackage;
import com.jiacer.modules.system.utils.DictionaryUtils;

/**
 * 
* @ClassName: CoursePackageEntity 
* @Description: 课程销售表
* @author 贺章鹏
* @date 2016年10月18日 下午12:06:23 
*
 */
public class CourseInfoEntity{

	private static final long serialVersionUID = 1L;
	
	private Integer courseId;
	private Integer sortNo;
	private String courseName;
	private String courseType;
	private String fitService;
	private String summary;
	private String reSign;
	private String status;
	private String courseTime;
	private String addAccount;
	private String modifyAccount;
	private String image;
	private BigDecimal price;
	private Date creatTime;
	private Date modifyTime;
	private Date addTime;
	
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public Integer getSortNo() {
		return sortNo;
	}
	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCourseType() {
		return courseType;
	}
	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}
	public String getFitService() {
		return fitService;
	}
	public void setFitService(String fitService) {
		this.fitService = fitService;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getReSign() {
		return reSign;
	}
	public void setReSign(String reSign) {
		this.reSign = reSign;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCourseTime() {
		return courseTime;
	}
	public void setCourseTime(String courseTime) {
		this.courseTime = courseTime;
	}
	public String getAddAccount() {
		return addAccount;
	}
	public void setAddAccount(String addAccount) {
		this.addAccount = addAccount;
	}
	public String getModifyAccount() {
		return modifyAccount;
	}
	public void setModifyAccount(String modifyAccount) {
		this.modifyAccount = modifyAccount;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Date getCreatTime() {
		return creatTime;
	}
	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	
}























