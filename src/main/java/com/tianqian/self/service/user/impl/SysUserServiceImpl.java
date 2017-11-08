package com.tianqian.self.service.user.impl;

import com.github.pagehelper.PageInfo;
import com.tianqian.self.dao.user.sysUserDao;
import com.tianqian.self.model.entity.user.sysUser;
import com.tianqian.self.service.user.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class SysUserServiceImpl implements SysUserService{

    @Autowired
    private sysUserDao sysUserDao;

    @Override
    public int addUser(sysUser user) {
        return sysUserDao.insertSelective(user);
    }

    @Override
    public int modifyUser(sysUser user) {
        return sysUserDao.updateByPrimaryKey(user);
    }

    @Override
    public sysUser selectByPrimaryKey(Long id) {
        return sysUserDao.selectByPrimaryKey(id);
    }

    @Override
    public List<sysUser> getList() {
        return null;
    }

    @Override
    public PageInfo<sysUser> getPageByCriteria() {
        return null;
    }
}
