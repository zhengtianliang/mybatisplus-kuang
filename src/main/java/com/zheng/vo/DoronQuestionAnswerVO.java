package com.zheng.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: ZhengTianLiang
 * @date: 2020/12/31  11:00
 * @desc: 多伦问答的前端展示实体
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoronQuestionAnswerVO {

    // 节点关系的主键id
    private Integer id;
    // 节点id
    private Integer nodeId;
    // 节点内容
    private String nodeContent;
    // 节点类型
    private String nodeType;
    // 下个节点id，多个id之间用，分割
    private String nextNodeId;
    // 是否是开始节点  1是，0否
    private Integer start;
    // 是否是结束节点  1是，0否
    private Integer end;

    // 下个节点的集合
    @TableField(exist = false)
    List<DoronQuestionAnswerVO> nextNodeList;

}
