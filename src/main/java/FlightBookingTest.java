import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.List;

public class FlightBookingTest{
	
	private static WebDriver driver;

	@BeforeClass 
	public void setup() 
	{
		//moved chromedriver.exe from directory to src folder
		System.setProperty("webdriver.chrome.driver","src/chromedriver.exe");
		driver = new ChromeDriver();
	}
	 
    @Test
    public void testThatResultsAppearForAOneWayJourney()
    {
    	
    	//to maximize the browser
    	driver.manage().window().maximize();
        driver.get("https://www.cleartrip.com/");
        waitFor(2000);
        driver.findElement(By.id("OneWay")).click();
        driver.findElement(By.id("FromTag")).clear();
        driver.findElement(By.id("FromTag")).sendKeys("Bangalore");

        //wait for the auto complete options to appear for the origin

        waitFor(3000);
        List<WebElement> originOptions = driver.findElement(By.id("ui-id-1")).findElements(By.tagName("li"));
        originOptions.get(0).click();

        driver.findElement(By.id("ToTag")).clear();
        driver.findElement(By.id("ToTag")).sendKeys("Delhi");

        //wait for the auto complete options to appear for the destination

        waitFor(2000);
        
        //select the first item from the destination auto complete list
        
        List<WebElement> destinationOptions = driver.findElement(By.id("ui-id-2")).findElements(By.tagName("li"));
        destinationOptions.get(0).click();
        
        //Using below code for date picker will help to pick the future dates
        
        driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div[1]/table/tbody/tr[5]/td[7]/a")).click();
        
        //Using below code for date by send keys , Please change the dates as per the availability.
        //Date format (Day, Date Month, Year)
        
        driver.findElement(By.id("DepartDate")).sendKeys("Sun, 30 Sep, 2018");
        driver.findElement(By.id("DepartDate")).sendKeys(Keys.ENTER);
        
        //all fields filled in. Now click on search
        
        waitFor(3000);
        driver.findElement(By.id("SearchBtn")).click();

        waitFor(5000);
        //verify that result appears for the provided journey search
        Assert.assertTrue(isElementPresent(By.className("searchSummary")));
        
        //long wait just to show the working results
        waitFor(10000);

        //close the browser
        driver.close();

    }

    private void waitFor(int durationInMilliSeconds) {
        try {
            Thread.sleep(durationInMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
    private boolean isElementPresent(By by) {
        try 
        {
        	
        	driver.findElement(by);
            return true;
        } 
        catch (NoSuchElementException e) 
        {
            return false;
        }
    }
}

    
