package com.tianqian.self.model.dto.user;

import io.swagger.annotations.ApiModelProperty;

public class SysUserQueryDto {

    @ApiModelProperty(value="登录名")
    private String loginName;

    @ApiModelProperty(value="手机号")
    private String mobile;

    @ApiModelProperty(value="页号")
    private Integer pageNum = 1;

    @ApiModelProperty(value="页码")
    private Integer pageSize= 10;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
