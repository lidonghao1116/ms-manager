package com.jiacer.modules.business.bean.operation;

import com.google.common.collect.Maps;
import com.jiacer.modules.business.bean.HandleStatusType;
import com.jiacer.modules.business.bean.IdcardInfoExtractor;
import com.jiacer.modules.business.bean.OptType;
import com.jiacer.modules.common.config.Global;
import com.jiacer.modules.common.utils.DateUtils;
import com.jiacer.modules.common.utils.EntryptUtils;
import com.jiacer.modules.common.utils.SqlUtils;
import com.jiacer.modules.common.utils.StringUtils;
import com.jiacer.modules.mybatis.entity.ApplyOrdersEntity;
import com.jiacer.modules.mybatis.entity.UserBaseInfoEntity;
import com.jiacer.modules.mybatis.entity.UserExtendInfoEntity;
import com.jiacer.modules.system.config.DBStatus;
import com.jiacer.modules.system.config.DBStatus.UserStatus;
import com.jiacer.modules.system.config.SysConstants;
import com.jiacer.modules.system.utils.UserUtils;

import java.util.Date;
import java.util.Map;

/** 
 * BOND - 封装所有参数
* @ClassName: ApplyOrdersOpt 
* @Description: 预约订单对象参数操作
* @author 贺章鹏
* @date 2016年10月24日 下午4:19:47 
*  
*/
public class ApplyOrdersOpt {
	/**
	 * 构建查询参数Map
	 * @return
	 */
	public static Map<Object, Object> buildMap(ApplyOrdersEntity applyOrdersEntity,String type){
		Map<Object, Object> map=Maps.newHashMap();
		if(HandleStatusType.PEND_APPLY.getValue().equals(type)){//待审核
			map.put("dateType", "1");//预报名
			if(applyOrdersEntity.getStartDate()!=null){
				map.put("startDate", DateUtils.joinTime(applyOrdersEntity.getStartDate(),SysConstants.MIN_TIME));
			}
			if(applyOrdersEntity.getEndDate()!=null){
				map.put("endDate", DateUtils.joinTime(applyOrdersEntity.getEndDate(),SysConstants.MAX_TIME));
			}
			
			map.put("packageId", applyOrdersEntity.getPackageId());
			map.put("courseId", applyOrdersEntity.getCourseId());
			map.put("handleStatus", DBStatus.HandleStatus.PENDING);
			map.put("orderType", DBStatus.OrderType.YUYUE);
			if(!StringUtils.isEmpty(applyOrdersEntity.getUserName())){
				map.put("userName", SqlUtils.like(applyOrdersEntity.getUserName()));
			}
		}
		if(HandleStatusType.REP_APPLY.getValue().equals(type)){//补考
			map.put("handleStatus", DBStatus.HandleStatus.PENDING);
			map.put("formType", "02");
			map.put("orderTime", new Date());
			if(!StringUtils.isEmpty(applyOrdersEntity.getUserName())){
				map.put("userName", SqlUtils.like(applyOrdersEntity.getUserName()));
			}
			map.put("courseId", applyOrdersEntity.getCourseId());
			map.put("orderTime", new Date());
			if(applyOrdersEntity.getStartDate()!=null){
				map.put("startDate", DateUtils.joinTime(applyOrdersEntity.getStartDate(),SysConstants.MIN_TIME));
			}
			if(applyOrdersEntity.getEndDate()!=null){
				map.put("endDate", DateUtils.joinTime(applyOrdersEntity.getEndDate(),SysConstants.MAX_TIME));
			}
		}
		if(HandleStatusType.NOT_MATCH.getValue().equals(type)){//不通过
			map.put("dateType", "2");//预报名
			if(applyOrdersEntity.getStartDate()!=null){
				map.put("startDate", DateUtils.joinTime(applyOrdersEntity.getStartDate(),SysConstants.MIN_TIME));
			}
			if(applyOrdersEntity.getEndDate()!=null){
				map.put("endDate", DateUtils.joinTime(applyOrdersEntity.getEndDate(),SysConstants.MAX_TIME));
			}
			map.put("packageId", applyOrdersEntity.getPackageId());
			map.put("handleStatus", DBStatus.HandleStatus.FAILED);
			if(!StringUtils.isEmpty(applyOrdersEntity.getUserName())){
				map.put("userName", SqlUtils.like(applyOrdersEntity.getUserName()));
			}
		}
		//@xiehui已退学
		if(HandleStatusType.OUT_APPLY.getValue().equals(type)){//已退学
			
			if(applyOrdersEntity.getStartDate()!=null){
				map.put("txStartDate", DateUtils.joinTime(applyOrdersEntity.getStartDate(),SysConstants.MIN_TIME));
			}
			if(applyOrdersEntity.getEndDate()!=null){
				map.put("txEndDate", DateUtils.joinTime(applyOrdersEntity.getEndDate(),SysConstants.MAX_TIME));
			}
			
			map.put("packageId", applyOrdersEntity.getPackageId());
			map.put("courseId", applyOrdersEntity.getCourseId());
			map.put("handleStatus", DBStatus.HandleStatus.OUT);
			if(!StringUtils.isEmpty(applyOrdersEntity.getUserName())){
				map.put("userName", SqlUtils.like(applyOrdersEntity.getUserName()));
			}
			/*
			map.put("dateType", "2");//预报名
			if(applyOrdersEntity.getStartDate()!=null){
				map.put("startDate", DateUtils.joinTime(applyOrdersEntity.getStartDate(),SysConstants.MIN_TIME));
			}
			if(applyOrdersEntity.getEndDate()!=null){
				map.put("endDate", DateUtils.joinTime(applyOrdersEntity.getEndDate(),SysConstants.MAX_TIME));
			}
			map.put("packageId", applyOrdersEntity.getPackageId());
			map.put("handleStatus", DBStatus.HandleStatus.OUT);
			if(!StringUtils.isEmpty(applyOrdersEntity.getUserName())){
				map.put("userName", SqlUtils.like(applyOrdersEntity.getUserName()));
			}
		*/	
			
			
		}
		if(HandleStatusType.SUCCESS_APPLY.getValue().equals(type)){//已报名
			
			map.put("dateType", "2");//已报名
			map.put("packageId", applyOrdersEntity.getPackageId());
			map.put("courseId", applyOrdersEntity.getCourseId());
			if(applyOrdersEntity.getStartDate()!=null){
				map.put("ybmStartDate", DateUtils.joinTime(applyOrdersEntity.getStartDate(),SysConstants.MIN_TIME));
			}
			if(applyOrdersEntity.getEndDate()!=null){
				map.put("ybmEndDate", DateUtils.joinTime(applyOrdersEntity.getEndDate(),SysConstants.MAX_TIME));
			}
			if(!StringUtils.isEmpty(applyOrdersEntity.getSourceType())){
				map.put("sourceType", applyOrdersEntity.getSourceType());
			}
			if(!StringUtils.isEmpty(applyOrdersEntity.getSourceTypeSec())){
				map.put("sourceTypeSec", applyOrdersEntity.getSourceTypeSec());
			}
			if(!StringUtils.isEmpty(applyOrdersEntity.getOrderStatus())){
				map.put("formType", applyOrdersEntity.getOrderStatus());
			}
			
			map.put("handleStatus", DBStatus.HandleStatus.SUCCESS);
			if(!StringUtils.isEmpty(applyOrdersEntity.getUserName())){
				map.put("userName", SqlUtils.like(applyOrdersEntity.getUserName()));
			}
		}
		return map;
	}
	
	
	
