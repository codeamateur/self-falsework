package com.tianqian.self.controller.user;


import com.tianqian.self.common.base.BaseCodeEnum;
import com.tianqian.self.common.base.BaseResult;
import com.tianqian.self.common.utils.SecurityUtil;
import com.tianqian.self.model.dto.user.LoginDto;
import com.tianqian.self.model.entity.user.SysUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(tags = "用户管理")
public class AccountController {

	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

	@ApiIgnore
	@GetMapping(value = "/login")
	public BaseResult<String> login(HttpServletRequest req, HttpServletResponse response) {
		response.setHeader("sessionstatus", "timeout");
		return new BaseResult<String>(false, BaseCodeEnum.FAILURE.getIndex(), "timeout");
	}

	@ApiOperation(value = "登录", notes = "")
	@ApiImplicitParam(name = "dto", value = "登录信息", required = true, dataType = "LoginDto")
	@PostMapping(value = "/login")
	public BaseResult<SysUser> login(@RequestBody LoginDto dto, HttpServletRequest req) {
		BaseResult<SysUser> result = new BaseResult<SysUser>();
		String error = null;
		try {
			UsernamePasswordToken token = new UsernamePasswordToken();
			token.setUsername(dto.getLoginId());
			token.setPassword(SecurityUtil.MD5AndSHA256(dto.getPassword()).toCharArray());
			SecurityUtils.getSubject().login(token);
		} catch (UnknownAccountException uae) {
			logger.error("weblogin UnknownAccount: " + uae.toString());
			error = "用户名/密码错误";
		} catch (IncorrectCredentialsException ice) {
			logger.error("weblogin IncorrectCredentials: " + ice.toString());
			error = "用户名/密码错误";
		} catch (LockedAccountException lke) {
			logger.error("weblogin account locked: " + lke.toString());
			error = "账户被锁定";
		} catch (Exception e) {
			logger.error("weblogin error: ", e);
			error = "其他错误：" + e;
		}
		if (error == null) {
			SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
			result.setObj(user);
		} else {
			result.setSuccess(false);
			result.setMsg(error);
		}
		return result;
	}

	/**
	 * 退出
	 * 
	 * @return
	 */
	@ApiOperation(value = "退出", notes = "")
	@GetMapping(value = "/logout")
	public BaseResult<String> logout() {
		SecurityUtils.getSubject().logout();
		return new BaseResult<String>();
	}

}
