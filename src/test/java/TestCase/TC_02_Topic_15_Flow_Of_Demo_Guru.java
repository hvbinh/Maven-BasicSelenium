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

import java.util.Random;

public class TC_02_Topic_15_Flow_Of_Demo_Guru {
    WebDriver driver;
    WebDriverWait wait;
    String user, pass;
    String url = "http://demo.guru99.com/v4/";

    //new/edit Customer
    By nameTextBoxBy = By.name("name");
    By genderMaleBy = By.xpath("(//input[@name='rad1'])[1]");
    By dobBy = By.id("dob");
    By addressTextAreaBy = By.name("addr");
    By cityTextBoxBy = By.name("city");
    By stateTextBoxBy = By.name("state");
    By pinTextBoxBy = By.name("pinno");
    By telephoneTextBoxBy = By.name("telephoneno");
    By emailTextBoxBy = By.name("emailid");
    By passwordTextBoxBy = By.name("password");
    By submitButtonBy = By.name("sub");

    String name = "sinbad";
    String dob = "01/01/1990";
    String address = "105 Hai Chau";
    String city = "BuonMaThuot";
    String state = "Califorina";
    String pin = "123456";
    String password = "123456";
    String telephone = "0987878788";
    String email = "sinbad"+random()+"@example.com";



    @BeforeClass
    void beforeClass()
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, 30);
    }
    @Test
    void TC_01_RegisterAccount()
    {
        String email = "ta"+random()+"@automation.com";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='here']")));
        driver.findElement(By.xpath("//a[text()='here']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("emailid")));
        driver.findElement(By.name("emailid")).sendKeys(email);
        driver.findElement(By.name("btnLogin")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td/h2")));
        Assert.assertEquals(driver.findElement(By.xpath("//td/h2")).getText(), "Access details to demo site.");

        user = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
        pass = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
        System.out.println("user is: "+user);
        System.out.println("pass is: "+pass);

        driver.get(url);
    }
    @Test
    void TC_02_Login()
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("uid")));
        driver.findElement(By.name("uid")).sendKeys(user);
        driver.findElement(By.name("password")).sendKeys(pass);
        driver.findElement(By.name("btnLogin")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td/marquee")));
        Assert.assertTrue(driver.findElement(By.xpath("//td/marquee")).isDisplayed());


    }
    @Test
    void TC_03_Create_New_Customer()
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='New Customer']")));
        driver.findElement(By.xpath("//a[text()='New Customer']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(nameTextBoxBy));
        driver.findElement(nameTextBoxBy).sendKeys(name);

        driver.findElement(genderMaleBy).click();

        driver.findElement(dobBy).sendKeys(dob);
        driver.findElement(addressTextAreaBy).sendKeys(address);
        driver.findElement(cityTextBoxBy).sendKeys(city);
        driver.findElement(stateTextBoxBy).sendKeys(state);
        driver.findElement(pinTextBoxBy).sendKeys(pin);
        driver.findElement(telephoneTextBoxBy).sendKeys(telephone);
        driver.findElement(emailTextBoxBy).sendKeys(email);
        driver.findElement(passwordTextBoxBy).sendKeys(password);
        driver.findElement(submitButtonBy).click();

        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), name);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(), "male");
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), "1990-01-01");
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), address);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), state);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), telephone);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), email);




    }

    int random()
    {
        Random random = new Random();

        return random.nextInt(99999);
    }

}
