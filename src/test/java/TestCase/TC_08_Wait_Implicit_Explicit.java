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

    @BeforeClass
    void beforeClass()
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }
    @Test
    void TC01_Implicit_Wait()
    {
       driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");

       driver.findElement(By.xpath("//div[@id='start']/button")).click();

       Assert.assertTrue(driver.findElement(By.xpath("//h4[text()='Hello World!']")).isDisplayed());
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


    private void sleepInSecond(long time) throws InterruptedException {
        Thread.sleep(time * 1000);
    }

}
