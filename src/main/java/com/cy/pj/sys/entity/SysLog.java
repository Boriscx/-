package com.cy.pj.sys.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 日志持久化对象
 * 建议:java中所有用于封装数据的对象都实现序列化接口,便于后续的扩展
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysLog extends AbstractObject {

    public static final String TABLE_NAME = "sys_logs";

    /**
     * 序列化id,对象序列化时的唯一表示,
     * 不定义此id对象序列化id时,也会自动生成一个id(这个id的生成是基于类的结构信息自动生成),
     * 与对象对应的字节存储在一起.
     * 但是假如类的机构在反序列化的时候已经发生变化,不能保证原有字节能够正常反序列化
     */
    private static final long serialVersionUID = -5525671327284104526L;

    public static final String USERNAME = "username";
    private String username;
    private String operation;
    private String method;
    private String params;
    private Long time;
    private String ip;

    @Override
    public String getTableName() {
        return null;
    }
}
