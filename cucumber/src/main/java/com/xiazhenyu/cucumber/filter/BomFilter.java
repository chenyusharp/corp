package com.xiazhenyu.cucumber.filter;

import java.io.Serializable;

/**
 * Date: 2022/10/31
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public interface BomFilter extends Serializable {


    boolean contains(String str);


    boolean add(String str);


}
