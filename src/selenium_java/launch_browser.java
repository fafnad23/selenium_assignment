package selenium_java;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;


public class launch_browser {
	
	public static WebDriver driver = null;

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver","E:\\BRAINSTATION\\selenium_java\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
        
		//PART 1
        getToHome();
        
        //PART 2
        createAccount("daphne12@gmail.com", "Daphne", "Bridgerton", "daphne");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
        createAccount("simon12@gmail.com", "Simon", "Hastings", "simon");
        
        //PART 3
        loginAccount("daphne12@gmail.com", "daphne");
        
        //PART 4
        addCasualDressToCart();
        
        //PART 5
        addBlueTshirtToCart();
        
        //PART 6
        proceedToCheckout();
        
        //Run the whole thing for another user
        loginAccount("simon12@gmail.com", "simon");
        addCasualDressToCart();
        addBlueTshirtToCart();
        proceedToCheckout();
	}
	static void getToHome() {
    	driver.get("http://automationpractice.com/index.php");
    	driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
    }
	static void createAccount(String mail, String firstname, String lastname, String password) {
    	driver.findElement(By.className("login")).click();
        
    	//Email Input
        driver.findElement(By.id("email_create")).sendKeys(mail);
        driver.findElement(By.id("email_create")).click();
        driver.findElement(By.id("SubmitCreate")).click();
        
        //Form Fill-up
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(8000));
        driver.findElement(By.id("id_gender1")).click();
        driver.findElement(By.id("customer_firstname")).sendKeys(firstname);
        driver.findElement(By.id("customer_lastname")).sendKeys(lastname);
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("passwd")).sendKeys(password);
        driver.findElement(By.id("firstname")).sendKeys(firstname);
        driver.findElement(By.id("lastname")).sendKeys(lastname);
        driver.findElement(By.id("address1")).sendKeys("8th Floor, 2 Bir Uttam AK Khandakar Road, Dhaka 1212");
        driver.findElement(By.id("city")).sendKeys("Dhaka");
        new Select(driver.findElement(By.id("id_state"))).selectByVisibleText("Montana");
        driver.findElement(By.id("postcode")).sendKeys("12121");
        new Select(driver.findElement(By.id("id_country"))).selectByVisibleText("United States");
        driver.findElement(By.id("phone_mobile")).sendKeys("01700000000");
        driver.findElement(By.id("alias")).sendKeys("Dhaka 1212");
        driver.findElement(By.id("submitAccount")).click();
        
        //Logout
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
        driver.findElement(By.className("logout")).click();
        getToHome();
    }
	static void loginAccount(String mail, String password) {
        driver.findElement(By.className("login")).click();
        
        driver.findElement(By.id("email")).sendKeys(mail);
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("passwd")).sendKeys(password);
        driver.findElement(By.id("SubmitLogin")).click();
        
        getToHome();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
    }
	static void addCasualDressToCart() {
    	Actions actions = new Actions(driver);
    	WebElement dress = driver.findElement(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[2]/a"));
    	actions.moveToElement(dress).perform();
    	driver.findElement(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[2]/ul/li[1]/a")).click();
        
    	//Locating element by link text and store in variable "Element"        		
        WebElement Element = driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li/div/div[1]/div/a[1]"));

        // Scrolling down the page till the element is found
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", Element);
    	
        //Adding dress to cart
    	Actions action = new Actions(driver);
    	WebElement casual_dress = driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li/div/div[1]/div/a[1]"));
    	action.moveToElement(casual_dress).build().perform();
    	driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li/div/div[2]/div[2]/a[1]")).click();
    	
    	getToHome();
    }
	static void addBlueTshirtToCart() {
    	driver.findElement(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[3]/a")).click();
    	driver.findElement(By.id("layered_id_attribute_group_14")).click();
    	driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
    	
    	//Locating element by link text and store in variable "Element"        		
        WebElement Element1 = driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li/div/div[1]/div/a[1]/img"));

        // Scrolling down the page till the element is found
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", Element1);
        
        //Adding tshirt to cart
        Actions action2 = new Actions(driver);
    	WebElement tshirt = driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li/div/div[1]/div/a[1]/img"));
    	action2.moveToElement(tshirt).build().perform();
    	driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li/div/div[2]/div[2]/a[1]")).click();
    }
	static void proceedToCheckout() {
    	driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a")).click();

        //Locating element by link text and store in variable "Element"
        WebElement Element = driver.findElement(By.xpath("//*[@id=\"center_column\"]/p[2]/a[1]"));

        // Scrolling down the page till the element is found
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", Element);
        driver.findElement(By.xpath("//*[@id=\"center_column\"]/p[2]/a[1]")).click();
        driver.findElement(By.xpath("//*[@id=\"center_column\"]/form/p/button")).click();
        driver.findElement(By.xpath("//*[@id=\"uniform-cgv\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"form\"]/p/button")).click();
        driver.findElement(By.xpath("//*[@id=\"HOOK_PAYMENT\"]/div[2]/div/p/a")).click();
        driver.findElement(By.xpath("//*[@id=\"cart_navigation\"]/button")).click();
        
        //Order complete
        //Logout
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
        driver.findElement(By.className("logout")).click();
        getToHome();
    }
        
      
    	
}


	


