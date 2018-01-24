package com.jiacer.modules.business.controller;

import com.google.common.collect.Lists;
import com.jiacer.modules.business.bean.DictionariesUtil;
import com.jiacer.modules.business.bean.HandleStatusType;
import com.jiacer.modules.business.bean.OptType;
import com.jiacer.modules.business.service.*;
import com.jiacer.modules.business.utils.IdCardInfoUtils;
import com.jiacer.modules.business.validate.ApplyOrdersValidate;
import com.jiacer.modules.common.controller.BaseController;
import com.jiacer.modules.common.page.Page;
import com.jiacer.modules.common.utils.JsonResult;
import com.jiacer.modules.common.utils.StringUtils;
import com.jiacer.modules.log.Log;
import com.jiacer.modules.mybatis.dao.*;
import com.jiacer.modules.mybatis.entity.*;
import com.jiacer.modules.system.config.DBStatus;
import com.jiacer.modules.system.config.MappingURL;
import com.jiacer.modules.system.config.Message;
import com.jiacer.modules.system.config.SysConstants;
import com.jiacer.modules.system.utils.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 贺章鹏
 * @ClassName: ApplyOrdersController
 * @Description: 学员申请订单管理
 * @date 2016年10月19日 下午12:49:23
 */
@Controller
@RequestMapping(MappingURL.ORDERS_URL)
public class ApplyOrdersController extends BaseController {

    @Autowired
    ApplyOrdersService applyOrdersService;

    @Autowired
    ExamClassMapper examClassMapper;

    @Autowired
    ApplyOrdersMapper applyOrdersMapper;

    @Resource
    ExamsService examsService;

    @Resource
    CoursesService coursesService;

    @Resource
    CoursePackageService coursePackageService;

    @Autowired
    UserExtendInfoMapper userExtendInfoDao;

    @Resource
    LearnTypesMapper coursesDao;

    @Resource
    CoursePackageMapper coursePackageDao;

    @Resource
    StuUserInfoService stuUserInfoService;

    @Resource
    StuUserInfoDao stuUserInfoDao;

    @Resource
    CfgParamMapper cfgParamDao;

    @Resource
    CoursesTimeDao coursesTimeDao;

    @Resource
    PartnersMapper partnersDao;

    @Autowired
    ApplyOrdersMapper applyOrdersDao;

    @Autowired
    UserBaseInfoMapper userBaseInfoDao;

    @Autowired
    ExamClassMapper examClassDao;

    @Autowired
    SmsService smsService;


    private final static Logger log = LoggerFactory.getLogger(ApplyOrdersController.class);

    // 列表页面
    @RequestMapping(MappingURL.LIST_URL)
    public String list(Model model, String type) {

        //课程
        List<LearnTypesEntity> result = Lists.newArrayList();
        LearnTypesEntity entity = new LearnTypesEntity();
        entity.setIsUsable(DBStatus.IsUsable.TRUE);//基础参数类型
        entity.setStatus(DBStatus.CourseStatus.NOMAL);
        entity.setInstitutionInfoId(UserUtils.getUser().getInstitutionInfoId());
        try {
            result = coursesDao.findAllList(entity);
        } catch (Exception e) {
            log.error("获取课程数据失败");
        }
        model.addAttribute("learnTypeList", result);

        List<CoursePackageEntity> result1 = Lists.newArrayList();
        CoursePackageEntity entity1 = new CoursePackageEntity();
        entity1.setIsUsable(DBStatus.IsUsable.TRUE);//基础参数类型
        entity1.setStatus(DBStatus.CourseStatus.NOMAL);
        entity1.setInstitutionInfoId(UserUtils.getUser().getInstitutionInfoId());
        try {
            result1 = coursePackageDao.findAllList(entity1);
        } catch (Exception e) {
            log.error("获取课程数据失败");
        }
        model.addAttribute("coursePackageList", result1);

        if (HandleStatusType.PEND_APPLY.getValue().equals(type)) {
            return "modules/applys/1/list";
        }
        if (HandleStatusType.REP_APPLY.getValue().equals(type)) {
            return "modules/applys/1/list_002";
        }
        if (HandleStatusType.NOT_MATCH.getValue().equals(type)) {
            return "modules/applys/2/list_003";
        }
        if (HandleStatusType.SUCCESS_APPLY.getValue().equals(type)) {
            model.addAttribute("orderStatusList", DictionariesUtil.getorderFromTypeList());
            //报名管理-已处理-已报名 list
            return "modules/applys/2/list";
        }
        if (HandleStatusType.OUT_APPLY.getValue().equals(type)) {
            return "modules/applys/2/list_out";
        }
        return "modules/applys/1/list";
    }

