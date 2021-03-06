package com.jiacer.modules.mybatis.model;

import java.util.Date;

public class CertAuthority {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cert_authority.authority_id
     *
     * @mbggenerated Wed May 24 16:46:24 CST 2017
     */
    private Integer authorityId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cert_authority.authority_name
     *
     * @mbggenerated Wed May 24 16:46:24 CST 2017
     */
    private String authorityName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cert_authority.remark
     *
     * @mbggenerated Wed May 24 16:46:24 CST 2017
     */
    private String remark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cert_authority.status
     *
     * @mbggenerated Wed May 24 16:46:24 CST 2017
     */
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cert_authority.add_time
     *
     * @mbggenerated Wed May 24 16:46:24 CST 2017
     */
    private Date addTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cert_authority.add_account
     *
     * @mbggenerated Wed May 24 16:46:24 CST 2017
     */
    private String addAccount;
    
    private Date licenceDate;

    
    
    public Date getLicenceDate() {
		return licenceDate;
	}

	public void setLicenceDate(Date licenceDate) {
		this.licenceDate = licenceDate;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cert_authority.authority_id
     *
     * @return the value of cert_authority.authority_id
     *
     * @mbggenerated Wed May 24 16:46:24 CST 2017
     */
    public Integer getAuthorityId() {
        return authorityId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cert_authority.authority_id
     *
     * @param authorityId the value for cert_authority.authority_id
     *
     * @mbggenerated Wed May 24 16:46:24 CST 2017
     */
    public void setAuthorityId(Integer authorityId) {
        this.authorityId = authorityId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cert_authority.authority_name
     *
     * @return the value of cert_authority.authority_name
     *
     * @mbggenerated Wed May 24 16:46:24 CST 2017
     */
    public String getAuthorityName() {
        return authorityName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cert_authority.authority_name
     *
     * @param authorityName the value for cert_authority.authority_name
     *
     * @mbggenerated Wed May 24 16:46:24 CST 2017
     */
    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cert_authority.remark
     *
     * @return the value of cert_authority.remark
     *
     * @mbggenerated Wed May 24 16:46:24 CST 2017
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cert_authority.remark
     *
     * @param remark the value for cert_authority.remark
     *
     * @mbggenerated Wed May 24 16:46:24 CST 2017
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cert_authority.status
     *
     * @return the value of cert_authority.status
     *
     * @mbggenerated Wed May 24 16:46:24 CST 2017
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cert_authority.status
     *
     * @param status the value for cert_authority.status
     *
     * @mbggenerated Wed May 24 16:46:24 CST 2017
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cert_authority.add_time
     *
     * @return the value of cert_authority.add_time
     *
     * @mbggenerated Wed May 24 16:46:24 CST 2017
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cert_authority.add_time
     *
     * @param addTime the value for cert_authority.add_time
     *
     * @mbggenerated Wed May 24 16:46:24 CST 2017
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cert_authority.add_account
     *
     * @return the value of cert_authority.add_account
     *
     * @mbggenerated Wed May 24 16:46:24 CST 2017
     */
    public String getAddAccount() {
        return addAccount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cert_authority.add_account
     *
     * @param addAccount the value for cert_authority.add_account
     *
     * @mbggenerated Wed May 24 16:46:24 CST 2017
     */
    public void setAddAccount(String addAccount) {
        this.addAccount = addAccount;
    }
}