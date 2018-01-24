package com.jiacer.modules.business.bean.operation;

import java.util.Date;
import java.util.Map;
import com.google.common.collect.Maps;
import com.jiacer.modules.common.utils.SqlUtils;
import com.jiacer.modules.common.utils.StringUtils;
import com.jiacer.modules.mybatis.entity.PartnersEntity;
import com.jiacer.modules.system.config.DBStatus;
import com.jiacer.modules.system.utils.UserUtils;

/** 
* @ClassName: PartnersOpt 
* @Description: 合作商对象参数操作
* @author 贺章鹏
* @date 2016年10月24日 下午3:27:52 
*  
*/
public class PartnersOpt {
	/**
	 * 构建查询参数Map
	 * @param learnTypesEntity
	 * @return
	 */
	public static Map<Object, Object> buildMap(PartnersEntity partnersEntity){
		Map<Object, Object> map=Maps.newHashMap();
		if(!StringUtils.isEmpty(partnersEntity.getPartnerName())){
			map.put("partnerName", SqlUtils.like(partnersEntity.getPartnerName()));
		}
		if(!StringUtils.isEmpty(partnersEntity.getPartnerType())){
			map.put("partnerType", partnersEntity.getPartnerType());
		}
		
		if(!StringUtils.isEmpty(partnersEntity.getContactPhone())){
			map.put("contactPhone", partnersEntity.getContactPhone());
		}
		
		if(!StringUtils.isEmpty(partnersEntity.getProvince())){
			map.put("province", partnersEntity.getProvince());
		}
		
		if(!StringUtils.isEmpty(partnersEntity.getCity())){
			map.put("city", partnersEntity.getCity());
		}
		
		if(!StringUtils.isEmpty(partnersEntity.getCounty())){
			map.put("county", partnersEntity.getCounty());
		}
		
		map.put("isUsable", DBStatus.IsUsable.TRUE);
		return map;
	}
	
	/**
	 * 构建新增参数
	 * @param obj
	 * @return
	 */
	public static PartnersEntity buildAdd(PartnersEntity obj){
		
		obj.setIsUsable(DBStatus.IsUsable.TRUE);
		obj.setAddAccount(UserUtils.getAccount());
		obj.setAddTime(new Date());
		obj.setInstitutionInfoId(UserUtils.getUser().getInstitutionInfoId());
		
		return obj;
	}
	
	/**
	 * 建构修改参数
	 * @param obj
	 * @param param
	 * @return
	 */
	public static PartnersEntity buildUpdate(PartnersEntity obj,PartnersEntity param){
		
		obj.setProvince(StringUtils.isEmpty(param.getProvince().trim())?"":param.getProvince().trim());
		obj.setCity(StringUtils.isEmpty(param.getCity().trim())?"":param.getCity().trim());
		obj.setCounty(StringUtils.isEmpty(param.getCounty().trim())?"":param.getCounty().trim());
		obj.setAddress(StringUtils.isEmpty(param.getAddress().trim())?"":param.getAddress().trim());
		obj.setZipCode(param.getZipCode().trim());
		obj.setContactPhone(StringUtils.isEmpty(param.getContactPhone().trim())?"":param.getContactPhone().trim());
		obj.setContacts(StringUtils.isEmpty(param.getContacts().trim())?"":param.getContacts().trim());
		obj.setStorePhone(param.getStorePhone().trim());
		obj.setBrokerageId(param.getBrokerageId()!=null?param.getBrokerageId():null);
		obj.setModifyAccount(UserUtils.getAccount());
		obj.setModifyTime(new Date());
		obj.setPartnerName(StringUtils.isEmpty(param.getPartnerName().trim())?"":param.getPartnerName().trim());
		obj.setSalesman(StringUtils.isEmpty(param.getSalesman().trim())?"":param.getSalesman().trim());
		return obj;
	}
	
	/**
	 * 构建删除参数
	 * @param obj
	 * @return
	 */
	public static PartnersEntity buildDelete(PartnersEntity obj){
		obj.setIsUsable(DBStatus.IsUsable.FALSE);
		return obj;
	}
}
