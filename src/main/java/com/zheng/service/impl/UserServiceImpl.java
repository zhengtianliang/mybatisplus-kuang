package com.zheng.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zheng.mapper.UserMapper;
import com.zheng.pojo.User;
import com.zheng.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author: ZhengTianLiang
 * @date: 2020/11/17 0017  21:24
 * @desc:
 */

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
