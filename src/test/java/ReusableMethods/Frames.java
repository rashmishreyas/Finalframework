package ReusableMethods;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;



public class Frames {


	//Switching to frame using Index
	public static void switchToFrameByIndex(int index, WebDriver driver){
		
			try{
					
					driver.switchTo().frame(index);
			}
			catch (NoSuchFrameException e){
				
			}
			catch (Exception e)
			{
				
			}
		}

	/*
	 * Switching to frame with Name
	 */
	public static void switchToFrameByName(String fname, WebDriver driver)
		{
			try{
				
				driver.switchTo().frame(fname);
			}
			catch (NoSuchFrameException e){
				
			}
			catch (Exception e){
				
			}
		}
	

	/*
	 * Switching to frame with ID
	 */
	
	public static void switchToFrameById(String id, WebDriver driver)
	{
		try{
			
			driver.switchTo().frame(id);
		}
		catch (NoSuchFrameException e){
			
		}
		catch (Exception e){
			
		}
	}
		
		/*
		 * Switching out of a frame
         */
          public static void switchToDefaultContent(WebDriver driver){
            
        	  try{
                    
                     driver.switchTo().defaultContent();
        	  }catch (NoSuchFrameException e){
                     
        	  }catch (Exception e){
                     
              }
   }	

}
