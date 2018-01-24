package com.jiacer.modules.business.validate;

import java.util.HashMap;
import java.util.Map;

import com.jiacer.modules.business.service.PartnersService;
import com.jiacer.modules.common.utils.JsonResult;
import com.jiacer.modules.common.utils.StringUtils;
import com.jiacer.modules.mybatis.entity.PartnersEntity;

/** 
* @ClassName: PartnersValidate 
* @Description: 合作商管理校验类
* @author 贺章鹏
* @date 2016年10月19日 下午4:16:46 
*  
*/
public class PartnersValidate {
	/**
	 * 新增方法参数校验
	 * @param partnersEntity
	 * @return
	 */
	public static JsonResult addValidate(PartnersEntity partnersEntity,PartnersService partnersService){
		
		if(StringUtils.isEmpty(partnersEntity.getPartnerType())){
			return new JsonResult(false,"请选择合作商类型",null);
		}
		
		if(StringUtils.isEmpty(partnersEntity.getPartnerName())){
			return new JsonResult(false,"请填写合作商名称",null);
		}else{
			Map map = new HashMap();
			map.put("partnerName", partnersEntity.getPartnerName());
			if(partnersService.count(map) != 0){
				return new JsonResult(false,"合作商名称已经存在",null);
			}
			
		}
		if(StringUtils.isEmpty(partnersEntity.getProvince())
				|| StringUtils.isEmpty(partnersEntity.getCity())
				|| StringUtils.isEmpty(partnersEntity.getCounty())
				|| StringUtils.isEmpty(partnersEntity.getAddress())){
			return new JsonResult(false,"请填写合作商地址",null);
		}
		if(StringUtils.isEmpty(partnersEntity.getContacts())){
			return new JsonResult(false,"请填写联系人",null);
		}
		if(StringUtils.isEmpty(partnersEntity.getContactPhone())){
			return new JsonResult(false,"请填写手机号码",null);
		}else{
			if(!FormatUtils.isPhoneLegal(partnersEntity.getContactPhone())){
				return new JsonResult(false,"请填写正确的手机号码",null);
			}	
		}
		return new JsonResult(true,null,null);
	}
	
	/**
	 * 修改方法参数校验
	 * @param partnersEntity
	 * @return
	 */
	public static JsonResult modifyValidate(PartnersEntity partnersEntity,PartnersService partnersService){
		
		if(StringUtils.isEmpty(partnersEntity.getProvince())
				|| StringUtils.isEmpty(partnersEntity.getCity())
				|| StringUtils.isEmpty(partnersEntity.getCounty())
				|| StringUtils.isEmpty(partnersEntity.getAddress())){
			return new JsonResult(false,"请填写合作商地址",null);
		}
		
		if(StringUtils.isEmpty(partnersEntity.getContacts())){
			return new JsonResult(false,"请填写联系人",null);
		}
		
		if(StringUtils.isEmpty(partnersEntity.getContactPhone())){
			return new JsonResult(false,"请填写手机号码",null);
		}else{
			if(!FormatUtils.isPhoneLegal(partnersEntity.getContactPhone())){
				return new JsonResult(false,"请填写正确的手机号码",null);
			}	
		}
		PartnersEntity partnersEntityOld = partnersService.getById(partnersEntity.getId());
		if(!partnersEntityOld.getPartnerName().equals(partnersEntity.getPartnerName())){
			Map map = new HashMap();
			map.put("partnerName", partnersEntity.getPartnerName());
			if(partnersService.count(map) != 0){
				return new JsonResult(false,"合作商名称已经存在",null);
			}
		}
		return new JsonResult(true,null,null);
	}
	
}












