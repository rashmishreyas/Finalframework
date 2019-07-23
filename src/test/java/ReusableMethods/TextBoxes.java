package ReusableMethods;

import org.openqa.selenium.WebElement;


public class TextBoxes {
	 public static void enterTextValue(WebElement element,String textData,String fieldName)
	    {
	           try
	           {
	                  
	                  element.sendKeys(textData);
	                  
	           }
	           catch(Exception e)
	           {
	                System.out.println(e);
	           }
	    }

}
