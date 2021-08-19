package cn.gloryroad.appMoudles;

import cn.gloryroad.pageObjects.LoginPage;
import cn.gloryroad.util.Constant;
import cn.gloryroad.util.Log;
import org.openqa.selenium.WebDriver;

/**
 * @Author: Zhang Huijuan
 * @Date: 2021/8/10 7:48
 */
public class LoginAction {
    public static void execute(WebDriver driver, String username, String password) throws Exception {
        Log.info("访问网址 http://mail.126.com");
        driver.get(Constant.Url);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.switchToFrame();

        Log.info("输入用户名：" + username);
        loginPage.username().sendKeys(username);
        Log.info("输入密码：" + password);
        loginPage.password().sendKeys(password);
        Log.info("单击登录页面的“登录”按钮");
        loginPage.loginButton().click();
        Log.info("单击“登录”按钮后，休眠 5 秒，等待从登录页跳转到登录后的用户主页");
        loginPage.defaultToFrame();

        Thread.sleep(3000);
    }
}
