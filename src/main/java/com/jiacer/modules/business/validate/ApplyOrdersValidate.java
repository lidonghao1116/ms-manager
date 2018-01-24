package com.jiacer.modules.business.validate;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.jiacer.modules.business.bean.HandleStatusType;
import com.jiacer.modules.business.service.StuUserInfoService;
import com.jiacer.modules.business.utils.ExamsUtils;
import com.jiacer.modules.common.utils.JsonResult;
import com.jiacer.modules.common.utils.StringUtils;
import com.jiacer.modules.mybatis.dao.UserExtendInfoMapper;
import com.jiacer.modules.mybatis.entity.ApplyOrdersEntity;
import com.jiacer.modules.mybatis.entity.ExamClassEntity;
import com.jiacer.modules.mybatis.entity.StuUserInfoEntity;
import com.jiacer.modules.mybatis.entity.UserBaseInfoEntity;
import com.jiacer.modules.mybatis.entity.UserExtendInfoEntity;
import com.jiacer.modules.system.utils.IdCardInfoUtils;

/**
 * @author 贺章鹏
 * @ClassName: ApplyOrdersValidate
 * @Description: 学员申请订单管理校验类
 * @date 2016年10月19日 下午4:13:49
 */
public class ApplyOrdersValidate {


    public static JsonResult modifyValidate(ApplyOrdersEntity applyOrdersEntity, String type) {
        if (HandleStatusType.SUCCESS_APPLY.getValue().equals(type)) {//已报名学员修改
            if (applyOrdersEntity.getClassNumber() == null) {
                return new JsonResult(false, "请选择班级编号", null);
            }
        } else if (HandleStatusType.SPECIAL.getValue().equals(type)) {//特殊处理--之前没有已通过的订单
            ApplyOrdersEntity param = applyOrdersEntity;
            if (param == null) {
                return new JsonResult(false, "参数错误", null);
            }
            if (StringUtils.isEmpty(param.getUserName())) {
                return new JsonResult(false, "请填写用户姓名", null);
            }

            if (StringUtils.isEmpty(param.getCertNo())) {
                return new JsonResult(false, "请填写身份证", null);
            }

            if (!BaseValidate.isIdcard(param.getCertNo())) {
                return new JsonResult(false, "请填写正确的身份证", null);
            }

            if (StringUtils.isEmpty(param.getHeadImage())) {
                return new JsonResult(false, "身份证头像读取失败，请重新读取身份证", null);
            }

            if (StringUtils.isEmpty(param.getEducation())) {
                return new JsonResult(false, "请选择学历", null);
            }

            if (StringUtils.isEmpty(param.getNation())) {
                return new JsonResult(false, "请选择民族", null);
            }

            if (StringUtils.isEmpty(param.getAddress())) {
                return new JsonResult(false, "请填写户籍地址", null);
            }

            if (StringUtils.isEmpty(param.getSourceType())) {
                return new JsonResult(false, "请选择来源", null);
            }

            if (StringUtils.isEmpty(param.getSourceTypeSec())) {
                return new JsonResult(false, "请选择来源", null);
            }

            if (StringUtils.isEmpty(param.getSourceValue())) {
                return new JsonResult(false, "请填写或选择来源", null);
            }

            if (StringUtils.isEmpty(applyOrdersEntity.getClassTime())) {
                return new JsonResult(false, "请选择上课时间", null);
            }

            if (applyOrdersEntity.getClassNumber() == null) {
                return new JsonResult(false, "请选择班级编号", null);
            }

            if (StringUtils.isEmpty(applyOrdersEntity.getIsExam())) {
                return new JsonResult(false, "请选择是否考试", null);
            }

            if (applyOrdersEntity.getSchoolFee() == null) {
                return new JsonResult(false, "请填写学费", null);
            }
        } else if (HandleStatusType.REP_APPLY.getValue().equals(type)) {//补考订单
            if (applyOrdersEntity.getClassNumber() == null) {
                return new JsonResult(false, "请选择班级编号", null);
            }
            if (applyOrdersEntity.getMakeupFee() == null) {
                return new JsonResult(false, "请填写补考金额", null);
            }
        } else if (HandleStatusType.PEND_APPLY.getValue().equals(type)) {
            if (StringUtils.isEmpty(applyOrdersEntity.getAddress())) {
                return new JsonResult(false, "请填写户籍地址", null);
            }

            if (StringUtils.isEmpty(applyOrdersEntity.getIsExam())) {
                return new JsonResult(false, "请选择是否考试", null);
            }

            if (StringUtils.isEmpty(applyOrdersEntity.getSourceType())) {
                return new JsonResult(false, "请选择来源", null);
            }

            if (StringUtils.isEmpty(applyOrdersEntity.getSourceTypeSec())) {
                return new JsonResult(false, "请选择来源", null);
            }
            if (applyOrdersEntity.getClassNumber() == null) {
                return new JsonResult(false, "请选择班级标号", null);
            }

            if (StringUtils.isEmpty(applyOrdersEntity.getIsExam())) {
                return new JsonResult(false, "请选择是否考试", null);
            }

            if (applyOrdersEntity.getSchoolFee() == null) {
                return new JsonResult(false, "请填写学费", null);
            }
        }
        return new JsonResult(true, null, null);
    }

