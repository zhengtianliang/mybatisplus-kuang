package com.zheng.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: ZhengTianLiang
 * @date: 2020/12/31  11:06
 * @desc: 关系节点
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KnowledgeNode {

    private Integer id;
    // 节点内容
    private String nodeContent;
    // 节点类型
    private String nodeType;


}
