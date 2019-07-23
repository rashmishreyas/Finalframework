package ReusableMethods;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.pages.HomePage;






public class ServiceNowUtils {
	
public static void navigateToModuleName(WebDriver driver, String moduleName) throws InterruptedException {
		
	
	Thread.sleep(3000);
		
		TextBoxes.enterTextValue(HomePage.getfilterEdt(driver), moduleName, "Filter Edit box for searching : "+moduleName);
		//WaitUtils.waitForXpathPresent(driver,"//a[text()='Create New']");
		Thread.sleep(3000);
		
		HomePage.getCreateNewBtn2(driver).click();
		

		Frames.switchToFrameById("gsft_main", driver);	
		
	}
public static void navigateToModuleName1(WebDriver driver, String moduleName) throws InterruptedException {
	
	WaitUtils.waitForIdPresent(driver, "filter");
	//WebDriverWait wait = new WebDriverWait(driver, 30); 
	//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("filter")));
	TextBoxes.enterTextValue(HomePage.getfilterEdt(driver), moduleName, "Filter Edit box for searching : "+moduleName);
	//WaitUtils.waitForXpathPresent(driver,"//a[text()='Create New']");
	WaitUtils.waitForPageToLoad(driver, 30);
	
	/*create new for incident from intake*/
	HomePage.getCreateNewBtn1(driver).click();

	Frames.switchToFrameById("gsft_main", driver);	
	
}
public static void navigateToAllQueueForDesiredModule(WebDriver driver, String moduleName){
	WaitUtils.waitForPageToLoad(driver, 10);
	WaitUtils.waitForIdPresent(driver, "filter");
	HomePage.getfilterEdt(driver).sendKeys(moduleName);
	WaitUtils.waitForPageToLoad(driver, 10);
	//HomePage.getAllLnk(driver).click();
	WebElement element = driver.findElement(By.linkText("All"));
	Actions actions = new Actions(driver);
	actions.moveToElement(element).click().build().perform();
	Frames.switchToFrameById("gsft_main", driver);
	WaitUtils.waitForPageToLoad(driver, 10);
}

}

