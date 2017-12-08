package com.tianqian.self.common.base;

import io.swagger.annotations.ApiModelProperty;

public class BasePage {

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
}
