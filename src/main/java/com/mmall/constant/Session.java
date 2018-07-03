package com.mmall.constant;

/**
 * @Author: facedamon
 * @Description:
 * @Date: Credted in 上午12:23 2018/7/4
 * @Modified by:
 */
public enum Session {
    USER("user");

    private String value;

    Session(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
