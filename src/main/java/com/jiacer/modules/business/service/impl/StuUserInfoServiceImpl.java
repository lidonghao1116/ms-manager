package com.jiacer.modules.business.service.impl;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiacer.modules.business.bean.DictionariesUtil;
import com.jiacer.modules.business.bean.operation.UsersOpt;
import com.jiacer.modules.business.service.StuUserInfoService;
import com.jiacer.modules.common.page.Page;
import com.jiacer.modules.common.service.BaseService;
import com.jiacer.modules.common.utils.DateUtils;
import com.jiacer.modules.common.utils.SqlUtils;
import com.jiacer.modules.common.utils.StringUtils;
import com.jiacer.modules.log.Log;
import com.jiacer.modules.mybatis.dao.ApplyOrdersMapper;
import com.jiacer.modules.mybatis.dao.CertAuthorityDao;
import com.jiacer.modules.mybatis.dao.CfgParamMapper;
import com.jiacer.modules.mybatis.dao.ExamClassMapper;
import com.jiacer.modules.mybatis.dao.LearnTypesMapper;
import com.jiacer.modules.mybatis.dao.PartnersMapper;
import com.jiacer.modules.mybatis.dao.StuUserInfoDao;
import com.jiacer.modules.mybatis.dao.SysUsersMapper;
import com.jiacer.modules.mybatis.dao.UserScoresMapper;
import com.jiacer.modules.mybatis.entity.ApplyOrdersEntity;
import com.jiacer.modules.mybatis.entity.CfgParamEntity;
import com.jiacer.modules.mybatis.entity.ExamClassEntity;
import com.jiacer.modules.mybatis.entity.StuUserInfoEntity;
import com.jiacer.modules.mybatis.entity.SysUsersEntity;
import com.jiacer.modules.mybatis.entity.UserBaseInfoEntity;
import com.jiacer.modules.mybatis.entity.UserExtendInfoEntity;
import com.jiacer.modules.mybatis.entity.UserScoresEntity;
import com.jiacer.modules.mybatis.model.CertAuthority;
import com.jiacer.modules.mybatis.model.UserScoresKey;
import com.jiacer.modules.system.config.DBStatus;
import com.jiacer.modules.system.config.Message;
import com.jiacer.modules.system.config.SysConstants;
import com.jiacer.modules.system.utils.UserUtils;

@Service
public class StuUserInfoServiceImpl extends BaseService implements StuUserInfoService {

	@Autowired
	StuUserInfoDao stuUserInfoDao; 
	
	@Autowired
	ApplyOrdersMapper applyOrdersDao;
	
	@Autowired
	CfgParamMapper cfgParamDao;
	
	@Autowired
	PartnersMapper partnersDao; 
	
	@Autowired
	ExamClassMapper examClassDao;
	
	@Autowired
	UserScoresMapper userScoresDao;
	
	@Autowired
	LearnTypesMapper learnTypesDao;
	
	@Autowired
	CertAuthorityDao certAuthorityDao;
	
	@Autowired
	SysUsersMapper sysUsersDao;
	
	@Override
	public int insert(StuUserInfoEntity stuUserInfoEntity) {
		// TODO Auto-generated method stub
		return stuUserInfoDao.insert(stuUserInfoEntity);
	}

