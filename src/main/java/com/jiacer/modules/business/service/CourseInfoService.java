package com.jiacer.modules.business.service;

import java.util.List;
import java.util.Map;

import com.jiacer.modules.mybatis.entity.CourseInfoEntity;

public interface CourseInfoService {

	List<CourseInfoEntity> getAllCourseInfo(Map<Object, Object> map);

}
