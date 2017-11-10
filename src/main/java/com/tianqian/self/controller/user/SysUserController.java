package com.tianqian.self.controller.user;

import com.tianqian.self.common.BaseCodeEnum;
import com.tianqian.self.common.BaseResult;
import com.tianqian.self.common.LocalBindingErrorUtil;
import com.tianqian.self.model.entity.user.sysUser;
import com.tianqian.self.service.user.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@Api(tags = "用户管理")
public class SysUserController {
    private Logger logger = LoggerFactory.getLogger(SysUserController.class);

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private JavaMailSender mailSender;

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

    /**
     * 根据主键获取用户信息
     * @param userId
     * @return
     */
    @ApiOperation(value = "根据主键获取用户信息")
    @ApiImplicitParam(name = "userId", value = "用户主键", required = true, dataType = "int",paramType = "path")
    @GetMapping("/get/{userId}")
    public BaseResult<sysUser> selectByPrimaryKey(@Valid @PathVariable("userId") Integer userId){
        return new BaseResult<sysUser>(sysUserService.selectByPrimaryKey(userId.longValue()));
    }


    /**
     * 测试
     * @return
     */
    @ApiOperation(value = "")
    @GetMapping("/test")
    public BaseResult<String> test(){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("system@feicuilicai.com");
        message.setTo("zhangjinxing@feicuilicai.com");
        message.setSubject("主题：简单邮件");
        message.setText("测试邮件内容");
        mailSender.send(message);

        return new BaseResult<String>();
    }




}
