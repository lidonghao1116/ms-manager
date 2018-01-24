package com.jiacer.modules.mybatis.model;

import java.io.Serializable;

public class CourseTimeTable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer learntypesId;//learn_types 表中 对应的 id
	
	private String templateId; // courses_time_template 表中的 id

	public Integer getLearntypesId() {
		return learntypesId;
	}

	public void setLearntypesId(Integer learntypesId) {
		this.learntypesId = learntypesId;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	
	
}
