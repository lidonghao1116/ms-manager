package com.jiacer.modules.system.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jiacer.modules.common.controller.BaseController;
import com.jiacer.modules.common.utils.RandomValidateCode;
import com.jiacer.modules.system.config.MappingURL;


@Controller
@RequestMapping(MappingURL.COMMON_BASE_URL)
public class CommonController extends BaseController {
	
	
    @RequestMapping(value = MappingURL.CAPTCHA_URL, method=RequestMethod.GET)
	public void getCaptcha(HttpServletRequest request, HttpServletResponse response) {
		try {
			RandomValidateCode randomCode = new RandomValidateCode();
			randomCode.getRandcode(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
