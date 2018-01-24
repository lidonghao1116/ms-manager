package com.jiacer.modules.mybatis.model;

import com.jiacer.modules.common.persistence.ModelSerializable;

public class SysRole implements ModelSerializable{
	private static final long serialVersionUID = -4534438270047240626L;
    
	 private Integer id;
	 private String  rname;//角色名
	 private String  rcode;//角色代码
	 private String  comment;//备注
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	public String getRcode() {
		return rcode;
	}
	public void setRcode(String rcode) {
		this.rcode = rcode;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	 
	 
	
	
	
}
