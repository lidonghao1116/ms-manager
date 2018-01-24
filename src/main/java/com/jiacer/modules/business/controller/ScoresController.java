package com.jiacer.modules.business.controller;

import com.google.common.collect.Lists;
import com.jiacer.modules.business.bean.DictionariesUtil;
import com.jiacer.modules.business.service.CoursesService;
import com.jiacer.modules.business.service.ExamsService;
import com.jiacer.modules.business.service.SchoolsService;
import com.jiacer.modules.common.controller.BaseController;
import com.jiacer.modules.common.page.Page;
import com.jiacer.modules.common.utils.JsonResult;
import com.jiacer.modules.common.utils.StringUtils;
import com.jiacer.modules.log.Log;
import com.jiacer.modules.mybatis.dao.ApplyOrdersMapper;
import com.jiacer.modules.mybatis.dao.LearnTypesMapper;
import com.jiacer.modules.mybatis.dao.SchoolsMapper;
import com.jiacer.modules.mybatis.entity.*;
import com.jiacer.modules.system.config.DBStatus;
import com.jiacer.modules.system.config.MappingURL;
import com.jiacer.modules.system.config.Message;
import com.jiacer.modules.system.config.SysConstants;
import com.jiacer.modules.system.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 
* @ClassName: ScoresController 
* @Description: 成绩管理控制类
* @author 贺章鹏
* @date 2016年10月19日 下午12:52:41 
*  
*/
@Controller
@RequestMapping(MappingURL.SCORES_URL)
public class ScoresController extends BaseController{

	
	@Resource
	ExamsService examsService;
	
	@Resource
	CoursesService coursesService;
	
	@Resource
	SchoolsService schoolsService;
	
	@Autowired
	ApplyOrdersMapper applyOrdersDao;
	
	@Resource
	LearnTypesMapper coursesDao;
	
	@Resource
	SchoolsMapper SchoolsDao;
	
	//修改页面
	@RequestMapping(MappingURL.FORM_URL)
	public String form(Model model,Integer id) {
		ExamClassEntity examScoresBean=examsService.getExamScores(id);
		LearnTypesEntity learnTypesEntity = coursesService.getById(examScoresBean.getCourseId());
		if(learnTypesEntity.getCertName() != null){
			model.addAttribute("certName", learnTypesEntity.getCertName());
		}
		SchoolsEntity schoolsEntity = schoolsService.getSchoolsById(examScoresBean.getShoolId());
		if(schoolsEntity.getSchoolName() != null){
			model.addAttribute("schoolsName", schoolsEntity.getSchoolName());	
		}
		if(examScoresBean.getExamForm() != null){
			Map map = DictionariesUtil.getExamTypeMap();
			model.addAttribute("examFormName", map.get(examScoresBean.getExamForm()));
		}
		
		model.addAttribute("authenticateGrade", DictionariesUtil.getAuthenticateGradeName(learnTypesEntity.getAuthenticateGrade()));
		model.addAttribute("courseName", learnTypesEntity.getTypeName());
		model.addAttribute("update", Boolean.TRUE);
		model.addAttribute("model", examScoresBean);
	    return "modules/scores/form";
	}
	
	//列表页面
	@RequestMapping(MappingURL.LIST_URL)
	public String list(Model model) {
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
		model.addAttribute("learnTypeList", result);
		
		//学校
		SchoolsEntity schoolsEntity = new SchoolsEntity(); 
		schoolsEntity.setInstitutionInfoId(UserUtils.getUser().getInstitutionInfoId());
		List<SchoolsEntity> schoolsList = SchoolsDao.findAllList(schoolsEntity);
		model.addAttribute("schoolsList", schoolsList);	
		
	    return "modules/scores/list";
	}
	
