package com.zheng.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.ref.PhantomReference;

/**
 * @author: ZhengTianLiang
 * @date: 2020/12/31  15:25
 * @desc:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NodeDTO {

    private String nodeContent;
    private String nodeType;
}
