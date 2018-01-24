package com.jiacer.modules.business.service.impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiacer.modules.business.bean.operation.CoursesTimeOpt;
import com.jiacer.modules.business.bean.operation.LearnTypesOpt;
import com.jiacer.modules.business.bean.operation.SchoolsOpt;
import com.jiacer.modules.business.service.CoursesService;
import com.jiacer.modules.business.service.CoursesTimeService;
import com.jiacer.modules.business.utils.CoursesTimeUtils;
import com.jiacer.modules.business.utils.CoursesUtils;
import com.jiacer.modules.business.utils.SchoolsUtils;
import com.jiacer.modules.common.page.Page;
import com.jiacer.modules.common.service.BaseService;
import com.jiacer.modules.common.service.ServiceException;
import com.jiacer.modules.log.Log;
import com.jiacer.modules.mybatis.dao.CoursesTimeDao;
import com.jiacer.modules.mybatis.dao.LearnTypesMapper;
import com.jiacer.modules.mybatis.entity.CoursesTimeEntity;
import com.jiacer.modules.mybatis.entity.LearnTypesEntity;
import com.jiacer.modules.mybatis.entity.SchoolsEntity;
import com.jiacer.modules.mybatis.entity.SysUsersEntity;
import com.jiacer.modules.system.config.Message;
import com.jiacer.modules.system.utils.UserUtils;

/** 
* @ClassName: CoursesServiceImpl 
* @Description: TODO
* @author 贺章鹏
* @date 2016年10月19日 下午4:19:10 
*  
*/
@Service
public class CoursesTimeServiceImpl extends BaseService implements CoursesTimeService {
	
	@Autowired
	CoursesTimeDao coursesTimeDao;

	@Override
	public CoursesTimeEntity getCoursesTimeById(Integer id) {
		try {
			return coursesTimeDao.getById(id);
		} catch (Exception e) {
			Log.error(Message.buildErrInfo(Message.QUERY_ERROR_EXCEPTION, e));
		}
		return null;
	}

	@Override
	public Page<CoursesTimeEntity> getCoursesPage(CoursesTimeEntity coursesTimeEntity, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		
		try {
            //获取总条数
			Map<Object, Object>  map=CoursesTimeOpt.buildMap(coursesTimeEntity);
			SysUsersEntity sysUser = UserUtils.getUser();//获取登录用户信息
			map.put("institutionInfoId", sysUser.getInstitutionInfoId());
            Integer totalCount=coursesTimeDao.count(map);
            //分页实体
            Page<CoursesTimeEntity> page=new Page<CoursesTimeEntity>();
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
            	List<CoursesTimeEntity> list=coursesTimeDao.getPageList(map);
            	for(int i=0; i < list.size(); i++){
            		CoursesTimeEntity cte = list.get(i);
            		if(cte.getTemplateType().equals("0")){
            			cte.setTemplateType("否");
            		}else{
            			cte.setTemplateType("是");
            		}
            		String[] str= cte.getOpenCycle().split(","); 
            		StringBuffer sb = new StringBuffer();
            		for(int j = 0; j < str.length; j++){
            			if(str[j].toString().equals("monday")){
            				sb.append("周一");
            			}
            			if(str[j].toString().equals("tuesday")){
            				sb.append("周二");
            			}
            			if(str[j].toString().equals("wednesday")){
            				sb.append("周三");
            			}
            			if(str[j].toString().equals("thursday")){
            				sb.append("周四");
            			}
            			if(str[j].toString().equals("friday")){
            				sb.append("周五");
            			}
            			if(str[j].toString().equals("saturday")){
            				sb.append("周六");
            			}
            			if(str[j].toString().equals("sunday")){
            				sb.append("周日");
            			}
            			if(j!=str.length-1){
            				sb.append(",");	
            			}
            		}
            		cte.setOpenCycle(sb.toString());
            		cte.setBeginTime(cte.getBeginTime()+"-"+cte.getEndTime());
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
	public Integer countNum() {
		// TODO Auto-generated method stub
		if(coursesTimeDao.countNum() != null){
			return coursesTimeDao.countNum()+1;	
		}
		return 1;
	}

	@Override
	public void addCourses(CoursesTimeEntity coursesTimeEntity) throws Exception {
		// TODO Auto-generated method stub
		coursesTimeDao.insert(coursesTimeEntity);
		CoursesTimeUtils.flush();
		
	}

	@Override
	public void modifyCourses(CoursesTimeEntity coursesTimeEntity) throws Exception {
		// TODO Auto-generated method stub
		CoursesTimeEntity bean=this.getCoursesTimeById(coursesTimeEntity.getId());
		if(bean==null){
			throw new ServiceException(Message.UPDATE_DATE_NOEXIST);
		}else{
			coursesTimeDao.update(CoursesTimeOpt.buildUpdate(bean, coursesTimeEntity));
		}
		SchoolsUtils.flush();
		
	}

	@Override
	public void delCourses(CoursesTimeEntity coursesTimeEntity) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CoursesTimeEntity> getAllTimeYes() {
		// TODO Auto-generated method stub
		return coursesTimeDao.getAllTimeYes();
	}

	@Override
	public boolean isTemplateName(CoursesTimeEntity coursesTimeEntity) {
		// TODO Auto-generated method stub
		Map<Object, Object>  map=CoursesTimeOpt.buildMap(coursesTimeEntity);
        if(coursesTimeDao.count(map) == 0){
        	return true;
        }else{
        	return false;
        }
		
	}

	@Override
	public CoursesTimeEntity getById(Integer id) {
		// TODO Auto-generated method stub
		return coursesTimeDao.getById(id);
	}

	@Override
	public List<CoursesTimeEntity> getTemplate(Map<Object, Object> map) {
		// TODO Auto-generated method stub
		return coursesTimeDao.getTemplate(map);
	}


}
