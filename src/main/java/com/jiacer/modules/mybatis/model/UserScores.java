package com.jiacer.modules.mybatis.model;

import java.math.BigDecimal;
import java.util.Date;


public class UserScores extends UserScoresKey {
	private static final long serialVersionUID = 1L;
    private String orderNo;
    private BigDecimal theoryScores;
    private BigDecimal poScores;
    private String examResult;
    private String dealResult;
    private String certificateNo;
    private BigDecimal makeupExamFree;
    private BigDecimal comprehensiveScores;
    private BigDecimal abilityScores;
    private Date issuingDate;
    private String isExam;
    private String certAuthorityId;
    private String authenticateGrade;
    private String typeOfWork; //工种
    private String certName; //证书名称（课程名称）
    private Integer stuUserInfoId;
    
	public Integer getStuUserInfoId() {
		return stuUserInfoId;
	}

	public void setStuUserInfoId(Integer stuUserInfoId) {
		this.stuUserInfoId = stuUserInfoId;
	}

	public String getTypeOfWork() {
		return typeOfWork;
	}

	public void setTypeOfWork(String typeOfWork) {
		this.typeOfWork = typeOfWork;
	}

	public String getCertName() {
		return certName;
	}

	public void setCertName(String certName) {
		this.certName = certName;
	}

	public String getIsExam() {
		return isExam;
	}

	public void setIsExam(String isExam) {
		this.isExam = isExam;
	}

	public String getCertAuthorityId() {
		return certAuthorityId;
	}

	public void setCertAuthorityId(String certAuthorityId) {
		this.certAuthorityId = certAuthorityId;
	}

	public String getAuthenticateGrade() {
		return authenticateGrade;
	}

	public void setAuthenticateGrade(String authenticateGrade) {
		this.authenticateGrade = authenticateGrade;
	}

	public Date getIssuingDate() {
		return issuingDate;
	}

	public void setIssuingDate(Date issuingDate) {
		this.issuingDate = issuingDate;
	}

	public BigDecimal getComprehensiveScores() {
		return comprehensiveScores;
	}

	public void setComprehensiveScores(BigDecimal comprehensiveScores) {
		this.comprehensiveScores = comprehensiveScores;
	}

	public BigDecimal getAbilityScores() {
		return abilityScores;
	}

	public void setAbilityScores(BigDecimal abilityScores) {
		this.abilityScores = abilityScores;
	}

	public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public BigDecimal getTheoryScores() {
        return theoryScores;
    }

    public void setTheoryScores(BigDecimal theoryScores) {
        this.theoryScores = theoryScores;
    }

    public BigDecimal getPoScores() {
        return poScores;
    }

    public void setPoScores(BigDecimal poScores) {
        this.poScores = poScores;
    }

    public String getExamResult() {
        return examResult;
    }

    public void setExamResult(String examResult) {
        this.examResult = examResult == null ? null : examResult.trim();
    }

    public String getDealResult() {
        return dealResult;
    }

    public void setDealResult(String dealResult) {
        this.dealResult = dealResult == null ? null : dealResult.trim();
    }

    public String getCertificateNo() {
        return certificateNo;
    }

    public void setCertificateNo(String certificateNo) {
        this.certificateNo = certificateNo == null ? null : certificateNo.trim();
    }

    public BigDecimal getMakeupExamFree() {
        return makeupExamFree;
    }

    public void setMakeupExamFree(BigDecimal makeupExamFree) {
        this.makeupExamFree = makeupExamFree;
    }
}