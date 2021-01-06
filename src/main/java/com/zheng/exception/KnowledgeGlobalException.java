package com.zheng.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: ZhengTianLiang
 * @date: 2021/1/6  16:11
 * @desc:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KnowledgeGlobalException{

    private Integer code;

    private String message;
}
