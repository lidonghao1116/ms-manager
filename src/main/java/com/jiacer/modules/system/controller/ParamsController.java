package com.jiacer.modules.system.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.common.collect.Lists;
import com.jiacer.modules.common.controller.BaseController;
import com.jiacer.modules.common.page.Page;
import com.jiacer.modules.common.utils.JsonResult;
import com.jiacer.modules.common.utils.StringUtils;
import com.jiacer.modules.log.Log;
import com.jiacer.modules.mybatis.entity.CfgParamEntity;
import com.jiacer.modules.system.config.MappingURL;
import com.jiacer.modules.system.config.Message;
import com.jiacer.modules.system.config.SysConstants;
import com.jiacer.modules.system.service.SystemService;
import com.jiacer.modules.system.utils.ParamsUtils;

/** 
* @ClassName: ParamsController 
* @Description: 参数管理
* @author 贺章鹏
* @date 2016年10月19日 下午3:27:28 
*  
*/
@Controller
@RequestMapping(MappingURL.SYSTEM_BASE_URL+MappingURL.PARAMS_URL)
public class ParamsController extends BaseController{
	
	@Resource
	SystemService systemService;
	
	public CfgParamEntity getModel(Integer id){
		if(id != null){
			return systemService.getParamById(id);
		}else{
			return new CfgParamEntity();
		}
	}
	
	//新增修改页面
	@RequestMapping(MappingURL.FORM_URL)
	public String form(Model model,Integer id) {
		
		CfgParamEntity cfgParamEntity=this.getModel(id);
		
		if(cfgParamEntity==null || cfgParamEntity.getId() == null){
			model.addAttribute("update", Boolean.FALSE);
			model.addAttribute("model", null);
		}else{
			model.addAttribute("update", Boolean.TRUE);
			model.addAttribute("model", cfgParamEntity);
		}
	    return "modules/system/params/form";
	}
	
	//列表页面
	@RequestMapping(MappingURL.LIST_URL)
	public String list(Model model) {
	    return "modules/system/params/list";
	}
	
	//查询
	@RequestMapping(MappingURL.QUERY_URL)
	@ResponseBody
	public Page<CfgParamEntity> page(
			@RequestParam(value = "page", defaultValue = SysConstants.DEFAULT_PAGE_NO) int pageNumber,
			@RequestParam(value = "records", defaultValue = SysConstants.DEFAULT_PAGE_SIZE) int pageSize,
			Model model,CfgParamEntity cfgParamEntity) {
		Page<CfgParamEntity> reslut=systemService.getParamsPage(cfgParamEntity, pageNumber, pageSize);
	    return reslut;
	}
	
	
	//新增
	@RequestMapping(MappingURL.ADD_URL)
	@ResponseBody
	public JsonResult add(Model model,CfgParamEntity cfgParamEntity) {
		if(cfgParamEntity == null){
			logger.info(Message.buildErrInfo(Message.PARAM_ERROR_EXCEPTION, "字典参数对象为null"));
			return new JsonResult(false,Message.PARAM_ERROR_MSG,null);
		}
		
		JsonResult jsonResult=null;
		
		try {
			systemService.addParams(cfgParamEntity);
			jsonResult=new JsonResult(true,Message.SUCCESS_MSG,null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(Message.buildErrInfo(Message.ERROR_EXCEPTION,e));
			jsonResult=new JsonResult(false,Message.FAILED_MSG,null);
		}
		return jsonResult;
	}
	
	//修改
	@RequestMapping(MappingURL.MODIFY_URL)
	@ResponseBody
	public JsonResult modify(Model model,CfgParamEntity cfgParamEntity) {
		if(cfgParamEntity == null || cfgParamEntity.getId() ==null){
			logger.info(Message.buildErrInfo(Message.PARAM_ERROR_EXCEPTION, "字典参数对象为null或字典参数id为空"));
			return new JsonResult(false,Message.PARAM_ERROR_MSG,null);
		}
		
		JsonResult jsonResult=null;
		
		try {
			systemService.modifyParams(cfgParamEntity);
			jsonResult=new JsonResult(true,Message.SUCCESS_MSG,null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(Message.buildErrInfo(Message.ERROR_EXCEPTION,e));
			jsonResult=new JsonResult(false,Message.FAILED_MSG,null);
		}
		return jsonResult;
	}
	
	//删除
	@RequestMapping(MappingURL.DELETE_URL)
	@ResponseBody
	public JsonResult delete(Model model,CfgParamEntity cfgParamEntity) {
		if(cfgParamEntity == null || cfgParamEntity.getId() ==null){
			logger.info(Message.buildErrInfo(Message.PARAM_ERROR_EXCEPTION, "字典参数对象为null或字典参数id为空"));
			return new JsonResult(false,Message.PARAM_ERROR_MSG,null);
		}
		
		JsonResult jsonResult=null;
		
		try {
			systemService.delParams(cfgParamEntity);
			jsonResult=new JsonResult(true,Message.SUCCESS_MSG,null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(Message.buildErrInfo(Message.ERROR_EXCEPTION,e));
			jsonResult=new JsonResult(false,Message.FAILED_MSG,null);
		}
		return jsonResult;
	}
	
	@RequestMapping(value = "")
	@ResponseBody
	public List<CfgParamEntity> getPamams(Integer type,Integer pid,String values) {
		List<CfgParamEntity> list = Lists.newArrayList();
		try {
			if(!StringUtils.isEmpty(values)){
				list=ParamsUtils.getParamsList(type, pid, values);
			}else{
				list=ParamsUtils.getParams(type,pid);
			}
		} catch (Exception e) {
			Log.error("获取参数列表列表失败");
			e.printStackTrace();
		}
		return list;
	}
}
