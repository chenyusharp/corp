package com.xiazhenyu.cucumber.filter;

/**
 * Date: 2022/10/31
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public interface BitMap {


    int MACHINE32 = 32;


    int MACHINE64 = 64;


    void add(long i);


    boolean contains(long i);


    void remove(long i);


}
