package com.jiacer.modules.business.service.impl;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jiacer.modules.mybatis.model.UserBaseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.jiacer.modules.business.bean.IdcardInfoExtractor;
import com.jiacer.modules.business.bean.UserBean;
import com.jiacer.modules.business.bean.form.UsersQuery;
import com.jiacer.modules.business.bean.operation.PartnersOpt;
import com.jiacer.modules.business.bean.operation.UsersOpt;
import com.jiacer.modules.business.service.UsersService;
import com.jiacer.modules.common.page.Page;
import com.jiacer.modules.common.service.BaseService;
import com.jiacer.modules.common.service.ServiceException;
import com.jiacer.modules.common.utils.DateUtils;
import com.jiacer.modules.common.utils.ExcelOutput;
import com.jiacer.modules.common.utils.StringUtils;
import com.jiacer.modules.log.Log;
import com.jiacer.modules.mybatis.dao.ApplyOrdersMapper;
import com.jiacer.modules.mybatis.dao.CfgParamMapper;
import com.jiacer.modules.mybatis.dao.ExamClassMapper;
import com.jiacer.modules.mybatis.dao.PartnersMapper;
import com.jiacer.modules.mybatis.dao.StuUserInfoDao;
import com.jiacer.modules.mybatis.dao.UserBaseInfoMapper;
import com.jiacer.modules.mybatis.dao.UserExtendInfoMapper;
import com.jiacer.modules.mybatis.dao.UserScoresMapper;
import com.jiacer.modules.mybatis.entity.ApplyOrdersEntity;
import com.jiacer.modules.mybatis.entity.CfgParamEntity;
import com.jiacer.modules.mybatis.entity.ExamClassEntity;
import com.jiacer.modules.mybatis.entity.PartnersEntity;
import com.jiacer.modules.mybatis.entity.StuUserInfoEntity;
import com.jiacer.modules.mybatis.entity.UserBaseInfoEntity;
import com.jiacer.modules.mybatis.entity.UserExtendInfoEntity;
import com.jiacer.modules.mybatis.entity.UserScoresEntity;
import com.jiacer.modules.mybatis.model.UserScoresKey;
import com.jiacer.modules.system.config.DBStatus;
import com.jiacer.modules.system.config.Message;
import com.jiacer.modules.system.utils.UserUtils;

/** 
* @ClassName: UsersServiceImpl 
* @Description: TODO
* @author 贺章鹏
* @date 2016年10月19日 下午4:20:37 
*  
*/
@Service
public class UsersServiceImpl extends BaseService implements UsersService {
	
	@Autowired
	UserBaseInfoMapper userBaseInfoDao;
	
	@Autowired
	UserExtendInfoMapper userExtendInfoDao;
	
	@Autowired
	ApplyOrdersMapper applyOrdersDao;
	
	@Autowired
	ExamClassMapper examClassDao;
	
	@Autowired
	UserScoresMapper userScoresDao;
	
	@Autowired
	CfgParamMapper cfgParamDao;
	
	@Autowired
	PartnersMapper partnersDao;
	
	@Autowired
	StuUserInfoDao stuUserInfoDao;


