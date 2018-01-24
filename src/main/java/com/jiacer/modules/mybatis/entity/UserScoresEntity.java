package com.jiacer.modules.mybatis.entity;

import java.util.List;

import com.jiacer.modules.mybatis.model.CertAuthority;
import com.jiacer.modules.mybatis.model.UserScores;
import com.jiacer.modules.system.config.DBStatus;

/** 
* @ClassName: UserScoresEntity 
* @Description: 学员成绩表
* @author 贺章鹏
* @date 2016年10月21日 下午4:45:12 
*  
*/
public class UserScoresEntity extends UserScores{

	private static final long serialVersionUID = 1L;
	
	private StuUserInfoEntity stuUserInfo;
	
	private UserBaseInfoEntity userInfo;
	
	private UserExtendInfoEntity userExtend;
	
	private String examResultName;

	/**课程名称*/
	private String learnLypesName;
	
	/**鉴定等级名*/
	private String authenticateGradeName;
	
	/**发证机构列表***/
	private List<CertAuthority> certAuthorityList;
	
	/**鉴定等级列表*/
	private List<ControlEntity> getAuthenticateGradeList;
	
	/**发证机构名*/
	private String certAuthorityName;
	
	private String issuingDateTxt;
	
	

	public String getIssuingDateTxt() {
		return issuingDateTxt;
	}

	public void setIssuingDateTxt(String issuingDateTxt) {
		this.issuingDateTxt = issuingDateTxt;
	}

	public StuUserInfoEntity getStuUserInfo() {
		return stuUserInfo;
	}

	public void setStuUserInfo(StuUserInfoEntity stuUserInfo) {
		this.stuUserInfo = stuUserInfo;
	}

	public String getCertAuthorityName() {
		return certAuthorityName;
	}

	public void setCertAuthorityName(String certAuthorityName) {
		this.certAuthorityName = certAuthorityName;
	}

	public List<CertAuthority> getCertAuthorityList() {
		return certAuthorityList;
	}

	public void setCertAuthorityList(List<CertAuthority> certAuthorityList) {
		this.certAuthorityList = certAuthorityList;
	}

	public List<ControlEntity> getGetAuthenticateGradeList() {
		return getAuthenticateGradeList;
	}

	public void setGetAuthenticateGradeList(List<ControlEntity> getAuthenticateGradeList) {
		this.getAuthenticateGradeList = getAuthenticateGradeList;
	}

	public String getAuthenticateGradeName() {
		return authenticateGradeName;
	}

	public void setAuthenticateGradeName(String authenticateGradeName) {
		this.authenticateGradeName = authenticateGradeName;
	}

	public String getExamResultName() {
		return examResultName;
	}

	public void setExamResultName(String examResultName) {
		this.examResultName = examResultName;
	}

	public String getLearnLypesName() {
		return learnLypesName;
	}

	public void setLearnLypesName(String learnLypesName) {
		this.learnLypesName = learnLypesName;
	}

	public UserBaseInfoEntity getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserBaseInfoEntity userInfo) {
		this.userInfo = userInfo;
	}

	public UserExtendInfoEntity getUserExtend() {
		return userExtend;
	}

	public void setUserExtend(UserExtendInfoEntity userExtend) {
		this.userExtend = userExtend;
	}
	
	
	
}
