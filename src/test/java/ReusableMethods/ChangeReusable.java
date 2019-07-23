package ReusableMethods;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.pages.ChangePage;
//import com.servicenow.genericlibraries.ReporterLogs;





public class ChangeReusable {
	static String changeId=null;
	static String assignmentGroup = null;
	static String configurationItem = null;
	static String shortDescription = null;
	static String description = null;
	static String reasonForChange = null;
	static String customerImpactDuringChange = null;
	static String implementationPlan= null;
	static String testPlan = null;
	static String backoutPlan = null;
	static String plannedStartDate = null;
	static String plannedEndtDate=null;
	static String crNumber=null;
	static String assignedTo=null;
	static String configItem=null;
	static String ManTestCase=null;
	static String tara=null;
	
	
	public static String createChange(WebDriver driver, int serialnum,int cnum){	
		try{
			
				Thread.sleep(3000);
				ChangePage.getNormalLnk(driver).click();
				//ExtentReport.reportLog(LogStatus.INFO, "Creating Normal Change Ticket");
				assignmentGroup = ExcelUtis.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 5);
				configurationItem = ExcelUtis.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 6);
				shortDescription = ExcelUtis.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 7);
				description = ExcelUtis.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 8);
				reasonForChange = ExcelUtis.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 9);
				changeId=ChangePage.getChangeNumberEdt(driver).getAttribute("value");
				ExcelUtis.writeDataIntoCell("Change_Management_TestData.xlsx", "Smoke_Suite", serialnum, cnum, changeId);
				//ExtentReport.reportLog(LogStatus.INFO, "Change Id : "+changeId);
				TextBoxes.enterTextValue(ChangePage.getAssignmentGrpEdt(driver), assignmentGroup, "Assignement Group Field");
			
				Thread.sleep(5000);
				TextBoxes.enterTextValue(ChangePage.getConfigurationItemEdt(driver), configurationItem, "Configuration Item");
				
				WaitUtils.waitForIdPresent(driver, "change_request.short_description");
				TextBoxes.enterTextValue(ChangePage.getShortDescriptionEdt(driver), shortDescription, "Short Description");
				
				WaitUtils.waitForIdPresent(driver, "change_request.description");
				TextBoxes.enterTextValue(ChangePage.getDescriptionEdt(driver), description, "Description");	
				
				WaitUtils.waitForXpathPresent(driver, "//span[contains(text(),'Planning') and @class='tab_caption_text']");
				ChangePage.getPlanningTab(driver).click();
				WaitUtils.waitForIdPresent(driver, "change_request.u_reason_for_change");
				TextBoxes.enterTextValue(ChangePage.getReasonForChangeEdt(driver), reasonForChange, "Reason For Change");
			//	ReporterLogs.log("Assignment Group field is entered successfully "+ reasonForChange, "info");		
				WaitUtils.waitForXpathPresent(driver, "//span[contains(text(),'Schedule')]");
				ChangePage.getScheduleTab(driver).click();
				String requestedByDate = Utils.getCurrentDateTime();
				TextBoxes.enterTextValue(ChangePage.getRequestedByDateEdt(driver), requestedByDate, "Requested By Date");
			//	ReporterLogs.log("Requested By Date field is entered successfully "+ requestedByDate, "info");
				WaitUtils.waitForIdPresent(driver, "sysverb_insert");
				ChangePage.getSubmitBtn(driver).click();
				
				//driver.findElement(By.xpath("//button[text()='Save']")).click();
				/*crNumber = ExcelUtils.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 2, 2);
				ServiceNowUtils.navigateToAllQueueForDesiredModule(driver, "change");
				ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
				ChangePage.getChangeNumberFromQueue(driver, crNumber).click();	
				WaitUtils.waitForPageToLoad(driver, 10);*/
				//ChangePage.getSubmitForPlanningBtn(driver).click();
				//Thread.sleep(50000);
				boolean b=driver.findElement(By.xpath("//button[text()='Update']")).isDisplayed();
				System.out.println(b);
				
				}
		catch(Exception e){
			e.getMessage();
			//System.out.println("handeled");

			
		}
		 System.out.println("Final"+changeId);
		return changeId;
	
	
	}
	
	
