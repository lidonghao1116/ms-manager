package com.jiacer.modules.mybatis.entity;

public class ControlEntity {

	private String id;
	private String value;
	private String authenticateGrade;
	
	public ControlEntity(String id, String value) {
		super();
		this.id = id;
		this.value = value;
	}
	
	public ControlEntity(String id, String value,String authenticateGrade) {
		super();
		this.id = id;
		this.value = value;
		this.authenticateGrade = authenticateGrade;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	public String getAuthenticateGrade() {
		return authenticateGrade;
	}

	public void setAuthenticateGrade(String authenticateGrade) {
		this.authenticateGrade = authenticateGrade;
	}
	
	
	
}
