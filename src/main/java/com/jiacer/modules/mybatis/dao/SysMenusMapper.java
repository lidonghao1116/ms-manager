package com.jiacer.modules.mybatis.dao;

import com.jiacer.modules.common.persistence.CrudDao;
import com.jiacer.modules.common.persistence.annotation.MyBatisDao;
import com.jiacer.modules.mybatis.entity.SysMenusEntity;

@MyBatisDao
public interface SysMenusMapper extends CrudDao<SysMenusEntity>{

}