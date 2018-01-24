package com.jiacer.modules.business.service.impl;

import com.google.common.collect.Lists;
import com.jiacer.modules.business.bean.DictionariesUtil;
import com.jiacer.modules.business.bean.HandleStatusType;
import com.jiacer.modules.business.bean.IdcardInfoExtractor;
import com.jiacer.modules.business.bean.operation.ApplyOrdersOpt;
import com.jiacer.modules.business.service.ApplyOrdersService;
import com.jiacer.modules.business.service.ExamsService;
import com.jiacer.modules.business.utils.CfgParamUtils;
import com.jiacer.modules.business.utils.ExamsUtils;
import com.jiacer.modules.business.utils.IdCardInfoUtils;
import com.jiacer.modules.common.constants.Constants;
import com.jiacer.modules.common.page.Page;
import com.jiacer.modules.common.service.BaseService;
import com.jiacer.modules.common.service.ServiceException;
import com.jiacer.modules.common.utils.DateUtils;
import com.jiacer.modules.common.utils.ExcelOutput;
import com.jiacer.modules.common.utils.JsonResult;
import com.jiacer.modules.common.utils.StringUtils;
import com.jiacer.modules.log.Log;
import com.jiacer.modules.mybatis.dao.*;
import com.jiacer.modules.mybatis.entity.*;
import com.jiacer.modules.system.config.DBStatus;
import com.jiacer.modules.system.config.Message;
import com.jiacer.modules.system.utils.UserUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 贺章鹏
 * @ClassName: ApplyOrdersServiceImpl
 * @Description: TODO
 * @date 2016年10月19日 下午4:18:33
 */
@Service
public class ApplyOrdersServiceImpl extends BaseService implements ApplyOrdersService {
	private static final Logger log = LoggerFactory.getLogger(ApplyOrdersServiceImpl.class);
    @Autowired
    ApplyOrdersMapper applyOrdersDao;

    @Autowired
    UserBaseInfoMapper userBaseInfoDao;

    @Autowired
    UserExtendInfoMapper userExtendInfoDao;

    @Autowired
    StuUserInfoDao stuUserInfoDao;

    @Autowired
    CfgParamMapper cfgParamDao;

    @Autowired
    PartnersMapper partnersDao;

    @Autowired
    LearnTypesMapper learnTypesDao;

    @Autowired
    ExamsService examsService;

    @Override
    public Page<ApplyOrdersEntity> getApplyOrdersPage(ApplyOrdersEntity applyOrdersEntity, String type, int pageNumber,
                                                      int pageSize) {
        try {
            //获取总条数
            Map<Object, Object> map = ApplyOrdersOpt.buildMap(applyOrdersEntity, type);
            map.put("institutionInfoId", com.jiacer.modules.system.utils.UserUtils.getUser().getInstitutionInfoId());
            Integer totalCount = applyOrdersDao.count(map);
            //分页实体
            Page<ApplyOrdersEntity> page = new Page<ApplyOrdersEntity>();
            page.setPage(pageNumber);
            page.setRowNum(pageSize);
            if (totalCount == null) {
                return page;
            }
            //最大页数判断
            int pageM = maxPage(totalCount, page.getRowNum(), page.getPage());
            if (pageM > 0) {
                page.setPage(pageM);
            }
            if (totalCount > 0) {
                map.put("offset", page.getOffset());
                map.put("pageSize", page.getRowNum());
                List<ApplyOrdersEntity> list = applyOrdersDao.getPageList(map);
                for (ApplyOrdersEntity entity : list) {
                    StuUserInfoEntity stu = stuUserInfoDao.getStuInfoByUserId(entity.getInstitutionInfoId(), entity.getUserId());
                    if (stu != null) {
                        entity.setUserName(stu.getUserName());
                        entity.setEducation(stu.getEducation());
                        entity.setUserAge(stu.getAge() == null ? 0 : Integer.valueOf(stu.getAge()));
                    }else{
                        UserBaseInfoEntity usr = userBaseInfoDao.getById(entity.getUserId());
                        UserExtendInfoEntity ext = userExtendInfoDao.getById(entity.getUserId());
                        if(usr != null){
                            entity.setUserName(usr.getUserName());
                            entity.setEducation(ext.getEducation());
                        }
                    }
                }
                page.setRows(list);
                page.setRecords(totalCount.longValue());
            }
            return page;
        } catch (Exception e) {
            e.printStackTrace();
            Log.error(String.format(Message.QUERY_ERROR_EXCEPTION, e));
            return null;
        }
    }

    @Override
    public Page<ApplyOrdersEntity> getApplyOrdersStuUserPage(ApplyOrdersEntity applyOrdersEntity, String type,
                                                                int pageNumber, int pageSize) {
        try {
            //获取总条数
            Map<Object, Object> map = ApplyOrdersOpt.buildMap(applyOrdersEntity, type);
            map.put("sourceType", applyOrdersEntity.getSourceType());
            map.put("sourceTypeSec", applyOrdersEntity.getSourceTypeSec());
            map.put("institutionInfoId", com.jiacer.modules.system.utils.UserUtils.getUser().getInstitutionInfoId());
            Integer totalCount = applyOrdersDao.countStu(map);
            //分页实体
            Page<ApplyOrdersEntity> page = new Page<ApplyOrdersEntity>();
            page.setPage(pageNumber);
            page.setRowNum(pageSize);
            if (totalCount == null) {
                return page;
            }
            //最大页数判断
            int pageM = maxPage(totalCount, page.getRowNum(), page.getPage());
            if (pageM > 0) {
                page.setPage(pageM);
            }
            if (totalCount > 0) {
                map.put("offset", page.getOffset());
                map.put("pageSize", page.getRowNum());
                List<ApplyOrdersEntity> list = applyOrdersDao.getPageListStu(map);
                for (ApplyOrdersEntity entity : list) {
                    StuUserInfoEntity stuUserInfo = stuUserInfoDao.getById(entity.getStuUserInfoId());
                    if(stuUserInfo == null){
                        UserBaseInfoEntity usr = userBaseInfoDao.getById(entity.getUserId());
                        UserExtendInfoEntity ext = userExtendInfoDao.getById(entity.getUserId());
                        if(usr != null){
                            stuUserInfo = new StuUserInfoEntity();
                            stuUserInfo.setUserName(usr.getUserName());
                            stuUserInfo.setEducation(ext.getEducation());
                        }
                    }
                    entity.setStuUserInfo(stuUserInfo);
                }
                page.setRows(list);
                page.setRecords(totalCount.longValue());
            }
            return page;
        } catch (Exception e) {
            e.printStackTrace();
            Log.error(String.format(Message.QUERY_ERROR_EXCEPTION, e));
            return null;
        }
    }

