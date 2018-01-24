package com.jiacer.modules.business.utils;

import java.util.List;
import com.google.common.collect.Lists;
import com.jiacer.modules.common.utils.CacheUtils;
import com.jiacer.modules.common.utils.SpringContextHolder;
import com.jiacer.modules.log.Log;
import com.jiacer.modules.mybatis.dao.SchoolsMapper;
import com.jiacer.modules.mybatis.entity.SchoolsEntity;
import com.jiacer.modules.system.config.DBStatus;

/** 
* @ClassName: SchoolsUtils 
* @Description: TODO
* @author 贺章鹏
* @date 2016年11月4日 下午3:48:09 
*  
*/
public class SchoolsUtils {
public static final String CACHE_SCHOOLS_LIST = "schoolsList";
	
	private static SchoolsMapper schoolsDao = SpringContextHolder.getBean(SchoolsMapper.class);
	
	/**
	 * 获取课程所有
	 */
	public static List<SchoolsEntity> init(){
		
		List<SchoolsEntity> list=getCache("all");
		if(list!=null && list.size()>0){
			return list;
		}
		
		List<SchoolsEntity> result=Lists.newArrayList();
		SchoolsEntity entity=new SchoolsEntity();
		entity.setIsUsable(DBStatus.IsUsable.TRUE);//基础参数类型
		try {
			result=schoolsDao.findAllList(entity);
			putCache("all",result);
		} catch (Exception e) {
			Log.error("获取课程数据失败");
		}
		return result;
	}
	
	
	public static String getName(Integer id){
		List<SchoolsEntity> list=init();
		for(SchoolsEntity schoolsEntity:list){
			if(id==schoolsEntity.getId()){
				return schoolsEntity.getSchoolName();
			}
		}
		return "";
	}
	
	/**
	 * 清除缓存
	 */
	public static void clear(){
		System.out.println("04:"+CacheUtils.getCacheManager().getCache(CACHE_SCHOOLS_LIST));
		if(CacheUtils.getCacheManager().getCache(CACHE_SCHOOLS_LIST) != null){
			CacheUtils.getCacheManager().getCache(CACHE_SCHOOLS_LIST).removeAll();
		}
		
	}
	
	/**
	 * 移除指定缓存
	 * @param key
	 */
	public static void remove(String key){
		CacheUtils.remove(CACHE_SCHOOLS_LIST,key);
	}
	
	/**
	 * 获取缓存
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<SchoolsEntity> getCache(String key){
		if(CacheUtils.get(CACHE_SCHOOLS_LIST, key)!=null){
			return (List<SchoolsEntity>)CacheUtils.get(CACHE_SCHOOLS_LIST, key);
		}
		return null;
	}
	
	/**
	 * 添加缓存
	 * @param key
	 * @param count
	 */
	public static void putCache(String key,List<SchoolsEntity> value){
		System.out.println("CACHE_SCHOOLS_LIST:"+value);
		CacheUtils.put(CACHE_SCHOOLS_LIST, key, value);
	}
	
	/**
	 * 刷新缓存
	 */
	public static void flush(){
		System.out.println("SchoolsUtils:flush");
		clear();
		init();
	}
	
	public static SchoolsEntity getObject(Integer id){
		List<SchoolsEntity> list=init();
		for(SchoolsEntity schoolsEntity:list){
			if(id==schoolsEntity.getId()){
				return schoolsEntity;
			}
		}
		return null;
	}
}
