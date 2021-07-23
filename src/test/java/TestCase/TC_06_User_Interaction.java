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
import java.util.concurrent.TimeUnit;

public class TC_06_User_Interaction {
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
    void TC01_Hover_Element()
    {
       driver.get("https://automationfc.github.io/jquery-tooltip/");
       wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='age']")));

       action.moveToElement(driver.findElement(By.xpath("//input[@id='age']"))).perform();

       Assert.assertEquals(driver.findElement(By.xpath("//div[@class='ui-tooltip-content']")).getText().trim(), "We ask for your age only for statistical purposes.");

       driver.get("https://www.myntra.com/");
       wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-group='kids']")));

       action.moveToElement(driver.findElement(By.xpath("//a[@data-group='kids']"))).perform();

       driver.findElement(By.xpath("//a[text()='Home & Bath']")).click();

       wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@class='title-title']")));
       Assert.assertEquals(driver.findElement(By.xpath("//h1[@class='title-title']")).getText().trim(), "Kids Home Bath");

    }
    @Test
    void TC02_Click_And_Hold()
    {
        driver.get("https://automationfc.github.io/jquery-selectable/");

        List<WebElement> listUnSelect = driver.findElements(By.xpath("//li[contains(@class,'ui-selectee')]"));

        action.clickAndHold(listUnSelect.get(0)).moveToElement(listUnSelect.get(3)).perform();
        action.release().perform();

        List<WebElement> listSelect = driver.findElements(By.xpath("//li[contains(@class,'ui-selected')]"));
        Assert.assertEquals(listSelect.size(), 4);

        driver.navigate().refresh();
        listUnSelect = driver.findElements(By.xpath("//li[contains(@class,'ui-selectee')]"));

        action.click(listUnSelect.get(0)).perform();
        action.keyDown(Keys.CONTROL).perform();

        action.click(listUnSelect.get(2)).perform();
        action.click(listUnSelect.get(5)).perform();
        action.click(listUnSelect.get(10)).perform();
        action.release().perform();

        listSelect = driver.findElements(By.xpath("//li[contains(@class,'ui-selected')]"));
        Assert.assertEquals(listSelect.size(), 4);


    }
    @Test
    void TC03_Double_Click() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@ondblclick='doubleClickMe()']")));

        action.doubleClick(driver.findElement(By.xpath("//button[@ondblclick='doubleClickMe()']"))).perform();

        Assert.assertEquals(driver.findElement(By.xpath("//p[@id='demo']")).getText(), "Hello Automation Guys!");

    }
    @Test
    void TC04_Right_Click() {
        driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='context-menu-one btn btn-neutral']")));

        action.contextClick(driver.findElement(By.xpath("//span[@class='context-menu-one btn btn-neutral']"))).perform();

        Assert.assertTrue(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit')]")).isDisplayed());

        driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit')]")).click();
        alert = driver.switchTo().alert();
        alert.accept();

        Assert.assertFalse(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit')]")).isDisplayed());

    }
    @Test
    void TC05_Drap_And_Drop_HTML4() {
        driver.get("https://automationfc.github.io/kendo-drag-drop/");
        WebElement source = driver.findElement(By.id("draggable"));
        WebElement target = driver.findElement(By.id("droptarget"));
        action.dragAndDrop(source, target).perform();
        Assert.assertEquals(target.getText().trim(), "You did great!");
    }

    int random()
    {
        Random random = new Random();

        return random.nextInt(99999);
    }

}
