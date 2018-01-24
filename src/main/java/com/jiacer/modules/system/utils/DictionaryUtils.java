package com.jiacer.modules.system.utils;

import java.util.List;

import com.google.common.collect.Lists;
import com.jiacer.modules.common.utils.StringUtils;
import com.jiacer.modules.mybatis.entity.CfgParamEntity;

/** 
* @ClassName: DictionaryUtils 
* @Description: 字典型参数
* @author 贺章鹏
* @date 2016年11月8日 上午11:54:29 
*  
*/
public class DictionaryUtils {

	
	/**
	 * 考试形式
	 * @param values
	 * @return
	 */
	public static String getExamType(String values){
		return ParamsUtils.getText(1, 0, values);
	}
	
	/**
	 * 课程状态
	 * @param values
	 * @return
	 */
	public static String getCourseStatus(String values){
		return ParamsUtils.getText(2, 0, values);
	}
	
	/**
	 * 课程销售状态
	 * @param values
	 * @return
	 */
	public static String getProductStatus(String values){
		return ParamsUtils.getText(11, 0, values);
	}
	
	/**
	 * 考试状态
	 * @param values
	 * @return
	 */
	public static String getExamStatus(String values){
		return ParamsUtils.getText(32, 0, values);
	}
	
	/**
	 * 来源
	 * @param values
	 * @return
	 */
	public static String getSourceTypeName(String values){
		return ParamsUtils.getText(5, 0, values);
	}
	
	/**
	 * 来源
	 * @param values
	 * @return
	 */
	public static String getSourceTypeSecName(String pValue,String values){
		return ParamsUtils.getText(5, ParamsUtils.getObject(5, 0, pValue).getId(), values);
	}
	
	/**
	 * 获取上课时间列表
	 * @param values
	 * @return
	 */
	public static List<CfgParamEntity> getClassTimes(String values){
		List<CfgParamEntity> newList=Lists.newArrayList();
		List<CfgParamEntity> list=ParamsUtils.getParams(16,0);
		String[] ss=StringUtils.split(values,",");
		int i,j=ss.length;
		for(CfgParamEntity cfgParamEntity:list){
			for(i=0;i<ss.length;i++){
				if(ss[i].equals(cfgParamEntity.getValue())){
					newList.add(cfgParamEntity);
					j--;
				}
			}
			if(j==0){//最后一个跳出循环
				break;
			}
		}
		return newList;
	}

	/**
	 * 学历
	 * @param values
	 * @return
	 */
	public static String getEducationName(String values){
		return ParamsUtils.getText(3, 0, values);
	}

	/**
	 * 民族
	 * @param values
	 * @return
	 */
	public static String getNationName(String values){
		return ParamsUtils.getText(6, 0, values);
	}
	
	/**
	 * 考试结果
	 * @param values
	 * @return
	 */
	public static String getExamResultName(String values){
		return ParamsUtils.getText(12, 0, values);
	}
	
	/**
	 * 处理结果
	 * @param values
	 * @return
	 */
	public static String getDealResultName(String values){
		return ParamsUtils.getText(13, 0, values);
	}
	
}
