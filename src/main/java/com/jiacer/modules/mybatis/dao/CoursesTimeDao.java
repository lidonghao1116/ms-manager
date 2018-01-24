package com.jiacer.modules.mybatis.dao;

import java.util.List;
import java.util.Map;

import com.jiacer.modules.common.persistence.CrudDao;
import com.jiacer.modules.common.persistence.annotation.MyBatisDao;
import com.jiacer.modules.mybatis.entity.CoursesTimeEntity;

@MyBatisDao
public interface CoursesTimeDao extends CrudDao<CoursesTimeEntity>{

	Integer count(Map<Object, Object> map);
	
	/**
	 * @param map
	 * @return
	 */
	List<CoursesTimeEntity> getPageList(Map<Object, Object> map);
	
	Integer countNum();
	
	public List<CoursesTimeEntity> getAllTimeYes();
	
	public List<CoursesTimeEntity> getTemplate(Map<Object, Object> map);
	
	public List<CoursesTimeEntity> getSKTemplate(Map<Object, Object> map);

	/**
	 * 
	 * @MethodName:getTimeInfoByMap
	 * @Type:CoursesTimeDao
	 * @Description:获取课程时间信息
	 * @Return:List<CoursesTimeEntity>
	 * @Param:@param map
	 * @Param:@return
	 * @Thrown:
	 * @Date:Oct 9, 2017 6:17:22 AM
	 */
	CoursesTimeEntity getTimeInfoByMap(Map<Object, Object> map);
}
