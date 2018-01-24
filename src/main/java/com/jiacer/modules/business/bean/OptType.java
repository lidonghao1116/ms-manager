package com.jiacer.modules.business.bean;

/** 
* @ClassName: OptType 
* @Description: 操作类型
* @author 贺章鹏
* @date 2016年10月28日 下午4:20:44 
*  
*/
public enum OptType {
	DRAFT("opt00"), INSERT("opt01"),UPDATE("opt02"),PASS("opt03"),UNPASS("opt04");
    
    private final String value;

    OptType(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
}
