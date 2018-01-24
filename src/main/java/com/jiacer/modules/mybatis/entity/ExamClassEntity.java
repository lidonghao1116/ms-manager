package com.jiacer.modules.mybatis.entity;

import java.util.List;

import com.jiacer.modules.business.bean.DictionariesUtil;
import com.jiacer.modules.business.bean.ExamResult;
import com.jiacer.modules.business.utils.CoursesUtils;
import com.jiacer.modules.business.utils.SchoolsUtils;
import com.jiacer.modules.mybatis.model.ExamClass;
import com.jiacer.modules.system.utils.DictionaryUtils;

/**
 * 
* @ClassName: ExamClassEntity 
* @Description: 考试班级表
* @author 贺章鹏
* @date 2016年10月18日 下午12:07:14 
*
 */
public class ExamClassEntity extends ExamClass{
	private static final long serialVersionUID = 1L;
	
	private String courseName;
	private String schoolName;
	private String authenticateGrade;
	
	/************用于中文显示****************/
	/**
	@SuppressWarnings("unused")
	private String courseName;
	
	@SuppressWarnings("unused")
	private String schoolName;
	**/
	@SuppressWarnings("unused")
	private String examStatusName;
	
	/***************用于数据展示**********************/
	private ExamResult examResult;//考试结果
	
	private List<ApplyOrdersEntity> applyOrders;//报考学员
	
	private List<String> examStatusList;
	
	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	
	
	public String getAuthenticateGrade() {
		if(authenticateGrade != null){
			return DictionariesUtil.getAuthenticateGradeName(authenticateGrade);
		}
		return authenticateGrade;
	}

	public void setAuthenticateGrade(String authenticateGrade) {
		this.authenticateGrade = authenticateGrade;
	}

	/**
	public String getCourseName() {
		return CoursesUtils.getName(super.getCourseId());
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	**/
	/**
	public String getSchoolName() {
		return SchoolsUtils.getName(super.getShoolId());
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	**/
	public ExamResult getExamResult() {
		return examResult;
	}

	public void setExamResult(ExamResult examResult) {
		this.examResult = examResult;
	}
	
	public List<ApplyOrdersEntity> getApplyOrders() {
		return applyOrders;
	}

	public void setApplyOrders(List<ApplyOrdersEntity> applyOrders) {
		this.applyOrders = applyOrders;
	}

	public String getExamStatusName() {
		return DictionaryUtils.getExamStatus(super.getExamStatus());
	}

	public void setExamStatusName(String examStatusName) {
		this.examStatusName = examStatusName;
	}

	public List<String> getExamStatusList() {
		return examStatusList;
	}

	public void setExamStatusList(List<String> examStatusList) {
		this.examStatusList = examStatusList;
	}
}
