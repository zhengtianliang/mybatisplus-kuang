package com.zheng.controller;

import com.zheng.dto.NodeDTO;
import com.zheng.exception.AuthorityException;
import com.zheng.exception.LoginException;
import com.zheng.mapper.NodeMapper;
import com.zheng.pojo.KnowledgeNodeRelation;
import com.zheng.pojo.Node;
import com.zheng.service.NodeService;
import com.zheng.util.BeanConvertUtils;
import com.zheng.vo.DoronQuestionAnswerVO;
import com.zheng.vo.NodeTestVO;
import com.zheng.vo.NodeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private NodeMapper nodeMapper;


    /**
     * 测试异常的接口
     */
    @GetMapping(value = "/testException/{id}")
    public void testException(@PathVariable(value = "id") String id) {
        if ("1".equals(id)) {
            throw new AuthorityException();
        } else if ("2".equals(id)) {
            throw new LoginException();
        } else if ("3".equals(id)) {
            throw new NullPointerException();
        } else if ("4".equals(id)) {
            throw new IndexOutOfBoundsException();
        }

    }


    /**
     * 菜单栏的接口
     *
     * @return
     */
    @RequestMapping(value = "/list")
    public NodeVo queryNodeList() {
        List<Node> list = nodeService.list(null);

        NodeVo nodeVo = new NodeVo();
        List<Node> result = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            List<Node> result1 = new ArrayList<>();
            Node node = list.get(i);

            if (node.getPid() == 0) {
                result.add(node);
            }
            if (node.getLavel() == 2) {
                result1.add(node);
                Integer pid = node.getPid();
                list.stream().forEach((e) -> {
                    if (e.getId() == pid) {
                        if (CollectionUtils.isEmpty(e.getChildNodeList())) {
                            e.setChildNodeList(result1);
                        } else {
                            List<Node> childNodeList = e.getChildNodeList();
                            childNodeList.addAll(result1);
                        }
                    }
                });
               /* for (int j=0;j<list.size();j++){
                    Node node1 = list.get(j);
                    if (node1.getId() == pid){
                       if (CollectionUtils.isEmpty(node1.getChildNodeList())){
                           node1.setChildNodeList(result1);
                       }else {
                           List<Node> childNodeList = node1.getChildNodeList();
                           childNodeList.addAll(result1);
                       }
                        break;
                    }
                }*/
            }
        }

        nodeVo.setNodeList(result);
        return nodeVo;
    }

    /**
     * 测试stream流
     */
    @RequestMapping("/testStream")
    public void testStream() {
        List<Node> list = nodeService.list(null);

        List<Node> result1 = new ArrayList<>();
        Integer pid = 2;

        // foreach只是遍历，里面该几个元素还是几个元素，只不过可能是对元素做了一些操作
        /*list.stream().forEach((e) -> {
            if (e.getId() == pid){
                e.setChildNodeList(result1);
            }
        });*/

        // filter能过滤出来，符合条件的数据，但是要记得，重新赋值给该对象。
        list = list.stream().filter(s -> s.getPid() == 2).collect(Collectors.toList());

        System.out.printf("123");
    }


    /**
     * 多伦知识的测试
     */
    @RequestMapping("/doron")
    public DoronQuestionAnswerVO testDoron() {
        // 查出属于这个知识的全部节点列表数据
        List<KnowledgeNodeRelation> list = nodeService.testDoron();
        // 组合
        // 拿到开始的节点
        list = list.stream().filter(s -> s.getStart() == 1).collect(Collectors.toList());
        if (list.size() != 1) {
            throw new RuntimeException("节点关系数据有问题，有多个开始节点");
        }
        // 将根节点，set进返回结果中
        DoronQuestionAnswerVO result = BeanConvertUtils.copyProperties(list.get(0), DoronQuestionAnswerVO.class);
        // 查询节点表，拿到根节点的内容，类型
        NodeDTO nodeDTO = nodeService.findNodeContentAndTypeById(list.get(0).getNodeId());
        if (nodeDTO == null) {
            throw new RuntimeException("节点id不正确");
        }
        result.setNodeContent(nodeDTO.getNodeContent());
        result.setNodeType(nodeDTO.getNodeType());
        // 拿到下一个节点


        for (int i = 0; i < list.size(); i++) {
            list = list.stream().filter(s -> s.getStart() == 1).collect(Collectors.toList());
            if (list.size() != 1) {
                throw new RuntimeException("节点关系数据有问题，有多个开始节点");
            }
            // 将根节点，set进返回结果中
            DoronQuestionAnswerVO result1 = BeanConvertUtils.copyProperties(list.get(0), DoronQuestionAnswerVO.class);
            for (int j = 0; j < list.size(); j++) {
                DoronQuestionAnswerVO doronQuestionAnswerVO = new DoronQuestionAnswerVO();
                List<DoronQuestionAnswerVO> list1 = new ArrayList<>();
                String nextNodeId = list.get(0).getNextNodeId(); // 下一个节点
                if (nextNodeId.contains(",")) { // 多个子节点
                    List<String> ids = Arrays.asList(nextNodeId.split(","));
                    ids.stream().forEach(s -> {
                        KnowledgeNodeRelation knowledgeNodeById = nodeMapper.findKnowledgeNodeById(Integer.valueOf(nextNodeId));
                        DoronQuestionAnswerVO doronQuestionAnswerVO1 = BeanConvertUtils.copyProperties(knowledgeNodeById, DoronQuestionAnswerVO.class);
                        list1.add(doronQuestionAnswerVO1);
                    });
                } else { // 一个子节点
                    // 根据下一个子节点的id，查出数据
                    KnowledgeNodeRelation knowledgeNodeById = nodeMapper.findKnowledgeNodeById(Integer.valueOf(nextNodeId));
                    DoronQuestionAnswerVO doronQuestionAnswerVO1 = BeanConvertUtils.copyProperties(knowledgeNodeById, DoronQuestionAnswerVO.class);
                    list1.add(doronQuestionAnswerVO1);
                }
                result1.setNextNodeList(list1);
            }
        }


        System.out.printf("123");
        return null;
    }

    public List<DoronQuestionAnswerVO> method(KnowledgeNodeRelation knowledgeNodeRelation) {
        List<DoronQuestionAnswerVO> result = new ArrayList<>();
        if (knowledgeNodeRelation.getEnd() == 1) {
            return result;
        }
        if (knowledgeNodeRelation.getNextNodeId().contains(",")) {
        }
        return null;
    }


    /**
     * 流程图，先拿到全部的id
     */
    @RequestMapping("/testNode")
    public void testNode() {
        NodeTestVO nodeTestVO = new NodeTestVO();
        List<KnowledgeNodeRelation> list = nodeService.testDoron();
        // 组合
        // 拿到开始的节点
        list = list.stream().filter(s -> s.getStart() == 1).collect(Collectors.toList());
        Integer nodeId = list.get(0).getNodeId();
        // 根节点
        nodeTestVO.setId(nodeId);

        String nextIds = nodeMapper.getNextIdsByNodeId(nodeId);
        List<String> ids = new ArrayList<>();
        if (nextIds.contains(",")) {
            ids = Arrays.asList(nextIds.split(","));
        } else {
            ids.add(nextIds);
        }

    }

    /*public NodeTestVO test2(List<String> ids) {
        for (int i = 0; i < ids.size(); i++) {
            if (nodeMapper.getNextIdsByNodeId(Integer.valueOf(ids.get(i))) == null) {
                return ids.get(i);
            } else {
                String nextIdsByNodeId = nodeMapper.getNextIdsByNodeId(Integer.valueOf(ids.get(i)));
            }
        }
        NodeTestVO result = new NodeTestVO();
        if (nodeMapper.getNextIdsByNodeId(Integer.valueOf(ids.get(i))) == null)


    }*/


}
