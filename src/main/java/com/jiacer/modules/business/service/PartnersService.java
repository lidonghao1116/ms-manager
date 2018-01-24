package com.jiacer.modules.business.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import com.jiacer.modules.common.page.Page;
import com.jiacer.modules.mybatis.entity.ApplyOrdersEntity;
import com.jiacer.modules.mybatis.entity.LearnTypesEntity;
import com.jiacer.modules.mybatis.entity.PartnersEntity;

/**
 * @ClassName: PartnersService
 * @Description: 合作商接口管理服务
 * @author 贺章鹏
 * @date 2016年10月19日 下午4:06:34
 * 
 */
public interface PartnersService {

	Integer count(Map<Object, Object> map);
	/**
	 * 根据id获取合作商对象
	 * 
	 * @param id
	 * @return
	 */
	PartnersEntity getPartnersById(Integer id);
	
	PartnersEntity getById(Integer id);

	/**
	 * 根据id获取合作商对象
	 * 
	 * @param id
	 * @return
	 */
	PartnersEntity getByLearnName(Integer id);

	/**
	 * 合作商分页查询
	 * 
	 * @param partnersEntity
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	Page<PartnersEntity> getPartnersPage(PartnersEntity partnersEntity, int pageNumber, int pageSize);

	/**
	 * 新增合作商
	 * 
	 * @param partnersEntity
	 * @throws Exception
	 */
	void addPartners(PartnersEntity partnersEntity) throws Exception;

	/**
	 * 修改合作商
	 * 
	 * @param partnersEntity
	 * @throws Exception
	 */
	void modifyPartners(PartnersEntity partnersEntity) throws Exception;

	/**
	 * 删除合作商
	 * 
	 * @param partnersEntity
	 * @throws Exception
	 */
	void delPartner(PartnersEntity partnersEntity) throws Exception;

	/**
	 * 处理已报名学员excel导出功能
	 * 
	 * @param model
	 * @param entity
	 * @param response
	 * @return
	 */
	Model dealExport(Model model, PartnersEntity partnersEntity, HttpServletResponse response,HttpServletRequest request);

	// List<PartnersEntity> getBySoucerValueId(Map<Object, Object> map);

	List<PartnersEntity>  getBySoucerValueId(Integer id);
}
