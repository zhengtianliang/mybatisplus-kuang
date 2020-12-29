package com.zheng.controller;

import com.zheng.pojo.Node;
import com.zheng.service.NodeService;
import com.zheng.vo.NodeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: ZhengTianLiang
 * @date: 2020/12/29 0029  21:32
 * @desc:
 */

@RestController
@RequestMapping("/node")
public class NodeController {

    @Autowired
    private NodeService nodeService;

    @RequestMapping(value = "/list")
    public List<Node> queryNodeList(){
        List<Node> list = nodeService.list(null);
        return list;
    }
}
