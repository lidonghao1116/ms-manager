package com.jiacer.modules.business.utils;

import java.util.List;
import com.google.common.collect.Lists;
import com.jiacer.modules.common.utils.CacheUtils;
import com.jiacer.modules.common.utils.SpringContextHolder;
import com.jiacer.modules.log.Log;
import com.jiacer.modules.mybatis.dao.CoursesTimeDao;
import com.jiacer.modules.mybatis.entity.CoursesTimeEntity;
import com.jiacer.modules.system.config.DBStatus;

/** 
* @ClassName: CoursesTimeUtils 
* @Description: TODO
* @author 贺章鹏
* @date 2016年11月4日 下午3:48:09 
*  
*/
public class CoursesTimeUtils {
public static final String CACHE_COURSES_TIME_LIST = "CoursesTimeList";
	
	private static CoursesTimeDao coursesTimeDao = SpringContextHolder.getBean(CoursesTimeDao.class);
	
	/**
	 * 获取课程所有
	 */
	public static List<CoursesTimeEntity> init(){
		
		List<CoursesTimeEntity> list=getCache("all");
		if(list!=null && list.size()>0){
			return list;
		}
		
		List<CoursesTimeEntity> result=Lists.newArrayList();
		CoursesTimeEntity entity=new CoursesTimeEntity();
		entity.setTemplateType(DBStatus.IsUsable.TRUE);
		try {
			result=coursesTimeDao.findAllList(entity);
			putCache("all",result);
		} catch (Exception e) {
			Log.error("获取课程数据失败");
		}
		return result;
	}
	
	
	public static String getName(Integer id){
		List<CoursesTimeEntity> list=init();
		for(CoursesTimeEntity coursesTimeEntity:list){
			if(id==coursesTimeEntity.getId()){
				return coursesTimeEntity.getTemplateName();
			}
		}
		return "";
	}
	
	/**
	 * 清除缓存
	 */
	public static void clear(){
		if(CacheUtils.getCacheManager().getCache(CACHE_COURSES_TIME_LIST) != null){
			CacheUtils.getCacheManager().getCache(CACHE_COURSES_TIME_LIST).removeAll();
		}
	}
	
	/**
	 * 移除指定缓存
	 * @param key
	 */
	public static void remove(String key){
		CacheUtils.remove(CACHE_COURSES_TIME_LIST,key);
	}
	
	/**
	 * 获取缓存
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<CoursesTimeEntity> getCache(String key){
		if(CacheUtils.get(CACHE_COURSES_TIME_LIST, key)!=null){
			return (List<CoursesTimeEntity>)CacheUtils.get(CACHE_COURSES_TIME_LIST, key);
		}
		return null;
	}
	
	/**
	 * 添加缓存
	 * @param key
	 * @param count
	 */
	public static void putCache(String key,List<CoursesTimeEntity> value){
		CacheUtils.put(CACHE_COURSES_TIME_LIST, key, value);
	}
	
	/**
	 * 刷新缓存
	 */
	public static void flush(){
		clear();
		init();
	}
	
	public static CoursesTimeEntity getObject(Integer id){
		List<CoursesTimeEntity> list=init();
		for(CoursesTimeEntity coursesTimeEntity:list){
			if(id==coursesTimeEntity.getId()){
				return coursesTimeEntity;
			}
		}
		return null;
	}
}
