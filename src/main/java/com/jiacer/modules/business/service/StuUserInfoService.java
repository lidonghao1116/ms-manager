package com.jiacer.modules.business.service;

import java.util.List;
import java.util.Map;

import com.jiacer.modules.common.page.Page;
import com.jiacer.modules.mybatis.entity.StuUserInfoEntity;

public interface StuUserInfoService {

	int insert(StuUserInfoEntity stuUserInfoEntity);
	
	public Page<StuUserInfoEntity> getUsersPage(StuUserInfoEntity stuUserInfoQuery, int pageNumber, int pageSize);
	
	StuUserInfoEntity getCphoneStuUserInfo(Map<Object, Object> map);
	
	Integer count(Map<Object, Object> map);
	
	List<StuUserInfoEntity> getPageList(Map<Object, Object> map);
	
	public StuUserInfoEntity getStuUserInfo(Integer id);
	
	public StuUserInfoEntity getById(Integer id);
	
}
