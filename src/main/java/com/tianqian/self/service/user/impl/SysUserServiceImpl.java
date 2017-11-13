package com.tianqian.self.service.user.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tianqian.self.dao.user.SysUserDao;
import com.tianqian.self.model.dto.user.SysUserQueryDto;
import com.tianqian.self.model.entity.user.SysUser;
import com.tianqian.self.service.user.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class SysUserServiceImpl implements SysUserService{

    @Autowired
    private SysUserDao sysUserDao;

    @Override
    public int addUser(SysUser user) {
        return sysUserDao.insertSelective(user);
    }

    @Override
    public int modifyUser(SysUser user) {
        return sysUserDao.updateByPrimaryKey(user);
    }

    @Override
    public SysUser selectByPrimaryKey(Long id) {
        return sysUserDao.selectByPrimaryKey(id);
    }

    @Override
    public List<SysUser> getList() {
        return null;
    }

    @Override
    public PageInfo<SysUser> getPageByCriteria(SysUserQueryDto dto) {
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        return new PageInfo<SysUser>(sysUserDao.getPageByCriteria(dto));
    }

    @Override
    public int deleteUser(Long id) {
        return sysUserDao.deleteByPrimaryKey(id);
    }


}
