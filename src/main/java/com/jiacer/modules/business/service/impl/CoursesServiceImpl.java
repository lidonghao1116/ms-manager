package com.jiacer.modules.business.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiacer.modules.business.bean.DictionariesUtil;
import com.jiacer.modules.business.bean.operation.LearnTypesOpt;
import com.jiacer.modules.business.service.CoursesService;
import com.jiacer.modules.common.constants.Constants;
import com.jiacer.modules.common.page.Page;
import com.jiacer.modules.common.service.BaseService;
import com.jiacer.modules.common.service.ServiceException;
import com.jiacer.modules.log.Log;
import com.jiacer.modules.mybatis.dao.CertAuthorityDao;
import com.jiacer.modules.mybatis.dao.CfgParamMapper;
import com.jiacer.modules.mybatis.dao.CourseInstitutionMapper;
import com.jiacer.modules.mybatis.dao.CoursePackageMapper;
import com.jiacer.modules.mybatis.dao.CourseTimeTableMapper;
import com.jiacer.modules.mybatis.dao.CoursesBaseInfoMapper;
import com.jiacer.modules.mybatis.dao.CoursesTimeDao;
import com.jiacer.modules.mybatis.dao.InstitutionInfoMapper;
import com.jiacer.modules.mybatis.dao.LearnTypesMapper;
import com.jiacer.modules.mybatis.entity.ControlEntity;
import com.jiacer.modules.mybatis.entity.CourseBaseInfoEntity;
import com.jiacer.modules.mybatis.entity.CoursesTimeEntity;
import com.jiacer.modules.mybatis.entity.LearnTypesEntity;
import com.jiacer.modules.mybatis.entity.SysUsersEntity;
import com.jiacer.modules.mybatis.model.CertAuthority;
import com.jiacer.modules.mybatis.model.CourseInstitution;
import com.jiacer.modules.mybatis.model.InstitutionInfo;
import com.jiacer.modules.system.config.Message;
import com.jiacer.modules.system.utils.UserUtils;

/** 
* @ClassName: CoursesServiceImpl 
* @author 贺章鹏
* @date 2016年10月19日 下午4:19:10 
*  
*/
@Service
public class CoursesServiceImpl extends BaseService implements CoursesService {
	
	@Autowired
	LearnTypesMapper learnTypesDao;
	
	@Autowired
	CfgParamMapper cfgParamMapper;

	@Autowired
	CertAuthorityDao CertAuthorityDao;
	
	@Autowired
	CoursesTimeDao coursesTimeDao;
	
	@Autowired
	CoursesBaseInfoMapper coursesBaseInfoMapper;
	
	@Autowired
	CourseTimeTableMapper couresTimeTableMapper;
	
	@Autowired
	CourseInstitutionMapper courseInstitutionMapper;
	
	@Autowired
	CoursePackageMapper coursePackageDao;
	
	@Override
	public LearnTypesEntity getCoursesById(Integer id) {
		try {
			
			LearnTypesEntity lEntity = learnTypesDao.getById(id);
			return lEntity;
		} catch (Exception e) {
			Log.error(Message.buildErrInfo(Message.QUERY_ERROR_EXCEPTION, e));
		}
		return null;
	}

