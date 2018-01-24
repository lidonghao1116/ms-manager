package com.jiacer.modules.business.bean.operation;

import java.util.Map;

import com.google.common.collect.Maps;
import com.jiacer.modules.business.bean.form.LearnRecordsQuery;
import com.jiacer.modules.common.config.Global;
import com.jiacer.modules.common.utils.SqlUtils;
import com.jiacer.modules.common.utils.StringUtils;

/** 
* @ClassName: LearnRecordsOpt 
* @Description: TODO
* @author 贺章鹏
* @date 2016年11月7日 下午3:38:34 
*  
*/
public class LearnRecordsOpt {
	/**
	 * 构建查询参数Map
	 * @param learnTypesEntity
	 * @return
	 */
	public static Map<Object, Object> buildMap(LearnRecordsQuery learnRecordsQuery){
		Map<Object, Object> map=Maps.newHashMap();
		if(!StringUtils.isEmpty(learnRecordsQuery.getClassNumber())){
			map.put("classNumber", learnRecordsQuery.getClassNumber());
		}
		if(!StringUtils.isEmpty(learnRecordsQuery.getUserName())){
			map.put("userName", SqlUtils.like(learnRecordsQuery.getUserName()));
		}
		map.put("isFinished", Global.YES);
		return map;
	}
}
