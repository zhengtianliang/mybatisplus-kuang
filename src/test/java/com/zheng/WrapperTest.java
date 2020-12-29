package com.zheng;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zheng.mapper.UserMapper;
import com.zheng.pojo.User;
import com.zheng.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

/**
 * @author: ZhengTianLiang
 * @date: 2020/11/19  21:16
 * @desc:
 */

@SpringBootTest
public class WrapperTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    // 查询列表
    @Test
    public void test01() {
        // 查询name = Jone ，age 大于2的 email不为空的user对象
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.isNotNull("email")
                .eq("name", "Jone")
                .gt("age", 2);

        //userMapper.selectList(wrapper); // SELECT id,name,age,email,reversion,create_time,modify_time,status FROM user WHERE status=1 AND email IS NOT NULL AND name = ? AND age > ?
        userService.list(wrapper); // SELECT id,name,age,email,reversion,create_time,modify_time,status FROM user WHERE status=1 AND email IS NOT NULL AND name = ? AND age > ?
    }

    // 查询单个
    @Test
    public void test02() {
        // 查询名字是Jone 的对象，
        QueryWrapper<User> wrapper = new QueryWrapper();
        wrapper.eq("name", "Jone");

        // User one = userService.getOne(wrapper); // 即使结果集有多个，也不报错，但是只会展示第一个  SELECT id,name,age,email,reversion,create_time,modify_time,status FROM user WHERE status=1 AND name = ?
        User one = userMapper.selectOne(wrapper); // 结果集是多个的话，报错  SELECT id,name,age,email,reversion,create_time,modify_time,status FROM user WHERE status=1 AND name = ?
        System.out.println(one);
    }

    @Test
    public void test03() {
        // 查询年龄在20-30之间的用户
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.between("age", 10, 20);

        //Integer integer = userMapper.selectCount(wrapper); // SELECT COUNT(1) FROM user WHERE status=1 AND age BETWEEN ? AND ?
        int integer = userService.count(wrapper);  // SELECT COUNT(1) FROM user WHERE status=1 AND age BETWEEN ? AND ?
        System.out.println(integer);
    }

    // 模糊查询
    @Test
    public void test04() {
        // 查询名字中不带e的，并且email是以a开头的  用户
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.notLike("name", "e") // AND name NOT LIKE '%e%'
                .likeLeft("email", "a") // AND email LIKE '%a'
                .likeRight("age", "1"); // AND age LIKE '1%'

        //List<Map<String, Object>> maps = userMapper.selectMaps(wrapper);// SELECT id,name,age,email,reversion,create_time,modify_time,status FROM user WHERE status=1 AND name NOT LIKE ? AND email LIKE ? AND age LIKE ?
        Map<String, Object> maps = userService.getMap(wrapper); // SELECT id,name,age,email,reversion,create_time,modify_time,status FROM user WHERE status=1 AND name NOT LIKE ? AND email LIKE ? AND age LIKE ?

    }

    // 子查询
    @Test
    public void test05() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.inSql("id", "select id from user where id <3");

        // SELECT id,name,age,email,reversion,create_time,modify_time,status FROM user WHERE status=1 AND id IN (select id from user where id <3)
        //List<Object> objects = userMapper.selectObjs(wrapper); // 这个结果集是一个list   查到了2个user对象
        //objects.forEach(System.out::println);

        // SELECT id,name,age,email,reversion,create_time,modify_time,status FROM user WHERE status=1 AND id IN (select id from user where id <3)
        Object obj = userService.getObj(wrapper); // 这个结果集是一个对象，  查到了2个对象，但是只展示第一个对象
        System.out.println(obj);
    }

    // 排序
    @Test
    public void test06() {
        // 通过name、id降序排列
        QueryWrapper<User> wrapper = new QueryWrapper();
        wrapper.orderByDesc("name", "id");

        //List<User> users = userMapper.selectList(wrapper); // SELECT id,name,age,email,reversion,create_time,modify_time,status FROM user WHERE status=1 ORDER BY name DESC , id DESC
        List<User> users = userService.list(wrapper); // SELECT id,name,age,email,reversion,create_time,modify_time,status FROM user WHERE status=1 ORDER BY name DESC , id DESC
        System.out.println(users);
    }


}
