package com.zheng.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zheng.mapper.ProductMapper;
import com.zheng.pojo.Product;
import com.zheng.service.ProductService;
import org.springframework.stereotype.Service;

/**
 * @author: ZhengTianLiang
 * @date: 2020/12/2  20:56
 * @desc:
 */

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
}
