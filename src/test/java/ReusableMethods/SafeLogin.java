package ReusableMethods;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import com.pages.loginPage;





public class SafeLogin {

	
	public static void logInUser(WebDriver driver) throws IOException{
		
		
		
		if(driver.getTitle().equalsIgnoreCase("Sign in to SAFE")){
			loginPage.getsafeUserIDEdt(driver).sendKeys(Capabilities.getPropertyValue("UserId"));
			loginPage.getsafePasswordEdt(driver).sendKeys(Capabilities.getPropertyValue("Password"));
			loginPage.getsafeLoginbtn(driver).click();	
			
		}	
	
}

}
