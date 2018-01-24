package com.jiacer.modules.business.bean.operation;

import java.util.Map;
import com.google.common.collect.Maps;
import com.jiacer.modules.business.bean.IdcardInfoExtractor;
import com.jiacer.modules.business.bean.UserBean;
import com.jiacer.modules.business.bean.form.UsersQuery;
import com.jiacer.modules.common.utils.DateUtils;
import com.jiacer.modules.common.utils.EntryptUtils;
import com.jiacer.modules.common.utils.SqlUtils;
import com.jiacer.modules.common.utils.StringUtils;
import com.jiacer.modules.mybatis.entity.UserBaseInfoEntity;
import com.jiacer.modules.mybatis.entity.UserExtendInfoEntity;
import com.jiacer.modules.system.config.SysConstants;
import com.jiacer.modules.system.config.DBStatus.UserStatus;

/** 
* @ClassName: UsersOpt 
* @Description: 用户对象操作
* @author 贺章鹏
* @date 2016年10月28日 下午3:21:26 
*  
*/
public class UsersOpt {
	
	public static Map<Object, Object> buildMap(UsersQuery usersQuery){
		Map<Object, Object> map=Maps.newHashMap();
		if(!StringUtils.isEmpty(usersQuery.getUserName())){
			map.put("userName", SqlUtils.like(usersQuery.getUserName()));
		}
		
		if(!StringUtils.isEmpty(usersQuery.getSourceType())){
			map.put("sourceType", usersQuery.getSourceType());
		}
		
		if(!StringUtils.isEmpty(usersQuery.getSourceTypeSec())){
			map.put("sourceTypeSec", usersQuery.getSourceTypeSec());
		}
		
		if(usersQuery.getStartDate()!=null){
			map.put("startDate", DateUtils.joinTime(usersQuery.getStartDate(),SysConstants.MIN_TIME));
		}
		if(usersQuery.getEndDate()!=null){
			map.put("endDate", DateUtils.joinTime(usersQuery.getEndDate(),SysConstants.MAX_TIME));
		}
		if(usersQuery.getMobile()!=null){
			map.put("mobile", usersQuery.getMobile());
		}
		return map;
	}
	
	public static UserExtendInfoEntity buildUserExtend(UserExtendInfoEntity obj,UserExtendInfoEntity param){
		
		if(!StringUtils.isEmpty(param.getEducation())){
			obj.setEducation(param.getEducation());
		}
		if(!StringUtils.isEmpty(param.getNation())){
			obj.setNation(param.getNation());
		}
		if(!StringUtils.isEmpty(param.getBirthplace())){
			obj.setBirthplace(param.getBirthplace());
		}
		if(!StringUtils.isEmpty(param.getAddress())){
			 obj.setAddress(param.getAddress());
		}
		if(!StringUtils.isEmpty(param.getUserName())){
			obj.setUserName(param.getUserName());
		}
	    if(!StringUtils.isEmpty(param.getCertNo())){
	    	 obj.setCertNo(param.getCertNo());
	    	 IdcardInfoExtractor idcardInfo=new IdcardInfoExtractor(param.getCertNo()); 
	    	 obj.setSex(idcardInfo.getGender());
	    }
	    obj.setContacts(param.getContacts());
	    obj.setContactPhone(param.getContactPhone());
		return obj;
	}
	
	public static UserBaseInfoEntity buildUserInfo(UserBaseInfoEntity obj,UserBean param){
		UserExtendInfoEntity entity=param.getUserExtend();
		UserBaseInfoEntity user=param.getUserInfo();
		obj.setMobile(user.getMobile());
		obj.setSalt(String.format(SysConstants.ENTRY_STRING, obj.getMobile()));
		obj.setUserStatus(UserStatus.NOMAL);
		obj.setLoginPassword(EntryptUtils.entryptUserPassword(entity.getCertNo().substring(entity.getCertNo().length()-6,entity.getCertNo().length()),user.getMobile()));
		return obj;
	}
}
