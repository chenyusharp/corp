package com.xiazhenyu.corp.orm.plugins;


/**
 * Date: 2022/12/28
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public abstract class PageMethod {


    protected static final ThreadLocal<Page> LOCAL_PAGE = new ThreadLocal<Page>();


    protected static boolean DEFAULT_COUNT = true;


    protected static void setLocalPage(Page page) {
        LOCAL_PAGE.set(page);
    }


    public static <T> Page<T> getLocalPage() {
        return LOCAL_PAGE.get();
    }


}