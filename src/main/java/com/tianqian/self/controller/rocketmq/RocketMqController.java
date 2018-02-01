package com.tianqian.self.controller.rocketmq;

import com.qianmi.ms.starter.rocketmq.core.RocketMQTemplate;
import com.tianqian.self.common.base.BaseResult;
import com.tianqian.self.common.utils.LocalBindingErrorUtil;
import com.tianqian.self.model.dto.user.LoginDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/rocketmq")
@Api(tags = "消息队列")
public class RocketMqController {
    private final Logger logger = LoggerFactory.getLogger(RocketMqController.class);

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 发送消息
     * @param login
     * @param bindingResult
     * @return
     */
    @ApiOperation(value = "发送消息")
    @ApiImplicitParam(name = "login", value = "登录信息", required = true, dataType = "LoginDto")
    @PostMapping("/sendMessage")
    public BaseResult<String> sendMessage(@Valid @RequestBody LoginDto login, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
          return LocalBindingErrorUtil.handle(bindingResult,String.class);
        }
        rocketMQTemplate.convertAndSend("zjx:first-topic", login);
        return new BaseResult<String>();
    }

}
