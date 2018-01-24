package com.jiacer.modules.mybatis.model;

import com.jiacer.modules.business.utils.PartnerUtils;
import com.jiacer.modules.common.constants.Constants;
import com.jiacer.modules.common.persistence.ModelSerializable;
import com.jiacer.modules.common.utils.StringUtils;
import com.jiacer.modules.mybatis.entity.CfgParamEntity;
import com.jiacer.modules.system.utils.ParamsUtils;

import java.math.BigDecimal;
import java.util.Date;

public class ApplyOrders implements ModelSerializable {

    private static final long serialVersionUID = 1L;

    /**
     * 表：apply_orders
     * 字段：id
     * 注释：主键
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * 表：apply_orders
     * 字段：user_id
     * 注释：用户id
     *
     * @mbggenerated
     */
    private Integer userId;

    /**
     * 表：apply_orders
     * 字段：user_age
     * 注释：用户报名时年龄
     *
     * @mbggenerated
     */
    private Integer userAge;

    /**
     * 表：apply_orders
     * 字段：course_id
     * 注释：课程
     *
     * @mbggenerated
     */
    private Integer courseId;

    /**
     * 表：apply_orders
     * 字段：class_number
     * 注释：班级标号
     *
     * @mbggenerated
     */
    private Integer classNumber;

    /**
     * 表：apply_orders
     * 字段：is_exam
     * 注释：是否考试
     *
     * @mbggenerated
     */
    private String isExam;

    /**
     * 表：apply_orders
     * 字段：is_deposit
     * 注释：是否有押金
     *
     * @mbggenerated
     */
    private String isDeposit;
    
    public String getIsDeposit() {
		return isDeposit;
	}

	public void setIsDeposit(String isDeposit) {
		this.isDeposit = isDeposit;
	}

	/**
     * 表：apply_orders
     * 字段：is_staging
     * 注释：是否分期
     *
     * @mbggenerated
     */
    private String isStaging;
    /**
     * 表：apply_orders
     * 字段：is_deposit_clear
     * 注释：押金是否已退还
     *
     * @mbggenerated
     */
    private String isDepositClear;
    public String getIsDepositClear() {
		return isDepositClear;
	}

	public void setIsDepositClear(String isDepositClear) {
		this.isDepositClear = isDepositClear;
	}

	/**
     * 表：apply_orders
     * 字段：school_fee
     * 注释：学费
     *
     * @mbggenerated
     */
    private BigDecimal schoolFee;

    /**
     * 表：apply_orders
     * 字段：deposit
     * 注释：押金
     *
     * @mbggenerated
     */
    private BigDecimal deposit;

    /**
     * 表：apply_orders
     * 字段：book_free
     * 注释：书费
     *
     * @mbggenerated
     */
    private BigDecimal bookFree;

    /**
     * 表：apply_orders
     * 字段：remarks
     * 注释：备注
     *
     * @mbggenerated
     */
    private String remarks;

    /**
     * 表：apply_orders
     * 字段：first_pay
     * 注释：首付款
     *
     * @mbggenerated
     */
    private BigDecimal firstPay;

    /**
     * 表：apply_orders
     * 字段：is_clear
     * 注释：费用已结清
     *
     * @mbggenerated
     */
    private String isClear;

    /**
     * 表：apply_orders
     * 字段：order_time
     * 注释：报名时间
     *
     * @mbggenerated
     */
    private Date orderTime;

    /**
     * 表：apply_orders
     * 字段：handle_time
     * 注释：审核时间
     *
     * @mbggenerated
     */
    private Date handleTime;

    /**
     * 表：apply_orders
     * 字段：handler
     * 注释：审核人
     *
     * @mbggenerated
     */
    private Integer handler;
    
    /**
     * 表:apply_orders
     * 字段：处理人
     */
    private Integer modifyAccount;
    
    /**
     * 表：apply_orders
     * 字段：order_status
     * 注释：订单状态
     *
     * @mbggenerated
     */
    private String orderStatus;

    /**
     * 表：apply_orders
     * 字段：handle_status
     * 注释：受理状态
     *
     * @mbggenerated
     */
    private String handleStatus;

