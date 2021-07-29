package TestCase;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TC_08_Wait_Mix_Implicit_Explicit {
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    void beforeClass()
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.manage().window().maximize();

    }
    @Test
    void TC01_Element_Not_Found_Only_Implicit()
    {
        driver.get("https://www.facebook.com/");

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("start: "+getDateTimeSecondNow());
        try
        {
            WebElement element = driver.findElement(By.xpath("//input[@name='not_here']"));
            Assert.assertTrue(element.isDisplayed());
            System.out.println("in try");
        }catch (Exception ex)
        {
            //System.out.println(ex.toString());
            System.out.println("............exception in catch...........");
        }
        System.out.println("end: "+getDateTimeSecondNow());

    }
    @Test
    void TC02_Element_Not_Found_Implicit_Greater_Than_Explicit()
    {
        driver.get("https://www.facebook.com/");

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        explicitWait = new WebDriverWait(driver, 5);
        System.out.println("start: "+getDateTimeSecondNow());
        try
        {
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='not_here']")));

            System.out.println("in try");
        }catch (Exception ex)
        {
            //System.out.println(ex.toString());
            System.out.println("............exception in catch...........");
        }
        System.out.println("end: "+getDateTimeSecondNow());

    }
    @Test
    void TC03_Element_Not_Found_Explicit_Using_By()
    {
        driver.get("https://www.facebook.com/");

        explicitWait = new WebDriverWait(driver, 10);
        System.out.println("start: "+getDateTimeSecondNow());
        try
        {
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='not_here']")));
            System.out.println("in try");
        }catch (Exception ex)
        {
            //System.out.println(ex.toString());
            System.out.println("............exception in catch...........");
        }
        System.out.println("end: "+getDateTimeSecondNow());

    }
    @Test
    void TC04_Element_Not_Found_Explicit_Using_WebElement()
    {
        driver.get("https://www.facebook.com/");

        explicitWait = new WebDriverWait(driver, 10);
        System.out.println("start: "+getDateTimeSecondNow());
        try
        {
            WebElement element = driver.findElement(By.xpath("//input[@name='not_here']"));
            System.out.println("in try");
        }catch (Exception ex)
        {
            //System.out.println(ex.toString());
            System.out.println("............exception in catch...........");
        }
        System.out.println("end: "+getDateTimeSecondNow());

    }
    public String getDateTimeSecondNow()
    {
        Date date = new Date();
        return date.toString();
    }



}
