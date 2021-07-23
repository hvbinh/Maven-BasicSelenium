package TestCase;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class TC_07_Pop_Up_Iframe_Windown_Tab_Browser {
    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor jsExecutor;
    Alert alert;
    Actions action;

    @BeforeClass
    void beforeClass()
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, 30);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        jsExecutor = (JavascriptExecutor) driver;

        action = new Actions(driver);
    }
    @Test
    void TC01_Random_Popup_Have_In_DOM()
    {
       driver.get("https://blog.testproject.io/");


       if(driver.findElement(By.xpath("//div[@id='close-mailch']")).isDisplayed())
       {
           driver.findElement(By.xpath("//div[@id='close-mailch']")).click();
       }

           driver.findElement(By.xpath("//section[@id='search-2']//input[@class='search-field']")).sendKeys("Selenium");

    }
    @Test
    void TC02_Random_Popup_Not_Have_In_DOM()
    {
        driver.get("https://shopee.vn/");

        List<WebElement> popup = driver.findElements(By.xpath("//div[@class='shopee-popup__container']"));
        if(popup.size()>0)
        {
            driver.findElement(By.xpath("//div[@class='shopee-popup__close-btn']")).click();
        }
        driver.findElement(By.xpath("//input[@class='shopee-searchbar-input__input']")).sendKeys("Macbook Pro");
        driver.findElement(By.cssSelector("button[class*='btn-solid-primary']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='iPhone 12 & iPhone 12 Pro']")));
        Assert.assertEquals(driver.findElement(By.xpath("//div[text()='iPhone 12 & iPhone 12 Pro']")).getText().trim(), "iPhone 12 & iPhone 12 Pro");

    }
    @Test
    void TC03_Frame_Iframe() throws InterruptedException {
        driver.get("https://kyna.vn/");

        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='face-content']/iframe")).isDisplayed());

        driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='face-content']/iframe")));

        Assert.assertEquals(driver.findElement(By.xpath("//a[@title='Kyna.vn']/parent::div/following-sibling::div")).getText(), "168K lượt thích");

        driver.switchTo().defaultContent();

        driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='cs-live-chat']//iframe")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class*='meshim_widget_components_chatButton_Button']")));

        driver.findElement(By.cssSelector("div[class*='meshim_widget_components_chatButton_Button']")).click();
        driver.findElement(By.cssSelector("input[class*='input_name']")).sendKeys("john");
        driver.findElement(By.cssSelector("input[class*='input_phone']")).sendKeys("0908123456");
        driver.findElement(By.name("message")).sendKeys("test");
        selectItemInDropdown("//select[@id='serviceSelect']","//select[@id='serviceSelect']/option","TƯ VẤN TUYỂN SINH");
        driver.findElement(By.cssSelector("input[class*='submit']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='controls'] button[class*='wide']")));
        driver.findElement(By.cssSelector("div[class='controls'] button[class*='wide']")).click();

        driver.switchTo().defaultContent();

    }
    @Test
    public void windows_Tab_Browser_1()
    {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='GOOGLE']")));
        driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();

        String id = driver.getWindowHandle();
        switch_Tab_Browser_By_ID(id);

        Assert.assertEquals(driver.getTitle(), "Google");

        driver.switchTo().window(id);

        driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();

        switch_Tab_Browser_By_ID(id);

        Assert.assertEquals(driver.getTitle(), "Facebook - Đăng nhập hoặc đăng ký");

        driver.switchTo().window(id);

    }
    @Test
    public void windows_Tab_Browser_2()
    {
        driver.get("https://kyna.vn/");

        WebElement element = driver.findElement(By.xpath("//div[@id='k-footer']//img[@alt='facebook']"));

        jsExecutor.executeScript("arguments[0].scrollIntoView();", element);

        driver.findElement(By.xpath("//div[@id='k-footer']//img[@alt='facebook']")).click();
        driver.findElement(By.xpath("//div[@id='k-footer']//img[@alt='youtube']")).click();
        String id = driver.getWindowHandle();
        closeAllWindowsExceptParrentWindow(id);

    }
    public void switch_Tab_Browser_By_Title(String title)
    {
        Set<String> listID = driver.getWindowHandles();
        for(String id : listID)
        {
            if(driver.switchTo().window(id).equals(title))
            {
                break;
            }
        }
    }
    public void closeAllWindowsExceptParrentWindow(String mainBrowserID)
    {
        Set<String> listID = driver.getWindowHandles();
        for(String id : listID)
        {
            if(!id.equals(mainBrowserID))
            {
                driver.switchTo().window(id);
                driver.close();
            }
        }
        driver.switchTo().window(mainBrowserID);
    }
    public void switch_Tab_Browser_By_ID(String mainBrowserID)
    {
        Set<String> listID = driver.getWindowHandles();
        for(String id : listID)
        {
            if(!id.equals(mainBrowserID))
            {
                driver.switchTo().window(id);
            }
        }
    }

    public void selectItemInDropdown(String parentXpath, String childXpath, String expectedResult) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(parentXpath)));
        driver.findElement(By.xpath(parentXpath)).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(childXpath)));
        List<WebElement> actualElements = driver.findElements(By.xpath(childXpath));

        for (WebElement element : actualElements) {
            if(element.getText().trim().equalsIgnoreCase(expectedResult.trim()))
            {

                jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
                Thread.sleep(1000);
                element.click();

                sleepInSecond(2);

                break;
            }
        }

    }
    private void sleepInSecond(long time) throws InterruptedException {
        Thread.sleep(time * 1000);
    }

}
