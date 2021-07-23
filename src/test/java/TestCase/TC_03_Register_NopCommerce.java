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

import java.time.LocalDate;
import java.util.Random;

public class TC_03_Register_NopCommerce {
    WebDriver driver;
    WebDriverWait wait;

    By maleGenderBy = By.id("gender-male");
    By firstNameBy = By.id("FirstName");
    By lastNameBy = By.id("LastName");
    By dateOfBirthDayBy = By.name("DateOfBirthDay");
    By dateOfBirthMonthBy = By.name("DateOfBirthMonth");
    By dateOfBirthYearBy = By.name("DateOfBirthYear");
    By emailBy = By.id("Email");
    By companyBy = By.id("Company");
    By passwordBy = By.id("Password");
    By confirmPasswordBy = By.id("ConfirmPassword");
    By registerBy = By.id("register-button");

    String firstName = "Skype"+random();
    String lastName = "User"+random();
    String email = "giri"+random()+"@example.com";
    String company = "IT";
    String password = "123456";
    String confirmPassword = "123456";


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
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ico-register")));
        driver.findElement(By.className("ico-register")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("FirstName")));

        driver.findElement(maleGenderBy).click();
        driver.findElement(firstNameBy).sendKeys(firstName);
        System.out.println(firstName);

        driver.findElement(lastNameBy).sendKeys(lastName);
        System.out.println(lastName);

        Select day = new Select(driver.findElement(dateOfBirthDayBy));
        day.selectByVisibleText("10");

        Select month = new Select(driver.findElement(dateOfBirthMonthBy));
        month.selectByVisibleText("May");

        Select year = new Select(driver.findElement(dateOfBirthYearBy));
        year.selectByVisibleText("1990");

        driver.findElement(emailBy).sendKeys(email);
        System.out.println(email);

        driver.findElement(companyBy).sendKeys(company);

        driver.findElement(passwordBy).sendKeys(password);
        driver.findElement(confirmPasswordBy).sendKeys(confirmPassword);

        driver.findElement(registerBy).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='result']")));
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(), "Your registration completed");


    }
    private int random()
    {
        Random random = new Random();

        return random.nextInt(99999);
    }
    private  String  getCurrentDateAndRandom()
    {
        LocalDate date = LocalDate.now();
        String currentDate = date.toString()+"-"+random();
        return currentDate;
    }

}
