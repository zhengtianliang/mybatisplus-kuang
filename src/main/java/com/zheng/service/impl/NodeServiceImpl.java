package com.zheng.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zheng.dto.NodeDTO;
import com.zheng.mapper.NodeMapper;
import com.zheng.mapper.ProductMapper;
import com.zheng.pojo.KnowledgeNodeRelation;
import com.zheng.pojo.Node;
import com.zheng.pojo.Product;
import com.zheng.service.NodeService;
import com.zheng.service.ProductService;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: ZhengTianLiang
 * @date: 2020/12/2  20:56
 * @desc:
 */

@Service
public class NodeServiceImpl extends ServiceImpl<NodeMapper, Node> implements NodeService {

    @Autowired
    private NodeMapper nodeMapper;

    @Override
    public List<KnowledgeNodeRelation> testDoron() {
        return nodeMapper.testDoron();
    }

    @Override
    public NodeDTO findNodeContentAndTypeById(Integer nodeId) {
        return nodeMapper.findNodeContentAndTypeById(nodeId);
    }
}
