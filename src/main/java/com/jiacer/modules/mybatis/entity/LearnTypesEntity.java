package com.jiacer.modules.mybatis.entity;

import com.jiacer.modules.mybatis.model.LearnTypes;
import com.jiacer.modules.system.utils.DictionaryUtils;

public class LearnTypesEntity extends LearnTypes{

	private static final long serialVersionUID = 1L;

	/**中间表*/
	private int learnTypesId; 	//中间表
	private String templateId; //中间表
	
	private String timeTemplate;
	
	private int coursePackageId;
	
	/***********中文显示字段*****************/
	@SuppressWarnings("unused")
	private String examTypeName;//考试形式
	
	@SuppressWarnings("unused")
	private String statusName;//课程

	private String authorityName;//机构名称
	
	
	public String getAuthorityName() {
		return authorityName;
	}

	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}

	public int getCoursePackageId() {
		return coursePackageId;
	}

	public void setCoursePackageId(int coursePackageId) {
		this.coursePackageId = coursePackageId;
	}

	public String getTimeTemplate() {
		return timeTemplate;
	}

	public void setTimeTemplate(String timeTemplate) {
		this.timeTemplate = timeTemplate;
	}

	public int getLearnTypesId() {
		return learnTypesId;
	}

	public void setLearnTypesId(int learnTypesId) {
		this.learnTypesId = learnTypesId;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getExamTypeName() {
		return DictionaryUtils.getExamType(super.getExamType());
	}

	public void setExamTypeName(String examTypeName) {
		this.examTypeName = examTypeName;
	}

	public String getStatusName() {
		return DictionaryUtils.getCourseStatus(super.getStatus());
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
}
