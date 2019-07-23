package ReusableMethods;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.pages.IncidentPage;



import com.pages.IncidentTask;
import com.pages.IntakePage;





public class IncidentReusables {
	static String assignmentGroup2 = null;
	static String openedByGrouptaskValue = null;
	static String Assignmentgroup1 =null;
	static String incidentState = null;
	static String date = null;
	static String incidenttaskState = null;
	static String businessServceValue = null;
	static String sourcecivalue = null;
	static String incidentmangroup = null;
	static String assignmentGroupValue = null;
	static String openedByGroupValue = null;
	static String userImpact = null;
	static String shortDescription = null;
	static String description = null;
	static String reasonForIncidentManager = null;
	static String incidentNumber = null;
	static String copiedincidentNumber = null;
	static String incMgrReqdValue = null;
	static String incNumber = null;
	static String assignedTo = null;
	static String incidentStateWIP = null;
	static String configurationItem = null;
	static String causeCode = null;
	static String subCauseCode = null;
	static String mitigationSolutionSteps = null;
	static String incidentStateResolved = null;
	static String reasonForCancellation = null;
	static String incidentStateCancelled = null;
	static String worknotes = null;
	static String test = null;
	static String Latestupdate = null;
	static String location = null;
	static String businessServiceCatalog = null;
	static String situation = null;
	static String openedbygroup = null;
	
	static String incidenttasknum = null;
	private static WebElement element;
	static String problemId=null;
	static String additionalcomments = null;
	static String source=null;
	static String ManTestCase = null;
	static String changeId=null;
	static String reasonForChange = null;
	static String ChildIncnum = null;
	static WebElement incidenttab=null;
	static String SourceCI = null;
	static String Incidentowner = null;
	static String Incidentmanagergroup = null;
	static String Incidentmanager = null;
	static String Accountablebusinessunit = null;
	static String Affectedbusinessunit = null;
	static String incidentalertnumber = null;
	static String requestedby = null;
	
	
	public static String createstandaloneincident1(WebDriver driver, int sNum, int cellNum) throws Exception
	{
		
		
	businessServceValue=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 5);
	System.out.println(businessServceValue);
	assignmentGroupValue=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 6);
	openedByGroupValue=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 8);
	userImpact=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 9);
	shortDescription=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 10);
	description=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 11);			

	WaitUtils.waitForPageToLoad(driver, 60);
	WaitUtils.waitForIdPresent(driver, "sys_readonly.incident.number");
	incidentNumber=driver.findElement(By.xpath("//input[@id='sys_readonly.incident.number']")).getAttribute("value");
	ExcelUtis.writeDataIntoCell("Incident_Management_TestData.xlsx","Smoke_Suite", sNum, cellNum, incidentNumber);
	ExcelUtis.writeDataIntoCell("Incident_Management_TestData.xlsx","Smoke_Suite",1, 2, incidentNumber);
	

	Thread.sleep(3000);
	driver.findElement(By.id("sys_display.incident.u_business_service")).sendKeys("170 Systems (Markview)");
	



	//Assignment group value			
	WaitUtils.waitForIdPresent(driver, "sys_display.incident.assignment_group");
	IncidentPage.getAssignmentGroupEdt(driver).sendKeys(assignmentGroupValue);

	

	//OpenedByGroup value			
	WaitUtils.waitForIdPresent(driver, "sys_display.incident.u_opened_by_group");
	IncidentPage.getOpenedByGroupEdt(driver).sendKeys(openedByGroupValue);

	
			
	//User Impact Field			
	WaitUtils.waitForIdPresent(driver, "incident.u_user_impact");
	DropDowns.selectDropdownByVisibleText(IncidentPage.getUserImpactDropdown(driver), userImpact, "User impact");
	
	//Short Description field			
	WaitUtils.waitForIdPresent(driver, "incident.short_description");
	IncidentPage.getShortDescriptionEdt(driver).sendKeys(shortDescription+ incidentNumber);

	//Description field
	WaitUtils.waitForIdPresent(driver, "incident.description");
	IncidentPage.getDescriptionEdt(driver).sendKeys(description+ incidentNumber);

	Thread.sleep(2000);			

	//Click on Submit button
	WaitUtils.waitForXpathPresent(driver, "//button[text()='Submit']");
	driver.findElement(By.xpath("//button[text()='Submit']")).click();

	System.out.println("Final"+incidentNumber);
	return incidentNumber;
	}
	public static void verifyIncidentCreation(WebDriver driver, String incNumber) throws Exception
	{
		WaitUtils.waitForPageToLoad(driver, 30);
		
        if(IncidentPage.getSearchDropDown(driver).getAttribute("value").equalsIgnoreCase("number"))
        {
        	Thread.sleep(2000);
            WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']/label[text()='Search']/following-sibling::input");
            TextBoxes.enterTextValue(IncidentPage.getSearchIncidentEdt(driver),incNumber,"Search Incident");
            IncidentPage.getSearchIncidentEdt(driver).sendKeys(Keys.ENTER);
           Thread.sleep(5000);
            WaitUtils.waitForPageToLoad(driver, 30);
            incidentState=IncidentPage.getIncidentStatusfromQueue(driver, incNumber).getText();
            
            if(incidentState.equalsIgnoreCase("New"))
            {
         	   Assert.assertEquals(incidentState, "New");
         	   
         	
         	   ExcelUtis.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", 1, 4, "Passed");
         	 
         }else{
        	   	
         	  
         	   ExcelUtis.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", 1, 4, "Failed");
         	  
         	   Assert.assertEquals(incidentState, "New");
         	 }  	       
        }      
        else if(!IncidentPage.getSearchDropDown(driver).getAttribute("value").equalsIgnoreCase("number")){
        	   WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']//select");
        	   DropDowns.selectDropdownByVisibleText(IncidentPage.getSearchDropDown(driver), "Number", "Search Drop Down");
        	   TextBoxes.enterTextValue(IncidentPage.getSearchIncidentEdt(driver), incNumber, "Search Incident ");
        	   
        	   IncidentPage.getSearchIncidentEdt(driver).sendKeys(Keys.ENTER);	         
        	   if(incidentState.equalsIgnoreCase("New"))
        	   {
	         	Assert.assertEquals(incidentState, "New");
	         	
	            ExcelUtis.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", 1, 4, "Passed"); 
	           
        	   }else{        	    
			   
			    ExcelUtis.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", 1, 4, "Failed");
			    
			    Assert.assertEquals(incidentState, "New");
		       }      
	  		}
		}
	
	public static String createmanagedIncident(WebDriver driver, int sNum, int cellNum) throws Exception
	{
		
		try{
		WaitUtils.waitForPageToLoad(driver, 50);
		
		//Reading values from Excel file for Managed Incident Test Case
		businessServceValue=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 5);
		assignmentGroupValue=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 6);
		openedByGroupValue=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 8);
		userImpact=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 2, 9);
		shortDescription=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 10);
		description=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 11);			
		
		reasonForIncidentManager=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 2, 12);			
		
		//Store the Incident number in a variable
		WaitUtils.waitForPageToLoad(driver, 60);
		WaitUtils.waitForIdPresent(driver, "sys_readonly.incident.number");
		incidentNumber=driver.findElement(By.xpath("//input[@id='sys_readonly.incident.number']")).getAttribute("value");
		
		ExcelUtis.writeDataIntoCell("Incident_Management_TestData.xlsx","Smoke_Suite", sNum, cellNum, incidentNumber);
		//ExtentReport.reportLog(LogStatus.INFO, "Incident number captured: "+incidentNumber);
	
		
		//Business Service Field
	Thread.sleep(3000);
		//WaitUtils.waitForXpathPresent(driver, "//input[@id='sys_display.incident.u_business_service']");
		//IncidentPage.getBusinessServiceEdt(driver).sendKeys(businessServceValue);
	driver.findElement(By.id("sys_display.incident.u_business_service")).sendKeys(businessServceValue);
		
		Thread.sleep(6000);
		IncidentPage.getBusinessServiceEdt(driver).sendKeys(Keys.ENTER);
		
		//Assignment group value
		WebDriverWait wait = new WebDriverWait(driver,20);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='sys_display.incident.u_business_service']")));		
		IncidentPage.getBusinessServiceEdt(driver).sendKeys(Keys.ENTER);
		
		//WaitUtils.waitForXpathPresent(driver, "//input[@id='sys_display.incident.u_business_service']");
		//driver.findElement(By.xpath("//input[@id='sys_display.incident.u_business_service']")).sendKeys(businessServceValue);
		WaitUtils.waitForIdPresent(driver, "sys_display.incident.assignment_group");
		IncidentPage.getAssignmentGroupEdt(driver).sendKeys(assignmentGroupValue);
		
		//driver.findElement(By.xpath("//input[@id='sys_display.incident.u_business_service']")).sendKeys(businessServceValue);
	
		//Thread.sleep(6000);
		//IncidentPage.getBusinessServiceEdt(driver).sendKeys(Keys.ENTER);
				
		//OpenedByGroup value			
		WaitUtils.waitForIdPresent(driver, "sys_display.incident.u_opened_by_group");
		IncidentPage.getOpenedByGroupEdt(driver).sendKeys(openedByGroupValue);
			
		//User Impact Field			
		WaitUtils.waitForIdPresent(driver, "incident.u_user_impact");
		DropDowns.selectDropdownByVisibleText(IncidentPage.getUserImpactDropdown(driver), userImpact, "User impact");
		
		//WebElement tocheckbox = driver.findElement(By.cssSelector("input[id = 'ni.incident.u_incident_manager_required']"));
		WebElement myelement = driver.findElement(By.xpath("//span[@class='input-group-checkbox']/label[@id='label.ni.incident.u_incident_manager_required'] "));
		JavascriptExecutor jse2 = (JavascriptExecutor)driver;
		jse2.executeScript("arguments[0].click();", myelement); 
		//myelement.click();
			DropDowns.selectDropdownByVisibleText(IncidentPage.getReasonForIncidentManagerDropdown(driver), reasonForIncidentManager, "Reason for Incident Manager");
		//Short Description field			
		WaitUtils.waitForIdPresent(driver, "incident.short_description");
		IncidentPage.getShortDescriptionEdt(driver).sendKeys(shortDescription+ incidentNumber);
		
		//Description field
		WaitUtils.waitForIdPresent(driver, "incident.description");
		IncidentPage.getDescriptionEdt(driver).sendKeys(description+ incidentNumber);
		
		Thread.sleep(5000);
		
		//Click on Submit button
		WaitUtils.waitForXpathPresent(driver, "//button[text()='Submit']");
		driver.findElement(By.xpath("//button[text()='Submit']")).click();	
		try {
	        Alert alert = driver.switchTo().alert();
	        String alertText = alert.getText();
	    
	       // ExtentReport.reportLog(LogStatus.FAIL, "Alert message: " + alertText);
	        Assert.fail("Unexpected alert !!!! ");
	        } 
		catch(Exception e){
			e.getMessage();
	    }
		
	}

	catch (UnhandledAlertException f) {
	    try {
	        Alert alert = driver.switchTo().alert();
	        String alertText = alert.getText();
	        System.out.println("Alert data: " + alertText);
	         Assert.fail("Unhandled alert");
	        
	        } 
	     
	    	 catch(Exception e){
	 			e.getMessage();
	    	 }}	
		System.out.println("final"+incidentNumber);
		return incidentNumber;
		}
	//verify managed incident creation
			public static void verifymanagedIncidentCreation(WebDriver driver, String incNumber) throws Exception
			{
				WaitUtils.waitForPageToLoad(driver, 30);
				WaitUtils.waitForTitleIs(driver, "Incidents | ServiceNow");
		        if(IncidentPage.getSearchDropDown(driver).getAttribute("value").equalsIgnoreCase("number"))
		        {
		        	Thread.sleep(3000);
		            WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']/label[text()='Search']/following-sibling::input");
		            TextBoxes.enterTextValue(IncidentPage.getSearchIncidentEdt(driver),incNumber,"Search Incident");
		            IncidentPage.getSearchIncidentEdt(driver).sendKeys(Keys.ENTER);
		            //Thread.sleep(3000);
		            WaitUtils.waitForPageToLoad(driver, 30);
		            incidentState=IncidentPage.getIncidentStatusfromQueue(driver, incNumber).getText();
		            
		            if(incidentState.equalsIgnoreCase("New"))
		            {
		         	   Assert.assertEquals(incidentState, "New");
		         	 
		         	   ExcelUtis.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", 2, 4, "Passed");
		         	 
		         }else{
		        	   	
		         	  
		         	   ExcelUtis.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", 2, 4, "Failed");
		         	 
		         	   Assert.assertEquals(incidentState, "New");
		         	 }  	       
		        }      
		        else if(!IncidentPage.getSearchDropDown(driver).getAttribute("value").equalsIgnoreCase("number")){
		        	   WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']//select");
		        	   DropDowns.selectDropdownByVisibleText(IncidentPage.getSearchDropDown(driver), "Number", "Search Drop Down");
		        	   TextBoxes.enterTextValue(IncidentPage.getSearchIncidentEdt(driver), incNumber, "Search Incident ");
		        	 
		        	   IncidentPage.getSearchIncidentEdt(driver).sendKeys(Keys.ENTER);	         
		        	   if(incidentState.equalsIgnoreCase("New"))
		        	   {
			         	Assert.assertEquals(incidentState, "New");
			         	
			            ExcelUtis.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", 2, 4, "Passed"); 
			            
		        	   }else{        	    
					  
					    ExcelUtis.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", 1, 4, "Failed");
					   
					    Assert.assertEquals(incidentState, "New");
				       }      
			  		}
				}
			public static void Captureerrormessagenew1(WebDriver driver, String errormessage) throws Exception
			{
				

					WaitUtils.waitForPageToLoad(driver, 30);
					
					//Search for the Incident Ticket
					incNumber=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 2);
					assignedTo=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 5, 7);
					worknotes = ExcelUtis.getData("Incident_Management_TestData.xlsx" , "Smoke_Suite", 5, 18);
					ExcelUtis.writeDataIntoCell("Incident_Management_TestData.xlsx","Smoke_Suite", 5, 2, incNumber);
					
					WaitUtils.waitForTitleIs(driver, "Incidents | ServiceNow");			
					if(!IncidentPage.getSearchDropDown(driver).getAttribute("value").equalsIgnoreCase("number")){
						 WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']//select");
				         DropDowns.selectDropdownByVisibleText(IncidentPage.getSearchDropDown(driver), "Number", "Search Drop Down");
					}
			        IncidentReusables.searchIncidentTicketFromQueue(driver, incNumber);
			        incidentState=IncidentPage.getIncidentStatusfromQueue(driver, incNumber).getText();
			        if (incidentState.equalsIgnoreCase("New")) {
			        	Thread.sleep(3000);
			        	IncidentPage.getIncidentNumberfromQueue(driver, incNumber).click();
			        	WaitUtils.waitForPageToLoad(driver, 30);
			        	
			        	 
			        	//Update the status from New to Work in Progress
			        	
			        	DropDowns.selectDropdownByVisibleText(IncidentPage.getStateDropdown(driver), "Work in Progress", "State");
				Thread.sleep(3000);
			        	WaitUtils.waitForXpathPresent(driver, "//button[text()='Save']");
				driver.findElement(By.xpath("//button[text()='Save']")).click();
				WaitUtils.waitForPageToLoad(driver, 30);
				
				
					
				boolean isdisplayed = driver.findElement(By.xpath("//span[@class='outputmsg_text']//parent::div[1]")).isDisplayed();
					
					if(isdisplayed)
					{
						System.out.println("element is displayed");
					}
					else
					{
						System.out.println("element not displayed");
					}	
				
				
					
			}

			
			}

