package com.tianqian.self.service.user;

import com.github.pagehelper.PageInfo;
import com.tianqian.self.model.entity.user.sysUser;

import java.util.List;

public interface SysUserService {

    /**
     * 新增用户
     *
     * @return
     */
    int addUser(sysUser user);

    /**
     * 修改用户
     *
     * @return
     */
    int modifyUser(sysUser user);

    /**
     * 根据id获取用户
     *
     * @param id
     * @return
     */
    sysUser selectByPrimaryKey(Long id);

    /**
     * 获取用户列表
     *
     * @return
     */
    List<sysUser> getList();

    /**
     * 分页获取用户列表
     *
     * @return
     */
    PageInfo<sysUser> getPageByCriteria();
}