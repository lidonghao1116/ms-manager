package com.jiacer.modules.business.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jiacer.modules.business.validate.BaseValidate;
import com.jiacer.modules.mybatis.entity.*;
import com.jiacer.modules.mybatis.model.UserBaseInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.jiacer.modules.business.bean.UserBean;
import com.jiacer.modules.business.bean.form.UsersQuery;
import com.jiacer.modules.business.service.CourseInfoService;
import com.jiacer.modules.business.service.StuUserInfoService;
import com.jiacer.modules.business.service.UsersService;
import com.jiacer.modules.business.validate.UsersValidate;
import com.jiacer.modules.common.controller.BaseController;
import com.jiacer.modules.common.page.Page;
import com.jiacer.modules.common.utils.JsonResult;
import com.jiacer.modules.common.utils.StringUtils;
import com.jiacer.modules.mybatis.dao.CourseInfoBuyRecordDao;
import com.jiacer.modules.mybatis.dao.StuUserInfoDao;
import com.jiacer.modules.system.config.MappingURL;
import com.jiacer.modules.system.config.Message;
import com.jiacer.modules.system.config.SysConstants;
import com.jiacer.modules.system.utils.UserUtils;

/**
 * @author 贺章鹏
 * @ClassName: UsersController
 * @Description: 学员管理控制类
 * @date 2016年10月19日 下午12:48:07
 */
@Controller
@RequestMapping(MappingURL.USER_URL)
public class UsersController extends BaseController {

    @Resource
    UsersService usersService;

    @Resource
    StuUserInfoService stuUserInfoService;

    @Resource
    CourseInfoService courseInfoService;

    @Resource
    CourseInfoBuyRecordDao courseInfoBuyRecordDao;

    @Resource
    StuUserInfoDao stuUserInfoDao;

    //列表页面
    @RequestMapping(MappingURL.LIST_URL)
    public String list(Model model) {
        return "modules/users/list";
    }

    @RequestMapping(MappingURL.QUERY_URL)
    @ResponseBody
    public Page<StuUserInfoEntity> page(
            @RequestParam(value = "page", defaultValue = SysConstants.DEFAULT_PAGE_NO) int pageNumber,
            @RequestParam(value = "records", defaultValue = SysConstants.DEFAULT_PAGE_SIZE) int pageSize,
            Model model, StuUserInfoEntity usersQuery) {
        Page<StuUserInfoEntity> reslut = stuUserInfoService.getUsersPage(usersQuery, pageNumber, pageSize);
        return reslut;
    }


    //新增修改页面
    @RequestMapping(MappingURL.INFO_URL)
    public String info(Model model, Integer id) {

        UserBean user = new UserBean();
        if (id == null) {
            logger.info(Message.buildErrInfo(Message.PARAM_ERROR_EXCEPTION, "参数id为null"));
        } else {
            user = usersService.getUsersInfo(id);
        }
        model.addAttribute("model", user);
        return "modules/users/detail";
    }

    //新增修改页面
    @RequestMapping(MappingURL.FORM_URL)
    public String form(Model model, Integer id) {
        if (id == null) {
            logger.info(Message.buildErrInfo(Message.PARAM_ERROR_EXCEPTION, "参数id为null"));
            return "erros/illegalRequest";
        } else {
            List<CourseInfoEntity> courseInfoList = courseInfoService.getAllCourseInfo(null);
            model.addAttribute("courseInfoList", courseInfoList);
            UserBean userList = usersService.getUsersInfo(id);
            model.addAttribute("modelList", userList);
            StuUserInfoEntity stuUserInfoOrderList = stuUserInfoService.getStuUserInfo(id);
            model.addAttribute("stuUserInfo", stuUserInfoOrderList);
            return "modules/users/form";
        }
    }

