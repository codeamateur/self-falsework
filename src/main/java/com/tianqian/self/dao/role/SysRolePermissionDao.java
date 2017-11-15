package com.tianqian.self.dao.role;

import com.tianqian.self.model.entity.role.SysRolePermission;

public interface SysRolePermissionDao {
    int insert(SysRolePermission record);

    int insertSelective(SysRolePermission record);
}