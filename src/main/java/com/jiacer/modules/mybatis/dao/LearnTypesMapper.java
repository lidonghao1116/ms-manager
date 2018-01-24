package com.jiacer.modules.mybatis.dao;

import java.util.List;
import java.util.Map;

import com.jiacer.modules.common.persistence.CrudDao;
import com.jiacer.modules.common.persistence.annotation.MyBatisDao;
import com.jiacer.modules.mybatis.entity.LearnTypesEntity;

@MyBatisDao
public interface LearnTypesMapper extends CrudDao<LearnTypesEntity>{

	/**
	 * 
	 * @MethodName:count
	 * @Type:LearnTypesMapper
	 * @Description:获取课程信息总条数
	 * @Return:Integer
	 * @Param:@param map
	 * @Param:@return
	 * @Thrown:
	 * @Date:Oct 7, 2017 12:58:33 PM
	 */
	Integer count(Map<Object, Object> map);
	
	/**
	 * 
	 * @MethodName:getPageList
	 * @Type:LearnTypesMapper
	 * @Description:获取分页信息
	 * @Return:List<LearnTypesEntity>
	 * @Param:@param map
	 * @Param:@return
	 * @Thrown:
	 * @Date:Oct 7, 2017 1:00:07 PM
	 */
	List<LearnTypesEntity> getPageList(Map<Object, Object> map);
	
	/**
	 * 
	 * @param map 
	 * @MethodName:getCourseInfo
	 * @Type:LearnTypesMapper
	 * @Description:获取learnType表中学校未开设的全部课程
	 * @Return:List<LearnTypesEntity>
	 * @Param:@return
	 * @Thrown:
	 * @Date:Oct 8, 2017 6:16:40 PM
	 */
	List<LearnTypesEntity> getCourseInfo(Map<Object, Object> map);
	
	/**
	 * 
	 * @MethodName:getcertNameById
	 * @Type:LearnTypesMapper
	 * @Description:获取课程对应的证书名称
	 * @Return:String
	 * @Param:@param courseId
	 * @Param:@return
	 * @Thrown:
	 * @Date:Oct 8, 2017 7:11:25 PM
	 */
	String getcertNameById(Integer courseId);
	
	/**
	 * 
	 * @MethodName:insertCourseInfo
	 * @Type:LearnTypesMapper
	 * @Description:新增课程
	 * @Return:void
	 * @Param:@param learnTypesEntity
	 * @Thrown:
	 * @Date:Oct 8, 2017 9:17:23 PM
	 */
	void insertCourseInfo(LearnTypesEntity learnTypesEntity);
	
	Integer typeNameCount(Map<Object, Object> map);
	
	LearnTypesEntity getById(Integer id);
	
	LearnTypesEntity getLearnTypes(Map<Object, Object> map);
	

	//public int  insertCourseTimeTable(LearnTypesEntity learnTypesEntity);
	public int  insertCourseTimeTable(Map<Object, Object> map);
	
	public void deleteCourseTimeTable(Map<Object, Object> map);
	
	public int getMaxId(Map<Object, Object> map);
	
	List<LearnTypesEntity> getCourseTimeTable(Map<Object, Object> map);
	
	List<LearnTypesEntity> findAllList(Map<Object, Object> map);
	
	List<LearnTypesEntity> getLearnTypeNameList(Map<Object, Object> map);
	/**
	 * 
	 * @MethodName:updateCourseInstitutionstatus
	 * @Type:LearnTypesMapper
	 * @Description:更改courseInstitution表中status状态
	 * @Return:void
	 * @Param:@param courseIsStart
	 * @Thrown:
	 * @Date:Oct 12, 2017 4:04:08 PM
	 */
	void updateCourseInstitutionstatus(Map<Object, Object> map);
	
	/**
	 * 
	 * @MethodName:findAllListByStatus
	 * @Type:LearnTypesMapper
	 * @Description:课程销售--获取上架课程信息
	 * @Return:List<LearnTypesEntity>
	 * @Param:@param entity
	 * @Param:@return
	 * @Thrown:
	 * @Date:Oct 14, 2017 9:39:51 AM
	 */
	List<LearnTypesEntity> findAllListByStatus(LearnTypesEntity entity);

	

	

	
	
}