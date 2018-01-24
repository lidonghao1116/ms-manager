package com.jiacer.modules.mybatis.entity;

import java.util.Date;
import java.util.List;

import com.jiacer.modules.mybatis.model.SysUsers;
import com.jiacer.modules.system.config.DBStatus;



/**
 * 
* @ClassName: SysUsersEntity 
* @Description: 管理系统用户表
* @author 贺章鹏
* @date 2016年10月18日 下午12:08:40 
*
 */
public class SysUsersEntity extends SysUsers{

	private static final long serialVersionUID = 1L;

	//机构管理员账号
	private String rootLoginAccount;
	 
	//用户角色
	private List<SysRoleEntity>  roles;
	
	//用户权限（菜单）
	private List<SysMenusEntity> menus;

    //用户角色id字符串，新建或者修改时，用于记录用户角色，插入用户角色关联表，不对用户表进行修改
	private String ids;
	public String getIds() {
		return ids;
	}


	public void setIds(String ids) {
		this.ids = ids;
	}


	public String getRootLoginAccount() {
		return rootLoginAccount;
	}
   
	
	public void setRootLoginAccount(String rootLoginAccount) {
		this.rootLoginAccount = rootLoginAccount;
	}
    
	public SysUsersEntity(){
		super();
		setIsUsable(DBStatus.IsUsable.TRUE);
		setAddTime(new Date());
	}

	public List<SysRoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(List<SysRoleEntity> roles) {
		this.roles = roles;
	}

	public List<SysMenusEntity> getMenus() {
		return menus;
	}

	public void setMenus(List<SysMenusEntity> menus) {
		this.menus = menus;
	}

}
