package com.jiacer.modules.business.validate;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.jiacer.modules.business.bean.UserBean;
import com.jiacer.modules.business.service.UsersService;
import com.jiacer.modules.common.utils.DateUtils;
import com.jiacer.modules.common.utils.JsonResult;
import com.jiacer.modules.common.utils.StringUtils;
import com.jiacer.modules.mybatis.entity.UserBaseInfoEntity;
import com.jiacer.modules.mybatis.entity.UserExtendInfoEntity;
import com.jiacer.modules.mybatis.model.UserBaseInfo;

/**
 * @author 贺章鹏
 * @ClassName: UsersValidate
 * @Description: 会员管理校验类
 * @date 2016年10月19日 下午4:17:36
 */
public class UsersValidate {

    /**
     * @param userBean
     * @return
     */
    public static JsonResult modifyValidate(UserBean userBean, UsersService usersService) {
        UserBaseInfoEntity userInfo = userBean.getUserInfo();
        UserExtendInfoEntity userExtend = userBean.getUserExtend();

        if (StringUtils.isEmpty(userInfo.getMobile())) {
            return new JsonResult(false, "请填写手机号码", null);
        }
        if (!BaseValidate.isMobile(userInfo.getMobile())) {
            return new JsonResult(false, "请填写正确的手机号码", null);
        }

        if (StringUtils.isEmpty(userExtend.getUserName())) {
            return new JsonResult(false, "请填写用户姓名", null);
        }

        if (StringUtils.isEmpty(userExtend.getCertNo())) {
            return new JsonResult(false, "请填写身份证", null);
        } else {
            UserExtendInfoEntity userExtendInfoEntityOld = usersService.getById(userExtend.getUserId());
            if (!userExtendInfoEntityOld.getCertNo().equals(userBean.getUserExtend().getCertNo())) {
                Map map = new HashMap();
                map.put("certNo", userBean.getUserExtend().getCertNo());
                if (usersService.getCertNoUserExtendInfo(map) != null) {
                    return new JsonResult(false, "身份证信息已存在，请重新输入", null);
                }
            }
        }
        if (!BaseValidate.isIdcard(userExtend.getCertNo())) {
            return new JsonResult(false, "请填写正确的身份证", null);
        }

        if (StringUtils.isEmpty(userExtend.getEducation())) {
            return new JsonResult(false, "请选择学历", null);
        }

        if (StringUtils.isEmpty(userExtend.getNation())) {
            return new JsonResult(false, "请选择名族", null);
        }

        if (StringUtils.isEmpty(userExtend.getBirthplace())) {
            return new JsonResult(false, "请选择籍贯", null);
        }

        if (StringUtils.isEmpty(userExtend.getAddress())) {
            return new JsonResult(false, "请填写户籍地址", null);
        }
        return new JsonResult(true, null, null);
    }

    public static JsonResult IdCardValidate(UserBaseInfo userInfo) {

        if (StringUtils.isEmpty(userInfo.getUserName())) {
            return new JsonResult(false, "请填写用户姓名", null);
        }

        if (StringUtils.isEmpty(userInfo.getCertNo())) {
            return new JsonResult(false, "请填写身份证", null);
        }

        if (!BaseValidate.isIdcard(userInfo.getCertNo())) {
            return new JsonResult(false, "请填写正确的身份证", null);
        }

        if (StringUtils.isEmpty(userInfo.getBirthplace())) {
            return new JsonResult(false, "请选择籍贯", null);
        }
        if (StringUtils.isEmpty(userInfo.getAddress())) {
            return new JsonResult(false, "请填写户籍地址", null);
        }
        if (StringUtils.isBlank(userInfo.getIssueOrg())) {
            return new JsonResult(false, "发证机构不能为空", null);
        }
        if (StringUtils.isBlank(userInfo.getExpiredTime())) {
            return new JsonResult(false, "证件过期日期不能为空", null);
        }
        if(!userInfo.getExpiredTime().contains("长期")){
            Date dt = DateUtils.parseDate(userInfo.getExpiredTime());
            if (dt != null && dt.before(Calendar.getInstance().getTime())) {
                return new JsonResult(false, "证件已过期", null);
            }
        }
        return new JsonResult(true, null, null);
    }

}
