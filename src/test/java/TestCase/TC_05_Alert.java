package TestCase;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TC_05_Alert {
    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor jsExecutor;
    Alert alert;

    @BeforeClass
    void beforeClass()
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.fahasa.com/customer/account/create?attempt=1");
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, 30);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        jsExecutor = (JavascriptExecutor) driver;
    }
    @Test
    void TC01_Remove_Attribute_In_Button()
    {
       wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='fhs-btn-register']")));

       jsExecutor.executeScript("arguments[0].removeAttribute('disabled')", driver.findElement(By.xpath("//button[@class='fhs-btn-register']")));
        driver.findElement(By.xpath("//button[@class='fhs-btn-register']")).click();
    }
    @Test
    void TC02_Accept_Alert()
    {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@onclick='jsAlert()']")));
        driver.findElement(By.xpath("//button[@onclick='jsAlert()']")).click();

        alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(), "I am a JS Alert");
        alert.accept();

    }
    @Test
    void TC03_Confirm_Alert()
    {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@onclick='jsConfirm()']")));
        driver.findElement(By.xpath("//button[@onclick='jsConfirm()']")).click();

        alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(), "I am a JS Confirm");
        alert.accept();

        driver.findElement(By.xpath("//button[@onclick='jsConfirm()']")).click();
        alert.dismiss();

    }
    @Test
    void TC04_Prompt_Alert()
    {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@onclick='jsPrompt()']")));
        driver.findElement(By.xpath("//button[@onclick='jsPrompt()']")).click();

        String promptText = "yes";

        alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(), "I am a JS prompt");
        alert.sendKeys(promptText);
        alert.accept();
        Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(),"You entered: "+promptText);

        driver.findElement(By.xpath("//button[@onclick='jsPrompt()']")).click();
        alert.dismiss();

    }
    @Test
    void TC05_By_Pass_Authen_Alert()
    {
        driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='example']/p")).getText().trim(),"Congratulations! You must have the proper credentials.");

    }
    int random()
    {
        Random random = new Random();

        return random.nextInt(99999);
    }

}
