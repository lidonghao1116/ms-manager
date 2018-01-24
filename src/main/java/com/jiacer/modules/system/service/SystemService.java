package com.jiacer.modules.system.service;

import com.jiacer.modules.common.page.Page;
import com.jiacer.modules.mybatis.entity.CfgParamEntity;
import com.jiacer.modules.mybatis.entity.SysUsersEntity;

/**
 * 
* @ClassName: SystemService 
* @Description: 系统服务接口 包含（系统管理员、角色、菜单、权限、字典参数、常量管理、参数缓存等）
* @author 贺章鹏
* @date 2016年10月19日 下午3:57:17 
*
 */
public interface SystemService {
	
	
	//*********************************系统管理用户*******************************************//
	/**
	 * 根据登陆账号获取用户信息
	 * @param account
	 * @return
	 */
	SysUsersEntity getUseByLoginAccount(String account);

	/**
	 * 根据id获取用户信息
	 * @param id
	 * @return
	 */
	SysUsersEntity getUseById(Integer id);
	/*
	 * 根据用户得到角色及菜单信息
	 */
	SysUsersEntity getRoleAndMenus(SysUsersEntity user);
	/**
	 * 新增用户
	 * @param user
	 * @throws Exception
	 */
	void addUser(SysUsersEntity user) throws Exception;

	/**
	 * 修改用户
	 * @param user
	 * @throws Exception
	 */
	void modifyUser(SysUsersEntity user) throws Exception;

	/**
	 * 删除用户
	 * @param user
	 * @throws Exception
	 */
	void delUser(SysUsersEntity user) throws Exception;
	
	void updateUserPwd(SysUsersEntity user) throws Exception;

	/**
	 * 用户管理分页
	 * @param userEntity
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	Page<SysUsersEntity> getUserPage(SysUsersEntity userEntity, int pageNumber,int pageSize);

	/**
	 * 修改用户密码
	 * @param id
	 * @param newPassword
	 * @throws Exception
	 */
	void modifyPasswordById(Integer id, String newPassword) throws Exception;
	
	//*********************************系统管理用户结束*******************************************//
	
	//*********************************系统管理角色*******************************************//
    //*********************************系统管理角色结束*******************************************//
	//*********************************系统管理权限*******************************************//
	//*********************************系统管理权限结束*******************************************//

	
	
	//*********************************字典参数*******************************************//
	/**
	 * 根据id获取字典参数对象
	 * @param id
	 * @return
	 */
	CfgParamEntity getParamById(Integer id);

	/**
	 * 字典参数分页查询
	 * @param cfgParamEntity
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	Page<CfgParamEntity> getParamsPage(CfgParamEntity cfgParamEntity, int pageNumber, int pageSize);

	/**
	 * 字典参数新增
	 * @param cfgParamEntity
	 * @throws Exception
	 */
	void addParams(CfgParamEntity cfgParamEntity)  throws Exception;

	/**
	 * 字典参数删除
	 * @param cfgParamEntity
	 * @throws Exception
	 */
	void delParams(CfgParamEntity cfgParamEntity)  throws Exception;

	/**
	 * 字典参数修改
	 * @param cfgParamEntity
	 * @throws Exception
	 */
	void modifyParams(CfgParamEntity cfgParamEntity) throws Exception;
	//*********************************字典参数结束*******************************************//

}
