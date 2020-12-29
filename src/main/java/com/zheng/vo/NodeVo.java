package com.zheng.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: ZhengTianLiang
 * @date: 2020/12/29  21:31
 * @desc: 返回实体，前段展示用
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NodeVo {
    private Integer id;
    private String name;
    private Integer pid;
    // 父级id的路径的集合
    private String parentIds;
    // 序号，同一行的第几个
    private Integer sequence;
    // 第几层
    private Integer lavel;
}
