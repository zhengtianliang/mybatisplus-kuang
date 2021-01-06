package com.zheng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zheng.dto.NodeDTO;
import com.zheng.pojo.KnowledgeNodeRelation;
import com.zheng.pojo.Node;
import com.zheng.pojo.User;

import java.util.List;

/**
 * @author: ZhengTianLiang
 * @date: 2020/11/14  21:15
 * @desc: 用户service
 */

public interface NodeService extends IService<Node> {
    List<KnowledgeNodeRelation> testDoron();

    NodeDTO findNodeContentAndTypeById(Integer nodeId);
}
