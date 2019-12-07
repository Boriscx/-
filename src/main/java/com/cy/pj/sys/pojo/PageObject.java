package com.cy.pj.sys.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PageObject<T> implements Serializable {

    private static final long serialVersionUID = 1979652515199654316L;

    private Integer pageSize;   // 每页记录数 pageSize
    private Integer pageCurrent;  // 当前页码pageCurrent
    private Integer startIndex; // 查询开始位置
    private Integer rowCount;//记录总条数 rowCount
    private Integer pageCount;// 总页数   pageCount
    private List<T> records;   // 数据   records

    public PageObject() {
    }

    public PageObject(Integer pageSize, Integer pageCurrent, Integer startIndex, Integer rowCount, List<T> records) {
        this.pageSize = pageSize;
        this.pageCurrent = pageCurrent;
        this.startIndex = startIndex;
        this.startIndex = (pageCurrent - 1) * pageSize;
        this.rowCount = rowCount;
        this.pageCount = (rowCount + pageSize - 1) / pageSize;
        this.records = records;
    }
}
