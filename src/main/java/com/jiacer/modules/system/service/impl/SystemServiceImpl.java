package com.jiacer.modules.system.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiacer.modules.common.page.Page;
import com.jiacer.modules.common.service.BaseService;
import com.jiacer.modules.common.utils.EntryptUtils;
import com.jiacer.modules.common.utils.SqlUtils;
import com.jiacer.modules.common.utils.StringUtils;
import com.jiacer.modules.mybatis.dao.SysMenusMapper;
import com.jiacer.modules.mybatis.dao.SysRoleMapper;
import com.jiacer.modules.mybatis.dao.SysRoleMenuMapper;
import com.jiacer.modules.mybatis.dao.SysUserRoleMapper;
import com.jiacer.modules.mybatis.dao.SysUsersMapper;
import com.jiacer.modules.mybatis.entity.CfgParamEntity;
import com.jiacer.modules.mybatis.entity.SysMenusEntity;
import com.jiacer.modules.mybatis.entity.SysRoleEntity;
import com.jiacer.modules.mybatis.entity.SysUsersEntity;
import com.jiacer.modules.mybatis.model.SysRoleMenu;
import com.jiacer.modules.mybatis.model.SysUserRole;
import com.jiacer.modules.system.config.DBStatus;
import com.jiacer.modules.system.config.Message;
import com.jiacer.modules.system.service.SystemService;
import com.jiacer.modules.system.utils.UserUtils;

/**
 * @Description: 系统服务实现类
 * @author hzp
 * @date 2016-4-8
 *
 */
@Service
public class SystemServiceImpl extends BaseService implements SystemService{

	//*********************************系统管理用户*******************************************//
	@Autowired
	SysUsersMapper sysUsersDao;
	/*
	 * 注入查询角色、菜单、用户-角色、角色-菜单的dao
	 * 
	 */
	@Autowired
	SysRoleMapper sysRoleDao;
	@Autowired
	SysMenusMapper sysMenuDao;
	@Autowired
	SysUserRoleMapper sysUserRoleDao;
	@Autowired
	SysRoleMenuMapper sysRoleMenuDao;
	//根据账号查找用户:新建时会调用 登录时会调用
	@Override
	@Transactional(readOnly=true)
	public SysUsersEntity getUseByLoginAccount(String account) {
		SysUsersEntity usersEntity=new SysUsersEntity();
		usersEntity.setLoginAccount(account);
		usersEntity=sysUsersDao.get(usersEntity);
		//用户为空时，不去取角色和菜单
		if(usersEntity!=null)
		{
			usersEntity=this.getRoleAndMenus(usersEntity);
		}
		//用户为空时，不设置RootLoginAccount
		if(usersEntity!=null)
		{
			usersEntity.setRootLoginAccount(sysUsersDao.getRootAccount(usersEntity.getInstitutionInfoId()).getLoginAccount());
		}
		return usersEntity;
	}
    //根据id查找用户 修改用户时会用到
	@Override
	@Transactional(readOnly=true)
	public SysUsersEntity getUseById(Integer id) {
		SysUsersEntity user = sysUsersDao.getById(id);
		//用户为空时，不去取角色和菜单
		if(user!=null)
		{
			user=this.getRoleAndMenus(user);
		}
		user.setRootLoginAccount(sysUsersDao.getRootAccount(user.getInstitutionInfoId()).getLoginAccount());
		return user;
	}
    //新增
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void addUser(SysUsersEntity user) throws Exception {
		user.setPassword(EntryptUtils.entryptPassword("000000"));
		user.setIsInit("0");
		user.setUserType("02");
		user.setAddAccount(UserUtils.getUser().getLoginAccount());
		//登陆账号为jiacedu +三位数字 前台传过来三位数字，所以要加上jiacedu
		user.setLoginAccount(UserUtils.getUser().getRootLoginAccount()+user.getLoginAccount());
		user.setAddTime(new Date());
		user.setLoginFlag("1");
		user.setIsUsable(DBStatus.IsUsable.TRUE);
		user.setInstitutionInfoId(UserUtils.getUser().getInstitutionInfoId());
		sysUsersDao.insert(user);
		SysUsersEntity newU=sysUsersDao.get(user);
		int id=newU.getId();
		if(user.getIds().length()>0)
		{
			String ids=user.getIds().substring(0, user.getIds().length()-1);
			String[] idsnum=ids.split(",");
			for(String id0:idsnum)
			{   
				SysUserRole userRole=new SysUserRole();
				Integer rid=Integer.parseInt(id0);
				userRole.setUid(id);
				userRole.setRid(rid);
				sysUserRoleDao.insert(userRole);
			}
		}
		
		
	}
    //修改
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void modifyUser(SysUsersEntity user) throws Exception {
		//修改时不可修改账号
		user.setLoginAccount(null);
		user.setModifyTime(new Date());
		user.setModifyAccount(UserUtils.getUser().getLoginAccount());
		sysUsersDao.update(user);
		sysUserRoleDao.deleteByUid(user.getId());
		if(user.getIds().length()>0)
		{
			String ids=user.getIds().substring(0, user.getIds().length()-1);
			String[] idsnum=ids.split(",");
			for(String id:idsnum)
			{   
				SysUserRole userRole=new SysUserRole();
				Integer rid=Integer.parseInt(id);
				userRole.setUid(user.getId());
				userRole.setRid(rid);
				sysUserRoleDao.insert(userRole);
			}
		}
		
		
	}
	
