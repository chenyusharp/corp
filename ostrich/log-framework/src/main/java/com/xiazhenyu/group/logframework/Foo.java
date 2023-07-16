package com.xiazhenyu.group.logframework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Date: 2023/2/5
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class Foo {

    private static final Logger logger = LoggerFactory.getLogger(Foo.class);

    public void doIt() {
        logger.debug("Did it again!");
    }


}