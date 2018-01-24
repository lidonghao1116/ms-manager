package com.jiacer.modules.system.bean;

import com.jiacer.modules.system.utils.ParamsUtils;

/** 
* @ClassName: InitLoader 
* @Description: TODO
* @author 贺章鹏
* @date 2016年11月1日 上午9:50:52 
*  
*/
public class InitLoader {

	public void init(){
		//初始化缓存数据
		ParamsUtils.init();
	}
}
