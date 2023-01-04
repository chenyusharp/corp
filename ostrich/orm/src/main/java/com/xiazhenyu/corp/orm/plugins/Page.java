package com.xiazhenyu.corp.orm.plugins;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Date: 2022/12/28
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class Page<E> extends ArrayList<E> implements Closeable {


    private static final long serialVersionUID = -4072502748121733617L;


    private final String stackTrace = StackTraceUtil.current();


    private int pageNum;

    private int pageSize;

    private long startRow;

    private long endRow;

    private long total;

    private int pages;

    private boolean count = true;

    private Boolean reasonable;

    private Boolean pageSizeZero;

    private String countColumn;

    private String orderBy;

    private boolean orderByOnly;


    private BoundSqlInterceptor boundSqlInterceptor;


    private transient BoundSqlInterceptor.Chain chain;


    private String dialectClass;

    private Boolean keepOrderBy;


    private Boolean keepSubSelectOrderBy;


    public Page() {
        super();
    }


    public Page(int pageNum, int pageSize) {
        this(pageNum, pageSize, true, null);
    }

    public Page(int pageNum, int pageSize, boolean count) {
        this(pageNum, pageSize, count, null);
    }


    public Page(int pageNum, int pageSize, boolean count, Boolean reasonable) {
        super(0);
        if (pageNum == 1 && pageSize == Integer.MAX_VALUE) {
            pageSizeZero = true;
            pageSize = 0;
        }
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.count = count;
        calculateStartAndEndRow();
        setReasonable(reasonable);
    }

    public Page(int[] rowBounds, boolean count) {
        super(0);
        if (rowBounds[0] == 0 && rowBounds[1] == Integer.MAX_VALUE) {
            pageSizeZero = true;
            this.pageSize = 0;
            this.pageNum = 1;
        } else {
            this.pageSize = rowBounds[1];
            this.pageNum = rowBounds[1] != 0 ? (int) (Math.ceil(((double) rowBounds[0] + rowBounds[1]) / rowBounds[1])) : 0;
        }
        this.startRow = rowBounds[0];
        this.count = count;
        this.endRow = this.startRow + rowBounds[1];
    }


    public String getStackTrace() {
        return stackTrace;
    }


    public List<E> getResult() {
        return this;
    }

    public int getPages() {
        return pages;
    }

    public Page<E> setPages(int pages) {
        this.pages = pages;
        return this;
    }

    public long getEndRow() {
        return endRow;
    }

    public int getPageNum() {
        return pageNum;
    }

    public Page<E> setPageNum(int pageNum) {
        this.pageNum = ((reasonable != null && reasonable) && pageNum <= 0) ? 1 : pageNum;
        return this;
    }


    public int getPageSize() {
        return pageSize;
    }

    public Page<E> setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }


    public long getStartRow() {
        return startRow;
    }

    public Page<E> setStartRow(long startRow) {
        this.startRow = startRow;
        return this;
    }


    public void setTotal(long total) {
        this.total = total;
        if (total == -1) {
            pages = 1;
            return;
        }
        if (pageSize > 0) {
            pages = (int) (total / pageSize + ((total % pageSize == 0) ? 0 : 1));
        } else {
            pages = 0;
        }
        if ((reasonable != null && reasonable) && pageNum > pages) {
            if (pages != 0) {
                pageNum = pages;
            }
            calculateStartAndEndRow();
        }
    }


    public Boolean getReasonable() {
        return reasonable;
    }

    private Page<E> setReasonable(Boolean reasonable) {

        if (reasonable == null) {
            return this;
        }
        this.reasonable = reasonable;
        if (this.reasonable && this.pageNum <= 0) {
            this.pageNum = 1;
            calculateStartAndEndRow();
        }
        return this;
    }

    public Boolean getPageSizeZero() {
        return pageSizeZero;
    }


    public Page<E> setPageSizeZero(Boolean pageSizeZero) {
        if (this.pageSizeZero == null && pageSizeZero != null) {
            this.pageSizeZero = pageSizeZero;
        }
        return this;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public <E> Page<E> setOrderBy(String orderBy) {
        this.orderBy = orderBy;
        return (Page<E>) this;
    }

    public boolean isOrderByOnly() {
        return orderByOnly;
    }


    public void setOrderByOnly(boolean orderByOnly) {
        this.orderByOnly = orderByOnly;
    }


    public String getDialectClass() {
        return dialectClass;
    }

    public void setDialectClass(String dialectClass) {
        this.dialectClass = dialectClass;
    }


    public Boolean getKeepOrderBy() {
        return keepOrderBy;
    }

    public Page<E> setKeepOrderBy(Boolean keepOrderBy) {
        this.keepOrderBy = keepOrderBy;
        return this;
    }

    public Boolean getKeepSubSelectOrderBy() {
        return keepSubSelectOrderBy;
    }


    public Page<E> using(String dialect) {
        this.dialectClass = dialect;
        return this;
    }


    public boolean isCount() {
        return count;
    }

    public Page<E> setCount(boolean count) {
        this.count = count;
        return this;
    }


    public Page<E> pageNum(int pageNum) {
        this.pageNum = ((reasonable != null && reasonable) && pageNum <= 0) ? 1 : pageNum;
        return this;
    }


    public Page<E> pageSize(int pageSize) {
        this.pageSize = pageSize;
        calculateStartAndEndRow();
        return this;
    }


    public Page<E> count(Boolean count) {
        this.count = count;
        return this;
    }

    public Page<E> reasonable(Boolean reasonable) {
        setReasonable(reasonable);
        return this;
    }


    public Page<E> pageSizeZero(Boolean pageSizeZero) {
        setPageSizeZero(pageSizeZero);
        return this;
    }


    public BoundSqlInterceptor getBoundSqlInterceptor() {
        return boundSqlInterceptor;
    }

    public void setBoundSqlInterceptor(BoundSqlInterceptor boundSqlInterceptor) {
        this.boundSqlInterceptor = boundSqlInterceptor;
    }

    public void setKeepSubSelectOrderBy(Boolean keepSubSelectOrderBy) {
        this.keepSubSelectOrderBy = keepSubSelectOrderBy;
    }

    private void calculateStartAndEndRow() {
        this.startRow = this.startRow > 0 ? (this.pageNum - 1) * this.pageSize : 0;
        this.endRow = this.startRow + this.pageSize * (this.pageNum > 0 ? 1 : 0);
    }

    @Override
    public void close() throws IOException {

    }


    public interface Function<E, T> {

        T apply(E t);
    }


}