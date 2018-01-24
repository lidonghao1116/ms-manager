package com.jiacer.modules.business.bean.operation;

import com.jiacer.modules.common.utils.DateUtils;
import com.jiacer.modules.common.utils.StringUtils;
import com.jiacer.modules.mybatis.entity.UserScoresEntity;
import org.apache.poi.util.StringUtil;

import java.text.SimpleDateFormat;

/** 
* @ClassName: ScoresOpt 
* @Description: 成绩管理操作
* @author 贺章鹏
* @date 2016年10月24日 下午4:14:59 
*  
*/
public class ScoresOpt {

	public static UserScoresEntity buildModify(UserScoresEntity obj,UserScoresEntity param){
		if(param.getComprehensiveScores() != null){
			obj.setComprehensiveScores(param.getComprehensiveScores());
		}
		if(param.getAbilityScores() != null){
			obj.setAbilityScores(param.getAbilityScores());
		}
		if(param.getTheoryScores() != null){
			obj.setTheoryScores(param.getTheoryScores());
		}
		if(param.getPoScores() != null){
			obj.setPoScores(param.getPoScores());
		}
		if(param.getIssuingDate() != null){
			obj.setIssuingDate(param.getIssuingDate());
		}
		if(param.getCertificateNo() != null){
			obj.setCertificateNo(param.getCertificateNo());
		}
		if(param.getIsExam() != null){
			obj.setIsExam(param.getIsExam());
		}
		if(param.getExamResult() != null){
			obj.setExamResult(param.getExamResult());
		}
		if(param.getMakeupExamFree() != null){
			obj.setMakeupExamFree(param.getMakeupExamFree());
		}
		if(StringUtils.isNotBlank(param.getIssuingDateTxt())
				&& obj.getIssuingDate()==null
				&& StringUtils.isNotBlank(param.getCertificateNo())){
			obj.setIssuingDate(DateUtils.parseDate(param.getIssuingDateTxt()));
		}
		return obj;
	}
}






















