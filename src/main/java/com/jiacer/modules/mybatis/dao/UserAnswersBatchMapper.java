package com.jiacer.modules.mybatis.dao;

import java.util.List;
import java.util.Map;

import com.jiacer.modules.common.persistence.CrudDao;
import com.jiacer.modules.common.persistence.annotation.MyBatisDao;
import com.jiacer.modules.mybatis.entity.UserAnswersBatchEntity;
import com.jiacer.modules.mybatis.entity.extend.LearnRecordEntity;

@MyBatisDao
public interface UserAnswersBatchMapper extends CrudDao<UserAnswersBatchEntity>{

	/**
	 * @param map
	 * @return
	 */
	Integer learnCount(Map<Object, Object> map);

	/**
	 * @param map
	 * @return
	 */
	List<LearnRecordEntity> getLearnPageList(Map<Object, Object> map);

	/**
	 * @param map
	 * @return
	 */
	LearnRecordEntity getLearnRecord(Map<Object, Object> map);
}