package com.jiacer.modules.mybatis.model;

import java.math.BigDecimal;
import java.util.Date;

import com.jiacer.modules.common.persistence.ModelSerializable;

public class UserAnswersBatch implements ModelSerializable{
   
	private static final long serialVersionUID = 1L;

	/**
     * 表：user_answers_batch
     * 字段：ID
     * 注释：主键
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * 表：user_answers_batch
     * 字段：USER_ID
     * 注释：用户ID
     *
     * @mbggenerated
     */
    private Integer userId;

    /**
     * 表：user_answers_batch
     * 字段：START_TIME
     * 注释：答题时间
     *
     * @mbggenerated
     */
    private Date startTime;

    /**
     * 表：user_answers_batch
     * 字段：END_TIME
     * 注释：答题结束时间
     *
     * @mbggenerated
     */
    private Date endTime;

    /**
     * 表：user_answers_batch
     * 字段：FINISH_TIME
     * 注释：用户答题完成时间
     *
     * @mbggenerated
     */
    private Date finishTime;

    /**
     * 表：user_answers_batch
     * 字段：SCORE
     * 注释：用户答题分数
     *
     * @mbggenerated
     */
    private BigDecimal score;

    /**
     * 表：user_answers_batch
     * 字段：IS_FINISHED
     * 注释：是否完成
     *
     * @mbggenerated
     */
    private String isFinished;

    /**
     * 表：user_answers_batch
     * 字段：type_id
     * 注释：分类id
     *
     * @mbggenerated
     */
    private Integer typeId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public String getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(String isFinished) {
        this.isFinished = isFinished == null ? null : isFinished.trim();
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }
}