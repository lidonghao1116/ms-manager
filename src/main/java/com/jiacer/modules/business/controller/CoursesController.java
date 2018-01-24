package com.jiacer.modules.business.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiacer.modules.business.bean.DictionariesUtil;
import com.jiacer.modules.business.service.CertAuthorityService;
import com.jiacer.modules.business.service.CoursePackageService;
import com.jiacer.modules.business.service.CoursesService;
import com.jiacer.modules.business.service.CoursesTimeService;
import com.jiacer.modules.business.validate.CoursesValidate;
import com.jiacer.modules.common.constants.Constants;
import com.jiacer.modules.common.controller.BaseController;
import com.jiacer.modules.common.page.Page;
import com.jiacer.modules.common.utils.JsonResult;
import com.jiacer.modules.log.Log;
import com.jiacer.modules.mybatis.dao.CfgParamMapper;
import com.jiacer.modules.mybatis.dao.LearnTypesMapper;
import com.jiacer.modules.mybatis.entity.ControlEntity;
import com.jiacer.modules.mybatis.entity.CourseBaseInfoEntity;
import com.jiacer.modules.mybatis.entity.CoursePackageEntity;
import com.jiacer.modules.mybatis.entity.CoursesTimeEntity;
import com.jiacer.modules.mybatis.entity.LearnTypesEntity;
import com.jiacer.modules.mybatis.entity.SysUsersEntity;
import com.jiacer.modules.mybatis.model.CertAuthority;
import com.jiacer.modules.mybatis.model.CfgParam;
import com.jiacer.modules.system.config.MappingURL;
import com.jiacer.modules.system.config.Message;
import com.jiacer.modules.system.config.SysConstants;
import com.jiacer.modules.system.utils.UserUtils;


/** 
* @ClassName: CoursesController 
* @Description: 课程管理控制类
* @author 贺章鹏
* @date 2016年10月19日 下午12:45:06 
*  
*/
@Controller
@RequestMapping(MappingURL.COURSES_URL)
public class CoursesController extends BaseController{
	
	private final static Logger log = LoggerFactory.getLogger(CoursesController.class);
	
	@Resource
	CoursesService coursesService;
	
	@Resource
	CoursesTimeService coursesTimeService;
	
	@Resource
	CertAuthorityService certAuthorityService;
	
	@Resource
	LearnTypesMapper coursesDao;
	
	@Resource
	CoursePackageService coursePackageService;
	
	@Resource
	CfgParamMapper cfgParamMapper;
	
	public LearnTypesEntity getModel(Integer id){
		if(id != null){
			return coursesService.getCoursesById(id);
		}else{
			return new LearnTypesEntity();
		}
	}
	
	/**
	 * 
	 * @MethodName:list
	 * @Type:CoursesController
	 * @Description:教务管理--课程管理--列表页面
	 * @Return:String
	 * @Param:@param model
	 * @Param:@return
	 * @Thrown:
	 * @Date:Oct 6, 2017 9:20:31 PM
	 */
	@RequestMapping(value=MappingURL.LIST_URL,method={RequestMethod.GET,RequestMethod.POST})
	public String list(Model model) {
	    return "modules/courses/list";
	}
	
