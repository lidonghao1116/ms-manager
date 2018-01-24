package com.jiacer.modules.business.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiacer.modules.business.bean.operation.BrokerageOpt;
import com.jiacer.modules.business.service.BrokerageService;
import com.jiacer.modules.common.page.Page;
import com.jiacer.modules.common.service.BaseService;
import com.jiacer.modules.common.service.ServiceException;
import com.jiacer.modules.log.Log;
import com.jiacer.modules.mybatis.dao.BrokerageRulesMapper;
import com.jiacer.modules.mybatis.dao.BrokerageSchemeMapper;
import com.jiacer.modules.mybatis.entity.BrokerageRulesEntity;
import com.jiacer.modules.mybatis.entity.BrokerageSchemeEntity;
import com.jiacer.modules.system.config.DBStatus;
import com.jiacer.modules.system.config.Message;
import com.jiacer.modules.system.utils.UserUtils;

/** 
* @ClassName: BrokerageServiceImpl 
* @Description: TODO
* @author 贺章鹏
* @date 2016年10月19日 下午4:18:46 
*  
*/
@Service
public class BrokerageServiceImpl extends BaseService implements BrokerageService {

	@Autowired
	BrokerageSchemeMapper schemeDao;
	
	@Autowired
	BrokerageRulesMapper rulesDao;
	
	@Override
	public BrokerageSchemeEntity getSchemeById(Integer id) {
		try {
			return schemeDao.getById(id);
		} catch (Exception e) {
			Log.error(Message.buildErrInfo(Message.QUERY_ERROR_EXCEPTION, e));
		}
		return null;
	}

	@Override
	public Page<BrokerageSchemeEntity> getBrokerageSchemePage(BrokerageSchemeEntity brokerageSchemeEntity,
			int pageNumber, int pageSize) {
		try {
            //获取总条数
			Map<Object, Object>  map=BrokerageOpt.buildMap(brokerageSchemeEntity);
			map.put("institutionInfoId", UserUtils.getUser().getInstitutionInfoId());
            Integer totalCount=schemeDao.count(map);
            //分页实体
            Page<BrokerageSchemeEntity> page=new Page<BrokerageSchemeEntity>();
            page.setPage(pageNumber);
            page.setRowNum(pageSize);
            if(totalCount==null){
                return page;
            }
            //最大页数判断
            int pageM = maxPage(totalCount, page.getRowNum(), page.getPage());
            if (pageM > 0) {
                page.setPage(pageM);
            }
            if (totalCount > 0) {
            	map.put("offset",page.getOffset());
            	map.put("pageSize",page.getRowNum());
            	List<BrokerageSchemeEntity> list=schemeDao.getPageList(map);
                page.setRows(list);
                page.setRecords(totalCount.longValue());
            }
            return page;
        } catch (Exception e) {
        	e.printStackTrace();
        	Log.error(String.format(Message.QUERY_ERROR_EXCEPTION, e));
            return null;
        }
	}

	@Override
	public void addBrokerageScheme(BrokerageSchemeEntity brokerageSchemeEntity) throws Exception{
		schemeDao.insert(BrokerageOpt.buildAdd(brokerageSchemeEntity));
		for(BrokerageRulesEntity rule:brokerageSchemeEntity.getRules()){
			rule.setBonusType(DBStatus.BonusType.NUMBER);
			rule.setSchemeId(brokerageSchemeEntity.getId());
			rulesDao.insert(rule);
		}
	}

	@Override
	public void modifyBrokerageScheme(BrokerageSchemeEntity brokerageSchemeEntity)  throws Exception{
		BrokerageSchemeEntity bean=this.getSchemeById(brokerageSchemeEntity.getId());
		if(bean==null){
			throw new ServiceException(Message.UPDATE_DATE_NOEXIST);
		}else{
			schemeDao.update(BrokerageOpt.buildUpdate(bean, brokerageSchemeEntity));
			BrokerageRulesEntity ruleObj=null;
			for(BrokerageRulesEntity rule:brokerageSchemeEntity.getRules()){
				ruleObj=new BrokerageRulesEntity();
				ruleObj=rulesDao.getByKeys(rule);
				if(ruleObj==null){
					rule.setBonusType(DBStatus.BonusType.NUMBER);
					rule.setSchemeId(brokerageSchemeEntity.getId());
					rulesDao.insert(rule);
				}else{
					ruleObj.setBonusAmount(rule.getBonusAmount());
					rulesDao.update(ruleObj);
				}
				
			}
		}
	}

	@Override
	public void delBrokerageScheme(BrokerageSchemeEntity brokerageSchemeEntity) {
		BrokerageSchemeEntity bean=this.getSchemeById(brokerageSchemeEntity.getId());
		if(bean==null){
			throw new ServiceException(Message.UPDATE_DATE_NOEXIST);
		}else{
			schemeDao.update(BrokerageOpt.buildDelete(bean));
		}
	}

	@Override
	@Transactional(readOnly=true)
	public List<BrokerageRulesEntity> findRules(BrokerageRulesEntity brokerageRulesEntity) {
		return rulesDao.findAllList(brokerageRulesEntity);
	}

	
	@Override
	@Transactional(readOnly=true)
	public List<BrokerageSchemeEntity> findSchemes(BrokerageSchemeEntity brokerageSchemeEntity) {
		return schemeDao.findAllList(brokerageSchemeEntity);
	}

}
