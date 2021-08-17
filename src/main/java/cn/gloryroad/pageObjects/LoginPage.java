package cn.gloryroad.pageObjects;

import cn.gloryroad.util.ObjectMap;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


/**
 * @Author: Zhang Huijuan
 * @Date: 2021/8/9 22:46
 */
public class LoginPage {
    private WebElement element;
    //制定页面元素定位表达式配置文件的绝对路径
    private ObjectMap objectMap = new
            ObjectMap("D:\\Program Files\\WorkSpace\\DataDrivenFrameWork\\objectMap.properties");

    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    //进入iframe
    public void switchToFrame() throws Exception {
        Thread.sleep(5000);
        driver.switchTo().frame(driver.findElement(objectMap.getLocator
                ("126mail.loginPage.iframe")));
    }
    //退出iframe
    public void defaultToFrame()throws Exception{
        Thread.sleep(5000);
        driver.switchTo().defaultContent();
    }

    //返回登录页面中的用户名输入框页面元素对象
    public WebElement username() throws Exception {
        //使用ObjectMap类中的getLocator方法获取配置文件中关于用户名的定位方式和定位表达式
        element = driver.findElement(objectMap.getLocator
                ("126mail.loginPage.username"));
        return element;
    }
    //返回登录页面中的密码输入框页面元素对象
    public WebElement password() throws Exception {
        element = driver.findElement(objectMap.getLocator
                ("126mail.loginPage.password"));
        return element;
    }
    //返回登录页面中的登录按钮页面元素对象
    public WebElement loginButton() throws Exception {
        element = driver.findElement(objectMap.getLocator
                ("126mail.loginPage.loginbutton"));
        return element;
    }
}
