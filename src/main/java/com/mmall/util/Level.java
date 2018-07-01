package com.mmall.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @Authoe facedamon
 * @Description:
 * @Date: Credted in 下午9:35 2018/5/18
 * @Modified by:
 */
public enum Level {
    SEPARATOR("."),
    ROOT("0");

    private String value;

    public String getValue() {
        return value;
    }

    Level(String value) {
        this.value = value;
    }

    public static String calculateLevel(String parentLevel,Integer parentId){
        if(StringUtils.isBlank(parentLevel)){
            return ROOT.value;
        }
        return StringUtils.join(parentLevel,SEPARATOR.value,parentId);
    }
}
