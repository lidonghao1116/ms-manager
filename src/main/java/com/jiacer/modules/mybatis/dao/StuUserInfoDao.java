package com.jiacer.modules.mybatis.dao;


import java.util.List;
import java.util.Map;

import com.jiacer.modules.common.persistence.CrudDao;
import com.jiacer.modules.common.persistence.annotation.MyBatisDao;
import com.jiacer.modules.mybatis.entity.StuUserInfoEntity;
import org.apache.ibatis.annotations.Param;

@MyBatisDao
public interface StuUserInfoDao extends CrudDao<StuUserInfoEntity>{

	Integer count(Map<Object, Object> map);
	
	Integer countStu(Map<Object, Object> map);
	
	List<StuUserInfoEntity> getPageList(Map<Object, Object> map);
	
	StuUserInfoEntity getCphoneStuUserInfo(Map<Object, Object> map);
	
	StuUserInfoEntity getStuInfoByUserId(@Param("institutionInfoId") Integer institutionInfoId, @Param("userId")Integer userId);
	
	List<StuUserInfoEntity> findAllList(Map<Object, Object> map);

    List<StuUserInfoEntity> findStudentByCertNo(@Param("institutionInfoId") Integer institutionInfoId, @Param("certNo") String certNo);
}