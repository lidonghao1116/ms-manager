package com.jiacer.modules.business.utils;

import java.util.List;
import com.google.common.collect.Lists;
import com.jiacer.modules.common.utils.SpringContextHolder;
import com.jiacer.modules.log.Log;
import com.jiacer.modules.mybatis.dao.CoursePackageMapper;
import com.jiacer.modules.mybatis.entity.CoursePackageEntity;
import com.jiacer.modules.system.config.DBStatus;

/** 
* @ClassName: ProductsUtils 
* @Description: 销售课程列表
* @author 贺章鹏
* @date 2016年11月1日 下午6:09:53 
*  
*/
public class ProductsUtils {
	
	private static CoursePackageMapper productDao = SpringContextHolder.getBean(CoursePackageMapper.class);
	/**
	 * 获取销售课程
	 */
	public static List<CoursePackageEntity> init(){
		CoursePackageEntity entity=new CoursePackageEntity();
		entity.setIsUsable(DBStatus.IsUsable.TRUE);
		List<CoursePackageEntity> list=Lists.newArrayList();
		try {
			list=productDao.findAllList(entity);
		} catch (Exception e) {
			Log.error("获取销售课程失败");
		}
		return list;
	}
	
	public static String getName(Integer id){
		try {
			CoursePackageEntity entity=productDao.getById(id);
			if(entity!=null){
				return entity.getPackageName();
			}else{
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
}
