package com.jiacer.modules.mybatis.dao;

import java.util.List;
import java.util.Map;

import com.jiacer.modules.common.persistence.CrudDao;
import com.jiacer.modules.common.persistence.annotation.MyBatisDao;
import com.jiacer.modules.mybatis.entity.SysUsersEntity;



@MyBatisDao
public interface SysUsersMapper extends CrudDao<SysUsersEntity>{

	/**
	 * @param map
	 * @return
	 */
	List<SysUsersEntity> getPageList(Map<Object, Object> map);

	/**
	 * @param map
	 * @return
	 */
	Integer count(Map<Object, Object> map);

    SysUsersEntity getRootAccount(Integer institutionInfoId);
    void updatePwd(SysUsersEntity user);
    
}