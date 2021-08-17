package cn.gloryroad.pageObjects;

import cn.gloryroad.util.ObjectMap;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

/**
 * @Author: Zhang Huijuan
 * @Date: 2021/8/10 21:20
 */
public class HomePage {
    private WebElement element;
    private ObjectMap objectMap = new ObjectMap("D:\\Program Files\\WorkSpace\\DataDrivenFrameWork\\objectMap.properties");

    private WebDriver driver;
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    //获取主页中的通讯录链接
    public WebElement addressLink() throws Exception {
        element = driver.findElement(objectMap.getLocator("126mail.homePage.addressbook"));
        return element;
    }

    //操作更多元素，后续补充

}
