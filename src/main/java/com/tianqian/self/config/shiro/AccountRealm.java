package com.tianqian.self.config.shiro;

import com.tianqian.self.dao.permission.SysPermissionDao;
import com.tianqian.self.dao.role.SysRoleDao;
import com.tianqian.self.dao.user.SysUserDao;
import com.tianqian.self.model.entity.permission.SysPermission;
import com.tianqian.self.model.entity.role.SysRole;
import com.tianqian.self.model.entity.user.SysUser;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;



/**
 * @author zjx
 */
@Component
public class AccountRealm extends AuthorizingRealm {
	
	@Resource
	private SysUserDao sysUserDao;
	@Resource
	private SysRoleDao sysRoleDao;
	@Resource
	private SysPermissionDao sysPermissionDao;
	
	/**
	 * 认证回调函数,登录时调用
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		//获取用户信息实体
		SysUser user = sysUserDao.selectOneByLoginName(token.getUsername());
     	if (user == null){
     		throw new UnknownAccountException();
     	} 
        if(user.getStatus() !=null && user.getStatus()== false) {
            throw new LockedAccountException();
        }
		return new SimpleAuthenticationInfo(user,
				user.getPassword().toCharArray(),getName());
	}
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		SysUser shiroUser = (SysUser) principals.getPrimaryPrincipal();
		List<SysRole> roleList = sysRoleDao.selectRolesByUserId(shiroUser.getId());
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		List<String> roles = new ArrayList<String>();
		List<String> permissions = new ArrayList<String>();
		if(roleList != null && roleList.size()>0){
			for (SysRole role : roleList) {
				roles.add(role.getName());
				List<SysPermission> permissionList = sysPermissionDao.selectPermissionsByRoleId(role.getId());
				if(permissionList != null && permissionList.size()>0){
					for(SysPermission permission :permissionList){
						permissions.add(permission.getPermission());
					}
				}
			}			
		}
		//基于Role的权限信息
		info.addRoles(roles);
		//基于Permission的权限信息
		info.addStringPermissions(permissions);
		return info;
	}
}
