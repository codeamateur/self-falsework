package com.tianqian.self.dao.role;

import com.tianqian.self.model.entity.role.SysRole;

import java.util.List;

public interface SysRoleDao {
    int deleteByPrimaryKey(Long id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

    List<SysRole> selectRolesByUserId(Long userId);
}