    // 查询
    @RequestMapping(MappingURL.QUERY_URL)
    @ResponseBody
    public Page<ApplyOrdersEntity> page(
            @RequestParam(value = "page", defaultValue = SysConstants.DEFAULT_PAGE_NO) int pageNumber,
            @RequestParam(value = "records", defaultValue = SysConstants.DEFAULT_PAGE_SIZE) int pageSize, Model model,
            ApplyOrdersEntity applyOrdersEntity, String type) {
        Page<ApplyOrdersEntity> reslut = applyOrdersService.getApplyOrdersPage(applyOrdersEntity, type, pageNumber,
                pageSize);
        return reslut;
    }

    @RequestMapping(MappingURL.QUERYSTU_URL)
    @ResponseBody
    public Page<ApplyOrdersEntity> pageStu(
            @RequestParam(value = "page", defaultValue = SysConstants.DEFAULT_PAGE_NO) int pageNumber,
            @RequestParam(value = "records", defaultValue = SysConstants.DEFAULT_PAGE_SIZE) int pageSize, Model model,
            ApplyOrdersEntity applyOrdersEntity, String type) {
        Page<ApplyOrdersEntity> reslut = applyOrdersService.getApplyOrdersStuUserPage(applyOrdersEntity, type, pageNumber,
                pageSize);
        return reslut;
    }

    // 新增订单／学员
    @RequestMapping(MappingURL.ADD_URL)
    @ResponseBody
    public JsonResult add(@RequestBody ApplyOrdersEntity applyOrdersEntity) {
        if (applyOrdersEntity == null) {
            logger.info(Message.buildErrInfo(Message.PARAM_ERROR_EXCEPTION, "报名订单为空"));
            return new JsonResult(false, Message.PARAM_ERROR_MSG, null);
        }

        if (StringUtils.isBlank(applyOrdersEntity.getSign()) || !applyOrdersEntity.getSign().equals(applyOrdersEntity.getValidSign())) {
            logger.info(Message.buildErrInfo(Message.PARAM_ERROR_EXCEPTION, "身份证签名校验失败，请重新读取身份证"));
            return new JsonResult(false, "请读取身份证信息", "身份证签名校验失败，请重新读取身份证");
        }

       
  
         boolean rlt = smsService.checkCode(applyOrdersEntity.getMobile(), applyOrdersEntity.getCode());
        if (!rlt) {
            logger.info(Message.buildErrInfo(Message.PARAM_ERROR_EXCEPTION, "短信验证码校验失败，请确认收到的短信"));
            return new JsonResult(false, "短信验证码不正确", "短信验证码校验失败，请确认收到的短信");
        }
         

        JsonResult validate = ApplyOrdersValidate.addFormValidate(applyOrdersEntity);

        if (!validate.isSuccess()) {
            return validate;
        }

        JsonResult jsonResult = null;
        log.info("=============================="+applyOrdersEntity.getSex());
        try {
            return applyOrdersService.addApplyOrders(applyOrdersEntity);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(Message.buildErrInfo(Message.ERROR_EXCEPTION, e));
            jsonResult = new JsonResult(false, Message.FAILED_MSG, null);
        }
        return jsonResult;
    }


