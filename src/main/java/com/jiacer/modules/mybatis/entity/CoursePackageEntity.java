package com.jiacer.modules.mybatis.entity;

import java.util.Date;
import com.jiacer.modules.mybatis.model.CoursePackage;
import com.jiacer.modules.system.utils.DictionaryUtils;

/**
 * 
* @ClassName: CoursePackageEntity 
* @Description: 课程销售表
* @author 贺章鹏
* @date 2016年10月18日 下午12:06:23 
*
 */
public class CoursePackageEntity extends CoursePackage{

	private static final long serialVersionUID = 1L;
	
	/*************用于查询条件**************/
	private Date startDate;
	
	private Date endDate;
	
	/*************用于显示中文名称**************/
	@SuppressWarnings("unused")
	private String statusName;

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

	public String getStatusName() {
		return DictionaryUtils.getProductStatus(super.getStatus());
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	
}
