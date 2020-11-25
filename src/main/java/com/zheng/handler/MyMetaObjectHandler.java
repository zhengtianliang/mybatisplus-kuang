package com.zheng.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author: ZhengTianLiang
 * @date: 2020/11/17  21:58
 * @desc: 规定一下update的规则
 */

@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {

    // 插入填充时的策略
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insertFill ");
        this.setFieldValByName("createTime", LocalDateTime.now(),metaObject);
        this.setFieldValByName("modifyTime", LocalDateTime.now(),metaObject);
    }

    // 更新填充时的策略
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start updateFill ");
        this.setFieldValByName("modifyTime",LocalDateTime.now(),metaObject);
    }
}
