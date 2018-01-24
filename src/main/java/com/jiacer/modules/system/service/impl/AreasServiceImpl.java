package com.jiacer.modules.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiacer.modules.common.service.BaseService;
import com.jiacer.modules.mybatis.dao.AreasMapper;
import com.jiacer.modules.mybatis.entity.AreasEntity;
import com.jiacer.modules.system.service.AreasService;

/** 
* @ClassName: AreasServiceImpl 
* @Description: TODO
* @author 贺章鹏
* @date 2016年11月3日 下午2:36:19 
*  
*/
@Service
public class AreasServiceImpl extends BaseService implements AreasService{

	@Autowired
	AreasMapper areasDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<AreasEntity> getArears() {
		return areasDao.getAreas();
	}

}
