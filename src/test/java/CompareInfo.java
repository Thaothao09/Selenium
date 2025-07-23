import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class CompareInfo {
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

            driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

            //wait thay the bang thread.sleep -> boc vo bang try catch
            //simple wait (sau nay se thay the bang Webdriverwait)
            Thread.sleep(10000);
            //locator
            WebElement userName = driver.findElement(By.xpath("//input[@name=\"username\"]"));
            WebElement passWord = driver.findElement(By.xpath("//input[@name=\"password\"]"));
            String userNameText = driver.findElement(By.xpath("//p[starts-with(normalize-space(),\"Username\")]")).getText();
            String passWordText = driver.findElement(By.xpath("//p[starts-with(normalize-space(),\"Password\")]")).getText();
            //xu ly chuoi
            String userNameValue = userNameText.split(":")[1].trim();
            String passWordValue = passWordText.split(":")[1].trim();
            //nhap lieu
            userName.sendKeys(userNameValue);
            passWord.sendKeys(passWordValue);

            WebElement btnLogin = driver.findElement(By.xpath("//button[@type=\"submit\"]"));
            btnLogin.click();
            System.out.println("Dang nhap thanh cong");
            Thread.sleep(7000);

            driver.findElement(By.xpath("//span[text()=\"My Info\"]")).click();
            System.out.println("Click My Info thanh cong");
            Thread.sleep(5000);

            //lay gia tri firstname va lastname tren myinfo
            WebElement firstName = driver.findElement(By.name("firstName"));
            WebElement lastName = driver.findElement(By.name("lastName"));

            String firstNameValue = driver.findElement(By.name("firstName")).getAttribute("value").trim();
            String lastNameValue = driver.findElement(By.name("lastName")).getAttribute("value").trim();
            String fullNameValue = firstNameValue + " " + lastNameValue;

            //lay xpath bang classname => by.classname, by.cssSelector
            WebElement fullNameDisplay = driver.findElement(By.cssSelector(".oxd-userdropdown-name"));
            String fullNameValueDisplay = fullNameDisplay.getText().trim();

            //so sanh - .equalIgnoreCase -> so sanh gia tri bat ke la viet hoa hay viet thuong
            if(fullNameValue.equalsIgnoreCase(fullNameValueDisplay)){
                System.out.println("Ket qua trung khop");
            } else {
                System.out.println("Ket qua khong trung khop");
            }

            Thread.sleep(7000);
        } catch (InterruptedException e) {
        System.out.println("Loi: " + e.getMessage());
    } finally {
        driver.quit();
    }
}
}