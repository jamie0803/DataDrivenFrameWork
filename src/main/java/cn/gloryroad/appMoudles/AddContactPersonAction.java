package cn.gloryroad.appMoudles;

import cn.gloryroad.pageObjects.AddressBookPage;
import cn.gloryroad.pageObjects.HomePage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

/**
 * @Author: Zhang Huijuan
 * @Date: 2021/8/11 21:45
 */
public class AddContactPersonAction {
    public static void createContact(WebDriver driver, String username, String password, String contactName, String email, String phone) throws Exception {
        LoginAction.execute(driver, username, password);
        Thread.sleep(3000);
        Assert.assertTrue(driver.getPageSource().contains("未读邮件"));
        System.out.println(true);
        HomePage homePage = new HomePage(driver);
        homePage.addressLink().click();

        AddressBookPage bookPage = new AddressBookPage(driver);
        Thread.sleep(3000);
        bookPage.createContactPerson().click();
        bookPage.createPersonName().sendKeys(contactName);
        bookPage.createPersonEmail().sendKeys(email);
        bookPage.createPersonMobile().sendKeys(phone);
        bookPage.saveButton().click();
        Thread.sleep(3000);
    }

}
