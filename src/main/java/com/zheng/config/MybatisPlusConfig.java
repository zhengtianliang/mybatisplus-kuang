package com.zheng.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author: ZhengTianLiang
 * @date: 2020/11/17 0017  22:17
 * @desc: 这是测试乐观锁的时候用的
 */

@Component
@EnableTransactionManagement // 这个是默认开启的，可以不写
@MapperScan(value = "com.zheng.mapper") // 这个本来是写在启动类上的，我给挪到这个config里面了
// 注意，要想使用mybatisplus，这个mapperScan必不可少
public class MybatisPlusConfig {

    // 返回一个乐观锁组件
    @Bean
    public OptimisticLockerInterceptor getOptimisticLockerInnerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
        // paginationInterceptor.setOverflow(false);
        // 设置最大单页限制数量，默认 500 条，-1 不受限制
        // paginationInterceptor.setLimit(500);
        // 开启 count 的 join 优化,只针对部分 left join
//        paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
        return paginationInterceptor;
    }


    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }

    /**
     * 配置慢sql用的 bean对象
     */
    @Bean
    @Profile({"dev", "test"}) // 设置  是dev、test环境下才会开启，保证线上环境的效率
    public PerformanceInterceptor getPerformanceIntercepter() {
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        performanceInterceptor.setMaxTime(10000); // 设置sql执行的最大时间，超过了这个时间就会报错，单位是毫秒
        performanceInterceptor.setFormat(true); // 设置sql的格式化

        return performanceInterceptor;
    }


}
