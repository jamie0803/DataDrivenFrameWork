package cn.gloryroad.pageObjects;

import cn.gloryroad.util.Constant;
import cn.gloryroad.util.ObjectMap;
import cn.gloryroad.util.Tools;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @Author: Zhang Huijuan
 * @Date: 2021/8/10 21:21
 */
public class AddressBookPage {
    public WebElement element;
//    private ObjectMap objectMap = new ObjectMap("objectMap.properties");
    private ObjectMap objectMap = new ObjectMap(Tools.getFilePath(AddressBookPage.class, Constant.ObjectMapProfileName));

    private WebDriver driver;

    public AddressBookPage(WebDriver driver) {
        this.driver = driver;
    }

    //新建联系人
    public WebElement createContactPerson() throws Exception {
        element = driver.findElement(objectMap.getLocator("126mail.addressBook.createContactPerson"));
        return element;
    }

    //姓名
    public WebElement createPersonName() throws Exception {
        element = driver.findElement(objectMap.getLocator("126mail.addressBook.contactPersonName"));
        return element;
    }

    //email
    public WebElement createPersonEmail() throws Exception {
        element = driver.findElement(objectMap.getLocator("126mail.addressBook.contactPersonEmail"));
        return element;
    }

    //phone
    public WebElement createPersonMobile() throws Exception {
        element = driver.findElement(objectMap.getLocator("126mail.addressBook.contactPersonMobile"));
        return element;
    }

    //save button
    public WebElement saveButton() throws Exception {
        element = driver.findElement(objectMap.getLocator("126mail.addressBook.saveButton"));
        return element;
    }
}
