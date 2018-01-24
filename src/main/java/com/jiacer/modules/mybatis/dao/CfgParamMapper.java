package com.jiacer.modules.mybatis.dao;

import java.util.List;
import java.util.Map;

import com.jiacer.modules.common.persistence.CrudDao;
import com.jiacer.modules.common.persistence.annotation.MyBatisDao;
import com.jiacer.modules.mybatis.entity.CfgParamEntity;
import com.jiacer.modules.mybatis.model.CfgParam;

@MyBatisDao
public interface CfgParamMapper extends CrudDao<CfgParamEntity>{

	/**
	 * 
	 * @MethodName:getCfgParamOfGrade
	 * @Type:CfgParamMapper
	 * @Description:鉴定等级
	 * @Return:List<CfgParam>
	 * @Param:@param value
	 * @Param:@return
	 * @Thrown:
	 * @Date:Oct 7, 2017 12:12:30 PM
	 */
	List<CfgParam> getCfgParamOfValue(String value);
	
	/**
	 * 
	 * @MethodName:getTextByValue
	 * @Type:CfgParamMapper
	 * @Description:获取参数列表值
	 * @Return:String
	 * @Param:@param map
	 * @Param:@return
	 * @Thrown:
	 * @Date:Oct 7, 2017 8:13:12 PM
	 */
	String getTextByValue(Map<Object, Object> map);

    String getCodeByText(Map<Object, Object> map);
}