    @Override
    public JsonResult modifyApplyOrders(ApplyOrdersEntity applyOrdersEntity, String type) throws Exception {
        ApplyOrdersEntity bean = applyOrdersDao.getById(applyOrdersEntity.getId());
        if (bean == null) {
            throw new ServiceException(Message.UPDATE_DATE_NOEXIST);
        } else {
            if (!applyOrdersEntity.getType().equals("RETREAT_FEE_APPLY")) {
                ExamClassEntity examClassEntity = ExamsUtils.getClassObjcet(applyOrdersEntity.getClassNumber());
                if (!bean.getCourseId().equals(examClassEntity.getCourseId())) {
                    return new JsonResult(false, "所选班级没有此课程，请检查", null);
                }
            }
            if (type.equals("RETREAT_FEE_APPLY")) {
                bean.setRetreatFee(applyOrdersEntity.getRetreatFee());//退费
                bean.setHandleStatus("04");//已退学-强制退学
                bean.setIsClear("1");
                bean.setIsDepositClear("1");
                bean.setRetreatFeeDate(new Date());
            } else {
                if (!StringUtils.isEmpty(applyOrdersEntity.getContacts()) || StringUtils.isEmpty(applyOrdersEntity.getCourseName())) {
                    StuUserInfoEntity stuUserInfoEntity = stuUserInfoDao.getById(bean.getStuUserInfoId());
                    if (!StringUtils.isEmpty(applyOrdersEntity.getContactPhone())) {
                        stuUserInfoEntity.setContactPhone(applyOrdersEntity.getContactPhone());
                    }
                    if (!StringUtils.isEmpty(applyOrdersEntity.getEducation())) {
                        stuUserInfoEntity.setEducation(applyOrdersEntity.getEducation());
                    }
                    if (!StringUtils.isEmpty(applyOrdersEntity.getNation())) {
                        stuUserInfoEntity.setNation(applyOrdersEntity.getNation());
                    }
                    if (!StringUtils.isEmpty(applyOrdersEntity.getAddress())) {
                        stuUserInfoEntity.setAddress(applyOrdersEntity.getAddress());
                    }
                    if (!StringUtils.isEmpty(applyOrdersEntity.getContacts())) {
                        stuUserInfoEntity.setContacts(applyOrdersEntity.getContacts());
                    }
                    if (!StringUtils.isEmpty(applyOrdersEntity.getUserName())) {
                        stuUserInfoEntity.setUserName(applyOrdersEntity.getUserName());
                    }
                    if (!StringUtils.isEmpty(applyOrdersEntity.getContactAddress())) {
                        stuUserInfoEntity.setContactAddress(applyOrdersEntity.getContactAddress());
                    }
                    if (!StringUtils.isEmpty(applyOrdersEntity.getContactPhone())) {
                        stuUserInfoEntity.setContactPhone(applyOrdersEntity.getContactPhone());
                    }
                    if (!StringUtils.isEmpty(applyOrdersEntity.getCertNo())) {
                        stuUserInfoEntity.setCertNo(applyOrdersEntity.getCertNo());
                        stuUserInfoEntity.setAge(DictionariesUtil.getAge(applyOrdersEntity.getCertNo()) + "");
                        stuUserInfoEntity.setSex(DictionariesUtil.getSex(applyOrdersEntity.getCertNo()) + "");
                        stuUserInfoEntity.setBirthplace(new IdcardInfoExtractor(applyOrdersEntity.getCertNo()).getProvince());
                    }
                    stuUserInfoDao.update(stuUserInfoEntity);
                }
            }
            applyOrdersDao.update(ApplyOrdersOpt.buildModify(bean, applyOrdersEntity));
        }
        return new JsonResult(true, Message.SUCCESS_MSG, null);
    }