    //修改
    @RequestMapping(MappingURL.MODIFY_URL)
    @ResponseBody
    public JsonResult modify(Model model, @RequestBody UserBean userBean) {
        UserExtendInfoEntity userExtend = userBean.getUserExtend();
        JsonResult jsonResult = null;
        if (userBean.getUserInfo().getId() != null) {
            System.out.println(userBean.getUserInfo().getId());
            StuUserInfoEntity stuUserInfo = stuUserInfoDao.getById(userBean.getUserInfo().getId());

            if (userExtend.getEducation() != null) {
                System.out.println(userExtend.getEducation());
                stuUserInfo.setEducation(userExtend.getEducation());
            }
            if (userExtend.getNation() != null) {
                stuUserInfo.setNation(userExtend.getNation());
            }
            if (userExtend.getAddress() != null) {
                stuUserInfo.setAddress(userExtend.getAddress());
            }
            if (userExtend.getContactAddress() != null) {
                stuUserInfo.setContactAddress(userExtend.getContactAddress());
            }

            if (userExtend.getContacts() != null) {
                stuUserInfo.setContacts(userExtend.getContacts());
            }
            if (userExtend.getContactPhone() != null) {
                stuUserInfo.setContactPhone(userExtend.getContactPhone());
            }
            try {
                stuUserInfoDao.update(stuUserInfo);
                jsonResult = new JsonResult(true, Message.SUCCESS_MSG, null);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error(Message.buildErrInfo(Message.ERROR_EXCEPTION, e));
                jsonResult = new JsonResult(false, Message.FAILED_MSG, null);
            }
        }
        return jsonResult;
    }

    // 导出
    @RequestMapping(MappingURL.EXPORT)
    public String export(Model model, HttpServletResponse response, HttpServletRequest request,
                         UsersQuery usersQuery) {
        usersService.dealExport(model, usersQuery, response, request);
        return "modules/excel/info";
    }

    //在线支付 http://localhost:8080/jiacerconsole/user/getCourseInfoBuyRecrd 购买-BOND
    @RequestMapping(MappingURL.GET_COURSES_INFO_BUY_RECORD)
    @ResponseBody
    public JsonResult getCourseInfoBuyRecrd(Model model, CourseInfoBuyRecordEntity courseInfoBuyRecord) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("institutionInfoId", UserUtils.getUser().getInstitutionInfoId());
        map.put("stuUserInfoId", courseInfoBuyRecord.getStuUserInfoId());
        map.put("userId", courseInfoBuyRecord.getUserId());
        map.put("courseId", courseInfoBuyRecord.getCourseId());

        int count = courseInfoBuyRecordDao.count(map);
        if (count > 0) {
            return new JsonResult(false, "失败，该学员已购买该课程", null);
        } else {
            courseInfoBuyRecordDao.insert(courseInfoBuyRecord);
            return new JsonResult(true, "购买成功", null);
        }
    }


    /**
     * 读取身份证信息判断
     * @return
     */
    @RequestMapping(value = MappingURL.READ_IDCARD_INFO, method = RequestMethod.POST)
    @ResponseBody
    public JsonResult readIdcard(UserBaseInfo userBaseInfo) {
        //1. 验证信息是否为空
        JsonResult js = UsersValidate.IdCardValidate(userBaseInfo);
        if(!js.isSuccess()){
            return js;
        }
        if(StringUtils.isEmpty(userBaseInfo.getMobile())){
            return new JsonResult(false,"请填写手机号码",null);
        }
        if(!BaseValidate.isMobile(userBaseInfo.getMobile())){
            return new JsonResult(false,"请填写正确的手机号码",null);
        }
        //2. 验证身份证或手机是否存在
        List<UserBaseInfoEntity> users = usersService.findUserByCertNoOrMobile(userBaseInfo.getCertNo(),userBaseInfo.getMobile());

        //3. 验证手机号与身份证是否匹配
        if(users == null || users.size() == 0){
            return new JsonResult( true , "success", Collections.singletonMap("sign",userBaseInfo.getSign()));
        }
        if(users.size() > 1){
            logger.info(String.format("身份证与手机号已绑定不同的用户「%s」「%s」",userBaseInfo.getCertNo(), userBaseInfo.getMobile()));
            return new JsonResult( false , "学员信息冲突，身份信息与预留手机号不一致", null);
        }
        UserBaseInfo ub = users.get(0);
        if(userBaseInfo.getCertNo().equalsIgnoreCase(ub.getCertNo()) && userBaseInfo.getMobile().equals(ub.getMobile())){
            return new JsonResult( true , "success", Collections.singletonMap("sign",userBaseInfo.getSign()));
        }
        if(StringUtils.isBlank(ub.getMobile()) || StringUtils.isBlank(ub.getCertNo())){
            return new JsonResult( true , "success", Collections.singletonMap("sign",userBaseInfo.getSign()));
        }
        logger.info(String.format("学员信息冲突「%s」「%s」",userBaseInfo.getCertNo(), userBaseInfo.getMobile()));
        return new JsonResult( false , "学员信息冲突，身份信息与预留手机号不一致", null);
    }

}

















