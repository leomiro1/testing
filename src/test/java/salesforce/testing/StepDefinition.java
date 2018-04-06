package salesforce.testing;

import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import util.WebDriverUtils;
import util.PropertyManager;

public class StepDefinition {

	WebDriver driver;
	WebDriverUtils util = new WebDriverUtils(driver);
	PropertyManager property;
	
	
	
	@Before
	public void testSetUp() {
		// create a property var that it will be used to manage parameters
		property = new PropertyManager();
		property.genarateProperty();
		// create an instance of Webdriver
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
	    capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
    	System.setProperty("webdriver.chrome.driver", property.chromedriverPath);
    	driver = new ChromeDriver();
	}
	
	@After
	public void testShutDown() {
		driver.quit();		
	}
	
	
		
	
	@Given("^I am on the Google page$")
	public void navigateToGooglePage() throws Throwable {
		driver.navigate().to("http://www.google.com");
	}

	@When("^I enter \"(.*?)\" on the input search$")
	public void enterInputSearch(String inputSearch) throws Throwable {
		driver.findElement(By.id(property.idInputGoogleSearch)).sendKeys(inputSearch);
	}

	@And("^I start the search$")
	public void startSearch() throws Throwable {
		driver.findElement(By.id(property.idInputGoogleSearch)).sendKeys(Keys.ENTER);
		util.wait(5);
	}

	
	
	
	@Then("^I need to show the ads-urls in the console$")
	public void showAdsUrlsInConsole() throws Throwable {
	    
		// listAds is a list which contains the content of the ads section
		List<WebElement> listAds = driver.findElements(By.cssSelector(property.cssAds));
		
		// if list is empty then there are no ads to show, otherwise I show them
		if (listAds.size() == 0) {
			System.out.println("\n");
			System.out.println("no se hallaron anuncios");
		}	
		else {
			// Listing the ads
			System.out.println("Anuncios:");
			System.out.println("*********");
			
			for(int i=0;i<listAds.size();i++) {
				String elementText = listAds.get(i).getText();
				if (elementText.toLowerCase().toString().contains("anuncio") || elementText.toLowerCase().toString().contains("ad"))
					System.out.println(elementText.substring(7)); 
			}
		}
	}
	
	
	@Then("^I need to show in the console the argentinian urls of the first \"(.*?)\" pages$")
	public void showArgentinianUrlsInConsole(String pages) throws Throwable {
	    
		// cantPages represents the quantity of pages that the search covers
		int cantPages = Integer.parseInt(pages);
		
		// urls will contain the urls of the search
		List<WebElement> urls = driver.findElements(By.xpath(property.xpathUrlsSearch));
		
    	// Listing the argentinian urls
		System.out.println("\n");
		System.out.println("URLs argentinas:");
    	System.out.println("****************");
		
		// I look on the amount of pages specified by cantPages and put the content of the urls in the url list
	    for (int i=0; i< cantPages;i++) {
	    	
	    	urls = driver.findElements(By.xpath(property.xpathUrlsSearch));
	    	
	    	// I go through the url list and print in console the argentinian urls
	    	for(WebElement url : urls)
	        	{
	            	String text = url.getAttribute("href");
	            	if (text.toLowerCase().toString().contains(".ar"))
	            		System.out.println("página " + (i+1) + " de la búsqueda - " + text); 
	        	}
	    	
	    	// Go to the next page
	    	driver.findElement(By.id(property.idNextButton)).click();
	    }
	}
	
	
	
	@Then("^I need to show in the console the positioning of \"(.*?)\"$")
	public void showPositioningInConsole(String keyWord) throws Throwable {

		/******** ADS *********/
		
		// listAds is a list which contains the content of the ads section
		List<WebElement> listAds = driver.findElements(By.cssSelector(property.cssAds));
		
		// if list is empty then there are no ads to show, otherwise I show them
		if (listAds.size() == 0) {
			System.out.println("\n");
			System.out.println("no se hallaron anuncios con la keyWord " + keyWord + "\n");
		}	
		else {
			// Listing the ads
			System.out.println("\n");
			System.out.println("Anuncios posicionados:");
			System.out.println("**********************");
			
			for(int i=0;i<listAds.size();i++) {
				String elementText = listAds.get(i).getText();
				if (elementText.toLowerCase().toString().contains(keyWord.toLowerCase()))
					System.out.println(elementText.substring(7)); 
			}
		}
		
		/******** SIDE *********/
		
		// listSideUrl is a list which contains the content of the title of the right side of search
		List<WebElement> listSideUrl = driver.findElements(By.cssSelector(property.cssRightSide));
		
		// if list is empty then there are no side urls to show, otherwise I show them
		if (listSideUrl.size() == 0) {
			System.out.println("\n");
			System.out.println("no se hallaron anuncios laterales con la keyword " + keyWord + "\n");
		}	
		else {
			// Listing the positioning keywords
			System.out.println("\n");
			System.out.println("URLs posicionadas lateralmente:");
			System.out.println("*******************************");
			
			for(int i=0;i<listSideUrl.size();i++) {
				String elementText = listSideUrl.get(i).getText();
				if (elementText.toLowerCase().toString().contains(keyWord.toLowerCase()))
					System.out.println(elementText); 
			}

		}
		
		/******** ORGANIC *********/
		
		// urls will contain the organic urls of the search
		List<WebElement> urls = driver.findElements(By.xpath(property.xpathUrlsSearch));
		
		// if list is empty then there are no side urls to show, otherwise I show them
		if (urls.size() == 0) {
			System.out.println("\n");
			System.out.println("no se hallaron urls orgánicas con la keyword " + keyWord + "\n");
		}	
		else {
			// Listing the positioning keywords
			System.out.println("\n");
			System.out.println("URLs orgánicas:");
			System.out.println("***************");
			
			int i = 1;
			
	    	// I go through the url list and print in console the organic urls
	    	for(WebElement url : urls) {
	    		
            	String text = url.getAttribute("href");
            	if (text.toLowerCase().toString().contains(keyWord.toLowerCase()))
            		System.out.println("link en posición " + i + " de la búsqueda: " + text);
            	i++;
        	}

		}
		
	}
	
}
