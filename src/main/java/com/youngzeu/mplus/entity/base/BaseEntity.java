package com.youngzeu.mplus.entity.base;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BaseEntity extends Model<BaseEntity> {
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
    @TableField(value = "INSERT_TIME",  fill = FieldFill.INSERT)
    protected LocalDateTime insertTime;

    /**
     * 修改时间
     */
    @JsonIgnore
    @TableField(value = "UPDATE_TIME",  fill = FieldFill.UPDATE)
    protected LocalDateTime updateTime;

    /**
     * 创建人
     */
    @JsonIgnore
    @TableField(value = "INSERT_USER",  fill = FieldFill.INSERT)
    protected String insertUser;

    /**
     * 修改人
     */
    @JsonIgnore
    @TableField(value = "UPDATE_USER",  fill = FieldFill.UPDATE)
    protected String updateUser;

    /**
     * 是否删除 : 0 未删除,1 已删除
     */
    @TableLogic
    @JsonIgnore
    protected Integer isDelete;
}
