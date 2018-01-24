package com.jiacer.modules.system.utils;

import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;
import com.jiacer.modules.common.utils.CacheUtils;
import com.jiacer.modules.common.utils.SpringContextHolder;
import com.jiacer.modules.common.utils.StringUtils;
import com.jiacer.modules.log.Log;
import com.jiacer.modules.mybatis.dao.CfgParamMapper;
import com.jiacer.modules.mybatis.entity.CfgParamEntity;

/** 
* @ClassName: ParamsUtils 
* @Description: 字典行参数
* @author 贺章鹏
* @date 2016年10月28日 下午4:44:55 
*  
*/
public class ParamsUtils {

	public static final String CACHE_PARMAS_LIST = "paramsList";
	
	private static CfgParamMapper paramsDao = SpringContextHolder.getBean(CfgParamMapper.class);
	
	/**
	 * 初始化字典型参数
	 */
	public static void init(){
		CfgParamEntity entity=new CfgParamEntity();
		entity.setType(0);//基础参数类型
		List<CfgParamEntity> cfgparams=Lists.newArrayList();
		try {
			cfgparams=paramsDao.findAllList(entity);
		} catch (Exception e) {
			Log.error("获取字典参数失败");
		}
		for(CfgParamEntity bean:cfgparams){
			putCache(bean.getValue(), getParams(Integer.parseInt(bean.getValue()),entity));
		}
	}
	
	/**
	 * 根据类型获取所有的值
	 * @param type
	 * @param entity
	 * @return
	 */
	public static List<CfgParamEntity> getParams(Integer type,CfgParamEntity entity){
		entity.setType(type);
		List<CfgParamEntity> cfgparams=Lists.newArrayList();
		try {
			cfgparams=paramsDao.findAllList(entity);
		} catch (Exception e) {
			Log.error("获取字典参数失败");
		}
		return cfgparams;
	}
	
	@SuppressWarnings({ "unchecked"})
	public static List<CfgParamEntity> getParams(Integer type,Integer pid){
		List<CfgParamEntity> cfgparams = getCache(String.valueOf(type));
		List<CfgParamEntity> list=Lists.newArrayList();
		for(CfgParamEntity cfg:cfgparams){
			if(pid==cfg.getFkParentParamId()){
				list.add(cfg);
			}
		}
		ComparatorParams comparator=new ComparatorParams();
		Collections.sort(list, comparator);
		return list;
	}
	
	
	/**
	 * 清除缓存
	 */
	public static void clear(){
		System.out.println("02:"+CacheUtils.getCacheManager().getCache(CACHE_PARMAS_LIST));
		if(CacheUtils.getCacheManager().getCache(CACHE_PARMAS_LIST) != null){
			CacheUtils.getCacheManager().getCache(CACHE_PARMAS_LIST).removeAll();
		}
		
	}
	
	/**
	 * 移除指定缓存
	 * @param key
	 */
	public static void remove(String key){
		CacheUtils.remove(CACHE_PARMAS_LIST,key);
	}
	
	/**
	 * 获取缓存
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<CfgParamEntity> getCache(String key){
		if(CacheUtils.get(CACHE_PARMAS_LIST, key)!=null){
			return (List<CfgParamEntity>)CacheUtils.get(CACHE_PARMAS_LIST, key);
		}
		return null;
	}
	
	/**
	 * 添加缓存
	 * @param key
	 * @param count
	 */
	public static void putCache(String key,List<CfgParamEntity> value){
		System.out.println("CACHE_PARMAS_LIST:"+value);
		CacheUtils.put(CACHE_PARMAS_LIST, key, value);
	}
	
	/**
	 * 刷新缓存
	 */
	public static void flush(){
		System.out.println("ParamsUtils:flush");
		clear();
		init();
	}
	
	public static String getText(Integer type,Integer pid,String values){
		List<CfgParamEntity> list=getParams(type,pid);
		String[] ss=StringUtils.split(values,",");
		if(ss==null || ss.length<1){
			return "";
		}
		StringBuffer sb=new StringBuffer();
		int i,j=ss.length;
		for(CfgParamEntity cfgParamEntity:list){
			for(i=0;i<ss.length;i++){
				if(ss[i].equals(cfgParamEntity.getValue())){
					sb.append(cfgParamEntity.getText()).append(",");
					j--;
				}
			}
			if(j==0){//最后一个跳出循环
				break;
			}
		}
		if(sb!=null && sb.length()>0){
			return sb.substring(0, sb.length()-1);
		}else{
			return "";
		}
		
	}
	
	public static CfgParamEntity getObject(Integer type,Integer pid,String value){
		CfgParamEntity cfgParam=new CfgParamEntity();
		if(StringUtils.isEmpty(value)){
			return cfgParam;
		}
		List<CfgParamEntity> list=getParams(type,pid);
		for(CfgParamEntity cfgParamEntity:list){
			if(value.equals(cfgParamEntity.getValue())){
				cfgParam=cfgParamEntity;
				break;
			}
		}
		return cfgParam;
	}
	
	
	public static List<CfgParamEntity> getParamsList(Integer type,Integer pid,String values){
		List<CfgParamEntity> newList=Lists.newArrayList();
		List<CfgParamEntity> list=ParamsUtils.getParams(type,pid);
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
	
}
