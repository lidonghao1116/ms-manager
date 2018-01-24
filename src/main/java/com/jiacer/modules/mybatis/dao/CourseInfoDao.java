package com.jiacer.modules.mybatis.dao;

import java.util.List;
import java.util.Map;

import com.jiacer.modules.common.persistence.CrudDao;
import com.jiacer.modules.common.persistence.annotation.MyBatisDao;
import com.jiacer.modules.mybatis.entity.CourseInfoEntity;

@MyBatisDao
public interface CourseInfoDao extends CrudDao<CourseInfoEntity>{

	List<CourseInfoEntity> getAllCourseInfo(Map<Object, Object> map);
	
}
