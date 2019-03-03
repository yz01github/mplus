package com.youngzeu.mplus.entity.base;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;

public class BaseEntity {
	/**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    @TableId(type=IdType.AUTO)
    @JsonIgnore
    protected Long id;

    /**
     * 创建时间
     */

    @JsonIgnore
    protected LocalDateTime createTime;

    /**
     * 修改时间
     */
    @JsonIgnore
    protected LocalDateTime updateTime;

    /**
     * 创建人
     */
    @JsonIgnore
    protected String createUser;

    /**
     * 修改人
     */
    @JsonIgnore
    protected String updateUser;

    /**
     * 是否删除 : 0 未删除,1 已删除
     */
    @TableLogic
    @JsonIgnore
    protected Integer isDelete;
}
