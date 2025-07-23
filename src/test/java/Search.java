import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

public class Search {
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

            driver.findElement(By.xpath("//span[text()=\"Admin\"]")).click();
            System.out.println("Click Admin thanh cong");
            Thread.sleep(5000);
            //nhap du lieu vao username va search
            WebElement username = driver.findElement(By.xpath("//label[text()=\"Username\"]/../following-sibling::div/input"));
            username.sendKeys("Admin");

            driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();
            System.out.println("Da nhan tim kiem");
            Thread.sleep(7000);

            //lay danh sach
            List<WebElement> rows = driver.findElements(By.xpath("//div[@class=\"oxs-tbale-body\"]/div")); //=> [{username, userrole}]
            //dat co
            boolean userFound = false; //khong tim thay
            //tao vong lap lay du lieu tung dong
            for(WebElement row : rows) {
                String userFind = row.findElement(By.xpath(".//div[@role=\"cell\"][2]")).getText();
                if (userFind.equalsIgnoreCase("Admin")) {
                    userFound = true;
                    break; // dung vong lap
                }
            }
            if (userFound) {
                System.out.println("Tim thay user");
            } else {
                System.out.println("Khong tim thay");
            }

        } catch (Exception e) {
        } finally {
            driver.quit();
        }
    }
}
