package com.jiacer.modules.mybatis.dao;

import java.util.List;
import java.util.Map;

import com.jiacer.modules.common.persistence.CrudDao;
import com.jiacer.modules.common.persistence.annotation.MyBatisDao;
import com.jiacer.modules.mybatis.entity.PartnersEntity;
import com.jiacer.modules.mybatis.entity.UserExtendInfoEntity;

@MyBatisDao
public interface UserExtendInfoMapper extends CrudDao<UserExtendInfoEntity>{
	//public UserExtendInfoEntity getBySourceValue(String sourceValue);
	/**
	 * @param map
	 * @return
	 */
	List<UserExtendInfoEntity> getBySourceValue(String sourceValue);
	List<UserExtendInfoEntity>   findAll();

	UserExtendInfoEntity getCertNoUserExtendInfo(Map map);
	
}