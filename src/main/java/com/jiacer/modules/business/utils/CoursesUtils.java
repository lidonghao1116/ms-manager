package com.jiacer.modules.business.utils;

import java.util.List;
import com.google.common.collect.Lists;
import com.jiacer.modules.common.utils.CacheUtils;
import com.jiacer.modules.common.utils.SpringContextHolder;
import com.jiacer.modules.common.utils.StringUtils;
import com.jiacer.modules.log.Log;
import com.jiacer.modules.mybatis.dao.LearnTypesMapper;
import com.jiacer.modules.mybatis.entity.LearnTypesEntity;
import com.jiacer.modules.mybatis.entity.SysUsersEntity;
import com.jiacer.modules.system.config.DBStatus;
import com.jiacer.modules.system.utils.UserUtils;

/** 
* @ClassName: CoursesUtils 
* @Description: 课程utils 缓存
* @author 贺章鹏
* @date 2016年11月1日 上午11:58:02 
*  
*/
public class CoursesUtils {
	
	public static final String CACHE_COURSES_LIST = "coursesList";
	
	private static LearnTypesMapper coursesDao = SpringContextHolder.getBean(LearnTypesMapper.class);
	
	
	
	/**
	 * 获取课程所有
	 */
	public static List<LearnTypesEntity> init(){
		
		List<LearnTypesEntity> list=getCache("all");
		if(list!=null && list.size()>0){
			return list;
		}
		
		List<LearnTypesEntity> result=Lists.newArrayList();
		SysUsersEntity sysUser = UserUtils.getUser();//获取登录用户信息
		LearnTypesEntity entity=new LearnTypesEntity();
		entity.setIsUsable(DBStatus.IsUsable.TRUE);//基础参数类型
		entity.setInstitutionInfoId(sysUser.getInstitutionInfoId());
		try {
			result=coursesDao.findAllList(entity);
			putCache("all",result);
		} catch (Exception e) {
			Log.error("获取课程数据失败");
		}
		return result;
	}
	
	
	public static String getName(Integer id){
		List<LearnTypesEntity> list=init();
		for(LearnTypesEntity learnTypesEntity:list){
			if(id==learnTypesEntity.getId()){
				return learnTypesEntity.getTypeName();
			}
		}
		return "";
	}
	
	/**
	 * 清除缓存
	 */
	public static void clear(){
		CacheUtils.getCacheManager().getCache(CACHE_COURSES_LIST).removeAll();
	}
	
	/**
	 * 移除指定缓存
	 * @param key
	 */
	public static void remove(String key){
		CacheUtils.remove(CACHE_COURSES_LIST,key);
	}
	
	/**
	 * 获取缓存
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<LearnTypesEntity> getCache(String key){
		if(CacheUtils.get(CACHE_COURSES_LIST, key)!=null){
			return (List<LearnTypesEntity>)CacheUtils.get(CACHE_COURSES_LIST, key);
		}
		return null;
	}
	
	/**
	 * 添加缓存
	 * @param key
	 * @param count
	 */
	public static void putCache(String key,List<LearnTypesEntity> value){
		CacheUtils.put(CACHE_COURSES_LIST, key, value);
	}
	
	/**
	 * 刷新缓存
	 */
	public static void flush(){
		clear();
		init();
	}
	
	public static LearnTypesEntity getObject(Integer id){
		List<LearnTypesEntity> list=init();
		for(LearnTypesEntity learnTypesEntity:list){
			if(id==learnTypesEntity.getId()){
				return learnTypesEntity;
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @param values
	 * @return
	 */
	@SuppressWarnings("unused")
	public static String getSchoolCourses(String values){
		String[] ss=StringUtils.split(values,",");
		StringBuilder result = new StringBuilder();
		if(ss != null){
			for(int i=0;i<ss.length;i++){
				String value=CoursesUtils.getName(Integer.parseInt(ss[i]));
				if(!StringUtils.isEmpty(value)){
					result.append(value).append(",");
				}
			}
			if(result!=null){
				if(ss.length != 0){
					if(result.length() != 0){
						return result.substring(0, result.length()-1);	
					}
				}
			}
		}
		return result.toString();
	}
	
	public static List<LearnTypesEntity> getCourses(String values){
		List<LearnTypesEntity> newList=Lists.newArrayList();
		List<LearnTypesEntity> list=init();
		String[] ss=StringUtils.split(values,",");
		int i=0,j=ss.length;
		for(LearnTypesEntity learnTypesEntity:list){
			for(i=0;i<ss.length;i++){
				if(learnTypesEntity.getId()==Integer.parseInt(ss[i])){
					newList.add(learnTypesEntity);
					j--;
				}
			}
			if(j==0){
				break;
			}
		}
		return newList;
	}
}
