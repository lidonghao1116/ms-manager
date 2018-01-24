package com.jiacer.modules.system.utils;

import java.util.Comparator;

import com.jiacer.modules.mybatis.entity.CfgParamEntity;

/** 
* @ClassName: ComparatorParams 
* @Description: 字典参数排序
* @author 贺章鹏
* @date 2016年10月28日 下午5:51:39 
*  
*/
@SuppressWarnings("rawtypes")
public class ComparatorParams implements Comparator{
	public int compare(Object arg0, Object arg1) {
		CfgParamEntity param1 = (CfgParamEntity) arg0;
		CfgParamEntity param2 = (CfgParamEntity) arg1;
		int result = param1.getSortNo().compareTo(param2.getSortNo());
		return result;
	}
}
