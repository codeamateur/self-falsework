package com.tianqian.self.dao.user;

import com.tianqian.self.model.entity.user.sysUser;

public interface sysUserDao {
    int deleteByPrimaryKey(Long id);

    int insert(sysUser record);

    int insertSelective(sysUser record);

    sysUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(sysUser record);

    int updateByPrimaryKey(sysUser record);
}