package com.tianqian.self.model.dto.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

@Data
public class LoginDto implements Serializable{

    @ApiModelProperty(value="登录ID",required=true)
    @NotEmpty(message="notEmpty")
    private String loginId;

    @ApiModelProperty(value="用户密码",required=true)
    @NotEmpty(message="notEmpty")
    private String password;


}
