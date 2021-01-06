package com.zheng.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: ZhengTianLiang
 * @date: 2021/1/6  16:13
 * @desc: 未登陆异常
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginException extends RuntimeException{

    private Integer code;

    private String message;
}
