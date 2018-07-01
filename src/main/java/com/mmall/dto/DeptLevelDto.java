package com.mmall.dto;

import com.google.common.collect.Lists;
import com.mmall.model.SysDept;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * @Authoe facedamon
 * @Description:
 * @Date: Credted in 下午10:08 2018/5/18
 * @Modified by:
 */
@Getter
@Setter
@ToString
public class DeptLevelDto extends SysDept {

    /**
     * 下一层部门
     */
    private List<DeptLevelDto> deptLevelDtoList = Lists.newArrayList();

    public static DeptLevelDto adapt(SysDept dept){
        DeptLevelDto dto = new DeptLevelDto();
        BeanUtils.copyProperties(dept,dto);
        return dto;
    }
}
