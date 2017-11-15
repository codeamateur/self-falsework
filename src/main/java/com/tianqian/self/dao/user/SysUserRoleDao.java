package com.tianqian.self.dao.user;

import com.tianqian.self.model.entity.user.SysUserRole;

public interface SysUserRoleDao {
    int deleteByPrimaryKey(Long id);

    int insert(SysUserRole record);

    int insertSelective(SysUserRole record);

    SysUserRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUserRole record);

    int updateByPrimaryKey(SysUserRole record);
}