package com.zheng.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: ZhengTianLiang
 * @date: 2021/1/6  16:07
 * @desc: 未授权异常
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorityException extends RuntimeException{

    private Integer code;

    private String message;
}
