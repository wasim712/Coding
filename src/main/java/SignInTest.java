import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SignInTest {

	private static WebDriver driver;

	@BeforeClass 
	public void setup() {
		
		//moved chromedriver.exe from directory to src folder
		System.setProperty("webdriver.chrome.driver","src/chromedriver.exe");
		 driver = new ChromeDriver();
	}
	
    @Test
    public void shouldThrowAnErrorIfSignInDetailsAreMissing() {

    	driver.manage().window().maximize();
        driver.get("https://www.cleartrip.com/");
        
        waitFor(2000);

        driver.findElement(By.linkText("Your trips")).click();
        waitFor(2000);
        driver.findElement(By.id("SignIn")).click();
        waitFor(2000);
        
        //For the error to occur on clicking signInButton it must first go into the particular frame.
        
        List<WebElement> iframes = driver.findElements(By.id("modal_window"));
        // print your number of frames
        System.out.println(iframes.size());

        for (WebElement iframe : iframes) 
        {
        	
        	//switching to frame
       	    driver.switchTo().frame(iframe);
       
        }
        
        waitFor(2000);
        driver.findElement(By.id("signInButton")).click();
        
        String errors1 = driver.findElement(By.id("errors1")).getText();
        Assert.assertTrue(errors1.contains("There were errors in your submission"));
        waitFor(5000);
        driver.quit();
    }

    private void waitFor(int durationInMilliSeconds) {
        try {
            Thread.sleep(durationInMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
