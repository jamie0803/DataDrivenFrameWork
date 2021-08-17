package cn.gloryroad.testScripts;

import cn.gloryroad.appMoudles.AddContactPersonAction;
import cn.gloryroad.util.Constant;
import cn.gloryroad.util.ExcelUtil;
import cn.gloryroad.util.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * @Author: Zhang Huijuan
 * @Date: 2021/8/11 22:12
 */
public class TestMail126AddContactPerson {
    public WebDriver webDriver;

    private static Logger log = Logger.getLogger(Log.class.getName());

    @BeforeClass
    public void beforeClass() throws Exception {
        ExcelUtil.setExcelFile(Constant.TestDataExcelFilePath, Constant.TestDataExcelFileSheet);
    }

    @BeforeTest
    public void beforeMethod() {
        //设置驱动地址
        System.setProperty("webdriver.gecko.driver", Constant.driverPath);
        //设置打开浏览器的地址
        System.setProperty("webdriver.firefox.bin", Constant.browswePath);
        webDriver = new FirefoxDriver();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void testMail126AddContactPerson() throws Exception {
        log.info(ExcelUtil.getCellData(1,0));
        String username = ExcelUtil.getCellData(1, 1);
        String password = ExcelUtil.getCellData(1, 2);
        String contactName = ExcelUtil.getCellData(1, 3);
        String email = ExcelUtil.getCellData(1, 4);
        String phone = ExcelUtil.getCellData(1, 5);

        log.info("调用AddContactPersonAction的createContact方法");
        AddContactPersonAction.createContact(webDriver, username,
                password, contactName, email, phone);
        Thread.sleep(3000);
        Assert.assertTrue(webDriver.getPageSource().contains(contactName));
        Assert.assertTrue(webDriver.getPageSource().contains(email));
        Assert.assertTrue(webDriver.getPageSource().contains(phone));
        ExcelUtil.setCellData(1, 9, "success");
    }


    @AfterMethod
    public void afterMethod() {
        webDriver.quit();//关闭浏览器
    }

}
