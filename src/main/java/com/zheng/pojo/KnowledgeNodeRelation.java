package com.zheng.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: ZhengTianLiang
 * @date: 2020/12/31  11:08
 * @desc: 节点关系实体(也可以叫做多伦问答 答案实体)
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KnowledgeNodeRelation {

    // 节点关系的主键id
    private Integer id;
    // 节点id
    private Integer nodeId;
    // 下个节点id，多个id之间用，分割
    private String nextNodeId;
    // 是否是开始节点  1是，0否
    private Integer start;
    // 是否是结束节点  1是，0否
    private Integer end;

}
