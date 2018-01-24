package com.jiacer.modules.mybatis.dao;

import java.util.Map;

import com.jiacer.modules.common.persistence.CrudDao;
import com.jiacer.modules.common.persistence.annotation.MyBatisDao;
import com.jiacer.modules.mybatis.entity.CourseInfoBuyRecordEntity;

@MyBatisDao
public interface CourseInfoBuyRecordDao extends CrudDao<CourseInfoBuyRecordEntity>{

	Integer count(Map<String, Integer> map);
	
}