public static void searchDesiredChangeTicket(WebDriver driver,String ticketNumber) throws InterruptedException{
		
		if(ChangePage.getSearchDropDown(driver).getAttribute("value").equalsIgnoreCase("number")){
			Thread.sleep(5000);
			TextBoxes.enterTextValue(ChangePage.getSearchChangeEdt(driver), ticketNumber, "Searching for change "+ticketNumber);
			ChangePage.getSearchChangeEdt(driver).sendKeys(Keys.ENTER);
			WaitUtils.waitForPageToLoad(driver, 10);
		}else{
				//WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']//select");
			Thread.sleep(3000);
				DropDowns.selectDropdownByVisibleText(ChangePage.getSearchDropDown(driver), "Number", "Search Drop Down");
				TextBoxes.enterTextValue(ChangePage.getSearchChangeEdt(driver), ticketNumber, "Search Change ");
			//	ReporterLogs.log("Entering change in the Search Text "+ticketNumber, "info");
				ChangePage.getSearchChangeEdt(driver).sendKeys(Keys.ENTER);
				WaitUtils.waitForPageToLoad(driver, 10);
		}

	
	
	
	

}
public static void verifyStateOfChangeTicket(WebDriver driver, String expectedStateOfTicket,String crNum,int reqRow, int reqcol) {
	try{
			String actualStateOfTicket = DropDowns.getFirstSelectedOptionName(ChangePage.getChangeStateEdtDropDown(driver), "State Drop Down");
			System.out.println(actualStateOfTicket);
			//ReporterLogs.log("State of the Change is :"+actualStateOfTicket);
			if(actualStateOfTicket.equalsIgnoreCase(expectedStateOfTicket)){
				Assert.assertEquals(actualStateOfTicket, expectedStateOfTicket);
				//ExtentReport.reportLog(LogStatus.PASS, "Actual State of the Change Ticket : "+actualStateOfTicket);
				//ReporterLogs.log("Successfully updated Change with Id "+crNum, "info");
				ExcelUtis.writeDataIntoCell("Change_Management_TestData.xlsx", "Smoke_Suite", reqRow, reqcol, crNum);
				ExcelUtis.writeDataIntoCell("Change_Management_TestData.xlsx", "Smoke_Suite", reqRow, 3, actualStateOfTicket);
				ExcelUtis.writeDataIntoCell("Change_Management_TestData.xlsx", "Smoke_Suite", reqRow, 4, "Passed");
			}else{
				//ExtentReport.reportLog(LogStatus.FAIL, "State of the Change ticket are not same : "+actualStateOfTicket);
				//ReporterLogs.log("Unable to update Change with Id "+crNum, "error");
				ExcelUtis.writeDataIntoCell("Change_Management_TestData.xlsx", "Smoke_Suite", reqRow, reqcol, crNum);
				ExcelUtis.writeDataIntoCell("Change_Management_TestData.xlsx", "Smoke_Suite", reqRow, 3, actualStateOfTicket);
				ExcelUtis.writeDataIntoCell("Change_Management_TestData.xlsx", "Smoke_Suite", reqRow, 4, "Failed");
				Assert.assertEquals(actualStateOfTicket, expectedStateOfTicket);
}
	}catch(Exception e){
		//ReporterLogs.log("Exception :"+e.getMessage(),"error");
	}
}


