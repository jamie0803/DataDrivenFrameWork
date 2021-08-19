package cn.gloryroad.testScripts;

import cn.gloryroad.appMoudles.LoginAction;
import cn.gloryroad.pageObjects.LoginPage;
import cn.gloryroad.util.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Zhang Huijuan
 * @Date: 2021/8/10 7:38
 */
public class TestMail126Login {
    public WebDriver webDriver;

    //    String baseUrl = "https://mail.126.com";
    @BeforeTest
    public void beforeMethod() {
        //设置驱动地址
        System.setProperty("webdriver.gecko.driver", "D:\\ForDevelopTest\\geckodriver.exe");
        //设置打开浏览器的地址
        System.setProperty("webdriver.firefox.bin", "D:\\Program Files\\Mozilla Firefox\\firefox.exe");
        webDriver = new FirefoxDriver();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void testMailLogin() throws Exception {
        /*webDriver.get(baseUrl);
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.switchToFrame();
        loginPage.username().sendKeys("jamie_zhj");
        loginPage.password().sendKeys("wo8482125");
        loginPage.loginButton().click();
        Thread.sleep(5000);
        loginPage.defaultToFrame();*/
        //直接调用loginAction封装好的登录方法
        LoginAction.execute(webDriver, "jamie_zhj", "wo8482125");
        Thread.sleep(5000);
        Assert.assertTrue(webDriver.getPageSource().contains("未读邮件"));
    }

    @AfterMethod
    public void afterMethod() {
        webDriver.quit();//关闭浏览器
    }
}
