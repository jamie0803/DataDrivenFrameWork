package cn.gloryroad.util;

import org.apache.poi.ss.formula.functions.T;

import java.net.URL;

/**
 * @Author: Zhang Huijuan
 * @Date: 2021/9/12 13:05
 * 如果objectMap.properties文件在根目录下（Java项目），直接在page页面创建Properties对象读取
 * 如果在resource文件夹下（maven项目），要设法获取文件路径
 */
public class Tools {
    public static String getFilePath(Class<?> className, String configNileName) {
        ClassLoader classLoader = className.getClassLoader();
        URL resource = classLoader.getResource(configNileName);
        String configPath = resource.getPath();
        return configPath;
    }
}
