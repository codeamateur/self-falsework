package com.tianqian.self.service.user;

import com.github.pagehelper.PageInfo;
import com.tianqian.self.model.dto.user.SysUserQueryDto;
import com.tianqian.self.model.entity.user.SysUser;

import java.util.List;

public interface SysUserService {

    /**
     * 新增用户
     *
     * @return
     */
    int addUser(SysUser user);

    /**
     * 修改用户
     *
     * @return
     */
    int modifyUser(SysUser user);

    /**
     * 根据id获取用户
     *
     * @param id
     * @return
     */
    SysUser selectByPrimaryKey(Long id);

    /**
     * 获取用户列表
     *
     * @return
     */
    List<SysUser> getList();

    /**
     * 分页获取用户列表
     *
     * @return
     */
    PageInfo<SysUser> getPageByCriteria(SysUserQueryDto dto);

    /**
     * 删除用户
     *
     * @return
     */
    int deleteUser(Long id);
}