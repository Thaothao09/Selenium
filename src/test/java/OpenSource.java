import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class OpenSource {
    public static void main(String[] args) {
        //yêu cầu: viết chương trình automation tìm đến trang opensource và lấy ra ô username, password và button login
        //setup chrome driver
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
            Thread.sleep(7000);
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

            driver.findElement(By.xpath("//span[text()=\"PIM\"]")).click();
            System.out.println("Click PIM thanh cong");
            Thread.sleep(5000);
            driver.findElement(By.xpath("//a[contains(text(),\"Add Employee\")]")).click();
            Thread.sleep(5000);
            System.out.println("Click Add employee thanh cong");
            Thread.sleep(7000);

            String firstName = "Nguyen";
            String lastName = "Thao";
            driver.findElement(By.xpath("//input[@name=\"firstName\"]")).sendKeys(firstName);
            driver.findElement(By.xpath("//input[@name=\"lastName\"]")).sendKeys(lastName);
            WebElement empID = driver.findElement(By.xpath("//label[text()=\"Employee Id\"]/../following-sibling::div/input"));
            String empIDValue = empID.getAttribute("value").trim();

            driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();

            System.out.println("Tao thanh cong");
            Thread.sleep(15000);

            String actualFirstName = driver.findElement(By.name("firstName")).getAttribute("value").trim();
            String actualLastName = driver.findElement(By.name("lastName")).getAttribute("value").trim();
            String actualEmpId = driver.findElement(By.xpath("//label[text()=\"Employee Id\"]/../following-sibling::div/input")).getAttribute("value").trim();
            Thread.sleep(5000);
            boolean isNameCorrect = firstName.equals(actualFirstName) && lastName.equals(actualLastName);
            boolean isIdCorrect = empIDValue.equals(actualEmpId);
            Thread.sleep(5000);
            if(isIdCorrect && isNameCorrect) {
                System.out.println("Them nhan vien thanh cong va thong tin trung khop");
            } else{
                System.out.println("Thong tin sau khi luu khong trung khop");
            }
            Thread.sleep(6000);

            //nhap lieu
 //           userName.sendKeys("Admin");
 //           passWord.sendKeys("admin123");

 //           WebElement btnLogin = driver.findElement(By.xpath("//button[@type=\"submit\"]"));
 //                   btnLogin.click();
  //                  Thread.sleep(10000);

 //           System.out.println("Dang nhap thanh cong");

        } catch (InterruptedException e) {
            System.out.println("Loi: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
