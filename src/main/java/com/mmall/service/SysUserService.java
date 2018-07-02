package com.mmall.service;

import com.google.common.base.Preconditions;
import com.mmall.dao.SysUserMapper;
import com.mmall.exception.ParamException;
import com.mmall.model.SysUser;
import com.mmall.param.UserParam;
import com.mmall.util.BeanValidator;
import com.mmall.util.MD5Util;
import com.mmall.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author: facedamon
 * @Description:
 * @Date: Credted in 下午8:18 2018/7/1
 * @Modified by:
 */
@Service
public class SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    public void save(UserParam userParam){
        BeanValidator.check(userParam);
        if (checkTelePhoneExis(userParam.getTelephone(),userParam.getId())){
            throw new ParamException("电话已被占用");
        }
        if (checkEmailExis(userParam.getMail(),userParam.getId())){
            throw new ParamException("邮箱已被占用");
        }
        String password = PasswordUtil.randomPassword();
        password = "123456";
        String encryptedPassword = MD5Util.encrypt(password);

        SysUser user = SysUser.builder().deptId(userParam.getDeptId())
                .id(userParam.getId())
                .mail(userParam.getMail())
                .password(encryptedPassword)
                .telephone(userParam.getTelephone())
                .username(userParam.getUsername())
                .remark(userParam.getRemark())
                .status(userParam.getStatus())
                .build();
        /**
         * TODO
         */
        user.setOperator("system");
        /**
         * TODO
         */
        user.setOperatorIp("127.0.0.1");
        user.setOperatorTime(new Date());

        /**
         * TODO Send Email
         */

        sysUserMapper.insertSelective(user);
    }

    /**
     * houtai update
     * @param userParam
     */
    public void update(UserParam userParam){
        BeanValidator.check(userParam);
        if (checkTelePhoneExis(userParam.getTelephone(),userParam.getId())){
            throw new ParamException("电话已被占用");
        }
        if (checkEmailExis(userParam.getMail(),userParam.getId())){
            throw new ParamException("邮箱已被占用");
        }
        SysUser before = sysUserMapper.selectByPrimaryKey(userParam.getId());
        Preconditions.checkNotNull(before,"待更新的用户不存在");
        SysUser after = SysUser.builder().id(userParam.getId())
                .username(userParam.getUsername())
                .telephone(userParam.getTelephone())
                .mail(userParam.getMail())
                .deptId(userParam.getDeptId())
                .status(userParam.getStatus())
                .remark(userParam.getRemark())
                .build();
        /**
         * TODO
         */
        after.setOperator("system");
        /**
         * TODO
         */
        after.setOperatorIp("127.0.0.1");
        after.setOperatorTime(new Date());
        sysUserMapper.updateByPrimaryKeySelective(after);
    }

    private boolean checkTelePhoneExis(String telPhone, Integer userId){
        return sysUserMapper.countByTelephone(telPhone,userId) > 0;
    }

    private boolean checkEmailExis(String mail, Integer userId){
        return sysUserMapper.countByMail(mail,userId) > 0;
    }

    public SysUser findByKeyword(String keyword){
        return sysUserMapper.findByKeyword(keyword);
    }

}