    /**
     * 审核报名订单
     *
     * @param applyOrdersEntity
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public JsonResult handleApplyOrders(ApplyOrdersEntity applyOrdersEntity) throws Exception {
        Integer institutionInfoId = UserUtils.getUser().getInstitutionInfoId();

        ApplyOrdersEntity bean = applyOrdersDao.getById(applyOrdersEntity.getId());
        applyOrdersEntity.setUserId(bean.getUserId());
        //验证 班级  课程
        ExamClassEntity examClass = examsService.getById(applyOrdersEntity.getClassNumber());
        if (examClass.getExamStatus().equals("02") || examClass.getExamStatus().equals("04")) {
            return new JsonResult(false, "班级已申报，无法更换班级标号", null);
        }
        //获取待审核订单信息
        Integer appOrderStuUserInfoId = null;
        //根据 userInfoId institutionInfoId 到stu_user_info 查询是否存在
        List<StuUserInfoEntity> stus = stuUserInfoDao.findStudentByCertNo(institutionInfoId, applyOrdersEntity.getCertNo());
        if (stus == null || stus.size() < 1) {
            //学员信息不存在，创建新学员
            appOrderStuUserInfoId = insertStudentInfo(applyOrdersEntity);
        } else if (stus.size() > 1) {
            return new JsonResult(false, "学员信息冲突，请确认学员证件是否已注册", null);
        } else {
            StuUserInfoEntity student = stus.get(0);
            //否则 更新本学校下的学员信息
            appOrderStuUserInfoId = updateStudentInfo(applyOrdersEntity, student);
        }

        Integer count = applyOrdersDao.getPassedOrder(appOrderStuUserInfoId, institutionInfoId, bean.getCourseId());
        if (count > 0) {
            return new JsonResult(false, "学员已报名该课程，不能重复报名", null);
        }

        bean.setStuUserInfoId(appOrderStuUserInfoId);

        applyOrdersDao.update(ApplyOrdersOpt.buildHandle(bean, applyOrdersEntity));

        //更新用户信息 user_base_info
        UserBaseInfoEntity userBase = applyOrdersEntity.buildUserBaseInfoEntity();
        UserExtendInfoEntity userExt = applyOrdersEntity.buildUserExtendInfoEntity();
        userBaseInfoDao.update(userBase);
        userExtendInfoDao.update(userExt);
        return new JsonResult(true, Message.SUCCESS_MSG, null);
    }

    /**
     * 补考审核
     *
     * @param applyOrdersEntity
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public JsonResult rehandleApplyOrders(ApplyOrdersEntity applyOrdersEntity) throws Exception {
        Integer institutionInfoId = UserUtils.getUser().getInstitutionInfoId();

        ApplyOrdersEntity bean = applyOrdersDao.getById(applyOrdersEntity.getId());
        applyOrdersEntity.setUserId(bean.getUserId());
        applyOrdersEntity.setInstitutionInfoId(institutionInfoId);
        //验证 班级  课程
        ExamClassEntity examClass = examsService.getById(applyOrdersEntity.getClassNumber());
        if (examClass.getExamStatus().equals("02") || examClass.getExamStatus().equals("04")) {
            return new JsonResult(false, "班级已申报，无法更换班级标号", null);
        }

        applyOrdersDao.update(ApplyOrdersOpt.buildHandle(bean, applyOrdersEntity));

        return new JsonResult(true, Message.SUCCESS_MSG, null);
    }


    /**
     * 更新学员信息
     *
     * @param applyOrdersEntity
     * @param existStudent
     * @return
     */
    private Integer updateStudentInfo(ApplyOrdersEntity applyOrdersEntity, StuUserInfoEntity existStudent) {
        StuUserInfoEntity stuUserInfoEntity = stuUserInfoDao.getById(existStudent.getId());
        if (StringUtils.isNotBlank(applyOrdersEntity.getExpiredTime())) {
            stuUserInfoEntity.setExpiredTime(applyOrdersEntity.getExpiredTime());
        }
        if (StringUtils.isNotBlank(applyOrdersEntity.getHeadImage())) {
            stuUserInfoEntity.setHeadImage(applyOrdersEntity.getHeadImage());
        }
        if (StringUtils.isNotBlank(applyOrdersEntity.getIssueOrg())) {
            stuUserInfoEntity.setIssueOrg(applyOrdersEntity.getIssueOrg());
        }
        if (!StringUtils.isEmpty(applyOrdersEntity.getNation())) {
            stuUserInfoEntity.setNation(CfgParamUtils.getCfgParamValueByText(Constants.CFG_PARAM_NATION_TYPE, applyOrdersEntity.getNation()));
        }
        if (!StringUtils.isEmpty(applyOrdersEntity.getAddress())) {
            stuUserInfoEntity.setAddress(applyOrdersEntity.getAddress());
        }
        if (!StringUtils.isEmpty(applyOrdersEntity.getContacts())) {
            stuUserInfoEntity.setContacts(applyOrdersEntity.getContacts());
        }
        if (!StringUtils.isEmpty(applyOrdersEntity.getContactPhone())) {
            stuUserInfoEntity.setContactPhone(applyOrdersEntity.getContactPhone());
        }
        if (!StringUtils.isEmpty(applyOrdersEntity.getContactAddress())) {
            stuUserInfoEntity.setContactAddress(applyOrdersEntity.getContactAddress());
        }
        if (!StringUtils.isEmpty(applyOrdersEntity.getUserName())) {
            stuUserInfoEntity.setUserName(applyOrdersEntity.getUserName());
        }
        if (!StringUtils.isEmpty(applyOrdersEntity.getCertNo())) {
            stuUserInfoEntity.setCertNo(applyOrdersEntity.getCertNo());
            stuUserInfoEntity.setAge(DictionariesUtil.getAge(applyOrdersEntity.getCertNo()) + "");
            stuUserInfoEntity.setSex(DictionariesUtil.getSex(applyOrdersEntity.getCertNo()) + "");
            stuUserInfoEntity.setBirthplace(new IdcardInfoExtractor(applyOrdersEntity.getCertNo()).getProvince());
        }
        if (!StringUtils.isEmpty(applyOrdersEntity.getEducation())) {
            stuUserInfoEntity.setEducation(applyOrdersEntity.getEducation());
        }
        stuUserInfoDao.update(stuUserInfoEntity);
        return stuUserInfoEntity.getId();
    }

