package com.jiacer.modules.business.validate;

import com.jiacer.modules.business.service.SchoolsService;
import com.jiacer.modules.common.utils.JsonResult;
import com.jiacer.modules.common.utils.StringUtils;
import com.jiacer.modules.mybatis.entity.SchoolsEntity;

/** 
* @ClassName: ShoolsValidate 
* @Description: 学校管理校验类
* @author 贺章鹏
* @date 2016年10月19日 下午4:17:16 
*  
*/
public class SchoolsValidate {
	/**
	 * 新增方法参数校验
	 * @param shoolsEntity
	 * @return
	 */
	public static JsonResult addValidate(SchoolsEntity shoolsEntity,SchoolsService schoolsService){
		
		if(StringUtils.isEmpty(shoolsEntity.getSchoolName())){
			return new JsonResult(false,"请填写学校名称",null);
		}
		
		if(StringUtils.isEmpty(shoolsEntity.getSchoolAddress())){
			return new JsonResult(false,"请填写学校地址",null);
		}
		
		if(StringUtils.isEmpty(shoolsEntity.getContacts())){
			return new JsonResult(false,"请填写联系人",null);
		}
		
		if(StringUtils.isEmpty(shoolsEntity.getContactPhone())){
			return new JsonResult(false,"请填写联系人电话",null);
		}else{
			if(!FormatUtils.isPhoneLegal(shoolsEntity.getContactPhone())){
				return new JsonResult(false,"请填写正确的联系人电话",null);
			}
		}
		/**
		if(StringUtils.isEmpty(shoolsEntity.getApplyCourses())){
			return new JsonResult(false,"请勾选报考课程",null);
		}
		**/
		if(shoolsEntity != null){
			if(!schoolsService.isSchoolName(shoolsEntity)){
				return new JsonResult(false,"学校名称已存在，请重新填写",null);
			}
		}
		return new JsonResult(true,null,null);
	}
	
	/**
	 * 修改方法参数校验
	 * @param shoolsEntity
	 * @return
	 */
	public static JsonResult modifyValidate(SchoolsEntity shoolsEntity){
		
		if(StringUtils.isEmpty(shoolsEntity.getContacts())){
			return new JsonResult(false,"请填写联系人",null);
		}
		
		if(StringUtils.isEmpty(shoolsEntity.getContactPhone())){
			return new JsonResult(false,"请填写联系人电话",null);
		}else{
			if(!FormatUtils.isPhoneLegal(shoolsEntity.getContactPhone())){
				return new JsonResult(false,"请填写正确的联系人电话",null);
			}
		}
		
		if(StringUtils.isEmpty(shoolsEntity.getApplyCourses())){
			return new JsonResult(false,"请勾选报考课程",null);
		}
		return new JsonResult(true,null,null);
	}
}
