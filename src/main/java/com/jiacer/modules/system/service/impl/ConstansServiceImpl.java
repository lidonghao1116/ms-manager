package com.jiacer.modules.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiacer.modules.mybatis.dao.ConstantsMapper;
import com.jiacer.modules.system.service.ConstansService;
@Service
public class ConstansServiceImpl implements ConstansService{
	@Autowired
	ConstantsMapper constDao;
	
	@Override
	@Transactional(readOnly=true)
	public String getConstValue(String constKey) {
		String constValue=constDao.getById(constKey).getConstValue();
		return constValue;
	}

}
