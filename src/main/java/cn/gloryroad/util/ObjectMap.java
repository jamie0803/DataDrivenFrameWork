package cn.gloryroad.util;

import org.openqa.selenium.By;

import java.io.*;
import java.util.Properties;

/**
 * 用于实现在外部配置文件中配置页面元素的定位表达式
 *
 * @Author: Zhang Huijuan
 * @Date: 2021/8/9 22:00
 */
public class ObjectMap {
    Properties properties;

    public ObjectMap(String propFile) {
        properties = new Properties();
        try {
            Reader reader = new InputStreamReader(new FileInputStream(propFile), "utf-8");
            properties.load(reader);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            System.out.println("文件编码错误");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("找不到文件");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("读取对象文件出错");
        }
    }

    public By getLocator(String ElementNameInpropFile) throws Exception {
        //根据变量ElementNameInpropFile,从属性文件中读取对应的配置对象
        String locator = properties.getProperty(ElementNameInpropFile);
        //将配置对象中的定位类型存入LocatorType变量，将定位表达式的值存入locatorValue变量
        String locatorType = locator.split(">")[0];
        String locatorValue = locator.split(">")[1];
        //输出locatorType变量值和locatorValue变量值，验证是否赋值正确
        System.out.println("获取的定位类型：" + locatorType + "\t" + "获取的定位表达式：" + locatorValue);
        //根据locatorType的值确定返回的是何种定位方式的By对象
        if (locatorType.toLowerCase().equals("id")) {
            return By.id(locatorValue);
        } else if (locatorType.toLowerCase().equals("name")) {
            return By.name(locatorValue);
        } else if (locatorType.toLowerCase().equals("classname") || locatorType.toLowerCase().equals("class")) {
            return By.className(locatorValue);
        } else if (locatorType.toLowerCase().equals("tagname") || locatorType.toLowerCase().equals("tag")) {
            return By.tagName(locatorValue);
        } else if (locatorType.toLowerCase().equals("linktext") || locatorType.toLowerCase().equals("link")) {
            return By.linkText(locatorValue);
        } else if (locatorType.toLowerCase().equals("partiallinktext")) {
            return By.partialLinkText(locatorValue);
        } else if (locatorType.toLowerCase().equals("cssselector") || locatorType.toLowerCase().equals("css")) {
            return By.cssSelector(locatorValue);
        } else if (locatorType.toLowerCase().equals("xpath")) {
            return By.xpath(locatorValue);
        } else
            throw new Exception("输入的locator Type未在程序中定义：" + locatorType);
    }
}