	//修改密码
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void updateUserPwd(SysUsersEntity user) throws Exception {
		sysUsersDao.updatePwd(user);		
	}
	 

	
    //删除
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void delUser(SysUsersEntity user) throws Exception{
		user.setModifyAccount(UserUtils.getAccount());
		user.setModifyTime(new Date());
		user.setIsUsable(DBStatus.IsUsable.FALSE);
		user.setLoginFlag("0");
		sysUsersDao.update(user);
		sysUserRoleDao.deleteByUid(user.getId());
	}
    
	//用户列表
	@Override
	public Page<SysUsersEntity> getUserPage(SysUsersEntity userEntity,
			int pageNumber, int pageSize) {
		try {
            //获取总条数
			Map<Object, Object> map=new HashMap<Object, Object>();
			if(!StringUtils.isEmpty(userEntity.getLoginAccount())){
				map.put("loginAccount", userEntity.getLoginAccount());
			}
			if(!StringUtils.isEmpty(userEntity.getLoginName())){
				map.put("loginName", SqlUtils.like(userEntity.getLoginName()));
			}
			if(userEntity.getStartDate()!=null){
				map.put("startDate", userEntity.getStartDate());
			}
			if(userEntity.getEndDate()!=null){
				map.put("endDate", userEntity.getEndDate());
			}
			map.put("institutionInfoId", UserUtils.getUser().getInstitutionInfoId());
			map.put("isUsable", DBStatus.IsUsable.TRUE);
			if("0".equals(UserUtils.getUser().getIsInit())){
				//如果当前登陆用户非管理员用户，则不可查出管理员
				map.put("isInit", "0");
			}
			
            Integer totalCount=sysUsersDao.count(map);
            //分页实体
            Page<SysUsersEntity> page=new Page<SysUsersEntity>();
            page.setPage(pageNumber);
            page.setRowNum(pageSize);
            if(totalCount==null){
                return page;
            }
            //最大页数判断
            int pageM = maxPage(totalCount, page.getRowNum(), page.getPage());
            if (pageM > 0) {
                page.setPage(pageM);
            }
            if (totalCount > 0) {
            	map.put("offset",page.getOffset());
            	map.put("pageSize",page.getRowNum());
            	List<SysUsersEntity> list=sysUsersDao.getPageList(map);
                page.setRows(list);
                page.setRecords(totalCount.longValue());
            }
            return page;
        } catch (Exception e) {
        	e.printStackTrace();
            logger.error(String.format(Message.QUERY_ERROR_EXCEPTION, e));
            return null;
        }
	}

	@Override
	public void modifyPasswordById(Integer id, String newPassword) throws Exception{
		SysUsersEntity record=new SysUsersEntity();
		record.setId(id);
		record.setPassword(EntryptUtils.entryptPassword(newPassword));
		//修改用户信息
		int result=sysUsersDao.update(record);
		if(result==0){
			logger.error("用户修改密码sql异常");
			throw new RuntimeException("用户修改密码异常");
		}
	}
    //获取角色及菜单
	@Override
	@Transactional(readOnly=true)
	public SysUsersEntity getRoleAndMenus(SysUsersEntity user)
	{
		//根据用户ID从sys_user_role中取出所有用户-角色关联信息
		List<SysUserRole> userRoles=sysUserRoleDao.getByUId(user.getId());
		if(userRoles.size()>0)
		{
			//取出用户对应的角色
			List<SysRoleEntity> roles=new ArrayList<SysRoleEntity>();
			for(SysUserRole userRole:userRoles)
			{
				roles.add(sysRoleDao.getById(userRole.getRid()));
			}
			user.setRoles(roles);
			/*
			 * 			//根据角色id从sys_role_menu中取出所有角色——菜单关联信息
			List<SysRoleMenu> roleMenus=new ArrayList<SysRoleMenu>();
			for(SysRoleEntity role:roles)
			{
				List<SysRoleMenu> rm=sysRoleMenuDao.getByRId(role.getId());
				for(SysRoleMenu a:rm)
				{
					roleMenus.add(a);
				}
			}
			//取出用户对应的菜单（权限）
			List<SysMenusEntity> menus=new ArrayList<SysMenusEntity>();
			for(SysRoleMenu roleMenu:roleMenus)
			{
				menus.add(sysMenuDao.getById(roleMenu.getMid()));
			}
			
			user.setMenus(menus);
			 */

		}
	   else {
		   //为了授权时不出现空指针而添加
			user.setRoles(new ArrayList<SysRoleEntity>());
			//user.setMenus(new ArrayList<SysMenusEntity>());
		}
		 
		
		
		return user;
	}
	
	//*********************************系统管理用户结束*******************************************//
	
	//*********************************字典参数*******************************************//
	@Override
	public CfgParamEntity getParamById(Integer id) {
		return null;
	}

	@Override
	public Page<CfgParamEntity> getParamsPage(CfgParamEntity cfgParamEntity, int pageNumber, int pageSize) {
		return null;
	}

	@Override
	public void addParams(CfgParamEntity cfgParamEntity) throws Exception {
		
	}

	@Override
	public void delParams(CfgParamEntity cfgParamEntity) throws Exception {
		
	}

	@Override
	public void modifyParams(CfgParamEntity cfgParamEntity) throws Exception {
		
	}
	//*********************************字典参数结束*******************************************//
}