    /**
     * 表：apply_orders
     * 字段：package_id
     * 注释：售卖产品id
     *
     * @mbggenerated
     */
    private Integer packageId;

    /**
     * 表：apply_orders
     * 字段：order_amount
     * 注释：订单金额
     *
     * @mbggenerated
     */
    private BigDecimal orderAmount;

    /**
     * 表：apply_orders
     * 字段：order_type
     * 注释：订单类型
     *
     * @mbggenerated
     */
    private String orderType;

    /**
     * 表：apply_orders
     * 字段：class_time
     * 注释：上课时间
     *
     * @mbggenerated
     */
    private String classTime;

    /**
     * 表：apply_orders
     * 字段：modify_time
     * 注释：修改时间
     *
     * @mbggenerated
     */
    private Date modifyTime;

    /**
     * 表：apply_orders
     * 字段：order_no
     * 注释：订单编号
     *
     * @mbggenerated
     */
    private String orderNo;


    /**
     * 表：apply_orders
     * 字段：is_has_pf
     * 注释：是否交金
     *
     * @mbggenerated
     */
    private String isHasPf;

    /**
     * 表：apply_orders
     * 字段：makeup_fee
     * 注释：补考费
     *
     * @mbggenerated
     */
    private BigDecimal makeupFee;

    /***
     * 退费时间
     */
    private Date retreatFeeDate;
    /***
     * 退费
     */
    private BigDecimal retreatFee;

    private BigDecimal orterFee;

    private int stuUserInfoId;

    private int institutionInfoId;

    /**
     * 来源类型
     */
    private String sourceType;

    /**
     * 来源类型2
     */
    private String sourceTypeSec;

    /**
     * 来源值
     */
    private String sourceValue;
    private String sourceValueName;
    /**
     * 来源备注
     */
    private String sourceRemarks;

    /**
     * 来源信息全称
     */
    private String sourceWholeText;
    
    /**
     * 分期首付结清时间
     */
    private Date stagingClearTime;
    
    /**
     * 分期首付结清账号
     */
    private Integer stagingClearAccount;
    
    /**
     * 押金费退还时间
     */
    private Date depositClearTime;
    
    /**
     * 押金费退还账号
     */
    private Integer depositClearAccount;

    public String getSourceValueName() {
        try {
            int partnerId = Integer.parseInt(this.getSourceValue());
            return PartnerUtils.getName(partnerId);
        } catch (Exception e) {
            return this.getSourceValue();
        }
    }

