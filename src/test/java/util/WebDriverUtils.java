package util;

import org.openqa.selenium.WebDriver;

public class WebDriverUtils {

	WebDriver driver;
	
	// constructor
	public WebDriverUtils(WebDriver driver) {
		this.driver = driver;
	}
	
	// wait for a set amount of time
	// and I should be able to set the time I want
	public void wait (int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
}
