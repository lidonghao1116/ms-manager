package com.jiacer.modules.business.service;

import java.util.List;

import com.jiacer.modules.mybatis.model.CertAuthority;

/** 
* @ClassName: CoursesService 
* @Description: 上课时间管理接口服务
* @author 贺章鹏
* @date 2016年10月19日 下午4:06:08 
*  
*/
public interface CertAuthorityService {

	public List<CertAuthority> getAllCertAuthority();
	
	public List<CertAuthority> getStatusCertAuthority();
	
	public CertAuthority getById(Integer id);

}
