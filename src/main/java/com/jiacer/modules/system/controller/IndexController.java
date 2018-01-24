package com.jiacer.modules.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jiacer.modules.common.controller.BaseController;
import com.jiacer.modules.system.config.MappingURL;




@Controller
public class IndexController extends BaseController{
    /**
     * 进入首页
     * @param model
     * @return
     */
    @RequestMapping(value = {MappingURL.BASE_ADMIN_URL,MappingURL.BASE_ADMIN_INDEX_URL}, method = {RequestMethod.GET,RequestMethod.HEAD})
    public String index(Model model) {
        return "index";
    }

    @RequestMapping(MappingURL.ERROR_403_URL)
    public String unauthorizedUrl(Model model) {
        return "error/403";
    }
}
