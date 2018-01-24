package com.jiacer.modules.business.bean;

/**
 * @author 贺章鹏
 * @ClassName: HandleStatusType
 * @Description: 报名管理查询类型  预报名学员PEND_APPLY、不符合学员NOT_MATCH、补考学员REP_APPLY、已报名学员SUCCESS_APPLY，RETREAT_FEE_APPLY 退费
 * 在新增处理是增加处理（若用户之前没用通过的订单则可编辑修改用户的信息。）若存在则仅仅修改用户订单信息
 * @date 2016年10月21日 下午5:15:50
 */
public enum HandleStatusType {
    PEND_APPLY("PEND_APPLY"), NOT_MATCH("NOT_MATCH"), REP_APPLY("REP_APPLY"), SUCCESS_APPLY("SUCCESS_APPLY"), SPECIAL("SPECIAL"), OUT_APPLY("OUT_APPLY"), RETREAT_FEE_APPLY("RETREAT_FEE_APPLY");

    private final String value;

    HandleStatusType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
