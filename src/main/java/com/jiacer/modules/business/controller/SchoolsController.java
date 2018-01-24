package com.jiacer.modules.business.controller;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.jiacer.modules.business.service.SchoolsService;
import com.jiacer.modules.business.validate.SchoolsValidate;
import com.jiacer.modules.common.controller.BaseController;
import com.jiacer.modules.common.page.Page;
import com.jiacer.modules.common.utils.JsonResult;
import com.jiacer.modules.log.Log;
import com.jiacer.modules.mybatis.dao.LearnTypesMapper;
import com.jiacer.modules.mybatis.entity.LearnTypesEntity;
import com.jiacer.modules.mybatis.entity.SchoolsEntity;
import com.jiacer.modules.system.config.DBStatus;
import com.jiacer.modules.system.config.MappingURL;
import com.jiacer.modules.system.config.Message;
import com.jiacer.modules.system.config.SysConstants;
import com.jiacer.modules.system.utils.UserUtils;

/** 
* @ClassName: schoolsController 
* @Description: 学校管理
* @author 贺章鹏
* @date 2016年10月19日 下午1:24:57 
*  
*/
@Controller
@RequestMapping(MappingURL.SCHOOLS_URL)
public class SchoolsController extends BaseController{

	@Resource
	SchoolsService schoolsService;
	
	@Resource
	LearnTypesMapper coursesDao;
	
	
	public SchoolsEntity getModel(Integer id){
		if(id != null){
			return schoolsService.getSchoolsById(id);
		}else{
			return new SchoolsEntity();
		}
	}
	
	//BOND-合作学校跳转-list.jsp
	@RequestMapping(MappingURL.LIST_URL)
	public String list(Model model) {
	    return "modules/schools/list";
	}
	//查询
	@RequestMapping(MappingURL.QUERY_URL)
	@ResponseBody
	public Page<SchoolsEntity> page(
			@RequestParam(value = "page", defaultValue = SysConstants.DEFAULT_PAGE_NO) int pageNumber,
			@RequestParam(value = "records", defaultValue = SysConstants.DEFAULT_PAGE_SIZE) int pageSize,
			Model model,SchoolsEntity schoolsEntity) {
		Page<SchoolsEntity> reslut=schoolsService.getSchoolsPage(schoolsEntity, pageNumber, pageSize);
		return reslut;
	}
		
	//BOND-新增or修改-跳转
	@RequestMapping(MappingURL.FORM_URL)
	public String form(Model model,Integer id) {
		
		SchoolsEntity schoolsEntity=this.getModel(id);
		List<LearnTypesEntity> result=Lists.newArrayList();
		LearnTypesEntity entity=new LearnTypesEntity();
		entity.setIsUsable(DBStatus.IsUsable.TRUE);//基础参数类型
		entity.setStatus(DBStatus.CourseStatus.NOMAL);
		entity.setInstitutionInfoId(UserUtils.getUser().getInstitutionInfoId());
		try {
			result=coursesDao.findAllList(entity);
		} catch (Exception e) {
			Log.error("获取课程数据失败");
		}
		
		if(schoolsEntity==null || schoolsEntity.getId() == null){
			model.addAttribute("update", Boolean.FALSE);
			model.addAttribute("model", null);
			model.addAttribute("courses", result);
		    return "modules/schools/form";
		}else{
			model.addAttribute("update", Boolean.TRUE);
			model.addAttribute("model", schoolsEntity);
			model.addAttribute("courses", result);
		    return "modules/schools/formUpdate";
		}
		
	}

	//新增
	@RequestMapping(MappingURL.ADD_URL)
	@ResponseBody
	public JsonResult add(Model model,SchoolsEntity schoolsEntity) {
		if(schoolsEntity == null){
			logger.info(Message.buildErrInfo(Message.PARAM_ERROR_EXCEPTION, "学校管理对象为null"));
			return new JsonResult(false,Message.PARAM_ERROR_MSG,null);
		}
		JsonResult validate=SchoolsValidate.addValidate(schoolsEntity,schoolsService);
		if(!validate.isSuccess()){
			return validate;
		}
		JsonResult jsonResult=null;
		try {
			schoolsService.addschools(schoolsEntity);
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
	public JsonResult modify(Model model,SchoolsEntity schoolsEntity) {
		if(schoolsEntity == null || schoolsEntity.getId() ==null){
			logger.info(Message.buildErrInfo(Message.PARAM_ERROR_EXCEPTION, "学校管理对象对象为null或学校管理对象id为空"));
			return new JsonResult(false,Message.PARAM_ERROR_MSG,null);
		}
		
		JsonResult validate=SchoolsValidate.modifyValidate(schoolsEntity);
		
		if(!validate.isSuccess()){
			return validate;
		}
		
		JsonResult jsonResult=null;
		
		try {
			schoolsService.modifyschools(schoolsEntity);
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
	public JsonResult delete(Model model,SchoolsEntity schoolsEntity) {
		if(schoolsEntity == null || schoolsEntity.getId() ==null){
			logger.info(Message.buildErrInfo(Message.PARAM_ERROR_EXCEPTION, "学校管理对象对象为null或学校管理对象id为空"));
			return new JsonResult(false,Message.PARAM_ERROR_MSG,null);
		}
		
		JsonResult jsonResult=null;
		
		try {
			schoolsService.delschools(schoolsEntity);
			jsonResult=new JsonResult(true,Message.SUCCESS_MSG,null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(Message.buildErrInfo(Message.ERROR_EXCEPTION,e));
			jsonResult=new JsonResult(false,Message.FAILED_MSG,null);
		}
		return jsonResult;
	}
}
