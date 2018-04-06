package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyManager {

	public String chromedriverPath = "";
	public String idInputGoogleSearch = "";
	public String idNextButton = "";
	public String cssAds = "";
	public String xpathUrlsSearch = "";
	public String cssRightSide = "";
	
	public void genarateProperty() {
		
		Properties prop = new Properties();
		InputStream input = null;
	
		try {
			input = new FileInputStream("src/parameters.properties");
			prop.load(input);
			
			chromedriverPath = prop.get("chromedriverPath").toString();
			idInputGoogleSearch = prop.get("idInputGoogleSearch").toString();
			idNextButton = prop.get("idNextButton").toString();
			cssAds = prop.get("cssAds").toString();
			xpathUrlsSearch = prop.get("xpathUrlsSearch").toString();
			cssRightSide = prop.get("cssRightSide").toString();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
