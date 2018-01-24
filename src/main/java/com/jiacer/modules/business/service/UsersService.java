package com.jiacer.modules.business.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jiacer.modules.mybatis.model.UserBaseInfo;
import org.springframework.ui.Model;

import com.jiacer.modules.business.bean.UserBean;
import com.jiacer.modules.business.bean.form.UsersQuery;
import com.jiacer.modules.common.page.Page;
import com.jiacer.modules.mybatis.entity.PartnersEntity;
import com.jiacer.modules.mybatis.entity.UserBaseInfoEntity;
import com.jiacer.modules.mybatis.entity.UserExtendInfoEntity;

/** 
* @ClassName: UsersService 
* @Description: 会员用户管理接口服务
* @author 贺章鹏
* @date 2016年10月19日 下午4:07:09 
*  
*/
public interface UsersService {

	UserExtendInfoEntity getById(Integer userId);
	
	UserExtendInfoEntity getCertNoUserExtendInfo(Map map);
	
	Model dealExport(Model model, UsersQuery usersQuery, HttpServletResponse response,HttpServletRequest request);

	/**
	 * 获取用户的详情根据id
	 * @param id
	 * @return
	 */
	UserBean getUsersInfo(Integer id);

	/**
	 * 获取会员信息
	 * @param id
	 * @return
	 */
	UserBean getUsers(Integer id);

	/**
	 * 修改用户信息
	 * @param userBean
	 */
	void modifyUsers(UserBean userBean);

	/**
	 * 根据证件号或者手机号查询用户信息
	 * @param certNo
	 * @param mobile
	 * @return
	 */
     List<UserBaseInfoEntity> findUserByCertNoOrMobile(String certNo, String mobile);
}
