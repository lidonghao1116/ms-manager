package com.jiacer.modules.business.controller;

import com.jiacer.modules.business.bean.form.InstitutionQuery;
import com.jiacer.modules.business.service.SchoolsService;
import com.jiacer.modules.business.validate.CoursePackageValidate;
import com.jiacer.modules.business.validate.IntroValidate;
import com.jiacer.modules.common.controller.BaseController;
import com.jiacer.modules.common.utils.JsonResult;
import com.jiacer.modules.mybatis.entity.SysUsersEntity;
import com.jiacer.modules.mybatis.model.InstitutionInfo;
import com.jiacer.modules.system.config.MappingURL;
import com.jiacer.modules.system.config.Message;
import com.jiacer.modules.system.utils.UserUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(MappingURL.INTRO)
public class IntroController extends BaseController{


    @Resource
    SchoolsService schoolsService;

    /**
     *
     * @MethodName:getSchoolsIntroduction
     * @Type:CoursePackageController
     * @Description:运营管理--销售管理--学校简介页面
     * @Return:String
     * @Param:@param request
     * @Param:@param model
     * @Param:@return
     * @Thrown:
     * @Date:Oct 6, 2017 10:54:27 PM
     */
    @RequestMapping(value=MappingURL.GET_SCHOOL_INTRODUCE,method={RequestMethod.GET,RequestMethod.POST})
    public String getSchoolsIntroduction(HttpServletRequest request,Model model){
        SysUsersEntity sysUser = UserUtils.getUser();//获取登录用户信息
        try {
            InstitutionInfo InstitutionInfo = schoolsService.getSchoolIntroduce(sysUser.getInstitutionInfoId());
            model.addAttribute("institution", InstitutionInfo);
            return "modules/schoolIntro/form";
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(Message.QUERY_ERROR_EXCEPTION,e);
            return "error/404";
        }
    }

    /**
     *
     * @MethodName:updateInstitutionInfo
     * @Type:CoursePackageController
     * @Description:修改学校简介
     * @Return:JsonResult
     * @Param:@param request
     * @Param:@param institutionQuery
     * @Param:@return
     * @Thrown:
     * @Date:Oct 9, 2017 2:32:14 PM
     */
    @RequestMapping(value= MappingURL.UPDATE_INSTITUTION,method={RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public JsonResult updateInstitutionInfo(HttpServletRequest request, InstitutionQuery institutionQuery) {
        if(institutionQuery== null){
            logger.info(Message.buildErrInfo(Message.PARAM_ERROR_EXCEPTION, "学校简介内容为null"));
            return new JsonResult(false,Message.PARAM_ERROR_MSG,null);
        }

        JsonResult validate= IntroValidate.UpdateInstitionValidate(institutionQuery);

        if(!validate.isSuccess()){
            return validate;
        }

        JsonResult jsonResult=null;

        try {
            int result =schoolsService.updateInstitution(institutionQuery);
            if (result <=0) {
                return jsonResult=new JsonResult(false,Message.FAILED_MSG,null);
            }
            jsonResult=new JsonResult(true,Message.SUCCESS_MSG,null);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(Message.buildErrInfo(Message.ERROR_EXCEPTION,e));
            jsonResult=new JsonResult(false,Message.FAILED_MSG,null);
        }
        return jsonResult;
    }
}
