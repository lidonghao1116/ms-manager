package com.jiacer.modules.business.utils;

import com.jiacer.modules.common.utils.SpringContextHolder;
import com.jiacer.modules.log.Log;
import com.jiacer.modules.mybatis.dao.UserBaseInfoMapper;
import com.jiacer.modules.mybatis.dao.UserExtendInfoMapper;
import com.jiacer.modules.mybatis.entity.UserBaseInfoEntity;
import com.jiacer.modules.mybatis.entity.UserExtendInfoEntity;

/** 
* @ClassName: UserUtils 
* @Description: TODO
* @author 贺章鹏
* @date 2016年11月6日 下午3:05:17 
*  
*/
public class UserUtils {

	private static UserExtendInfoMapper userExtendInfoDao = SpringContextHolder.getBean(UserExtendInfoMapper.class);
	
	private static UserBaseInfoMapper userBaseInfoDao = SpringContextHolder.getBean(UserBaseInfoMapper.class);

	public static Boolean checkIsExistMobile(String mobile){
		Boolean reslut=Boolean.FALSE;
		try {
			UserBaseInfoEntity entity=new UserBaseInfoEntity();
			entity.setMobile(mobile);
			UserBaseInfoEntity bean=userBaseInfoDao.get(entity);
			if(bean!=null){
				reslut=Boolean.TRUE;
			}
		} catch (Exception e) {
			Log.error("校验手机号失败");
			e.printStackTrace();
			reslut=Boolean.TRUE;
		}
		return reslut;
	}
	
	public static Boolean checkIsExistCertNo(String certNo){
		Boolean reslut=Boolean.FALSE;
		try {
			UserExtendInfoEntity entity=new UserExtendInfoEntity();
			entity.setCertNo(certNo);
			UserExtendInfoEntity bean=userExtendInfoDao.get(entity);
			if(bean!=null){
				reslut=Boolean.TRUE;
			}
		} catch (Exception e) {
			Log.error("校验身份证失败");
			e.printStackTrace();
			reslut=Boolean.TRUE;
		}
		return reslut;
	}
}
