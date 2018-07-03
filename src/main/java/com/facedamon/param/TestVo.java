package com.facedamon.param;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Authoe facedamon
 * @Description:
 * @Date: Credted in 下午10:00 2018/5/17
 * @Modified by:
 */
@Getter
@Setter
public class TestVo {

    @NotBlank
    private String msg;

    @NotNull(message = "id 不能为空")
    @Max(value = 10,message = "id 最大为10")
    @Min(value = 0,message = "id 最小大于等于0")
    private Integer id;

    private List<String> str;
}
