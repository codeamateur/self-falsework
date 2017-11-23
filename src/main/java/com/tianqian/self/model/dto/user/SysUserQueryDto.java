package com.tianqian.self.model.dto.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SysUserQueryDto {

    @ApiModelProperty(value="登录名")
    private String loginName;

    @ApiModelProperty(value="手机号")
    private String mobile;

    @ApiModelProperty(value="页号")
    private Integer pageNum = 1;

    @ApiModelProperty(value="页码")
    private Integer pageSize= 10;


}
