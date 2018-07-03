package com.mmall.beans;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

/**
 * @Author: facedamon
 * @Description:
 * @Date: Credted in 上午12:53 2018/7/4
 * @Modified by:
 */
@Data
@Builder
public class Mail {

    private String message;
    private String subject;
    private Set<String> receivers;

}
