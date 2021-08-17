package cn.gloryroad.util;

import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * @Author: Zhang Huijuan
 * @Date: 2021/8/15 11:17
 */
public class Log {
    public static Logger logger = Logger.getLogger(Log.class.getName());

    //定义测试用例开始执行的打印方法，在日志中打印测试用例开始执行的信息
    public static void startTestCase(String testCaseName){
        logger.info("-----------------"+testCaseName+"开始执行-----------------");
    }
    //定义测试用例执行完毕的打印方法，在日志中打印测试用例执行完毕的信息
    public static void endTestCase(String testCaseName){
        logger.info("-----------------"+testCaseName+"开始执行-----------------");
    }

    //定义打印 info 级别日志的方法
    public static void info(String message){
        Log.info(message);
        logger.info(message);
    }
    //定义打印 error 级别日志的方法
    public static void error(String message){
    }
    //定义打印 debug 级别日志的方法
    public static void debug(String message){
        Log.debug(message);
    }



}

