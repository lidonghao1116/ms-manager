package com.jiacer.modules.business.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.common.collect.Lists;
import com.jiacer.modules.business.utils.CfgParamUtils;
import com.jiacer.modules.business.utils.CoursesUtils;
import com.jiacer.modules.business.utils.ExamsUtils;
import com.jiacer.modules.business.utils.PartnerUtils;
import com.jiacer.modules.business.utils.ProductsUtils;
import com.jiacer.modules.business.utils.SchoolsUtils;
import com.jiacer.modules.common.controller.BaseController;
import com.jiacer.modules.common.utils.StringUtils;
import com.jiacer.modules.log.Log;
import com.jiacer.modules.mybatis.entity.CoursePackageEntity;
import com.jiacer.modules.mybatis.entity.ExamClassEntity;
import com.jiacer.modules.mybatis.entity.LearnTypesEntity;
import com.jiacer.modules.mybatis.entity.PartnersEntity;
import com.jiacer.modules.mybatis.entity.SchoolsEntity;
import com.jiacer.modules.mybatis.model.CfgParam;
import com.jiacer.modules.system.config.MappingURL;
import com.jiacer.modules.system.utils.UserUtils;

/** 
* @ClassName: BusinessParamsController 
* @Description: 系统参数下拉框参数：课程，课程销售、学校
* @author 贺章鹏
* @date 2016年11月4日 下午3:54:46 
*  
*/
@Controller
@RequestMapping(MappingURL.PARAMS_URL)
public class BusinessParamsController extends BaseController{

	/**
	 * 
	 * @MethodName:initCourses
	 * @Type:BusinessParamsController
	 * @Description:课程管理--课程名称
	 * @Return:List<LearnTypesEntity>
	 * @Param:@param values
	 * @Param:@return
	 * @Thrown:
	 * @Date:Oct 7, 2017 11:08:13 AM
	 */
	@RequestMapping(value = MappingURL.COURSES_URL,method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<LearnTypesEntity> initCourses(String values) {
		List<LearnTypesEntity> list = Lists.newArrayList();
		try {
			if(StringUtils.isEmpty(values)){
				list=CoursesUtils.init();
			}else{
				list=CoursesUtils.getCourses(values);
			}
		} catch (Exception e) {
			Log.error("获取课程列表失败");
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 
	 * @MethodName:initCfgParam
	 * @Type:BusinessParamsController
	 * @Description:课程管理--鉴定等级
	 * @Return:List<CfgParam>
	 * @Param:@return
	 * @Thrown:
	 * @Date:Sep 28, 2017 3:45:50 PM
	 */
	@RequestMapping(value=MappingURL.CFG_PARAMS_GRADE_URL,method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<CfgParam>  initCfgParamOfGrade(){
		List<CfgParam> list = new ArrayList<>();
		try {
			list=CfgParamUtils.getCfgParamofGrade();
		} catch (Exception e) {
			e.printStackTrace();
			Log.error("获取鉴定等级信息失败");
		}
		return list;
	}
	
	/**
	 * 
	 * @MethodName:initCfgParam
	 * @Type:BusinessParamsController
	 * @Description:课程管理--课程状态
	 * @Return:List<CfgParam>
	 * @Param:@return
	 * @Thrown:
	 * @Date:Sep 28, 2017 3:45:50 PM
	 */
	@RequestMapping(value=MappingURL.CFG_PARAMS_STATUS_URL,method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<CfgParam>  initCfgParamOfStatus(){
		List<CfgParam> list = new ArrayList<>();
		try {
			list=CfgParamUtils.getCfgParamOfStatus();
		} catch (Exception e) {
			e.printStackTrace();
			Log.error("获取鉴定等级信息失败");
		}
		return list;
	}
	
	
	@RequestMapping(value = MappingURL.COURSES_PACKAGE_URL)
	@ResponseBody
	public List<CoursePackageEntity> initProducts() {
		List<CoursePackageEntity> list = Lists.newArrayList();
		try {
			list=ProductsUtils.init();
		} catch (Exception e) {
			Log.error("获取销售列表失败");
			e.printStackTrace();
		}
		return list;
	}
	
	@RequestMapping(value = MappingURL.SCHOOLS_URL)
	@ResponseBody
	public List<SchoolsEntity> initSchools() {
		List<SchoolsEntity> list = Lists.newArrayList();
		try {
			list=SchoolsUtils.init();
		} catch (Exception e) {
			Log.error("获取销售列表失败");
			e.printStackTrace();
		}
		return list;
	}
	
	@RequestMapping(value = MappingURL.EXAMS_URL)
	@ResponseBody
	public List<ExamClassEntity> initClass(HttpServletRequest request, HttpServletResponse response, Model model) {
		String courseId = request.getParameter("courseId");
		List<ExamClassEntity> list = Lists.newArrayList();
		try {
			list=ExamsUtils.init(Integer.valueOf(courseId));
		} catch (Exception e) {
			Log.error("获取班级列表失败");
			e.printStackTrace();
		}
		return list;
	}
	//xiehui已报名新增查询班级标号
		@RequestMapping(value = MappingURL.CLASSNUMBER_URL)
		@ResponseBody
		public List<ExamClassEntity> initClass(HttpServletRequest request, HttpServletResponse response, Model model,String courseId) {
			List<ExamClassEntity> list = Lists.newArrayList();
			try {
				list=ExamsUtils.init(Integer.valueOf(courseId));
			} catch (Exception e) {
				Log.error("获取班级列表失败");
				e.printStackTrace();
			}
			return list;
		}
	@RequestMapping(value = MappingURL.PARTNERS_URL)
	@ResponseBody
	public List<PartnersEntity> initPartners(String type) {
		List<PartnersEntity> list = Lists.newArrayList();
		try {
			list=PartnerUtils.getPartners(type,UserUtils.getUser().getInstitutionInfoId());
		} catch (Exception e) {
			Log.error("获取合作商列表失败");
			e.printStackTrace();
		}
		return list;
	}
}
