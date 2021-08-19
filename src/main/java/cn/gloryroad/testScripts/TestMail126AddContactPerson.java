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

/**
 * @Author: Zhang Huijuan
 * @Date: 2021/8/11 22:12
 */
public class TestMail126AddContactPerson {
    public WebDriver webDriver;


    @BeforeClass
    public void beforeClass() throws Exception {
        ExcelUtil.setExcelFile(Constant.TestDataExcelFilePath, Constant.TestDataExcelFileSheet);
    }

    @BeforeTest
    public void beforeMethod() {
        Log.info("打开浏览器");
        //设置驱动地址
        System.setProperty("webdriver.gecko.driver", Constant.driverPath);
        //设置打开浏览器的地址
        System.setProperty("webdriver.firefox.bin", Constant.browswePath);
        webDriver = new FirefoxDriver();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void testMail126AddContactPerson() throws Exception {
        Log.startTestCase(ExcelUtil.getCellData(1, 0));
        String username = ExcelUtil.getCellData(1, 1);
        String password = ExcelUtil.getCellData(1, 2);
        String contactName = ExcelUtil.getCellData(1, 3);
        String email = ExcelUtil.getCellData(1, 4);
        String phone = ExcelUtil.getCellData(1, 5);

        Log.info("调用AddContactPersonAction的createContact方法");
        AddContactPersonAction.createContact(webDriver, username, password, contactName, email, phone);
        Log.info("调用 AddContactPerson_Action类的execute方法后，休眠 3 秒");
        Thread.sleep(3000);
        Log.info("断言通讯录的页面是否包含联系人姓名的关键字");
        Assert.assertTrue(webDriver.getPageSource().contains(contactName));
        Log.info("断言通讯录的页面是否包含联系人电子邮件地址的关键字");
        Assert.assertTrue(webDriver.getPageSource().contains(email));
        Log.info("断言通讯录的页面是否包含联系人手机号的关键字");
        Assert.assertTrue(webDriver.getPageSource().contains(phone));
        Log.info("新建联系人的全部断言成功，在 Excel 测试数据文件的“测试执行结果”列中写入“执行成功”");
        ExcelUtil.setCellData(1, 9, "success");
        Log.endTestCase(ExcelUtil.getCellData(1, 0));
    }


    @AfterMethod
    public void afterMethod() {
        Log.info("关闭浏览器");
        webDriver.quit();//关闭浏览器
    }

}
