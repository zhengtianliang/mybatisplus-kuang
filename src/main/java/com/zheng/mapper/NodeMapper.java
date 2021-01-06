package com.zheng.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zheng.dto.NodeDTO;
import com.zheng.pojo.KnowledgeNodeRelation;
import com.zheng.pojo.Node;
import com.zheng.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author: ZhengTianLiang
 * @date: 2020/11/14  23:00
 * @desc: 节点的mapper文件
 */

public interface NodeMapper extends BaseMapper<Node> {

    //@Select(" select * from tb_multi_knowledge ")
    List<KnowledgeNodeRelation> testDoron();

    @Select("select node_content, node_type from tb_multi_knowledge_node where is_valid = 1 " +
            "and id = #{nodeId}")
    NodeDTO findNodeContentAndTypeById(@Param("nodeId") Integer nodeId);

    KnowledgeNodeRelation findKnowledgeNodeById(@Param("id") Integer id);

    @Select("select next_node_id from tb_multi_knowledge where node_id = #{id}")
    String getNextIdsByNodeId(@Param("id") Integer nodeId);
}
