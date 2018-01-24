package com.jiacer.modules.mybatis.entity;

import java.util.List;

import com.jiacer.modules.mybatis.model.Partners;
import com.jiacer.modules.system.utils.AreasUtils;

/**
 * @ClassName: PartnersEntity
 * @Description: 合作商
 * @author 贺章鹏
 * @date 2016年10月21日 下午2:11:14
 * 
 */
public class PartnersEntity extends Partners {

	private static final long serialVersionUID = 1L;
	// 输送总人数字段
	private Integer count;
	private Integer countTotal;
	private List<PartnersEntity> leatnList;

	public List<PartnersEntity> getLeatnList() {
		return leatnList;
	}

	public void setLeatnList(List<PartnersEntity> leatnList) {
		this.leatnList = leatnList;
	}

	public Integer getCountTotal() {
		return countTotal;
	}

	public void setCountTotal(Integer countTotal) {
		this.countTotal = countTotal;
	}

	private String typeName;

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	private String salesman;

	public String getSalesman() {
		return salesman;
	}

	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	private UserBaseInfoEntity userBaseInfo;

	private UserExtendInfoEntity userExtendInfo;

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

	/*********** 用于中文显示 ***************/
	@SuppressWarnings("unused")
	private String proviceName;

	@SuppressWarnings("unused")
	private String countyName;

	@SuppressWarnings("unused")
	private String cityName;

	public String getProviceName() {
		return AreasUtils.getName(super.getProvince());
	}

	public void setProviceName(String proviceName) {
		this.proviceName = proviceName;
	}

	public String getCountyName() {
		return AreasUtils.getName(super.getCounty());
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}

	public String getCityName() {
		return AreasUtils.getName(super.getCity());
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
}
