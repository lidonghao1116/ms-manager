package com.jiacer.modules.mybatis.dao;

import java.util.List;
import java.util.Map;

import com.jiacer.modules.common.persistence.CrudDao;
import com.jiacer.modules.common.persistence.annotation.MyBatisDao;
import com.jiacer.modules.mybatis.entity.ExamClassEntity;

@MyBatisDao
public interface ExamClassMapper extends CrudDao<ExamClassEntity>{

	/**
	 * @param map
	 * @return
	 */
	Integer count(Map<Object, Object> map);
	
	ExamClassEntity getExamClass(Map<Object, Object> map);
	
	ExamClassEntity getById(Integer id);

	/**
	 * @param map
	 * @return
	 */
	List<ExamClassEntity> getPageList(Map<Object, Object> map);
	
	List<ExamClassEntity> findAllList(Map<Object, Object> map);
	
	List<ExamClassEntity> getCourseIdExamClass(Map<Object, Object> map);
	
	List<ExamClassEntity> getCourseIdExamClassDsh(Map<Object, Object> map);
	
	List<ExamClassEntity> getExam(ExamClassEntity examClassEntity);
	/**
	 * @author xiehui
	 * @param courseId
	 * @param classNumber
	 * @return查询班级标号是否是已结清或者待申报状态
	 */
	ExamClassEntity  getValidClassNumber(Integer courseId, String classNumber);
	
	
	
}