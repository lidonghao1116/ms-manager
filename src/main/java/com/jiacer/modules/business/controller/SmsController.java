package com.jiacer.modules.business.controller;

import com.jiacer.modules.business.service.SmsService;
import com.jiacer.modules.common.utils.JsonResult;
import com.jiacer.modules.common.utils.SessionUtils;
import com.jiacer.modules.system.config.MappingURL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = MappingURL.SMS_URL)
public class SmsController {

    @Autowired
    SmsService smsService;

    @RequestMapping(value = "/student/check/send", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult sendSms(String mobile) {
        smsService.sendCode(mobile);
        return new JsonResult(true, "success", null);
    }

}