	/**
	 * 构建新增参数
	 * @param obj
	 * @return
	 */
	public static ApplyOrdersEntity buildAdd(ApplyOrdersEntity obj){
		obj.setOrderTime(new Date());
		obj.setOrderType(DBStatus.OrderType.LURU);
		obj.setHandleStatus(DBStatus.HandleStatus.SUCCESS);
		obj.setHandleTime(new Date());
		obj.setHandler(UserUtils.getUser().getId());
		return obj;
	}
	
	/**
	 * 建构修改参数
	 * @param obj
	 * @param param
	 * @return
	 */
	public static ApplyOrdersEntity buildHandle(ApplyOrdersEntity obj,ApplyOrdersEntity param){


		obj.setHandler(UserUtils.getUser().getId());



		obj.setHandleTime(new Date());
		obj.setModifyTime(param.getModifyTime());
//		obj.setModifyAccount(UserUtils.getUser().getId());
		if(OptType.UNPASS.getValue().equals(param.getOptType())){
			obj.setHandleStatus(DBStatus.HandleStatus.FAILED);
		}else if(OptType.PASS.getValue().equals(param.getOptType())){
			if(!StringUtils.isEmpty(param.getSourceType())){
				obj.setSourceType(param.getSourceType());
			}
			if(!StringUtils.isEmpty(param.getSourceTypeSec())){
				obj.setSourceTypeSec(param.getSourceTypeSec());
			}
			if(!StringUtils.isEmpty(param.getSourceValue())){
				obj.setSourceValue(param.getSourceValue());
			}
			if(!StringUtils.isEmpty(param.getSourceRemarks())){
				obj.setSourceRemarks(param.getSourceRemarks());
			}
			if(!StringUtils.isEmpty(param.getIsHasPf())){
				obj.setIsHasPf(param.getIsHasPf());
			}
			if(param.getClassNumber()!=null){	
				obj.setClassNumber(param.getClassNumber());
				obj.setClassId(param.getClassNumber());
			}
			if(!StringUtils.isEmpty(param.getIsExam())){
				obj.setIsExam(param.getIsExam());
			}
			if(param.getSchoolFee()!=null){
				obj.setSchoolFee(param.getSchoolFee());
			}
			if(param.getDeposit()!=null){
				obj.setDeposit(param.getDeposit());
			}
			
			if(param.getBookFree()!=null){
				obj.setBookFree(param.getBookFree());
			}
			if(!StringUtils.isEmpty(param.getIsStaging())){
				obj.setIsStaging(param.getIsStaging());
			}
			if(!StringUtils.isEmpty(param.getIsDepositClear())){
				obj.setIsDepositClear(param.getIsDepositClear());
			}
			if(!StringUtils.isEmpty(param.getIsDeposit())){
				obj.setIsDeposit(param.getIsDeposit());
			}
			if(param.getFirstPay()!=null){
				obj.setFirstPay(param.getFirstPay());
			}
			if(!StringUtils.isEmpty(param.getIsClear())){
				obj.setIsClear(param.getIsClear());
			}
			if(param.getMakeupFee()!=null){
				obj.setMakeupFee(param.getMakeupFee());
			}
			if(param.getUserName()!=null){
				obj.setUserName(param.getUserName());
			}
			if (StringUtils.isBlank(param.getFormType())) {
				if (HandleStatusType.REP_APPLY.getValue().equals(param.getType())) {
					// xiehui如果是补考订单生产的，则状态是补考
					obj.setFormType("02");
				} else {
					//xiehui 如果是已报名订单生产的，则状态是正常
					obj.setFormType("01");
				}
			}
			obj.setHandleStatus(DBStatus.HandleStatus.SUCCESS);
			if(!StringUtils.isEmpty(param.getClassTime())){
				obj.setClassTime(param.getClassTime());
			}
		}
		return obj;
	}
	
