package com.jiacer.modules.business.service;

import java.util.List;

import com.jiacer.modules.common.page.Page;
import com.jiacer.modules.mybatis.entity.BrokerageRulesEntity;
import com.jiacer.modules.mybatis.entity.BrokerageSchemeEntity;

/** 
* @ClassName: BrokerageService 
* @Description: 佣金管理服务接口
* @author 贺章鹏
* @date 2016年10月19日 下午4:05:42 
*  
*/
public interface BrokerageService {

	/**
	 * 根据id获取方案对象
	 * @param id
	 * @return
	 */
	BrokerageSchemeEntity getSchemeById(Integer id);

	/**
	 * 提成方案分页
	 * @param brokerageSchemeEntity
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	Page<BrokerageSchemeEntity> getBrokerageSchemePage(BrokerageSchemeEntity brokerageSchemeEntity, int pageNumber,
			int pageSize);

	/**
	 * 新增方案
	 * @param brokerageSchemeEntity
	 * @throws Exception
	 */
	void addBrokerageScheme(BrokerageSchemeEntity brokerageSchemeEntity) throws Exception;

	/**
	 * 修改方案
	 * @param brokerageSchemeEntity
	 * @throws Exception
	 */
	void modifyBrokerageScheme(BrokerageSchemeEntity brokerageSchemeEntity) throws Exception;

	/**
	 * 删除方案
	 * @param brokerageSchemeEntity
	 * @throws Exception
	 */
	void delBrokerageScheme(BrokerageSchemeEntity brokerageSchemeEntity);

	/**
	 * @param brokerageRulesEntity
	 * @return
	 */
	List<BrokerageRulesEntity> findRules(BrokerageRulesEntity brokerageRulesEntity);
	
	/**
	 * 
	 * @param brokerageSchemeEntity
	 * @return
	 */
	List<BrokerageSchemeEntity> findSchemes(BrokerageSchemeEntity brokerageSchemeEntity);

}