	/**
	 * 
	 * @throws UnsupportedEncodingException 
	 * @MethodName:page
	 * @Type:CoursesController
	 * @Description:课程管理--查询
	 * @Return:Page<LearnTypesEntity>
	 * @Param:@param pageNumber
	 * @Param:@param pageSize
	 * @Param:@param model
	 * @Param:@param learnTypesEntity
	 * @Param:@return
	 * @Thrown:
	 * @Date:Oct 7, 2017 12:43:05 PM
	 */
	@RequestMapping(value=MappingURL.QUERY_URL,method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Page<LearnTypesEntity> page(
			@RequestParam(value = "page", defaultValue = SysConstants.DEFAULT_PAGE_NO) int pageNumber,
			@RequestParam(value = "records", defaultValue = SysConstants.DEFAULT_PAGE_SIZE) int pageSize,
			Model model,LearnTypesEntity learnTypesEntity,HttpServletRequest request) throws UnsupportedEncodingException {
		Page<LearnTypesEntity> reslut=coursesService.getCoursesPage(learnTypesEntity, pageNumber, pageSize);
		return reslut;
	}

	/**
	 * 
	 * @MethodName:form
	 * @Type:CoursesController
	 * @Description:课程管理--新增修改页面
	 * @Return:String
	 * @Param:@param model
	 * @Param:@param id
	 * @Param:@return
	 * @Thrown:
	 * @Date:Oct 7, 2017 9:07:15 PM
	 */
	@RequestMapping(value=MappingURL.FORM_URL,method={RequestMethod.GET,RequestMethod.POST})
	public String form(Model model,Integer id) {
		SysUsersEntity sysUser = UserUtils.getUser();//获取登录用户信息
		Map<Object,Object> map = new HashMap<Object, Object>();
		map.put("institutionInfoId", sysUser.getInstitutionInfoId());
		
		List<CertAuthority> caList = certAuthorityService.getStatusCertAuthority();
		List<ControlEntity> mcalist = new ArrayList<ControlEntity>();
		for(int i = 0; i < caList.size(); i++){
			CertAuthority certAuthority = caList.get(i);
			mcalist.add(new ControlEntity(certAuthority.getAuthorityId().toString(),certAuthority.getAuthorityName()));
		}
		
		List<CoursesTimeEntity> csList = coursesTimeService.getTemplate(map);
		List<ControlEntity> mcslist = new ArrayList<ControlEntity>();
		for(int i = 0; i < csList.size(); i++){
			CoursesTimeEntity coursesTimeEntity = csList.get(i);
			mcslist.add(new ControlEntity(coursesTimeEntity.getId()+"",coursesTimeEntity.getTemplateName()));
		}
		List<CourseBaseInfoEntity> courseBaseInfoEntities = coursesService.getBaseCourseInfo(map);
		List<CfgParam> cfgParam = cfgParamMapper.getCfgParamOfValue(Constants.YES_NO);
		LearnTypesEntity learnTypesEntity=coursesService.getCourseInfoById(id);
		model.addAttribute("timeSlot", DictionariesUtil.getTimeSlot());
		model.addAttribute("timeList", mcslist);
		model.addAttribute("certList", mcalist);
		model.addAttribute("courses", courseBaseInfoEntities);
		model.addAttribute("isNeedHasPf",cfgParam);
		if(learnTypesEntity==null || learnTypesEntity.getId() == null){
			model.addAttribute("update", Boolean.FALSE);
			model.addAttribute("model", null);
			return "modules/courses/form";
		}else{
			model.addAttribute("update", Boolean.TRUE);
			model.addAttribute("model", learnTypesEntity);
			map.put("id", learnTypesEntity.getId());
			List<CoursesTimeEntity> courseTimeTableList = coursesService.getCourseTimeTable(map);
			model.addAttribute("courseTimeTableList", courseTimeTableList);
			return "modules/courses/updateForm";
			
		}
	}
	
	/**
	 * 
	 * @MethodName:getCertName
	 * @Type:CoursesController
	 * @Description:课程对应的信息
	 * @Return:String
	 * @Param:@param request
	 * @Param:@param courseId
	 * @Param:@return
	 * @Thrown:
	 * @Date:Oct 8, 2017 7:14:29 PM
	 */
	@RequestMapping(value=MappingURL.GET_CERT_NAME,method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public CourseBaseInfoEntity getCourseInfoById(HttpServletRequest request,Integer courseId){
		
		CourseBaseInfoEntity cEntity = coursesService.getBaseCourseInfoById(courseId);
		return cEntity;
	}
	
	
	
	/**
	 * 
	 * @throws UnsupportedEncodingException 
	 * @MethodName:add
	 * @Type:CoursesController
	 * @Description:课程管理--课程新增
	 * @Return:JsonResult
	 * @Param:@param model
	 * @Param:@param learnTypesEntity
	 * @Param:@return
	 * @Thrown:
	 * @Date:Oct 8, 2017 7:34:36 PM
	 */
	@RequestMapping(value=MappingURL.ADD_URL,method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public JsonResult add(Model model,LearnTypesEntity learnTypesEntity,HttpServletRequest request) throws UnsupportedEncodingException {
		if(learnTypesEntity == null){
			logger.info(Message.buildErrInfo(Message.PARAM_ERROR_EXCEPTION, "课程管理对象为null"));
			return new JsonResult(false,Message.PARAM_ERROR_MSG,null);
		}
		
		JsonResult validate=CoursesValidate.addValidate(learnTypesEntity);
		if(!validate.isSuccess()){
			return validate;
		}
		JsonResult jsonResult=null;
		try {
			coursesService.addCourses(learnTypesEntity);
			jsonResult=new JsonResult(true,Message.SUCCESS_MSG,null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(Message.buildErrInfo(Message.ERROR_EXCEPTION,e));
			jsonResult=new JsonResult(false,Message.FAILED_MSG,null);
		}
		return jsonResult;
	}
	
	/**
	 * 
	 * @MethodName:modify
	 * @Type:CoursesController
	 * @Description:课程管理--修改课程
	 * @Return:JsonResult
	 * @Param:@param model
	 * @Param:@param learnTypesEntity
	 * @Param:@return
	 * @Thrown:
	 * @Date:Oct 9, 2017 6:37:45 AM
	 */
	@RequestMapping(value=MappingURL.MODIFY_URL,method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public JsonResult modify(Model model,LearnTypesEntity learnTypesEntity) {
		if(learnTypesEntity == null || learnTypesEntity.getId() ==null){
			logger.info(Message.buildErrInfo(Message.PARAM_ERROR_EXCEPTION, "课程管理对象为null或课程管理id为空"));
			return new JsonResult(false,Message.PARAM_ERROR_MSG,null);
		}
		JsonResult validate=CoursesValidate.modifyValidate(learnTypesEntity,coursesService);
		if(!validate.isSuccess()){
			return validate;
		}
		JsonResult jsonResult=null;
		try {
			coursesService.modifyCourses(learnTypesEntity);
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
	public JsonResult delete(Model model,LearnTypesEntity learnTypesEntity) {
		if(learnTypesEntity == null || learnTypesEntity.getId() ==null){
			logger.info(Message.buildErrInfo(Message.PARAM_ERROR_EXCEPTION, "课程管理对象为null或课程管理id为空"));
			return new JsonResult(false,Message.PARAM_ERROR_MSG,null);
		}
		JsonResult jsonResult=null;
		try {
			coursesService.delCourses(learnTypesEntity);
			jsonResult=new JsonResult(true,Message.SUCCESS_MSG,null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(Message.buildErrInfo(Message.ERROR_EXCEPTION,e));
			jsonResult=new JsonResult(false,Message.FAILED_MSG,null);
		}
		return jsonResult;
	}
	
	
	@RequestMapping(MappingURL.GET_COU_PAC_LEARNTYPELIST)
	@ResponseBody
	public Map<String,String> getPackIdLearnTypeList(Model model,LearnTypesEntity learnTypesEntity) {
		Map<String,String> map = new HashMap<String,String>();
		CoursePackageEntity coursePackageEntity = coursePackageService.getById(learnTypesEntity.getCoursePackageId());
		String[] learnTypesId = coursePackageEntity.getApplyCourses().split(",");
		for(int i = 0; i < learnTypesId.length; i ++){
			LearnTypesEntity learnTypes = coursesService.getById(Integer.parseInt(learnTypesId[i]));
			if(learnTypes.getStatus().equals("01")){
				map.put(learnTypes.getId()+"", learnTypes.getTypeName());	
			}
		}
		return map;
	}


	@RequestMapping(MappingURL.GET_COURSES_STATUS)
	@ResponseBody
	public JsonResult getIsExamsAvailable(Model model,LearnTypesEntity learnTypesEntity) {
		LearnTypesEntity learnTypes = coursesService.getById(learnTypesEntity.getId());
		//LearnTypesEntity learnTypes = coursesService.getById(8);
		Map<String,String> map = new HashMap<String, String>();
		map.put("examForm", learnTypes.getExamType());
		map.put("examTypeName", DictionariesUtil.getExamTypeMap().get(learnTypes.getExamType()));
		return new JsonResult(true, "", map);
	}
	

	//http://localhost:8080/jiacerconsole/courses/getAuthenticateGradeName
	@RequestMapping(MappingURL.GET_COURSES_INFO_BUY_AUTHNAME)
	@ResponseBody
	public Map<String,String> getAuthenticateGradeName(Model model,LearnTypesEntity learnTypesEntity) {
		System.out.println("----------------m-1:"+learnTypesEntity.getId());
		LearnTypesEntity learnTypes = coursesService.getById(learnTypesEntity.getId());
		System.out.println("----------------m-2:"+learnTypes);
		System.out.println("----------------m-2:"+learnTypes.getAuthenticateGrade());
		Map<String, String> map = new HashMap<String, String>();
		map.put("authenticateGrade", DictionariesUtil.getAuthenticateGradeName(learnTypes.getAuthenticateGrade()));
		return map;
	}




}








