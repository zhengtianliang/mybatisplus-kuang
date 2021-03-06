package com.zheng.vo;

import com.zheng.pojo.Node;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: ZhengTianLiang
 * @date: 2020/12/29  21:31
 * @desc: 返回实体，前段展示用
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NodeVo {
    List<Node> nodeList;
}
