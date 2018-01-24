package com.jiacer.modules.common.utils;

import com.jiacer.modules.common.persistence.ModelSerializable;

/**
 * 封装返回数据
 * @author hzp
 *
 */
public class JsonResult implements ModelSerializable{

    private static final long serialVersionUID = -3941817907260975651L;
    private boolean success;
    private String code;
    private String msg;
    private String toUrl;
    private Object jsonData;

    public JsonResult() {
    }

    public JsonResult(boolean success,String code, String msg, Object data) {
    	this.code=code;
        this.success = success;
        this.msg = msg;
        this.jsonData = data;
    }
    
    public JsonResult(boolean success, String msg, Object data) {
        this.success = success;
        this.msg = msg;
        this.jsonData = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getJsonData() {
        return jsonData;
    }

    public void setJsonData(Object jsonData) {
        this.jsonData = jsonData;
    }

    public String getToUrl() {
        return toUrl;
    }

    public void setToUrl(String toUrl) {
        this.toUrl = toUrl;
    }
    
}
