package com.jiacer.modules.business.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.jiacer.modules.business.bean.DictionariesUtil;
import com.jiacer.modules.business.service.ExamsService;
import com.jiacer.modules.business.service.SchoolsService;
import com.jiacer.modules.business.utils.CoursesUtils;
import com.jiacer.modules.business.validate.ExamsClassValidate;
import com.jiacer.modules.common.controller.BaseController;
import com.jiacer.modules.common.page.Page;
import com.jiacer.modules.common.utils.JsonResult;
import com.jiacer.modules.common.utils.StringUtils;
import com.jiacer.modules.log.Log;
import com.jiacer.modules.mybatis.dao.ExamClassMapper;
import com.jiacer.modules.mybatis.dao.LearnTypesMapper;
import com.jiacer.modules.mybatis.dao.SchoolsMapper;
import com.jiacer.modules.mybatis.entity.ApplyOrdersEntity;
import com.jiacer.modules.mybatis.entity.ControlEntity;
import com.jiacer.modules.mybatis.entity.CoursePackageEntity;
import com.jiacer.modules.mybatis.entity.ExamClassEntity;
import com.jiacer.modules.mybatis.entity.LearnTypesEntity;
import com.jiacer.modules.mybatis.entity.SchoolsEntity;
import com.jiacer.modules.mybatis.entity.extend.LearnRecordEntity;
import com.jiacer.modules.system.config.DBStatus;
import com.jiacer.modules.system.config.MappingURL;
import com.jiacer.modules.system.config.Message;
import com.jiacer.modules.system.config.SysConstants;
import com.jiacer.modules.system.utils.UserUtils;

/**
 * @ClassName: ExamsController
 * @Description: 考试管理控制类包含
 * @author 贺章鹏
 * @date 2016年10月19日 下午12:50:49
 * 
 */
@Controller
@RequestMapping(MappingURL.EXAMS_URL)
public class ExamsClassController extends BaseController {

	@Resource
	ExamsService examsService;
	
	@Autowired
	ExamClassMapper examClassDao;

	
	@Resource
	SchoolsService schoolsService;
	
	@Resource
	LearnTypesMapper coursesDao;
	
	@Resource
	SchoolsMapper SchoolsDao;
	
	@Resource
	LearnTypesMapper learnTypesDao;
	
	

	/**
	 * 获取班级对象
	 * 
	 * @param id
	 * @return
	 */
	public ExamClassEntity getModel(Integer id) {
		if (id != null) {
			return examsService.getExamClassById(id);
		} else {
			return new ExamClassEntity();
		}
	}

	// 新增修改页面
	@RequestMapping(MappingURL.FORM_URL)
	public String form(Model model, Integer id) {

		ExamClassEntity examClassEntity = examsService.getExamClassInfo(id);

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
		
		SchoolsEntity schoolsEntity = new SchoolsEntity(); 
		schoolsEntity.setInstitutionInfoId(UserUtils.getUser().getInstitutionInfoId());
		List<SchoolsEntity> schoolsList = SchoolsDao.findAllList(schoolsEntity);
		model.addAttribute("schoolsList", schoolsList);
		
		if (examClassEntity == null || examClassEntity.getId() == null) {
			model.addAttribute("update", Boolean.FALSE);
			model.addAttribute("model", null);
			return "modules/exams/form";
		} else {
			model.addAttribute("update", Boolean.TRUE);
			SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd"); 
			if(examClassEntity.getTheoryDate() != null){
				model.addAttribute("theoryDate", time.format(examClassEntity.getTheoryDate()));	
			}else{
				model.addAttribute("theoryDate", "");
			}
			if(examClassEntity.getOperationDate() != null){
				model.addAttribute("operationDate", time.format(examClassEntity.getOperationDate()));	
			}else{
				model.addAttribute("operationDate", "");
			}
			/**
			List<ApplyOrdersEntity> list = examClassEntity.getApplyOrders();
			for(int i = 0; i < list.size(); i++){
				ApplyOrdersEntity applyOrdersEntity = list.get(i);
				System.out.println(applyOrdersEntity.getUserBaseInfo().getUserName());
			}
			**/
			LearnTypesEntity lte = learnTypesDao.getById(examClassEntity.getCourseId());
			List<ControlEntity> appCouOldNameList = new ArrayList<ControlEntity>();
			appCouOldNameList.add(new ControlEntity(lte.getId()+"",lte.getTypeName()));
			model.addAttribute("authenticateGrade", DictionariesUtil.getAuthenticateGradeName(lte.getAuthenticateGrade()));
			model.addAttribute("appCouOldNameList", appCouOldNameList);
			model.addAttribute("model", examClassEntity);
			return "modules/exams/updateForm";
		}
		
	}

	// 列表页面
	@RequestMapping(MappingURL.LIST_URL)
	public String list(Model model) {
		//课程
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
		
		return "modules/exams/list";
	}

	// 查询
	@RequestMapping(MappingURL.QUERY_URL)
	@ResponseBody
	public Page<ExamClassEntity> page(
			@RequestParam(value = "page", defaultValue = SysConstants.DEFAULT_PAGE_NO) int pageNumber,
			@RequestParam(value = "records", defaultValue = SysConstants.DEFAULT_PAGE_SIZE) int pageSize, Model model,
			ExamClassEntity examClassEntity) {
		Page<ExamClassEntity> reslut = examsService.getExamClassPage(examClassEntity, pageNumber, pageSize);
		return reslut;
	}

