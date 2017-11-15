package com.tianqian.self.dao.permission;

import com.tianqian.self.model.entity.permission.SysPermission;

import java.util.List;

public interface SysPermissionDao {
    int deleteByPrimaryKey(Long id);

    int insert(SysPermission record);

    int insertSelective(SysPermission record);

    SysPermission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysPermission record);

    int updateByPrimaryKey(SysPermission record);

    List<SysPermission> selectPermissionsByRoleId(Long roleId);
}