    private Integer insertStudentInfo(ApplyOrdersEntity applyOrdersEntity) {
        Integer institutionInfoId = com.jiacer.modules.system.utils.UserUtils.getUser().getInstitutionInfoId();
        //获取 user_base_info 信息
        UserBaseInfoEntity userBaseInfoEntity = userBaseInfoDao.getById(applyOrdersEntity.getUserId());
        UserExtendInfoEntity userExtendInfoEntity = userExtendInfoDao.getById(userBaseInfoEntity.getId());
        //创建 一个新的stu_user_info
        StuUserInfoEntity stuUserInfo = new StuUserInfoEntity();
        stuUserInfo.setInstitutionInfoId(institutionInfoId);
        stuUserInfo.setUserId(applyOrdersEntity.getUserId());
        stuUserInfo.setOwnedTeacher(userExtendInfoEntity.getOwnedTeacher());
        stuUserInfo.setCertNo(applyOrdersEntity.getCertNo());
        stuUserInfo.setCertType(userBaseInfoEntity.getCertType());

        stuUserInfo.setEducation(applyOrdersEntity.getEducation());
        stuUserInfo.setExpiredTime(applyOrdersEntity.getExpiredTime());
        stuUserInfo.setHeadImage(applyOrdersEntity.getHeadImage());
        stuUserInfo.setIssueOrg(applyOrdersEntity.getIssueOrg());

        stuUserInfo.setAge(userExtendInfoEntity.getAge().toString());
        stuUserInfo.setSex(userExtendInfoEntity.getSex());
        stuUserInfo.setBirthplace(applyOrdersEntity.getAddress());
        stuUserInfo.setAddTime(new Date());
        stuUserInfo.setModifyTime(new Date());
        stuUserInfo.setUserName(applyOrdersEntity.getUserName());
        stuUserInfo.setMobile(applyOrdersEntity.getMobile());
        stuUserInfo.setContactAddress(applyOrdersEntity.getContactAddress());
        if (!StringUtils.isEmpty(applyOrdersEntity.getNation())) {
            stuUserInfo.setNation(CfgParamUtils.getCfgParamValueByText(Constants.CFG_PARAM_NATION_TYPE, applyOrdersEntity.getNation()));
        }
        if (!StringUtils.isEmpty(applyOrdersEntity.getAddress())) {
            stuUserInfo.setAddress(applyOrdersEntity.getAddress());
        }
        if (!StringUtils.isEmpty(applyOrdersEntity.getContacts())) {
            stuUserInfo.setContacts(applyOrdersEntity.getContacts());
        }
        if (!StringUtils.isEmpty(applyOrdersEntity.getContactPhone())) {
            stuUserInfo.setContactPhone(applyOrdersEntity.getContactPhone());
        }
        if (!StringUtils.isBlank(applyOrdersEntity.getCertNo())) {
            stuUserInfo.setCertNo(applyOrdersEntity.getCertNo());
            stuUserInfo.setAge(DictionariesUtil.getAge(applyOrdersEntity.getCertNo()) + "");
            stuUserInfo.setSex(DictionariesUtil.getSex(applyOrdersEntity.getCertNo()) + "");
            stuUserInfo.setBirthplace(new IdcardInfoExtractor(applyOrdersEntity.getCertNo()).getProvince());
        }
        if (!StringUtils.isBlank(applyOrdersEntity.getUserName())) {
            stuUserInfo.setUserName(applyOrdersEntity.getUserName());
        }
        if (!StringUtils.isEmpty(applyOrdersEntity.getEducation())) {
            stuUserInfo.setEducation(applyOrdersEntity.getEducation());
        }
        if (!StringUtils.isEmpty(applyOrdersEntity.getContactAddress())) {
            stuUserInfo.setContactAddress(applyOrdersEntity.getContactAddress());
        }
        if (!StringUtils.isEmpty(applyOrdersEntity.getContactPhone())) {
            stuUserInfo.setContactPhone(applyOrdersEntity.getContactPhone());
        }
        log.info("==========================stuUserInfo：sex"+stuUserInfo.getSex());
        stuUserInfoDao.insert(stuUserInfo);
        return stuUserInfo.getId();
    }

