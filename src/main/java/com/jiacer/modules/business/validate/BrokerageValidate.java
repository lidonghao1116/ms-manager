package com.jiacer.modules.business.validate;

import java.util.List;
import com.jiacer.modules.common.utils.JsonResult;
import com.jiacer.modules.common.utils.StringUtils;
import com.jiacer.modules.mybatis.entity.BrokerageRulesEntity;
import com.jiacer.modules.mybatis.entity.BrokerageSchemeEntity;

/** 
* @ClassName: BrokerageValidate 
* @Description: 佣金管理校验类
* @author 贺章鹏
* @date 2016年10月19日 下午4:14:41 
*  
*/
public class BrokerageValidate {
	
	/**
	 * 新增方法参数校验
	 * @param partnersEntity
	 * @return
	 */
	public static JsonResult addValidate(BrokerageSchemeEntity brokerageSchemeEntity){
		
		if(StringUtils.isEmpty(brokerageSchemeEntity.getSchemeName())){
			return new JsonResult(false,"请填写方案名称",null);
		}
		
		if(brokerageSchemeEntity.getRules()==null || brokerageSchemeEntity.getRules().size()<1){
			return new JsonResult(false,"方案对应产品参数错误",null);
		}
		
		
		if(!checkRules(brokerageSchemeEntity.getRules()).isSuccess()){
			return checkRules(brokerageSchemeEntity.getRules());
		}
		
		return new JsonResult(true,null,null);
	}
	
	/**
	 * 修改方法参数校验
	 * @param partnersEntity
	 * @return
	 */
	public static JsonResult modifyValidate(BrokerageSchemeEntity brokerageSchemeEntity){
		
		if(StringUtils.isEmpty(brokerageSchemeEntity.getSchemeName())){
			return new JsonResult(false,"请填写方案名称",null);
		}
		
		if(brokerageSchemeEntity.getRules()==null || brokerageSchemeEntity.getRules().size()<1){
			return new JsonResult(false,"方案对应产品参数错误",null);
		}
		
		if(!checkRules(brokerageSchemeEntity.getRules()).isSuccess()){
			return checkRules(brokerageSchemeEntity.getRules());
		}
		
		return new JsonResult(true,null,null);
		
	}
	
	protected static JsonResult checkRules(List<BrokerageRulesEntity> rules){
		
		JsonResult jsonResult =new JsonResult(true,null,null);
		for(BrokerageRulesEntity brokerageRule:rules){
			
			if(brokerageRule.getBonusAmount()==null){
				jsonResult=new JsonResult(false,"请配置方案比例",null);
				break;
			}
		}
		return jsonResult;
	}
}
