package com.jiacer.modules.business.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import com.jiacer.modules.business.bean.form.LearnRecordsQuery;
import com.jiacer.modules.common.page.Page;
import com.jiacer.modules.mybatis.entity.extend.LearnRecordEntity;

/** 
* @ClassName: LearnRecordsService 
* @Description: TODO
* @author 贺章鹏
* @date 2016年11月7日 下午3:28:03 
*  
*/
public interface LearnRecordsService {

	/**
	 * @param learnRecordsQuery
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	Page<LearnRecordEntity> getPage(LearnRecordsQuery learnRecordsQuery, int pageNumber, int pageSize);

	/**
	 * @param model
	 * @param learnRecordsQuery
	 * @param response
	 * @param request
	 */
	Model dealExport(Model model, LearnRecordsQuery learnRecordsQuery, HttpServletResponse response,
			HttpServletRequest request);

}