    public static JsonResult addValidate(ApplyOrdersEntity applyOrdersEntity) {
        UserExtendInfoEntity param = applyOrdersEntity.getUserExtendInfo();
        UserBaseInfoEntity userParam = applyOrdersEntity.getUserBaseInfo();
        if (param == null || userParam == null) {
            return new JsonResult(false, "参数错误", null);
        }

        if (StringUtils.isEmpty(userParam.getMobile())) {
            return new JsonResult(false, "请填写手机号码", null);
        }

        if (!BaseValidate.isMobile(userParam.getMobile())) {
            return new JsonResult(false, "请填写正确的手机号码", null);
        }

        if (StringUtils.isEmpty(param.getUserName())) {
            return new JsonResult(false, "请填写用户姓名", null);
        }

        if (StringUtils.isEmpty(param.getCertNo())) {
            return new JsonResult(false, "请填写身份证", null);
        }

        if (!BaseValidate.isIdcard(param.getCertNo())) {
            return new JsonResult(false, "请填写正确的身份证", null);
        }

        if (StringUtils.isEmpty(param.getEducation())) {
            return new JsonResult(false, "请选择学历", null);
        }

        if (StringUtils.isEmpty(param.getNation())) {
            return new JsonResult(false, "请选择民族", null);
        }

        if (StringUtils.isEmpty(param.getBirthplace())) {
            return new JsonResult(false, "请选择籍贯", null);
        }

        if (StringUtils.isEmpty(applyOrdersEntity.getAddress())) {
            return new JsonResult(false, "请填写户籍地址", null);
        }

        if (StringUtils.isEmpty(applyOrdersEntity.getSourceType())) {
            return new JsonResult(false, "请选择来源", null);
        }

        if (StringUtils.isEmpty(applyOrdersEntity.getSourceTypeSec())) {
            return new JsonResult(false, "请选择来源", null);
        }

        if (StringUtils.isEmpty(applyOrdersEntity.getSourceValue())) {
            return new JsonResult(false, "请填写或选择来源", null);
        }

        if (applyOrdersEntity.getPackageId() == null) {
            return new JsonResult(false, "请选择产品", null);
        }

        if (applyOrdersEntity.getCourseId() == null) {
            return new JsonResult(false, "请选择课程", null);
        }

        if (StringUtils.isEmpty(applyOrdersEntity.getClassTime())) {
            return new JsonResult(false, "请选择上课时间", null);
        }

        if (applyOrdersEntity.getClassNumber() == null) {
            return new JsonResult(false, "请选择班级编号", null);
        }

        if (StringUtils.isEmpty(applyOrdersEntity.getIsExam())) {
            return new JsonResult(false, "请选择是否考试", null);
        }

        if (applyOrdersEntity.getSchoolFee() == null) {
            return new JsonResult(false, "请填写学费", null);
        }
        ExamClassEntity examClassEntity = ExamsUtils.getClassObjcet(applyOrdersEntity.getClassNumber());
        if (applyOrdersEntity.getCourseId() != examClassEntity.getCourseId()) {
            return new JsonResult(false, "所选班级没有此课程，请检查", null);
        }
        return new JsonResult(true, null, null);
    }

