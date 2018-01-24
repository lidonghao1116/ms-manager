package com.jiacer.modules.mybatis.model;

import com.jiacer.modules.system.utils.AreasUtils;

import java.util.Date;

public class InstitutionInfo {
    private Integer id;

    private String schoolName;

    private String privince;

    private String city;

    private String area;

    private String schoolAddress;

    private String schoolPhone;

    private String contacts;

    private String contactPhone;

    private String companyName;

    private String licenceNo;

    private String licenceImg;

    private String agentName;

    private String agentIdNumber;

    private String idcardFrontImg;

    private String idcardBackImg;

    private String saler;

    private Date appltTime;

    private String remarks;

    private String applyCourses;

    private String isUsable;

    private Date addTime;

    private String addAccount;

    private Date modifyTime;

    private String modifyAccount;

    private String logoUrl;

    public String getPrivinceValue(){
        return AreasUtils.getName(this.getPrivince());
    }
    public String getCityValue(){
        return AreasUtils.getName(this.getCity());
    }
    public String getAreaValue(){
        return AreasUtils.getName(this.getArea());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName == null ? null : schoolName.trim();
    }

    public String getPrivince() {
        return privince;
    }

    public void setPrivince(String privince) {
        this.privince = privince == null ? null : privince.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getSchoolAddress() {
        return schoolAddress;
    }

    public void setSchoolAddress(String schoolAddress) {
        this.schoolAddress = schoolAddress == null ? null : schoolAddress.trim();
    }

    public String getSchoolPhone() {
        return schoolPhone;
    }

    public void setSchoolPhone(String schoolPhone) {
        this.schoolPhone = schoolPhone == null ? null : schoolPhone.trim();
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getLicenceNo() {
        return licenceNo;
    }

    public void setLicenceNo(String licenceNo) {
        this.licenceNo = licenceNo == null ? null : licenceNo.trim();
    }

    public String getLicenceImg() {
        return licenceImg;
    }

    public void setLicenceImg(String licenceImg) {
        this.licenceImg = licenceImg == null ? null : licenceImg.trim();
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName == null ? null : agentName.trim();
    }

    public String getAgentIdNumber() {
        return agentIdNumber;
    }

    public void setAgentIdNumber(String agentIdNumber) {
        this.agentIdNumber = agentIdNumber == null ? null : agentIdNumber.trim();
    }

    public String getIdcardFrontImg() {
        return idcardFrontImg;
    }

    public void setIdcardFrontImg(String idcardFrontImg) {
        this.idcardFrontImg = idcardFrontImg == null ? null : idcardFrontImg.trim();
    }

    public String getIdcardBackImg() {
        return idcardBackImg;
    }

    public void setIdcardBackImg(String idcardBackImg) {
        this.idcardBackImg = idcardBackImg == null ? null : idcardBackImg.trim();
    }

    public String getSaler() {
        return saler;
    }

    public void setSaler(String saler) {
        this.saler = saler == null ? null : saler.trim();
    }

    public Date getAppltTime() {
        return appltTime;
    }

    public void setAppltTime(Date appltTime) {
        this.appltTime = appltTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getApplyCourses() {
        return applyCourses;
    }

    public void setApplyCourses(String applyCourses) {
        this.applyCourses = applyCourses == null ? null : applyCourses.trim();
    }

    public String getIsUsable() {
        return isUsable;
    }

    public void setIsUsable(String isUsable) {
        this.isUsable = isUsable == null ? null : isUsable.trim();
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
        this.addAccount = addAccount == null ? null : addAccount.trim();
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
        this.modifyAccount = modifyAccount == null ? null : modifyAccount.trim();
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl == null ? null : logoUrl.trim();
    }

}