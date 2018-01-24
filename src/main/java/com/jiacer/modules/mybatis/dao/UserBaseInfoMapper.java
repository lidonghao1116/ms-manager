package com.jiacer.modules.mybatis.dao;

import com.jiacer.modules.common.persistence.CrudDao;
import com.jiacer.modules.common.persistence.annotation.MyBatisDao;
import com.jiacer.modules.mybatis.entity.UserBaseInfoEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisDao
public interface UserBaseInfoMapper extends CrudDao<UserBaseInfoEntity>{


	/**
	 * 根据证件号或者手机号查询用户
	 * @param certNo
	 * @param mobile
	 * @return
	 */
    List<UserBaseInfoEntity> findUserByCertNoOrMobile(@Param("certNo") String certNo, @Param("mobile") String mobile);
}