package com.jiacer.modules.mybatis.dao;

import java.util.List;

import com.jiacer.modules.common.persistence.CrudDao;
import com.jiacer.modules.common.persistence.annotation.MyBatisDao;
import com.jiacer.modules.mybatis.model.SysUserRole;
@MyBatisDao
public interface SysUserRoleMapper extends CrudDao<SysUserRole>{
	List<SysUserRole> getByUId(Integer uid);
	void deleteByUid(Integer uid);
}
