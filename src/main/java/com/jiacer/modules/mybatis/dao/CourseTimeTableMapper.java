package com.jiacer.modules.mybatis.dao;

import java.util.Map;

import com.jiacer.modules.common.persistence.CrudDao;
import com.jiacer.modules.common.persistence.annotation.MyBatisDao;
import com.jiacer.modules.mybatis.model.CourseTimeTable;

@MyBatisDao
public interface CourseTimeTableMapper extends CrudDao<CourseTimeTable>{

	/**
	 * 
	 * @MethodName:getValueById
	 * @Type:CourseTimeTableMapper
	 * @Description:获取template_id值
	 * @Return:String
	 * @Param:@param map
	 * @Param:@return
	 * @Thrown:
	 * @Date:Oct 10, 2017 5:00:10 PM
	 */
	String getValueById(Map<Object, Object> map);
	/**
	 * 
	 * @MethodName:updateValueByMap
	 * @Type:CourseTimeTableMapper
	 * @Description:修改对应课程对应的时间状态值
	 * @Return:void
	 * @Param:@param map
	 * @Thrown:
	 * @Date:Oct 13, 2017 10:21:06 AM
	 */
	void updateValueByMap(Map<Object, Object> map);

}
