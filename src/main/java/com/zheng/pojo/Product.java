package com.zheng.pojo;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: ZhengTianLiang
 * @date: 2020/12/2  20:42
 * @desc:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product extends Model<Product> {

    private String  id;
    private String name;
    private Integer age;
    private String email;

}
