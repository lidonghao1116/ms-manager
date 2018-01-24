package com.jiacer.modules.business.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiacer.modules.business.bean.DictionariesUtil;
import com.jiacer.modules.business.bean.operation.CoursesTimeOpt;
import com.jiacer.modules.business.service.CoursesService;
import com.jiacer.modules.business.service.CoursesTimeService;
import com.jiacer.modules.business.service.ExamsService;
import com.jiacer.modules.business.utils.CoursesTimeUtils;
import com.jiacer.modules.business.utils.CoursesUtils;
import com.jiacer.modules.business.validate.CoursesTimeValidate;
import com.jiacer.modules.business.validate.CoursesValidate;
import com.jiacer.modules.business.validate.SchoolsValidate;
import com.jiacer.modules.common.controller.BaseController;
import com.jiacer.modules.common.page.Page;
import com.jiacer.modules.common.utils.JsonResult;
import com.jiacer.modules.mybatis.dao.CoursesTimeDao;
import com.jiacer.modules.mybatis.entity.ControlEntity;
import com.jiacer.modules.mybatis.entity.CoursesTimeEntity;
import com.jiacer.modules.mybatis.entity.ExamClassEntity;
import com.jiacer.modules.mybatis.entity.LearnTypesEntity;
import com.jiacer.modules.mybatis.entity.SchoolsEntity;
import com.jiacer.modules.mybatis.entity.SysUsersEntity;
import com.jiacer.modules.system.config.MappingURL;
import com.jiacer.modules.system.config.Message;
import com.jiacer.modules.system.config.SysConstants;
import com.jiacer.modules.system.utils.UserUtils;

import net.sf.json.JSONObject;

/** 
* @ClassName: CoursesTimeController 
* @Description: 上课时间管理控制类
* @author 贺章鹏
* @date 2016年10月19日 下午12:45:06 
*  
*/
@Controller
@RequestMapping(MappingURL.COURSES_TIME_URL)
public class CoursesTimeController extends BaseController{
	
	@Resource
	CoursesTimeService coursesTimeService;
	
	@Resource
	CoursesTimeDao coursesTimeDao;
	
	
	//列表页面
	@RequestMapping(MappingURL.LIST_URL)
	public String list(Model model) {
	    return "modules/coursesTime/list";
	}
	//查询
	@RequestMapping(MappingURL.QUERY_URL)
	@ResponseBody
	public Page<CoursesTimeEntity> page(
		@RequestParam(value = "page", defaultValue = SysConstants.DEFAULT_PAGE_NO) int pageNumber,
		@RequestParam(value = "records", defaultValue = SysConstants.DEFAULT_PAGE_SIZE) int pageSize,
		Model model,CoursesTimeEntity coursesTimeEntity) {
		Page<CoursesTimeEntity> reslut=coursesTimeService.getCoursesPage(coursesTimeEntity, pageNumber, pageSize);
	    return reslut;
	}
	//新增调整页
	@RequestMapping(MappingURL.ADD_TRME_URL)
	public String addTrme(Model model) {
		model.addAttribute("openingTimeList", DictionariesUtil.getTimeSlot());
		model.addAttribute("openCycleList", DictionariesUtil.getWeek());
	    return "modules/coursesTime/addTrme";
	}
	//新增
	@RequestMapping(MappingURL.ADD_URL)
	@ResponseBody
	public JsonResult add(Model model,CoursesTimeEntity coursesTimeEntity) {
		if(coursesTimeEntity == null){
			logger.info(Message.buildErrInfo(Message.PARAM_ERROR_EXCEPTION, "上课时间管理对象为null"));
			return new JsonResult(false,Message.PARAM_ERROR_MSG,null);
		}
		JsonResult validate=CoursesTimeValidate.addValidate(coursesTimeEntity,coursesTimeService);
		if(!validate.isSuccess()){
			return validate;
		}
		
		JsonResult jsonResult=null;
		try {
			coursesTimeEntity.setTemplateId("T_00"+coursesTimeService.countNum());
			coursesTimeEntity.setCreateDate(new Date());
			coursesTimeEntity.setModifyDate(new Date());
			SysUsersEntity sysUser = UserUtils.getUser();//获取登录用户信息
			coursesTimeEntity.setInstitutionInfoId(sysUser.getInstitutionInfoId());
			coursesTimeService.addCourses(coursesTimeEntity);
			jsonResult=new JsonResult(true,Message.SUCCESS_MSG,null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(Message.buildErrInfo(Message.ERROR_EXCEPTION,e));
			jsonResult=new JsonResult(false,Message.FAILED_MSG,null);
		}
		return jsonResult;
	}
	
	public CoursesTimeEntity getModel(Integer id){
		if(id != null){
			return coursesTimeService.getCoursesTimeById(id);
		}else{
			return new CoursesTimeEntity();
		}
	}
	//BOND-新增or修改-跳转
	@RequestMapping(MappingURL.FORM_URL)
	public String form(Model model,Integer id) {
		CoursesTimeEntity coursesTimeEntity=this.getModel(id);
		List<CoursesTimeEntity> list=CoursesTimeUtils.init();
		model.addAttribute("update", Boolean.TRUE);
		model.addAttribute("model", coursesTimeEntity);
		model.addAttribute("courses", list);
		model.addAttribute("openingTimeList", DictionariesUtil.getTimeSlot());
		model.addAttribute("openCycleList", DictionariesUtil.getWeek());
		return "modules/coursesTime/updateTrme";
	}
	//修改
	@RequestMapping(MappingURL.MODIFY_URL)
	@ResponseBody
	public JsonResult modify(Model model,CoursesTimeEntity coursesTimeEntity) {
		if(coursesTimeEntity == null || coursesTimeEntity.getId() ==null){
			logger.info(Message.buildErrInfo(Message.PARAM_ERROR_EXCEPTION, "课程管理对象为null或课程管理id为空"));
			return new JsonResult(false,Message.PARAM_ERROR_MSG,null);
		}
		JsonResult validate=CoursesTimeValidate.modifyValidate(coursesTimeEntity,coursesTimeService);
		
		if(!validate.isSuccess()){
			return validate;
		}
		JsonResult jsonResult=null;
		try {
			coursesTimeEntity.setModifyDate(new Date());
			coursesTimeService.modifyCourses(coursesTimeEntity);
			jsonResult=new JsonResult(true,Message.SUCCESS_MSG,null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(Message.buildErrInfo(Message.ERROR_EXCEPTION,e));
			jsonResult=new JsonResult(false,Message.FAILED_MSG,null);
		}
		return jsonResult;
	}
	
	//http://localhost:8080/jiacerconsole/coursesTime/getTimeList
	@RequestMapping(MappingURL.GET_TIME_LIST)
	@ResponseBody
	public Map<String,List<CoursesTimeEntity>> getTimeList(Model model,LearnTypesEntity learnTypesEntity) {
		Map<String,List<CoursesTimeEntity>> map = new HashMap<String,List<CoursesTimeEntity>>();
		Map mapOpt = new HashMap();
		mapOpt.put("id", learnTypesEntity.getLearnTypesId());
		List<CoursesTimeEntity> coursesTimeList = coursesTimeDao.getSKTemplate(mapOpt);
		map.put("coursesTimeList", coursesTimeList);
		return map;
	}
	
	
}