    public String getSourceWholeText() {

        StringBuffer sb = new StringBuffer();

        CfgParamEntity fst = ParamsUtils.getObject(5, 0, this.getSourceType());
        CfgParamEntity sec = ParamsUtils.getObject(5, fst.getId(), this.getSourceTypeSec());
        sb.append(StringUtils.isBlank(fst.getText())?"":fst.getText()).append(" - ").append(StringUtils.isBlank(sec.getText())?"":sec.getText()).append(" - ");
        if (StringUtils.isNotBlank(this.getSourceValue()) && Constants.NUMERI_PATTERN.matcher(this.getSourceValue()).matches()) {
            Integer sourceValueId = Integer.parseInt(this.getSourceValue());
            String name = PartnerUtils.getName(sourceValueId);
            String str = StringUtils.isBlank(this.getSourceValue())?"":this.getSourceValue();
            sb.append(StringUtils.isBlank(name) ?  str : name);
        } else {
            String str = StringUtils.isBlank(this.getSourceValue())?"":this.getSourceValue();
            sb.append(str);
        }
        return sb.toString();
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getSourceTypeSec() {
        return sourceTypeSec;
    }

    public void setSourceTypeSec(String sourceTypeSec) {
        this.sourceTypeSec = sourceTypeSec;
    }

    public String getSourceValue() {
        return sourceValue;
    }

    public void setSourceValue(String sourceValue) {
        this.sourceValue = sourceValue;
    }

    public String getSourceRemarks() {
        return sourceRemarks;
    }

    public void setSourceRemarks(String sourceRemarks) {
        this.sourceRemarks = sourceRemarks;
    }

    public int getInstitutionInfoId() {
        return institutionInfoId;
    }

    public void setInstitutionInfoId(int institutionInfoId) {
        this.institutionInfoId = institutionInfoId;
    }

    public int getStuUserInfoId() {
        return stuUserInfoId;
    }

    public void setStuUserInfoId(int stuUserInfoId) {
        this.stuUserInfoId = stuUserInfoId;
    }

    public BigDecimal getOrterFee() {
        return orterFee;
    }

    public void setOrterFee(BigDecimal orterFee) {
        this.orterFee = orterFee;
    }

    public Date getRetreatFeeDate() {
        return retreatFeeDate;
    }

    public void setRetreatFeeDate(Date retreatFeeDate) {
        this.retreatFeeDate = retreatFeeDate;
    }

    public BigDecimal getRetreatFee() {
        return retreatFee;
    }

    public void setRetreatFee(BigDecimal retreatFee) {
        this.retreatFee = retreatFee;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(Integer classNumber) {
        this.classNumber = classNumber;
    }

    public String getIsExam() {
        return isExam;
    }

    public void setIsExam(String isExam) {
        this.isExam = isExam == null ? null : isExam.trim();
    }

    public String getIsStaging() {
        return isStaging;
    }

    public void setIsStaging(String isStaging) {
        this.isStaging = isStaging == null ? null : isStaging.trim();
    }

    public BigDecimal getSchoolFee() {
        return schoolFee;
    }

    public void setSchoolFee(BigDecimal schoolFee) {
        this.schoolFee = schoolFee;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public BigDecimal getBookFree() {
        return bookFree;
    }

    public void setBookFree(BigDecimal bookFree) {
        this.bookFree = bookFree;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public BigDecimal getFirstPay() {
        return firstPay;
    }

    public void setFirstPay(BigDecimal firstPay) {
        this.firstPay = firstPay;
    }

    public String getIsClear() {
        return isClear;
    }

    public void setIsClear(String isClear) {
        this.isClear = isClear == null ? null : isClear.trim();
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Date getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(Date handleTime) {
        this.handleTime = handleTime;
    }

    public Integer getHandler() {
        return handler;
    }

    public void setHandler(Integer handler) {
        this.handler = handler;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus == null ? null : orderStatus.trim();
    }

    public Integer getModifyAccount() {
		return modifyAccount;
	}

	public void setModifyAccount(Integer modifyAccount) {
		this.modifyAccount = modifyAccount;
	}

	public String getHandleStatus() {
        return handleStatus;
    }

    public void setHandleStatus(String handleStatus) {
        this.handleStatus = handleStatus == null ? null : handleStatus.trim();
    }

    public Integer getPackageId() {
        return packageId;
    }

    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType == null ? null : orderType.trim();
    }

    public String getClassTime() {
        return classTime;
    }

    public void setClassTime(String classTime) {
        this.classTime = classTime == null ? null : classTime.trim();
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }


    public String getIsHasPf() {
        return isHasPf;
    }

    public void setIsHasPf(String isHasPf) {
        this.isHasPf = isHasPf == null ? null : isHasPf.trim();
    }

    public BigDecimal getMakeupFee() {
        return makeupFee;
    }

    public void setMakeupFee(BigDecimal makeupFee) {
        this.makeupFee = makeupFee;
    }

    public Integer getUserAge() {
        return userAge;
    }

    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }

	public Date getStagingClearTime() {
		return stagingClearTime;
	}

	public void setStagingClearTime(Date stagingClearTime) {
		this.stagingClearTime = stagingClearTime;
	}

	public Integer getStagingClearAccount() {
		return stagingClearAccount;
	}

	public void setStagingClearAccount(Integer stagingClearAccount) {
		this.stagingClearAccount = stagingClearAccount;
	}

	public Date getDepositClearTime() {
		return depositClearTime;
	}

	public void setDepositClearTime(Date depositClearTime) {
		this.depositClearTime = depositClearTime;
	}

	public Integer getDepositClearAccount() {
		return depositClearAccount;
	}

	public void setDepositClearAccount(Integer depositClearAccount) {
		this.depositClearAccount = depositClearAccount;
	}
    
}