package cn.gloryroad.appMoudles;

import cn.gloryroad.pageObjects.LoginPage;
import org.openqa.selenium.WebDriver;

/**
 * @Author: Zhang Huijuan
 * @Date: 2021/8/10 7:48
 */
public class LoginAction {
    public static void execute(WebDriver driver, String username, String password) throws Exception {
        driver.get("https://mail.126.com");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.switchToFrame();

        loginPage.username().sendKeys(username);
        loginPage.password().sendKeys(password);
        loginPage.loginButton().click();
        loginPage.defaultToFrame();

        Thread.sleep(5000);
    }
}
