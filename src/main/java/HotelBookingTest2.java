import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HotelBookingTest2 {
	
	private static WebDriver driver;
	
	//used how and using annotations for specifying one of the location strategies
	@FindBy(how = How.XPATH, using = "//*[@id=\"Home\"]/div/div/ul/li/div/div[2]/aside[1]/nav/ul[1]/li[2]/a[1]")
    static WebElement hotelLink ;

    @FindBy(how = How.ID, using = "Tags")
    static WebElement localityTextBox;
    
    @FindBy(how = How.ID, using = "travellersOnhome")
    static WebElement travellerSelection;

    @FindBy(how = How.ID, using = "SearchHotelsButton")
    static WebElement searchButton;
	   
	@BeforeClass 
	public void setup()
	{	
		
		//moved chromedriver.exe from directory to src folder
		System.setProperty("webdriver.chrome.driver","src/chromedriver.exe");
		driver = new ChromeDriver();
	}
	
    @Test
    public void shouldBeAbleToSearchForHotels() {

    	driver.manage().window().maximize();
        driver.get("https://www.cleartrip.com/");
        
        waitFor(2000);
        
        //PageFactory will search for an element on the page that matches the field name of the ‘WebElement’ in the class.
        PageFactory.initElements(driver, HotelBookingTest2.class);
        hotelLink.click();
        
        waitFor(2000);
        localityTextBox.sendKeys("Indiranagar, Bangalore");
        waitFor(2000);
        
        List<WebElement> listOptions = driver.findElement(By.id("ui-id-1")).findElements(By.tagName("li"));
        listOptions.size();
        listOptions.get(1).click();
        
        waitFor(3000);
        new Select(travellerSelection).selectByVisibleText("1 room, 1 adult");
        waitFor(3000);
        //verify that result appears for the provided location search
        searchButton.click();
        
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