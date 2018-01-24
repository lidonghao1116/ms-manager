package com.jiacer.modules.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiacer.modules.system.config.MappingURL;
import com.jiacer.modules.system.service.ConstansService;

@Controller
@RequestMapping(MappingURL.SYSTEM_BASE_URL+MappingURL.CONSTANTS_URL)
public class ConstantsController {
    @Autowired
	ConstansService service;
    
	@RequestMapping(MappingURL.IS_SHOW_SYS_MENU)
	@ResponseBody
    public String getConstantsByKey(String constKey)
    {
    	String value=service.getConstValue(constKey);
    	return value;
    }
}
