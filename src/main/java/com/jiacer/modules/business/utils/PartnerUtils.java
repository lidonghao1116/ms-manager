package com.jiacer.modules.business.utils;

import java.util.List;

import com.jiacer.modules.common.utils.SpringContextHolder;
import com.jiacer.modules.mybatis.dao.PartnersMapper;
import com.jiacer.modules.mybatis.entity.PartnersEntity;
import com.jiacer.modules.system.config.DBStatus;

/** 
* @ClassName: PartnerUtils 
* @author 贺章鹏
* @date 2016年11月8日 下午1:57:06 
*  
*/
public class PartnerUtils {
	private static PartnersMapper partnersDao = SpringContextHolder.getBean(PartnersMapper.class);
	
	public static String getName(Integer id){
		PartnersEntity entity=partnersDao.getById(id);
		if(entity!=null){
			return entity.getPartnerName();
		}else{
			return "";
		}
	}

	public static List<PartnersEntity> getPartners(String type,Integer institutionInfoId){
		PartnersEntity entity=new PartnersEntity();
		entity.setIsUsable(DBStatus.IsUsable.TRUE);
		entity.setPartnerType(type);
		entity.setInstitutionInfoId(institutionInfoId);
		List<PartnersEntity> list=partnersDao.findAllList(entity);
		return list;
	}

}
