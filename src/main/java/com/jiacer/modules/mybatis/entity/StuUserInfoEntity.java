package com.jiacer.modules.mybatis.entity;

import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jiacer.modules.business.bean.DictionariesUtil;
import com.jiacer.modules.business.utils.IdCardInfoUtils;
import com.jiacer.modules.common.persistence.ModelSerializable;
import com.jiacer.modules.common.utils.SpringContextHolder;
import com.jiacer.modules.common.utils.StringUtils;
import com.jiacer.modules.mybatis.dao.CfgParamMapper;
import com.jiacer.modules.mybatis.dao.PartnersMapper;
import com.jiacer.modules.mybatis.dao.SysUsersMapper;
import com.jiacer.modules.system.utils.DictionaryUtils;

public class StuUserInfoEntity implements ModelSerializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private int userId;
    private int institutionInfoId;
    private String ownedTeacher;
    private String certNo;
    private String certType;
    private String education;
    private String nation;
    private String birthplace;
    private String address;
    private String contacts;
    private String contactPhone;
    private String contactAddress;
    private String age;
    private String sex;
    private Date addTime;
    private Date modifyTime;
    private String userName;
    private String mobile;


    private String issueOrg;

    private String expiredTime;

    private String headImage; //身份证头像

    //扩展
    private int couresCount;
    private String educationName;//学历中文返显

    private List<ApplyOrdersEntity> orderList; //订单列表

    public String getIssueOrg() {
        return issueOrg;
    }

    public void setIssueOrg(String issueOrg) {
        this.issueOrg = issueOrg;
    }

    public String getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(String expiredTime) {
        this.expiredTime = expiredTime;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    public List<ApplyOrdersEntity> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<ApplyOrdersEntity> orderList) {
        this.orderList = orderList;
    }

    public String getEducationName() {
        if (StringUtils.isNotBlank(this.education)) {
            return DictionariesUtil.getEducationName(this.education);
        }
        return educationName;
    }

    public String getNationName() {
        if (StringUtils.isNotBlank(this.nation)) {
            return DictionaryUtils.getNationName(this.nation);
        }
        return "";
    }

    public void setEducationName(String educationName) {
        this.educationName = educationName;
    }

    public int getCouresCount() {
        return couresCount;
    }

    public void setCouresCount(int couresCount) {
        this.couresCount = couresCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getInstitutionInfoId() {
        return institutionInfoId;
    }

    public void setInstitutionInfoId(int institutionInfoId) {
        this.institutionInfoId = institutionInfoId;
    }

    public String getOwnedTeacher() {
        return ownedTeacher == null ? "" : ownedTeacher;
    }

    public void setOwnedTeacher(String ownedTeacher) {
        this.ownedTeacher = ownedTeacher;
    }

    public String getCertNo() {
        return certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo;
    }

    public String getCertType() {
        return certType;
    }

    public void setCertType(String certType) {
        this.certType = certType;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getBirthplace() {
        IdCardInfoUtils idCard = new IdCardInfoUtils(this.getCertNo());
        return idCard.getProvince();
        //return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getAge() {
        if (this.certNo != null || this.certNo != "") {
            return DictionariesUtil.getAge(this.certNo) + "";
        } else {
            return age;
        }
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}