    public static JsonResult addFormValidate(ApplyOrdersEntity applyOrdersEntity) {

        //applyOrdersEntity.getUserName();//姓名
        if (StringUtils.isEmpty(applyOrdersEntity.getUserName())) {
            return new JsonResult(false, "请填写姓名", null);
        }
        //applyOrdersEntity.getCertNo();//身份证号
        if (StringUtils.isEmpty(applyOrdersEntity.getCertNo())) {
            return new JsonResult(false, "请填身份证号", null);
        } else {
            IdCardInfoUtils ie = new IdCardInfoUtils(applyOrdersEntity.getCertNo());
            if (ie.getProvince() == null || ie.getGender() == null || ie.getBirthday() == null) {
                return new JsonResult(false, "请填写正确的身份证号", null);
            }
        }
        //applyOrdersEntity.getPhoneNumber();//身份证号
        /**
         if(StringUtils.isEmpty(applyOrdersEntity.getPhoneNumber())){
         return new JsonResult(false,"请填写手机号",null);
         }
         **/
        //applyOrdersEntity.getEducation();//学历
        if (StringUtils.isEmpty(applyOrdersEntity.getEducation())) {
            return new JsonResult(false, "请填选中学历", null);
        }
        /**
         applyOrdersEntity.getIsExam();//性别
         if(StringUtils.isEmpty(userParam.getMobile())){
         return new JsonResult(false,"请填写手机号码",null);
         }
         **/
        /**
         //applyOrdersEntity.getAddress();
         if(StringUtils.isEmpty(applyOrdersEntity.getAddress())){
         return new JsonResult(false,"请填联系户籍地址",null);
         }
         //applyOrdersEntity.getContacts(); //紧急联系人
         if(StringUtils.isEmpty(applyOrdersEntity.getContacts())){
         return new JsonResult(false,"请填紧急联系人",null);
         }
         //applyOrdersEntity.getAddress(); //联系地址
         if(StringUtils.isEmpty(applyOrdersEntity.getAddress())){
         return new JsonResult(false,"请填联系地址",null);
         }
         //applyOrdersEntity.getNation();//民族
         if(StringUtils.isEmpty(applyOrdersEntity.getNation())){
         return new JsonResult(false,"请填选中民族",null);
         }
         //applyOrdersEntity.getContactPhone(); //紧急联系人电话
         if(StringUtils.isEmpty(applyOrdersEntity.getContactPhone())){
         return new JsonResult(false,"请填紧急联系人电话",null);
         }

         //来源
         if(StringUtils.isEmpty(applyOrdersEntity.getSourceType())){
         return new JsonResult(false,"请选择来源",null);
         }
         if(StringUtils.isEmpty(applyOrdersEntity.getSourceTypeSec())){
         return new JsonResult(false,"请选择来源",null);
         }
         if(applyOrdersEntity.getSourceValue() == null ||  applyOrdersEntity.getSourceValue() == 0){
         return new JsonResult(false,"请填写或选择来源",null);
         }
         **/
        //报考信息
        /**
         if(applyOrdersEntity.getPackageId()==null){
         return new JsonResult(false,"请选择产品",null);
         }
         if(applyOrdersEntity.getCourseId()==null){
         return new JsonResult(false,"请选择课程",null);
         }
         **/

        if (applyOrdersEntity.getClassNumber() == null) {
            return new JsonResult(false, "请选择班级编号", null);
        }
        /**
         if(StringUtils.isEmpty(applyOrdersEntity.getIsExam())){
         return new JsonResult(false,"请选择是否考试",null);
         }
         **/

        //收费信息
        if (applyOrdersEntity.getSchoolFee() == null) {
            return new JsonResult(false, "请填写学费", null);
        }
        /**
         if(StringUtils.isEmpty(applyOrdersEntity.getIsStaging())){
         return new JsonResult(false,"请选择是否分期",null);
         }
         **/

        return new JsonResult(true, null, null);
    }

}
