package com.jiacer.modules.system.controller;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiacer.modules.common.controller.BaseController;
import com.jiacer.modules.common.page.Page;
import com.jiacer.modules.common.utils.EntryptUtils;
import com.jiacer.modules.common.utils.JsonResult;
import com.jiacer.modules.common.utils.StringUtils;
import com.jiacer.modules.mybatis.entity.SysRoleEntity;
import com.jiacer.modules.mybatis.entity.SysUsersEntity;
import com.jiacer.modules.system.config.Message;
import com.jiacer.modules.system.config.MappingURL;
import com.jiacer.modules.system.config.SysConstants;
import com.jiacer.modules.system.service.SystemService;
import com.jiacer.modules.system.utils.UserUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * @Description: 系统管理控制类
 * @author hzp
 * @date 2016-4-8
 *
 */
@Controller("sysUsersController")
@RequestMapping(MappingURL.SYSTEM_BASE_URL+MappingURL.USER_URL)
public class UsersController extends BaseController{
	
	@Resource
	SystemService systemService;
	
	public SysUsersEntity getUser(Integer id){
		if(id != null){
			return systemService.getUseById(id);
		}else{
			return new SysUsersEntity();
		}
	}
	
	//*************************用户******************************//
	//新增修改页面
	@RequestMapping(MappingURL.FORM_URL)
	public String userForm(Model model,Integer id) {
		
		SysUsersEntity userEntity=this.getUser(id);
		
		if(userEntity==null || userEntity.getId() == null){
			model.addAttribute("update", Boolean.FALSE);
			model.addAttribute("user", null);
		}else{
			model.addAttribute("update", Boolean.TRUE);
			model.addAttribute("user", userEntity);
		}
	    return "modules/system/user/form";
	}
	
	//列表
	@RequestMapping(MappingURL.LIST_URL)
	public String userList(Model model,SysUsersEntity userEntity) {
	    return "modules/system/user/list";
	}
	
