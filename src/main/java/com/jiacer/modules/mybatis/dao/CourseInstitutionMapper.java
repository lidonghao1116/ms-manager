package com.jiacer.modules.mybatis.dao;

import java.util.List;
import java.util.Map;

import com.jiacer.modules.common.persistence.CrudDao;
import com.jiacer.modules.common.persistence.annotation.MyBatisDao;
import com.jiacer.modules.mybatis.model.CourseInstitution;

@MyBatisDao
public interface CourseInstitutionMapper extends CrudDao<CourseInstitution>{
	
	/**
	 * 
	 * @MethodName:getInfoById
	 * @Type:CourseInstitutionMapper
	 * @Description:通过ID获取courseInstitution表中信息
	 * @Return:List<CourseInstitution>
	 * @Param:@param map
	 * @Param:@return
	 * @Thrown:
	 * @Date:Oct 13, 2017 2:34:24 PM
	 */
	List<CourseInstitution> getInfoById(Map<Object, Object> map);

	
}