    //新增学员
    @Override
    @Deprecated
    public JsonResult addHandleApplyOrders(ApplyOrdersEntity applyOrdersEntity) throws Exception {
        Map map = new HashMap();
        map.put("mobile", applyOrdersEntity.getMobile());
        map.put("institutionInfoId", com.jiacer.modules.system.utils.UserUtils.getUser().getInstitutionInfoId());
        StuUserInfoEntity stuUserInfoEntity = stuUserInfoDao.getCphoneStuUserInfo(map);
        ApplyOrdersEntity applyOrdersEntityNew = new ApplyOrdersEntity();
        IdCardInfoUtils ie = new IdCardInfoUtils(applyOrdersEntity.getCertNo());
        if (stuUserInfoEntity != null) {//判断stu_user_info 手机号存在
            if (!stuUserInfoEntity.getCertNo().equalsIgnoreCase(applyOrdersEntity.getCertNo())) {//身份证不相同
                return new JsonResult(false, "该学员与系统中身份证或姓名不一致，请检查后再录入！", null);
            }
            if (!stuUserInfoEntity.getUserName().equals(applyOrdersEntity.getUserName())) {//比较姓名不相同
                return new JsonResult(false, "该学员与系统中身份证或姓名不一致，请检查后再录入！", null);
            }
            //身份证和姓名均相同
            //验证学员是否有相同的课程 如果存在就return出函数 并提示“该学员有已报名该课程”(待审核、补考、已审核 都不能在报名)
            Map aoMap = new HashMap();
            aoMap.put("stuUserInfoId", stuUserInfoEntity.getId());
            aoMap.put("institutionInfoId", com.jiacer.modules.system.utils.UserUtils.getUser().getInstitutionInfoId());
            aoMap.put("courseId", applyOrdersEntity.getCourseId());
            List<ApplyOrdersEntity> aoList = applyOrdersDao.getIsSchoolCourse(aoMap);
            if (aoList.size() > 0) {
                return new JsonResult(false, "该学员有已报名该课程！", null);
            }
            applyOrdersEntityNew.setStuUserInfoId(stuUserInfoEntity.getId());//这里放置stu_user_info ID
            applyOrdersEntityNew.setUserId(stuUserInfoEntity.getUserId());
            //更新stu_user_info
            StuUserInfoEntity stuUserInfo = new StuUserInfoEntity();
            stuUserInfo.setUserId(stuUserInfoEntity.getUserId());
            stuUserInfo.setInstitutionInfoId(com.jiacer.modules.system.utils.UserUtils.getUser().getInstitutionInfoId());
            stuUserInfo.setId(stuUserInfoEntity.getId());
            if (!StringUtils.isEmpty(applyOrdersEntity.getContactPhone())) {
                stuUserInfo.setContactPhone(applyOrdersEntity.getContactPhone());
            }
            if (!StringUtils.isEmpty(applyOrdersEntity.getEducation())) {
                stuUserInfo.setEducation(applyOrdersEntity.getEducation());
            }
            if (!StringUtils.isEmpty(applyOrdersEntity.getNation())) {
                stuUserInfo.setNation(applyOrdersEntity.getNation());
            }
            if (!StringUtils.isEmpty(applyOrdersEntity.getAddress())) {
                stuUserInfo.setAddress(applyOrdersEntity.getAddress());
            }
            if (!StringUtils.isEmpty(applyOrdersEntity.getContacts())) {
                stuUserInfo.setContacts(applyOrdersEntity.getContacts());
            }
            if (!StringUtils.isEmpty(applyOrdersEntity.getUserName())) {
                stuUserInfo.setUserName(applyOrdersEntity.getUserName());
            }
            if (!StringUtils.isEmpty(applyOrdersEntity.getEducation())) {
                stuUserInfo.setEducation(applyOrdersEntity.getEducation());
            }
            if (!StringUtils.isEmpty(applyOrdersEntity.getContactAddress())) {
                stuUserInfo.setContactAddress(applyOrdersEntity.getContactAddress());
            }
            if (!StringUtils.isEmpty(applyOrdersEntity.getContactPhone())) {
                stuUserInfo.setContactPhone(applyOrdersEntity.getContactPhone());
            }
            stuUserInfo.setCertNo(applyOrdersEntity.getCertNo());
            stuUserInfo.setBirthplace(ie.getProvince());
            stuUserInfo.setAge(DictionariesUtil.getSex(applyOrdersEntity.getCertNo()) + "");
            stuUserInfo.setSex(ie.getGender());
            stuUserInfo.setBirthplace(ie.getProvince());
            stuUserInfo.setModifyTime(new Date());
            stuUserInfoDao.update(stuUserInfo);
        } else {//手机号在stu_user_info不存在
            UserBaseInfoEntity UserBaseInfoOpt = new UserBaseInfoEntity();
            UserBaseInfoOpt.setMobile(applyOrdersEntity.getMobile());
            UserBaseInfoEntity userBaseInfoEntity = userBaseInfoDao.get(UserBaseInfoOpt);
            if (userBaseInfoEntity != null) {//获取user_base_info信息->创建stu_user_info
                UserExtendInfoEntity userExtendInfoEntity = userExtendInfoDao.getById(userBaseInfoEntity.getId());
                StuUserInfoEntity stuUserInfo = new StuUserInfoEntity();
                stuUserInfo.setUserId(userExtendInfoEntity.getUserId());
                stuUserInfo.setInstitutionInfoId(com.jiacer.modules.system.utils.UserUtils.getUser().getInstitutionInfoId());
                stuUserInfo.setUserName(applyOrdersEntity.getUserName());
                stuUserInfo.setCertNo(applyOrdersEntity.getCertNo());
                stuUserInfo.setBirthplace(ie.getProvince());
                stuUserInfo.setAge(DictionariesUtil.getAge(applyOrdersEntity.getCertNo()) + "");
                stuUserInfo.setSex(ie.getGender());
                stuUserInfo.setMobile(applyOrdersEntity.getMobile());
                stuUserInfo.setEducation(applyOrdersEntity.getEducation());
                stuUserInfo.setNation(applyOrdersEntity.getNation());
                stuUserInfo.setAddress(applyOrdersEntity.getAddress());
                stuUserInfo.setContacts(applyOrdersEntity.getContacts());
                stuUserInfo.setContactPhone(applyOrdersEntity.getContactPhone());
                stuUserInfo.setContactAddress(applyOrdersEntity.getContactAddress());
                stuUserInfo.setAddTime(new Date());
                stuUserInfoDao.insert(stuUserInfo);
                //设置apply_orders
                applyOrdersEntityNew.setStuUserInfoId(stuUserInfo.getId());
                applyOrdersEntityNew.setUserId(userExtendInfoEntity.getUserId());
            } else {//user_base_info 不存在生成user_id 同时创建 user_base_info
                UserBaseInfoEntity userBaseInfoEntityNew = new UserBaseInfoEntity();
                userBaseInfoEntityNew.setMobile(applyOrdersEntity.getMobile());
                userBaseInfoEntityNew.setUserStatus("00");
                userBaseInfoEntityNew.setRegisterTime(new Date());
                userBaseInfoEntityNew.setIsLocked("0");
                userBaseInfoEntityNew.setPwdTryCount(0);
                userBaseInfoEntityNew.setAddTime(new Date());
                userBaseInfoEntityNew.setCertNo(applyOrdersEntity.getCertNo());
                userBaseInfoEntityNew.setCertType("01");
                userBaseInfoEntityNew.setUserName(applyOrdersEntity.getUserName());
                userBaseInfoEntityNew.setUserType("00");
                userBaseInfoDao.insert(userBaseInfoEntityNew);
                UserBaseInfoEntity ue = new UserBaseInfoEntity();
                ue.setId(userBaseInfoEntityNew.getId());
                ue.setInviteCode(StringUtils.fillZero(5, userBaseInfoEntityNew.getId()));
                userBaseInfoDao.update(ue);
                //同时维护user_extend_info 信息
                UserExtendInfoEntity userExtendInfoNew = new UserExtendInfoEntity();
                userExtendInfoNew.setUserId(userBaseInfoEntityNew.getId());
                userExtendInfoNew.setUserName(applyOrdersEntity.getUserName());
                userExtendInfoNew.setCertNo(applyOrdersEntity.getCertNo());
                userExtendInfoNew.setBirthplace(ie.getProvince());
                userExtendInfoNew.setSex(ie.getGender());
                userExtendInfoNew.setCertType("01");
                userExtendInfoNew.setContactPhone(applyOrdersEntity.getContactPhone());
                userExtendInfoNew.setEducation(applyOrdersEntity.getEducation());
                userExtendInfoNew.setNation(applyOrdersEntity.getNation());
                userExtendInfoNew.setAddress(applyOrdersEntity.getAddress());
                userExtendInfoNew.setContacts(applyOrdersEntity.getContacts());
                userExtendInfoNew.setAddTime(new Date());
                userExtendInfoDao.insert(userExtendInfoNew);
                //创建stu_user_info
                StuUserInfoEntity stuUserInfo = new StuUserInfoEntity();
                stuUserInfo.setUserId(userBaseInfoEntityNew.getId());
                stuUserInfo.setInstitutionInfoId(com.jiacer.modules.system.utils.UserUtils.getUser().getInstitutionInfoId());
                stuUserInfo.setUserName(applyOrdersEntity.getUserName());
                stuUserInfo.setCertNo(applyOrdersEntity.getCertNo());
                stuUserInfo.setBirthplace(ie.getProvince());
                stuUserInfo.setAge(DictionariesUtil.getAge(applyOrdersEntity.getCertNo()) + "");
                stuUserInfo.setSex(ie.getGender());
                stuUserInfo.setMobile(applyOrdersEntity.getMobile());
                stuUserInfo.setEducation(applyOrdersEntity.getEducation());
                stuUserInfo.setNation(applyOrdersEntity.getNation());
                stuUserInfo.setAddress(applyOrdersEntity.getAddress());
                stuUserInfo.setContactAddress(applyOrdersEntity.getContactAddress());
                stuUserInfo.setContacts(applyOrdersEntity.getContacts());
                stuUserInfo.setContactPhone(applyOrdersEntity.getContactPhone());
                stuUserInfo.setAddTime(new Date());
                stuUserInfoDao.insert(stuUserInfo);
                //设置apply_orders
                applyOrdersEntityNew.setStuUserInfoId(stuUserInfo.getId());//这里放置stu_user_info ID
                applyOrdersEntityNew.setUserId(stuUserInfo.getUserId());
            }
        }
        applyOrdersEntityNew.setInstitutionInfoId(com.jiacer.modules.system.utils.UserUtils.getUser().getInstitutionInfoId());
        if (applyOrdersEntity.getCourseId() != null) {
            applyOrdersEntityNew.setCourseId(applyOrdersEntity.getCourseId());
        }
        if (applyOrdersEntity.getPackageId() != null) {
            applyOrdersEntityNew.setPackageId(applyOrdersEntity.getPackageId());
        }
        if (applyOrdersEntity.getClassNumber() != null) {
            applyOrdersEntityNew.setClassNumber(applyOrdersEntity.getClassNumber());
            applyOrdersEntityNew.setClassId(applyOrdersEntity.getClassNumber());
        }
        if (applyOrdersEntity.getIsExam() != null) {
            applyOrdersEntityNew.setIsExam(applyOrdersEntity.getIsExam());
        }
        if (applyOrdersEntity.getSchoolFee() != null) {
            applyOrdersEntityNew.setSchoolFee(applyOrdersEntity.getSchoolFee());
        }
        if (applyOrdersEntity.getBookFree() != null) {
            applyOrdersEntityNew.setBookFree(applyOrdersEntity.getBookFree());
        }
        if (applyOrdersEntity.getFirstPay() != null) {
            applyOrdersEntityNew.setFirstPay(applyOrdersEntity.getFirstPay());
        }
        if (applyOrdersEntity.getDeposit()!= null) {
            applyOrdersEntityNew.setDeposit(applyOrdersEntity.getDeposit());
        }
        applyOrdersEntityNew.setIsClear(applyOrdersEntity.getIsClear());
        applyOrdersEntityNew.setIsDepositClear(applyOrdersEntity.getIsDepositClear());
        applyOrdersEntityNew.setIsStaging(applyOrdersEntity.getIsStaging());
        if (applyOrdersEntity.getRemarks() != null) {
            applyOrdersEntityNew.setRemarks(applyOrdersEntity.getRemarks());
        }
        if (applyOrdersEntity.getClassTime() != null) {
            applyOrdersEntityNew.setClassTime(applyOrdersEntity.getClassTime());
        }
        applyOrdersEntityNew.setIsHasPf(applyOrdersEntity.getIsHasPf());
        applyOrdersEntityNew.setOrderTime(new Date());
        applyOrdersEntityNew.setHandleTime(new Date());
        applyOrdersEntityNew.setOrderStatus("01");
        applyOrdersEntityNew.setHandleStatus("02");
        applyOrdersEntityNew.setHandler(com.jiacer.modules.system.utils.UserUtils.getUser().getId());
        applyOrdersEntityNew.setFormType("01");
        applyOrdersDao.insert(applyOrdersEntityNew);
        return new JsonResult(true, Message.SUCCESS_MSG, null);
    }

