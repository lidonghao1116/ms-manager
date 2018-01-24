package com.jiacer.modules.business.utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.util.ObjectIdMap;
import com.jiacer.modules.common.constants.Constants;
import com.jiacer.modules.common.utils.SpringContextHolder;
import com.jiacer.modules.mybatis.dao.CfgParamMapper;
import com.jiacer.modules.mybatis.model.CfgParam;
import org.apache.commons.lang.StringUtils;

/**
 * 
 * @ClassName:CfgParamUtils.java
 * @Description:参数字典工具类
 * @Author:ticahmfock
 * @Date:Oct 7, 2017 12:05:28 PM
 */
public class CfgParamUtils implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static CfgParamMapper cfgParamMapper = SpringContextHolder.getBean(CfgParamMapper.class);
	
	/**
	 * 
	 * @MethodName:getCfgParamofGrade
	 * @Type:CfgParamUtils
	 * @Description:获取鉴定等级信息
	 * @Return:List<CfgParam>
	 * @Param:@return
	 * @Thrown:
	 * @Date:Sep 28, 2017 3:46:10 PM
	 */
	public static List<CfgParam> getCfgParamofGrade() {
		List<CfgParam> list = cfgParamMapper.getCfgParamOfValue(Constants.AUTHORITY_GRADE);
		return list;
	}
	
	/**
	 * 
	 * @MethodName:getCfgParamOfStatus
	 * @Type:CfgParamUtils
	 * @Description:获取课程状态信息
	 * @Return:List<CfgParam>
	 * @Param:@return
	 * @Thrown:
	 * @Date:Sep 28, 2017 3:50:04 PM
	 */
	public static List<CfgParam> getCfgParamOfStatus() {
		List<CfgParam> list = cfgParamMapper.getCfgParamOfValue(Constants.COURSE_SHELEVES);
		for (CfgParam cfgParam : list) {
			if ("01".equals(cfgParam.getValue())) {
				cfgParam.setText(Constants.COURSE_STATUS_NORMAL);
			}else{
				cfgParam.setText(Constants.COURSE_STATUS_STOP);
			}
		}
		return list;
	}

	/**
	 * 根据type和text 获取 code value
	 */
	public static String getCfgParamValueByText(String type, String text) {
		if(StringUtils.isBlank(type) || StringUtils.isBlank(text)){
			return "";
		}
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("type", type);
		map.put("text", text);
		return  cfgParamMapper.getCodeByText(map);
	}

}
