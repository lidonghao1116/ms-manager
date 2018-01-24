package com.jiacer.modules.system.config;

public class Message {
	public static final String QUERY_ERROR_EXCEPTION="查询异常,原因：%s";
	public static final String PARAM_ERROR_EXCEPTION="参数异常,原因：%s";
	public static final String ERROR_EXCEPTION="操作异常,原因：%s";
	public static final String CHECK_DATA_EXCEPTION="校验数据异常,原因%s";
	
	public static final String ADD_SUCCESS_MSG="新增成功";
	public static final String UPDATE_SUCCESS_MSG="修改成功";
	public static final String DELETE_SUCCESS_MSG="删除成功";
	public static final String APPROVE_SUCCESS_MSG="审核成功";
	public static final String FAILED_MSG="操作失败";
	public static final String SUCCESS_MSG="操作成功";
	public static final String PARAM_ERROR_MSG="参数异常";
	public static final String VALID_SUCCESS_MSG="校验成功";
	public static final String ILLEGAL_REQUEST_MSG="非法请求";
	public static final String REPEAT_REQUEST_MSG="数据已被处理,请确认数据状态刷新页面";
	public static final String CHECK_DATA_ERROR_MSG="校验数据异常";
	
	public static final String BUILD_MESSAGE_ERROR_MSG="组装提示信息失败";
	public static final String UPDATE_DATE_NOEXIST="要更新的数据对象不存在";
	
	/**
	 * 组装返回错误信息
	 * @param message
	 * @param res
	 * @return
	 */
	public static String buildErrInfo(String message,Object res){
		try {
			return String.format(message, res);
		} catch (Exception e) {
			return Message.BUILD_MESSAGE_ERROR_MSG;
		}
	}
}
