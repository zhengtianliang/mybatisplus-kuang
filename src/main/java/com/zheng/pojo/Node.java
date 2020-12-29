package com.zheng.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: ZhengTianLiang
 * @date: 2020/12/29  21:27
 * @desc:
 */

@TableName("tb_node")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Node extends Model<Node> {
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
