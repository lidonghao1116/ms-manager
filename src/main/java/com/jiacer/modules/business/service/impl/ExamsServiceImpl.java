
package com.jiacer.modules.business.service.impl;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jiacer.modules.common.security.session.SessionManager;
import com.jiacer.modules.common.utils.SessionUtils;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jiacer.modules.business.bean.DictionariesUtil;
import com.jiacer.modules.business.bean.ExamResult;
import com.jiacer.modules.business.bean.operation.ExamClassOpt;
import com.jiacer.modules.business.bean.operation.ScoresOpt;
import com.jiacer.modules.business.service.ApplyOrdersService;
import com.jiacer.modules.business.service.CertAuthorityService;
import com.jiacer.modules.business.service.CoursesService;
import com.jiacer.modules.business.service.ExamsService;
import com.jiacer.modules.common.config.Global;
import com.jiacer.modules.common.page.Page;
import com.jiacer.modules.common.service.BaseService;
import com.jiacer.modules.common.service.ServiceException;
import com.jiacer.modules.common.utils.DateUtils;
import com.jiacer.modules.common.utils.ExcelOutput;
import com.jiacer.modules.common.utils.StringUtils;
import com.jiacer.modules.log.Log;
import com.jiacer.modules.mybatis.dao.ApplyOrdersMapper;
import com.jiacer.modules.mybatis.dao.CfgParamMapper;
import com.jiacer.modules.mybatis.dao.CoursePackageMapper;
import com.jiacer.modules.mybatis.dao.ExamClassMapper;
import com.jiacer.modules.mybatis.dao.PartnersMapper;
import com.jiacer.modules.mybatis.dao.StuUserInfoDao;
import com.jiacer.modules.mybatis.dao.UserAnswersBatchMapper;
import com.jiacer.modules.mybatis.dao.UserBaseInfoMapper;
import com.jiacer.modules.mybatis.dao.UserExtendInfoMapper;
import com.jiacer.modules.mybatis.dao.UserScoresMapper;
import com.jiacer.modules.mybatis.entity.ApplyOrdersEntity;
import com.jiacer.modules.mybatis.entity.CfgParamEntity;
import com.jiacer.modules.mybatis.entity.ControlEntity;
import com.jiacer.modules.mybatis.entity.CoursePackageEntity;
import com.jiacer.modules.mybatis.entity.ExamClassEntity;
import com.jiacer.modules.mybatis.entity.LearnTypesEntity;
import com.jiacer.modules.mybatis.entity.StuUserInfoEntity;
import com.jiacer.modules.mybatis.entity.UserBaseInfoEntity;
import com.jiacer.modules.mybatis.entity.UserExtendInfoEntity;
import com.jiacer.modules.mybatis.entity.UserScoresEntity;
import com.jiacer.modules.mybatis.entity.extend.LearnRecordEntity;
import com.jiacer.modules.mybatis.model.CertAuthority;
import com.jiacer.modules.mybatis.model.CoursePackage;
import com.jiacer.modules.mybatis.model.UserScoresKey;
import com.jiacer.modules.system.config.DBStatus;
import com.jiacer.modules.system.config.DBStatus.DealResult;
import com.jiacer.modules.system.config.Message;
import com.jiacer.modules.system.utils.DictionaryUtils;
import com.jiacer.modules.system.utils.UserUtils;

/**
 * @author 贺章鹏
 * @ClassName: ExamsServiceImpl
 * @Description: TODO
 * @date 2016年10月19日 下午4:19:24
 */
@Service
public class ExamsServiceImpl extends BaseService implements ExamsService {

    @Autowired
    ExamClassMapper examClassDao;

    @Autowired
    ApplyOrdersMapper applyOrdersDao;

    @Autowired
    UserScoresMapper userScoresDao;

    @Autowired
    UserBaseInfoMapper userBaseInfoDao;

    @Autowired
    UserExtendInfoMapper userExtendInfoDao;

    @Autowired
    UserAnswersBatchMapper userAnswersBatchDao;

    @Autowired
    CoursePackageMapper coursePackageMapper;

    @Autowired
    ApplyOrdersService applyOrdersService;

    @Autowired
    CoursesService coursesService;

    @Autowired
    CertAuthorityService certAuthorityService;

    @Autowired
    CoursePackageMapper coursePackageDao;

    @Autowired
    StuUserInfoDao stuUserInfoDao;

