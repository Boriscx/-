package com.cy.pj.sys.web;

import com.cy.pj.sys.pojo.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 被{@link RestControllerAdvice @RestControllerAdvice}注解了, 用于处理Controller抛出的异常
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * @param e 运行时异常
     * @return JsonResult
     */
    @ExceptionHandler(Exception.class)
    public JsonResult handleRuntimeException(Exception e) {
        JsonResult r = new JsonResult();
        r.setState(0);
        log.debug("controller抛出异常: ", e);
        if(e instanceof UnknownAccountException) {
            r.setMessage("账户不存在");
        }else if(e instanceof LockedAccountException) {
            r.setMessage("账户已被禁用");
        }else if(e instanceof IncorrectCredentialsException) {
            r.setMessage("密码不正确");
        }else if(e instanceof AuthorizationException) {
            r.setMessage("没有此操作权限");
        }else if (e instanceof RuntimeException){
            r.setMessage(e.getMessage());
        }else{
            r.setMessage("系统维护中");
        }

        return r;
    }

}