	@Override
	public Model dealExport(Model model, UsersQuery usersQuery, HttpServletResponse response,
			HttpServletRequest request) {
		try {
			Map map = new HashMap();
			map.put("institutionInfoId", UserUtils.getUser().getInstitutionInfoId());
			map.put("userName",usersQuery.getUserName());
			map.put("mobile",usersQuery.getMobile());
			List<StuUserInfoEntity> part = stuUserInfoDao.findAllList(map);
			
			if(part==null || part.size()<1){
				model.addAttribute("message","没有数据可以导出");
			}
			
			String fileName = "学员信息列表"+DateUtils.getDate("yyyy-MM-dd")+".xls";
			response.reset();
	        response.addHeader("Content-Disposition", "filename="
	                + new String(fileName.getBytes("gb2312"), "iso8859-1"));
	        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
	        OutputStream out=new BufferedOutputStream(response.getOutputStream());
	        String filePath="./WEB-INF/excelTemp/user_temp";
            ExcelOutput e = new ExcelOutput(out,filePath,request);
            int i = 1;
            
            ApplyOrdersEntity entity=null;
            for (StuUserInfoEntity temp : part) {
            	e.createRow(i++);
				e.setCell(0, temp.getUserName());
				e.setCell(1, temp.getMobile());
				entity=new ApplyOrdersEntity();
    			entity.setStuUserInfoId(temp.getId());
    			entity.setInstitutionInfoId(UserUtils.getUser().getInstitutionInfoId());
    			entity.setHandleStatus(DBStatus.HandleStatus.SUCCESS);
    			Integer count=applyOrdersDao.findAllCount(entity);
				e.setCell(2, count);	
			}
	    	e.export();
		} catch (IOException ex) {
            logger.error("写入excel出错:" + ex);
            ex.printStackTrace();
            model.addAttribute("message","导出失败，程序异常");
        } catch (Exception e) {
        	e.printStackTrace();
			model.addAttribute("message","导出失败，程序异常");
		}
		return model;
	}

	
	@Override
	public UserBean getUsersInfo(Integer id) {
		UserBean user=new UserBean();
		try {
			UserBaseInfoEntity userInfo=userBaseInfoDao.getById(id);
			UserExtendInfoEntity userExtend=userExtendInfoDao.getById(id);
			ApplyOrdersEntity entity=new ApplyOrdersEntity();
			entity.setUserId(id);
			entity.setHandleStatus(DBStatus.HandleStatus.SUCCESS);
			List<ApplyOrdersEntity> list=applyOrdersDao.findAllList(entity);
			if(userInfo!=null){
				user.setUserInfo(userInfo);
			}
			if(userExtend!=null){
				user.setUserExtend(userExtend);
			}
			if(list!=null && list.size()>0){
				ExamClassEntity examClass=null;
				UserScoresEntity userScores=null;
				UserScoresKey userScoresKey=null;
				for(ApplyOrdersEntity order:list){
					examClass=new ExamClassEntity();
					examClass=examClassDao.getById(order.getClassNumber());
					if(examClass!=null){
						order.setExamClass(examClass);
					}
					userScores=new UserScoresEntity();
					userScoresKey=new UserScoresKey();
					userScoresKey.setClassId(order.getClassNumber());
					userScoresKey.setUserId(order.getUserId());
					userScores=userScoresDao.getByKey(userScoresKey);
					if(userScores!=null){
						order.setUserScores(userScores);
					}
				}
				user.setOrderList(list);
			}
		} catch (Exception e) {
			Log.error("获取用户详细失败");
			e.printStackTrace();
		}
		
		return user;
	}

	@Override
	public UserBean getUsers(Integer id) {
		UserBean user=new UserBean();
		try {
			UserBaseInfoEntity userInfo=userBaseInfoDao.getById(id);
			UserExtendInfoEntity userExtend=userExtendInfoDao.getById(id);
			if(userInfo!=null){
				user.setUserInfo(userInfo);
			}else{
				user.setUserInfo(new UserBaseInfoEntity());
			}
			if(userExtend!=null){
				IdcardInfoExtractor idcardInfo=new IdcardInfoExtractor(userExtend.getCertNo()); 
				userExtend.setAge(idcardInfo.getAge());
				userExtend.setBirthplace(idcardInfo.getProvince());
				userExtend.setSex(idcardInfo.getGender());
				user.setUserExtend(userExtend);
			}else{
				user.setUserExtend(new UserExtendInfoEntity());
			}
		} catch (Exception e) {
			Log.error("获取用户信息失败");
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public void modifyUsers(UserBean userBean) {
		UserBaseInfoEntity userInfo=userBaseInfoDao.getById(userBean.getUserInfo().getId());
		UserExtendInfoEntity userExtend=userExtendInfoDao.getById(userBean.getUserInfo().getId());
		if(userInfo==null){
			throw new ServiceException(Message.UPDATE_DATE_NOEXIST);
		}
		userBaseInfoDao.update(UsersOpt.buildUserInfo(userInfo, userBean));
		if(userExtend==null){
			UserExtendInfoEntity obj=new UserExtendInfoEntity();
			obj.setUserId(userBean.getUserInfo().getId());
			userExtendInfoDao.insert(UsersOpt.buildUserExtend(obj, userBean.getUserExtend()));
		}else{
			userExtendInfoDao.update(UsersOpt.buildUserExtend(userExtend, userBean.getUserExtend()));
		}
	}

	@Override
	public List<UserBaseInfoEntity> findUserByCertNoOrMobile(String certNo, String mobile) {
		return userBaseInfoDao.findUserByCertNoOrMobile(certNo, mobile);
	}

	@Override
	public UserExtendInfoEntity getById(Integer userId) {
		return userExtendInfoDao.getById(userId);
	}

	@Override
	public UserExtendInfoEntity getCertNoUserExtendInfo(Map map) {
		return userExtendInfoDao.getCertNoUserExtendInfo(map);
	}

}




















