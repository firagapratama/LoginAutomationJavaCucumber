package stepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import config.env_target;

import java.time.Duration;

public class Login extends env_target {
    @Given("User is on login page")
    public void userIsOnLoginPage() {
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--remote-allow-origins=*");

        System.setProperty("webdriver.chrome.driver","src\\test\\resources\\drivers\\chromedriver.exe");
        driver = new ChromeDriver(option);
        driver.manage().window().maximize();
        driver.get(baseUrl);

        Duration duration = Duration.ofSeconds(10);
        WebDriverWait wait = new WebDriverWait(driver, duration);
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"btn_submit\"]"))
        );
    }

    @When("User fill username and password")
    public void userFillUsernameAndPassword() {
        driver.findElement(By.name("user_name")).sendKeys("{your_username}");
        driver.findElement(By.name("user_password")).sendKeys("{your_password}");
    }

    @And("User click login button")
    public void userClickLoginButton() {
        driver.findElement(By.xpath("//*[@id=\"btn_submit\"]")).click();
    }

    @Then("User verify login result")
    public void userVerifyLoginResult() {
        Duration duration = Duration.ofSeconds(10);
        WebDriverWait wait = new WebDriverWait(driver, duration);
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"wrapper\"]/div[4]/div/div/div/div/div/h4"))
        );
        driver.close();
    }

    @When("User fill wrong username or password")
    public void userFillWrongUsernameAndPassword() {
        driver.findElement(By.name("user_name")).sendKeys("{your_wrong_username}");
        driver.findElement(By.name("user_password")).sendKeys("{your_wrong_password}");
    }

    @Then("User get error message")
    public void userGetErrorMessage() {
        Duration duration = Duration.ofSeconds(10);
        WebDriverWait wait = new WebDriverWait(driver, duration);
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/section/div/div/div/div/div/div[2]/div[1]"))
        );
        driver.close();
    }

}