    @Override
    public JsonResult handleUnPassApplyOrders(ApplyOrdersEntity applyOrdersEntity) throws Exception {
        ApplyOrdersEntity bean = applyOrdersDao.getById(applyOrdersEntity.getId());
        if (bean == null) {
            throw new ServiceException(Message.UPDATE_DATE_NOEXIST);
        } else {
            applyOrdersDao.update(ApplyOrdersOpt.buildHandle(bean, applyOrdersEntity));
        }
        return new JsonResult(true, Message.SUCCESS_MSG, null);
    }

    @Override
    public ApplyOrdersEntity getApplyOrders(Integer id) {
        ApplyOrdersEntity entity = applyOrdersDao.getById(id);
        if (entity == null) {
            return new ApplyOrdersEntity();
        }
        UserBaseInfoEntity userInfo = userBaseInfoDao.getById(entity.getUserId());
        entity.setUserBaseInfo(userInfo);
        if (entity.getAuthenticateGrade() != null) {
            entity.setAuthenticateGrade(DictionariesUtil.getAuthenticateGradeName(entity.getAuthenticateGrade()));
        }
        return entity;
    }

    /**
     * 添加订单
     *
     * @param applyOrdersEntity
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public JsonResult addApplyOrders(ApplyOrdersEntity applyOrdersEntity) throws Exception {
        Integer institutionInfoId = com.jiacer.modules.system.utils.UserUtils.getUser().getInstitutionInfoId();
        List<UserBaseInfoEntity> users = userBaseInfoDao.findUserByCertNoOrMobile(applyOrdersEntity.getCertNo(), applyOrdersEntity.getMobile());
        if (users == null || users.size() < 1) {
            //新增用户
        	log.info("---------------------------------->applyOrdersEntity:sex"+applyOrdersEntity.getSex());
            userBaseInfoDao.insert(applyOrdersEntity.buildUserBaseInfoEntity());
            
            Integer userId =userBaseInfoDao.get(applyOrdersEntity.buildUserBaseInfoEntity()).getId();
            log.info("---------------------------------->id"+userId);
            
            UserBaseInfoEntity uu = new UserBaseInfoEntity();
            uu.setId(userId);
            uu.setInviteCode(StringUtils.fillZero(5, 0));
            userBaseInfoDao.update(uu);
            UserExtendInfoEntity ext = applyOrdersEntity.buildUserExtendInfoEntity();
            ext.setEducation(applyOrdersEntity.getEducation());
            ext.setUserId(userId);
            log.info("---------------------------------->UserExtendInfoEntity:sex"+ext.getSex());
            userExtendInfoDao.insert(ext);
            applyOrdersEntity.setUserId(userId);
        } else if (users.size() == 1) {
            //修改用户
            UserBaseInfoEntity ubie = users.get(0);
            applyOrdersEntity.setUserId(ubie.getId());
            UserBaseInfoEntity ub = applyOrdersEntity.buildUserBaseInfoEntity();
            ub.setId(ubie.getId());
            userBaseInfoDao.update(ub);
            UserExtendInfoEntity ue = applyOrdersEntity.buildUserExtendInfoEntity();
            ue.setUserId(ubie.getId());
            userExtendInfoDao.update(ue);
        } else {
            return new JsonResult(false, "学员信息冲突，请确认学员证件是否已注册", null);
        }

        List<StuUserInfoEntity> stus = stuUserInfoDao.findStudentByCertNo(institutionInfoId, applyOrdersEntity.getCertNo());
        Integer stuUserInfoId = null;
        if (stus == null || stus.size() < 1) {
            //学员信息不存在，创建新学员
            stuUserInfoId = insertStudentInfo(applyOrdersEntity);
        } else if (stus.size() > 1) {
            return new JsonResult(false, "学员信息冲突，请确认学员证件是否已注册", null);
        } else {
            StuUserInfoEntity student = stus.get(0);
            //否则 更新本学校下的学员信息
            stuUserInfoId = updateStudentInfo(applyOrdersEntity, student);
        }

        Map aoMap = new HashMap();
        aoMap.put("stuUserInfoId", stuUserInfoId);
        aoMap.put("institutionInfoId", com.jiacer.modules.system.utils.UserUtils.getUser().getInstitutionInfoId());
        aoMap.put("courseId", applyOrdersEntity.getCourseId());
        List<ApplyOrdersEntity> aoList = applyOrdersDao.getIsSchoolCourse(aoMap);
        if (aoList.size() > 0) {
            return new JsonResult(false, "该学员有已报名该课程！", null);
        }

        applyOrdersEntity.setInstitutionInfoId(institutionInfoId);
        applyOrdersEntity.setStuUserInfoId(stuUserInfoId);
        applyOrdersEntity.setIsHasPf(applyOrdersEntity.getIsHasPf());
        applyOrdersEntity.setOrderTime(new Date());
        applyOrdersEntity.setHandleTime(new Date());
        applyOrdersEntity.setOrderStatus("01");
        applyOrdersEntity.setHandleStatus("02");
        applyOrdersEntity.setHandler(com.jiacer.modules.system.utils.UserUtils.getUser().getId());
        applyOrdersEntity.setFormType("01");
        applyOrdersEntity.setClassId(applyOrdersEntity.getClassNumber());
        applyOrdersDao.insert(applyOrdersEntity);
        return new JsonResult(true, Message.SUCCESS_MSG, null);
    }

    @Override
    public List<ApplyOrdersEntity> getOrderProduct(Integer courseId) {
        ApplyOrdersEntity entity = new ApplyOrdersEntity();
        entity.setCourseId(courseId);
        List<String> hstatusList = Lists.newArrayList();
        hstatusList.add(DBStatus.HandleStatus.SUCCESS);
        hstatusList.add(DBStatus.HandleStatus.PENDING);
        entity.setHstatusList(hstatusList);
        List<String> orderTypeList = Lists.newArrayList();
        orderTypeList.add(DBStatus.OrderType.LURU);
        orderTypeList.add(DBStatus.OrderType.YUYUE);
        entity.setOrderTypeList(orderTypeList);
        List<ApplyOrdersEntity> bean = applyOrdersDao.findAllList(entity);
        return bean;
    }

    @Override
    public boolean checkIsHasPass(Integer orderId) {
        Boolean reslut = Boolean.FALSE;
        ApplyOrdersEntity applyOrdersEntity = applyOrdersDao.getById(orderId);
        if (applyOrdersEntity == null) {
            return reslut;
        }
        ApplyOrdersEntity queryEntity = new ApplyOrdersEntity();
        queryEntity.setUserId(applyOrdersEntity.getUserId());
        queryEntity.setHandleStatus(DBStatus.HandleStatus.SUCCESS);
        List<ApplyOrdersEntity> list = applyOrdersDao.findAllList(queryEntity);
        if (list != null && list.size() > 0) {
            reslut = Boolean.TRUE;
        }
        return reslut;
    }

    /**
     * 报名管理审核通过
     *
     * @param applyOrdersEntity
     * @return
     */
    @Override
    public JsonResult specialHandleApplyOrders(ApplyOrdersEntity applyOrdersEntity) {
        //找到订单
        ApplyOrdersEntity bean = applyOrdersDao.getById(applyOrdersEntity.getId());
        if (bean == null) {
            throw new ServiceException(Message.UPDATE_DATE_NOEXIST);
        } else {
            ExamClassEntity examClassEntity = ExamsUtils.getClassObjcet(applyOrdersEntity.getClassNumber());
            if (bean.getCourseId().equals(examClassEntity.getCourseId())) {
                return new JsonResult(false, "所选班级没有此课程，请检查", null);
            }
            //获取用户信息
            UserExtendInfoEntity extendInfoParam = applyOrdersEntity.getUserExtendInfo();
            UserExtendInfoEntity extendInfoEntity = userExtendInfoDao.getById(bean.getUserId());
            if (extendInfoEntity != null && !extendInfoParam.getCertNo().equals(extendInfoEntity.getCertNo())) {//身份证号码改变
                UserBaseInfoEntity userBaseInfoEntity = userBaseInfoDao.getById(bean.getUserId());
                if (userBaseInfoEntity != null) {
                    userBaseInfoDao.update(ApplyOrdersOpt.buildModifyPassWord(userBaseInfoEntity, extendInfoParam));
                }
            }
            if (extendInfoEntity != null) {
                userExtendInfoDao.update(ApplyOrdersOpt.buildSpecialUserExtend(extendInfoEntity, extendInfoParam));
            }

            applyOrdersDao.update(ApplyOrdersOpt.buildHandle(bean, applyOrdersEntity));
        }
        return new JsonResult(true, Message.SUCCESS_MSG, null);
    }

