package com.jiacer.modules.business.service;

import com.jiacer.modules.business.bean.form.InstitutionQuery;
import com.jiacer.modules.common.page.Page;
import com.jiacer.modules.mybatis.entity.SchoolsEntity;
import com.jiacer.modules.mybatis.model.InstitutionInfo;

/** 
* @ClassName: schoolsService 
* @Description: 学校管理接口服务
* @author 贺章鹏
* @date 2016年10月19日 下午4:06:49 
*  
*/
public interface SchoolsService {

	/**
	 * 根据id获取学校对象
	 * @param id
	 * @return
	 */
	SchoolsEntity getSchoolsById(Integer id);

	/**
	 * 学校对象分页
	 * @param schoolsEntity
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	Page<SchoolsEntity> getSchoolsPage(SchoolsEntity schoolsEntity, int pageNumber, int pageSize);

	/**
	 * 添加学校
	 * @param schoolsEntity
	 * @throws Exception
	 */
	void addschools(SchoolsEntity schoolsEntity) throws Exception;

	/**
	 * 修改学校
	 * @param schoolsEntity
	 * @throws Exception
	 */
	void modifyschools(SchoolsEntity schoolsEntity) throws Exception;

	/**
	 * 删除学校
	 * @param schoolsEntity
	 * @throws Exception
	 */
	void delschools(SchoolsEntity schoolsEntity) throws Exception;

	/***
	 * 判断学校名称是否存在
	 * @param schoolsEntity
	 * @return
	 */
	public boolean isSchoolName(SchoolsEntity schoolsEntity);

	/**
	 * 
	 * @MethodName:getSchoolIntroduce
	 * @Type:SchoolsService
	 * @Description:获取学校简介信息
	 * @Return:SchoolsEntity
	 * @Param:@param institutionInfoId
	 * @Param:@return
	 * @Thrown:
	 * @Date:Oct 6, 2017 10:56:51 PM
	 */
	InstitutionInfo getSchoolIntroduce(Integer institutionInfoId);
	
	/**
	 * 
	 * @return 
	 * @MethodName:updateInstitution
	 * @Type:SchoolsService
	 * @Description:修改机构学校信息
	 * @Return:void
	 * @Param:@param institutionQuery
	 * @Thrown:
	 * @Date:Oct 9, 2017 2:40:42 PM
	 */
	int updateInstitution(InstitutionQuery institutionQuery);
	
	
}
