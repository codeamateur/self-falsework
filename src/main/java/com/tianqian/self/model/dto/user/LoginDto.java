package com.tianqian.self.model.dto.user;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

public class LoginDto {

    @ApiModelProperty(value="登录ID",required=true)
    @NotEmpty(message="notEmpty")
    private String loginId;

    @ApiModelProperty(value="用户密码",required=true)
    @NotEmpty(message="notEmpty")
    private String password;

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
