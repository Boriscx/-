package com.cy.pj.sys.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 日志持久化对象
 * 建议:java中所有用于封装数据的对象都实现序列化接口,便于后续的扩展
 */
@Data
public class SysLog implements Serializable {

    /**
     * 序列化id,对象序列化时的唯一表示,
     * 不定义此id对象序列化id时,也会自动生成一个id(这个id的生成是基于类的结构信息自动生成),
     * 与对象对应的字节存储在一起.
     * 但是假如类的机构在反序列化的时候已经发生变化,不能保证原有字节能够正常反序列化
     */
    private static final long serialVersionUID = -5525671327284104526L;


    //private static final Long serialVersionUID = null;

    private Integer id;
    private String username;
    private String operation;
    private String method;
    private String params;
    private Long time;
    private String ip;
    private Date createdTime;


}
