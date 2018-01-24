package com.jiacer.modules.system.service;

import java.util.List;

import com.jiacer.modules.mybatis.entity.AreasEntity;

/** 
* @ClassName: AreasService 
* @Description: TODO
* @author 贺章鹏
* @date 2016年11月3日 下午2:36:12 
*  
*/
public interface AreasService {

	/**
	 * 根据级别和父级获取区域值
	 * @param level
	 * @param pCode
	 * @return
	 */
	public List<AreasEntity> getArears();
}