    /**
     * 报名审核通过
     *
     * @param applyOrdersEntity
     * @return
     * @throws Exception
     */
    @RequestMapping(value = MappingURL.ORDER_PASS_URL, method = RequestMethod.POST)
    @ResponseBody
    public JsonResult orderConfirm(@RequestBody ApplyOrdersEntity applyOrdersEntity) throws Exception {
        if (applyOrdersEntity == null || applyOrdersEntity.getId() == null) {
            logger.info(Message.buildErrInfo(Message.PARAM_ERROR_EXCEPTION, "报名订单为空或者订单Id为空"));
            return new JsonResult(false, "请选择订单", "报名订单为空或者订单Id为空");
        }
        if (StringUtils.isBlank(applyOrdersEntity.getSign()) || !applyOrdersEntity.getSign().equals(applyOrdersEntity.getValidSign())) {
            logger.info(Message.buildErrInfo(Message.PARAM_ERROR_EXCEPTION, "身份证签名校验失败，请重新读取身份证"));
            return new JsonResult(false, "请读取身份证信息", "身份证签名校验失败，请重新读取身份证");
        }

        JsonResult validate = ApplyOrdersValidate.modifyValidate(applyOrdersEntity, OptType.PASS.getValue());
        if (!validate.isSuccess()) {
            return validate;
        }
        return applyOrdersService.handleApplyOrders(applyOrdersEntity);
    }

    /**
     * 报名审核不通过
     *
     * @param applyOrdersEntity
     * @return
     * @throws Exception
     */
    @RequestMapping(value = MappingURL.ORDER_UNPASS_URL, method = RequestMethod.POST)
    @ResponseBody
    public JsonResult orderReject(@RequestBody ApplyOrdersEntity applyOrdersEntity) throws Exception {
        if (applyOrdersEntity == null || applyOrdersEntity.getId() == null) {
            logger.info(Message.buildErrInfo(Message.PARAM_ERROR_EXCEPTION, "报名订单为空或者订单Id为空"));
            return new JsonResult(false, "请选择订单", "报名订单为空或者订单Id为空");
        }
        applyOrdersEntity.setOptType(OptType.UNPASS.getValue());
        return applyOrdersService.handleUnPassApplyOrders(applyOrdersEntity);
    }

