package com.jiacer.modules.business.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.jiacer.modules.business.bean.form.LearnRecordsQuery;
import com.jiacer.modules.business.service.LearnRecordsService;
import com.jiacer.modules.common.controller.BaseController;
import com.jiacer.modules.common.page.Page;
import com.jiacer.modules.common.utils.StringUtils;
import com.jiacer.modules.mybatis.entity.extend.LearnRecordEntity;
import com.jiacer.modules.system.config.MappingURL;
import com.jiacer.modules.system.config.SysConstants;

/**
 * @ClassName: UserLearnRecordsController
 * @Description: 学习记录
 * @author 贺章鹏
 * @date 2016年11月7日 下午3:26:22
 * 
 */
@Controller
@RequestMapping(MappingURL.LEARN_URL)
public class LearnRecordsController extends BaseController {

	@Autowired
	LearnRecordsService learnRecordsService;

	// 列表页面
	@RequestMapping(MappingURL.LIST_URL)
	public String list(Model model) {
		return "modules/learnRecords/list";
	}

	// 查询
	@RequestMapping(MappingURL.QUERY_URL)
	@ResponseBody
	public Page<LearnRecordEntity> page(
			@RequestParam(value = "page", defaultValue = SysConstants.DEFAULT_PAGE_NO) int pageNumber,
			@RequestParam(value = "records", defaultValue = SysConstants.DEFAULT_PAGE_SIZE) int pageSize, Model model,
			LearnRecordsQuery learnRecordsQuery) {
		Page<LearnRecordEntity> reslut = learnRecordsService.getPage(learnRecordsQuery, pageNumber, pageSize);
		return reslut;
	}

	@RequestMapping(MappingURL.EXPORT)
	public String export(Model model, HttpServletResponse response, HttpServletRequest request,
			@PathVariable String type, LearnRecordsQuery learnRecordsQuery) {

		if (StringUtils.isEmpty(type)) {
			model.addAttribute("message", "参数错误");
		} else {
			learnRecordsService.dealExport(model, learnRecordsQuery, response, request);
		}
		return "modules/excel/info";
	}

}
