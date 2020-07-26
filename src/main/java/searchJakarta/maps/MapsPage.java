package searchJakarta.maps;

import org.openqa.selenium.Point;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class MapsPage {
	
	public static MobileElement element = null;
	
	public static MobileElement searchTextBox(AndroidDriver<MobileElement> driver) {
		element = driver.findElementById("com.google.android.apps.maps:id/search_omnibox_text_box");
		return element;
	}
	
	public static void clickOnSearchTextBox(AndroidDriver<MobileElement> driver) {
		element = searchTextBox(driver);
		element.click();
	}
	
	public static MobileElement searchEditBox(AndroidDriver<MobileElement> driver) {
		element = driver.findElementById("com.google.android.apps.maps:id/search_omnibox_edit_text");
		return element;
	}
	
	public static void fillSearchEditBox(AndroidDriver<MobileElement> driver, String city) {
		element = searchEditBox(driver);
		element.sendKeys(city);
	}
	
	public static MobileElement searchSecondResult(AndroidDriver<MobileElement> driver) {
		element = driver.findElementByXPath("//android.widget.LinearLayout[@index=2]");
		return element;
	}
	
	public static void clickSecondOption(AndroidDriver<MobileElement> driver) {
		element = searchSecondResult(driver);
		element.click();
	}
	
	public static MobileElement choosenCity(AndroidDriver<MobileElement> driver) {
		element = driver.findElementByXPath("//android.widget.TextView[@index=0]");
		return element;	
	} 
	
	public static MobileElement getLocationPage(AndroidDriver<MobileElement> driver) {
		element = driver.findElementById("com.google.android.apps.maps:id/home_bottom_sheet_container");
		return element;
	}
	
	public static void zoomInLocation(AndroidDriver<MobileElement> driver, Point center) throws InterruptedException {
		ZoomHelper h = new ZoomHelper();
        Thread.sleep(2000);
        System.out.println("Zoom in the location");
        driver.perform(h.zoomIn(center, 500));
        System.out.println("Zoom in more");
        driver.perform(h.zoomIn(center, 1500));
    }

}
