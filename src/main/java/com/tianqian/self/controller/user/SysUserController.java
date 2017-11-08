package com.tianqian.self.controller.user;

import com.tianqian.self.common.BaseCodeEnum;
import com.tianqian.self.common.BaseResult;
import com.tianqian.self.common.LocalBindingErrorUtil;
import com.tianqian.self.model.entity.user.sysUser;
import com.tianqian.self.service.user.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@Api(tags = "用户管理")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    /**
     * 新增用户
     * @param user
     * @param bindingResult
     * @return
     */
    @ApiOperation(value = "新增用户")
    @ApiImplicitParam(name = "user", value = "用户信息", required = true, dataType = "sysUser")
    @PostMapping("/add")
    public BaseResult<String> addUser(@Valid @RequestBody sysUser user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
          return LocalBindingErrorUtil.handle(bindingResult,String.class);
        }
        if (sysUserService.addUser(user) ==1){
            return new BaseResult<String>();
        }else {
            return new BaseResult<String>(false, BaseCodeEnum.FAILURE.getIndex(),"新增失败，请重试");
        }
    }




}
