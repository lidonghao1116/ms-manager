package com.jiacer.modules.business.service.sms;

import java.util.HashMap;
import java.util.Map;

public class SmsTemplates {

    /**
     * 注册验证码模板ID
     */
    public static final String REG_SMS_CODE = "165385";
    /**
     * 重置密码验证码模板ID
     */
    public static final String RESET_PWD_SMS_CODE = "158468";

    /**
     * 学校管理系统新增学员
     */
    public static final String MS_MANAGER_ADD_STUDENT = "215568";

    /**
     * 支付尾款成功模板ID
     */
    public static final String PAYMENT_SUCCESS = "173570";
    /**
     * 支付定金成功模板ID
     */
    public static final String PAYDJ_SUCCESS = "174917";
    /**
     * 支付成功模板ID
     */
    public static final String PAY_SUCCESS = "173570";
    /**
     * 短信模板内容
     */
    private static Map<String, String> CONTENT;

    static {
        CONTENT = new HashMap<String, String>();
        CONTENT.put(MS_MANAGER_ADD_STUDENT, "【家策健康学院】学员审核验证码：{1}，{2}分钟内有效。");
    }

    public static String getContent(String templateId) {
        return CONTENT.get(templateId);
    }

}
