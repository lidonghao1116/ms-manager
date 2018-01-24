package com.jiacer.modules.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiacer.modules.business.utils.CoursesUtils;
import com.jiacer.modules.business.utils.SchoolsUtils;
import com.jiacer.modules.common.controller.BaseController;
import com.jiacer.modules.common.utils.JsonResult;
import com.jiacer.modules.system.config.MappingURL;
import com.jiacer.modules.system.config.Message;
import com.jiacer.modules.system.config.SysConstants;
import com.jiacer.modules.system.utils.AreasUtils;
import com.jiacer.modules.system.utils.ParamsUtils;

/** 
* @ClassName: CachesController 
* @Description: TODO
* @author 贺章鹏
* @date 2016年11月4日 下午1:54:53 
*  
*/
@Controller
@RequestMapping(MappingURL.CACHE_URL)
public class CachesController extends BaseController{
	
	
	@RequestMapping(MappingURL.FLUSH_URL)
	@ResponseBody
	public JsonResult flush(Model model,@PathVariable String type) {
		System.out.println("sssss");
		JsonResult jsonResult=null;
		try {
			if(type.equals(SysConstants.CacheType.TYPE_AREA)){
				AreasUtils.flush();
			}else if(type.equals(SysConstants.CacheType.TYPE_COURSE)){
				CoursesUtils.flush();
			}else if(type.equals(SysConstants.CacheType.TYPE_PARAMS)){
				ParamsUtils.flush();
			}else if(type.equals(SysConstants.CacheType.TYPE_SCHOOLS)){
				SchoolsUtils.flush();
			}else{
				return new JsonResult(false,Message.FAILED_MSG,null);
			}
			jsonResult=new JsonResult(true,Message.SUCCESS_MSG,null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(Message.buildErrInfo(Message.ERROR_EXCEPTION,e));
			jsonResult=new JsonResult(false,Message.FAILED_MSG,null);
		}
		return jsonResult;
	}
}
