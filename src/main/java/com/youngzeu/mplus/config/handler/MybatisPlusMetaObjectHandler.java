package com.youngzeu.mplus.config.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.Date;

// mybatisPlus 属性自动注入
@Slf4j
@Configuration
public class MybatisPlusMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.debug("insertFill begin...");
        //获取当前登录的用户
        setFieldValByName("insertUser", "DEFAULT_INSERT", metaObject);
        setFieldValByName("insertTime", LocalDateTime.now(), metaObject);
        setFieldValByName("isDelete", '0', metaObject);
        log.debug("insertFill end...");
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.debug("updateFill begin...");
        setFieldValByName("updateUser", "DEFAULT_UPDATE", metaObject);
        setFieldValByName("updateTime",LocalDateTime.now(), metaObject);
        log.debug("updateFill end...");
    }
}
