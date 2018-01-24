package com.jiacer.modules.business.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.jiacer.modules.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiacer.modules.business.bean.form.InstitutionQuery;
import com.jiacer.modules.business.bean.operation.SchoolsOpt;
import com.jiacer.modules.business.service.SchoolsService;
import com.jiacer.modules.business.utils.SchoolsUtils;
import com.jiacer.modules.common.page.Page;
import com.jiacer.modules.common.service.BaseService;
import com.jiacer.modules.common.service.ServiceException;
import com.jiacer.modules.log.Log;
import com.jiacer.modules.mybatis.dao.InstitutionInfoMapper;
import com.jiacer.modules.mybatis.dao.LearnTypesMapper;
import com.jiacer.modules.mybatis.dao.SchoolsMapper;
import com.jiacer.modules.mybatis.entity.LearnTypesEntity;
import com.jiacer.modules.mybatis.entity.SchoolsEntity;
import com.jiacer.modules.mybatis.entity.SysUsersEntity;
import com.jiacer.modules.mybatis.model.InstitutionInfo;
import com.jiacer.modules.system.config.Message;
import com.jiacer.modules.system.utils.AreasUtils;
import com.jiacer.modules.system.utils.UserUtils;

/** 
* @ClassName: schoolsServiceImpl 
* @Description: TODO
* @author 贺章鹏
* @date 2016年10月19日 下午4:20:25 
*  
*/
@Service
public class SchoolsServiceImpl extends BaseService implements SchoolsService {
	
	@Autowired
	SchoolsMapper schoolsDao;

	@Autowired
	LearnTypesMapper LearnTypesDao;
	
	@Autowired
	InstitutionInfoMapper institutionInfoMapper;
	
	@Override
	public SchoolsEntity getSchoolsById(Integer id) {
		try {
			return schoolsDao.getById(id);
		} catch (Exception e) {
			Log.error(Message.buildErrInfo(Message.QUERY_ERROR_EXCEPTION, e));
		}
		return null;
	}

	@Override
	public Page<SchoolsEntity> getSchoolsPage(SchoolsEntity schoolsEntity, int pageNumber, int pageSize) {
		try {
            //获取总条数
			Map<Object, Object>  map=SchoolsOpt.buildMap(schoolsEntity);
			
            Integer totalCount=schoolsDao.count(map);
            //分页实体
            Page<SchoolsEntity> page=new Page<SchoolsEntity>();
            page.setPage(pageNumber);
            page.setRowNum(pageSize);
            if(totalCount==null){
                return page;
            }
            //最大页数判断
            int pageM = maxPage(totalCount, page.getRowNum(), page.getPage());
            if (pageM > 0) {
                page.setPage(pageM);
            }
            if (totalCount > 0) {
            	map.put("offset",page.getOffset());
            	map.put("pageSize",page.getRowNum());
            	List<SchoolsEntity> list=schoolsDao.getPageList(map);
            	List<SchoolsEntity> listNew = new ArrayList<SchoolsEntity>();
            	for(int i = 0; i < list.size(); i++){
            		String lName = "";
            		SchoolsEntity schoolsEntityOld = list.get(i);
            		String[] idStr = schoolsEntityOld.getApplyCourses().split(",");
            		for(int c = 0; c < idStr.length; c++){
            			if(StringUtils.isBlank(idStr[c])){
							continue;
						}
            			int id = Integer.parseInt(idStr[c]);
            			LearnTypesEntity learnTypesEntity = LearnTypesDao.getById(id);
            			if(learnTypesEntity != null){
							lName += learnTypesEntity.getTypeName()+" ";
						}
            		}
            		schoolsEntityOld.setApplyCoursesName(lName);
            		listNew.add(schoolsEntityOld);
            	}
                //page.setRows(list);
            	page.setRows(listNew);
                page.setRecords(totalCount.longValue());
            }
            return page;
        } catch (Exception e) {
        	e.printStackTrace();
        	Log.error(String.format(Message.QUERY_ERROR_EXCEPTION, e));
            return null;
        }
	}

	@Override
	public void addschools(SchoolsEntity schoolsEntity) throws Exception {
		schoolsDao.insert(SchoolsOpt.buildAdd(schoolsEntity));
		SchoolsUtils.flush();
	}

	@Override
	public void modifyschools(SchoolsEntity schoolsEntity) throws Exception {
		SchoolsEntity bean=this.getSchoolsById(schoolsEntity.getId());
		if(bean==null){
			throw new ServiceException(Message.UPDATE_DATE_NOEXIST);
		}else{
			schoolsDao.update(SchoolsOpt.buildUpdate(bean, schoolsEntity));
		}
		SchoolsUtils.flush();
	}

	@Override
	public void delschools(SchoolsEntity schoolsEntity) throws Exception {
		SchoolsEntity bean=this.getSchoolsById(schoolsEntity.getId());
		if(bean==null){
			throw new ServiceException(Message.UPDATE_DATE_NOEXIST);
		}else{
			schoolsDao.update(SchoolsOpt.buildDelete(bean));
		}
	}

	@Override
	public boolean isSchoolName(SchoolsEntity schoolsEntity) {
		Map<Object, Object>  map=SchoolsOpt.buildMap(schoolsEntity);
		System.out.println(schoolsDao.count(map));
        if(schoolsDao.count(map) == 0){
        	return true;
        }else{
        	return false;
        }
	}

	/**
	 * 运营管理--销售管理--学校简介
	 */
	@Override
	public InstitutionInfo getSchoolIntroduce(Integer institutionInfoId) {
		InstitutionInfo info =institutionInfoMapper.getInstitutionInfoById(institutionInfoId);
		if (info==null) {
			throw new ServiceException(Message.QUERY_ERROR_EXCEPTION);
		}
		return info;
	}

	/**
	 * 修改机构学校信息
	 */
	@Override
	public int updateInstitution(InstitutionQuery institutionQuery) {
		SysUsersEntity sysUser = UserUtils.getUser();//获取登录用户信息
		institutionQuery.setModifyAccount(sysUser.getLoginAccount());
		institutionQuery.setModifyTime(new Date());
		int result = institutionInfoMapper.updateInstitution(institutionQuery);
		if (result <=0) {
			throw new ServiceException(Message.QUERY_ERROR_EXCEPTION);
		}
		return 1;
	}

	

	
}