	// 新增
	@RequestMapping(MappingURL.ADD_URL)
	@ResponseBody
	public JsonResult add(Model model, ExamClassEntity examClassEntity) {
		if (examClassEntity == null) {
			logger.info(Message.buildErrInfo(Message.PARAM_ERROR_EXCEPTION, "班级管理对象为null"));
			return new JsonResult(false, Message.PARAM_ERROR_MSG, null);
		}

		JsonResult validate = ExamsClassValidate.addValidate(examClassEntity,examClassDao,learnTypesDao);

		if (!validate.isSuccess()) {
			return validate;
		}

		JsonResult jsonResult = null;

		try {
			examsService.addExamClass(examClassEntity);
			jsonResult = new JsonResult(true, Message.SUCCESS_MSG, null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(Message.buildErrInfo(Message.ERROR_EXCEPTION, e));
			jsonResult = new JsonResult(false, Message.FAILED_MSG, null);
		}
		return jsonResult;
	}
   //返回
	@RequestMapping(MappingURL.GO_BACK)
	@ResponseBody
	public JsonResult goback() {


		JsonResult validate = new JsonResult();

		validate.setSuccess(true);
		return validate;
	}

	// 修改
	@RequestMapping(MappingURL.MODIFY_URL)
	@ResponseBody
	public JsonResult modify(Model model, ExamClassEntity examClassEntity) {
		if (examClassEntity == null || examClassEntity.getId() == null) {
			logger.info(Message.buildErrInfo(Message.PARAM_ERROR_EXCEPTION, "班级管理对象为null或班级管理对象id为空"));
			return new JsonResult(false, Message.PARAM_ERROR_MSG, null);
		}

		JsonResult validate = ExamsClassValidate.modifyValidate(examClassEntity,examsService);

		if (!validate.isSuccess()) {
			return validate;
		}

		JsonResult jsonResult = null;

		try {
			examsService.modifyExamClass(examClassEntity);
			jsonResult = new JsonResult(true, Message.SUCCESS_MSG, null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(Message.buildErrInfo(Message.ERROR_EXCEPTION, e));
			jsonResult = new JsonResult(false, Message.FAILED_MSG, null);
		}
		return jsonResult;
	}

	// 删除
	@RequestMapping(MappingURL.DELETE_URL)
	@ResponseBody
	public JsonResult delete(Model model, ExamClassEntity examClassEntity) {
		if (examClassEntity == null || examClassEntity.getId() == null) {
			logger.info(Message.buildErrInfo(Message.PARAM_ERROR_EXCEPTION, "班级管理对象为null或班级管理对象id为空"));
			return new JsonResult(false, Message.PARAM_ERROR_MSG, null);
		}

		JsonResult jsonResult = null;

		try {
			examsService.delExamClass(examClassEntity);
			jsonResult = new JsonResult(true, Message.SUCCESS_MSG, null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(Message.buildErrInfo(Message.ERROR_EXCEPTION, e));
			jsonResult = new JsonResult(false, Message.FAILED_MSG, null);
		}
		return jsonResult;
	}

	@RequestMapping(MappingURL.EXPORT)
	public String export(Model model, HttpServletResponse response, HttpServletRequest request,
			@PathVariable String type, Integer id) {

		if (StringUtils.isEmpty(type) || id == null) {
			model.addAttribute("message", "参数错误");
		} else {
			examsService.dealExport(model, id, response, request);
		}
		return "modules/excel/info";
	}

	// 班级学习成绩
	@RequestMapping(MappingURL.LEARN_URL + MappingURL.FORM_URL)
	public String recordForm(Model model, Integer id) {
		List<LearnRecordEntity> list = examsService.getExamLearnRecords(id);
		model.addAttribute("model", list);
		model.addAttribute("classId", id);

		return "modules/exams/recordForm";
	}

	@RequestMapping(MappingURL.LEARN_URL + MappingURL.EXPORT)
	public String exportExamLearnRecords(Model model, HttpServletResponse response, HttpServletRequest request,
			@PathVariable String type, Integer id) {

		if (StringUtils.isEmpty(type) || id == null) {
			model.addAttribute("message", "参数错误");
		} else {
			examsService.exportExamLearnRecords(id, model, response, request);
		}
		return "modules/excel/info";
	}
	
	@RequestMapping(MappingURL.GET_EXAMS_LEARNTYPELIST)
	@ResponseBody
	public Map<String,String> getExamsLearnTypeList(Model model,ExamClassEntity examClassEntity) {
		Map<String,String> map = new HashMap<String,String>();
		ExamClassEntity examClassOpt = new ExamClassEntity();
		examClassOpt.setInstitutionInfoId(UserUtils.getUser().getInstitutionInfoId());
		examClassOpt.setLearnTypesId(examClassEntity.getLearnTypesId());
		examClassOpt.setExamStatus("01");
		List<ExamClassEntity> examList = examClassDao.findAllList(examClassOpt);
		for(int i = 0; i < examList.size(); i++){
			ExamClassEntity examClass = examList.get(i);
			map.put(examClass.getId()+"", examClass.getClassNumber());
		}
		return map;
	}
	
	@RequestMapping(MappingURL.GET_EXAMS_AVAILABLE)
	@ResponseBody
	public JsonResult getIsExamsAvailable(Model model,ExamClassEntity examClassEntity) {
		Map map = new HashMap();
		map.put("courseId", examClassEntity.getCourseId());
		map.put("institutionInfoId", UserUtils.getUser().getInstitutionInfoId());
		List<ExamClassEntity> courseClassList = examsService.getCourseIdExamClass(map);
		ExamClassEntity examClass = examsService.getById(examClassEntity.getId());
		if(examClass.getExamStatus().equals("02")){
			return new JsonResult(false, "班级已申报，无法更换班级标号", courseClassList);
		}
		if(examClass.getExamStatus().equals("04")){
			return new JsonResult(false, "班级已结清，无法更换班级标号", courseClassList);
		}
		return new JsonResult(true, "", courseClassList);
	}
}


















