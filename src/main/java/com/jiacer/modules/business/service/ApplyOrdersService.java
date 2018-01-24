package com.jiacer.modules.business.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import com.jiacer.modules.common.page.Page;
import com.jiacer.modules.common.utils.JsonResult;
import com.jiacer.modules.mybatis.entity.ApplyOrdersEntity;

/** 
* @ClassName: ApplyOrdersService 
* @Description: 学员申请订单管理接口服务
* @author 贺章鹏
* @date 2016年10月19日 下午4:05:11 
*  
*/
public interface ApplyOrdersService {

	/**
	 * 报名管理分页
	 * @param applyOrdersEntity
	 * @param type 
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	Page<ApplyOrdersEntity> getApplyOrdersPage(ApplyOrdersEntity applyOrdersEntity, String type, int pageNumber, int pageSize);

	/**
	 * 报名管理分页 - 已报名 - 未通过 - 补考
	 * @param applyOrdersEntity
	 * @param type 
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	Page<ApplyOrdersEntity> getApplyOrdersStuUserPage(ApplyOrdersEntity applyOrdersEntity, String type, int pageNumber, int pageSize);
	
	/**
	 * 修改报名订单
	 * @param applyOrdersEntity
	 * @return 
	 * @throws Exception
	 */
	JsonResult modifyApplyOrders(ApplyOrdersEntity applyOrdersEntity,String type) throws Exception;

	/**
	 * @param id
	 * @return
	 */
	ApplyOrdersEntity getApplyOrders(Integer id);

	/**
	 * 新增报名订单
	 * @param applyOrdersEntity
	 * @return 
	 */
	JsonResult addApplyOrders(ApplyOrdersEntity applyOrdersEntity)  throws Exception;

	/**
	 * @param packageId
	 * @return
	 */
	List<ApplyOrdersEntity> getOrderProduct(Integer packageId);

	/**
	 * 处理订单
	 * @param applyOrdersEntity
	 * @return 
	 * @throws Exception
	 */
	JsonResult handleApplyOrders(ApplyOrdersEntity applyOrdersEntity) throws Exception;

	/**
	 * 补考
	 * @param applyOrdersEntity
	 * @return
	 * @throws Exception
	 */
	JsonResult rehandleApplyOrders(ApplyOrdersEntity applyOrdersEntity) throws Exception;

	/**
	 * 新增学员并处理订单
	 * @param applyOrdersEntity
	 * @return 
	 * @throws Exception
	 */
	JsonResult addHandleApplyOrders(ApplyOrdersEntity applyOrdersEntity) throws Exception;
	
	/**
	 * 通过用户信息是否通过
	 * @param orderId
	 * @return
	 */
	boolean checkIsHasPass(Integer orderId);

	/**
	 * 特殊处理订单--需要修改用户信息记录用户信息并且修改订单信息
	 * @param applyOrdersEntity
	 * @return 
	 */
	JsonResult specialHandleApplyOrders(ApplyOrdersEntity applyOrdersEntity);

	/**
	 * 处理已报名学员excel导出功能
	 * @param model
	 * @param entity
	 * @param response
	 * @return 
	 */
	Model dealExport(Model model, ApplyOrdersEntity entity,HttpServletResponse response);

	/**
	 * @param applyOrdersEntity
	 * @return
	 * @throws Exception
	 */
	JsonResult handleUnPassApplyOrders(ApplyOrdersEntity applyOrdersEntity) throws Exception;
	
	ApplyOrdersEntity getById(Integer id);

}
