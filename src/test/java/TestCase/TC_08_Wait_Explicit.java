package TestCase;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TC_08_Wait_Explicit {
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

    private void sleepInSecond(long time) throws InterruptedException {
        Thread.sleep(time * 1000);
    }

}
