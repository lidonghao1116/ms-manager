package com.jiacer.modules.business.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiacer.modules.business.service.CourseInfoService;
import com.jiacer.modules.common.service.BaseService;
import com.jiacer.modules.mybatis.dao.CourseInfoDao;
import com.jiacer.modules.mybatis.entity.CourseInfoEntity;

@Service
public class CourseInfoServiceImpl extends BaseService implements CourseInfoService {

	@Autowired
	CourseInfoDao courseInfoDao;
	
	@Override
	public List<CourseInfoEntity> getAllCourseInfo(Map<Object, Object> map) {
		// TODO Auto-generated method stub
		return courseInfoDao.getAllCourseInfo(map);
	}
	

}
