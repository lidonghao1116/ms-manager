package com.jiacer.modules.mybatis.entity;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.jiacer.modules.business.bean.DictionariesUtil;
import com.jiacer.modules.business.bean.IdcardInfoExtractor;
import com.jiacer.modules.business.utils.CfgParamUtils;
import com.jiacer.modules.business.utils.CoursesUtils;
import com.jiacer.modules.business.utils.ProductsUtils;
import com.jiacer.modules.common.constants.Constants;
import com.jiacer.modules.common.utils.MD5;
import com.jiacer.modules.mybatis.model.ApplyOrders;
import com.jiacer.modules.system.config.DBStatus;
import com.jiacer.modules.system.utils.DictionaryUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author 贺章鹏
 * @ClassName: ApplyOrdersEntity
 * @Description: 申请报班订单表
 * @date 2016年10月18日 下午12:04:49
 */
public class ApplyOrdersEntity extends ApplyOrders {

    private static final long serialVersionUID = 1L;

    private StuUserInfoEntity stuUserInfo;

    private String typeName;
    
    private String handlerName;
    
    private String modifyName;
    
    private String stagingClearName;
    
    private String depositClearName;

    /***
     * 订单状态中文名
     */
    private String formTypeName;

    private UserBaseInfoEntity userBaseInfo;

    private UserExtendInfoEntity userExtendInfo;

    private UserScoresEntity userScores;//用户成绩

    private ExamClassEntity examClass;//班级

    private List<ApplyOrdersEntity> orderList;//报考信息

    private String type;//

    /***************用户查询***********************/
    private Date startDate;//开始时间

    private Date endDate;//结束时间

    private String optType;//操作类型

    private List<String> hstatusList;

    private List<String> orderTypeList;

    private String userName;

    private String authenticateGrade;//鉴定等级

    private String authorityName;//认证机构

    private String examResult; //是否合格

    /***基本信息***/
    private String nation; //民族
    private String address; //户籍地址
    private String contacts; //紧急联系人
    private String contactPhone; //紧急联系人电话
    private String certNo;//身份证
    private String education;//学历
    private String sex;
    private String mobile;
    private String contactAddress;
    private String issueOrg;
    private String expiredTime;
    private String headImage; //身份证头像

    /**短信验证码**/
    private String code;


    public UserBaseInfoEntity buildUserBaseInfoEntity() {
        UserBaseInfoEntity user = new UserBaseInfoEntity();
        user.setId(this.getUserId());
        user.setUserName(this.getUserName());
        user.setCertNo(this.getCertNo());
        user.setHeadImage(this.getHeadImage());
        user.setBirthplace(this.getNation());
        user.setUserType(DBStatus.UserType.NORMAL);
        user.setAddress(this.getAddress());
        user.setMobile(this.getMobile());
        user.setIssueOrg(this.getIssueOrg());
        user.setExpiredTime(this.getExpiredTime());
        user.setGender(DictionariesUtil.getSex(this.getCertNo()) + "");;
        return user;
    }

    public UserExtendInfoEntity buildUserExtendInfoEntity() {
        UserExtendInfoEntity ext = new UserExtendInfoEntity();
        ext.setUserId(this.getUserId());
        ext.setContactAddress(this.getContactAddress());
        ext.setContacts(this.getContacts());
        ext.setContactPhone(this.getContactPhone());
        ext.setSex(DictionariesUtil.getSex(this.getCertNo()) + "");//应改为存入code
      //ext.setEducation(this.getEducation());
        ext.setNation(CfgParamUtils.getCfgParamValueByText(Constants.CFG_PARAM_NATION_TYPE, this.getNation()));
        ext.setAddress(this.getAddress());
        ext.setUserName(this.getUserName());
        ext.setBirthplace(this.getCertNo().substring(0,2));
        return ext;
    }

    /**
     * 订单对应的班级编号
     **/
    private Integer classId;


