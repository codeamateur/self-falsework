package com.tianqian.self.controller.user;

import com.tianqian.self.common.base.BaseCodeEnum;
import com.tianqian.self.common.base.BaseResult;
import com.tianqian.self.common.utils.LocalBindingErrorUtil;
import com.tianqian.self.model.dto.user.SysUserQueryDto;
import com.tianqian.self.model.entity.user.SysUser;
import com.tianqian.self.service.user.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
@Api(tags = "用户管理")
public class SysUserController {
    private Logger logger = LoggerFactory.getLogger(SysUserController.class);

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private RedissonClient redisson;

    /**
     * 新增用户
     * @param user
     * @param bindingResult
     * @return
     */
    @ApiOperation(value = "新增用户")
    @ApiImplicitParam(name = "user", value = "用户信息", required = true, dataType = "SysUser")
    @PostMapping("/add")
    public BaseResult<String> addUser(@Valid @RequestBody SysUser user, BindingResult bindingResult){
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
    public BaseResult<SysUser> selectByPrimaryKey(@Valid @PathVariable("userId") Integer userId){
        return new BaseResult<SysUser>(sysUserService.selectByPrimaryKey(userId.longValue()));
    }

    /**
     * 根据主键删除用户信息
     * @param userId
     * @return
     */
    @ApiOperation(value = "根据主键删除用户信息")
    @ApiImplicitParam(name = "userId", value = "用户主键", required = true, dataType = "int",paramType = "path")
    @GetMapping("/del/{userId}")
    public BaseResult<String> del(@Valid @PathVariable("userId") Integer userId){
        if (sysUserService.deleteUser(userId.longValue()) ==1){
            return new BaseResult<String>();
        }else {
            return new BaseResult<String>(false, BaseCodeEnum.FAILURE.getIndex(),"删除失败，请重试");
        }
    }

    /**
     * 分页用户
     * @param dto
     * @param bindingResult
     * @return
     */
    @ApiOperation(value = "分页用户")
    @ApiImplicitParam(name = "dto", value = "分页信息", required = true, dataType = "SysUserQueryDto")
    @PostMapping("/queryUser")
    public BaseResult<SysUser> queryUser(@Valid @RequestBody SysUserQueryDto dto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return LocalBindingErrorUtil.handle(bindingResult,SysUser.class);
        }
        return new BaseResult<SysUser>(sysUserService.getPageByCriteria(dto));
    }


    /**
     * 测试分布式锁
     * @return
     */
    @ApiOperation(value = "测试分布式锁")
    @GetMapping("/testLock")
    public BaseResult<String> testLock(){
        RLock lock = redisson.getFairLock("anyLock");
        try {
            lock.tryLock(3, 60, TimeUnit.SECONDS);
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return new BaseResult<String>();
    }








}