public static void moveToAssessmentState(WebDriver driver){
	
	try{
			customerImpactDuringChange = ExcelUtis.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 10);
			implementationPlan= ExcelUtis.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 11);
			testPlan = ExcelUtis.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 12);
			backoutPlan = ExcelUtis.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 13);
			ChangePage.getPlanningTab(driver).click();
			WaitUtils.waitForIdPresent(driver, "change_request.u_customer_impact");
			TextBoxes.enterTextValue(ChangePage.getCustomerImpactDuringChangeEdt(driver), customerImpactDuringChange, "Customer Impact During Change");	
			//ReporterLogs.log("Assignment Group field is entered successfully "+ customerImpactDuringChange, "info");
			WaitUtils.waitForIdPresent(driver, "change_request.change_plan");
			TextBoxes.enterTextValue(ChangePage.getImplementationPlanEdt(driver), implementationPlan, "Implementation Plan");
			//ReporterLogs.log("Assignment Group field is entered successfully "+ implementationPlan, "info");
			WaitUtils.waitForIdPresent(driver, "change_request.test_plan");
			TextBoxes.enterTextValue(ChangePage.getTestPlanEdt(driver), testPlan, "Test Plan");
			//ReporterLogs.log("Assignment Group field is entered successfully "+ testPlan, "info");
			WaitUtils.waitForIdPresent(driver, "change_request.backout_plan");
			TextBoxes.enterTextValue(ChangePage.getBackoutPlanEdt(driver), backoutPlan, "Backout Plan");
			//ReporterLogs.log("Assignment Group field is entered successfully "+ backoutPlan, "info");
			ChangePage.getRiskAndImpactTab(driver).click();
			DropDowns.selectDropdownByIndex(ChangePage.getEnvironmentInWhichChangeIsToBeExecutedDropDown(driver), 1, "---Environment In Which Change Is To Be Executed Drop Down---");
			DropDowns.selectDropdownByIndex(ChangePage.getExpectedServiceImpactDuringExecutionOfTheChangeDropDown(driver), 1, "---Expected Service Impact During Execution Of The Change Drop Down---");
			DropDowns.selectDropdownByIndex(ChangePage.getPotentialServiceImpactDuringExecutionOfTheChangeDropDown(driver), 1, "---Potential Service Impact During Execution Of The Change Drop Down---");
			DropDowns.selectDropdownByIndex(ChangePage.getUserSupportedByTheAssetDropDown(driver), 1, "---Users Supported By The Asset Drop Down---");
			DropDowns.selectDropdownByIndex(ChangePage.getBackOutRecoveryComplexityDropDown(driver), 1, "---Back Out Recovery Complexity Drop Down---");
			DropDowns.selectDropdownByIndex(ChangePage.getFamilarityWithChangeDropDown(driver), 1, "---Familarity With Change Drop Down---");
			DropDowns.selectDropdownByIndex(ChangePage.getRedundantServiceDropDown(driver), 1, "---Redundant Service Drop Down---");
			ChangePage.getSubmitForAssessmentBtn(driver).click();
			Thread.sleep(10000);
	}catch(Exception e){
	//ReporterLogs.log("Exception encountred "+ e.getMessage(), "error");
}
	
}
	
	public static void schedule(WebDriver driver)
	{
		plannedStartDate = Utils.getDesiredDateAndTime(1);
		plannedEndtDate= Utils.getDesiredDateAndTime(2);
		WaitUtils.waitForXpathPresent(driver, "//span[contains(text(),'Schedule')]");
		ChangePage.getScheduleTab(driver).click();
		WaitUtils.waitForIdPresent(driver, "change_request.start_date");
		TextBoxes.enterTextValue(ChangePage.getPlannedStartDateEdt(driver), plannedStartDate, "Planned Start Date");
		//ReporterLogs.log("Requested By Date field is entered successfully "+ plannedStartDate, "info");
		WaitUtils.waitForIdPresent(driver, "change_request.end_date");
		TextBoxes.enterTextValue(ChangePage.getPlannedEndDateEdt(driver), plannedEndtDate, "Planned End Date");
		
		
	}
	
	
	
	
	
	public static void moveToApprovalState(WebDriver driver) {
		try{
				
				ChangePage.getRequestImpementationApprovalBtn(driver).click();
				Thread.sleep(5000);
			
				
				
				
		}catch(Exception e){
			//ReporterLogs.log("Exception :"+e.getMessage(),"error");
		}	
	}
	
	
	
	
	public static void FinalReport(WebDriver driver, String expectedStateOfTicket,String crNum,int reqRow, int reqcol) throws FileNotFoundException, IOException {
		
		try{
			String actualStateOfTicket = DropDowns.getFirstSelectedOptionName(ChangePage.getChangeStateEdtDropDown(driver), "State Drop Down");
			System.out.println(actualStateOfTicket);
			//ReporterLogs.log("State of the Change is :"+actualStateOfTicket);
			  ManTestCase  = ExcelUtis.getData("SmokeTestReport.xlsx", "Change", 1, 3);
			if(actualStateOfTicket.equalsIgnoreCase(expectedStateOfTicket)){
				Assert.assertEquals(actualStateOfTicket, expectedStateOfTicket);
				//ExtentReport.reportLog(LogStatus.PASS, "Actual State of the Change Ticket : "+actualStateOfTicket);
				//ReporterLogs.log("Successfully updated Change with Id "+crNum, "info");
				
				ExcelUtis.writeDataIntoCell("Change_Management_TestData.xlsx", "Smoke_Suite", reqRow, reqcol, crNum);
				ExcelUtis.writeDataIntoCell("Change_Management_TestData.xlsx", "Smoke_Suite", reqRow, 3, actualStateOfTicket);
				ExcelUtis.writeDataIntoCell("Change_Management_TestData.xlsx", "Smoke_Suite", reqRow, 4, "Passed");
                System.out.println(ManTestCase);
				
				
					ExcelUtis.writeDataIntoCell("SmokeTestReport.xlsx", "Change", reqRow, 4, "Passed");
				
			}else{
				//ExtentReport.reportLog(LogStatus.FAIL, "State of the Change ticket are not same : "+actualStateOfTicket);
				//ReporterLogs.log("Unable to update Change with Id "+crNum, "error");
				ExcelUtis.writeDataIntoCell("Change_Management_TestData.xlsx", "Smoke_Suite", reqRow, reqcol, crNum);
				ExcelUtis.writeDataIntoCell("Change_Management_TestData.xlsx", "Smoke_Suite", reqRow, 3, actualStateOfTicket);
				ExcelUtis.writeDataIntoCell("Change_Management_TestData.xlsx", "Smoke_Suite", reqRow, 4, "Failed");
				ExcelUtis.writeDataIntoCell("SmokeTestReport.xlsx", "Change", reqRow, 4, "Failed");
				Assert.assertEquals(actualStateOfTicket, expectedStateOfTicket);
				//ExcelUtils.writeDataIntoCell("SmokeTestReport.xlsx", "Change", 1, 4, "Failed");
			}
		}
			catch(Exception e)
			{
				System.out.println("handled");
			}
		
		
		
}
	
	
	
	public static void moveToCancelledState(WebDriver driver,String crNumber) {
		try{
			ChangePage.getActivityTab(driver).click();
			ChangePage.getReasonForCancellationEdt(driver).sendKeys("test Cancel");
			ChangePage.getChangeCancelBtn(driver).click();
			WaitUtils.waitForPageToLoad(driver, 20);
			ChangePage.getChangeNumberFromQueue(driver, crNumber).click();	
			WaitUtils.waitForPageToLoad(driver, 10);
			String stateofChange = DropDowns.getFirstSelectedOptionName(ChangePage.getChangeStateEdtDropDown(driver), "State Drop Down");
			System.out.println(stateofChange);
			//ReporterLogs.log("State of the Change is :Cancelled"+stateofChange);
			/*if(stateofChange.equalsIgnoreCase("")){
					Assert.assertEquals(stateofChange, "Cancelled");
					ExtentReport.reportLog(LogStatus.PASS, "Successfully Change change : "+crNumber);
					ReporterLogs.log("Successfully updated Change with Id "+crNumber, "info");
					ExcelUtils.writeDataIntoCell("Change_Management_TestData.xlsx", "Smoke_Suite", 3, 2, crNumber);
					ExcelUtils.writeDataIntoCell("Change_Management_TestData.xlsx", "Smoke_Suite", 3, 4, "Passed");
				}else{
					ExtentReport.reportLog(LogStatus.FAIL, "Unable to Cancel change : "+crNumber);
					ReporterLogs.log("Unable to update Change with Id "+crNumber, "error");
					ExcelUtils.writeDataIntoCell("Change_Management_TestData.xlsx", "Smoke_Suite", 3, 2, crNumber);
					ExcelUtils.writeDataIntoCell("Change_Management_TestData.xlsx", "Smoke_Suite", 3, 4, "Failed");
					Assert.assertEquals(stateofChange, "Planning");
		}*/
		}catch(Exception e){
			//ReporterLogs.log("Exception :"+e.getMessage(),"error");
		}		
	}
	public static void AssignedTo(WebDriver driver) throws IOException
	{
		assignedTo = ExcelUtis.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 14);
		driver.findElement(By.id("sys_display.change_task.assigned_to")).sendKeys(assignedTo);
		
	}
	
	
	
	public static void ConfigItem(WebDriver driver) throws IOException
	{
		configItem = ExcelUtis.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 6);
		System.out.println(configItem);
		driver.findElement(By.id("sys_display.change_task.cmdb_ci")).sendKeys(configItem);
		
	}
	public static void AssignmentGroup(WebDriver driver) throws IOException
	{
		assignmentGroup = ExcelUtis.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 5);
		driver.findElement(By.id("sys_display.change_task.assignment_group")).sendKeys(assignmentGroup);
		
		
		
	}
	
	
	public static void Dis(WebDriver driver) throws IOException
	{
		description = ExcelUtis.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 8);
		
		driver.findElement(By.id("change_task.description")).sendKeys(description);
	}
	
	public static void ShortDis(WebDriver driver) throws IOException
	{
		shortDescription = ExcelUtis.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 7);
		driver.findElement(By.id("change_task.short_description")).sendKeys(shortDescription);
	}
	}