	@Override
	public Page<LearnTypesEntity> getCoursesPage(LearnTypesEntity learnTypesEntity, int pageNumber, int pageSize) {
		try {
            //获取总条数
			//Map<Object, Object>  map=LearnTypesOpt.buildMap(learnTypesEntity);
			Map<Object, Object> map = new HashMap<>();
			SysUsersEntity sysUser = UserUtils.getUser();//获取登录用户信息
			map.put("institutionInfoId", sysUser.getInstitutionInfoId());
			if (learnTypesEntity.getTypeName()!=null
					&&!"---请选择---".equals(learnTypesEntity.getTypeName())) {
				map.put("typeName",learnTypesEntity.getTypeName());
			}
			if (learnTypesEntity.getAuthenticateGrade() !=null
					&&!"".equals(learnTypesEntity.getAuthenticateGrade())) {
				map.put("authenticateGrade", learnTypesEntity.getAuthenticateGrade());
			}
			if (learnTypesEntity.getStatus() !=null
					&&!"".equals(learnTypesEntity.getStatus())) {
				map.put("status", learnTypesEntity.getStatus());
			}
            Integer totalCount=learnTypesDao.count(map);
            //分页实体
            Page<LearnTypesEntity> page=new Page<LearnTypesEntity>();
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
            	List<LearnTypesEntity> list=learnTypesDao.getPageList(map);
            	for(int i = 0; i < list.size(); i++){
            		LearnTypesEntity learnTypes = list.get(i);
            		if(learnTypes.getAuthenticateGrade() != null){
            			learnTypes.setAuthenticateGrade(DictionariesUtil.getAuthenticateGradeName(learnTypes.getAuthenticateGrade()));	
            		}
            		if (learnTypes.getExamType() !=null) {
						learnTypes.setExamTypeName(DictionariesUtil.getExamTypeName(learnTypes.getExamType()));
					}
            		if (learnTypes.getStatus() !=null&&"01".equals(learnTypes.getStatus())) {
						learnTypes.setStatusName(Constants.COURSE_STATUS_NORMAL);
					}
            	}
                page.setRows(list);
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
	public void addCourses(LearnTypesEntity learnTypesEntity) {
			int maxId = learnTypesDao.getMaxId(null)+1;//learnType表对应的新增course信息
			CourseBaseInfoEntity cEntity = coursesBaseInfoMapper.getBaseCourseInfoById(learnTypesEntity.getId());
			SysUsersEntity sysUser = UserUtils.getUser();//获取登录用户信息
			learnTypesEntity.setId(maxId);
			learnTypesEntity.setTypeName(cEntity.getCourseName());
			learnTypesEntity.setAuthenticateGrade(cEntity.getAuthenticateGrade());
			learnTypesEntity.setTotalHours(cEntity.getTotalHours());
			learnTypesEntity.setExamType(cEntity.getExamType());
			learnTypesEntity.setAuthorityId(cEntity.getAuthorityId());
			learnTypesEntity.setInstitutionInfoId(sysUser.getInstitutionInfoId());
			learnTypesEntity.setAddAccount(sysUser.getLoginAccount());
			learnTypesEntity.setAddTime(new Date());
			learnTypesEntity.setIsUsable("1");
			learnTypesEntity.setCourseBaseInfoId(cEntity.getCourseId());
			learnTypesEntity.setCertName(cEntity.getCertName());
			learnTypesDao.insertCourseInfo(learnTypesEntity);
			
			String lteStr = learnTypesEntity.getTimeTemplate();
			Map<Object, Object> map = new HashMap<Object, Object>();
			map.put("id", maxId);
			map.put("templateId", lteStr);
			String templateId=couresTimeTableMapper.getValueById(map);
			if (templateId !=null) {
			 couresTimeTableMapper.updateValueByMap(map);
			}
			learnTypesDao.insertCourseTimeTable(map);
			
			map.put("status", Constants.COURSE_IS_START);
			map.put("courseId", cEntity.getCourseId());
			map.put("institutionId", sysUser.getInstitutionInfoId());
			learnTypesDao.updateCourseInstitutionstatus(map);
	}

	@Override
	public void modifyCourses(LearnTypesEntity learnTypesEntity) {
		
		LearnTypesEntity bean=this.getCoursesById(learnTypesEntity.getId());
		if(bean==null){
			throw new ServiceException(Message.UPDATE_DATE_NOEXIST);
		}
		learnTypesDao.update(learnTypesEntity);
		String lteStr = learnTypesEntity.getTimeTemplate();
		Map<Object, Object> mapTrem = new HashMap<Object, Object>();
		mapTrem.put("id", learnTypesEntity.getId());
		learnTypesDao.deleteCourseTimeTable(mapTrem);
		
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("id", learnTypesEntity.getId());
		map.put("templateId",lteStr );
	    learnTypesDao.insertCourseTimeTable(map);

	    //判断下架的课程，将产品下架
	    if ("02".equals(learnTypesEntity.getStatus())) {
	    	map.put("institution", bean.getInstitutionInfoId());
	    	map.put("courseId", String.valueOf(learnTypesEntity.getId()));
			coursePackageDao.updateStatusByCourseStatsMap(map);
		}

	}
	
	
	@Override
	public void delCourses(LearnTypesEntity learnTypesEntity) throws Exception {
		LearnTypesEntity bean=this.getCoursesById(learnTypesEntity.getId());
		if(bean==null){
			throw new ServiceException(Message.UPDATE_DATE_NOEXIST);
		}else{
			learnTypesDao.update(LearnTypesOpt.buildDelete(bean));
		}
	}

	@Override
	public List<ControlEntity> getCourseIdName() {
		// TODO Auto-generated method stub
		List<ControlEntity> cList = new ArrayList<ControlEntity>();
		LearnTypesEntity learnTypes = new LearnTypesEntity();
		learnTypes.setInstitutionInfoId(UserUtils.getUser().getInstitutionInfoId());
		List<LearnTypesEntity> list = learnTypesDao.findAllList(learnTypes);
		for(int i = 0; i < list.size(); i++){
			LearnTypesEntity learnTypesEntity = list.get(i);
			cList.add(new ControlEntity(learnTypesEntity.getId().toString(), learnTypesEntity.getTypeName()));
		}
		return cList;
	}

	@Override
	public boolean isCoursesName(LearnTypesEntity learnTypesEntity) {
		// TODO Auto-generated method stub
		Map<Object, Object>  map=LearnTypesOpt.buildMap(learnTypesEntity);
        if(learnTypesDao.typeNameCount(map) == 0){
        	return true;
        }else{
        	return false;
        }
	}

	@Override
	public LearnTypesEntity getById(Integer id) {
		// TODO Auto-generated method stub
		return learnTypesDao.getById(id);
	}

	@Override
	public LearnTypesEntity getLearnTypes(Map<Object, Object> map) {
		return learnTypesDao.getLearnTypes(map);
	}

	@Override
	public Integer count(Map<Object, Object> map) {
		// TODO Auto-generated method stub
		return learnTypesDao.count(map);
	}

	@Override
	public List<CoursesTimeEntity> getCourseTimeTable(Map<Object, Object> map) {
		
		List<CoursesTimeEntity> list = new ArrayList<>();
		
		String timeTables=couresTimeTableMapper.getValueById(map);
		String [] strArr= timeTables.split(","); 
		for(int i = 0; i < strArr.length; i++){
			CoursesTimeEntity cEntity = new CoursesTimeEntity();
			map.put("templateId", Integer.parseInt(strArr[i].toString()));
			cEntity = coursesTimeDao.getTimeInfoByMap(map);
			list.add(cEntity);
		}
		return list;
		
	}

	@Override
	public List<LearnTypesEntity> findAllList(Map<Object, Object> map) {
		// TODO Auto-generated method stub
		return learnTypesDao.findAllList();
	}

	@Override
	public List<LearnTypesEntity> getLearnTypeNameList(Map<Object, Object> map) {
		// TODO Auto-generated method stub
		return learnTypesDao.getLearnTypeNameList(map);
	}
	
	/**
	 * 获取learnType表中学校未开设的全部课程
	 */
	@Override
	public List<CourseBaseInfoEntity> getBaseCourseInfo(Map<Object, Object> map) {
		
		return coursesBaseInfoMapper.getcourseInfoByMap(map);
	}

	/**
	 * 单条课程信息
	 */
	@Override
	public LearnTypesEntity getCourseInfoById(Integer id) {
		LearnTypesEntity lEntity = new LearnTypesEntity();
		Map<Object, Object> map = new HashMap<Object,Object>();
		
		lEntity = learnTypesDao.getById(id);
		if (lEntity ==null) {
			return null;
		}
		
		if (lEntity.getAuthenticateGrade() !=null) {
			map.put("value", Constants.AUTHORITY_GRADE);
			map.put("status",lEntity.getAuthenticateGrade());
			String grade = cfgParamMapper.getTextByValue(map);
			lEntity.setAuthenticateGrade(grade);
		}
		if (lEntity.getExamType() !=null) {
			map.put("value", Constants.EXAM_TYPE);
			map.put("status",lEntity.getExamType());
			String examTypeName = cfgParamMapper.getTextByValue(map);
			lEntity.setExamTypeName(examTypeName);
		}
		if (lEntity.getAuthorityId() !=null) {
			CertAuthority certAuthority =CertAuthorityDao.getById(lEntity.getAuthorityId());
			lEntity.setAuthorityName(certAuthority.getAuthorityName());
		}
		return lEntity;
	}

	/**
	 * 获取总控课程状态信息
	 */
	@Override
	public String getBaseInfoStatus(LearnTypesEntity learnTypesEntity) {
		LearnTypesEntity bean=this.getCoursesById(learnTypesEntity.getId());
		if(bean==null){
			throw new ServiceException(Message.UPDATE_DATE_NOEXIST);
		}
		String status= coursesBaseInfoMapper.getstatusById(bean.getCourseBaseInfoId());
		return status;
	}
	
	/**
	 * 获取总控中单挑课程信息
	 */
	@Override
	public CourseBaseInfoEntity getBaseCourseInfoById(Integer courseId) {
		
		CourseBaseInfoEntity cEntity = coursesBaseInfoMapper.getBaseCourseInfoById(courseId);
		if (cEntity == null) {
			throw new ServiceException("改课程ID对应信息为空");
		}
		cEntity.setExamType(DictionariesUtil.getExamTypeName(cEntity.getExamType()));
		cEntity.setAuthenticateGrade(DictionariesUtil.getAuthenticateGradeName(cEntity.getAuthenticateGrade()));
		cEntity.setAuthorityName(CertAuthorityDao.getById(cEntity.getAuthorityId()).getAuthorityName());
		return cEntity;
	}

}
