package com.facedamon.beans;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;

/**
 * @Author: facedamon
 * @Description:
 * @Date: Credted in 下午12:17 2018/7/3
 * @Modified by:
 */
public class PageQuery {

    @Getter
    @Setter
    @Min(value = 1,message = "当前页码不合法")
    private int pageNo = 1;

    @Getter
    @Setter
    @Min(value = 1,message = "每页展示数量不合法")
    private int pageSize = 10;

    /**
     * 偏移量
     */
    @Setter
    private int offset;

    public int getOffset(){
        return (pageNo - 1) * pageSize;
    }
}
