package com.jiacer.modules.business.service;

/**
 * 短信
 */
public interface SmsService {

    void sendCode(String mobile);

    boolean checkCode(String mobile, String code);
}
