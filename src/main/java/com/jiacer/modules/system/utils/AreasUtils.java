package com.jiacer.modules.system.utils;

import java.util.List;
import com.google.common.collect.Lists;
import com.jiacer.modules.common.utils.CacheUtils;
import com.jiacer.modules.common.utils.SpringContextHolder;
import com.jiacer.modules.log.Log;
import com.jiacer.modules.mybatis.dao.AreasMapper;
import com.jiacer.modules.mybatis.entity.AreasEntity;

/** 
* @ClassName: AreasUtils 
* @Description: TODO
* @author 贺章鹏
* @date 2016年11月1日 下午6:16:51 
*  
*/
public class AreasUtils {
	public static final String CACHE_AREAS_LIST = "areasList";
	
	private static AreasMapper areasDao = SpringContextHolder.getBean(AreasMapper.class);
	/**
	 * 根据类型获取所有的值
	 * @return
	 */
	public static List<AreasEntity> init(){
		
		
		List<AreasEntity> list=getCache("all");
		if(list!=null && list.size()>0){
			return list;
		}
		
		List<AreasEntity> result=Lists.newArrayList();
		try {
			result=areasDao.getAreas();
			putCache("all",result);
		} catch (Exception e) {
			Log.error("获取区域参数失败");
		}
		return result;
	}
	
	public static String getName(String code){
		List<AreasEntity> list=init();
		for(AreasEntity areasEntity:list){
			if(code.equals(areasEntity.getAreaCode())){
				return areasEntity.getAreaName();
			}
		}
		return "";
	}
	
	/**
	 * 清除缓存
	 */
	public static void clear(){
		CacheUtils.getCacheManager().getCache(CACHE_AREAS_LIST).removeAll();
	}
	
	/**
	 * 移除指定缓存
	 * @param key
	 */
	public static void remove(String key){
		CacheUtils.remove(CACHE_AREAS_LIST,key);
	}
	
	/**
	 * 获取缓存
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<AreasEntity> getCache(String key){
		if(CacheUtils.get(CACHE_AREAS_LIST, key)!=null){
			return (List<AreasEntity>)CacheUtils.get(CACHE_AREAS_LIST, key);
		}
		return null;
	}
	
	/**
	 * 添加缓存
	 * @param key
	 * @param count
	 */
	public static void putCache(String key,List<AreasEntity> value){
		CacheUtils.put(CACHE_AREAS_LIST, key, value);
	}
	
	/**
	 * 刷新缓存
	 */
	public static void flush(){
		clear();
		init();
	}
}