	//查询
	@RequestMapping(MappingURL.QUERY_URL)
	@ResponseBody
	public Page<SysUsersEntity> userPage(
			@RequestParam(value = "page", defaultValue = SysConstants.DEFAULT_PAGE_NO) int pageNumber,
			@RequestParam(value = "records", defaultValue = SysConstants.DEFAULT_PAGE_SIZE) int pageSize,
			Model model,SysUsersEntity userEntity) {
		
		Page<SysUsersEntity> reslut=systemService.getUserPage(userEntity, pageNumber, pageSize);
	    return reslut;
	}
	
	
	//新增
	@RequestMapping(MappingURL.ADD_URL)
	@ResponseBody
	public JsonResult addUser(Model model,SysUsersEntity user) {
		if(user == null){
			logger.info(Message.buildErrInfo(Message.PARAM_ERROR_EXCEPTION, "用户对象为null"));
			return new JsonResult(false,Message.PARAM_ERROR_MSG,null);
		}

		SysUsersEntity entity = systemService.getUseByLoginAccount(UserUtils.getUser().getLoginAccount()+user.getLoginAccount());
		if(entity != null){
			logger.info(Message.buildErrInfo("用户名重复，请修改", "用户名重复，请修改"));
			return new JsonResult(false,"用户名重复，请修改",null);
		}

		JsonResult jsonResult=null;
		
		try {
			systemService.addUser(user);
			jsonResult=new JsonResult(true,Message.SUCCESS_MSG,null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(Message.buildErrInfo(Message.ERROR_EXCEPTION,e));
			jsonResult=new JsonResult(false,Message.FAILED_MSG,null);
		}
		return jsonResult;
	}
	
	//修改
	@RequestMapping(MappingURL.MODIFY_URL)
	@ResponseBody
	public JsonResult modifyUser(Model model,SysUsersEntity user) {
		if(user == null || user.getId() ==null){
			logger.info(Message.buildErrInfo(Message.PARAM_ERROR_EXCEPTION, "用户对象为null或用户id为空"));
			return new JsonResult(false,Message.PARAM_ERROR_MSG,null);
		}
		
		JsonResult jsonResult=null;
		
		try {
			systemService.modifyUser(user);
			jsonResult=new JsonResult(true,Message.SUCCESS_MSG,null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(Message.buildErrInfo(Message.ERROR_EXCEPTION,e));
			jsonResult=new JsonResult(false,Message.FAILED_MSG,null);
		}
		return jsonResult;
	}

	//修改密码
	@RequestMapping(MappingURL.RESET_PWD_URL)
	@ResponseBody
	public JsonResult resetPwd(Model model,SysUsersEntity user) {
		if(user == null || user.getId() ==null){
			logger.info(Message.buildErrInfo(Message.PARAM_ERROR_EXCEPTION, "用户对象为null或用户id为空"));
			return new JsonResult(false,Message.PARAM_ERROR_MSG,null);
		}

		JsonResult jsonResult=null;
        
		try {
			user.setPassword(EntryptUtils.entryptPassword("000000"));
			
			
			systemService.updateUserPwd(user);
		
			jsonResult=new JsonResult(true,Message.SUCCESS_MSG,null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(Message.buildErrInfo(Message.ERROR_EXCEPTION,e));
			jsonResult=new JsonResult(false,Message.FAILED_MSG,null);
		}
		return jsonResult;
	}
	
	//删除
	@RequestMapping(MappingURL.DELETE_URL)
	@ResponseBody
	public JsonResult delUser(Model model,SysUsersEntity user) {
		if(user == null || user.getId() ==null){
			logger.info(Message.buildErrInfo(Message.PARAM_ERROR_EXCEPTION, "用户对象为null或用户id为空"));
			return new JsonResult(false,Message.PARAM_ERROR_MSG,null);
		}
		if(user.getId().equals(UserUtils.getUser().getId())){
			logger.info(Message.buildErrInfo("不可删除当前登陆用户", "不可删除当前登陆用户"));
			return new JsonResult(false,"不可删除当前登陆用户",null);
		}


		JsonResult jsonResult=null;
		
		try {
			systemService.delUser(user);
			jsonResult=new JsonResult(true,Message.SUCCESS_MSG,null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(Message.buildErrInfo(Message.ERROR_EXCEPTION,e));
			jsonResult=new JsonResult(false,Message.FAILED_MSG,null);
		}
		return jsonResult;
	}
	
	//获取当前用户
	@RequestMapping(MappingURL.INFO_URL)
	@ResponseBody
	public JsonResult getCUser() {
		SysUsersEntity user = UserUtils.getUser();
		JsonResult jsonResult=null;
		if(user==null){
			jsonResult=new JsonResult(false,Message.FAILED_MSG,null);
		}
		jsonResult=new JsonResult(true,Message.SUCCESS_MSG,user);
		return jsonResult;
	}
	
	//获取当前用户的角色
	@RequestMapping(MappingURL.ROLE_QUERY_URL)
	@ResponseBody
	public List<String> getCRoles() {
		SysUsersEntity user = UserUtils.getUser();
		List<SysRoleEntity> roles=user.getRoles();
		List<String> strs=new ArrayList<String>();
		strs.add("");
		if(roles!=null)
		{
			for(SysRoleEntity role:roles )
			{
				strs.add(role.getRcode());
			}
		}
		return strs;
	}
	
	@RequestMapping(MappingURL.MODIFY_PWD)
	@ResponseBody
	public JsonResult modifyPwd(String oldPassword, String newPassword, Model model) {
		JsonResult jsonResult=null;
		try {
			SysUsersEntity user = UserUtils.getUser();
			if (StringUtils.isNotBlank(oldPassword) && StringUtils.isNotBlank(newPassword)){
				if (EntryptUtils.validatePassword(oldPassword, user.getPassword())){
					systemService.modifyPasswordById(user.getId(), newPassword);
					jsonResult=new JsonResult(true,Message.SUCCESS_MSG,null);
				}else{
					jsonResult=new JsonResult(false,"修改密码失败，旧密码错误",null);
				}
			}else{
				jsonResult=new JsonResult(false,"修改密码失败，参数异常",null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult=new JsonResult(false,"修改密码失败",null);
			logger.error("修改密码失败", e);
		}
		
		return jsonResult;
	}
	
	//*************************用户******************************//
}
