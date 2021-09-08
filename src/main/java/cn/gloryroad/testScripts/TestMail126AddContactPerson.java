package cn.gloryroad.testScripts;

import cn.gloryroad.appMoudles.AddContactPersonAction;
import cn.gloryroad.util.Constant;
import cn.gloryroad.util.ExcelUtil;
import cn.gloryroad.util.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
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
        webDriver.get(Constant.Url);
    }

    /**
     * 第一种方式，后续通过@DataProvider注解提供参数
     *
     * @Test public void testMail126AddContactPerson() throws Exception {
     * Log.startTestCase(ExcelUtil.getCellData(1, 0));
     * String username = ExcelUtil.getCellData(1, 1);
     * String password = ExcelUtil.getCellData(1, 2);
     * String contactName = ExcelUtil.getCellData(1, 3);
     * String email = ExcelUtil.getCellData(1, 4);
     * String phone = ExcelUtil.getCellData(1, 5);
     * <p>
     * Log.info("调用AddContactPersonAction的createContact方法");
     * AddContactPersonAction.createContact(webDriver, username, password, contactName, email, phone);
     * Log.info("调用 AddContactPerson_Action类的execute方法后，休眠 3 秒");
     * Thread.sleep(3000);
     * Log.info("断言通讯录的页面是否包含联系人姓名的关键字");
     * Assert.assertTrue(webDriver.getPageSource().contains(contactName));
     * Log.info("断言通讯录的页面是否包含联系人电子邮件地址的关键字");
     * Assert.assertTrue(webDriver.getPageSource().contains(email));
     * Log.info("断言通讯录的页面是否包含联系人手机号的关键字");
     * Assert.assertTrue(webDriver.getPageSource().contains(phone));
     * Log.info("新建联系人的全部断言成功，在 Excel 测试数据文件的“测试执行结果”列中写入“执行成功”");
     * ExcelUtil.setCellData(1, 9, "success");
     * Log.endTestCase(ExcelUtil.getCellData(1, 0));
     * }
     */

    @DataProvider(name = "testData")
    public Object[][] testData() throws IOException {
        return ExcelUtil.getTestData(Constant.TestDataExcelFilePath, Constant.TestDataExcelFileSheet);
    }

    @Test(dataProvider = "testData")
    public void testAddressBook(String caseRowNum, String testCaseName, String userName,
                                String password, String contactName, String email, String contactPhone,
                                String assertContactName, String assertEmail, String assertPhone) throws InterruptedException {
        Log.startTestCase(testCaseName);

        Log.info("执行AddContactPersonAction类的createPerson()");
        try {
            AddContactPersonAction.createContact(webDriver, userName, password, contactName, email, contactPhone);
        } catch (Exception e) {
            e.printStackTrace();
            Log.info("添加联系人失败");
            /**因为Excel的序号格式默认为带一位小数，所以使用split(".")[0]取前面整数部分，
             * 并传给setCellData()，在对应序号的测试数据行的最后一列设定为“测试执行失败”
             */
            ExcelUtil.setCellData(Integer.parseInt(caseRowNum.split(".")[0]), ExcelUtil.getLastColumnNum(), "测试执行失败");
            //调用Assert类的fail将此测试用例设为执行失败，后续测试代码将不再执行
            Assert.fail("执行AddContactPersonAction类的createPerson()失败");
        }
        Log.info("调用AddContactPersonAction类的createPerson()后，休眠2秒");
        Thread.sleep(2000);

        Log.info("判断通讯录的页面是否包含联系人姓名等关键字");
        try {
            Assert.assertTrue(webDriver.getPageSource().contains(assertContactName));
        } catch (Exception e) {
            Log.info("判断通讯录的页面是否包含联系人姓名等关键字失败");
            ExcelUtil.setCellData(Integer.parseInt(caseRowNum.split(".")[0]), ExcelUtil.getLastColumnNum(), "测试执行失败");
            Assert.fail("判断通讯录的页面是否包含联系人姓名等关键字失败");
        }

        Log.info("判断通讯录的页面是否包含联系人email等关键字");
        try {
            Assert.assertTrue(webDriver.getPageSource().contains(assertEmail));
        } catch (Exception e) {
            Log.info("判断通讯录的页面是否包含联系人email等关键字失败");
            ExcelUtil.setCellData(Integer.parseInt(caseRowNum.split(".")[0]), ExcelUtil.getLastColumnNum(), "测试执行失败");
            Assert.fail("判断通讯录的页面是否包含联系人email等关键字失败");
        }

        Log.info("判断通讯录的页面是否包含联系人电话等关键字");
        try {
            Assert.assertTrue(webDriver.getPageSource().contains(assertPhone));
        } catch (Exception e) {
            Log.info("判断通讯录的页面是否包含联系人电话等关键字失败");
            ExcelUtil.setCellData(Integer.parseInt(caseRowNum.split(".")[0]), ExcelUtil.getLastColumnNum(), "测试执行失败");
            Assert.fail("判断通讯录的页面是否包含联系人电话等关键字失败");
        }

        Log.info("新建联系人的全部断言成功，在Excel的“测试执行结果”列写入“执行成功”");
        try {
            Assert.assertTrue(webDriver.getPageSource().contains(assertContactName));
        } catch (Exception e) {
            ExcelUtil.setCellData(Integer.parseInt(caseRowNum.split(".")[0]), ExcelUtil.getLastColumnNum(), "执行成功");
            Log.info("测试执行结果成功写入Excel数据文件的“测试执行结果”列");
            Log.endTestCase(testCaseName);
        }
    }


    @AfterMethod
    public void afterMethod() {
        Log.info("关闭浏览器");
        webDriver.quit();//关闭浏览器
    }

}
