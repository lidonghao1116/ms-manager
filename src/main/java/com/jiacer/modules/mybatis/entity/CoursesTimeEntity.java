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
public class CoursesTimeEntity implements ModelSerializable{

	private static final long serialVersionUID = 1L;
	
	/**
	 * ID
	 */
	private Integer id;
	/**
	 * 模板ID
	 */
	private String templateId;
	
	/**
	 *创建时间 
	 */
	private Date createDate;
	
	/***
	 * 修改时间
	 */
	private Date modifyDate;
	
	/**
	 * 模板名称
	 */
	private String templateName;
	/**
	 * 课程开始周期
	 */
	private String openCycle;
	/**
	 * 开始时间
	 */
	private String beginTime;
	
	/**
	 * 结束时间
	 */
	private String endTime;
	/**
	 * 课程状态
	 */
	private String templateType;
	
	private int institutionInfoId;
	
	public int getInstitutionInfoId() {
		return institutionInfoId;
	}
	public void setInstitutionInfoId(int institutionInfoId) {
		this.institutionInfoId = institutionInfoId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getTemplateId() {
		return templateId;
	}
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	public String getOpenCycle() {
		return openCycle;
	}
	public void setOpenCycle(String openCycle) {
		this.openCycle = openCycle;
	}
	
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getTemplateType() {
		return templateType;
	}
	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}
}
