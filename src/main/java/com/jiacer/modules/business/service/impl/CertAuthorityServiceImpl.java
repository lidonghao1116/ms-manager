package com.jiacer.modules.business.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiacer.modules.business.service.CertAuthorityService;
import com.jiacer.modules.common.service.BaseService;
import com.jiacer.modules.mybatis.dao.CertAuthorityDao;
import com.jiacer.modules.mybatis.model.CertAuthority;

/** 
* @ClassName: CoursesServiceImpl 
* @Description: TODO
* @author 贺章鹏
* @date 2016年10月19日 下午4:19:10 
*  
*/
@Service
public class CertAuthorityServiceImpl extends BaseService implements CertAuthorityService {
	
	@Autowired
	CertAuthorityDao certAuthorityDao;

	@Override
	public List<CertAuthority> getAllCertAuthority() {
		// TODO Auto-generated method stub
		return certAuthorityDao.getAllCertAuthority();
	}

	@Override
	public CertAuthority getById(Integer id) {
		// TODO Auto-generated method stub
		return certAuthorityDao.getById(id);
	}

	@Override
	public List<CertAuthority> getStatusCertAuthority() {
		// TODO Auto-generated method stub
		return certAuthorityDao.getStatusCertAuthority();
	}



}
