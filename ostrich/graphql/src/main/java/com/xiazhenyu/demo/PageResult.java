package com.xiazhenyu.demo;

import java.util.List;
import lombok.Data;

/**
 * Date: 2023/12/15
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
//@Data
public class PageResult<T> {

    private int current;
    private int pages;

    private List<T> records;

    private boolean searchCount;

    private int size;

    private int total;

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

    public boolean isSearchCount() {
        return searchCount;
    }

    public void setSearchCount(boolean searchCount) {
        this.searchCount = searchCount;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}