import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HotelBookingTest {    
    
    private static WebDriver driver;
    
	@BeforeClass 
	public void setup()
	{
		//moved chromedriver.exe from directory to src folder
		System.setProperty("webdriver.chrome.driver","src/chromedriver.exe");
		driver = new ChromeDriver();
	}

    @Test
    public void shouldBeAbleToSearchForHotels()
    {
    	
    	//to maximize the browser
    	driver.manage().window().maximize();
    	driver.get("https://www.cleartrip.com/hotels");
    	waitFor(3000);
    	driver.findElement(By.id("Tags")).clear();
    	driver.findElement(By.id("Tags")).sendKeys("Indiranagar, Bangalore");
    	waitFor(2000);
        List<WebElement> listOptions = driver.findElement(By.id("ui-id-1")).findElements(By.tagName("li"));
        listOptions.get(1).click();
        
    	driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div[1]/table/tbody/tr[5]/td[4]/a")).click();
    	waitFor(1000);
    	driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div[1]/table/tbody/tr[5]/td[6]/a")).click();
    	
    	
     	Select dropdown = new Select(driver.findElement(By.id("travellersOnhome")));
    	dropdown.selectByVisibleText("1 room, 1 adult");
    	waitFor(2000);
    	
    	//verify that result appears for the provided journey search
    	driver.findElement(By.id("SearchHotelsButton")).click();
    	
    	//long wait just to show the working results
    	waitFor(10000);

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