    /**
     * 身份证校验签名
     **/
    private String sign;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @JsonIgnore
    public String getSign() {
        return sign;
    }

    @JsonSetter
    public void setSign(String sign) {
        this.sign = sign;
    }

    @JsonIgnore
    public String getValidSign() {
        return MD5.getStrMD5(this.getUserId() + this.getUserName() + this.getCertNo() + this.getMobile());
    }

    public String getExamResult() {
        return examResult;
    }

    public void setExamResult(String examResult) {
        this.examResult = examResult;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getFormTypeName() {
        if (this.formType != null) {
            return DictionariesUtil.getorderFromTypeName(this.formType);
        }
        return formTypeName;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public void setFormTypeName(String formTypeName) {
        this.formTypeName = formTypeName;
    }

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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEducation() {
        return education;
    }

    public String getEducationValue() {
        return StringUtils.isBlank(education)? "" : DictionariesUtil.getEducationName(education);
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getCertNo() {
        return certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
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

    public StuUserInfoEntity getStuUserInfo() {
        return stuUserInfo;
    }

    public void setStuUserInfo(StuUserInfoEntity stuUserInfo) {
        this.stuUserInfo = stuUserInfo;
    }

    public String getAuthenticateGrade() {
        return authenticateGrade;
    }

    public void setAuthenticateGrade(String authenticateGrade) {
        this.authenticateGrade = authenticateGrade;
    }

    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }

    private String formType;//订单类别01正常02补考


    public String getFormType() {
        return formType;
    }

    public void setFormType(String formType) {
        this.formType = formType;
    }

    /***************用户中文展示*******************/
    @SuppressWarnings("unused")
    private String courseName;

    @SuppressWarnings("unused")
    private String productName;

    public UserBaseInfoEntity getUserBaseInfo() {
        return userBaseInfo;
    }

    public void setUserBaseInfo(UserBaseInfoEntity userBaseInfo) {
        this.userBaseInfo = userBaseInfo;
    }

    public UserExtendInfoEntity getUserExtendInfo() {
        return userExtendInfo;
    }

    public void setUserExtendInfo(UserExtendInfoEntity userExtendInfo) {
        this.userExtendInfo = userExtendInfo;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<ApplyOrdersEntity> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<ApplyOrdersEntity> orderList) {
        this.orderList = orderList;
    }

    public String getOptType() {
        return optType;
    }

    public void setOptType(String optType) {
        this.optType = optType;
    }

    public List<String> getHstatusList() {
        return hstatusList;
    }

    public void setHstatusList(List<String> hstatusList) {
        this.hstatusList = hstatusList;
    }

    public UserScoresEntity getUserScores() {
        return userScores;
    }

    public void setUserScores(UserScoresEntity userScores) {
        this.userScores = userScores;
    }

    public String getCourseName() {
        return CoursesUtils.getName(super.getCourseId());
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getProductName() {
        return ProductsUtils.getName(super.getPackageId());
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public ExamClassEntity getExamClass() {
        return examClass;
    }

    public void setExamClass(ExamClassEntity examClass) {
        this.examClass = examClass;
    }

    public List<String> getOrderTypeList() {
        return orderTypeList;
    }

    public void setOrderTypeList(List<String> orderTypeList) {
        this.orderTypeList = orderTypeList;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

	public String getHandlerName() {
		return handlerName;
	}

	public void setHandlerName(String handlerName) {
		this.handlerName = handlerName;
	}

	public String getModifyName() {
		return modifyName;
	}

	public void setModifyName(String modifyName) {
		this.modifyName = modifyName;
	}

	public String getStagingClearName() {
		return stagingClearName;
	}

	public void setStagingClearName(String stagingClearName) {
		this.stagingClearName = stagingClearName;
	}

	public String getDepositClearName() {
		return depositClearName;
	}

	public void setDepositClearName(String depositClearName) {
		this.depositClearName = depositClearName;
	}

}

