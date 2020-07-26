package searchJakarta.maps;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

public class TestSearchJakarta {
	
		public static URL url;
		public static DesiredCapabilities capabilities;
		public static AndroidDriver<MobileElement> driver;
		public static String targetCity = "Jakarta";
		public static String selectedCityInMap = "Jakarta Selatan, South Jakarta City, Jakarta, Indonesia";
		WebDriverWait wait = null;
 	
	  @BeforeClass
	  public void setupAppium() throws MalformedURLException {
	 
	    final String URL_STRING = "http://0.0.0.0:4723/wd/hub";
	    
		capabilities=new DesiredCapabilities();				
		capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"emulator-5554");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,"10.0");
		capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,"com.google.android.apps.maps");
		capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,"com.google.android.maps.MapsActivity");
		
		url = null;
		
		try {
			url = new URL(URL_STRING);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		
		driver=new AndroidDriver<MobileElement>(url,capabilities);
		wait=new WebDriverWait(driver,30);
	  }
	
	  @Test
	  public void findJakarta() throws InterruptedException {
		 wait.until(ExpectedConditions.elementToBeClickable(MapsPage.searchTextBox(driver)));
		 MapsPage.clickOnSearchTextBox(driver);
		 
		 wait.until(ExpectedConditions.elementToBeClickable(MapsPage.searchEditBox(driver)));
		 MapsPage.fillSearchEditBox(driver, targetCity);
		 
		 Thread.sleep(3000);
		 wait.until(ExpectedConditions.elementToBeClickable(MapsPage.searchSecondResult(driver)));
		 MapsPage.clickSecondOption(driver);
		 
		 Assert.assertEquals(MapsPage.choosenCity(driver).getText(), selectedCityInMap, "The selected city is not as expected.");
	  }
	  
	  @Test
	  public void zoomLocation() throws InterruptedException {
		 // get center point and zoom
		 Rectangle map = MapsPage.getLocationPage(driver).getRect();
		 Point center = new Point(map.x + map.getWidth() / 2, map.y + map.getHeight() / 2);
		 MapsPage.zoomInLocation(driver, center);
		 
		 Thread.sleep(6000);
	  }
	  
	  @AfterClass
	  public void quit() {
		  driver.quit();
	  }
 
}
