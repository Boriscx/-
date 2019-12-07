package com.cy.pj.sys.controller;

import com.cy.pj.sys.aspect.annotation.RequestLog;
import com.cy.pj.sys.pojo.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 被{@link RestControllerAdvice @RestControllerAdvice}注解了, 用于处理Controller抛出的异常
 */
@RestControllerAdvice
@Slf4j
public class ExceptionHandler {

    /**
     * @param ex 运行时异常
     * @return JsonResult
     */
    @RequestLog("发生了异常")
    @org.springframework.web.bind.annotation.ExceptionHandler(RuntimeException.class)
    public JsonResult handleRuntimeException(RuntimeException ex) {
        log.debug("controller抛出异常: ", ex);
        return new JsonResult(0, ex.getMessage());
    }

}
