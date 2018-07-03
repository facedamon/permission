package com.facedamon.beans;

import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * @Author: facedamon
 * @Description:
 * @Date: Credted in 下午10:09 2018/5/14
 * @Modified by:
 */
@Getter
@Setter
public class JsonData {

    private boolean ret;

    private String msg;

    private Object data;

    public JsonData(boolean ret){
        this.ret = ret;
    }

    public  static JsonData success(Object object,String msg){
        JsonData jsonData = new JsonData(true);
        jsonData.data = object;
        jsonData.msg = msg;
        return jsonData;
    }

    public  static JsonData success(Object object){
        JsonData jsonData = new JsonData(true);
        jsonData.data = object;
        return jsonData;
    }

    public  static JsonData success(){
        return new JsonData(true);
    }

    public  static JsonData failer(String msg){
        JsonData jsonData = new JsonData(false);
        jsonData.msg = msg;
        return jsonData;
    }

    public Map<String,Object> toMap(){
        Map<String,Object> map = Maps.newHashMap();
        map.put("msg",msg);
        map.put("ret",ret);
        map.put("data",data);
        return map;
    }
}
