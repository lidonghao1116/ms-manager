package com.jiacer.modules.mybatis.dao;

import java.util.List;
import java.util.Map;

import com.jiacer.modules.common.persistence.CrudDao;
import com.jiacer.modules.common.persistence.annotation.MyBatisDao;
import com.jiacer.modules.mybatis.entity.LearnTypesEntity;
import com.jiacer.modules.mybatis.entity.PartnersEntity;

@MyBatisDao
public interface PartnersMapper extends CrudDao<PartnersEntity> {

	/**
	 * @param map
	 * @return
	 */
	Integer count(Map<Object, Object> map);
	
	List<PartnersEntity> findAllList(Map<Object, Object> map);

	/**
	 * 根据id获取合作商对象
	 * 
	 * @param id
	 * @return
	 */
	PartnersEntity getByLearnName(Integer id);
	
	public PartnersEntity getById(Integer id);

	List<PartnersEntity> getBySoucerValueId(Integer id);

	/**
	 * @param map
	 * @return
	 */
	List<PartnersEntity> getPageList(Map<Object, Object> map);

	List<PartnersEntity> getCountByUserId(Map<Object, Object> map);

	List<PartnersEntity> getCountByUser(Map<Object, Object> map);
	
	
	public int getProCount(Map<Object, Object> map);
	
	
}