package com.zheng.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: ZhengTianLiang
 * @date: 2020/12/31  16:23
 * @desc:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NodeTestVO {

    private Integer id;
    private List<NodeTestVO> nextIds;

}
