package com.jiacer.modules.mybatis.dao;

import java.util.List;
import java.util.Map;

import com.jiacer.modules.common.persistence.CrudDao;
import com.jiacer.modules.common.persistence.annotation.MyBatisDao;
import com.jiacer.modules.mybatis.entity.ApplyOrdersEntity;
import org.apache.ibatis.annotations.Param;

@MyBatisDao
public interface ApplyOrdersMapper extends CrudDao<ApplyOrdersEntity>{

	/**
	 * @param map
	 * @return
	 */
	Integer count(Map<Object, Object> map);
	
	Integer getCount(Map<Object, Object> map);
	
	Integer getMaxId(Map<Object, Object> map);
	
	Integer getIsBk(Map<Object, Object> map);
	
	ApplyOrdersEntity getUserIdClassIdApplyOrders(Map<Object, Object> map);
	
	ApplyOrdersEntity getAppSchool(Map<Object, Object> map);
	
	/**
	 * @param map
	 * @return
	 */
	List<ApplyOrdersEntity> getPageList(Map<Object, Object> map);
	
	List<ApplyOrdersEntity> getIsSchoolCourse(Map<Object, Object> map);
	
	List<ApplyOrdersEntity> getPageListStu(Map<Object, Object> map);
	Integer countStu(Map<Object, Object> map);
	

	/**
	 * 获取个数
	 * @param entity
	 * @return
	 */
	Integer findAllCount(ApplyOrdersEntity entity);
	
	Integer getClassNumberStatus(Integer id,String classNumbre);

	@Override
	ApplyOrdersEntity getById(Integer id);

    Integer getPassedOrder(@Param("stuUserInfoId") Integer appOrderStuUserInfoId, @Param("institutionInfoId") Integer institutionInfoId, @Param("courseId") Integer courseId);
}