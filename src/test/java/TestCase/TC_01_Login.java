package TestCase;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.DriverManager;
import java.util.Random;

public class TC_01_Login {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    void beforeClass()
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://demo.nopcommerce.com/");
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, 30);
    }
    @Test
    void registerAccount()
    {
        String email;
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ico-register")));
        driver.findElement(By.className("ico-register")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("gender-male")));
        driver.findElement(By.id("gender-male")).click();

        driver.findElement(By.id("FirstName")).sendKeys("ta firstname");
        driver.findElement(By.id("LastName")).sendKeys("ta LastName");

        Select dateOfBirth = new Select(driver.findElement(By.name("DateOfBirthDay")));
        dateOfBirth.selectByVisibleText("2");

        Select month = new Select(driver.findElement(By.name("DateOfBirthMonth")));
        month.selectByVisibleText("March");

        Select year = new Select(driver.findElement(By.name("DateOfBirthYear")));
        year.selectByVisibleText("1990");

        email = "ta"+random()+"@example.com";
        driver.findElement(By.id("Email")).sendKeys(email);
        System.out.println("email: "+email);

        driver.findElement(By.id("Company")).sendKeys("example company");

        driver.findElement(By.id("Password")).sendKeys("123456");

        driver.findElement(By.id("ConfirmPassword")).sendKeys("123456");


        System.out.println("CSS value of Register button: "+driver.findElement(By.id("register-button")).getCssValue("background-color").toString());
        

        driver.findElement(By.id("register-button")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("result")));
        Assert.assertEquals(driver.findElement(By.className("result")).getText(), "Your registration completed");

    }
    int random()
    {
        Random random = new Random();

        return random.nextInt(99999);
    }

}
