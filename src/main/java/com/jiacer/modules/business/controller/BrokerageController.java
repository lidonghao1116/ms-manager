package com.jiacer.modules.business.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.jiacer.modules.business.service.BrokerageService;
import com.jiacer.modules.business.utils.ProductsUtils;
import com.jiacer.modules.business.validate.BrokerageValidate;
import com.jiacer.modules.common.controller.BaseController;
import com.jiacer.modules.common.page.Page;
import com.jiacer.modules.common.utils.JsonResult;
import com.jiacer.modules.mybatis.dao.CoursePackageMapper;
import com.jiacer.modules.mybatis.entity.BrokerageRulesEntity;
import com.jiacer.modules.mybatis.entity.BrokerageSchemeEntity;
import com.jiacer.modules.mybatis.entity.CoursePackageEntity;
import com.jiacer.modules.system.config.DBStatus;
import com.jiacer.modules.system.config.MappingURL;
import com.jiacer.modules.system.config.Message;
import com.jiacer.modules.system.config.SysConstants;
import com.jiacer.modules.system.utils.UserUtils;

/** 
* @ClassName: BrokerageController 
* @Description: 提成管理控制类
* @author 贺章鹏
* @date 2016年10月19日 下午12:54:31 
*  
*/
@Controller
@RequestMapping(MappingURL.BROKERAGE_URL)
public class BrokerageController extends BaseController{
	@Resource
	BrokerageService brokerageService;
	
	@Resource
	CoursePackageMapper CoursePackageDao;
	
	public BrokerageSchemeEntity getModel(Integer id){
		if(id != null){
			return brokerageService.getSchemeById(id);
		}else{
			return new BrokerageSchemeEntity();
		}
	}
	
	//新增修改页面
	@RequestMapping(MappingURL.FORM_URL)
	public String form(Model model,Integer id) {
		
		BrokerageSchemeEntity brokerageSchemeEntity=this.getModel(id);
		
		//List<CoursePackageEntity> list=ProductsUtils.init();
		CoursePackageEntity entity=new CoursePackageEntity();
		entity.setIsUsable(DBStatus.IsUsable.TRUE);
		entity.setInstitutionInfoId(UserUtils.getUser().getInstitutionInfoId());
		List<CoursePackageEntity> list=CoursePackageDao.findAllList(entity);
		
		model.addAttribute("products", list);
		if(brokerageSchemeEntity==null || brokerageSchemeEntity.getId() == null){
			model.addAttribute("update", Boolean.FALSE);
			model.addAttribute("model", null);
			return "modules/brokerages/form";
		}else{
			//规则所有值
			model.addAttribute("update", Boolean.TRUE);
			model.addAttribute("model", brokerageSchemeEntity);
			return "modules/brokerages/updateForm";
		}
		
	    
	}
	
	//列表页面
	@RequestMapping(MappingURL.LIST_URL)
	public String list(Model model) {
	    return "modules/brokerages/list";
	}
	
	//查询
	@RequestMapping(MappingURL.QUERY_URL)
	@ResponseBody
	public Page<BrokerageSchemeEntity> page(
			@RequestParam(value = "page", defaultValue = SysConstants.DEFAULT_PAGE_NO) int pageNumber,
			@RequestParam(value = "records", defaultValue = SysConstants.DEFAULT_PAGE_SIZE) int pageSize,
			Model model,BrokerageSchemeEntity brokerageSchemeEntity) {
		Page<BrokerageSchemeEntity> reslut=brokerageService.getBrokerageSchemePage(brokerageSchemeEntity, pageNumber, pageSize);
	    return reslut;
	}
	
	
	//新增
	@RequestMapping(MappingURL.ADD_URL)
	@ResponseBody
	public JsonResult add(Model model,@RequestBody BrokerageSchemeEntity brokerageSchemeEntity) {
		if(brokerageSchemeEntity == null){
			logger.info(Message.buildErrInfo(Message.PARAM_ERROR_EXCEPTION, "提成方案对象为null"));
			return new JsonResult(false,Message.PARAM_ERROR_MSG,null);
		}
		
		JsonResult validate=BrokerageValidate.addValidate(brokerageSchemeEntity);
		
		if(!validate.isSuccess()){
			return validate;
		}
		
		JsonResult jsonResult=null;
		
		try {
			brokerageService.addBrokerageScheme(brokerageSchemeEntity);
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
	public JsonResult modify(Model model,@RequestBody BrokerageSchemeEntity brokerageSchemeEntity) {
		if(brokerageSchemeEntity == null || brokerageSchemeEntity.getId() ==null){
			logger.info(Message.buildErrInfo(Message.PARAM_ERROR_EXCEPTION, "提成方案对象为null或提成方案对象id为空"));
			return new JsonResult(false,Message.PARAM_ERROR_MSG,null);
		}
		
		JsonResult validate=BrokerageValidate.modifyValidate(brokerageSchemeEntity);
		
		if(!validate.isSuccess()){
			return validate;
		}
		
		JsonResult jsonResult=null;
		
		try {
			brokerageService.modifyBrokerageScheme(brokerageSchemeEntity);
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
	public JsonResult delete(Model model,BrokerageSchemeEntity brokerageSchemeEntity) {
		if(brokerageSchemeEntity == null || brokerageSchemeEntity.getId() ==null){
			logger.info(Message.buildErrInfo(Message.PARAM_ERROR_EXCEPTION, "提成方案对象为null或提成方案对象id为空"));
			return new JsonResult(false,Message.PARAM_ERROR_MSG,null);
		}
		
		JsonResult jsonResult=null;
		
		try {
			brokerageService.delBrokerageScheme(brokerageSchemeEntity);
			jsonResult=new JsonResult(true,Message.SUCCESS_MSG,null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(Message.buildErrInfo(Message.ERROR_EXCEPTION,e));
			jsonResult=new JsonResult(false,Message.FAILED_MSG,null);
		}
		return jsonResult;
	}
	
	@RequestMapping("/rules")
	@ResponseBody
	public JsonResult getRules(Integer id) {
		if(id == null){
			logger.info(Message.buildErrInfo(Message.PARAM_ERROR_EXCEPTION, "参数id为空"));
			return new JsonResult(false,Message.PARAM_ERROR_MSG,null);
		}
		
		JsonResult jsonResult=null;
		
		try {
			BrokerageRulesEntity rule=new BrokerageRulesEntity();
			rule.setSchemeId(id);
			List<BrokerageRulesEntity> rules=brokerageService.findRules(rule);
			jsonResult=new JsonResult(true,Message.SUCCESS_MSG,rules);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(Message.buildErrInfo(Message.ERROR_EXCEPTION,e));
			jsonResult=new JsonResult(false,Message.FAILED_MSG,null);
		}
		return jsonResult;
	}
}
