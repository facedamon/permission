package com.facedamon.param;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * @Authoe facedamon
 * @Description:
 * @Date: Credted in 下午9:21 2018/5/18
 * @Modified by:
 */
@Getter
@Setter
@ToString
public class DeptParam {

    private Integer id;

    @NotBlank(message = "部门名称不能为空")
    @Length(max = 15 ,min = 2,message = "部门名称长度应在2-15个字之间")
    private String name;

    private Integer parentId = 0;

    @NotNull(message = "展示顺序不能为空")
    private Integer seq;

    @Length(max = 150,message = "备注成都最大为150个字")
    private String remark;
}
