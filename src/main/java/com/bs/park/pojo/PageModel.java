package com.bs.park.pojo;

import java.util.List;

/**
 * Created by 林强 on 2016-12-28.
 */
public class PageModel {
    private int pageSize;
    private int pageModel;
    private int total;
    private List<Class<?>> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Class<?>> getRows() {
        return rows;
    }

    public void setRows(List<Class<?>> rows) {
        this.rows = rows;
    }
}
