package com.facedamon.service;

import com.google.common.base.Preconditions;
import com.facedamon.common.RequestHolder;
import com.facedamon.dao.SysDeptMapper;
import com.facedamon.exception.ParamException;
import com.facedamon.model.SysDept;
import com.facedamon.param.DeptParam;
import com.facedamon.util.BeanValidator;
import com.facedamon.util.IpUtil;
import com.facedamon.util.Level;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Authoe facedamon
 * @Description:
 * @Date: Credted in 下午9:45 2018/5/18
 * @Modified by:
 */
@Service
public class SysDeptService {

    @Autowired
    private SysDeptMapper sysDeptMapper;

    public void save(DeptParam param){
        BeanValidator.check(param);
        if(checkExists(param.getParentId(),param.getName(),param.getId())){
            throw new ParamException("同一层级下有相同的部门名称");
        }

        //builder SysDept
        SysDept sysDept = SysDept.builder().name(param.getName())
                                .parentId(param.getParentId())
                                .seq(param.getSeq())
                                .remark(param.getRemark())
                                .build();
        sysDept.setLevel(Level.calculateLevel(getLevel(sysDept.getParentId()),sysDept.getParentId()));
        sysDept.setOperator(RequestHolder.getUserLocal().getUsername());
        sysDept.setOperatorIp(IpUtil.getRemoteIp(RequestHolder.getRequestLocal()));
        sysDept.setOperatorTime(new Date());

        sysDeptMapper.insertSelective(sysDept);
    }

    public void update(DeptParam param){
        BeanValidator.check(param);
        SysDept before = sysDeptMapper.selectByPrimaryKey(param.getId());
        Preconditions.checkNotNull(before,"待更新的部门不存在");

        if(checkExists(param.getParentId(),param.getName(),param.getId())){
            throw new ParamException("同一层级下有相同的部门名称");
        }

        //builder SysDept with id
        SysDept after = SysDept.builder().id(param.getId()).name(param.getName())
                .parentId(param.getParentId())
                .level(Level.calculateLevel(getLevel(param.getParentId()),param.getParentId()))
                .seq(param.getSeq())
                .remark(param.getRemark())
                .build();

        after.setLevel(Level.calculateLevel(getLevel(after.getParentId()),after.getParentId()));
        after.setOperator(RequestHolder.getUserLocal().getUsername());
        after.setOperatorIp(IpUtil.getRemoteIp(RequestHolder.getRequestLocal()));
        after.setOperatorTime(new Date());

        updateWithChild(before,after);
    }

    @Transactional(rollbackForClassName = "PermissionException")
    void updateWithChild(SysDept before, SysDept after){
        String newLevelPrefix = after.getLevel();
        String oldLevelPrefix = before.getLevel();

        /**
         * 如果更新前后的level值不一样，则需要更新子部门的level
         */
        if (!before.getLevel().equals(after.getLevel())) {
            List<SysDept> deptList = sysDeptMapper.getChildBDeptListByLevel(before.getLevel());
            if (CollectionUtils.isNotEmpty(deptList)) {
                for (SysDept sysDept : deptList) {
                    String level = sysDept.getLevel();
                    if (level.indexOf(oldLevelPrefix) == 0){
                        level = newLevelPrefix + level.substring(oldLevelPrefix.length());
                        sysDept.setLevel(level);
                    }
                }
                sysDeptMapper.batchUpdateLevel(deptList);
            }
        }
        sysDeptMapper.updateByPrimaryKey(after);
    }

    /**
     * 检查同一层级下是否有相同名称的部门
     * @param parentId
     * @param name
     * @param deptId
     * @return
     */
    private boolean checkExists(Integer parentId,String name,Integer deptId){
        return sysDeptMapper.countByNameAndParentId(parentId,name,deptId) > 0;
    }

    private String getLevel(Integer deptId){
        SysDept sysDept = sysDeptMapper.selectByPrimaryKey(deptId);
        if(null == sysDept){
            return null;
        }
        return sysDept.getLevel();
    }

}