	@Override
	public Page<StuUserInfoEntity> getUsersPage(StuUserInfoEntity stuUserInfoQuery, int pageNumber, int pageSize) {
		try {
            //获取总条数
			Map<Object, Object>  map = new HashMap();
			map.put("institutionInfoId", UserUtils.getUser().getInstitutionInfoId());
			
			if(stuUserInfoQuery.getUserName() != null){
				map.put("userName", SqlUtils.like(stuUserInfoQuery.getUserName()));
			}
			if(stuUserInfoQuery.getMobile() != null){
				map.put("mobile", SqlUtils.like(stuUserInfoQuery.getMobile()));
			}
            Integer totalCount = stuUserInfoDao.countStu(map);
            //分页实体
            Page<StuUserInfoEntity> page=new Page<StuUserInfoEntity>();
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
            	List<StuUserInfoEntity> list=stuUserInfoDao.getPageList(map);
            	ApplyOrdersEntity entity=null;
            	for(StuUserInfoEntity stuUserBean:list){
            		entity=new ApplyOrdersEntity();
            		entity.setStuUserInfoId(stuUserBean.getId());
            		entity.setInstitutionInfoId(UserUtils.getUser().getInstitutionInfoId());
        			entity.setHandleStatus(DBStatus.HandleStatus.SUCCESS);
        			entity.setFormType("01");
        			Integer count=applyOrdersDao.findAllCount(entity);
        			stuUserBean.setCouresCount(count);
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
	public StuUserInfoEntity getCphoneStuUserInfo(Map<Object, Object> map) {
		// TODO Auto-generated method stub
		return stuUserInfoDao.getCphoneStuUserInfo(map);
	}

	@Override
	public Integer count(Map<Object, Object> map) {
		return null;
	}

	@Override
	public List<StuUserInfoEntity> getPageList(Map<Object, Object> map) {
		return null;
	}
	

	@Override
	public StuUserInfoEntity getStuUserInfo(Integer id) {
		
		SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd"); 
		
		StuUserInfoEntity stuUserInfoEntity = stuUserInfoDao.getById(id);
		try {
			ApplyOrdersEntity entity=new ApplyOrdersEntity();
			entity.setStuUserInfoId(id);
			entity.setInstitutionInfoId(UserUtils.getUser().getInstitutionInfoId());
			entity.setHandleStatus(DBStatus.HandleStatus.SUCCESS);
			List<ApplyOrdersEntity> list=applyOrdersDao.findAllList(entity);
			if(list!=null && list.size()>0){
				ExamClassEntity examClass=null;
				UserScoresEntity userScores=null;
				UserScoresKey userScoresKey=null;
				for(ApplyOrdersEntity order:list){
					examClass=examClassDao.getById(order.getClassId());
					if(examClass!=null){
						order.setExamClass(examClass);
					}
					userScoresKey=new UserScoresKey();
					userScoresKey.setClassId(order.getClassId());
					userScoresKey.setStuUserInfoId(order.getStuUserInfoId());
					userScores=userScoresDao.getByKey(userScoresKey);
					if(userScores!=null){
						order.setUserScores(userScores);
						CertAuthority certAuthority = certAuthorityDao.getById(Integer.parseInt(userScores.getCertAuthorityId()));
						userScores.setCertAuthorityName(certAuthority.getAuthorityName());
						if(userScores.getIssuingDate() != null){
							userScores.setIssuingDateTxt(formatter.format(userScores.getIssuingDate()));	
						}
					}
					order.setAuthenticateGrade(DictionariesUtil.getAuthenticateGradeName(learnTypesDao.getById(order.getCourseId()).getAuthenticateGrade()));
					order.setTypeName(learnTypesDao.getById(order.getCourseId()).getTypeName());
					Integer stagingClearAccount = order.getStagingClearAccount();
					if(stagingClearAccount != null){
						SysUsersEntity usersEntity = sysUsersDao.getById(stagingClearAccount);
						if(usersEntity != null && usersEntity.getLoginAccount() != null){
							order.setStagingClearName(usersEntity.getLoginName());
						}
					}
					Integer depositClearAccount = order.getDepositClearAccount();
					if(depositClearAccount != null){
						SysUsersEntity usersEntity = sysUsersDao.getById(depositClearAccount);
						if(usersEntity != null && usersEntity.getLoginAccount() != null){
							order.setDepositClearName(usersEntity.getLoginName());
						}
					}
					
				}
				stuUserInfoEntity.setOrderList(list);
			}
		} catch (Exception e) {
			Log.error("获取用户详细失败");
			e.printStackTrace();
		}
		
		return stuUserInfoEntity;
	}

	@Override
	public StuUserInfoEntity getById(Integer id) {
		return stuUserInfoDao.getById(id);
	}

}