public static void searchIncidentTicketFromQueue(WebDriver driver, String ticketNumber) throws Exception{
	try{
	Thread.sleep(2000);
	WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']/label[text()='Search']/following-sibling::input");
	TextBoxes.enterTextValue(IncidentPage.getIncidentSearchTicketEdt(driver), ticketNumber, "Search Ticket");
	IncidentPage.getIncidentSearchTicketEdt(driver).sendKeys(Keys.ENTER);
	
}
	
	catch (Exception e) {
	
	}

}
//Resolve the Incident ticket				    

		public static void resolveIncident(WebDriver driver) throws Exception
			{
			try{
				WaitUtils.waitForPageToLoad(driver, 30);
				
				//Search for the Incident Ticket
				incNumber=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 2);
				assignedTo=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 5, 7);
				worknotes = ExcelUtis.getData("Incident_Management_TestData.xlsx" , "Smoke_Suite", 5, 18);
				ExcelUtis.writeDataIntoCell("Incident_Management_TestData.xlsx","Smoke_Suite", 5, 2, incNumber);
					
				if(!IncidentPage.getSearchDropDown(driver).getAttribute("value").equalsIgnoreCase("number")){
					 WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']//select");
			         DropDowns.selectDropdownByVisibleText(IncidentPage.getSearchDropDown(driver), "Number", "Search Drop Down");
				}
		        IncidentReusables.searchIncidentTicketFromQueue(driver, incNumber);
		        incidentState=IncidentPage.getIncidentStatusfromQueue(driver, incNumber).getText();
		        System.out.println(incidentState);
		        if (incidentState.equalsIgnoreCase("New")) {
		        	Thread.sleep(3000);
		        	IncidentPage.getIncidentNumberfromQueue(driver, incNumber).click();
		        	WaitUtils.waitForPageToLoad(driver, 30);
		        	
		        	//Update the status from New to Work in Progress
		        	DropDowns.selectDropdownByVisibleText(IncidentPage.getStateDropdown(driver), "Work in Progress", "State");
		        	IncidentPage.getAssignedToEdt(driver).sendKeys(assignedTo);
		        	IncidentPage.getAssignedToEdt(driver).sendKeys(Keys.ENTER);
		        	Thread.sleep(2000);
		        	WaitUtils.waitForXpathPresent(driver, "//button[text()='Save']");
					driver.findElement(By.xpath("//button[text()='Save']")).click();
					Thread.sleep(2000);
		        	try {
				        Alert alert = driver.switchTo().alert();
				        String alertText = alert.getText();
				    
				        Assert.fail("Unexpected alert !!!! ");
				        } 
		        	catch(Exception e){
		    			e.getMessage();}
		        
		        	 
		        	
		        	  
						DropDowns.selectDropdownByVisibleText(IncidentPage.getStateDropdown(driver), "Resolved", "State");					
						configurationItem=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 3, 13);
						//DropDowns.selectDropdownByVisibleText(IncidentPage.ConfigurationItem(driver), "QA Test Runner", "On hold type");
						causeCode=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 3, 14);
						subCauseCode=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 3, 15);
						mitigationSolutionSteps=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 3, 16);
						//IncidentPage.getConfigurationItemEdt(driver).sendKeys(configurationItem);
						WaitUtils.waitForIdPresent(driver, "sys_display.incident.cmdb_ci");
						driver.findElement(By.id("sys_display.incident.cmdb_ci")).sendKeys(configurationItem);
						Thread.sleep(5000);
						IncidentPage.getConfigurationItemEdt(driver).sendKeys(Keys.ENTER);
						
						//Fill closure tab fields
						//WaitUtils.waitForXpathPresent(driver, "//span[contains(text(),'Closure')");
						
						IncidentPage.getClosureTab(driver).click();
						
						DropDowns.selectDropdownByVisibleText(IncidentPage.getCauseCodeDropdown(driver), causeCode, "Cause Code");
					
						DropDowns.selectDropdownByVisibleText(IncidentPage.getSubCauseCodeDropdown(driver), subCauseCode, "Sub Cause Code");
						//Thread.sleep(4000);
						
						TextBoxes.enterTextValue(IncidentPage.getMitigationAndSolutionStepsEdt(driver), mitigationSolutionSteps + incNumber, "Mitigation and Solution Steps");
						
						WaitUtils.waitForXpathPresent(driver, "//button[text()='Save']");
						driver.findElement(By.xpath("//button[text()='Save']")).click();
						try {
					        Alert alert = driver.switchTo().alert();
					        String alertText = alert.getText();
					       
					        Assert.fail("Unexpected alert !!!! ");
					        } 
						catch(Exception e){
							e.getMessage();
					    }
						WaitUtils.waitForTitleIs(driver, "incidentNumber|Incidents | ServiceNow");
			        	 
			        	
			        	 
						//Update the incident ticket from Work in Progress to Resolved State
						if (incidentStateResolved.equalsIgnoreCase("Resolved")) {
							Assert.assertEquals(incidentStateResolved, "Resolved");
							
							ExcelUtis.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", 3, 4, "Passed");
			        	 }
						else{
			        	
			        		ExcelUtis.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", 3, 4, "Failed");
			        		Assert.assertEquals(incidentStateResolved, "Resolved");
			        	 	}
		        	 	}
		        	 
		        	
		        	 
		         		
		         else {
		        	 
						ExcelUtis.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", 3, 4, "Failed");
		        		Assert.assertEquals(incidentState, "New");
				}  }       
					
			catch(Exception e){
				e.getMessage();
			}
			System.out.println(incidentNumber);
			//return incidentNumber;
		}
		public static void cancelIncident(WebDriver driver, String incNumber) throws Exception
		{
			try{
				WaitUtils.waitForPageToLoad(driver, 30);
				
				//Search for the Incident Ticket
				incNumber=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 2);
				assignedTo=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 5, 7);
				worknotes = ExcelUtis.getData("Incident_Management_TestData.xlsx" , "Smoke_Suite", 5, 18);
				ExcelUtis.writeDataIntoCell("Incident_Management_TestData.xlsx","Smoke_Suite", 5, 2, incNumber);
					
				if(!IncidentPage.getSearchDropDown(driver).getAttribute("value").equalsIgnoreCase("number")){
					 WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']//select");
			         DropDowns.selectDropdownByVisibleText(IncidentPage.getSearchDropDown(driver), "Number", "Search Drop Down");
				}
		        IncidentReusables.searchIncidentTicketFromQueue(driver, incNumber);
		        incidentState=IncidentPage.getIncidentStatusfromQueue(driver, incNumber).getText();
		        if (incidentState.equalsIgnoreCase("New")) {
		        	Thread.sleep(3000);
		        	IncidentPage.getIncidentNumberfromQueue(driver, incNumber).click();
		        	WaitUtils.waitForPageToLoad(driver, 30);
		        	
		        	//Update the status from New to Work in Progress
		        	DropDowns.selectDropdownByVisibleText(IncidentPage.getStateDropdown(driver), "Work in Progress", "State");
		        	IncidentPage.getAssignedToEdt(driver).sendKeys(assignedTo);
		        	IncidentPage.getAssignedToEdt(driver).sendKeys(Keys.ENTER);
		        	Thread.sleep(2000);
		     
		        	WaitUtils.waitForXpathPresent(driver, "//button[text()='Save']");
					driver.findElement(By.xpath("//button[text()='Save']")).click();
		        
		        	
		        	
		        	
				DropDowns.selectDropdownByVisibleText(IncidentPage.getStateDropdown(driver), "Cancelled", "State");
				 reasonForCancellation=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 4, 17);
		 			subCauseCode=ExcelUtis.getData("Incident_Management_TestData.xlsx", "Smoke_Suite", 4, 15);
		 					
		        	 WaitUtils.waitForXpathPresent(driver, "//span[text()='Governance']");
		        	 Thread.sleep(3000);
		        	 IncidentPage.getGovernanceTab(driver).click();
		        	 
		        	 WaitUtils.waitForElementToBeVisible(driver, IncidentPage.getReasonForCancellationEdt(driver));
		        	 Thread.sleep(2000);
		        	 WaitUtils.waitForXpathPresent(driver, "//textarea[@id='incident.u_reason_for_cancellation']");
		        	 IncidentPage.getReasonForCancellationEdt(driver).sendKeys(reasonForCancellation);		        	 
		        	 WaitUtils.waitForXpathPresent(driver, "//span[text()='Closure']");
		        	 Thread.sleep(2000);
		        	 IncidentPage.getClosureTab(driver).click();
		        	 DropDowns.selectDropdownByVisibleText(IncidentPage.getSubCauseCodeDropdown(driver),subCauseCode,"Sub Cause Code");		        	 
		        	 Thread.sleep(2000);
		        	
		        	 WaitUtils.waitForXpathPresent(driver, "//button[text()='Save']");
						driver.findElement(By.xpath("//button[text()='Save']")).click();
						try {
					        Alert alert = driver.switchTo().alert();
					        String alertText = alert.getText();
					    
					      
					        Assert.fail("Unexpected alert !!!! ");
					        } 
						catch(Exception e){
							e.getMessage();
					    }
			        	
			        }}

			        catch (UnhandledAlertException f) {
					    try {
					        Alert alert = driver.switchTo().alert();
					        String alertText = alert.getText();
					        System.out.println("Alert data: " + alertText);
					         Assert.fail("Unhandled alert");
					        
					        } 
					    catch(Exception e){
							e.getMessage();
					    
			        }  }
			System.out.println("Incident is cancelled");
			}    
					    public static String createstandardchangefromincident(WebDriver driver, boolean ManagedIncident, int sNum, int cellNum) throws Exception
						{
							try{
								WaitUtils.waitForPageToLoad(driver, 30);

								//Reading values from Excel file for Standalone Incident Test Case
								businessServceValue=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 5);
								assignmentGroupValue=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 6);
								openedByGroupValue=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 8);
								userImpact=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 9);
								shortDescription=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 10);
								description=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 11);			
								
								//Store the Incident number in a variable
								WaitUtils.waitForPageToLoad(driver, 50);
								WaitUtils.waitForIdPresent(driver, "sys_readonly.incident.number");
								incidentNumber=driver.findElement(By.xpath("//input[@id='sys_readonly.incident.number']")).getAttribute("value");
								ExcelUtis.writeDataIntoCell("Incident_Management_TestData.xlsx","Smoke_Suite", sNum, cellNum, incidentNumber);
											
								
								//Business Service Field
								Thread.sleep(3000);
								//WaitUtils.waitForXpathPresent(driver, "//input[@id='sys_display.incident.u_business_service']");
								//IncidentPage.getBusinessServiceEdt(driver).sendKeys(businessServceValue);
							driver.findElement(By.id("sys_display.incident.u_business_service")).sendKeys(businessServceValue);
								
								Thread.sleep(6000);
								IncidentPage.getBusinessServiceEdt(driver).sendKeys(Keys.ENTER);
								//Assignment group value			
								WaitUtils.waitForIdPresent(driver, "sys_display.incident.assignment_group");
								IncidentPage.getAssignmentGroupEdt(driver).sendKeys(assignmentGroupValue);
								IncidentPage.getAssignmentGroupEdt(driver).sendKeys(Keys.ENTER);
								
										
								//OpenedByGroup value			
								WaitUtils.waitForIdPresent(driver, "sys_display.incident.u_opened_by_group");
								IncidentPage.getOpenedByGroupEdt(driver).sendKeys(openedByGroupValue);
								IncidentPage.getOpenedByGroupEdt(driver).sendKeys(Keys.ENTER);
								//ReporterLogs.log("Opened By Group value selected: "+openedByGroupValue, "info");
										
								//User Impact Field			
								WaitUtils.waitForIdPresent(driver, "incident.u_user_impact");
								DropDowns.selectDropdownByVisibleText(IncidentPage.getUserImpactDropdown(driver), userImpact, "User impact");
								
										
								//Short Description field			
								WaitUtils.waitForIdPresent(driver, "incident.short_description");
								IncidentPage.getShortDescriptionEdt(driver).sendKeys(shortDescription+ incidentNumber);
								
								//Description field
								WaitUtils.waitForIdPresent(driver, "incident.description");
								IncidentPage.getDescriptionEdt(driver).sendKeys(description+ incidentNumber);
								
								Thread.sleep(2000);			
								
								//Click on Submit button
								WaitUtils.waitForXpathPresent(driver, "//button[text()='Save']");
								driver.findElement(By.xpath("//button[text()='Save']")).click();
								WaitUtils.waitForXpathPresent(driver,"//button[@class='btn btn-icon icon-menu navbar-btn additional-actions-context-menu-button']");
								driver.findElement(By.xpath("//button[@class='btn btn-icon icon-menu navbar-btn additional-actions-context-menu-button']")).click();
								Thread.sleep(2000);
								//click on standard change
								WaitUtils.waitForXpathPresent(driver, "//div[text()='Create Standard Change']");
								driver.findElement(By.xpath("//div[text()='Create Standard Change']")).click();
								
								assignmentGroup2 = ExcelUtis.getData("Change_Management_TestData1.xlsx", "Smoke_Suite", 1, 5);
								configurationItem = ExcelUtis.getData("Change_Management_TestData1.xlsx", "Smoke_Suite", 1, 6);
								changeId=IncidentPage.getChangeNumberEdt(driver).getAttribute("value");
								
								TextBoxes.enterTextValue(IncidentPage.getAssignmentGrpEdt1(driver), assignmentGroup2, "Assignement Group Field");
							
								Thread.sleep(5000);
								TextBoxes.enterTextValue(IncidentPage.getConfigurationItemEdt1(driver), configurationItem, "Configuration Item");
								
								WaitUtils.waitForXpathPresent(driver, "//span[contains(text(),'Planning') and @class='tab_caption_text']");
								Thread.sleep(3000);
								IncidentPage.getPlanningTab(driver).click();
						       // WaitUtils.waitForIdPresent(driver, "change_request.u_reason_for_change");
								//TextBoxes.enterTextValue(ChangePage.getReasonForChangeEdt(driver), reasonForChange, "Reason for Change (business justification)");
								WaitUtils.waitForXpathPresent(driver, "//span[contains(text(),'Schedule')]");
								IncidentPage.getScheduleTab(driver).click();
								String requestedByDate = Utils.getCurrentDateTime();
								TextBoxes.enterTextValue(IncidentPage.getRequestedByDateEdt(driver), requestedByDate, "Requested By Date");
								
								WaitUtils.waitForIdPresent(driver, "sysverb_insert");
								IncidentPage.getSubmitBtn1(driver).click();
							}
							
							catch(Exception e){
								e.getMessage();
							}
							System.out.println(incidentNumber);
							System.out.println(changeId);
							return changeId;
						}	
					    
					  //create problem from incident
						public static String createproblemfromincident(WebDriver driver, boolean ManagedIncident, int sNum, int cellNum) throws Exception
						{
							try{
								WaitUtils.waitForPageToLoad(driver, 30);

								//Reading values from Excel file for Standalone Incident Test Case
								businessServceValue=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 5);
								assignmentGroupValue=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 6);
								openedByGroupValue=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 8);
								userImpact=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 9);
								shortDescription=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 10);
								description=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 11);			
								
								//Store the Incident number in a variable
								WaitUtils.waitForPageToLoad(driver, 50);
								WaitUtils.waitForIdPresent(driver, "sys_readonly.incident.number");
								incidentNumber=driver.findElement(By.xpath("//input[@id='sys_readonly.incident.number']")).getAttribute("value");
								ExcelUtis.writeDataIntoCell("Incident_Management_TestData.xlsx","Smoke_Suite", sNum, cellNum, incidentNumber);
								
								
								//Business Service Field
								Thread.sleep(3000);
								//WaitUtils.waitForXpathPresent(driver, "//input[@id='sys_display.incident.u_business_service']");
								//IncidentPage.getBusinessServiceEdt(driver).sendKeys(businessServceValue);
							driver.findElement(By.id("sys_display.incident.u_business_service")).sendKeys(businessServceValue);
								
								Thread.sleep(6000);
								IncidentPage.getBusinessServiceEdt(driver).sendKeys(Keys.ENTER);
								//Assignment group value			
								WaitUtils.waitForIdPresent(driver, "sys_display.incident.assignment_group");
								IncidentPage.getAssignmentGroupEdt(driver).sendKeys(assignmentGroupValue);
								IncidentPage.getAssignmentGroupEdt(driver).sendKeys(Keys.ENTER);
								
										
								//OpenedByGroup value			
								WaitUtils.waitForIdPresent(driver, "sys_display.incident.u_opened_by_group");
								IncidentPage.getOpenedByGroupEdt(driver).sendKeys(openedByGroupValue);
								IncidentPage.getOpenedByGroupEdt(driver).sendKeys(Keys.ENTER);
								
										
								//User Impact Field			
								WaitUtils.waitForIdPresent(driver, "incident.u_user_impact");
								DropDowns.selectDropdownByVisibleText(IncidentPage.getUserImpactDropdown(driver), userImpact, "User impact");
								
										
								//Short Description field			
								WaitUtils.waitForIdPresent(driver, "incident.short_description");
								IncidentPage.getShortDescriptionEdt(driver).sendKeys(shortDescription+ incidentNumber);
								
								//Description field
								WaitUtils.waitForIdPresent(driver, "incident.description");
								IncidentPage.getDescriptionEdt(driver).sendKeys(description+ incidentNumber);
								
								Thread.sleep(2000);			
								
								//Click on Submit button
								WaitUtils.waitForXpathPresent(driver, "//button[text()='Save']");
								driver.findElement(By.xpath("//button[text()='Save']")).click();
								WaitUtils.waitForXpathPresent(driver,"//button[@class='btn btn-icon icon-menu navbar-btn additional-actions-context-menu-button']");
								driver.findElement(By.xpath("//button[@class='btn btn-icon icon-menu navbar-btn additional-actions-context-menu-button']")).click();
							Thread.sleep(5000);
								//click on problem
								WaitUtils.waitForXpathPresent(driver, "//div[text()='Create Problem']");
								driver.findElement(By.xpath("//div[text()='Create Problem']")).click();
								
								//creating problem
								 WaitUtils.waitForPageToLoad(driver, 30);
				                 //Thread.sleep(3000);
				                 source=ExcelUtis.getData("Problem_Management_TestData1.xlsx","Smoke_Suite", 1, 5);
				                 
				                 configurationItem=ExcelUtis.getData("Problem_Management_TestData1.xlsx","Smoke_Suite", 1, 7);
				                 shortDescription=ExcelUtis.getData("Problem_Management_TestData1.xlsx","Smoke_Suite", 1, 10);
				                 description=ExcelUtis.getData("Problem_Management_TestData1.xlsx","Smoke_Suite", 1, 11);
				                  
				                 WaitUtils.waitForIdPresent(driver, "sys_readonly.problem.number");
				                 problemId=driver.findElement(By.xpath("//input[@id='sys_readonly.problem.number']")).getAttribute("value");
				                 ExcelUtis.writeDataIntoCell("Problem_Management_TestData1.xlsx","Smoke_Suite", sNum, cellNum, problemId);
				                
				                   
				                 WaitUtils.waitForIdPresent(driver, "problem.contact_type");
				                 DropDowns.selectDropdownByVisibleText(IncidentPage.getSourceDropdown(driver), source, "Source");
				                 //Thread.sleep(3000);                                 
				                 
				                             
				                // TextBoxes.enterTextValue(ProblemPage.getShortdescriptionEdt(driver), shortDescription, "Short Description");
				                // Thread.sleep(2000);
				                // TextBoxes.enterTextValue(ProblemPage.getDescriptionEdt(driver), description, "Description");
				                // Thread.sleep(2000);
				                 WaitUtils.waitForIdPresent(driver, "sys_display.problem.cmdb_ci"); 
				                 TextBoxes.enterTextValue(IncidentPage.getConfigurationitemEdt(driver), configurationItem, "Configuration Item");
				                 IncidentPage.getConfigurationitemEdt(driver).sendKeys(Keys.ENTER);
				               
				                 Thread.sleep(5000);
				                 IncidentPage.getSubmitBtn(driver).click();
							
							
							}
							catch(Exception e){
								e.getMessage();
							}
							System.out.println(incidentNumber);
							System.out.println(problemId);
							return problemId;
						}
						public static String CreateIncidentTask(WebDriver driver, String incNumber) throws Exception
						{
						try
							{
							
							WaitUtils.waitForPageToLoad(driver, 30);
							
							//Search for the Incident Ticket
							incNumber=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 2);
							assignedTo=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 5, 7);
							worknotes = ExcelUtis.getData("Incident_Management_TestData.xlsx" , "Smoke_Suite", 5, 18);
							ExcelUtis.writeDataIntoCell("Incident_Management_TestData.xlsx","Smoke_Suite", 5, 2, incNumber);
						
							if(!IncidentPage.getSearchDropDown(driver).getAttribute("value").equalsIgnoreCase("number")){
								 WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']//select");
						         DropDowns.selectDropdownByVisibleText(IncidentPage.getSearchDropDown(driver), "Number", "Search Drop Down");
							}
						    IncidentReusables.searchIncidentTicketFromQueue(driver, incNumber);
						    incidentState=IncidentPage.getIncidentStatusfromQueue(driver, incNumber).getText();
						    if (incidentState.equalsIgnoreCase("New")) {
						    	Thread.sleep(3000);
						    	IncidentPage.getIncidentNumberfromQueue(driver, incNumber).click();
						    	WaitUtils.waitForPageToLoad(driver, 30);
						    	
						    	//Update the status from New to Work in Progress
						    	DropDowns.selectDropdownByVisibleText(IncidentPage.getStateDropdown(driver), "Work in Progress", "State");
						    	IncidentPage.getAssignedToEdt(driver).sendKeys(assignedTo);
						    	IncidentPage.getAssignedToEdt(driver).sendKeys(Keys.ENTER);
						    	Thread.sleep(2000);
						    	WaitUtils.waitForXpathPresent(driver, "//button[text()='Save']");
								driver.findElement(By.xpath("//button[text()='Save']")).click();
						        	 
								Thread.sleep(3000);
								
								IncidentTask.getroutingsituationbtn(driver).click();
								Thread.sleep(3000);
								driver.switchTo().frame(1);

								
								DropDowns.selectDropdownByVisibleText(IncidentTask.getroutingoption(driver), "Task a Group"," Routing Option");
								Assignmentgroup1=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 2, 6);
								WaitUtils.waitForIdPresent(driver, "sys_display.u_task_route.u_assignment_group");
								Thread.sleep(2000);
								IncidentPage.Assignmentgroupedt(driver).sendKeys(Assignmentgroup1);
							
								Thread.sleep(2000);
							    IncidentPage.Assignmentgroupedt(driver).sendKeys(Keys.ENTER);
							    WaitUtils.waitForXpathPresent(driver, "//button[text()='Next']");
								driver.findElement(By.xpath("//button[text()='Next']")).click();
								
								driver.switchTo().defaultContent();
								
								
								   try {
								        Alert alert = driver.switchTo().alert();
								        String alertText = alert.getText();
								       
								        Assert.fail("Unexpected alert !!!! ");
								        } 
								   catch(Exception e){
										e.getMessage();
								    }
						      
						       }}

						       catch (UnhandledAlertException f) {
								    try {
								        Alert alert = driver.switchTo().alert();
								        String alertText = alert.getText();
								        System.out.println("Alert data: " + alertText);
								         Assert.fail("Unhandled alert");
								        
								        } 
								    catch (NoAlertPresentException e) {
								        e.printStackTrace();
								    }	
								    
						       }
						return incNumber;
						}
						public static void verifyIncidentCreation1(WebDriver driver, String incNumber) throws Exception
						{
							WaitUtils.waitForPageToLoad(driver, 30);
						
					        if(IncidentPage.getSearchDropDown(driver).getAttribute("value").equalsIgnoreCase("number"))
					        {
					        	Thread.sleep(2000);
					            WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']/label[text()='Search']/following-sibling::input");
					            TextBoxes.enterTextValue(IncidentPage.getSearchIncidentEdt(driver),incNumber,"Search Incident");
					            IncidentPage.getSearchIncidentEdt(driver).sendKeys(Keys.ENTER);
					            //Thread.sleep(3000);
					            WaitUtils.waitForPageToLoad(driver, 30);
					            incidentState=IncidentPage.getIncidentStatusfromQueue(driver, incNumber).getText();
					            
					            if(incidentState.equalsIgnoreCase("Work in Progress"))
					            {
					         	   Assert.assertEquals(IncidentPage.getIncidentStatusfromQueue(driver, incNumber).getText(), "Work in Progress");
					         	
					         	   ExcelUtis.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", 1, 4, "Passed");
					         	
					         }else{
					        	   	
					         	
					         	   ExcelUtis.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", 1, 4, "Failed");
					         	  
					         	  Assert.assertEquals(IncidentPage.getIncidentStatusfromQueue(driver, incNumber).getText(), "Work in Progress");
					         	 }  	       
					        }      
					        else {
					        	   WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']//select");
					        	   DropDowns.selectDropdownByVisibleText(IncidentPage.getSearchDropDown(driver), "Number", "Search Drop Down");
					        	   TextBoxes.enterTextValue(IncidentPage.getSearchIncidentEdt(driver), incNumber, "Search Incident ");
					        	 
					        	   IncidentPage.getSearchIncidentEdt(driver).sendKeys(Keys.ENTER);	         
					        	   if(incidentState.equalsIgnoreCase("Work in Progress"))
					        	   {
					        		Assert.assertEquals(IncidentPage.getIncidentStatusfromQueue(driver, incNumber).getText(), "Work in Progress");
						         	
						            ExcelUtis.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", 1, 4, "Passed"); 
						          
					        	   }else{        	    
								
								    ExcelUtis.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", 1, 4, "Failed");
								  
								    Assert.assertEquals(IncidentPage.getIncidentStatusfromQueue(driver, incNumber).getText(), "Work in Progress");
							       }      
						  		}
							}
						public static String MovetasktoWIP(WebDriver driver, String incNumber) throws Exception
						{
							try{
								WaitUtils.waitForPageToLoad(driver, 30);
								
								//Search for the Incident Ticket
								incNumber=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 2);
								assignedTo=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 5, 7);
								openedByGrouptaskValue=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 8);
								worknotes = ExcelUtis.getData("Incident_Management_TestData.xlsx" , "Smoke_Suite", 5, 18);
								ExcelUtis.writeDataIntoCell("Incident_Management_TestData.xlsx","Smoke_Suite", 5, 2, incNumber);
										
								if(!IncidentPage.getSearchDropDown(driver).getAttribute("value").equalsIgnoreCase("number")){
									 WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']//select");
							         DropDowns.selectDropdownByVisibleText(IncidentPage.getSearchDropDown(driver), "Number", "Search Drop Down");
								}
						        IncidentReusables.searchIncidentTicketFromQueue(driver, incNumber);
						        incidentState=IncidentPage.getIncidentStatusfromQueue(driver, incNumber).getText();
						        if (incidentState.equalsIgnoreCase("Work in Progress")) {
						        	Thread.sleep(3000);
						        	IncidentPage.getIncidentNumberfromQueue(driver, incNumber).click();
						        	WaitUtils.waitForPageToLoad(driver, 30);
						        	
						        	WaitUtils.waitForXpathPresent(driver, "//button[text()='Save']");
									driver.findElement(By.xpath("//button[text()='Save']")).click();
									IncidentPage.getIncidentTaskLnk(driver, 1).click();
									DropDowns.selectDropdownByVisibleText(IncidentPage.getincidentaskstate(driver), "Work in Progress", "State");
									WaitUtils.waitForPageToLoad(driver, 50);
									WaitUtils.waitForIdPresent(driver, "sys_readonly.u_inc_task.number");
									Thread.sleep(3000);
									incidenttasknum=driver.findElement(By.xpath("//input[@id='sys_readonly.u_inc_task.number']")).getAttribute("value");
								
									//OpenedByGroup value			
									WaitUtils.waitForIdPresent(driver, "sys_display.u_inc_task.u_opened_by_group");
									IncidentPage.getopenbygrouptaskedt(driver).sendKeys(openedByGrouptaskValue);
									IncidentPage.getopenbygrouptaskedt(driver).sendKeys(Keys.ENTER);
								
									
									//assigned to
									WaitUtils.waitForIdPresent(driver,"sys_display.u_inc_task.assigned_to");
									IncidentPage.getassignedtotaskedt(driver).sendKeys(assignedTo);
						        	IncidentPage.getassignedtotaskedt(driver).sendKeys(Keys.ENTER);
						        	Thread.sleep(2000);
						        	
						        	WaitUtils.waitForXpathPresent(driver, "//button[text()='Save']");
									driver.findElement(By.xpath("//button[text()='Save']")).click();
									
						        }}
									catch(Exception e){
										e.getMessage();	 
									}
							System.out.println(incidentNumber);
							System.out.println(incidenttasknum);
							return incidentNumber;
						        
				}
		//create normal change
						public static String createnormalchangefromincident(WebDriver driver, boolean ManagedIncident, int sNum, int cellNum) throws Exception
						{
							try{
								WaitUtils.waitForPageToLoad(driver, 30);

								//Reading values from Excel file for Standalone Incident Test Case
								businessServceValue=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 5);
								assignmentGroupValue=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 6);
								openedByGroupValue=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 8);
								userImpact=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 9);
								shortDescription=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 10);
								description=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 11);			
								
								//Store the Incident number in a variable
								WaitUtils.waitForPageToLoad(driver, 50);
								WaitUtils.waitForIdPresent(driver, "sys_readonly.incident.number");
								incidentNumber=driver.findElement(By.xpath("//input[@id='sys_readonly.incident.number']")).getAttribute("value");
								ExcelUtis.writeDataIntoCell("Incident_Management_TestData.xlsx","Smoke_Suite", sNum, cellNum, incidentNumber);
										
								
								//Business Service Field
								Thread.sleep(3000);
								//WaitUtils.waitForXpathPresent(driver, "//input[@id='sys_display.incident.u_business_service']");
								//IncidentPage.getBusinessServiceEdt(driver).sendKeys(businessServceValue);
							driver.findElement(By.id("sys_display.incident.u_business_service")).sendKeys(businessServceValue);
							
								Thread.sleep(6000);
								IncidentPage.getBusinessServiceEdt(driver).sendKeys(Keys.ENTER);
								
								//Assignment group value			
								WaitUtils.waitForIdPresent(driver, "sys_display.incident.assignment_group");
								IncidentPage.getAssignmentGroupEdt(driver).sendKeys(assignmentGroupValue);
								IncidentPage.getAssignmentGroupEdt(driver).sendKeys(Keys.ENTER);
							
										
								//OpenedByGroup value			
								WaitUtils.waitForIdPresent(driver, "sys_display.incident.u_opened_by_group");
								IncidentPage.getOpenedByGroupEdt(driver).sendKeys(openedByGroupValue);
								IncidentPage.getOpenedByGroupEdt(driver).sendKeys(Keys.ENTER);
							
										
								//User Impact Field			
								WaitUtils.waitForIdPresent(driver, "incident.u_user_impact");
								DropDowns.selectDropdownByVisibleText(IncidentPage.getUserImpactDropdown(driver), userImpact, "User impact");
							//	ReporterLogs.log("User Impact selected: "+userImpact, "info");
										
								//Short Description field			
								WaitUtils.waitForIdPresent(driver, "incident.short_description");
								IncidentPage.getShortDescriptionEdt(driver).sendKeys(shortDescription+ incidentNumber);
								
								//Description field
								WaitUtils.waitForIdPresent(driver, "incident.description");
								IncidentPage.getDescriptionEdt(driver).sendKeys(description+ incidentNumber);
								
								Thread.sleep(2000);			
								
								//Click on Submit button
								WaitUtils.waitForXpathPresent(driver, "//button[text()='Save']");
								driver.findElement(By.xpath("//button[text()='Save']")).click();
								WaitUtils.waitForXpathPresent(driver,"//button[@class='btn btn-icon icon-menu navbar-btn additional-actions-context-menu-button']");
								driver.findElement(By.xpath("//button[@class='btn btn-icon icon-menu navbar-btn additional-actions-context-menu-button']")).click();
								Thread.sleep(2000);
								
								WaitUtils.waitForXpathPresent(driver, "//div[text()='Create Normal Change']");
								driver.findElement(By.xpath("//div[text()='Create Normal Change']")).click();
								
								//Create Normal change
								assignmentGroup2 = ExcelUtis.getData("Change_Management_TestData1.xlsx", "Smoke_Suite", 1, 5);
								configurationItem = ExcelUtis.getData("Change_Management_TestData1.xlsx", "Smoke_Suite", 1, 6);
								changeId=IncidentPage.getChangeNumberEdt(driver).getAttribute("value");
								reasonForChange = ExcelUtis.getData("Change_Management_TestData1.xlsx", "Smoke_Suite", 1, 9);
								//ExcelUtis.writeDataIntoCell("Change_Management_TestData.xlsx", "Smoke_Suite", serialnum, cnum, changeId);
								System.out.println(changeId);
								TextBoxes.enterTextValue(IncidentPage.getAssignmentGrpEdt1(driver), assignmentGroup2, "Assignement Group Field");
							//	ReporterLogs.log("Assignment Group field is entered successfully "+assignmentGroup2, "info");
								Thread.sleep(5000);
								TextBoxes.enterTextValue(IncidentPage.getConfigurationItemEdt1(driver), configurationItem, "Configuration Item");
								//ReporterLogs.log("configuration item field is entered successfully "+configurationItem, "info");
								WaitUtils.waitForXpathPresent(driver, "//span[contains(text(),'Planning') and @class='tab_caption_text']");
								/*WebElement element = driver.findElement(By.xpath("//span[contains(text(),'Planning']"));
								Actions actions = new Actions(driver);
								actions.moveToElement(element).click().build().perform();*/
								Thread.sleep(3000);
							IncidentPage.getPlanningTab(driver).click();
								WaitUtils.waitForIdPresent(driver, "change_request.u_reason_for_change");
								TextBoxes.enterTextValue(IncidentPage.getReasonForChangeEdt(driver), reasonForChange, "Reason For Change");
								//ReporterLogs.log("reason for change field is entered successfully "+ reasonForChange, "info");		
								WaitUtils.waitForXpathPresent(driver, "//span[contains(text(),'Schedule')]");
								IncidentPage.getScheduleTab(driver).click();
								String requestedByDate = Utils.getCurrentDateTime();
								TextBoxes.enterTextValue(IncidentPage.getRequestedByDateEdt(driver), requestedByDate, "Requested By Date");
								//ReporterLogs.log("Requested By Date field is entered successfully "+ requestedByDate, "info");
								WaitUtils.waitForIdPresent(driver, "sysverb_insert");
								IncidentPage.getSubmitBtn1(driver).click();
							}
						
								catch(Exception e){
									e.getMessage();
								}
								System.out.println(incidentNumber);
								System.out.println("Normal change is"+ changeId);
								return changeId;
							}	
	
						//Create emergency change
						public static String createemergencychangefromincident(WebDriver driver, int sNum, int cellNum) throws Exception
						{
							try{
								WaitUtils.waitForPageToLoad(driver, 30);

								//Reading values from Excel file for Standalone Incident Test Case
								businessServceValue=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 5);
								assignmentGroupValue=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 6);
								openedByGroupValue=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 8);
								userImpact=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 9);
								shortDescription=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 10);
								description=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 11);			
								
								//Store the Incident number in a variable
								WaitUtils.waitForPageToLoad(driver, 50);
								WaitUtils.waitForIdPresent(driver, "sys_readonly.incident.number");
								incidentNumber=driver.findElement(By.xpath("//input[@id='sys_readonly.incident.number']")).getAttribute("value");
								ExcelUtis.writeDataIntoCell("Incident_Management_TestData.xlsx","Smoke_Suite", sNum, cellNum, incidentNumber);
								
								
								//Business Service Field
								Thread.sleep(3000);
								//WaitUtils.waitForXpathPresent(driver, "//input[@id='sys_display.incident.u_business_service']");
								//IncidentPage.getBusinessServiceEdt(driver).sendKeys(businessServceValue);
							driver.findElement(By.id("sys_display.incident.u_business_service")).sendKeys(businessServceValue);
							
								Thread.sleep(6000);
								IncidentPage.getBusinessServiceEdt(driver).sendKeys(Keys.ENTER);
								
								//Assignment group value			
								WaitUtils.waitForIdPresent(driver, "sys_display.incident.assignment_group");
								IncidentPage.getAssignmentGroupEdt(driver).sendKeys(assignmentGroupValue);
								IncidentPage.getAssignmentGroupEdt(driver).sendKeys(Keys.ENTER);
			
										
								//OpenedByGroup value			
								WaitUtils.waitForIdPresent(driver, "sys_display.incident.u_opened_by_group");
								IncidentPage.getOpenedByGroupEdt(driver).sendKeys(openedByGroupValue);
								IncidentPage.getOpenedByGroupEdt(driver).sendKeys(Keys.ENTER);
								
										
								//User Impact Field			
								WaitUtils.waitForIdPresent(driver, "incident.u_user_impact");
								DropDowns.selectDropdownByVisibleText(IncidentPage.getUserImpactDropdown(driver), userImpact, "User impact");
							
										
								//Short Description field			
								WaitUtils.waitForIdPresent(driver, "incident.short_description");
								IncidentPage.getShortDescriptionEdt(driver).sendKeys(shortDescription+ incidentNumber);
								
								//Description field
								WaitUtils.waitForIdPresent(driver, "incident.description");
								IncidentPage.getDescriptionEdt(driver).sendKeys(description+ incidentNumber);
								
								Thread.sleep(2000);			
								
								//Click on Submit button
								WaitUtils.waitForXpathPresent(driver, "//button[text()='Save']");
								driver.findElement(By.xpath("//button[text()='Save']")).click();
								WaitUtils.waitForXpathPresent(driver,"//button[@class='btn btn-icon icon-menu navbar-btn additional-actions-context-menu-button']");
								driver.findElement(By.xpath("//button[@class='btn btn-icon icon-menu navbar-btn additional-actions-context-menu-button']")).click();
								Thread.sleep(2000);
								//click on emergency change
								WaitUtils.waitForXpathPresent(driver, "//div[text()='Create Emergency Change']");
								driver.findElement(By.xpath("//div[text()='Create Emergency Change']")).click();
								
								//Create emergency change
								assignmentGroup2 = ExcelUtis.getData("Change_Management_TestData1.xlsx", "Smoke_Suite", 1, 5);
								configurationItem = ExcelUtis.getData("Incident_Management_TestData1.xlsx", "Smoke_Suite", 11, 13);
								changeId=IncidentPage.getChangeNumberEdt(driver).getAttribute("value");
								reasonForChange = ExcelUtis.getData("Change_Management_TestData1.xlsx", "Smoke_Suite", 1, 9);
								//ExcelUtis.writeDataIntoCell("Change_Management_TestData1.xlsx", "Smoke_Suite", sNum, cellNum, changeId);
								System.out.println(changeId);
								TextBoxes.enterTextValue(IncidentPage.getAssignmentGrpEdt1(driver), assignmentGroup2, "Assignement Group Field");
								
								Thread.sleep(5000);
								TextBoxes.enterTextValue(IncidentPage.getConfigurationItemEdt1(driver), "00 csnas-12", "Configuration Item");
							
								WaitUtils.waitForXpathPresent(driver, "//span[contains(text(),'Planning') and @class='tab_caption_text']");
								IncidentPage.getPlanningTab(driver).click();
								WaitUtils.waitForIdPresent(driver, "change_request.u_reason_for_change");
								TextBoxes.enterTextValue(IncidentPage.getReasonForChangeEdt(driver), reasonForChange, "Reason For Change");
								
								WaitUtils.waitForXpathPresent(driver, "//span[contains(text(),'Schedule')]");
								IncidentPage.getScheduleTab(driver).click();
								String requestedByDate = Utils.getCurrentDateTime();
								TextBoxes.enterTextValue(IncidentPage.getRequestedByDateEdt(driver), requestedByDate, "Requested By Date");
								
								WaitUtils.waitForIdPresent(driver, "sysverb_insert");
								IncidentPage.getSubmitBtn1(driver).click();
							}
						
								catch(Exception e){
									e.getMessage();
								}
								System.out.println(incidentNumber);
								System.out.println("Emergency change is"+ changeId);
								return changeId;
							}
						
						//Child incident
						public static String creatchildincidentfromincident(WebDriver driver, boolean ManagedIncident, int sNum, int cellNum) throws Exception
						{
							try{
								WaitUtils.waitForPageToLoad(driver, 30);

								//Reading values from Excel file for Standalone Incident Test Case
								businessServceValue=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 5);
								assignmentGroupValue=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 6);
								openedByGroupValue=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 8);
								userImpact=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 9);
								shortDescription=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 10);
								description=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 11);			
								
								//Store the Incident number in a variable
								WaitUtils.waitForPageToLoad(driver, 50);
								WaitUtils.waitForIdPresent(driver, "sys_readonly.incident.number");
								incidentNumber=driver.findElement(By.xpath("//input[@id='sys_readonly.incident.number']")).getAttribute("value");
								ExcelUtis.writeDataIntoCell("Incident_Management_TestData.xlsx","Smoke_Suite", sNum, cellNum, incidentNumber);
							
								Thread.sleep(3000);
								//WaitUtils.waitForXpathPresent(driver, "//input[@id='sys_display.incident.u_business_service']");
								//IncidentPage.getBusinessServiceEdt(driver).sendKeys(businessServceValue);
							driver.findElement(By.id("sys_display.incident.u_business_service")).sendKeys(businessServceValue);
							
								Thread.sleep(6000);
								IncidentPage.getBusinessServiceEdt(driver).sendKeys(Keys.ENTER);
								
								//Assignment group value			
								WaitUtils.waitForIdPresent(driver, "sys_display.incident.assignment_group");
								IncidentPage.getAssignmentGroupEdt(driver).sendKeys(assignmentGroupValue);
								IncidentPage.getAssignmentGroupEdt(driver).sendKeys(Keys.ENTER);
							
										
								//OpenedByGroup value			
								WaitUtils.waitForIdPresent(driver, "sys_display.incident.u_opened_by_group");
								IncidentPage.getOpenedByGroupEdt(driver).sendKeys(openedByGroupValue);
								IncidentPage.getOpenedByGroupEdt(driver).sendKeys(Keys.ENTER);
							
										
								//User Impact Field			
								WaitUtils.waitForIdPresent(driver, "incident.u_user_impact");
								DropDowns.selectDropdownByVisibleText(IncidentPage.getUserImpactDropdown(driver), userImpact, "User impact");
							
								//Short Description field			
								WaitUtils.waitForIdPresent(driver, "incident.short_description");
								IncidentPage.getShortDescriptionEdt(driver).sendKeys(shortDescription+ incidentNumber);
								
								//Description field
								WaitUtils.waitForIdPresent(driver, "incident.description");
								IncidentPage.getDescriptionEdt(driver).sendKeys(description+ incidentNumber);
								
								Thread.sleep(2000);			
								
								//Click on Submit button
								WaitUtils.waitForXpathPresent(driver, "//button[text()='Save']");
								driver.findElement(By.xpath("//button[text()='Save']")).click();
								WaitUtils.waitForXpathPresent(driver,"//button[@class='btn btn-icon icon-menu navbar-btn additional-actions-context-menu-button']");
								driver.findElement(By.xpath("//button[@class='btn btn-icon icon-menu navbar-btn additional-actions-context-menu-button']")).click();
								Thread.sleep(2000);
								WaitUtils.waitForXpathPresent(driver, "//div[text()='Create Child Incident']");
								driver.findElement(By.xpath("//div[text()='Create Child Incident']")).click();
								Thread.sleep(5000);
								businessServceValue=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 5);
								Thread.sleep(3000);
								//WaitUtils.waitForXpathPresent(driver, "//input[@id='sys_display.incident.u_business_service']");
								//IncidentPage.getBusinessServiceEdt(driver).sendKeys(businessServceValue);
							driver.findElement(By.id("sys_display.incident.u_business_service")).sendKeys(businessServceValue);
								
								Thread.sleep(6000);
								IncidentPage.getBusinessServiceEdt(driver).sendKeys(Keys.ENTER);
								
								openedByGroupValue=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 8);
								
								//OpenedByGroup value			
								WaitUtils.waitForIdPresent(driver, "sys_display.incident.u_opened_by_group");
								IncidentPage.getOpenedByGroupEdt(driver).sendKeys(openedByGroupValue);
								IncidentPage.getOpenedByGroupEdt(driver).sendKeys(Keys.ENTER);
							
								
								userImpact=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 9);
								//User Impact Field			
								WaitUtils.waitForIdPresent(driver, "incident.u_user_impact");
								DropDowns.selectDropdownByVisibleText(IncidentPage.getUserImpactDropdown(driver), userImpact, "User impact");
								
								ChildIncnum = IncidentPage.getchildIncidentNumber(driver).getAttribute("value");
								WaitUtils.waitForXpathPresent(driver, "//button[text()='Save']");
								driver.findElement(By.xpath("//button[text()='Save']")).click();
							}
								catch(Exception e){
									e.getMessage();
								}
								System.out.println("original incident number is" + incidentNumber);
								System.out.println("Child incident number is" +  ChildIncnum);
								return ChildIncnum;
										
							}	
						//Move task to close complete
						
						public static String Movetasktclosecomplete(WebDriver driver, String incNumber) throws Exception
						{
							try{
								WaitUtils.waitForPageToLoad(driver, 30);
								
								//Search for the Incident Ticket
								incNumber=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 2);
								assignedTo=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 5, 7);
								openedByGrouptaskValue=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 8);
								worknotes = ExcelUtis.getData("Incident_Management_TestData.xlsx" , "Smoke_Suite", 5, 18);
								ExcelUtis.writeDataIntoCell("Incident_Management_TestData.xlsx","Smoke_Suite", 5, 2, incNumber);
											
								if(!IncidentPage.getSearchDropDown(driver).getAttribute("value").equalsIgnoreCase("number")){
									 WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']//select");
							         DropDowns.selectDropdownByVisibleText(IncidentPage.getSearchDropDown(driver), "Number", "Search Drop Down");
								}
						        IncidentReusables.searchIncidentTicketFromQueue(driver, incNumber);
						        incidentState=IncidentPage.getIncidentStatusfromQueue(driver, incNumber).getText();
						        if (incidentState.equalsIgnoreCase("Work in Progress")) {
						        	Thread.sleep(3000);
						        	IncidentPage.getIncidentNumberfromQueue(driver, incNumber).click();
						        	WaitUtils.waitForPageToLoad(driver, 30);
						        
						        	WaitUtils.waitForXpathPresent(driver, "//button[text()='Save']");
									driver.findElement(By.xpath("//button[text()='Save']")).click();
									IncidentPage.getIncidentTaskLnk(driver, 1).click();
									DropDowns.selectDropdownByVisibleText(IncidentPage.getincidentaskstate(driver), "Work in Progress", "State");
									WaitUtils.waitForPageToLoad(driver, 50);
									WaitUtils.waitForIdPresent(driver, "sys_readonly.u_inc_task.number");
									Thread.sleep(3000);
									incidenttasknum=driver.findElement(By.xpath("//input[@id='sys_readonly.u_inc_task.number']")).getAttribute("value");
										
									//OpenedByGroup value			
									WaitUtils.waitForIdPresent(driver, "sys_display.u_inc_task.u_opened_by_group");
									IncidentPage.getopenbygrouptaskedt(driver).sendKeys(openedByGrouptaskValue);
									IncidentPage.getopenbygrouptaskedt(driver).sendKeys(Keys.ENTER);
									
									
									//assigned to
									WaitUtils.waitForIdPresent(driver,"sys_display.u_inc_task.assigned_to");
									IncidentPage.getassignedtotaskedt(driver).sendKeys(assignedTo);
						        	IncidentPage.getassignedtotaskedt(driver).sendKeys(Keys.ENTER);
						        	Thread.sleep(2000);
						        
						        	DropDowns.selectDropdownByVisibleText(IncidentPage.getincidentaskstate(driver), "Closed Complete", "State");
						        	TextBoxes.enterTextValue(IncidentPage.Inctaskworknotes(driver), worknotes + incNumber, "worknotes");
						        	
						        	
						        	WaitUtils.waitForXpathPresent(driver, "//button[text()='Save']");
									driver.findElement(By.xpath("//button[text()='Save']")).click();
									
						        }}
									catch(Exception e){
										e.getMessage();	 
									}
							System.out.println(incidentNumber);
							System.out.println(incidenttasknum);
							System.out.println("Incident task is moved to closecomplete state");
							return incidentNumber;
						        
				}	
						public static String Movetasktocancel(WebDriver driver, String incNumber) throws Exception
						{
							try{
								WaitUtils.waitForPageToLoad(driver, 30);
								
								//Search for the Incident Ticket
								incNumber=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 2);
								assignedTo=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 5, 7);
								openedByGrouptaskValue=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 8);
								worknotes = ExcelUtis.getData("Incident_Management_TestData.xlsx" , "Smoke_Suite", 5, 18);
								ExcelUtis.writeDataIntoCell("Incident_Management_TestData.xlsx","Smoke_Suite", 5, 2, incNumber);
									
								if(!IncidentPage.getSearchDropDown(driver).getAttribute("value").equalsIgnoreCase("number")){
									 WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']//select");
							         DropDowns.selectDropdownByVisibleText(IncidentPage.getSearchDropDown(driver), "Number", "Search Drop Down");
								}
						        IncidentReusables.searchIncidentTicketFromQueue(driver, incNumber);
						        incidentState=IncidentPage.getIncidentStatusfromQueue(driver, incNumber).getText();
						        if (incidentState.equalsIgnoreCase("Work in Progress")) {
						        	Thread.sleep(3000);
						        	IncidentPage.getIncidentNumberfromQueue(driver, incNumber).click();
						        	WaitUtils.waitForPageToLoad(driver, 30);
						        	
						        	WaitUtils.waitForXpathPresent(driver, "//button[text()='Save']");
									driver.findElement(By.xpath("//button[text()='Save']")).click();
									IncidentPage.getIncidentTaskLnk(driver, 1).click();
									DropDowns.selectDropdownByVisibleText(IncidentPage.getincidentaskstate(driver), "Cancelled", "State");
									WaitUtils.waitForPageToLoad(driver, 50);
									WaitUtils.waitForIdPresent(driver, "sys_readonly.u_inc_task.number");
									Thread.sleep(3000);
									incidenttasknum=driver.findElement(By.xpath("//input[@id='sys_readonly.u_inc_task.number']")).getAttribute("value");
								
									//OpenedByGroup value			
									WaitUtils.waitForIdPresent(driver, "sys_display.u_inc_task.u_opened_by_group");
									IncidentPage.getopenbygrouptaskedt(driver).sendKeys(openedByGrouptaskValue);
									IncidentPage.getopenbygrouptaskedt(driver).sendKeys(Keys.ENTER);
									
									//assigned to
									WaitUtils.waitForIdPresent(driver,"sys_display.u_inc_task.assigned_to");
									IncidentPage.getassignedtotaskedt(driver).sendKeys(assignedTo);
						        	IncidentPage.getassignedtotaskedt(driver).sendKeys(Keys.ENTER);
						        	Thread.sleep(2000);
						        	
						        	WaitUtils.waitForXpathPresent(driver, "//button[text()='Save']");
									driver.findElement(By.xpath("//button[text()='Save']")).click();
									
						        }}
									catch(Exception e){
										e.getMessage();	 
									}
							System.out.println(incidentNumber);
							System.out.println(incidenttasknum);
							System.out.println("incident task is successfully moved to cancel state");
							return incidentNumber;
						        
				}
						
						//create incident alert
						public static String CreateincidentAlert(WebDriver driver) throws Exception
						{
							
							try{
								WaitUtils.waitForPageToLoad(driver, 30);
								
								//Search for the Incident Ticket
								incNumber=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 2, 2);
								assignedTo=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 5, 7);
								worknotes = ExcelUtis.getData("Incident_Management_TestData.xlsx" , "Smoke_Suite", 5, 18);
								ExcelUtis.writeDataIntoCell("Incident_Management_TestData.xlsx","Smoke_Suite", 5, 2, incNumber);
							
								WaitUtils.waitForTitleIs(driver, "Incidents | ServiceNow");	
								//Store incident alert number in variable
								
								//WaitUtils.waitForIdPresent(driver, "sys_readonly.incident_alert.number");
								//incidentalertnumber = driver.findElement(By.xpath("//input[@id='sys_readonly.incident_alert.number']")).getAttribute("value");
								//ExcelUtis.writeDataIntoCell("Incident_Management_TestData.xlsx","Smoke_Suite", sNum, cellNum, incidentalertnumber);
								
								if(!IncidentPage.getSearchDropDown(driver).getAttribute("value").equalsIgnoreCase("number")){
									 WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']//select");
							         DropDowns.selectDropdownByVisibleText(IncidentPage.getSearchDropDown(driver), "Number", "Search Drop Down");
								}
						        IncidentReusables.searchIncidentTicketFromQueue(driver, incNumber);
						        incidentState=IncidentPage.getIncidentStatusfromQueue(driver, incNumber).getText();
						        if (incidentState.equalsIgnoreCase("New")) {
						        	Thread.sleep(3000);
						        	IncidentPage.getIncidentNumberfromQueue(driver, incNumber).click();
						        	WaitUtils.waitForPageToLoad(driver, 30);
						        	
						        	 
						        	//Update the status from New to Work in Progress
						        	DropDowns.selectDropdownByVisibleText(IncidentPage.getStateDropdown(driver), "Work in Progress", "State");
						        	IncidentPage.getAssignedToEdt(driver).sendKeys(assignedTo);
						        	IncidentPage.getAssignedToEdt(driver).sendKeys(Keys.ENTER);
						        	Thread.sleep(2000);
						     
						        	WaitUtils.waitForXpathPresent(driver, "//button[text()='Save']");
									driver.findElement(By.xpath("//button[text()='Save']")).click();
									//driver.findElement(By.xpath("//*[@id='4f6a779e13987200f05c7e276144b0b1']")).click();
									driver.findElement(By.linkText("Create Incident Alert")).click();
									//reading incident alert values from excel
									SourceCI=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 12, 20);
									Incidentowner=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 12, 21);
									Incidentmanagergroup=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 12, 22);
									Incidentmanager=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 12, 23);
									Accountablebusinessunit=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 12, 24);
									Affectedbusinessunit=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 12, 25);	
									Latestupdate=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 12, 26);
								
														
									//get source CI
									
									IncidentPage.getsourceci(driver).sendKeys(SourceCI);
								
									Thread.sleep(5000);
									IncidentPage.getsourceci(driver).sendKeys(Keys.ENTER);
									//get incidentmanager value
									WaitUtils.waitForIdPresent(driver, "sys_display.incident_alert.assigned_to");
									driver.findElement(By.id("sys_display.incident_alert.assigned_to")).clear();
									IncidentPage.getincidentmanager(driver).sendKeys(Incidentmanager);
								
									Thread.sleep(5000);
									IncidentPage.getincidentmanager(driver).sendKeys(Keys.ENTER);
									
									//get incident manager group value
									WaitUtils.waitForIdPresent(driver, "sys_display.incident_alert.assignment_group");
									IncidentPage.getincidentmanagergroup(driver).sendKeys(Incidentmanagergroup);
									
									Thread.sleep(5000);
									IncidentPage.getincidentmanagergroup(driver).sendKeys(Keys.ENTER);
									Thread.sleep(5000);
									//get incidentowner value
									WaitUtils.waitForIdPresent(driver, "sys_display.incident_alert.u_incident_owner");
									driver.findElement(By.id("sys_display.incident_alert.u_incident_owner")).clear();
									IncidentPage.getincidentowner(driver).sendKeys(Incidentowner);
								
									Thread.sleep(5000);
									IncidentPage.getincidentowner(driver).sendKeys(Keys.ENTER);
									
									//get accountablebu value
									IncidentPage.getaccountabletab(driver).click();
									WaitUtils.waitForIdPresent(driver, "sys_display.incident_alert.u_accountable_bu");
									IncidentPage.getaccountablebu(driver).sendKeys(Accountablebusinessunit);
								
									Thread.sleep(5000);
									IncidentPage.getaccountablebu(driver).sendKeys(Keys.ENTER);
									//get affectedunit value
									WaitUtils.waitForXpathPresent(driver, "//*[@id='incident_alert.u_affected_bu_s_unlock']");
									IncidentPage.getaffectedunit(driver).click();
									IncidentPage.getaffectedunitedt(driver).sendKeys(Affectedbusinessunit);
									IncidentPage.getaffectedlock(driver).click();
								
									Thread.sleep(5000);
									//nextupdate date
									//String nextdate = driver.findElement(By.id("incident_alert.u_next_update_due_by.ui_policy_sensitive")).getAttribute("value");
									//IncidentPage.getnextupdate(driver).click();
									String ActuallDate=Utils.getCurrentDateTime();
									
									driver.findElement(By.id("incident_alert.u_next_update_due_by")).sendKeys(ActuallDate);
									System.out.println(ActuallDate);
									//location
									IncidentPage.getimpactedlocation(driver).click();
									//latest update
									IncidentPage.getdetailstab(driver).click();
									WaitUtils.waitForIdPresent(driver, "incident_alert.u_latest_update");
									IncidentPage.getlatestupdate(driver).sendKeys(Latestupdate+ incidentNumber);
									
									Thread.sleep(2000);
									//incident alert number
									String incidentalertnumber = driver.findElement(By.id("sys_readonly.incident_alert.number")).getAttribute("value");
								System.out.println(incidentalertnumber);
									
									/*WaitUtils.waitForXpathPresent(driver, "//button[@id='timeline']");
									driver.findElement(By.xpath( "//button[@id='timeline']")).click();
									Alert alt = driver.switchTo().alert();
									 
									String alertmessage = driver.switchTo().alert().getText();
									
									 alt.accept();*/
									// WaitUtils.waitForXpathPresent(driver, wbXpath);
									WaitUtils.waitForXpathPresent(driver, "//button[@id='timeline']");
									driver.findElement(By.xpath( "//button[@id='timeline']")).click();
									Alert alert = driver.switchTo().alert();
									alert.accept();
									try {
								        Alert alert1 = driver.switchTo().alert();
								        String alertText = alert1.getText();
								        System.out.println("Alert data: " + alertText);
								        alert1.accept();
								        } 
						        	catch(Exception e){
						    			e.getMessage();
								    }
						        	
						        }}
					
						        catch (UnhandledAlertException f) {
								    try {
								        Alert alert = driver.switchTo().alert();
								        String alertText = alert.getText();
								        System.out.println("Alert data: " + alertText);
								         Assert.fail("Unhandled alert");
								        
								        } 
								     
								    	 catch(Exception e){
								 			e.getMessage();
								    	 }	
				}
							return incidentNumber;
						}

							
						public static String CreateINCforuserimpact3(WebDriver driver, boolean ManagedIncident, int sNum, int cellNum) throws Exception
						{
							
								
								try{
								WaitUtils.waitForPageToLoad(driver, 30);
										
								
									
									//Reading values from Excel file for Standalone Incident Test Case
									businessServceValue=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 5);
									assignmentGroupValue=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 6);
									openedByGroupValue=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 8);
									userImpact=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 9, 9);
									shortDescription=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 10);
									description=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 11);			
									
									//Store the Incident number in a variable
									WaitUtils.waitForPageToLoad(driver, 50);
									WaitUtils.waitForIdPresent(driver, "sys_readonly.incident.number");
									incidentNumber=driver.findElement(By.xpath("//input[@id='sys_readonly.incident.number']")).getAttribute("value");
									ExcelUtis.writeDataIntoCell("Incident_Management_TestData.xlsx","Smoke_Suite", sNum, cellNum, incidentNumber);
									
									System.out.println(incidentNumber);
									//Business Service Field
									Thread.sleep(3000);
									//WaitUtils.waitForXpathPresent(driver, "//input[@id='sys_display.incident.u_business_service']");
									//IncidentPage.getBusinessServiceEdt(driver).sendKeys(businessServceValue);
								driver.findElement(By.id("sys_display.incident.u_business_service")).sendKeys(businessServceValue);
									
									Thread.sleep(6000);
									IncidentPage.getBusinessServiceEdt(driver).sendKeys(Keys.ENTER);
									
									//Assignment group value			
									WaitUtils.waitForIdPresent(driver, "sys_display.incident.assignment_group");
									IncidentPage.getAssignmentGroupEdt(driver).sendKeys(assignmentGroupValue);
									IncidentPage.getAssignmentGroupEdt(driver).sendKeys(Keys.ENTER);
									
											
									//OpenedByGroup value			
									WaitUtils.waitForIdPresent(driver, "sys_display.incident.u_opened_by_group");
									IncidentPage.getOpenedByGroupEdt(driver).sendKeys(openedByGroupValue);
									IncidentPage.getOpenedByGroupEdt(driver).sendKeys(Keys.ENTER);
									
											
									//User Impact Field			
									WaitUtils.waitForIdPresent(driver, "incident.u_user_impact");
									DropDowns.selectDropdownByVisibleText(IncidentPage.getUserImpactDropdown(driver), userImpact, "User impact");
									
											
									//Short Description field			
									WaitUtils.waitForIdPresent(driver, "incident.short_description");
									IncidentPage.getShortDescriptionEdt(driver).sendKeys(shortDescription+ incidentNumber);
									Thread.sleep(2000);
									//Description field
									WaitUtils.waitForIdPresent(driver, "incident.description");
									IncidentPage.getDescriptionEdt(driver).sendKeys(description+ incidentNumber);
									
									
									
									Thread.sleep(2000);	
									
									WaitUtils.waitForXpathPresent(driver, "//button[text()='Submit']");
									driver.findElement(By.xpath("//button[text()='Submit']")).click();
									
								}
								catch(Exception e){
									e.getMessage();
								}
								System.out.println("Incident with user impact 3 is created" + incidentNumber);
								return incidentNumber;
						}
						
						
						public static String CreateINCforuserimpact1(WebDriver driver, boolean ManagedIncident, int sNum, int cellNum) throws Exception
						{
							
								
								try{
								WaitUtils.waitForPageToLoad(driver, 30);
										
								
									
									//Reading values from Excel file for Standalone Incident Test Case
									businessServceValue=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 5);
									assignmentGroupValue=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 6);
									openedByGroupValue=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 8);
									userImpact=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 11, 9);
									shortDescription=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 10);
									description=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 11);			
									
									//Store the Incident number in a variable
									WaitUtils.waitForPageToLoad(driver, 50);
									WaitUtils.waitForIdPresent(driver, "sys_readonly.incident.number");
									incidentNumber=driver.findElement(By.xpath("//input[@id='sys_readonly.incident.number']")).getAttribute("value");
									ExcelUtis.writeDataIntoCell("Incident_Management_TestData.xlsx","Smoke_Suite", sNum, cellNum, incidentNumber);
											
									
									//Business Service Field
									Thread.sleep(3000);
									//WaitUtils.waitForXpathPresent(driver, "//input[@id='sys_display.incident.u_business_service']");
									//IncidentPage.getBusinessServiceEdt(driver).sendKeys(businessServceValue);
								driver.findElement(By.id("sys_display.incident.u_business_service")).sendKeys(businessServceValue);
								
									Thread.sleep(6000);
									IncidentPage.getBusinessServiceEdt(driver).sendKeys(Keys.ENTER);
									
									//Assignment group value			
									WaitUtils.waitForIdPresent(driver, "sys_display.incident.assignment_group");
									IncidentPage.getAssignmentGroupEdt(driver).sendKeys(assignmentGroupValue);
									IncidentPage.getAssignmentGroupEdt(driver).sendKeys(Keys.ENTER);
								
											
									//OpenedByGroup value			
									WaitUtils.waitForIdPresent(driver, "sys_display.incident.u_opened_by_group");
									IncidentPage.getOpenedByGroupEdt(driver).sendKeys(openedByGroupValue);
									IncidentPage.getOpenedByGroupEdt(driver).sendKeys(Keys.ENTER);
								
									//User Impact Field			
									WaitUtils.waitForIdPresent(driver, "incident.u_user_impact");
									DropDowns.selectDropdownByVisibleText(IncidentPage.getUserImpactDropdown(driver), userImpact, "User impact");
																			
									//Short Description field			
									WaitUtils.waitForIdPresent(driver, "incident.short_description");
									IncidentPage.getShortDescriptionEdt(driver).sendKeys(shortDescription+ incidentNumber);
									Thread.sleep(2000);
									//Description field
									WaitUtils.waitForIdPresent(driver, "incident.description");
									IncidentPage.getDescriptionEdt(driver).sendKeys(description+ incidentNumber);
									
									
									
									Thread.sleep(2000);	
									
									WaitUtils.waitForXpathPresent(driver, "//button[text()='Submit']");
									driver.findElement(By.xpath("//button[text()='Submit']")).click();
								
									
								}
								catch(Exception e){
									e.getMessage();
								}
								System.out.println("incident with user impact 1 is created" + incidentNumber);
								return incidentNumber;
						}
						
						public static String CreateINCforuserimpact2(WebDriver driver, boolean ManagedIncident, int sNum, int cellNum) throws Exception
						{
							
								
								try{
								WaitUtils.waitForPageToLoad(driver, 30);
										
								
									
									//Reading values from Excel file for Standalone Incident Test Case
									businessServceValue=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 5);
									assignmentGroupValue=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 6);
									openedByGroupValue=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 8);
									userImpact=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 10, 9);
									shortDescription=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 10);
									description=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 11);			
									
									//Store the Incident number in a variable
									WaitUtils.waitForPageToLoad(driver, 50);
									WaitUtils.waitForIdPresent(driver, "sys_readonly.incident.number");
									incidentNumber=driver.findElement(By.xpath("//input[@id='sys_readonly.incident.number']")).getAttribute("value");
									ExcelUtis.writeDataIntoCell("Incident_Management_TestData.xlsx","Smoke_Suite", sNum, cellNum, incidentNumber);
								
									
									//Business Service Field
									Thread.sleep(3000);
									//WaitUtils.waitForXpathPresent(driver, "//input[@id='sys_display.incident.u_business_service']");
									//IncidentPage.getBusinessServiceEdt(driver).sendKeys(businessServceValue);
								driver.findElement(By.id("sys_display.incident.u_business_service")).sendKeys(businessServceValue);
								
									Thread.sleep(6000);
									IncidentPage.getBusinessServiceEdt(driver).sendKeys(Keys.ENTER);
									
									//Assignment group value			
									WaitUtils.waitForIdPresent(driver, "sys_display.incident.assignment_group");
									IncidentPage.getAssignmentGroupEdt(driver).sendKeys(assignmentGroupValue);
									IncidentPage.getAssignmentGroupEdt(driver).sendKeys(Keys.ENTER);
								
											
									//OpenedByGroup value			
									WaitUtils.waitForIdPresent(driver, "sys_display.incident.u_opened_by_group");
									IncidentPage.getOpenedByGroupEdt(driver).sendKeys(openedByGroupValue);
									IncidentPage.getOpenedByGroupEdt(driver).sendKeys(Keys.ENTER);
									
											
									//User Impact Field			
									WaitUtils.waitForIdPresent(driver, "incident.u_user_impact");
									DropDowns.selectDropdownByVisibleText(IncidentPage.getUserImpactDropdown(driver), userImpact, "User impact");
								
											
									//Short Description field			
									WaitUtils.waitForIdPresent(driver, "incident.short_description");
									IncidentPage.getShortDescriptionEdt(driver).sendKeys(shortDescription+ incidentNumber);
									Thread.sleep(2000);
									//Description field
									WaitUtils.waitForIdPresent(driver, "incident.description");
									IncidentPage.getDescriptionEdt(driver).sendKeys(description+ incidentNumber);
									
									
									
									Thread.sleep(2000);	
									
									WaitUtils.waitForXpathPresent(driver, "//button[text()='Submit']");
									driver.findElement(By.xpath("//button[text()='Submit']")).click();
								
									
								}
								
								
								
								catch(Exception e){
									e.getMessage();
								}
								System.out.println("incident with user impact 2 is created" + incidentNumber);
								return incidentNumber;
						}
						
						public static String ReassignIM(WebDriver driver, String incNumber) throws Exception
						{
						try
						{
						WaitUtils.waitForPageToLoad(driver, 30);
								
								//Search for the Incident Ticket
								incNumber=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 2);
								assignedTo=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 5, 7);
								worknotes = ExcelUtis.getData("Incident_Management_TestData.xlsx" , "Smoke_Suite", 5, 18);
								ExcelUtis.writeDataIntoCell("Incident_Management_TestData.xlsx","Smoke_Suite", 5, 2, incNumber);
								
								WaitUtils.waitForTitleIs(driver, "Incidents | ServiceNow");			
								if(!IncidentPage.getSearchDropDown(driver).getAttribute("value").equalsIgnoreCase("number")){
									 WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']//select");
							         DropDowns.selectDropdownByVisibleText(IncidentPage.getSearchDropDown(driver), "Number", "Search Drop Down");
								}
							    IncidentReusables.searchIncidentTicketFromQueue(driver, incNumber);
							    incidentState=IncidentPage.getIncidentStatusfromQueue(driver, incNumber).getText();
							    if (incidentState.equalsIgnoreCase("New")) {
							    	Thread.sleep(3000);
							    	IncidentPage.getIncidentNumberfromQueue(driver, incNumber).click();
							    	WaitUtils.waitForPageToLoad(driver, 30);
							    	
							    	//Update the status from New to Work in Progress
							    	DropDowns.selectDropdownByVisibleText(IncidentPage.getStateDropdown(driver), "Work in Progress", "State");
							    	IncidentPage.getAssignedToEdt(driver).sendKeys(assignedTo);
							    	IncidentPage.getAssignedToEdt(driver).sendKeys(Keys.ENTER);
							    	Thread.sleep(2000);
							    	WaitUtils.waitForXpathPresent(driver, "//button[text()='Save']");
									driver.findElement(By.xpath("//button[text()='Save']")).click();
							        	 
							System.out.println("before reassigning" + assignmentGroupValue);
								
								 
							IncidentTask.getroutingsituationbtn(driver).click();
							Thread.sleep(2000);
							driver.switchTo().frame(1);

							
							DropDowns.selectDropdownByVisibleText(IncidentTask.getroutingoption(driver), "Reassign"," Routing Option");
							Assignmentgroup1=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 9, 6);
							additionalcomments = ExcelUtis.getData("Incident_Management_TestData.xlsx", "Smoke_Suite", 3, 19);
							
							WaitUtils.waitForIdPresent(driver, "sys_display.u_task_route.u_assignment_group");
							Thread.sleep(2000);
							IncidentPage.Assignmentgroupedt(driver).sendKeys(Assignmentgroup1);
							Thread.sleep(2000);
						    IncidentPage.Assignmentgroupedt(driver).sendKeys(Keys.ENTER);
						  Alert alert =  driver.switchTo().alert();
						  alert.accept();
						  Thread.sleep(2000);
						  WaitUtils.waitForXpathPresent(driver, "//*[@id='u_task_route.u_additional_comments']");
						  TextBoxes.enterTextValue(IncidentPage.Additionalcommentstask(driver), additionalcomments + incNumber, "Additional comments");
						   
						  
						   Thread.sleep(2000);
						   
						  
						    WaitUtils.waitForXpathPresent(driver, "//button[text()='Next']");
							driver.findElement(By.xpath("//button[text()='Next']")).click();
							
							
								driver.switchTo().defaultContent();
							}}
						catch(Exception e){
							e.getMessage();
						}
						System.out.println(incidentNumber);
						System.out.println("After reassigning group" + Assignmentgroup1 );
						return incidentNumber;
						}
						public static String Copyincident(WebDriver driver, boolean ManagedIncident, int sNum, int cellNum) throws Exception
						{
							try{
								WaitUtils.waitForPageToLoad(driver, 30);

								//Reading values from Excel file for Standalone Incident Test Case
								businessServceValue=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 5);
								assignmentGroupValue=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 6);
								openedByGroupValue=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 8);
								userImpact=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 9);
								shortDescription=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 10);
								description=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 11);			
								
								//Store the Incident number in a variable
								WaitUtils.waitForPageToLoad(driver, 50);
								WaitUtils.waitForIdPresent(driver, "sys_readonly.incident.number");
								incidentNumber=driver.findElement(By.xpath("//input[@id='sys_readonly.incident.number']")).getAttribute("value");
								ExcelUtis.writeDataIntoCell("Incident_Management_TestData.xlsx","Smoke_Suite", sNum, cellNum, incidentNumber);
							
								//Business Service Field
								Thread.sleep(3000);
								//WaitUtils.waitForXpathPresent(driver, "//input[@id='sys_display.incident.u_business_service']");
								//IncidentPage.getBusinessServiceEdt(driver).sendKeys(businessServceValue);
							driver.findElement(By.id("sys_display.incident.u_business_service")).sendKeys(businessServceValue);
							
								Thread.sleep(6000);
								IncidentPage.getBusinessServiceEdt(driver).sendKeys(Keys.ENTER);
								
								//Assignment group value			
								WaitUtils.waitForIdPresent(driver, "sys_display.incident.assignment_group");
								IncidentPage.getAssignmentGroupEdt(driver).sendKeys(assignmentGroupValue);
								IncidentPage.getAssignmentGroupEdt(driver).sendKeys(Keys.ENTER);
								
										
								//OpenedByGroup value			
								WaitUtils.waitForIdPresent(driver, "sys_display.incident.u_opened_by_group");
								IncidentPage.getOpenedByGroupEdt(driver).sendKeys(openedByGroupValue);
								IncidentPage.getOpenedByGroupEdt(driver).sendKeys(Keys.ENTER);
								
										
								//User Impact Field			
								WaitUtils.waitForIdPresent(driver, "incident.u_user_impact");
								DropDowns.selectDropdownByVisibleText(IncidentPage.getUserImpactDropdown(driver), userImpact, "User impact");
							
										
								//Short Description field			
								WaitUtils.waitForIdPresent(driver, "incident.short_description");
								IncidentPage.getShortDescriptionEdt(driver).sendKeys(shortDescription+ incidentNumber);
								
								//Description field
								WaitUtils.waitForIdPresent(driver, "incident.description");
								IncidentPage.getDescriptionEdt(driver).sendKeys(description+ incidentNumber);
								
								Thread.sleep(2000);			
								
								//Click on Submit button
								WaitUtils.waitForXpathPresent(driver, "//button[text()='Save']");
								driver.findElement(By.xpath("//button[text()='Save']")).click();
								WaitUtils.waitForXpathPresent(driver,"//button[@class='btn btn-icon icon-menu navbar-btn additional-actions-context-menu-button']");
								driver.findElement(By.xpath("//button[@class='btn btn-icon icon-menu navbar-btn additional-actions-context-menu-button']")).click();
								Thread.sleep(2000);
								
								//copy incident
								WaitUtils.waitForXpathPresent(driver, "//div[text()='Copy Incident']");
								driver.findElement(By.xpath("//div[text()='Copy Incident']")).click();
								Thread.sleep(2000);
								businessServceValue=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 5);
								//Business Service Field
								IncidentPage.getBusinessServiceEdt(driver).sendKeys(businessServceValue);
							
								Thread.sleep(5000);
								IncidentPage.getBusinessServiceEdt(driver).sendKeys(Keys.ENTER);
								Thread.sleep(2000);
								openedByGroupValue=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 8);
								userImpact=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 10, 9);
								
								//OpenedByGroup value			
								WaitUtils.waitForIdPresent(driver, "sys_display.incident.u_opened_by_group");
								IncidentPage.getOpenedByGroupEdt(driver).sendKeys(openedByGroupValue);
								IncidentPage.getOpenedByGroupEdt(driver).sendKeys(Keys.ENTER);
							
								Thread.sleep(2000);		
								//User Impact Field			
								WaitUtils.waitForIdPresent(driver, "incident.u_user_impact");
								DropDowns.selectDropdownByVisibleText(IncidentPage.getUserImpactDropdown(driver), userImpact, "User impact");
							
								WaitUtils.waitForXpathPresent(driver, "//button[text()='Save']");
								driver.findElement(By.xpath("//button[text()='Save']")).click();
								
								WaitUtils.waitForPageToLoad(driver, 50);
								WaitUtils.waitForIdPresent(driver, "sys_readonly.incident.number");
								copiedincidentNumber=driver.findElement(By.xpath("//input[@id='sys_readonly.incident.number']")).getAttribute("value");
								ExcelUtis.writeDataIntoCell("Incident_Management_TestData.xlsx","Smoke_Suite", sNum, cellNum, copiedincidentNumber);
								
							}
							catch(Exception e){
								e.getMessage();
							}
							System.out.println("Original incident number is :" +incidentNumber);
							System.out.println("copied incident number is :" +copiedincidentNumber);
							return copiedincidentNumber;
						}

						//To validate active IM task
						public static void cancelIncident1(WebDriver driver, String incNumber) throws Exception
						{
							try{
								WaitUtils.waitForPageToLoad(driver, 30);
								
								//Search for the Incident Ticket
								incNumber=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 2);
								assignedTo=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 5, 7);
								worknotes = ExcelUtis.getData("Incident_Management_TestData.xlsx" , "Smoke_Suite", 5, 18);
								ExcelUtis.writeDataIntoCell("Incident_Management_TestData.xlsx","Smoke_Suite", 5, 2, incNumber);
											
								if(!IncidentPage.getSearchDropDown(driver).getAttribute("value").equalsIgnoreCase("number")){
									 WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']//select");
							         DropDowns.selectDropdownByVisibleText(IncidentPage.getSearchDropDown(driver), "Number", "Search Drop Down");
								}
						        IncidentReusables.searchIncidentTicketFromQueue(driver, incNumber);
						        incidentState=IncidentPage.getIncidentStatusfromQueue(driver, incNumber).getText();
						        if (incidentState.equalsIgnoreCase("Work in Progress")) {
						        	Thread.sleep(3000);
						        	IncidentPage.getIncidentNumberfromQueue(driver, incNumber).click();
						        	WaitUtils.waitForPageToLoad(driver, 30);
						        	
						        	 
						        	//Update the status from work in progress to cancel
						        	
						        	
						        	
								DropDowns.selectDropdownByVisibleText(IncidentPage.getStateDropdown(driver), "Cancelled", "State");
								 
						        	 WaitUtils.waitForXpathPresent(driver, "//button[text()='Save']");
										driver.findElement(By.xpath("//button[text()='Save']")).click();
										Thread.sleep(2000);
										Alert alt = driver.switchTo().alert();
										 
										String alertmessage = driver.switchTo().alert().getText();
										
										 alt.accept();
										 System.out.println(alertmessage);
										 WaitUtils.waitForXpathPresent(driver, "//button[text()='Update']");
											driver.findElement(By.xpath("//button[text()='Update']")).click();
										try {
									        Alert alert = driver.switchTo().alert();
									        String alertText = alert.getText();
									     
									    
									        Assert.fail("Unexpected alert !!!! ");
									        } 
										catch(Exception e){
											e.getMessage();
									    }
							        	
							        }}

							        catch (UnhandledAlertException f) {
									    try {
									        Alert alert = driver.switchTo().alert();
									        String alertText = alert.getText();
									        System.out.println("Alert data: " + alertText);
									         Assert.fail("Unhandled alert");
									        
									        } 
									    catch(Exception e){
											e.getMessage();
									    }	
									    
							        }
							
							}
						
						//Create incident from intake *Rashmi*
						public static String createIncidentfromIntake(WebDriver driver,int sNum, int cellNum) throws Exception
						{
							try
							{
								WaitUtils.waitForPageToLoad(driver, 30);
								//Reading values from excel for creating incident ticket
								
								requestedby=ExcelUtis.getData("Intake_Management_Testdata1.xlsx","Smoke_Suite",1,4);
								source=ExcelUtis.getData("Intake_Management_Testdata1.xlsx", "Smoke_Suite", 1, 5);
								location=ExcelUtis.getData("Intake_Management_Testdata1.xlsx", "Smoke_Suite", 1, 7);
								businessServiceCatalog=ExcelUtis.getData("Intake_Management_Testdata1.xlsx", "Smoke_Suite", 1, 8);
								situation=ExcelUtis.getData("Intake_Management_Testdata1.xlsx", "Smoke_Suite", 1,9);
								openedbygroup=ExcelUtis.getData("Intake_Management_Testdata1.xlsx", "Smoke_Suite", 1,11);
								shortDescription=ExcelUtis.getData("Intake_Management_Testdata1.xlsx", "Smoke_Suite", 1,12);
								description=ExcelUtis.getData("Intake_Management_Testdata1.xlsx", "Smoke_Suite", 1,13);
								
								//Store incident number in a variable
								/*WaitUtils.waitForPageToLoad(driver, 30);
								WaitUtils.waitForIdPresent(driver, "sys_readonly.incident.number");
								incidentNumber=driver.findElement(By.xpath("//input[@id='sys_readonly.incident.number']")).getAttribute("value");
								ExcelUtis.writeDataIntoCell("Incident_Management_TestData.xlsx","Smoke_Suite", sNum, cellNum, incidentNumber);
								ExtentReport.reportLog(LogStatus.INFO, "Incident number captured: "+incidentNumber);
								ReporterLogs.log("Incident number captured: "+incidentNumber, "info");*/
								
								//requested by field
								WaitUtils.waitForIdPresent(driver, "sys_display.ticket.u_requested_by");
								Thread.sleep(2000);
								IntakePage.getrequestedbyEdt(driver).sendKeys(requestedby);
								
								//source field
								WaitUtils.waitForIdPresent(driver, "ticket.contact_type");
								DropDowns.selectDropdownByVisibleText(IntakePage.getsourceEdt(driver), source, "Source");
								
								//loctaion field
								WaitUtils.waitForIdPresent(driver, "sys_display.ticket.location");
								IntakePage.getlocationEdt(driver).sendKeys(location);
								
								//business service catalog field
								
								WaitUtils.waitForIdPresent(driver, "sys_display.ticket.u_business_service_catalog");
								IntakePage.getbusinessServiceCatalogEdt(driver).sendKeys(businessServiceCatalog);
								
								//Situation field
								
								WaitUtils.waitForIdPresent(driver, "sys_display.ticket.u_symptom_map");
								Thread.sleep(5000);
								//Intakepage.getsituationEdt(driver).sendKeys(situation);
								TextBoxes.enterTextValue(IntakePage.getsituationEdt(driver), situation, "Situation");                    
								IntakePage.getsituationEdt(driver).sendKeys(Keys.ENTER);
								IntakePage.getsituationEdt(driver).sendKeys(Keys.DOWN);
								IntakePage.getsituationEdt(driver).sendKeys(Keys.ENTER);
						        Thread.sleep(5000);
								
								//openedby group field
								
								WaitUtils.waitForIdPresent(driver, "sys_display.ticket.u_opened_by_group");
								IntakePage.getopenedbygroup(driver).sendKeys(openedbygroup);
								 TextBoxes.enterTextValue(IntakePage.getshortdescEdt(driver), shortDescription, "Short Description");
						         Thread.sleep(2000);
						         TextBoxes.enterTextValue(IntakePage.getdescEdt(driver), description, "Description");
						         Thread.sleep(2000);
						         
								
								//shortdescription field
								
								/*WaitUtils.waitForIdPresent(driver, "ticket.short_description");
								Intakepage.getshortdescEdt(driver).sendKeys(shortDescription);
								
								//description field
								
								WaitUtils.waitForIdPresent(driver, "ticket.description");
								Intakepage.getdescEdt(driver).sendKeys(description);
								
								Thread.sleep(2000);*/
								
								//Click on Next button
							//WaitUtils.waitForXpathPresent(driver, "//button[text()='Next']");
								WebDriverWait wait = new WebDriverWait(driver,50);
								wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Next']")));
								
								driver.findElement(By.xpath("//button[text()='Next']")).click();
								
								//Store the Incident number in a variable
								WaitUtils.waitForPageToLoad(driver, 50);
								WaitUtils.waitForIdPresent(driver, "sys_readonly.incident.number");
								incidentNumber=driver.findElement(By.xpath("//input[@id='sys_readonly.incident.number']")).getAttribute("value");
								
								System.out.println(incidentNumber);
								ExcelUtis.writeDataIntoCell("Incident_Management_TestData.xlsx","Smoke_Suite", sNum, cellNum, incidentNumber);
								//ExtentReport.reportLog(LogStatus.INFO, "Incident number captured: "+incidentNumber);
								
								assignmentGroupValue=ExcelUtis.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 6);
								
								//Assignment group value			
								WaitUtils.waitForIdPresent(driver, "sys_display.incident.assignment_group");
								IncidentPage.getAssignmentGroupEdt(driver).sendKeys(assignmentGroupValue);

								
										
								Thread.sleep(2000);
								WaitUtils.waitForXpathPresent(driver, "//button[text()='Save']");
								driver.findElement(By.xpath("//button[text()='Save']")).click();
								
								
								
								
								
								
								
								try {
							        Alert alert = driver.switchTo().alert();
							        String alertText = alert.getText();
							       
							      //  ExtentReport.reportLog(LogStatus.FAIL, "Alert message: " + alertText);
							        Assert.fail("Unexpected alert !!!! ");
							        } 
								catch (Exception e) {
						       	 System.out.println("handled");
							    }	
								
								
							
						}catch (UnhandledAlertException f) {
						    try {
						        Alert alert = driver.switchTo().alert();
						        String alertText = alert.getText();
						        System.out.println("Alert data: " + alertText);
						        Assert.fail("Unhandled alert");
						        
						        } 
						    catch (NoAlertPresentException e) {
						        e.printStackTrace();
						    }		
						}	
							System.out.println("Incident created from intake is" + incidentNumber);
							return incidentNumber;
						
						}
			
						

}
		