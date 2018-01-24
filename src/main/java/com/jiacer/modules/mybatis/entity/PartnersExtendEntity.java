package com.jiacer.modules.mybatis.entity;

import com.jiacer.modules.mybatis.model.Partners;
import com.jiacer.modules.system.utils.AreasUtils;

/**
 * @ClassName: PartnersEntity
 * @Description: 合作商
 * @author 贺章鹏
 * @date 2016年10月21日 下午2:11:14
 * 
 */
public class PartnersExtendEntity extends Partners {

	private static final long serialVersionUID = 1L;

	private Integer total;

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
	
	// /***********用于中文显示***************/
	// @SuppressWarnings("unused")
	// private String proviceName;
	//
	// @SuppressWarnings("unused")
	// private String countyName;
	//
	// @SuppressWarnings("unused")
	// private String cityName;
	//
	// public String getProviceName() {
	// return AreasUtils.getName(super.getProvince());
	// }
	//
	// public void setProviceName(String proviceName) {
	// this.proviceName = proviceName;
	// }
	//
	// public String getCountyName() {
	// return AreasUtils.getName(super.getCounty());
	// }
	//
	// public void setCountyName(String countyName) {
	// this.countyName = countyName;
	// }
	//
	// public String getCityName() {
	// return AreasUtils.getName(super.getCity());
	// }
	//
	// public void setCityName(String cityName) {
	// this.cityName = cityName;
	// }
}