    @Override
    public Model dealExport(Model model, ApplyOrdersEntity entity, HttpServletResponse response) {
        try {
            Map<Object, Object> map = ApplyOrdersOpt.buildMap(entity, HandleStatusType.SUCCESS_APPLY.getValue());
            map.put("institutionInfoId", com.jiacer.modules.system.utils.UserUtils.getUser().getInstitutionInfoId());
            List<ApplyOrdersEntity> list = applyOrdersDao.getPageList(map);
            int j=list.size();
            if (list == null || j < 1) {
                model.addAttribute("message", "没有数据可以导出");
            }
            String fileName = "已报名学员表" + DateUtils.getDate("yyyy-MM-dd") + ".xls";
            response.reset();
            response.addHeader("Content-Disposition", "filename="
                    + new String(fileName.getBytes("gb2312"), "iso8859-1"));
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            OutputStream out = new BufferedOutputStream(response.getOutputStream());
            ExcelOutput e = new ExcelOutput(out);
            int i = 0;
            for (ApplyOrdersEntity orders : list) {
                StuUserInfoEntity stuUserInfo = stuUserInfoDao.getById(orders.getStuUserInfoId());
                if (stuUserInfo == null) {
                    continue;
                }
                e.createRow(i + 1);//创建行数
                e.setCell(0, DateUtils.formatDate(orders.getHandleTime(), "yyyy-MM-dd"));//日期
                e.setCell(1, stuUserInfo.getUserName());
                e.setCell(2, stuUserInfo.getMobile());
                e.setCell(3, stuUserInfo.getEducationName());
                e.setCell(4, stuUserInfo.getAddress());
                e.setCell(5, stuUserInfo.getNationName());
                e.setCell(6, orders.getProductName());
                e.setCell(7, learnTypesDao.getById(orders.getCourseId()).getTypeName());
                e.setCell(8, orders.getSourceWholeText());
                i++;
            }
            e.createRow(0);//表头
            e.setCell(0, "受理时间");
            e.setCell(1, "姓名");
            e.setCell(2, "联系电话");
            e.setCell(3, "学历");
            e.setCell(4, "户籍地址");
            e.setCell(5, "民族");
            e.setCell(6, "产品");
            e.setCell(7, "课程");
            e.setCell(8, "来源");
            e.export();
        } catch (IOException ex) {
            logger.error("写入excel出错:" + ex);
            model.addAttribute("message", "导出失败，程序异常");
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", "导出失败，程序异常");
        }
        return model;
    }

    @Override
    public ApplyOrdersEntity getById(Integer id) {
        return applyOrdersDao.getById(id);
    }

}
