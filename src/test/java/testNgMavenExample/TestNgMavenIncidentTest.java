package testNgMavenExample;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
//import com.relevantcodes.extentreports.LogStatus;

import ReusableMethods.IncidentReusables;

import ReusableMethods.SafeLogin;
import ReusableMethods.ServiceNowUtils;

import ReusableMethods.WaitUtils;
import ReusableMethods.Capabilities;

import ReusableMethods.DropDowns;

import ReusableMethods.TextBoxes;
import com.pages.IncidentPage;
public class TestNgMavenIncidentTest {
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	ExtentTest logger;
	static String incNumber=null;
	static String errormessage=null;
	static String incidenttasknum=null;
	static WebElement element = null;
	static String Assignmentgroup1 = null;
	static String modulename=null;
	@BeforeTest
	public void startReport(){
     htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/test-output/STMExtentReport11Incident.html");
		extent = new ExtentReports ();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name", "SoftwareTestingMaterial");
		extent.setSystemInfo("Environment", "Automation Testing");
		extent.setSystemInfo("User Name", "Rashmi");
		htmlReporter.config().setDocumentTitle("Incident Requuest");
		htmlReporter.config().setReportName("Incident Request Smoke suite");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.STANDARD);
	}
	@Test(priority=0,description="Create standalone incident ticket",enabled=true)
    public void testCreateStandAloneIncidentTicket() throws Exception{
    logger = extent.createTest("Create standalone incident ticket");
    String ChromeDrivers = Capabilities.getPropertyValue("ChromeDrivers");
	System.setProperty("webdriver.chrome.driver",ChromeDrivers);
	WebDriver driver=new ChromeDriver();
    driver.get("http://thomsonreutersqa.service-now.com");
    driver.manage().window().maximize();
		SafeLogin.logInUser(driver);
       WaitUtils.waitForPageToLoad(driver, 10);			
		ServiceNowUtils.navigateToModuleName(driver,"incident");
       incNumber=IncidentReusables.createstandaloneincident1(driver, 1, 2);
		IncidentReusables.verifyIncidentCreation(driver, incNumber);
		driver.close();
		Assert.assertTrue(true);
		logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is for Creation of a incident Ticket,searching of updated INC ticket and Creation of INC ticket for User impact 4", ExtentColor.GREEN));
	}
	@Test(priority=1,description="Create managed incident ticket",enabled=true)
    public void testCreateManagedIncidentTicket() throws Exception{
       logger = extent.createTest("Create managed incident ticket");
        String ChromeDrivers = Capabilities.getPropertyValue("ChromeDrivers");
 	    System.setProperty("webdriver.chrome.driver",ChromeDrivers);
 	    WebDriver driver=new ChromeDriver();
		driver.get("http://thomsonreutersqa.service-now.com");
		driver.manage().window().maximize();
		SafeLogin.logInUser(driver);
		WaitUtils.waitForPageToLoad(driver, 10);
        ServiceNowUtils.navigateToModuleName(driver,"incident");
		incNumber=IncidentReusables.createmanagedIncident(driver,2,2);
		IncidentReusables.verifymanagedIncidentCreation(driver, incNumber);
		driver.close();
		Assert.assertTrue(true);
		logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is for Creation of a Managed Incident Ticket", ExtentColor.GREEN));
}
	@Test(priority=2,description="Validate mandatory fields in WIP state",enabled=true)
	public void testverifymandatoryfieldWIP() throws Exception{
		logger = extent.createTest("Validate mandatory fields in WIP state");
		String ChromeDrivers = Capabilities.getPropertyValue("ChromeDrivers");
 	    System.setProperty("webdriver.chrome.driver",ChromeDrivers);
 	    WebDriver driver=new ChromeDriver();
		driver.get("http://thomsonreutersqa.service-now.com");
		driver.manage().window().maximize();
		SafeLogin.logInUser(driver);
		WaitUtils.waitForPageToLoad(driver, 10);
        ServiceNowUtils.navigateToModuleName(driver,"incident");
		incNumber=IncidentReusables.createstandaloneincident1(driver, 1, 2);
		IncidentReusables.verifyIncidentCreation(driver, incNumber);
		IncidentReusables.Captureerrormessagenew1(driver, errormessage);
		driver.close();
		Assert.assertTrue(true);
		logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is for verification of mandatory field in WIP state", ExtentColor.GREEN));
	}
	 @Test(priority=3,description="Create Incident ticket from Intake",enabled=true)
		public void testcreateIncidentfromIntake() throws Exception {
			logger = extent.createTest("Create Incident ticket from Intake");
			String ChromeDrivers = Capabilities.getPropertyValue("ChromeDrivers");
	 	    System.setProperty("webdriver.chrome.driver",ChromeDrivers);
	 	    WebDriver driver=new ChromeDriver();
			driver.get("http://thomsonreutersqa.service-now.com");
			driver.manage().window().maximize();
			SafeLogin.logInUser(driver);
			WaitUtils.waitForPageToLoad(driver, 10);
			ServiceNowUtils.navigateToModuleName1(driver,"intake");
			incNumber=IncidentReusables.createIncidentfromIntake(driver, 1, 2);
			driver.close();
			Assert.assertTrue(true);
			logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is for Creation of Incident ticket from Intake", ExtentColor.GREEN));
		}
	 @Test(priority=4,description="Resolving Incident ticket",enabled=true)
		public void testResolveIncidentTicket() throws Exception{
			logger = extent.createTest("Resolving Incident ticket");
			String ChromeDrivers = Capabilities.getPropertyValue("ChromeDrivers");
	 	    System.setProperty("webdriver.chrome.driver",ChromeDrivers);
	 	    WebDriver driver=new ChromeDriver();
			driver.get("http://thomsonreutersqa.service-now.com");
			driver.manage().window().maximize();
			SafeLogin.logInUser(driver);
			WaitUtils.waitForPageToLoad(driver, 10);
			ServiceNowUtils.navigateToModuleName(driver,"incident");
			incNumber=IncidentReusables.createstandaloneincident1(driver, 1, 2);
			IncidentReusables.verifyIncidentCreation(driver, incNumber);
			IncidentReusables.resolveIncident(driver);
			driver.close();
			Assert.assertTrue(true);
			logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is for Resolving incident ticket", ExtentColor.GREEN));
		}
	 @Test(priority=5,description="Cancelling Incident Ticket", enabled=true)
		public void testCancelIncidentTicket() throws Exception{
			logger = extent.createTest("Cancelling Incident Ticket");
			String ChromeDrivers = Capabilities.getPropertyValue("ChromeDrivers");
	 	    System.setProperty("webdriver.chrome.driver",ChromeDrivers);
	 	    WebDriver driver=new ChromeDriver();
			driver.get("http://thomsonreutersqa.service-now.com");
			driver.manage().window().maximize();
			SafeLogin.logInUser(driver);
			WaitUtils.waitForPageToLoad(driver, 10);
			ServiceNowUtils.navigateToModuleName(driver,"incident");
			incNumber=IncidentReusables.createstandaloneincident1(driver, 4, 2);
			IncidentReusables.verifyIncidentCreation(driver, incNumber);
	        IncidentReusables.cancelIncident(driver, incNumber);	
			driver.close();
			Assert.assertTrue(true);
			logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is for cancellation of incident ticket", ExtentColor.GREEN));
		}
	 @Test(priority=6,description="Create Standard change from Incident ",enabled=true)
		public void testCreateStandardChangefromInc() throws Exception{
			logger = extent.createTest("Create Standard change from Incident");
			String ChromeDrivers = Capabilities.getPropertyValue("ChromeDrivers");
	 	    System.setProperty("webdriver.chrome.driver",ChromeDrivers);
	 	    WebDriver driver=new ChromeDriver();
			driver.get("http://thomsonreutersqa.service-now.com");
			driver.manage().window().maximize();
			SafeLogin.logInUser(driver);
			WaitUtils.waitForPageToLoad(driver, 10);
			ServiceNowUtils.navigateToModuleName(driver,"incident");
			IncidentReusables.createstandardchangefromincident(driver, false, 1, 2);
			driver.close();
			Assert.assertTrue(true);
			logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is for Creation of Standardchange from Incident ticket", ExtentColor.GREEN));
		}
	 @Test(priority=7,description="Create Problem from Incident",enabled=true)
		public void testcreateProblemfromIncident() throws Exception
		{
			logger = extent.createTest("Create Problem from Incident");
			String ChromeDrivers = Capabilities.getPropertyValue("ChromeDrivers");
	 	    System.setProperty("webdriver.chrome.driver",ChromeDrivers);
	 	    WebDriver driver=new ChromeDriver();
			driver.get("http://thomsonreutersqa.service-now.com");
			driver.manage().window().maximize();
			SafeLogin.logInUser(driver);
			WaitUtils.waitForPageToLoad(driver, 10);
			ServiceNowUtils.navigateToModuleName(driver,"incident");
			incNumber=IncidentReusables.createproblemfromincident(driver, false, 1, 2);
			driver.close();
			Assert.assertTrue(true);
			logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is for Creation of Problem from Incident ticket", ExtentColor.GREEN));
		}
	 @Test(priority=8,description="Create Incident task",enabled=true)
		public void testcreateincidenttask() throws Exception{
			logger = extent.createTest("Create Incident task");
			String ChromeDrivers = Capabilities.getPropertyValue("ChromeDrivers");
	 	    System.setProperty("webdriver.chrome.driver",ChromeDrivers);
	 	    WebDriver driver=new ChromeDriver();
			driver.get("http://thomsonreutersqa.service-now.com");
			driver.manage().window().maximize();
			SafeLogin.logInUser(driver);
			WaitUtils.waitForPageToLoad(driver, 10);
			ServiceNowUtils.navigateToModuleName(driver,"incident");
			incNumber=IncidentReusables.createstandaloneincident1(driver, 1, 2);
			IncidentReusables.verifyIncidentCreation(driver, incNumber);
	        incidenttasknum = IncidentReusables.CreateIncidentTask(driver, incNumber);
			Thread.sleep(2000);
			driver.navigate().refresh();
	        Thread.sleep(2000);
	         
	       ServiceNowUtils.navigateToAllQueueForDesiredModule(driver, "incident");
			IncidentReusables.verifyIncidentCreation1(driver, incNumber);
			IncidentReusables.MovetasktoWIP(driver, incNumber);
			driver.close();
			Assert.assertTrue(true);
			logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is for Creation of Incident task", ExtentColor.GREEN));
		}
	 @Test(priority=9,description="Create Normal change from Incident ",enabled=true)
		public void testCreateNormalChangefromInc() throws Exception{
			logger = extent.createTest("Create Normal change from Incident ");
			String ChromeDrivers = Capabilities.getPropertyValue("ChromeDrivers");
	 	    System.setProperty("webdriver.chrome.driver",ChromeDrivers);
	 	    WebDriver driver=new ChromeDriver();
			driver.get("http://thomsonreutersqa.service-now.com");
			driver.manage().window().maximize();
			SafeLogin.logInUser(driver);
			WaitUtils.waitForPageToLoad(driver, 10);
			ServiceNowUtils.navigateToModuleName(driver,"incident");
			IncidentReusables.createnormalchangefromincident(driver, false, 1, 2);
			driver.close();
			Assert.assertTrue(true);
			logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is for Creation of Normal change from Incident", ExtentColor.GREEN));
		}
	 @Test(priority=10,description="Create Emergency change from Incident ",enabled=true)
		public void testCreateEmergencyChangefromInc() throws Exception{
			logger = extent.createTest("Create Emergency change from Incident ");
			String ChromeDrivers = Capabilities.getPropertyValue("ChromeDrivers");
	 	    System.setProperty("webdriver.chrome.driver",ChromeDrivers);
	 	    WebDriver driver=new ChromeDriver();
			driver.get("http://thomsonreutersqa.service-now.com");
			driver.manage().window().maximize();
			SafeLogin.logInUser(driver);
			WaitUtils.waitForPageToLoad(driver, 10);
			ServiceNowUtils.navigateToModuleName(driver,"incident");
			IncidentReusables.createemergencychangefromincident(driver, 1, 2);
			driver.close();
			Assert.assertTrue(true);
			logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is for Creation of Emergency change from Incident", ExtentColor.GREEN));
		}
	 @Test(priority=11,description="Create Child Incident from Incident ",enabled=true)
		public void testCreateChildIncfromInc() throws Exception{
			logger = extent.createTest("Create Child Incident from Incident ");
			String ChromeDrivers = Capabilities.getPropertyValue("ChromeDrivers");
	 	    System.setProperty("webdriver.chrome.driver",ChromeDrivers);
	 	    WebDriver driver=new ChromeDriver();
			driver.get("http://thomsonreutersqa.service-now.com");
			driver.manage().window().maximize();
			SafeLogin.logInUser(driver);
			WaitUtils.waitForPageToLoad(driver, 10);
			ServiceNowUtils.navigateToModuleName(driver,"incident");
			IncidentReusables.creatchildincidentfromincident(driver, false, 1, 2);
			driver.close();
			Assert.assertTrue(true);
			logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is for Creation of Child Incident", ExtentColor.GREEN));
		}
	 @Test(priority=12,description="Progressing IncidentTask to CloseComplete state",enabled=true)
		public void testmoveIncidentTasktoclosecomplete() throws Exception
		{
			logger = extent.createTest("Progressing IncidentTask to CloseComplete state");
			String ChromeDrivers = Capabilities.getPropertyValue("ChromeDrivers");
	 	    System.setProperty("webdriver.chrome.driver",ChromeDrivers);
	 	    WebDriver driver=new ChromeDriver();
			driver.get("http://thomsonreutersqa.service-now.com");
			driver.manage().window().maximize();
			SafeLogin.logInUser(driver);
			WaitUtils.waitForPageToLoad(driver, 10);
			ServiceNowUtils.navigateToModuleName(driver,"incident");
			incNumber=IncidentReusables.createstandaloneincident1(driver, 1, 2);
			IncidentReusables.verifyIncidentCreation(driver, incNumber);
	        IncidentReusables.CreateIncidentTask(driver, incNumber);
			Thread.sleep(2000);
			driver.navigate().refresh();
	        Thread.sleep(2000);
	        ServiceNowUtils.navigateToAllQueueForDesiredModule(driver, "incident");
			IncidentReusables.verifyIncidentCreation1(driver, incNumber);
	        IncidentReusables.Movetasktclosecomplete(driver, incNumber);
			driver.close();
			Assert.assertTrue(true);
			logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is for CloseCompleting Incident task", ExtentColor.GREEN));
		}
	 @Test(priority=13,description="Progressing IncidentTask to Cancelled state",enabled=true)
		public void testmoveIncidentTasktocancel() throws Exception
		{
			logger = extent.createTest("Progressing IncidentTask to Cancelled state");
			String ChromeDrivers = Capabilities.getPropertyValue("ChromeDrivers");
	 	    System.setProperty("webdriver.chrome.driver",ChromeDrivers);
	 	    WebDriver driver=new ChromeDriver();
			driver.get("http://thomsonreutersqa.service-now.com");
			driver.manage().window().maximize();
			SafeLogin.logInUser(driver);
			WaitUtils.waitForPageToLoad(driver, 10);
			ServiceNowUtils.navigateToModuleName(driver,"incident");
			incNumber=IncidentReusables.createstandaloneincident1(driver, 1, 2);
			IncidentReusables.verifyIncidentCreation(driver, incNumber);
			IncidentReusables.CreateIncidentTask(driver, incNumber);
			Thread.sleep(2000);
			driver.navigate().refresh();
			Thread.sleep(2000);
			ServiceNowUtils.navigateToAllQueueForDesiredModule(driver, "incident");
			IncidentReusables.verifyIncidentCreation1(driver, incNumber);
			IncidentReusables.Movetasktocancel(driver, incNumber);
			driver.close();
			Assert.assertTrue(true);
			logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is for Cancellation of Incident task", ExtentColor.GREEN));
		}
	 @Test(priority=14,description="Create Incident Alert",enabled=true)
		public void testCreateincidentalert() throws Exception{
			logger = extent.createTest("Create Incident Alert");
			String ChromeDrivers = Capabilities.getPropertyValue("ChromeDrivers");
	 	    System.setProperty("webdriver.chrome.driver",ChromeDrivers);
	 	    WebDriver driver=new ChromeDriver();
			driver.get("http://thomsonreutersqa.service-now.com");
			driver.manage().window().maximize();
			SafeLogin.logInUser(driver);
			WaitUtils.waitForPageToLoad(driver, 10);		
			ServiceNowUtils.navigateToModuleName(driver,"incident");
			incNumber=IncidentReusables.createmanagedIncident(driver,2,2);
			IncidentReusables.verifymanagedIncidentCreation(driver, incNumber);
			IncidentReusables.CreateincidentAlert(driver);
			driver.close();
			Assert.assertTrue(true);
			logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is for Creation of Incident Alert", ExtentColor.GREEN));
		}
	 @Test(priority=15,description="Create Incident ticket for User impact 3",enabled=true)
		public void testcreateINCforuserimpact3() throws Exception
		{
			logger = extent.createTest("Create Incident ticket for User impact 3");
			String ChromeDrivers = Capabilities.getPropertyValue("ChromeDrivers");
	 	    System.setProperty("webdriver.chrome.driver",ChromeDrivers);
	 	    WebDriver driver=new ChromeDriver();
			driver.get("http://thomsonreutersqa.service-now.com");
			driver.manage().window().maximize();
			SafeLogin.logInUser(driver);
			WaitUtils.waitForPageToLoad(driver, 10);
			ServiceNowUtils.navigateToModuleName(driver,"incident");
			incNumber=IncidentReusables.CreateINCforuserimpact3(driver, false, 1, 2);
			IncidentReusables.verifyIncidentCreation(driver, incNumber);
			driver.close();
			Assert.assertTrue(true);
			logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is for Creation of Incident for User impact 3", ExtentColor.GREEN));
		}
	 @Test(priority=16,description="Create Incident ticket for User impact 1",enabled=true)
		public void testcreateINCforuserimpact1() throws Exception
		{
			logger = extent.createTest("Create Incident ticket for User impact 1");
			String ChromeDrivers = Capabilities.getPropertyValue("ChromeDrivers");
	 	    System.setProperty("webdriver.chrome.driver",ChromeDrivers);
	 	    WebDriver driver=new ChromeDriver();
			driver.get("http://thomsonreutersqa.service-now.com");
			driver.manage().window().maximize();
			SafeLogin.logInUser(driver);
			WaitUtils.waitForPageToLoad(driver, 10);
			ServiceNowUtils.navigateToModuleName(driver,"incident");
			incNumber=IncidentReusables.CreateINCforuserimpact1(driver, false, 1, 2);
			IncidentReusables.verifyIncidentCreation(driver, incNumber);
			driver.close();
			Assert.assertTrue(true);
			logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is for Creation of Incident for User impact 1", ExtentColor.GREEN));
		}
		@Test(priority=17,description="Create Incident ticket for User impact 2",enabled=true)
		public void testcreateINCforuserimpact2() throws Exception
		{
			logger = extent.createTest("Create Incident ticket for User impact 2");
			String ChromeDrivers = Capabilities.getPropertyValue("ChromeDrivers");
	 	    System.setProperty("webdriver.chrome.driver",ChromeDrivers);
	 	    WebDriver driver=new ChromeDriver();
			driver.get("http://thomsonreutersqa.service-now.com");
			driver.manage().window().maximize();
			SafeLogin.logInUser(driver);
			WaitUtils.waitForPageToLoad(driver, 10);
			ServiceNowUtils.navigateToModuleName(driver,"incident");
			incNumber=IncidentReusables.CreateINCforuserimpact2(driver, false, 1, 2);
			IncidentReusables.verifyIncidentCreation(driver, incNumber);
			driver.close();
			Assert.assertTrue(true);
			logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is for Creation of Incident for User impact 2", ExtentColor.GREEN));
		}
		@Test(priority=18,description="Reassign Incident to different group ",groups="Incidents",enabled=true)
		public void testreassignim() throws Exception{
			logger = extent.createTest("Reassign Incident to different group ");
			String ChromeDrivers = Capabilities.getPropertyValue("ChromeDrivers");
	 	    System.setProperty("webdriver.chrome.driver",ChromeDrivers);
	 	    WebDriver driver=new ChromeDriver();
			driver.get("http://thomsonreutersqa.service-now.com");
			driver.manage().window().maximize();
			SafeLogin.logInUser(driver);
			WaitUtils.waitForPageToLoad(driver, 10);
			ServiceNowUtils.navigateToModuleName(driver,"incident");
			incNumber=IncidentReusables.createstandaloneincident1(driver, 1, 2);
			IncidentReusables.verifyIncidentCreation(driver, incNumber);
			IncidentReusables.ReassignIM(driver, incNumber);
			driver.close();
			Assert.assertTrue(true);
			logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is for Re-assigning Incident to different group", ExtentColor.GREEN));
		}
		@Test(priority=19,description="Copy Incident ",enabled=true)
		public void testcopyincident() throws Exception{
			logger = extent.createTest("Copy Incident");
			String ChromeDrivers = Capabilities.getPropertyValue("ChromeDrivers");
	 	    System.setProperty("webdriver.chrome.driver",ChromeDrivers);
	 	    WebDriver driver=new ChromeDriver();
			driver.get("http://thomsonreutersqa.service-now.com");
			driver.manage().window().maximize();
			SafeLogin.logInUser(driver);
			WaitUtils.waitForPageToLoad(driver, 10);
			ServiceNowUtils.navigateToModuleName(driver,"incident");
			IncidentReusables.Copyincident(driver, false, 1, 2);
			driver.close();
			Assert.assertTrue(true);
			logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is for Copying Incident ticket ", ExtentColor.GREEN));
		}
		@Test(priority=20,description="Validate Resolving/Cancelling INC with Active IM task ",enabled=true)
		public void testverifyINCforActiveIMtask() throws Exception
		{
			logger = extent.createTest("Validate Resolving/Cancelling INC with Active IM task");
			String ChromeDrivers = Capabilities.getPropertyValue("ChromeDrivers");
	 	    System.setProperty("webdriver.chrome.driver",ChromeDrivers);
	 	    WebDriver driver=new ChromeDriver();
			driver.get("http://thomsonreutersqa.service-now.com");
			driver.manage().window().maximize();
			SafeLogin.logInUser(driver);
			WaitUtils.waitForPageToLoad(driver, 10);
			ServiceNowUtils.navigateToModuleName(driver,"incident");
			incNumber=IncidentReusables.createstandaloneincident1(driver, 1, 2);
			IncidentReusables.verifyIncidentCreation(driver, incNumber);
			IncidentReusables.CreateIncidentTask(driver, incNumber);
			Thread.sleep(2000);
			driver.navigate().refresh();
			Thread.sleep(2000);
			ServiceNowUtils.navigateToAllQueueForDesiredModule(driver, "incident");
			IncidentReusables.verifyIncidentCreation1(driver, incNumber);
			IncidentReusables.cancelIncident1(driver, incNumber);
			driver.close();
			Assert.assertTrue(true);
			logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is for Validating Resolving/Cancelling INC with active IM task ", ExtentColor.GREEN));
		}
		
	@AfterMethod
	public void getResult(ITestResult result) throws IOException{
		WebDriver driver = null;
		if(result.getStatus() == ITestResult.FAILURE)
		{
            logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
			logger.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
		}
		else if(result.getStatus() == ITestResult.SKIP)
		{
						logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));	
		}
	}
	@AfterTest
	public void endReport(){
		extent.flush();
	}
}






