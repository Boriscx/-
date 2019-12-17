package com.cy.pj.sys.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class JsonResult<T> implements Serializable {


    private T data;
    private Integer state = 1;
    private String message = "ok";

    public JsonResult( Integer state, String message) {
        this.state = state;
        this.message = message;
    }

    public JsonResult(T data) {
        this.data = data;
    }


    public JsonResult(Throwable throwable) {
        this.state = 500;
        this.message = throwable.getMessage();
    }

    public JsonResult(String message) {
        this.message = message;
    }

}
