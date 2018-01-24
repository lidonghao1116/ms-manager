package com.jiacer.modules.mybatis.dao;

import java.util.List;
import java.util.Map;

import com.jiacer.modules.common.persistence.CrudDao;
import com.jiacer.modules.common.persistence.annotation.MyBatisDao;
import com.jiacer.modules.mybatis.entity.CoursesTimeEntity;
import com.jiacer.modules.mybatis.model.CertAuthority;

@MyBatisDao
public interface CertAuthorityDao extends CrudDao<CertAuthority>{

	public List<CertAuthority> getAllCertAuthority();
	
	public List<CertAuthority> getStatusCertAuthority();
	
	public CertAuthority getById(Integer id);
	
}
