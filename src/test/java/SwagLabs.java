import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SwagLabs {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        System.setProperty("webdriver.chrome.whitelistedIps", "");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        //khoi tao trinh duyet chrome
        WebDriver driver = new ChromeDriver(options);
        //mo full man hinh
        driver.manage().window().maximize();
        try {

            driver.get("https://www.saucedemo.com/");

            //wait thay the bang thread.sleep -> boc vo bang try catch
            //simple wait (sau nay se thay the bang Webdriverwait)
            Thread.sleep(5000);
            //locator
            //WebElement userName = driver.findElement(By.xpath("//input[@id=\"user-name\"]"));
            //hoac
            WebElement userName = driver.findElement(By.id("user-name"));

            WebElement passWord = driver.findElement(By.xpath("//input[@id=\"password\"]"));
            WebElement btnLogin = driver.findElement(By.xpath("//input[@id=\"login-button\"]"));

            //nhap lieu
            userName.sendKeys("standard_user");
            passWord.sendKeys("secret_sauce");
            btnLogin.click();

            Thread.sleep(5000);
            System.out.println("Dang nhap thanh cong");

        } catch (Exception e) {
            System.out.println("Loi: " + e.getMessage());
        } finally {
  //          driver.quit();
        }
    }
}