	public static ApplyOrdersEntity buildModify(ApplyOrdersEntity obj,ApplyOrdersEntity param){
		obj.setModifyTime(new Date());
		obj.setModifyAccount(UserUtils.getUser().getId());
		if(!StringUtils.isEmpty(param.getContacts())){
			obj.setContacts(param.getContacts());
		}
		if(!StringUtils.isEmpty(param.getContactPhone())){
			obj.setContactPhone(param.getContactPhone());
		}
		if(!StringUtils.isEmpty(param.getSourceRemarks())){
			obj.setSourceRemarks(param.getSourceRemarks());
		}
		if(param.getOrterFee() != null){
			obj.setOrterFee(param.getOrterFee());
		}
		if(!StringUtils.isEmpty(param.getSourceType())){
			obj.setSourceType(param.getSourceType());
		}
		if(!StringUtils.isEmpty(param.getSourceTypeSec())){
			obj.setSourceTypeSec(param.getSourceTypeSec());
		}
		if(!StringUtils.isEmpty(param.getSourceValue())){
			obj.setSourceValue(param.getSourceValue());
		}
		if(!StringUtils.isEmpty(param.getSourceRemarks())){
			obj.setSourceRemarks(param.getSourceRemarks());
		}
		if(!StringUtils.isEmpty(param.getIsHasPf())){
			obj.setIsHasPf(param.getIsHasPf());
		}
		if(param.getClassNumber()!=null){
			obj.setClassNumber(param.getClassNumber());
			obj.setClassId(param.getClassNumber());
		}
		if(param.getBookFree()!=null){
			obj.setBookFree(param.getBookFree());
		}
		if(!StringUtils.isEmpty(param.getIsStaging())){
			obj.setIsStaging(param.getIsStaging());
		}
		if(param.getFirstPay()!=null){
			obj.setFirstPay(param.getFirstPay());
		}
		if(!StringUtils.isEmpty(param.getIsClear())){
			obj.setIsClear(param.getIsClear());
			obj.setStagingClearAccount(UserUtils.getUser().getId());
			obj.setStagingClearTime(new Date());
		}
		
		if(!StringUtils.isEmpty(param.getIsDepositClear())){
			obj.setIsDepositClear(param.getIsDepositClear());
			obj.setDepositClearAccount(UserUtils.getUser().getId());
			obj.setDepositClearTime(new Date());
		}
		if(!StringUtils.isEmpty(param.getUserName())){
			obj.setUserName(param.getUserName());
		}
		return obj;
	}
	
