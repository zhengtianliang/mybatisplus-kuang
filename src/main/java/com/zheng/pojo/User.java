package com.zheng.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author: ZhengTianLiang
 * @date: 2020/11/14  22:59
 * @desc: 用户表
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    private String name;
    private Integer age;
    private String email;

    // 乐观锁
    @Version
    private Integer reversion;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime modifyTime;

    // 状态，0无效，1有效
    @TableLogic
    private Integer status;
}
