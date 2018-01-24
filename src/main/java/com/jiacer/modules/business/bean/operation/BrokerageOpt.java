package com.jiacer.modules.business.bean.operation;

import java.util.Date;
import java.util.Map;
import com.google.common.collect.Maps;
import com.jiacer.modules.common.utils.SqlUtils;
import com.jiacer.modules.common.utils.StringUtils;
import com.jiacer.modules.mybatis.entity.BrokerageSchemeEntity;
import com.jiacer.modules.system.config.DBStatus;
import com.jiacer.modules.system.utils.UserUtils;

/** 
* @ClassName: BrokerageOpt 
* @Description: 提成方案对象参数操作
* @author 贺章鹏
* @date 2016年10月24日 下午3:28:43 
*  
*/
public class BrokerageOpt {
	/**
	 * 构建查询参数Map
	 * @param learnTypesEntity
	 * @return
	 */
	public static Map<Object, Object> buildMap(BrokerageSchemeEntity brokerageSchemeEntity){
		Map<Object, Object> map=Maps.newHashMap();
		if(!StringUtils.isEmpty(brokerageSchemeEntity.getSchemeName())){
			map.put("schemeName", SqlUtils.like(brokerageSchemeEntity.getSchemeName()));
		}
		map.put("isUsable", DBStatus.IsUsable.TRUE);
		return map;
	}
	
	/**
	 * 构建新增参数
	 * @param obj
	 * @return
	 */
	public static BrokerageSchemeEntity buildAdd(BrokerageSchemeEntity obj){
		
		obj.setIsUsable(DBStatus.IsUsable.TRUE);
		obj.setAddAccount(UserUtils.getAccount());
		obj.setAddTime(new Date());
		obj.setModifyTime(new Date());
		obj.setInstitutionInfoId(UserUtils.getUser().getInstitutionInfoId());
		return obj;
	}
	
	/**
	 * 建构修改参数
	 * @param obj
	 * @param param
	 * @return
	 */
	public static BrokerageSchemeEntity buildUpdate(BrokerageSchemeEntity obj,BrokerageSchemeEntity param){
		
		obj.setSchemeName(StringUtils.isEmpty(param.getSchemeName().trim())?"":param.getSchemeName().trim());
		obj.setModifyAccount(UserUtils.getAccount());
		obj.setModifyTime(new Date());
		return obj;
	}
	
	/**
	 * 构建删除参数
	 * @param obj
	 * @return
	 */
	public static BrokerageSchemeEntity buildDelete(BrokerageSchemeEntity obj){
		obj.setIsUsable(DBStatus.IsUsable.FALSE);
		return obj;
	}
	
}
