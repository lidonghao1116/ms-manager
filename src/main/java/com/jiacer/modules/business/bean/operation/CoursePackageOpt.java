package com.jiacer.modules.business.bean.operation;

import java.util.Date;
import java.util.Map;
import com.google.common.collect.Maps;
import com.jiacer.modules.common.utils.DateUtils;
import com.jiacer.modules.common.utils.SqlUtils;
import com.jiacer.modules.common.utils.StringUtils;
import com.jiacer.modules.mybatis.entity.CoursePackageEntity;
import com.jiacer.modules.system.config.DBStatus;
import com.jiacer.modules.system.config.SysConstants;
import com.jiacer.modules.system.utils.UserUtils;

/** 
* @ClassName: CoursePackageOpt 
* @Description: 课程销售对象参数操作
* @author 贺章鹏
* @date 2016年10月24日 下午3:04:26 
*  
*/
public class CoursePackageOpt {
	/**
	 * 构建查询参数Map
	 * @param learnTypesEntity
	 * @return
	 */
	public static Map<Object, Object> buildMap(CoursePackageEntity coursePackageEntity){
		Map<Object, Object> map=Maps.newHashMap();
		if(!StringUtils.isEmpty(coursePackageEntity.getPackageName())){
			map.put("packageName", SqlUtils.like(coursePackageEntity.getPackageName()));
		}
		if(!StringUtils.isEmpty(coursePackageEntity.getStatus())){
			map.put("status", coursePackageEntity.getStatus());
		}
		if(coursePackageEntity.getStartDate()!=null){
			map.put("startDate", DateUtils.joinTime(coursePackageEntity.getStartDate(),SysConstants.MIN_TIME));
		}
		
		if(coursePackageEntity.getEndDate()!=null){
			map.put("endDate", DateUtils.joinTime(coursePackageEntity.getEndDate(),SysConstants.MAX_TIME));
		}
		
		map.put("isUsable", DBStatus.IsUsable.TRUE);
		
		return map;
	}
	
	/**
	 * 构建新增参数
	 * @param obj
	 * @return
	 */
	public static CoursePackageEntity buildAdd(CoursePackageEntity obj){
		obj.setIsUsable(DBStatus.IsUsable.TRUE);
		obj.setAddAccount(UserUtils.getAccount());
		obj.setAddTime(new Date());
		obj.setStatus(DBStatus.ProductStatus.ON_SHELF);
		obj.setInstitutionInfoId(UserUtils.getUser().getInstitutionInfoId());
		return obj;
	}
	
	/**
	 * 建构修改参数
	 * @param obj
	 * @param param
	 * @return
	 */
	public static CoursePackageEntity buildUpdate(CoursePackageEntity obj,CoursePackageEntity param){
		obj.setSummary(StringUtils.isEmpty(param.getSummary().trim())?"":param.getSummary().trim());
		//obj.setStatus(StringUtils.isEmpty(param.getStatus().trim())?"":param.getStatus().trim());
		obj.setRemarks(StringUtils.isEmpty(param.getRemarks().trim())?"":param.getRemarks().trim());
		obj.setOriginalPrice(param.getOriginalPrice()!=null?param.getOriginalPrice():null);
		obj.setPrice(param.getPrice()!=null?param.getPrice():null);
		obj.setIsDiscount(StringUtils.isEmpty(param.getIsDiscount().trim())?"":param.getIsDiscount().trim());
		obj.setModifyAccount(UserUtils.getAccount());
		obj.setModifyTime(new Date());
		obj.setPackageName(param.getPackageName());
		obj.setApplyCourses(param.getApplyCourses());
		obj.setSortNo(param.getSortNo());
		obj.setStatus(param.getStatus());
		obj.setTypeOfWork(param.getTypeOfWork());
		return obj;
	}
	
	/**
	 * 构建删除参数
	 * @param obj
	 * @return
	 */
	public static CoursePackageEntity buildDelete(CoursePackageEntity obj){
		obj.setIsUsable(DBStatus.IsUsable.FALSE);
		return obj;
	}
}
