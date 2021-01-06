package com.zheng.exception.handler;

import com.zheng.exception.AuthorityException;
import com.zheng.exception.KnowledgeGlobalException;
import com.zheng.exception.LoginException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author: ZhengTianLiang
 * @date: 2021/1/6  16:04
 * @desc: 统一异常处理类
 */


@RestControllerAdvice(value = "com.zheng.controller")
public class GlobalExceptionHandler {

    @ExceptionHandler(value = AuthorityException.class)
    public AuthorityException handlerException(AuthorityException e) {
        System.out.printf("进入的是  未授权的  异常");
        return new AuthorityException(001, "用户未登陆或登陆信息超时，请您重新登陆！");
    }

    @ExceptionHandler(value = LoginException.class)
    public LoginException handlerException(LoginException e) {
        System.out.printf("进入的是  未登录或已过期  异常");
        return new LoginException(002, "当前用户暂未授权，请您联系管理员！");
    }

    @ExceptionHandler(value = NullPointerException.class)
    public KnowledgeGlobalException handlerException(NullPointerException e){
        System.out.printf("进入的是  空指针  异常");
        return new KnowledgeGlobalException(003,"出现了空指针异常");
    }

    // 这个是全局的异常捕获
    @ExceptionHandler(value = Exception.class)
    public KnowledgeGlobalException handlerException(Exception e){
        System.out.printf("非上述异常的，都用它来处理");
        return new KnowledgeGlobalException(005,"进去了最终的Exception中");
    }

}
