package com.jiacer.modules.mybatis.dao;

import java.util.List;

import com.jiacer.modules.common.persistence.CrudDao;
import com.jiacer.modules.common.persistence.annotation.MyBatisDao;
import com.jiacer.modules.mybatis.model.SysRoleMenu;
@MyBatisDao
public interface SysRoleMenuMapper extends CrudDao<SysRoleMenu>{
   
	List<SysRoleMenu> getByRId(Integer rid);
}
 