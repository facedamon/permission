package com.facedamon.service;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.facedamon.dao.SysDeptMapper;
import com.facedamon.dto.DeptLevelDto;
import com.facedamon.model.SysDept;
import com.facedamon.util.Level;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @Authoe facedamon
 * @Description:
 * @Date: Credted in 下午11:54 2018/5/18
 * @Modified by:
 */
@Service
public class SysTreeService {
    @Resource
    private SysDeptMapper sysDeptMapper;

    public List<DeptLevelDto> deptTree(){
        List<SysDept> sysDepts = sysDeptMapper.getAllDept();
        List<DeptLevelDto> deptLevelDtos = Lists.newArrayList();

        if(CollectionUtils.isEmpty(sysDepts)){
            return deptLevelDtos;
        }
        for(SysDept sysDept : sysDepts){
            deptLevelDtos.add(DeptLevelDto.adapt(sysDept));
        }
        return deptListToTree(deptLevelDtos);
    }

    private List<DeptLevelDto> deptListToTree(List<DeptLevelDto> deptLevelDtos){
        if(CollectionUtils.isEmpty(deptLevelDtos)){
            return Lists.newArrayList();
        }
        /**
         * temp data struct
         * key:current level;value:current dept list
         */
        Multimap<String,DeptLevelDto> levelDeptMap = ArrayListMultimap.create();
        List<DeptLevelDto> rootList = Lists.newArrayList();

        for(DeptLevelDto dto : deptLevelDtos){
            levelDeptMap.put(dto.getLevel(),dto);
            if(dto.getLevel().equals(Level.ROOT.getValue())){
                rootList.add(dto);
            }
        }
        Collections.sort(rootList,comparator);
        transformDeptTree(deptLevelDtos,Level.ROOT.getValue(),levelDeptMap);

        return rootList;
    }

    private void transformDeptTree(List<DeptLevelDto> deptLevelDtos,String level,Multimap<String,DeptLevelDto> levelDeptMap){
        for (int i = 0; i < deptLevelDtos.size(); i++){
            /**
             * 获取当前层数据
             */
            DeptLevelDto deptLevelDto = deptLevelDtos.get(i);

            /**
             * 处理当前层数据
             */
            String nextLevel = Level.calculateLevel(level,deptLevelDto.getId());

            /**
             * 获取下一层数据
             */
            List<DeptLevelDto> tempDeptList = (List<DeptLevelDto>) levelDeptMap.get(nextLevel);

            /**
             * 排序数据
             */
            if (CollectionUtils.isNotEmpty(tempDeptList)){
                Collections.sort(tempDeptList,comparator);
                /**
                 * 设置下一层部门
                 */
                deptLevelDto.setDeptLevelDtoList(tempDeptList);

                /**
                 * 进入下一层处理
                 */
                transformDeptTree(tempDeptList,nextLevel,levelDeptMap);
            }

        }
    }

    /**
     * 从小到大排序比较器
     */
    private Comparator<DeptLevelDto> comparator  = new Comparator<DeptLevelDto> (){
        @Override
        public int compare(DeptLevelDto o1, DeptLevelDto o2) {
            return o1.getSeq() - o2.getSeq();
        }
    };
}