    @Resource
    CfgParamMapper cfgParamDao;

    @Resource
    PartnersMapper partnersDao;

    @Override
    public ExamClassEntity getExamClassById(Integer id) {
        try {
            return examClassDao.getById(id);
        } catch (Exception e) {
            Log.error(Message.buildErrInfo(Message.QUERY_ERROR_EXCEPTION, e));
        }
        return null;
    }

    @Override
    public Page<ExamClassEntity> getExamClassPage(ExamClassEntity examClassEntity, int pageNumber, int pageSize) {
        try {
            // 获取总条数
            Map<Object, Object> map = ExamClassOpt.buildMap(examClassEntity);
            map.put("institutionInfoId", UserUtils.getUser().getInstitutionInfoId());
            Integer totalCount = examClassDao.count(map);
            // 分页实体
            Page<ExamClassEntity> page = new Page<ExamClassEntity>();
            page.setPage(pageNumber);
            page.setRowNum(pageSize);
            if (totalCount == null) {
                return page;
            }
            // 最大页数判断
            int pageM = maxPage(totalCount, page.getRowNum(), page.getPage());
            if (pageM > 0) {
                page.setPage(pageM);
            }
            if (totalCount > 0) {
                map.put("offset", page.getOffset());
                map.put("pageSize", page.getRowNum());
                List<ExamClassEntity> list = examClassDao.getPageList(map);
                ExamClassEntity entity = null;
                for (ExamClassEntity exam : list) {
                    entity = new ExamClassEntity();
                    entity = getExamScores(exam.getId());
                    exam.setExamResult(entity.getExamResult());
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

//	@Override
//	public void addExamClass(ExamClassEntity examClassEntity) throws Exception {
//		examClassDao.insert(ExamClassOpt.buildAdd(examClassEntity));
//	}

    /**
     * @author xiehui
     * 根据课程id查课程上架下架与否
     */
    @Override
    public void addExamClass(ExamClassEntity examClassEntity) throws Exception {
        /*Integer courseId = examClassEntity.getCourseId();
		if(courseId!=null){
			LearnTypesEntity learnTypesEntity = coursesService.getById(courseId);
			if("02".equals(learnTypesEntity.getStatus())){
				throw new ServiceException(Message.UPDATE_DATE_NOEXIST);
			}
		}*/
        /**
         CoursePackage coursePackage = new CoursePackage();
         if(courseId!=null){
         coursePackage = coursePackageMapper.getById(courseId);
         if("02".equals(coursePackage.getStatus())){
         throw new ServiceException(Message.UPDATE_DATE_NOEXIST);
         }

         }
         **/
        examClassDao.insert(ExamClassOpt.buildAdd(examClassEntity));
    }

    @Override
    public void modifyExamClass(ExamClassEntity examClassEntity) throws Exception {
        ExamClassEntity bean = this.getExamClassById(examClassEntity.getId());
        if (bean == null) {
            throw new ServiceException(Message.UPDATE_DATE_NOEXIST);
        } else {
            examClassDao.update(ExamClassOpt.buildUpdate(bean, examClassEntity));
        }
    }

    @Override
    public void delExamClass(ExamClassEntity examClassEntity) throws Exception {
        ExamClassEntity bean = this.getExamClassById(examClassEntity.getId());
        if (bean == null) {
            throw new ServiceException(Message.UPDATE_DATE_NOEXIST);
        } else {
            examClassDao.update(ExamClassOpt.buildDelete(bean));
        }
    }

    @Override
    public ExamClassEntity getExamScores(Integer id) {
        // 获取班级信息
        ExamClassEntity bean = this.getExamClassById(id);
        if (bean == null) {
            return new ExamClassEntity();
        }
        // 获取班级下的学员信息
        ApplyOrdersEntity entity = new ApplyOrdersEntity();
        entity.setClassId(id);
        entity.setInstitutionInfoId(UserUtils.getUser().getInstitutionInfoId());
        entity.setHandleStatus(DBStatus.HandleStatus.SUCCESS);
        List<ApplyOrdersEntity> orderList = applyOrdersDao.findAllList(entity);

        if (orderList == null || orderList.size() < 1) {
            return bean;
        }

        UserScoresKey userScoresKey = null;
        int qualifiedNum = 0, unqualifiedNum = 0, lackExamNum = 0, totleNum = orderList.size(), inputNum = 0;
        Map<String, String> examResultMap = DBStatus.ExamResult.getExamResultAll;
        for (ApplyOrdersEntity orders : orderList) {
            userScoresKey = new UserScoresKey();
            userScoresKey.setClassId(id);
            userScoresKey.setStuUserInfoId(orders.getStuUserInfoId());
            UserScoresEntity userScores = userScoresDao.getByKey(userScoresKey);// 获取成绩
            StuUserInfoEntity stuUserInfoEntity = stuUserInfoDao.getById(orders.getStuUserInfoId());
            if (stuUserInfoEntity != null) {
                orders.setStuUserInfo(stuUserInfoEntity);
            }
            /**
             //UserBaseInfoEntity userBaseInfo = userBaseInfoDao.getById(orders.getUserId());// 获取用户基本信息
             //UserExtendInfoEntity userExtendInfo = userExtendInfoDao.getById(orders.getUserId());// 获取用户基本信息
             if (userBaseInfo != null) {
             orders.setUserBaseInfo(userBaseInfo);
             }
             if (userExtendInfo != null) {
             orders.setUserExtendInfo(userExtendInfo);
             }
             **/

            if (userScores != null) {
                if (userScores.getExamResult() != null) {
                    userScores.setExamResultName(examResultMap.get(userScores.getExamResult()));
                }
                inputNum++;
                orders.setUserScores(userScores);
                if (DBStatus.ExamResult.UN_PASS.equals(userScores.getExamResult())) {
                    unqualifiedNum++;
                }
                if (DBStatus.ExamResult.PASS.equals(userScores.getExamResult()) ||
                        DBStatus.ExamResult.GOOD.equals(userScores.getExamResult()) ||
                        DBStatus.ExamResult.FINE.equals(userScores.getExamResult())) {
                    qualifiedNum++;
                }
                if (DBStatus.ExamResult.MISSING.equals(userScores.getExamResult())) {
                    lackExamNum++;
                }
            }
        }

        bean.setApplyOrders(orderList);

        ExamResult examResult = new ExamResult();
        examResult.setInputNum(inputNum);
        examResult.setLackExamNum(lackExamNum);
        examResult.setQualifiedNum(qualifiedNum);
        examResult.setUnqualifiedNum(unqualifiedNum);
        examResult.setTotleNum(totleNum);

        bean.setExamResult(examResult);
        return bean;
    }

    @Override
    public Page<ExamClassEntity> getExamScoresPage(ExamClassEntity examClassEntity, int pageNumber, int pageSize) {
        try {
            // 获取总条数
            Map<Object, Object> map = ExamClassOpt.buildMap(examClassEntity);
            map.put("institutionInfoId", com.jiacer.modules.system.utils.UserUtils.getUser().getInstitutionInfoId());
            Integer totalCount = examClassDao.count(map);
            // 分页实体
            Page<ExamClassEntity> page = new Page<ExamClassEntity>();
            page.setPage(pageNumber);
            page.setRowNum(pageSize);
            if (totalCount == null) {
                return page;
            }
            // 最大页数判断
            int pageM = maxPage(totalCount, page.getRowNum(), page.getPage());
            if (pageM > 0) {
                page.setPage(pageM);
            }
            if (totalCount > 0) {
                map.put("offset", page.getOffset());
                map.put("pageSize", page.getRowNum());
                List<ExamClassEntity> list = examClassDao.getPageList(map);
                ExamClassEntity entity = null;
                for (ExamClassEntity exam : list) {
                    entity = new ExamClassEntity();
                    entity = getExamScores(exam.getId());
                    exam.setExamResult(entity.getExamResult());
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
    @Transactional(rollbackFor = Exception.class)
    public void addUserScores(UserScoresEntity userScoresEntity, Integer orderId) throws Exception {
        UserScoresKey userScoresKey = new UserScoresKey();
        userScoresKey.setStuUserInfoId(userScoresEntity.getStuUserInfoId());
        userScoresKey.setClassId(userScoresEntity.getClassId());
        UserScoresEntity bean = userScoresDao.getByKey(userScoresKey);
        Map map = new HashMap();
        map.put("stuUserInfoId", userScoresEntity.getStuUserInfoId());
        Integer sInstitutionInfoId = com.jiacer.modules.system.utils.UserUtils.getUser().getInstitutionInfoId();
        map.put("institutionInfoId", sInstitutionInfoId);
        map.put("classId", userScoresEntity.getClassId());
        map.put("handleStatus", "02");
        ApplyOrdersEntity applyOrdersEntity = applyOrdersDao.getUserIdClassIdApplyOrders(map);
        CoursePackageEntity coursePackageEntity = coursePackageDao.getById(applyOrdersEntity.getPackageId());
        LearnTypesEntity learnTypesEntity = coursesService.getById(applyOrdersEntity.getCourseId());

        if (bean == null) {
            userScoresEntity.setTypeOfWork(coursePackageEntity.getTypeOfWork());
            userScoresEntity.setCertName(learnTypesEntity.getCertName());
            userScoresEntity.setUserId(applyOrdersEntity.getUserId());
            userScoresEntity.setClassId(applyOrdersEntity.getClassId());
            if(userScoresEntity.getIssuingDate() == null
                    && StringUtils.isNotBlank(userScoresEntity.getIssuingDateTxt())
                    && StringUtils.isNotBlank(userScoresEntity.getCertificateNo())){
                //证书编号为空，则发证日期为空
                userScoresEntity.setIssuingDate(DateUtils.parseDate(userScoresEntity.getIssuingDateTxt()));
            }
            userScoresDao.insert(userScoresEntity);
        } else {
            bean.setTypeOfWork(coursePackageEntity.getTypeOfWork());
            bean.setCertName(learnTypesEntity.getCertName());
            bean.setUserId(applyOrdersEntity.getUserId());
            bean.setClassId(applyOrdersEntity.getClassId());
            userScoresDao.update(ScoresOpt.buildModify(bean, userScoresEntity));
        }
        if (userScoresEntity.getIsExam().equals("1")) {//补考
            ApplyOrdersEntity entity = applyOrdersDao.getById(applyOrdersEntity.getId());
            ApplyOrdersEntity entityNew = new ApplyOrdersEntity();
            entityNew.setHandleStatus(DBStatus.HandleStatus.PENDING);
            entityNew.setClassNumber(null);
            entityNew.setOrderStatus(DBStatus.OrderStatus.SUCCESS);
            entityNew.setOrderType(DBStatus.OrderType.BUKAO);
            entityNew.setMakeupFee(userScoresEntity.getMakeupExamFree());
            entityNew.setHandler(null);
            entityNew.setFormType("02"); //补考
            entityNew.setModifyTime(new Date());
            entityNew.setModifyAccount(UserUtils.getUser().getId());
            entityNew.setInstitutionInfoId(entity.getInstitutionInfoId());
            entityNew.setStuUserInfoId(entity.getStuUserInfoId());
            entityNew.setUserId(entity.getUserId());
            entityNew.setCourseId(entity.getCourseId());
            entityNew.setOrderTime(entity.getOrderTime());
            entityNew.setCourseId(entity.getCourseId());
            entityNew.setPackageId(entity.getPackageId());
            entityNew.setIsExam("0");
            entityNew.setIsStaging("0");
            entityNew.setIsHasPf(entity.getIsHasPf());
            entityNew.setSourceType(entity.getSourceType());
            entityNew.setSourceTypeSec(entity.getSourceTypeSec());
            entityNew.setSourceValue(entity.getSourceValue());
            entityNew.setSourceRemarks(entity.getSourceRemarks());
            entityNew.setSchoolFee(entity.getSchoolFee());
            entityNew.setBookFree(entity.getBookFree());
            entityNew.setFirstPay(entity.getFirstPay());
            entityNew.setHandler(com.jiacer.modules.system.utils.UserUtils.getUser().getId());
            entityNew.setHandleStatus("01");
            entityNew.setOrderTime(new Date());
            applyOrdersDao.insert(entityNew);
        }
    }

    @Override
    public UserScoresEntity getUserScores(UserScoresEntity userScoresEntity) {
        UserScoresKey userScoresKey = new UserScoresKey();
        userScoresKey.setClassId(userScoresEntity.getClassId());
        userScoresKey.setStuUserInfoId(userScoresEntity.getStuUserInfoId());
        UserScoresEntity bean = new UserScoresEntity();
        UserScoresEntity userScores = userScoresDao.getByKey(userScoresKey);
        if (userScores != null) {
            bean = userScores;
        }
        //课程
        Map map = new HashMap();
        map.put("classId", userScoresEntity.getClassId());
        Integer sInstitutionInfoId = com.jiacer.modules.system.utils.UserUtils.getUser().getInstitutionInfoId();
        map.put("institutionInfoId", sInstitutionInfoId);
        map.put("handleStatus", "02");
        map.put("stuUserInfoId", userScoresEntity.getStuUserInfoId());
        ApplyOrdersEntity applyOrdersEntity = applyOrdersDao.getUserIdClassIdApplyOrders(map);
        if (applyOrdersEntity != null) {
            LearnTypesEntity learnTypesEntity = coursesService.getById(applyOrdersEntity.getCourseId());
            bean.setLearnLypesName(learnTypesEntity.getTypeName());
            bean.setAuthenticateGradeName(DictionariesUtil.getAuthenticateGradeName(learnTypesEntity.getAuthenticateGrade()));
            bean.setCertAuthorityName(certAuthorityService.getById(learnTypesEntity.getAuthorityId()).getAuthorityName());
            bean.setCertAuthorityId(String.valueOf(learnTypesEntity.getAuthorityId()));
            bean.setAuthenticateGrade(learnTypesEntity.getAuthenticateGrade());
            bean.setCertName(learnTypesEntity.getCertName());
        }
        StuUserInfoEntity stuUserInfo = stuUserInfoDao.getById(userScoresEntity.getStuUserInfoId());
        if (stuUserInfo != null) {
            bean.setStuUserInfo(stuUserInfo);
        }
        bean.setClassId(userScoresEntity.getClassId());

        return bean;
    }

    //
    @Override
    public ExamClassEntity getExamClassInfo(Integer id) {
        ExamClassEntity bean = this.getExamClassById(id);
        if (bean == null) {
            return new ExamClassEntity();
        }
        // 获取班级下的学员信息
        ApplyOrdersEntity entity = new ApplyOrdersEntity();
        //entity.setClassNumber(id);
        entity.setClassId(id);
        entity.setHandleStatus(DBStatus.HandleStatus.SUCCESS);
        entity.setInstitutionInfoId(UserUtils.getUser().getInstitutionInfoId());
        List<ApplyOrdersEntity> orderList = applyOrdersDao.findAllList(entity);

        if (orderList == null || orderList.size() < 1) {
            return bean;
        }
        UserScoresKey usk = new UserScoresKey();
        usk.setClassId(id);
        for (ApplyOrdersEntity orders : orderList) {
            StuUserInfoEntity stuUserInfoEntity = stuUserInfoDao.getById(orders.getStuUserInfoId());
            orders.setStuUserInfo(stuUserInfoEntity);
            usk.setStuUserInfoId(orders.getStuUserInfoId());
            UserScoresEntity use = userScoresDao.getByKey(usk);
            if (use != null) {
                orders.setExamResult(DictionaryUtils.getExamResultName(use.getExamResult()));//是否合格
            }
        }
        bean.setApplyOrders(orderList);
        return bean;
    }

    // 考试信息导出
    @Override
    public Model dealExport(Model model, Integer id, HttpServletResponse response, HttpServletRequest request) {
        try {
            ExamClassEntity examClassEntity = this.getExamClassInfo(id);
            if (examClassEntity == null) {
                model.addAttribute("message", "没有数据可以导出");
                return model;
            }

            String fileName = "考试管理表" + DateUtils.getDate("yyyy-MM-dd") + ".xls";
            response.reset();
            response.addHeader("Content-Disposition",
                    "filename=" + new String(fileName.getBytes("gb2312"), "iso8859-1"));
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            OutputStream out = new BufferedOutputStream(response.getOutputStream());
            String filePath = "/WEB-INF/excelTemp/class_temp";
            ExcelOutput e = new ExcelOutput(out, filePath, request);
            e.getRow(1);// 获取
            e.setCell(1, examClassEntity.getClassName());
            e.setCell(3, DateUtils.formatDate(examClassEntity.getAddTime(), "yyyy-MM-dd"));
            e.getRow(2);// 获取
            //e.setCell(1, examClassEntity.getCourseName());
            e.setCell(3, examClassEntity.getSchoolName());
            e.getRow(3);// 获取
            e.setCell(1, examClassEntity.getClassNumber());
            e.getRow(6);// 获取
            e.setCell(1, DictionaryUtils.getExamType(examClassEntity.getExamForm()));
            e.setCell(3, examClassEntity.getExamStatusName());
            e.getRow(7);// 获取
            e.setCell(1, examClassEntity.getTheoryDate() != null
                    ? DateUtils.formatDate(examClassEntity.getTheoryDate(), "yyyy-MM-dd") : "");
            e.setCell(3, examClassEntity.getTheoryAddress());
            e.getRow(8);// 获取
            e.setCell(1, examClassEntity.getOperationDate() != null
                    ? DateUtils.formatDate(examClassEntity.getOperationDate(), "yyyy-MM-dd") : "");
            e.setCell(3, examClassEntity.getOperationAddress());

            List<ApplyOrdersEntity> orderList = examClassEntity.getApplyOrders();
            if (orderList == null || orderList.size() < 1) {
                e.export();
            }
            int total = orderList.size();
            e.getRow(10);// 获取
            e.setCell(0, "学员信息（" + total + "人）");
            int i = 1;
            e.createRow(10 + i);
            e.setCell(0, "序号");
            e.setCell(1, "报到日期");
            e.setCell(2, "姓名");
            e.setCell(3, "申报标号");
            e.setCell(4, "联系电话");
            e.setCell(5, "来源类型");
            e.setCell(6, "来源信息");
            e.setCell(7, "介绍人");
            e.setCell(8, "是否合格");
            e.setCell(9, "已支付返利");
            e.setCell(10, "支付日期");
            for (ApplyOrdersEntity order : orderList) {
                e.createRow(11 + i);
                String pn = order.getSourceValue();
                if (StringUtils.isNotBlank(order.getSourceValue())
                        && StringUtils.isNumeric(order.getSourceValue())) {
                    pn = partnersDao.getById(Integer.valueOf(order.getSourceValue())).getPartnerName();
                }
                e.setCell(0, i);
                e.setCell(1, order.getHandleTime() != null ? DateUtils.formatDate(order.getHandleTime(), "yyyyMMdd") : "-");
                e.setCell(2, order.getStuUserInfo() != null ? order.getStuUserInfo().getUserName() : "-");
                e.setCell(3, examClassEntity.getClassNumber() != null ? examClassEntity.getClassNumber() : "-");
                e.setCell(4, order.getStuUserInfo() != null ? order.getStuUserInfo().getMobile() : null);
                e.setCell(5, DictionaryUtils.getSourceTypeName(order.getSourceType()));
                e.setCell(6, pn == null ? "-" : pn);
                e.setCell(7, order.getStuUserInfo().getOwnedTeacher() == null ? "-" : order.getStuUserInfo().getOwnedTeacher());
                e.setCell(8, order.getExamResult() == null ? "-" : order.getExamResult());
                e.setCell(9, "");
                e.setCell(10, "");
                i++;
            }
            e.export();
        } catch (IOException ex) {
            logger.error("写入excel出错:" + ex.getMessage());
            model.addAttribute("message", "导出失败，程序异常");
        } catch (Exception e) {
            logger.error("excel出错:" + e.getMessage());
            model.addAttribute("message", "导出失败，程序异常");
        }
        return model;
    }

    // 考试成绩管理
    @Override
    public Model dealExportScores(Model model, Integer id, HttpServletResponse response, HttpServletRequest request) {
        try {
            System.out.println("=======" + id);
            ExamClassEntity examClassEntity = this.getExamScores(id);
            if (examClassEntity == null) {
                model.addAttribute("message", "没有数据可以导出");
                return model;
            }
            String fileName = "考试信息表" + DateUtils.getDate("yyyy-MM-dd") + ".xls";
            System.out.println("========" + fileName);
            response.reset();
            response.addHeader("Content-Disposition",
                    "filename=" + new String(fileName.getBytes("gb2312"), "iso8859-1"));
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            OutputStream out = new BufferedOutputStream(response.getOutputStream());
            String filePath = "/WEB-INF/excelTemp/scores_temp";
            ExcelOutput e = new ExcelOutput(out, filePath, request);
            e.getRow(1);// 获取
            e.setCell(1, examClassEntity.getClassName());
            e.setCell(3, DateUtils.formatDate(examClassEntity.getAddTime(), "yyyy-MM-dd"));
            e.getRow(2);// 获取
            e.setCell(3, examClassEntity.getSchoolName());
            e.getRow(3);// 获取
            e.setCell(1, examClassEntity.getClassNumber());
            e.getRow(6);// 获取
            e.setCell(1, DictionaryUtils.getExamType(examClassEntity.getExamForm()));
            e.setCell(3, examClassEntity.getExamStatusName());
            e.getRow(7);// 获取
            e.setCell(1, examClassEntity.getTheoryDate() != null
                    ? DateUtils.formatDate(examClassEntity.getTheoryDate(), "yyyy-MM-dd") : "");
            e.setCell(3, examClassEntity.getTheoryAddress());
            e.getRow(8);// 获取
            e.setCell(1, examClassEntity.getOperationDate() != null
                    ? DateUtils.formatDate(examClassEntity.getOperationDate(), "yyyy-MM-dd") : "");
            e.setCell(3, examClassEntity.getOperationAddress());

            e.getRow(11);// 获取
            // 报名数
            e.setCell(1,
                    examClassEntity.getExamResult() != null ? examClassEntity.getExamResult().getTotleNum() : null);
            // 录入数
            e.setCell(3,
                    examClassEntity.getExamResult() != null ? examClassEntity.getExamResult().getInputNum() : null);

            e.getRow(12);// 获取
            try {
                // 合格数
                e.setCell(1, examClassEntity.getExamResult() != null ? examClassEntity.getExamResult().getQualifiedNum()
                        : null);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            // 合格率
            e.setCell(3,
                    examClassEntity.getExamResult() != null
                            && examClassEntity.getExamResult().getQualifiedRate() != null
                            ? examClassEntity.getExamResult().getQualifiedRate().doubleValue() : null);

            List<ApplyOrdersEntity> orderList = examClassEntity.getApplyOrders();
            if (orderList == null || orderList.size() < 1) {
                e.export();
            }
            int total = orderList.size();
            System.out.println("==========================" + total);
            e.getRow(14);// 获取
            e.setCell(0, "学员成绩（" + total + "人）");
            int i = 1;
            e.createRow(14 + i);
            e.setCell(0, "姓名");
            e.setCell(1, "手机号码");
            e.setCell(2, "理论");
            e.setCell(3, "实操");
            e.setCell(4, "考试结果");
            e.setCell(5, "证书编号");
            e.setCell(6, "来源");
            for (ApplyOrdersEntity order : orderList) {
                e.createRow(15 + i);
                e.setCell(0, order.getUserExtendInfo() != null ? order.getUserExtendInfo().getUserName() : "");
                e.setCell(1, order.getUserBaseInfo() != null ? order.getUserBaseInfo().getMobile() : "");
                if (order.getUserScores() != null && null != order.getUserScores().getTheoryScores()
                        && !"".equals(order.getUserScores().getTheoryScores())) {
                    e.setCell(2, order.getUserScores() != null ? order.getUserScores().getTheoryScores().doubleValue()
                            : null);
                } else {
                    e.setCell(2, "");
                }
                if (order.getUserScores() != null && null != order.getUserScores().getPoScores()
                        && !"".equals(order.getUserScores().getPoScores())) {

                    e.setCell(3,
                            order.getUserScores() != null ? order.getUserScores().getPoScores().doubleValue() : null);
                } else {
                    e.setCell(3, "");
                }
                e.setCell(4, DictionaryUtils.getExamResultName(order.getUserScores().getExamResult()));
                e.setCell(5, order.getUserScores() != null ? order.getUserScores().getCertificateNo() : null);
                e.setCell(6, order.getSourceWholeText());
                i++;
            }
            e.export();
            System.out.println("============完成");
        } catch (IOException ex) {
            logger.error("写入excel出错:" + ex);
            model.addAttribute("message", "导出失败，程序异常");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("++++++++++++" + e.getMessage());
            model.addAttribute("message", "导出失败，程序异常");
        }
        return model;
    }

    @Override
    public List<LearnRecordEntity> getExamLearnRecords(Integer classId) {
        List<LearnRecordEntity> resultList = Lists.newArrayList();
        // 获取班级下的学员信息
        ApplyOrdersEntity entity = new ApplyOrdersEntity();
        entity.setClassNumber(classId);
        entity.setInstitutionInfoId(UserUtils.getUser().getInstitutionInfoId());
        entity.setHandleStatus(DBStatus.HandleStatus.SUCCESS);
        List<ApplyOrdersEntity> orderList = applyOrdersDao.findAllList(entity);
        if (orderList == null || orderList.size() < 1) {
            return resultList;
        }
        LearnRecordEntity obj = null;
        Map<Object, Object> map = Maps.newHashMap();
        for (ApplyOrdersEntity orders : orderList) {
            obj = new LearnRecordEntity();

            UserBaseInfoEntity userBaseInfoEntity = userBaseInfoDao.getById(orders.getUserId());
            UserExtendInfoEntity extendInfoEntity = userExtendInfoDao.getById(orders.getUserId());
            if (userBaseInfoEntity != null) {
                obj.setUserInfo(userBaseInfoEntity);
            }
            if (extendInfoEntity != null) {
                obj.setUserExtend(extendInfoEntity);
            }
            map.clear();
            map.put("classNumber", classId);
            map.put("isFinished", Global.YES);
            map.put("userId", orders.getUserId());
            LearnRecordEntity userRecord = userAnswersBatchDao.getLearnRecord(map);
            if (userRecord != null) {
                obj.setScores(userRecord.getScores());
                obj.setAnswersNum(userRecord.getAnswersNum());
            } else {
                obj.setScores(BigDecimal.ZERO);
                obj.setAnswersNum(0);
            }

            resultList.add(obj);
        }
        return resultList;
    }

    @Override
    public Model exportExamLearnRecords(Integer classId, Model model, HttpServletResponse response,
                                        HttpServletRequest request) {
        try {
            List<LearnRecordEntity> resultList = this.getExamLearnRecords(classId);
            if (resultList == null || resultList.size() < 1) {
                model.addAttribute("message", "没有数据可以导出");
            }
            String fileName = "学习记录表" + DateUtils.getDate("yyyy-MM-dd") + ".xls";
            response.reset();
            response.addHeader("Content-Disposition",
                    "filename=" + new String(fileName.getBytes("gb2312"), "iso8859-1"));
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            OutputStream out = new BufferedOutputStream(response.getOutputStream());
            ExcelOutput e = new ExcelOutput(out);
            int i = 0;
            for (LearnRecordEntity records : resultList) {
                e.createRow(i + 1);// 创建行数o
                if (records.getUserExtend() != null) {
                    e.setCell(1, records.getUserExtend().getUserName());
                }
                if (records.getUserInfo() != null) {
                    e.setCell(2, records.getUserInfo().getMobile());
                }
                e.setCell(3, records.getAnswersNum());
                e.setCell(4, records.getScores().doubleValue());
                i++;
            }
            e.createRow(0);// 表头
            e.setCell(1, "姓名");
            e.setCell(2, "联系方式");
            e.setCell(3, "练习次数");
            e.setCell(4, "最高成绩");
            e.export();
        } catch (IOException ex) {
            logger.error("写入excel出错:" + ex);
            model.addAttribute("message", "导出失败，程序异常");
        } catch (Exception e) {
            logger.error("写入excel出错:" + e);
            model.addAttribute("message", "导出失败，程序异常");
        }
        return model;
    }

    @Override
    public List<ControlEntity> getClassNumber() {
        // TODO Auto-generated method stub
        ExamClassEntity examClass = new ExamClassEntity();
        examClass.setInstitutionInfoId(UserUtils.getUser().getInstitutionInfoId());
        List<ExamClassEntity> list = examClassDao.findAllList(examClass);
        List<ControlEntity> cList = new ArrayList<ControlEntity>();
        for (int i = 0; i < list.size(); i++) {
            ExamClassEntity examClassEntity = list.get(i);
            cList.add(new ControlEntity(examClassEntity.getId().toString(), examClassEntity.getClassNumber()));
        }
        return cList;
    }

    @Override
    public ExamClassEntity getExamClass(Map<Object, Object> map) {
        // TODO Auto-generated method stub
        return examClassDao.getExamClass(map);
    }

    @Override
    public ExamClassEntity getById(Integer id) {
        // TODO Auto-generated method stub
        return examClassDao.getById(id);
    }

    @Override
    public List<ExamClassEntity> findAllList(Map<Object, Object> map) {
        // TODO Auto-generated method stub
        return examClassDao.findAllList(map);
    }

    @Override
    public List<ExamClassEntity> getCourseIdExamClass(Map<Object, Object> map) {
        // TODO Auto-generated method stub
        return examClassDao.getCourseIdExamClass(map);
    }

    @Override
    public List<ExamClassEntity> getCourseIdExamClassDsh(Map<Object, Object> map) {
        // TODO Auto-generated method stub
        return examClassDao.getCourseIdExamClassDsh(map);
    }


}










