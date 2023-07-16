package com.xiazhenyu.group.logframework;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Date: 2023/2/5
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class LogTest {


    private static final Logger rootLogger = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);


    public static void main(String[] args) {

        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        StatusPrinter.print(lc);

        rootLogger.info("entering application");

        Foo foo = new Foo();
        foo.doIt();

        rootLogger.info("Exiting application");


    }


}