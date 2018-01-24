package com.jiacer.modules.business.service.impl;

import com.jiacer.modules.business.service.SmsService;
import com.jiacer.modules.business.service.sms.SendSmsYTX;
import com.jiacer.modules.business.service.sms.SmsSendResult;
import com.jiacer.modules.business.service.sms.SmsTemplates;
import com.jiacer.modules.business.utils.JsonUtils;
import com.jiacer.modules.common.config.Global;
import com.jiacer.modules.common.utils.SessionUtils;
import com.jiacer.modules.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class SmsServiceImpl implements SmsService {
    private static final Logger log = LoggerFactory.getLogger(SmsServiceImpl.class);

    private static final String SMS_KEY = "MS.SCHOOL.STUDENT.INSERT.SMS.%s";

    @Autowired
    SendSmsYTX sms;

    @Override
    public void sendCode(String mobile) {
        String code = (String) SessionUtils.getSession().getAttribute(String.format(SMS_KEY, mobile));
        if (StringUtils.isBlank(code)) {
            code = String.valueOf(new Random().nextInt(9000) + 1000); // 注册验证码4位随机数字;
        }
        int liveMinites = 10; // 有效时间10分钟
        String[] datas = new String[]{code, liveMinites + "分钟"};
        String templateId = SmsTemplates.MS_MANAGER_ADD_STUDENT; // 模板ID
        log.info(generateSmsContent(templateId, datas));
        String sendSmsbj = Global.getConfig("sendSmsbj");
        if (null != sendSmsbj && "yes".equals(sendSmsbj)) {
            // 发送短信-正式环境预生产环境发送短信
            SmsSendResult sendResult = sms.send(mobile, templateId, datas);
            if (SmsSendResult.SUCCESS.equals(sendResult.getResultCode())) { // 发送成功
                SessionUtils.getSession().setAttribute(String.format(SMS_KEY, mobile), code);
            }
            log.info(JsonUtils.toJson(sendResult));
        }
    }

    @Override
    public boolean checkCode(String mobile, String code) {
        if (StringUtils.isBlank(mobile) || StringUtils.isBlank(code)) {
            return false;
        }
        String validCode = (String) SessionUtils.getSession().getAttribute(String.format(SMS_KEY, mobile));
        if (code.equals(validCode)) {
            return true;
        }
        return false;
    }

    /**
     * 生成短信内容
     *
     * @param templateId 短信模板ID
     * @param datas      参数
     * @return
     */
    private String generateSmsContent(String templateId, String[] datas) {
        String content = SmsTemplates.getContent(templateId); // 模板内容
        if (StringUtils.isNotBlank(content)) {
            int len = datas.length;
            String key = null;
            String value = null;
            for (int i = 0; i < len; i++) {
                key = "{" + (i + 1) + "}";
                value = datas[i] == null ? "" : datas[i];
                try {
                    content = content.replace(key, value);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return content;
    }
}