    // 修改
    @RequestMapping(MappingURL.MODIFY_URL)
    @ResponseBody
    public JsonResult modify(@RequestBody ApplyOrdersEntity applyOrdersEntity) {
        if (applyOrdersEntity == null || applyOrdersEntity.getId() == null
                || StringUtils.isEmpty(applyOrdersEntity.getType())) {
            logger.info(Message.buildErrInfo(Message.PARAM_ERROR_EXCEPTION, "报名订单为空或者订单Id为空"));
            return new JsonResult(false, Message.PARAM_ERROR_MSG, null);
        }

        String type = applyOrdersEntity.getType();
        JsonResult jsonResult = null;
        try {
            if (HandleStatusType.SUCCESS_APPLY.getValue().equals(type)) {
                JsonResult validate = ApplyOrdersValidate.modifyValidate(applyOrdersEntity, type);
                if (!validate.isSuccess()) {
                    return validate;
                }
                return applyOrdersService.modifyApplyOrders(applyOrdersEntity, type);
            } else if (HandleStatusType.SPECIAL.getValue().equals(type)) {
                if (StringUtils.isEmpty(applyOrdersEntity.getOptType())) {
                    logger.info(Message.buildErrInfo(Message.PARAM_ERROR_EXCEPTION, "操作类型为空"));
                    return new JsonResult(false, Message.PARAM_ERROR_MSG, null);
                }
                if (OptType.PASS.getValue().equals(applyOrdersEntity.getOptType())) {
                    //报名管理-审核通过
                    JsonResult validate = ApplyOrdersValidate.modifyValidate(applyOrdersEntity, type);
                    if (!validate.isSuccess()) {
                        return validate;
                    }
                    return applyOrdersService.specialHandleApplyOrders(applyOrdersEntity);
                } else {
                    return applyOrdersService.handleUnPassApplyOrders(applyOrdersEntity);
                }
            } else if (HandleStatusType.RETREAT_FEE_APPLY.getValue().equals(type)) {
                ApplyOrdersEntity applyOrders = applyOrdersDao.getById(applyOrdersEntity.getId());
                ExamClassEntity examClass = examClassMapper.getById(applyOrders.getClassId());
                //02-已申报 04-已结清
                if (examClass.getExamStatus().equals("02") || examClass.getExamStatus().equals("04")) {
                    return new JsonResult(false, "班级已申报，无法退学", null);
                }
                return applyOrdersService.modifyApplyOrders(applyOrdersEntity, type);
            } else if (type.equals("REP_APPLY")) {
                //补考
                JsonResult validate = ApplyOrdersValidate.modifyValidate(applyOrdersEntity, type);
                if (!validate.isSuccess()) {
                    return validate;
                }
                return applyOrdersService.rehandleApplyOrders(applyOrdersEntity);
            }else{
                return new JsonResult(false, "参数错误，无对应操作", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(Message.buildErrInfo(Message.ERROR_EXCEPTION, e));
            jsonResult = new JsonResult(false, Message.FAILED_MSG, null);
        }
        return jsonResult;
    }


    // xiehui根据根据身份证号获取籍贯
    @RequestMapping(MappingURL.GET_PROVINCE)
    @ResponseBody
    public JsonResult getProvince(Model model, @RequestBody String certNo) {
        JsonResult jsonResult = null;
        if (certNo == null) {
            logger.info(Message.buildErrInfo(Message.PARAM_ERROR_EXCEPTION, "身份证号为空"));
            return new JsonResult(false, Message.PARAM_ERROR_MSG, null);
        }
        // 调用身份证工具类
        IdCardInfoUtils idCard = new IdCardInfoUtils(certNo);
        // 获取籍贯
        String province = idCard.getProvince();
        if (province == null) {
            logger.error(Message.buildErrInfo(Message.ERROR_EXCEPTION, "籍贯查询失败"));
            jsonResult = new JsonResult(false, Message.FAILED_MSG, null);
        }
        model.addAttribute("province", province);
        return jsonResult;
    }

    @RequestMapping(MappingURL.INFO_URL)
    public String info(Model model, Integer id) {
        ApplyOrdersEntity entity = applyOrdersService.getApplyOrders(id);
        model.addAttribute("model", entity);
        return "modules/applys/1/views";
    }
    //导出excel
    @RequestMapping(MappingURL.EXPORT)
    @ResponseBody
    public String export(Model model, HttpServletResponse response, HttpServletRequest request,
                         @PathVariable String type, ApplyOrdersEntity entity) {
        if (StringUtils.isEmpty(type)) {
            model.addAttribute("message", "参数错误");
        } else {
        	applyOrdersService.dealExport(model, entity, response);
        }
        return "modules/excel/info";
    }

    //@xiehui点击保存时判断班级标号是否为“已申报”或“已结清”状态，若是，则提示：“班级已申报，无法更换班级标号
    @RequestMapping(MappingURL.GET_VALIDCLASSNUMBER)
    @ResponseBody
    public JsonResult getValidClassNumber(Model model, @RequestBody Integer courseId, String classNumber, Integer id) {
        //		JsonResult jsonResult = null;
        if (courseId == null) {
            logger.info(Message.buildErrInfo(Message.PARAM_ERROR_EXCEPTION, " 课程id为空"));
            return new JsonResult(false, Message.PARAM_ERROR_MSG, null);
        }
        if (classNumber == null) {
            logger.info(Message.buildErrInfo(Message.PARAM_ERROR_EXCEPTION, " 课程id为空"));
            return new JsonResult(false, Message.PARAM_ERROR_MSG, null);
        }
        if (id == null) {
            logger.info(Message.buildErrInfo(Message.PARAM_ERROR_EXCEPTION, " 课程id为空"));
            return new JsonResult(false, Message.PARAM_ERROR_MSG, null);
        }
        ExamClassEntity examClassEntity = examClassMapper.getValidClassNumber(courseId, classNumber);
        if (!"02".equals(examClassEntity.getExamStatus()) || !"04".equals(examClassEntity.getExamStatus())) {
            logger.info(Message.buildErrInfo(Message.PARAM_ERROR_EXCEPTION, " 课程id为空"));
            return new JsonResult(false, Message.PARAM_ERROR_MSG, null);
        }
        return new JsonResult(false, Message.VALID_SUCCESS_MSG, null);
    }


    /**
     * 报名管理 - 待审核 - 审核详情页面
     *
     * @param id
     * @param type
     * @return modules/applys/1/form
     */
    @RequestMapping(value = MappingURL.ORDER_FORM_URL, method = RequestMethod.GET)
    @ResponseBody
    public JsonResult orderForm(Integer id, String type) {
        Map<String, Object> model = new HashMap<String, Object>();
        ApplyOrdersEntity applyOrdersEntity = this.getModel(id);
        int sInstitutionInfoId = com.jiacer.modules.system.utils.UserUtils.getUser().getInstitutionInfoId();

        StuUserInfoEntity stuUserInfo = stuUserInfoDao.getStuInfoByUserId(sInstitutionInfoId, applyOrdersEntity.getUserId());
        if (stuUserInfo == null) {//同一个学校
            stuUserInfo = new StuUserInfoEntity();
            UserBaseInfoEntity userBaseInfoEntity = userBaseInfoDao.getById(applyOrdersEntity.getUserId());
            UserExtendInfoEntity userExtendInfoEntity = userExtendInfoDao.getById(applyOrdersEntity.getUserId());
            if (userBaseInfoEntity != null || userExtendInfoEntity != null) {
                stuUserInfo.setUserName(userBaseInfoEntity.getUserName());
                stuUserInfo.setCertNo(userBaseInfoEntity.getCertNo());
                stuUserInfo.setMobile(userBaseInfoEntity.getMobile());
                stuUserInfo.setEducation(userExtendInfoEntity.getEducation());
                IdCardInfoUtils idCard = new IdCardInfoUtils(userExtendInfoEntity.getCertNo());
                stuUserInfo.setAge(idCard.getAge() + "");
                stuUserInfo.setSex(idCard.getGender());
                stuUserInfo.setBirthplace(idCard.getProvince());
            }
        } else {
            IdCardInfoUtils idCard = new IdCardInfoUtils(stuUserInfo.getCertNo());
            if (stuUserInfo.getCertNo() != null || stuUserInfo.getCertNo() != "") {
                stuUserInfo.setAge(DictionariesUtil.getAge(stuUserInfo.getCertNo()) + "");
            }
            stuUserInfo.setSex(idCard.getGender());
            stuUserInfo.setBirthplace(idCard.getProvince());
        }
        model.put("stuUserInfo", stuUserInfo);

        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("courseId", applyOrdersEntity.getCourseId());
        map.put("institutionInfoId", UserUtils.getUser().getInstitutionInfoId());
        map.put("id", applyOrdersEntity.getCourseId());
        List<ExamClassEntity> courseClassList = examsService.getCourseIdExamClass(map);
        model.put("courseClassList", courseClassList);
        model.put("isExist", applyOrdersService.checkIsHasPass(id));
        log.info("**********************************************CourseId:"+applyOrdersEntity.getCourseId());
        List<CoursesTimeEntity> coursesTimeList = coursesTimeDao.getSKTemplate(map);
        log.info("**********************************************coursesTimeList.size:"+coursesTimeList.size());
        model.put("coursesTimeList", coursesTimeList);
        model.put("model", applyOrdersEntity);
        if (applyOrdersEntity.getClassTime() != null) {
            CoursesTimeEntity CoursesTimeEntity = coursesTimeDao.getById(Integer.parseInt(applyOrdersEntity.getClassTime()));
            model.put("goOnClassTimeId", CoursesTimeEntity.getId());
            model.put("goOnClassTime", CoursesTimeEntity.getTemplateName());
            log.info("------------------------------------------------------------TemplateName:"+CoursesTimeEntity.getTemplateName());
        } else {
            model.put("goOnClassTimeId", "");
            model.put("goOnClassTime", "");
            log.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
        }
        if (HandleStatusType.PEND_APPLY.getValue().equals(type)) {
            model.put("isExist", applyOrdersService.checkIsHasPass(id));
            model.put("educationList", DictionariesUtil.getEducationNameList());
        }
       
        
        
        
        return new JsonResult(true, "success", model);
    }

    @RequestMapping(MappingURL.FORM_URL)
    public String form(Model model, Integer id, String type) {
        ApplyOrdersEntity applyOrdersEntity = this.getModel(id);
        int sInstitutionInfoId = com.jiacer.modules.system.utils.UserUtils.getUser().getInstitutionInfoId();

        StuUserInfoEntity stuUserInfo = stuUserInfoDao.getStuInfoByUserId(sInstitutionInfoId, applyOrdersEntity.getUserId());
        if (stuUserInfo == null) {//同一个学校
            stuUserInfo = new StuUserInfoEntity();
            UserBaseInfoEntity userBaseInfoEntity = userBaseInfoDao.getById(applyOrdersEntity.getUserId());
            UserExtendInfoEntity userExtendInfoEntity = userExtendInfoDao.getById(applyOrdersEntity.getUserId());
            if (userBaseInfoEntity != null || userExtendInfoEntity != null) {
                stuUserInfo.setUserName(userBaseInfoEntity.getUserName());
                stuUserInfo.setCertNo(userExtendInfoEntity.getCertNo());
                stuUserInfo.setMobile(userBaseInfoEntity.getMobile());
                stuUserInfo.setEducation(userExtendInfoEntity.getEducation());
                IdCardInfoUtils idCard = new IdCardInfoUtils(userExtendInfoEntity.getCertNo());
                stuUserInfo.setAge(idCard.getAge() + "");
                stuUserInfo.setSex(idCard.getGender());
                stuUserInfo.setBirthplace(idCard.getProvince());
            }
        } else {
            IdCardInfoUtils idCard = new IdCardInfoUtils(stuUserInfo.getCertNo());
            if (stuUserInfo.getCertNo() != null || stuUserInfo.getCertNo() != "") {
                stuUserInfo.setAge(DictionariesUtil.getAge(stuUserInfo.getCertNo()) + "");
            }
            stuUserInfo.setSex(idCard.getGender());
            stuUserInfo.setBirthplace(idCard.getProvince());

            stuUserInfo.setContactPhone(stuUserInfo.getContactPhone());
            stuUserInfo.setContacts(stuUserInfo.getContacts());
            stuUserInfo.setNation(stuUserInfo.getNation());
            stuUserInfo.setAddress(stuUserInfo.getAddress());
            stuUserInfo.setContactAddress(stuUserInfo.getContactAddress());
            stuUserInfo.setContactAddress(stuUserInfo.getContactAddress());
        }
        model.addAttribute("stuUserInfo", stuUserInfo);
        model.addAttribute("cList", examsService.getClassNumber());
        model.addAttribute("kclist", coursesService.getCourseIdName());
        model.addAttribute("cplist", coursePackageService.getCoursePackageIdName());
        model.addAttribute("classNumberList", DictionariesUtil.getClassNumberList(examsService, applyOrdersEntity));
        //获取上课时间
        LearnTypesEntity learnEty = coursesDao.getById(applyOrdersEntity.getCourseId());

        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("courseId", applyOrdersEntity.getCourseId());
        map.put("institutionInfoId", UserUtils.getUser().getInstitutionInfoId());
        map.put("id", applyOrdersEntity.getCourseId());
        List<ExamClassEntity> courseClassList = examsService.getCourseIdExamClass(map);
        model.addAttribute("courseClassList", courseClassList);
        model.addAttribute("isExist", applyOrdersService.checkIsHasPass(id));
        List<CoursesTimeEntity> coursesTimeList = coursesTimeDao.getSKTemplate(map);
        model.addAttribute("coursesTimeList", coursesTimeList);
        model.addAttribute("model", applyOrdersEntity);
        //上课时间
        Map<Object, Object> skMap = new HashMap<Object, Object>();
        skMap.put("id", applyOrdersEntity.getCourseId());
        if (applyOrdersEntity.getClassTime() != null) {
            CoursesTimeEntity CoursesTimeEntity = coursesTimeDao.getById(Integer.parseInt(applyOrdersEntity.getClassTime()));
            model.addAttribute("goOnClassTimeId", CoursesTimeEntity.getId());
            model.addAttribute("goOnClassTime", CoursesTimeEntity.getTemplateName());
        } else {
            model.addAttribute("goOnClassTimeId", "");
            model.addAttribute("goOnClassTime", "");
        }

        if (HandleStatusType.PEND_APPLY.getValue().equals(type)) {
            //refactor orderForm()
            model.addAttribute("isExist", applyOrdersService.checkIsHasPass(id));
            model.addAttribute("educationList", DictionariesUtil.getEducationNameList());
            return "modules/applys/1/form";
        } else if (HandleStatusType.NOT_MATCH.getValue().equals(type)) {

            return "modules/applys/2/form_003";
        } else {
            //TODO add order.  refactor. del
            CfgParamEntity cfgParamEntity = new CfgParamEntity();
            cfgParamEntity.setType(5);
            cfgParamEntity.setFkParentParamId(0);
            List<CfgParamEntity> sourceTypeList = cfgParamDao.findAllList(cfgParamEntity);
            model.addAttribute("sourceTypeList", sourceTypeList);
            //课程
            List<LearnTypesEntity> result = Lists.newArrayList();
            LearnTypesEntity entity = new LearnTypesEntity();
            entity.setIsUsable(DBStatus.IsUsable.TRUE);//基础参数类型
            entity.setStatus(DBStatus.CourseStatus.NOMAL);
            entity.setInstitutionInfoId(UserUtils.getUser().getInstitutionInfoId());
            try {
                result = coursesDao.findAllList(entity);
            } catch (Exception e) {
                Log.error("获取课程数据失败");
            }
            model.addAttribute("learnTypeList", result);
            List<CoursePackageEntity> result1 = Lists.newArrayList();
            CoursePackageEntity entity1 = new CoursePackageEntity();
            entity1.setIsUsable(DBStatus.IsUsable.TRUE);//基础参数类型
            entity1.setStatus(DBStatus.CourseStatus.NOMAL);
            entity1.setInstitutionInfoId(UserUtils.getUser().getInstitutionInfoId());
            try {
                result1 = coursePackageDao.findAllList(entity1);
            } catch (Exception e) {
                Log.error("获取课程数据失败");
            }
            model.addAttribute("coursePackageList", result1);
            return "modules/applys/1/addForm";
        }
    }

    @RequestMapping(MappingURL.ORDER_ADD_URL)
    @ResponseBody
    public JsonResult addStudentOrder() {
        Map<String, Object> model = new HashMap<String, Object>();

        model.put("cList", examsService.getClassNumber());
        model.put("kclist", coursesService.getCourseIdName());
        model.put("cplist", coursePackageService.getCoursePackageIdName());

        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("institutionInfoId", UserUtils.getUser().getInstitutionInfoId());
        List<ExamClassEntity> courseClassList = examsService.getCourseIdExamClass(map);
        model.put("courseClassList", courseClassList);
        List<CoursesTimeEntity> coursesTimeList = coursesTimeDao.getSKTemplate(map);
        model.put("coursesTimeList", coursesTimeList);

        CfgParamEntity cfgParamEntity = new CfgParamEntity();
        cfgParamEntity.setType(5);
        cfgParamEntity.setFkParentParamId(0);
        List<CfgParamEntity> sourceTypeList = cfgParamDao.findAllList(cfgParamEntity);
        model.put("sourceTypeList", sourceTypeList);
        //课程
        List<LearnTypesEntity> result = Lists.newArrayList();
        LearnTypesEntity entity = new LearnTypesEntity();
        entity.setIsUsable(DBStatus.IsUsable.TRUE);//基础参数类型
        entity.setStatus(DBStatus.CourseStatus.NOMAL);
        entity.setInstitutionInfoId(UserUtils.getUser().getInstitutionInfoId());
        try {
            result = coursesDao.findAllList(entity);
        } catch (Exception e) {
            Log.error("获取课程数据失败");
        }
        model.put("learnTypeList", result);
        List<CoursePackageEntity> result1 = Lists.newArrayList();
        CoursePackageEntity entity1 = new CoursePackageEntity();
        entity1.setIsUsable(DBStatus.IsUsable.TRUE);//基础参数类型
        entity1.setStatus(DBStatus.CourseStatus.NOMAL);
        entity1.setInstitutionInfoId(UserUtils.getUser().getInstitutionInfoId());
        try {
            result1 = coursePackageDao.findAllList(entity1);
        } catch (Exception e) {
            Log.error("获取课程数据失败");
        }
        model.put("coursePackageList", result1);
        return new JsonResult(true, "success", model);
    }


    // 预报名修改页面
    @RequestMapping(MappingURL.FORM_YBM_URL)
    public String formYbm(Model model, Integer id, String type) {
        ApplyOrdersEntity applyOrdersEntity = this.getModel(id);
        StuUserInfoEntity stuUserInfoEntity = stuUserInfoDao.getById(applyOrdersEntity.getStuUserInfoId());
        //上课时间
        log.info("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&sex:"+stuUserInfoEntity.getSex());
        Map<Object, Object> skMap = new HashMap<Object, Object>();
        skMap.put("id", applyOrdersEntity.getCourseId());
        List<CoursesTimeEntity> cteList = coursesTimeDao.getSKTemplate(skMap);
        String goOnClassTime = "";
        for (CoursesTimeEntity entity : cteList) {
            if (entity != null) {
                goOnClassTime = entity.getTemplateName();
            }
        }
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("courseId", applyOrdersEntity.getCourseId());
        map.put("institutionInfoId", UserUtils.getUser().getInstitutionInfoId());
        List<ExamClassEntity> courseClassList = examsService.getCourseIdExamClass(map);
        model.addAttribute("courseClassList", courseClassList);

        if (applyOrdersEntity.getClassId() != null) {
            model.addAttribute("classNumber", examClassDao.getById(applyOrdersEntity.getClassId()).getClassNumber());//班级标号
            model.addAttribute("classNumberId", applyOrdersEntity.getClassId());//班级标号ID
        }
        model.addAttribute("cList", examsService.getClassNumber());
        model.addAttribute("kclist", coursesService.getCourseIdName());
        model.addAttribute("cplist", coursePackageService.getCoursePackageIdName());
        model.addAttribute("model", applyOrdersEntity);

        if (StringUtils.isNotBlank(applyOrdersEntity.getClassTime())) {
            CoursesTimeEntity CoursesTimeEntity = coursesTimeDao.getById(Integer.parseInt(applyOrdersEntity.getClassTime()));
            model.addAttribute("goOnClassTimeId", CoursesTimeEntity.getId());
            model.addAttribute("goOnClassTime", CoursesTimeEntity.getTemplateName());
        }
        model.addAttribute("stuUserInfoEntity", stuUserInfoEntity);
        if (HandleStatusType.REP_APPLY.getValue().equals(type)) {
            return "modules/applys/1/form_002";
        } else if (HandleStatusType.SUCCESS_APPLY.getValue().equals(type)) {
            return "modules/applys/2/form";
        } else {
            return "modules/applys/1/dropOutView";
        }
    }

    public ApplyOrdersEntity getModel(Integer id) {
        if (id != null) {
            return applyOrdersService.getApplyOrders(id);
        } else {
            return new ApplyOrdersEntity();
        }
    }


}
