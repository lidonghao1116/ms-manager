package com.jiacer.modules.mybatis.model;

import java.util.Date;

import com.jiacer.modules.business.bean.DictionariesUtil;
import com.jiacer.modules.common.persistence.ModelSerializable;

public class UserExtendInfo implements ModelSerializable{
    
	private static final long serialVersionUID = 1L;

	/**
     * 表：user_extend_info
     * 字段：user_id
     * 注释：用户id
     *
     * @mbggenerated
     */
    private Integer userId;

    /**
     * 表：user_extend_info
     * 字段：owned_teacher
     * 注释：所属门店老师
     *
     * @mbggenerated
     */
    private String ownedTeacher;

    /**
     * 表：user_extend_info
     * 字段：cert_no
     * 注释：证件号码
     *
     * @mbggenerated
     */
    private String certNo;

    /**
     * 表：user_extend_info
     * 字段：cert_type
     * 注释：证件类型
     *
     * @mbggenerated
     */
    private String certType;

    /**
     * 表：user_extend_info
     * 字段：education
     * 注释：学历
     *
     * @mbggenerated
     */
    private String education;

    /**
     * 表：user_extend_info
     * 字段：nation
     * 注释：民族
     *
     * @mbggenerated
     */
    private String nation;

    /**
     * 表：user_extend_info
     * 字段：birthplace
     * 注释：籍贯
     *
     * @mbggenerated
     */
    private String birthplace;

    /**
     * 表：user_extend_info
     * 字段：address
     * 注释：户籍地址
     *
     * @mbggenerated
     */
    private String address;

    /**
     * 表：user_extend_info
     * 字段：contacts
     * 注释：紧急联系人
     *
     * @mbggenerated
     */
    private String contacts;

    /**
     * 表：user_extend_info
     * 字段：contact_phone
     * 注释：联系电话
     *
     * @mbggenerated
     */
    private String contactPhone;

    /**
     * 表：user_extend_info
     * 字段：age
     * 注释：年龄
     *
     * @mbggenerated
     */
    private Integer age;

    /**
     * 表：user_extend_info
     * 字段：sex
     * 注释：性别
     *
     * @mbggenerated
     */
    private String sex;

    /**
     * 表：user_extend_info
     * 字段：add_time
     * 注释：添加时间
     *
     * @mbggenerated
     */
    private Date addTime;

    /**
     * 表：user_extend_info
     * 字段：modify_time
     * 注释：修改时间
     *
     * @mbggenerated
     */
    private Date modifyTime;

    /**
     * 表：user_extend_info
     * 字段：user_name
     * 注释：用户姓名
     *
     * @mbggenerated
     */
    private String userName;


    
	public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getOwnedTeacher() {
        return ownedTeacher;
    }

    public void setOwnedTeacher(String ownedTeacher) {
        this.ownedTeacher = ownedTeacher == null ? null : ownedTeacher.trim();
    }

    public String getCertNo() {
        return certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo == null ? null : certNo.trim();
    }

    public String getCertType() {
        return certType;
    }

    public void setCertType(String certType) {
        this.certType = certType == null ? null : certType.trim();
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education == null ? null : education.trim();
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation == null ? null : nation.trim();
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace == null ? null : birthplace.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts == null ? null : contacts.trim();
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone == null ? null : contactPhone.trim();
    }

    public Integer getAge() {
       if(this.certNo != null || this.certNo != ""){
    	   return DictionariesUtil.getAge(this.certNo);
       }else{
    	 return age;
       }
    }
    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

}