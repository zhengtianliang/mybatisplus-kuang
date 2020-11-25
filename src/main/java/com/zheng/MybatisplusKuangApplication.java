package com.zheng;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: ZhengTianLiang
 * @date: 2020/11/14  22:57
 * @desc: 启动类
 */

@SpringBootApplication
@MapperScan(value = "com.zheng.mapper") // 注意，要想使用mybatisplus，这个mapperScan必不可少
public class MybatisplusKuangApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisplusKuangApplication.class, args);
    }

}
