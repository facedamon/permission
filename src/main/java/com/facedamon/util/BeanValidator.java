package com.facedamon.util;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.facedamon.exception.ParamException;
import org.apache.commons.collections.MapUtils;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.*;

/**
 * @Authoe facedamon
 * @Description:
 * @Date: Credted in 下午8:56 2018/5/17
 * @Modified by:
 */
public class BeanValidator {

    private static ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

    public static <T> Map<String,String> validate(T t,Class... groups){
        Validator validator = validatorFactory.getValidator();
        Set validate = validator.validate(t,groups);

        if(validate.isEmpty()){
            return Collections.emptyMap();
        }else{
            LinkedHashMap errors = Maps.newLinkedHashMap();
            Iterator lt = validate.iterator();
            while(lt.hasNext()){
                ConstraintViolation constraintViolation = (ConstraintViolation) lt.next();
                errors.put(constraintViolation.getPropertyPath().toString(),constraintViolation.getMessage());
            }
            return errors;
        }
    }

    public static Map<String,String> validateList(Collection<?> collection){
        Preconditions.checkNotNull(collection);
        Iterator lt = collection.iterator();
        Map errors;

        do {
            if(!lt.hasNext()){
                return Collections.emptyMap();
            }
            Object object = lt.next();
            errors = validate(object,new Class[0]);
        }while (errors.isEmpty());

        return errors;
    }

    public static Map<String,String> validateObject(Object first,Object... objects){
        if(objects != null && objects.length > 0){
            return validateList(Lists.asList(first,objects));
        }else{
            return validate(first,new Class[0]);
        }
    }

    public static void check(Object object) throws ParamException{
        Map map = validate(object);
        if(MapUtils.isNotEmpty(map)){
            throw new ParamException(map.toString());
        }
    }

}
