package TestCase;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TC_04_Custome_Dropdown {
    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor jsExecutor;




    @BeforeClass
    void beforeClass()
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        wait = new WebDriverWait(driver, 30);
        jsExecutor = (JavascriptExecutor) driver;
    }
    @Test
    void TC01_dropdownJQuery() throws InterruptedException {
        selectItemInDropdown("//label[text()='Select a number']/following-sibling::span[@id='number-button']","//li[@class='ui-menu-item']/div","18");
        selectItemInDropdown("//label[text()='Select a number']/following-sibling::span[@id='number-button']","//li[@class='ui-menu-item']/div","1");

    }
    @Test
    void TC02_dropdownReactJS() throws InterruptedException {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='root']")));

        selectItemInDropdown("//div[@id='root']","//div[@role='option']/span","Matt");
        selectItemInDropdown("//div[@id='root']","//div[@role='option']/span","Jenny Hess");

    }
    @Test
    void TC03_dropdownAngular() throws InterruptedException {
        driver.get("https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@aria-owns='games_options']//span[@class='e-input-group-icon e-ddl-icon e-search-icon']")));

        selectItemInDropdown("//span[@aria-owns='games_options']//span[@class='e-input-group-icon e-ddl-icon e-search-icon']","//li[@class='e-list-item ']","Tennis");
        String actualResult = (String) jsExecutor.executeScript("return document.querySelector(\"ejs-dropdownlist[id='games']\").textContent");
        System.out.println(actualResult);

        selectItemInDropdown("//span[@aria-owns='games_options']//span[@class='e-input-group-icon e-ddl-icon e-search-icon']","//li[@role='option']","American Football");


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
                //jsExecutor.executeScript("arguments[0].click();",element);

                sleepInSecond(2);

                break;
            }
        }

    }
    private int random()
    {
        Random random = new Random();

        return random.nextInt(99999);
    }
    private void sleepInSecond(long time) throws InterruptedException {
        Thread.sleep(time * 1000);
    }
    private  String  getCurrentDateAndRandom()
    {
        LocalDate date = LocalDate.now();
        String currentDate = date.toString()+"-"+random();
        return currentDate;
    }

}