	//查询
	@RequestMapping(MappingURL.QUERY_URL)
	@ResponseBody
	public Page<ExamClassEntity> page(	
			@RequestParam(value = "page", defaultValue = SysConstants.DEFAULT_PAGE_NO) int pageNumber,
			@RequestParam(value = "records", defaultValue = SysConstants.DEFAULT_PAGE_SIZE) int pageSize,
			Model model,ExamClassEntity examClassEntity) {
		examClassEntity.setExamStatus(null);
		List<String> examStatusList=Lists.newArrayList();
		examStatusList.add(DBStatus.ExamStatus.CLEARING);
		examStatusList.add(DBStatus.ExamStatus.DECLARED);
		examClassEntity.setExamStatusList(examStatusList);
		Page<ExamClassEntity> reslut=examsService.getExamScoresPage(examClassEntity, pageNumber, pageSize);
	    return reslut;
	}
	
	//新增或修改
	@RequestMapping(MappingURL.ADD_URL)
	@ResponseBody
	public JsonResult add(Model model,UserScoresEntity userScoresEntity,Integer orderId) {
		if(userScoresEntity == null || userScoresEntity.getStuUserInfoId()==null || userScoresEntity.getClassId()==null){
			logger.info(Message.buildErrInfo(Message.PARAM_ERROR_EXCEPTION, "学员成绩对象为null或者用户id为空或者班级id为空"));
			return new JsonResult(false,Message.PARAM_ERROR_MSG,null);
		}
		JsonResult jsonResult=null;
		try {
			//验证学员是否补考过
			Map map = new HashMap();
			map.put("classId", userScoresEntity.getClassId());
			map.put("stuUserInfoId", userScoresEntity.getStuUserInfoId());
			map.put("handleStatus", "02");
			map.put("institutionInfoId", com.jiacer.modules.system.utils.UserUtils.getUser().getInstitutionInfoId());//
			ApplyOrdersEntity applyOrdersEntity = applyOrdersDao.getUserIdClassIdApplyOrders(map);
			map.put("courseId",applyOrdersEntity.getCourseId());
			map.put("formType","02");
			
			if(userScoresEntity.getIsExam().equals("1")){
				if(applyOrdersDao.getIsBk(map) > 0 ){
					return new JsonResult(false,"此课程已补考不能重复提交",null);
				}
			}
			examsService.addUserScores(userScoresEntity,orderId);
			jsonResult=new JsonResult(true,Message.SUCCESS_MSG,null);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(Message.buildErrInfo(Message.ERROR_EXCEPTION,e));
			jsonResult=new JsonResult(false,Message.FAILED_MSG,null);
		}
		return jsonResult;
	}
	
	//详细页面
	@RequestMapping(MappingURL.INFO_URL)
	@ResponseBody
	public JsonResult info(Model model,UserScoresEntity userScoresEntity) {
		if(userScoresEntity == null || userScoresEntity.getStuUserInfoId()==null || userScoresEntity.getClassId()==null){
			logger.info(Message.buildErrInfo(Message.PARAM_ERROR_EXCEPTION, "学员成绩对象为null或者用户id为空或者班级id为空"));
			return new JsonResult(false,Message.PARAM_ERROR_MSG,null);
		}
		JsonResult jsonResult=null;
		try {
			UserScoresEntity userScores=examsService.getUserScores(userScoresEntity);
			jsonResult=new JsonResult(true,Message.SUCCESS_MSG,userScores);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(Message.buildErrInfo(Message.ERROR_EXCEPTION,e));
			jsonResult=new JsonResult(false,Message.FAILED_MSG,null);
		}
		return jsonResult;
	}
	
	@RequestMapping(MappingURL.EXPORT)
	public String export(Model model,HttpServletResponse response,HttpServletRequest request,@PathVariable String type
			,Integer id) {
		
		if(StringUtils.isEmpty(type) || id==null){
			model.addAttribute("message", "参数错误");
		}else{
			examsService.dealExportScores(model,id,response,request);
		}
	    return "modules/excel/info";
	}
	
}
