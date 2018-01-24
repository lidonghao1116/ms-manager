package com.jiacer.modules.business.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import com.jiacer.modules.common.page.Page;
import com.jiacer.modules.mybatis.entity.ControlEntity;
import com.jiacer.modules.mybatis.entity.ExamClassEntity;
import com.jiacer.modules.mybatis.entity.UserScoresEntity;
import com.jiacer.modules.mybatis.entity.extend.LearnRecordEntity;

/** 
* @ClassName: ExamsService 
* @Description: 考试管理接口服务
* @author 贺章鹏
* @date 2016年10月19日 下午4:06:20 
*  
*/
public interface ExamsService {

	/**
	 * 根据id获取班级对象
	 * @param id
	 * @return
	 */
	ExamClassEntity getExamClassById(Integer id);
	
	ExamClassEntity getExamClass(Map<Object, Object> map);
	
	ExamClassEntity getById(Integer id);
	
	List<ExamClassEntity> findAllList(Map<Object, Object> map);
	
	List<ExamClassEntity> getCourseIdExamClass(Map<Object, Object> map);
	
	List<ExamClassEntity> getCourseIdExamClassDsh(Map<Object, Object> map);

	/**
	 * 班级管理对象分页
	 * @param examClassEntity
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	Page<ExamClassEntity> getExamClassPage(ExamClassEntity examClassEntity, int pageNumber, int pageSize);

	/**
	 * 新增班级
	 * @param examClassEntity
	 * @throws Exception
	 */
	void addExamClass(ExamClassEntity examClassEntity) throws Exception;

	/**
	 * 修改班级
	 * @param examClassEntity
	 * @throws Exception
	 */
	void modifyExamClass(ExamClassEntity examClassEntity) throws Exception;

	/**
	 * 删除班级
	 * @param examClassEntity
	 * @throws Exception
	 */
	void delExamClass(ExamClassEntity examClassEntity) throws Exception;

	/**
	 * 根据id获取成绩编辑实体类
	 * @param id
	 * @return
	 */
	ExamClassEntity getExamScores(Integer id);

	/**
	 * 成绩管理分页
	 * @param examClassEntity
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	Page<ExamClassEntity> getExamScoresPage(ExamClassEntity examClassEntity, int pageNumber, int pageSize);

	/**
	 * 新增或修改用户成绩
	 * @param userScoresEntity
	 * @throws Exception
	 */
	void addUserScores(UserScoresEntity userScoresEntity,Integer orderId)  throws Exception;

	/**
	 * 获取用户成绩实体
	 * @param userScoresEntity
	 * @return
	 */
	UserScoresEntity getUserScores(UserScoresEntity userScoresEntity);

	/**
	 * 获取详情信息
	 * @param id
	 * @return
	 */
	ExamClassEntity getExamClassInfo(Integer id);

	/**
	 * @param model
	 * @param id
	 * @param response
	 */
	Model dealExport(Model model, Integer id, HttpServletResponse response,HttpServletRequest request);

	/**
	 * @param model
	 * @param id
	 * @param response
	 * @param request
	 */
	Model dealExportScores(Model model, Integer id, HttpServletResponse response, HttpServletRequest request);

	/**
	 * @param id
	 * @return
	 */
	List<LearnRecordEntity> getExamLearnRecords(Integer id);

	List<ControlEntity> getClassNumber();
	
	/**
	 * @param classId
	 * @param model
	 * @param response
	 * @param request
	 * @return
	 */
	Model exportExamLearnRecords(Integer classId, Model model, HttpServletResponse response,
			HttpServletRequest request);

}
