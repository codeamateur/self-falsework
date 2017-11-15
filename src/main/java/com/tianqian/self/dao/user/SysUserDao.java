package com.tianqian.self.dao.user;

import com.tianqian.self.model.dto.user.SysUserQueryDto;
import com.tianqian.self.model.entity.user.SysUser;

import java.util.List;

public interface SysUserDao {
    int deleteByPrimaryKey(Long id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    List<SysUser> getPageByCriteria(SysUserQueryDto dto);

    SysUser selectOneByLoginName(String loginName);

}