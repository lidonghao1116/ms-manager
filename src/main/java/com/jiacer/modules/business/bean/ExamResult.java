package com.jiacer.modules.business.bean;

import java.math.BigDecimal;

import com.jiacer.modules.common.persistence.ModelSerializable;

/** 
* @ClassName: ExamResult 
* @Description: 考试结果
* @author 贺章鹏
* @date 2016年10月21日 下午4:16:59 
*  
*/
public class ExamResult implements ModelSerializable{

	private static final long serialVersionUID = 1L;

	private Integer totleNum;//报名数
	
	private Integer inputNum;//录入数
	
	private Integer qualifiedNum;//合格数
	
	private Integer lackExamNum;//缺考数
	
	@SuppressWarnings("unused")
	private BigDecimal qualifiedRate;//合格率
	
	private Integer unqualifiedNum;//不合格

	public Integer getTotleNum() {
		return totleNum;
	}

	public void setTotleNum(Integer totleNum) {
		this.totleNum = totleNum;
	}

	public Integer getInputNum() {
		return inputNum;
	}

	public void setInputNum(Integer inputNum) {
		this.inputNum = inputNum;
	}

	public Integer getQualifiedNum() {
		return qualifiedNum;
	}

	public void setQualifiedNum(Integer qualifiedNum) {
		this.qualifiedNum = qualifiedNum;
	}

	public BigDecimal getQualifiedRate() {
		if(inputNum!=null && inputNum>0){
			BigDecimal qualified=new BigDecimal(qualifiedNum);
			BigDecimal total=new BigDecimal(totleNum);
			return qualified.divide(total,4,BigDecimal.ROUND_HALF_UP);
		}else{
			return BigDecimal.ZERO;
		}
	}

	public void setQualifiedRate(BigDecimal qualifiedRate) {
		this.qualifiedRate = qualifiedRate;
	}

	public Integer getLackExamNum() {
		return lackExamNum;
	}

	public void setLackExamNum(Integer lackExamNum) {
		this.lackExamNum = lackExamNum;
	}

	public Integer getUnqualifiedNum() {
		return unqualifiedNum;
	}

	public void setUnqualifiedNum(Integer unqualifiedNum) {
		this.unqualifiedNum = unqualifiedNum;
	}
	
}
