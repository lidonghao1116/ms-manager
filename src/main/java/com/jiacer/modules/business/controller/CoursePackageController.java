package com.jiacer.modules.business.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.jiacer.modules.business.bean.DictionariesUtil;
import com.jiacer.modules.business.bean.form.InstitutionQuery;
import com.jiacer.modules.business.service.CoursePackageService;
import com.jiacer.modules.business.service.CoursesService;
import com.jiacer.modules.business.service.SchoolsService;
import com.jiacer.modules.business.validate.CoursePackageValidate;
import com.jiacer.modules.common.constants.Constants;
import com.jiacer.modules.common.controller.BaseController;
import com.jiacer.modules.common.page.Page;
import com.jiacer.modules.common.utils.JsonResult;
import com.jiacer.modules.log.Log;
import com.jiacer.modules.mybatis.dao.LearnTypesMapper;
import com.jiacer.modules.mybatis.entity.ControlEntity;
import com.jiacer.modules.mybatis.entity.CoursePackageEntity;
import com.jiacer.modules.mybatis.entity.LearnTypesEntity;
import com.jiacer.modules.mybatis.entity.SysUsersEntity;
import com.jiacer.modules.mybatis.model.InstitutionInfo;
import com.jiacer.modules.system.config.DBStatus;
import com.jiacer.modules.system.config.MappingURL;
import com.jiacer.modules.system.config.Message;
import com.jiacer.modules.system.config.SysConstants;
import com.jiacer.modules.system.utils.UserUtils;

/** 
* @ClassName: CoursePackageController 
* @Description: 课程销售管理控制类
* @author 贺章鹏
* @date 2016年10月19日 下午12:47:21 
*  
*/
@Controller
@RequestMapping(MappingURL.COURSES_PACKAGE_URL)
public class CoursePackageController extends BaseController{
	
	@Resource
	CoursePackageService coursePackageService;
	
	@Resource
	CoursesService coursesService;
	
	@Resource
	LearnTypesMapper coursesDao;

	public CoursePackageEntity getModel(Integer id){
		if(id != null){
			return coursePackageService.getCoursePackageById(id);
		}else{
			return new CoursePackageEntity();
		}
	}
	
	//新增修改页面
	@RequestMapping(MappingURL.FORM_URL)
	public String form(Model model,Integer id) {
		CoursePackageEntity coursePackageEntity=this.getModel(id);
		List<LearnTypesEntity> result=Lists.newArrayList();
		LearnTypesEntity entity=new LearnTypesEntity();
		entity.setIsUsable(DBStatus.IsUsable.TRUE);//基础参数类型
		entity.setStatus(DBStatus.CourseStatus.NOMAL);
		entity.setInstitutionInfoId(UserUtils.getUser().getInstitutionInfoId());
		entity.setStatus(Constants.COURSE_STATUS_SHELEVES);
		try {
			result=coursesDao.findAllListByStatus(entity);
		} catch (Exception e) {
			Log.error("获取课程数据失败");
		}
		model.addAttribute("courses", result);
		if(coursePackageEntity==null || coursePackageEntity.getId() == null){
			model.addAttribute("update", Boolean.FALSE);
			model.addAttribute("model", null);
			return "modules/coursePackage/form";//新增产品页面
		}else{
			model.addAttribute("update", Boolean.TRUE);
			model.addAttribute("model", coursePackageEntity);
			
			String applyCourses = coursePackageEntity.getApplyCourses();
			String [] stringArr= applyCourses.split(",");
			List<ControlEntity> appCouList = new ArrayList<ControlEntity>();
			List<ControlEntity> appCouOldNameList = new ArrayList<ControlEntity>();
			for(int i = 0; i < stringArr.length; i++){
				LearnTypesEntity lety = coursesDao.getById(Integer.parseInt(stringArr[i]));
				appCouList.add(new ControlEntity (stringArr[i], stringArr[i]));
				appCouOldNameList.add(new ControlEntity(lety.getId()+"", lety.getTypeName(),DictionariesUtil.getAuthenticateGradeName(lety.getAuthenticateGrade())));
			}
			model.addAttribute("statusList",DictionariesUtil.setCoursePackageStatus());
			model.addAttribute("kclist",coursesService.getCourseIdName());
			model.addAttribute("appCouList",appCouList);
			model.addAttribute("appCouOldNameList",appCouOldNameList); //课程
			return "modules/coursePackage/updateForm";//修改产品页面
		}
		
	   
	}
	
	//列表页面
	@RequestMapping(MappingURL.LIST_URL)
	public String list(Model model) {
	    return "modules/coursePackage/list";
	}
	
	//查询
	@RequestMapping(MappingURL.QUERY_URL)
	@ResponseBody
	public Page<CoursePackageEntity> page(
			@RequestParam(value = "page", defaultValue = SysConstants.DEFAULT_PAGE_NO) int pageNumber,
			@RequestParam(value = "records", defaultValue = SysConstants.DEFAULT_PAGE_SIZE) int pageSize,
			Model model,CoursePackageEntity coursePackageEntity) {
		Page<CoursePackageEntity> reslut=coursePackageService.getCoursePackagePage(coursePackageEntity, pageNumber, pageSize);
	    return reslut;
	}
	
	
	//新增
	@RequestMapping(MappingURL.ADD_URL)
	@ResponseBody
	public JsonResult add(Model model,CoursePackageEntity coursePackageEntity) {
		if(coursePackageEntity == null){
			logger.info(Message.buildErrInfo(Message.PARAM_ERROR_EXCEPTION, "课程销售对象为null"));
			return new JsonResult(false,Message.PARAM_ERROR_MSG,null);
		}
		
		JsonResult validate=CoursePackageValidate.addValidate(coursePackageEntity,coursePackageService);
		
		if(!validate.isSuccess()){
			return validate;
		}
		
		JsonResult jsonResult=null;
		
		try {
			coursePackageService.addCoursePackage(coursePackageEntity);
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
	public JsonResult modify(Model model,CoursePackageEntity coursePackageEntity) {
		if(coursePackageEntity == null || coursePackageEntity.getId() ==null){
			logger.info(Message.buildErrInfo(Message.PARAM_ERROR_EXCEPTION, "课程销售对象对象为null或课程销售对象id为空"));
			return new JsonResult(false,Message.PARAM_ERROR_MSG,null);
		}
		
		JsonResult validate=CoursePackageValidate.modifyValidate(coursePackageEntity,coursePackageService,coursesDao);
		
		if(!validate.isSuccess()){
			return validate;
		}
		
		JsonResult jsonResult=null;
		
		try {
			coursePackageService.modifyCoursePackage(coursePackageEntity);
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
	public JsonResult delete(Model model,CoursePackageEntity coursePackageEntity) {
		if(coursePackageEntity == null || coursePackageEntity.getId() ==null){
			logger.info(Message.buildErrInfo(Message.PARAM_ERROR_EXCEPTION, "课程销售对象对象为null或课程销售对象id为空"));
			return new JsonResult(false,Message.PARAM_ERROR_MSG,null);
		}
		
		JsonResult jsonResult=null;
		
		try {
			coursePackageService.delCoursePackage(coursePackageEntity);
			jsonResult=new JsonResult(true,Message.SUCCESS_MSG,null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(Message.buildErrInfo(Message.ERROR_EXCEPTION,e));
			jsonResult=new JsonResult(false,Message.FAILED_MSG,null);
		}
		return jsonResult;
	}

}