	/**
	 * 后台手动生成客户订单--构建用户信息
	 * @param obj
	 * @return
	 */
	public static UserBaseInfoEntity buildUserInfo(ApplyOrdersEntity obj){
		UserBaseInfoEntity user=obj.getUserBaseInfo();
		user.setRegisterTime(new Date());
		user.setSalt(String.format(SysConstants.ENTRY_STRING, user.getMobile()));
		user.setUserStatus(UserStatus.NOMAL);
		user.setIsLocked(Global.NO);
		user.setUserType(DBStatus.UserType.NORMAL);
		user.setAddress(obj.getAddress());
		user.setHeadImage(obj.getHeadImage());
		user.setExpiredTime(obj.getExpiredTime());
		user.setIssueOrg(obj.getIssueOrg());
		user.setMobile(obj.getMobile());
		user.setCertNo(obj.getCertNo());
		user.setCertType("01");
		user.setBirthplace(obj.getCertNo().substring(0, 2));

		return user;
	}
	
	/**
	 * 后台手动生成客户订单--构建用户扩展信息
	 * @param obj
	 * @return
	 */
	public static UserExtendInfoEntity buildUserExtend(ApplyOrdersEntity obj){
		UserExtendInfoEntity entity=obj.getUserExtendInfo();
		entity.setCertType(DBStatus.CertType.SFZ);
	    IdcardInfoExtractor idcardInfo=new IdcardInfoExtractor(obj.getCertNo());
		entity.setAge(idcardInfo.getAge());
		entity.setSex(idcardInfo.getGender());
		entity.setBirthplace(obj.getCertNo().substring(0, 2));
		entity.setEducation(obj.getEducation());
		entity.setNation(obj.getNation());
		entity.setContactPhone(obj.getContactPhone());
		entity.setContacts(obj.getContacts());
		return entity;
	}



	/**
	 * 构建用户扩展信息
	 * @return
	 */
	public static UserExtendInfoEntity buildSpecialUserExtend(UserExtendInfoEntity obj,UserExtendInfoEntity param) {
		
		if(!StringUtils.isEmpty(param.getUserName())){
			obj.setUserName(param.getUserName());
		}
		
		if(!StringUtils.isEmpty(param.getCertNo())){
			obj.setCertNo(param.getCertNo());
		}
		
		if(!StringUtils.isEmpty(param.getEducation())){
			obj.setEducation(param.getEducation());
		}
		
		if(!StringUtils.isEmpty(param.getNation())){
			obj.setNation(param.getNation());
		}
		
		if(!StringUtils.isEmpty(param.getBirthplace())){
			obj.setBirthplace(param.getBirthplace());
		}
		
		if(!StringUtils.isEmpty(param.getAddress())){
			obj.setAddress(param.getAddress());
		}
		
		obj.setContacts(param.getContacts());
		obj.setContactPhone(param.getContactPhone());
		return obj;
	}
	
	public static UserBaseInfoEntity buildModifyPassWord(UserBaseInfoEntity user,UserExtendInfoEntity entity){
		user.setLoginPassword(EntryptUtils.entryptUserPassword(entity.getCertNo().substring(entity.getCertNo().length()-6,entity.getCertNo().length()),user.getMobile()));
		return user;
	}
}
