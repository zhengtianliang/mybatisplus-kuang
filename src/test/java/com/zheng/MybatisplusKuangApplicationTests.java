package com.zheng;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.zheng.mapper.UserMapper;
import com.zheng.pojo.User;
import com.zheng.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class MybatisplusKuangApplicationTests {

    // 注入
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
    }

    @Test
    public void test01() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    @Test
    public void test02() {
        User user = new User();
        user.setName("你好");
        user.setAge(1231231);
        user.setId(7l);

        //userService.save(user);
        //userService.removeById(5);
        userService.updateById(user);
    }

    // 测试乐观锁。成功案例
    @Test
    public void test03() {
        User user = userMapper.selectById(7l);
        // SELECT id,name,age,email,reversion,create_time,modify_time FROM user WHERE id=?
        user.setName("lsdjd");

        //UPDATE user SET name=?, age=?, reversion=?, modify_time=? WHERE id=? AND reversion=?
        userMapper.updateById(user);
    }


    // 测试分页
    @Test
    public void test04() {
        Page<User> page = new Page<>(1, 5);
        userMapper.selectPage(page, null);

        page.getRecords().forEach(System.out::println);
        System.out.println("总条数为：" + page.getTotal());

    }

    // 测试删除,物理删除
    @Test
    public void test05() {
        // 通过id删除
        //int i = userMapper.deleteById("9"); // DELETE FROM user WHERE id=?
        // boolean a = userService.removeById("10");  // DELETE FROM user WHERE id=?

        // 通过id批量删除
        // int i = userMapper.deleteBatchIds(Arrays.asList(8,10)); //  DELETE FROM user WHERE id IN ( ? , ? )

        // 通过条件删除
        Map<String, Object> map = new HashMap<>();
        map.put("name", "Tom");
        int i = userMapper.deleteByMap(map); //  DELETE FROM user WHERE name = ?

        System.out.println(i);
        //System.out.println(a);

    }


    @Test
    public void testGenerateCode() {
        // 需要构建一个代码生成器对象
        AutoGenerator autoGenerator = new AutoGenerator();

        // 配置策略
        // 1、全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        System.out.println("项目的路径是：" + projectPath);
        gc.setOutputDir(projectPath + "src/main/java"); // 生成的代码的存放路径
        gc.setAuthor("ZhengTianLiang_mybatis_plus_auto"); // 设置作者
        gc.setOpen(false); // 是否自动打开文件夹
        gc.setFileOverride(false); // 是否覆盖
        gc.setServiceName("%sService"); // 去掉Service的I前缀
        gc.setIdType(IdType.ID_WORKER); // 设置主键策略
        gc.setDateType(DateType.ONLY_DATE); // 设置日期类型，(仅仅只是时间)
        gc.setSwagger2(true); // 是否开启swagger的注解

        autoGenerator.setGlobalConfig(gc); // 将全局配置set进代码生成器对象中去

        // 2、设置数据源
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/mybatis_kuang?serverTimezone=UTC&useUnicode=true&zeroDateTimeBehavior=convertToNull&autoReconnect=true&characterEncoding=utf-8");
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("123456");
        dataSourceConfig.setDbType(DbType.MYSQL); // 设置数据源 mysql数据库

        autoGenerator.setDataSource(dataSourceConfig); // 将数据源set进代码生成器对象中去

        // 3、包的位置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setModuleName("blog"); // 生成的代码都在这个包下面
        packageConfig.setParent("com.zheng"); // 设置包名
        packageConfig.setEntity("entity"); // 设置实体类的包的名
        packageConfig.setMapper("com"); // 设置mapper的名
        packageConfig.setService("service"); // 设置service
        packageConfig.setController("controller"); // 设置controller

        autoGenerator.setPackageInfo(packageConfig); // 将包的设置set进代码生成器对象中去

        // 策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setInclude("clas"); // 设置要生成的表名，可以多个，只生成这个对应的表
        strategyConfig.setNaming(NamingStrategy.underline_to_camel); // 包的命名规则(下划线变驼峰)
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel); // 列的命名规则(下划线变驼峰)
        // strategyConfig.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
        strategyConfig.setEntityLombokModel(true); // 实体上是否用lombok注解来简化代码
        strategyConfig.setRestControllerStyle(true); // 开启restful的驼峰命名格式

        // 设置逻辑删除的字段
        strategyConfig.setLogicDeleteFieldName("deleted");
        // 乐观锁的配置
        strategyConfig.setVersionFieldName("version");
        // 设置url的一个风格，可以不设置     localhost:8080/hello_id_2  比较清晰一些
        strategyConfig.setControllerMappingHyphenStyle(true);

        // 自动填充的配置
        TableFill createTime = new TableFill("create_time", FieldFill.INSERT);// 创建时间，创建的时候自动填充
        TableFill modifyTime = new TableFill("modify_time", FieldFill.INSERT_UPDATE); // 修改时间，创建和更新的时候自动填充

        // 将上面的自动填充  set进策略配置中
        List<TableFill> tableFills = new ArrayList<>();
        tableFills.add(createTime);
        tableFills.add(modifyTime);
        strategyConfig.setTableFillList(tableFills);

        autoGenerator.setStrategy(strategyConfig);

        // 执行
        autoGenerator.execute();

    }

}
