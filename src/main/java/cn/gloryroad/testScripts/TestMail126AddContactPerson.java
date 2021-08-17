package cn.gloryroad.testScripts;

import cn.gloryroad.appMoudles.AddContactPersonAction;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Zhang Huijuan
 * @Date: 2021/8/11 22:12
 */
public class TestMail126AddContactPerson {
    public WebDriver webDriver;
    @BeforeTest
    public void beforeMethod() {
        //设置驱动地址
        System.setProperty("webdriver.gecko.driver", "D:\\Program Files\\geckodriver\\geckodriver.exe");
        //设置打开浏览器的地址
        System.setProperty("webdriver.firefox.bin", "D:\\Program Files\\Mozilla Firefox\\firefox.exe");
        webDriver = new FirefoxDriver();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
    @Test
    public void testMail126AddContactPerson() throws Exception {
        AddContactPersonAction.createContact(webDriver,"jamie_zhj",
                "wo8482125","fu","524534403@qq.com","1834893690");
        Thread.sleep(3000);
        Assert.assertTrue(webDriver.getPageSource().contains("fu"));
        Assert.assertTrue(webDriver.getPageSource().contains("524534403@qq.com"));
        Assert.assertTrue(webDriver.getPageSource().contains("1834893690"));
    }

    @AfterMethod
    public void afterMethod() {
        webDriver.quit();//关闭浏览器
    }
}
