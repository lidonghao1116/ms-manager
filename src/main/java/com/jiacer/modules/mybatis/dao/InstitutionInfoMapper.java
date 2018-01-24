package com.jiacer.modules.mybatis.dao;

import com.jiacer.modules.business.bean.form.InstitutionQuery;
import com.jiacer.modules.common.persistence.CrudDao;
import com.jiacer.modules.common.persistence.annotation.MyBatisDao;
import com.jiacer.modules.mybatis.model.InstitutionInfo;

@MyBatisDao
public interface InstitutionInfoMapper extends CrudDao<InstitutionInfo>  {

	/**
	 * 
	 * @MethodName:getInstitutionInfoById
	 * @Type:InstitutionInfoMapper
	 * @Description:获取机构学校信息
	 * @Return:InstitutionInfo
	 * @Param:@param institutionInfoId
	 * @Param:@return
	 * @Thrown:
	 * @Date:Oct 9, 2017 2:03:41 PM
	 */
	InstitutionInfo getInstitutionInfoById(Integer institutionInfoId);
	
	/**
	 * 
	 * @MethodName:updateInstitution
	 * @Type:InstitutionInfoMapper
	 * @Description:修改机构学校简介
	 * @Return:int
	 * @Param:@param institutionQuery
	 * @Param:@return
	 * @Thrown:
	 * @Date:Oct 9, 2017 2:42:55 PM
	 */
	int updateInstitution(InstitutionQuery institutionQuery);






}