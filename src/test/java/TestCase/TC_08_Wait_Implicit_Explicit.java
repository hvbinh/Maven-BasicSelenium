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
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class TC_08_Wait_Implicit_Explicit {
    WebDriver driver;
    WebDriverWait explicitWait;
    JavascriptExecutor jsExecutor;
    String path;

    @BeforeClass
    void beforeClass()
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.manage().window().maximize();

        explicitWait = new WebDriverWait(driver, 30);

        jsExecutor = (JavascriptExecutor)driver;

        path = System.getProperty("user.dir");


    }
    @Test
    void TC01_Explicit_Wait()
    {
        driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");

        driver.findElement(By.xpath("//div[@id='start']/button")).click();

        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='loading']")));

        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText().trim(), "Hello World!");
    }
    @Test
    void TC02_Explicit_Wait() {
        driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");

        driver.findElement(By.xpath("//div[@id='start']/button")).click();

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='finish']/h4")));

        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText().trim(), "Hello World!");

    }
    @Test
    void TC03_Explicit_Wait() {
        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='contentWrapper']")));
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']")).getText().trim(), "No Selected Dates to display.");

        jsExecutor.executeScript("arguments[0].click();",driver.findElement(By.xpath("//a[text()='1']")));

        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='ctl00_ContentPlaceholder1_RadAjaxLoadingPanel1ctl00_ContentPlaceholder1_RadCalendar1']/div[@class='raDiv']")));

        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']")).getText().trim(), "Thursday, July 1, 2021");

    }
    @Test
    void TC04_Explicit_Wait() {
        driver.get("https://gofile.io/uploadFiles");
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='file']")));
        driver.findElement(By.xpath("//input[@type='file']")).sendKeys(path+"/src/test/Image/rose.jpg");

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("rowUploadSuccess-showFiles")));

        driver.findElement(By.id("rowUploadSuccess-showFiles")).click();

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("rowFolder-info-delete")));
        Assert.assertTrue(driver.findElement(By.id("rowFolder-info-delete")).isDisplayed());
    }



}
