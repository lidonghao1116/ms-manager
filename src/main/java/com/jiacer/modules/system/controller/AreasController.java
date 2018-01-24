package com.jiacer.modules.system.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.jiacer.modules.common.controller.BaseController;
import com.jiacer.modules.log.Log;
import com.jiacer.modules.mybatis.entity.AreasEntity;
import com.jiacer.modules.system.config.MappingURL;
import com.jiacer.modules.system.service.AreasService;
import com.jiacer.modules.system.utils.AreasUtils;

/** 
* @ClassName: AreasController 
* @Description: 区域控制类
* @author 贺章鹏
* @date 2016年11月3日 下午2:21:56 
*  
*/
@Controller
@RequestMapping(MappingURL.SYSTEM_BASE_URL+MappingURL.AREAS_URL)
public class AreasController extends BaseController {
	
	@Autowired
	AreasService areasService;
	
	@RequestMapping(value = MappingURL.AREAS_INIT_URL)
	@ResponseBody
	public List<AreasEntity> initArears() {
		List<AreasEntity> list = Lists.newArrayList();
		try {
			List<AreasEntity> sourcelist=AreasUtils.init();
			AreasEntity.sortList(list, sourcelist, AreasEntity.getRootCode(), true);
		} catch (Exception e) {
			Log.error("获取区域参数失败");
			e.printStackTrace();
		}
		return list;
	}
}
