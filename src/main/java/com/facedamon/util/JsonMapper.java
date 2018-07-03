package com.facedamon.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.ser.impl.SimpleFilterProvider;
import org.codehaus.jackson.type.TypeReference;

/**
 * @Authoe facedamon
 * @Description:
 * @Date: Credted in 下午9:34 2018/5/17
 * @Modified by:
 */
@Slf4j
public class JsonMapper {
    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.setFilters(new SimpleFilterProvider().setFailOnUnknownId(false));
        objectMapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_EMPTY);
    }

    public static <T> String obj2String (T t){
        if(t == null){
            return null;
        }

        try {
            return t instanceof String ? (String) t : objectMapper.writeValueAsString(t);
        }catch (Exception e){
            log.error("parse object to string exception,error:{}",e);
            return null;
        }
    }

    public static <T> T string2Obj(String src, TypeReference<T> typeReference){
        if(StringUtils.isBlank(src) || typeReference == null){
            return null;
        }

        try {
            return (T) (typeReference.getType().equals(String.class) ? src : objectMapper.readValue(src,typeReference));
        }catch (Exception e){
            log.error("parse string to object exception,string:{},type:{},error:{}",src,typeReference.getType(),e);
            return null;
        }
    }

}
