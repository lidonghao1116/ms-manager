package com.jiacer.modules.business.utils;

import java.util.List;

import com.google.common.collect.Lists;
import com.jiacer.modules.common.utils.SpringContextHolder;
import com.jiacer.modules.log.Log;
import com.jiacer.modules.mybatis.dao.ExamClassMapper;
import com.jiacer.modules.mybatis.entity.ExamClassEntity;
import com.jiacer.modules.system.config.DBStatus;

/** 
* @ClassName: ExamsUtils 
* @Description: TODO
* @author 贺章鹏
* @date 2016年11月6日 下午2:41:17 
*  
*/
public class ExamsUtils {
	private static ExamClassMapper examClassDao = SpringContextHolder.getBean(ExamClassMapper.class);
	/**
	 * 获取班级课程
	 */
	public static List<ExamClassEntity> init(Integer courseId){
		ExamClassEntity entity=new ExamClassEntity();
		entity.setIsUsable(DBStatus.IsUsable.TRUE);
		entity.setExamStatus(DBStatus.ExamStatus.PENDING_DECLARE);
		entity.setCourseId(courseId);
		List<ExamClassEntity> list=Lists.newArrayList();
		try {
			list=examClassDao.findAllList(entity);
		} catch (Exception e) {
			Log.error("获取班级列表失败");
		}
		return list;
	}
	
	public static String getClassNumber(Integer id){
		try {
			ExamClassEntity entity=examClassDao.getById(id);
			return entity.getClassNumber();
		} catch (Exception e) {
			Log.error("获取班级失败");
		}
		return "";
	}
	
	public static ExamClassEntity getClassObjcet(Integer id){
		try {
			ExamClassEntity entity=examClassDao.getById(id);
			return entity;
		} catch (Exception e) {
			Log.error("获取班级失败");
		}
		return new ExamClassEntity();
	}
}
