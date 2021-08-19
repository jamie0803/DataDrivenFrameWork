package cn.gloryroad.appMoudles;

import cn.gloryroad.pageObjects.AddressBookPage;
import cn.gloryroad.pageObjects.HomePage;
import cn.gloryroad.util.Log;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

/**
 * @Author: Zhang Huijuan
 * @Date: 2021/8/11 21:45
 */
public class AddContactPersonAction {
    public static void createContact(WebDriver driver, String username, String password, String contactName, String email, String phone) throws Exception {
        Log.info("调用 Login_Action 类的 execute 方法");
        LoginAction.execute(driver, username, password);
        Thread.sleep(3000);
        Log.info("断言登录后的页面是否包含“未读邮件”关键字");
        Assert.assertTrue(driver.getPageSource().contains("未读邮件"));
        Log.info("在登录后的用户主页中，单击“通讯录”链接");
        HomePage homePage = new HomePage(driver);
        homePage.addressLink().click();

        AddressBookPage bookPage = new AddressBookPage(driver);
        Log.info("休眠 3 秒，等待打开通讯录页面");
        Thread.sleep(3000);
        Log.info("在通讯录的页面，单击“新建联系人”按钮");
        bookPage.createContactPerson().click();
        Log.info("在联系人姓名的输入框中，输入：" + contactName);
        bookPage.createPersonName().sendKeys(contactName);
        Log.info("在联系人电子邮件的输入框中，输入：" + email);
        bookPage.createPersonEmail().sendKeys(email);
        Log.info("在联系人手机号的输入框中，输入：" + phone);
        bookPage.createPersonMobile().sendKeys(phone);
        Log.info("在联系人手机号的输入框中，单击“确定”按钮");
        bookPage.saveButton().click();
        Log.info("休眠 5 秒等待，等待保存联系人后返回到通讯录的主页面");
        Thread.sleep(3000);
    }

}
