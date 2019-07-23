package com.pages;
import java.util.Base64;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class IncidentPage {
	

	public static WebElement element;
	private String today;
	static WebDriver driver;
	
	public static WebElement getCreateNewIncidentlnk(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.xpath("//a[text()='Incident Management']/following::a[text()='Create New']"));
		return element;
	}
	
	public static WebElement getAllIncidentslnk(WebDriver driver) throws Exception
	{
		//element=driver.findElement(By.xpath("//a[text()='Incident Management']/following::a[text()='All']"));
		element = driver.findElement(By.xpath("//a[@id='b55b4ab0c0a80009007a9c0f03fb4da9']/div/div[text()='All']"));
		WebDriverWait wait = new WebDriverWait(driver, 10);
    	wait.until(ExpectedConditions.visibilityOf(element));
    	wait.until(ExpectedConditions.elementToBeClickable(element));
		return element;
	}
	
	public static WebElement getIncidentTaskTab(WebDriver driver){
    	element = driver.findElement(By.xpath("//div[@id='tabs2_list']//span[contains(text(),'Incident') and contains(text(),'Tasks')]"));
    	return element;
    }
	public static WebElement getincidentaskstate(WebDriver driver)
	{
		element = driver.findElement(By.id("u_inc_task.state"));
		return element;
	}
	public static WebElement getassignedtotaskedt(WebDriver driver)
	{
		element = driver.findElement(By.id("sys_display.u_inc_task.assigned_to"));
		return element;
	}
	public static WebElement getopenbygrouptaskedt(WebDriver driver)
	{
		element = driver.findElement(By.id("sys_display.u_inc_task.u_opened_by_group"));
		return element;
	}
	public static WebElement Inctaskworknotes(WebDriver driver)
	{
		element = driver.findElement(By.xpath("//textarea[@placeholder='Work notes']"));
		return element;
	}
	 public static WebElement getIncidentTaskLnk(WebDriver driver, int i){
	    	element = driver.findElement(By.xpath("//table[@list_name='incident.u_inc_task.u_ud_parent']//tbody//tr["+i+"]//td[3]//a[starts-with(text(),'INCTSK')]"));
	    	JavascriptExecutor js = (JavascriptExecutor) driver;
	    	js.executeScript("arguments[0].scrollIntoView();", element);
	    	WebDriverWait wait = new WebDriverWait(driver, 10);
	    	wait.until(ExpectedConditions.visibilityOf(element));
	    	wait.until(ExpectedConditions.elementToBeClickable(element));
			return element;
		 
	    }
	 
	
	
	public static String getIncidentNumber(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.xpath("//input[@id='sys_readonly.incident.number']"));
		String incidentNumber=element.getAttribute("value");
		return incidentNumber;
	}
	public static WebElement getchildIncidentNumber(WebDriver driver)
	{
		element=driver.findElement(By.xpath("//input[@id='sys_readonly.incident.number']"));
		return element;
		
	}
	
	public static WebElement getStateDropdown(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("incident.state"));
		System.out.println(element);
		return element;
	}
	
	public static WebElement getAssignmentGroupEdt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.xpath("//input[@id='sys_display.incident.assignment_group']"));
		return element;
	}
	
	public static WebElement getAssignmentGroupReadOnly(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("incident.assignment_group_label"));
		return element;
	}
	
	public static WebElement getAssignedToEdt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.xpath("//input[@id='sys_display.incident.assigned_to']"));
		return element;
	}
	
	public static WebElement getOpenedByGroupEdt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.xpath("//input[@id='sys_display.incident.u_opened_by_group']"));
		return element;
	}
	
	public static WebElement getRequestedByEdt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.xpath("//input[@id='sys_display.incident.u_requested_by']"));
		return element;
	}
	
	public static WebElement getRequestedForEdt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.xpath("//input[@id='sys_display.incident.u_requested_for']"));
		
		return element;
	}
	
	public static WebElement getBusinessServiceEdt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.xpath("//input[@id='sys_display.incident.u_business_service']"));
		//element=driver.findElement(By.xpath("//contains(@id,'incident.u_business_service')"));
		return element;
	}
	
	public static WebElement getBusinessServiceReadOnly(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("incident.u_business_service_label"));
		return element;
	}
	
	public static WebElement getImpactDropdown(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.xpath("//select[@id='incident.impact']"));
		return element;
	}
	
	public static WebElement getUrgencyDropdown(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.xpath("//select[@id='incident.urgency']"));
		return element;
	}
	
	public static WebElement getPriorityValue(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.xpath("//select[@id='incident.priority']"));
		return element;
	}
	
	public static WebElement getUserImpactDropdown(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.xpath("//select[@id='incident.u_user_impact']"));
		return element;
	}
	
	public static WebElement getIncidentModelEdt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.xpath("//input[@id='sys_display.incident.u_template']"));
		return element;
	}
	
	public static WebElement getConfigurationItemEdt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.xpath("//input[@id='sys_display.incident.cmdb_ci']"));
		return element;
	}
	
	public static String getCIEnvironment(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.xpath("//input[@id='incident.u_ci_environment']"));
		String ciEnvironment=element.getAttribute("value");
		return ciEnvironment;
	}
	
	
	public static String getOpenedAt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.xpath("//input[@id='sys_readonly.incident.opened_at']"));
		String openedAt=element.getAttribute("value");
		return openedAt;
	}
	
	public static String getStartTime(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.xpath("//input[@id='incident.u_incident_start_time']"));
		String startTime=element.getAttribute("value");
		return startTime;
	}
	
	public static WebElement getIncidentManagerRequiredChkbox(WebDriver driver) throws Exception
	{
		//element=driver.findElement(By.id("label.ni.incident.u_incident_manager_required"));
		element = driver.findElement(By.xpath("//span[@class='input-group-checkbox']/label[@id='label.ni.incident.u_incident_manager_required'] "));
		//WebElement element = driver.findElement(By.xpath("//span[@class='input-group-checkbox']/label[@id='label.ni.incident.u_incident_manager_required'] "));

		
		//element = driver.findElement(By.id("label.ni.incident.u_incident_manager_required"));
		
		/*WebDriverWait wait = new WebDriverWait(driver, 150);
		wait.until(ExpectedConditions.visibilityOf(element)); 
		wait.until(ExpectedConditions.elementToBeClickable(element));
		*/
		
		return element;
	}
	
	public static WebElement Assignmentgroupedt(WebDriver driver)
	{
		element = driver.findElement(By.id("sys_display.u_task_route.u_assignment_group"));
		return element;}
	
	public static WebElement getReasonForIncidentManagerDropdown(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("incident.u_reason_for_incident_manager"));
		return element;
	}
	
	public static WebElement getKnowledgeChkbox(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.xpath("//input[@id='sys_original.incident.knowledge']"));
		return element;
	}
	
	public static WebElement getMitigationTime(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.xpath("//input[@id='incident.u_mitigation_time']"));
		return element;
	}
	
	public static WebElement getResolvedTime(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.xpath("//input[@id='incident.u_mitigation_time']"));
		return element;
	}
	
	public static WebElement getShortDescriptionEdt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.xpath("//input[@id='incident.short_description']"));
		return element;
	}
	
	public static WebElement getShortDescriptionReadOnly(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("sys_readonly.incident.short_description"));
		return element;
	}
	
	public static WebElement getDescriptionEdt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.xpath("//textarea[@id='incident.description']"));
		return element;
	}
	
	public static WebElement getNotesTab(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.xpath("//span[text()='Notes']"));
		return element;
	}
	
	public static WebElement getWorkNotesEdt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("activity-stream-work_notes-textarea"));
		return element;
	}
	
	public static WebElement getAdditionalCommentsEdt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("incident.comments"));
		return element;
	}
	
	public static WebElement getRelatedRecordsTab(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.xpath("//span[contains(text(),'Related') and contains(text(),'Record')]"));
		return element;
	}
	
	public static WebElement getParentIncidentEdt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("sys_display.incident.parent_incident"));
		return element;
	}
	
	public static WebElement getAlertNumberEdt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("sys_display.incident.u_alert_number"));
		return element;
	}
	
	public static WebElement getProblemEdt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("sys_display.incident.problem_id"));
		return element;
	}
	
	public static WebElement getKbArticleEdt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("sys_display.incident.u_kb_article"));
		return element;
	}
	
	public static WebElement getResolvingChangeEdt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("sys_display.incident.rfc"));
		return element;
	}
	
	public static WebElement getCausedByChangeEdt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("sys_display.incident.caused_by"));
		return element;
	}
	
	public static WebElement getActivityTab(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.xpath("//span[text()='Activity']"));
		return element;
	}
	
	public static WebElement getUpdatedByEdt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("incident.sys_updated_by"));
		return element;
	}
	
	public static WebElement getUpdatedEdt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("incident.sys_updated_on"));
		return element;
	}
	
	public static WebElement getResolvedByEdt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("incident.resolved_by_label"));
		return element;
	}
	
	public static WebElement getGovernanceTab(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.xpath("//span[text()='Governance']"));
		return element;
	}
	
	public static WebElement getLateralAssignmentChkbox(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("label.ni.incident.u_lateral_assignment"));
		return element;
	}
	
	public static WebElement getCreatedByTier1Chkbox(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("label.ni.incident.u_created_by_tier_1"));
		return element;
	}	
	
	public static WebElement getFirstLineResolvedChkbox(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("label.ni.incident.u_first_line_resolved"));
		return element;
	}

	public static WebElement getFirstCallResolutionChkbox(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("label.ni.incident.u_first_call_resolution"));
		return element;
	}
	
	public static WebElement getReopenedChkbox(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("label.ni.incident.u_reopened"));
		return element;
	}
	
	public static WebElement getEscalationChkbox(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("label.ni.incident.u_escalation"));
		return element;
	}
	
	public static WebElement getProtocolNotFollowedChkbox(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("label.ni.incident.u_protocol_not_followed"));
		return element;
	}
	
	public static WebElement getReasonForCancellationEdt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("incident.u_reason_for_cancellation"));
		return element;
	}
	
	public static WebElement getReassignmentCountEdt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("sys_readonly.incident.reassignment_count"));
		return element;
	}
	
	public static WebElement getUpdatesEdt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("sys_readonly.incident.sys_mod_count"));
		return element;
	}
	public static WebElement getReopenCountEdt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("sys_readonly.incident.reopen_count"));
		return element;
	}
	public static WebElement getIncidentStateCountEdt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("sys_readonly.incident.u_incident_state_count"));
		return element;
	}
	public static WebElement getRelatedCallsCountEdt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("sys_readonly.incident.u_related_calls_count"));
		return element;
	}
	public static WebElement getSetToP1Edt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("sys_readonly.incident.u_set_to_p1"));
		return element;
	}
	
	
	
	public static WebElement getExternalReferenceTab(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.xpath("//span[contains(text(),'External') and contains(text(),'Reference')]"));
		return element;
	}
	
	public static WebElement getTicketSourceEdt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("incident.u_ticket_source"));
		return element;
	}
	
	public static WebElement getTicketComponentEdt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("incident.u_ticket_component"));
		return element;
	}
	
	public static WebElement getEventManagementIdEdt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("incident.u_event_management_id"));
		return element;
	}
	
	public static WebElement getFingerprintEdt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("incident.u_fingerprint"));
		return element;
	}
	
	public static WebElement getATTTicketIdEdt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("sys_readonly.incident.u_at_t_ticket_id"));
		return element;
	}
	
	public static WebElement getServiceCloudIdEdt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("sys_readonly.incident.u_service_cloud_id"));
		return element;
	}
	
	public static WebElement getExternalCustomerAlertIdEdt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("sys_readonly.incident.u_customer_zone_alert_id"));
		return element;
	}
	
	public static WebElement getNumberOfCustomerCallsEdt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("incident.u_number_of_customer_calls"));
		return element;
	}
	
	public static WebElement getEstimatedResolutionTimeEdt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("incident.u_estimated_resolution_time"));
		return element;
	}
	
	public static WebElement getClosureTab(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.xpath("//span[contains(text(),'Closure')]"));
		//element=driver.findElement(By.xpath("//div[@id='tabs2_section']//following::span[6]"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
    	js.executeScript("arguments[0].scrollIntoView();", element);
    	WebDriverWait wait = new WebDriverWait(driver, 10);
    	wait.until(ExpectedConditions.visibilityOf(element));
    	wait.until(ExpectedConditions.elementToBeClickable(element));
		return element;
	}
	public static WebElement getaccountabletab(WebDriver driver) throws Exception
	{
		element = driver.findElement(By.xpath("//span[contains(text(),'Accountability and Impact')]"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
    	js.executeScript("arguments[0].scrollIntoView();", element);
    	WebDriverWait wait = new WebDriverWait(driver, 10);
    	wait.until(ExpectedConditions.visibilityOf(element));
    	wait.until(ExpectedConditions.elementToBeClickable(element));
		return element;
	}
	public static WebElement getdetailstab(WebDriver driver) throws Exception
	{
		element = driver.findElement(By.xpath("//span[contains(text(),'Details')]"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
    	js.executeScript("arguments[0].scrollIntoView();", element);
    	WebDriverWait wait = new WebDriverWait(driver, 10);
    	wait.until(ExpectedConditions.visibilityOf(element));
    	wait.until(ExpectedConditions.elementToBeClickable(element));
		return element;
	}
	public static WebElement getCauseCodeDropdown(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("incident.u_cause_code"));
		return element;
	}
	
	public static WebElement getSubCauseCodeDropdown(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("incident.u_sub_cause_code"));
		return element;
	}
	
	public static WebElement getMitigationAndSolutionStepsEdt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("incident.close_notes"));
		return element;
	}
	
	public static WebElement getSaveBtn(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.xpath("//button[text()='Save']"));
		return element;
	}
	
	public static WebElement getUpdateBtn(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.xpath("//button[@id='sysverb_update']"));
		return element;
	}	
	
	public static WebElement getAttachedKnowledgeTab(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.xpath("//span[contains(text(),'Attached') and contains(text(),'Knowledge')]"));
		return element;
	}
	
	public static WebElement getBackBtn(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.xpath("//span[text()='Back']"));
		return element;
	}
	
	public static WebElement getAdditionalActionsBtn(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.xpath("//span[text()='Additional actions']"));
		return element;
	}
	
	public static WebElement getSearchDropDown(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.xpath("//div[@class='input-group']//select"));
		return element;
	}
	
	public static WebElement getSearchIncidentEdt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.xpath("//div[@class='input-group']/label[text()='Search']/following-sibling::input"));
		return element;
	}
	
	public static WebElement getIncidentStatusfromQueue(WebDriver driver, String incidentNumber) throws Exception
	{
		element=driver.findElement(By.xpath("//tbody[@class='list2_body']//a[text()='"+incidentNumber+"']/following::td"));
		return element;
	}
public static WebElement getIncidenttaskstatusfromQueue(WebDriver driver, String incidenttasknum) throws Exception
	{
		element = driver.findElement(By.xpath("//tbody[@class='list2_body]//a[text()='"+incidenttasknum+"']/following::td"));
		return element;
	}
	public static WebElement getSearchIncidenttask(WebDriver driver) throws Exception
	{
		element = driver.findElement(By.xpath("//div[@class='input-group']/label[text()='Search']/following-sibling::input"));
		return element;
	}
	public static WebElement getIncidentNumberfromQueue(WebDriver driver, String incidentNumber) throws Exception
	{
		element=driver.findElement(By.xpath("//tbody[@class='list2_body']//a[text()='"+incidentNumber+"']"));
		return element;
	}
	
	public static WebElement getIncidentManagerRequiredValueFromNotesTab(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.xpath("//ul[@class='h-card-wrapper activities-form']//span[text()='Incident Manager Required']//following-sibling::span//span"));
		return element;
	}
	
	public static WebElement getIncidentSearchTicketEdt(WebDriver driver){
    	element = driver.findElement(By.xpath("//div[@class='input-group']/label[text()='Search']/following-sibling::input"));
    	return element;
    }
   
    public static WebElement clickonsaveEdt(WebDriver driver){
    	element = driver.findElement(By.id("sysverb_insert"));
    	return element;
    }
    public static WebElement ErrormessagenewEdt(WebDriver driver)
    {
    	element = driver.findElement(By.xpath("//div[@class='outputmsg_div']/div[1]/span[2]"));
    	return element;
    }
    
   
    public static WebElement onholdtype(WebDriver driver)
    {
    	element = driver.findElement(By.id("incident.u_on_hold_type"));
    	return element;
    }
    
  public static WebElement Clickonexternalreference(WebDriver driver)
  {
	  //element=driver.findElement(By.xpath("//span[contains(text(),'External Reference')]"));
	  element = driver.findElement(By.xpath("/html/body/div[2]/form/div[1]/span[5]/span[1]/span[2]"));
	  JavascriptExecutor js = (JavascriptExecutor) driver;
  	 js.executeScript("arguments[0].scrollIntoView();", element);
	return element;
  }
  public static WebElement Additionalcommentstask(WebDriver driver)
  {
	  element = driver.findElement(By.xpath("//*[@id='u_task_route.u_additional_comments']"));
	  return element;
  }
    
    public static WebElement getIncidenttasktab(WebDriver driver)
    {
    	element = driver.findElement(By.xpath("//table[@id='incident.u_inc_task.u_ud_parent_table']//tbody//tr//td[3]"));
    	return element;
    }
    public static WebElement getsourceci(WebDriver driver)
    {
    	element = driver.findElement(By.id("sys_display.incident_alert.source_ci"));
    	return element;
    }
    public static WebElement getincidentmanagergroup(WebDriver driver)
    {
    	element = driver.findElement(By.id("sys_display.incident_alert.assignment_group"));
    	return element;
    }
    public static WebElement getincidentmanager(WebDriver driver)
    {
    	element = driver.findElement(By.id("sys_display.incident_alert.assigned_to"));
    	return element;
    }
    public static WebElement getincidentowner(WebDriver driver)
    {
    	element = driver.findElement(By.id("sys_display.incident_alert.u_incident_owner"));
    	return element;
    }
    public static WebElement getaccountablebu(WebDriver driver)
    {
    	element = driver.findElement(By.id("sys_display.incident_alert.u_accountable_bu"));
    	return element;
    }
    public static WebElement getaffectedunit (WebDriver driver){
    	element = driver.findElement(By.xpath("//*[@id='incident_alert.u_affected_bu_s_unlock']"));
    	return element;
    }
    public static WebElement getimpactedlocation(WebDriver driver)
    {
    	element = driver.findElement(By.xpath("//span[@class='input-group-checkbox']/label[@id='label.ni.incident_alert.u_amers']"));
    	return element;
    }
    public static WebElement getimpactedlocation1(WebDriver driver)
    {
    	element = driver.findElement(By.xpath("//span[@class='input-group-checkbox']/label[@id='label.ni.incident_alert.u_asia']"));
    	return element;
    }
    public static WebElement getaffectedunitedt(WebDriver driver)
    {
    	element = driver.findElement(By.id("sys_display.incident_alert.u_affected_bu_s"));
    	return element;
    }
    public static WebElement getaffectedlock(WebDriver driver)
    {
    	element = driver.findElement(By.xpath("//*[@id='incident_alert.u_affected_bu_s_lock']"));
    	return element;
    }
    public static WebElement getlatestupdate(WebDriver driver)
    {
    	element = driver.findElement(By.id("incident_alert.u_latest_update"));
    	return element;
    }
    public static WebElement getSourceDropdown(WebDriver driver) {
        element = driver.findElement(By.id("problem.contact_type"));
        return element;
       }
    public static WebElement getConfigurationitemEdt(WebDriver driver) {
        element = driver.findElement(By.id("sys_display.problem.cmdb_ci"));
        return element;
       }
    public static WebElement getSubmitBtn(WebDriver driver) {
        element = driver.findElement(By.id("sysverb_insert"));
        return element;
       }
    public static WebElement getReasonForChangeEdt(WebDriver driver){
    	element = driver.findElement(By.id("change_request.u_reason_for_change"));
    	return element;
    }
    public static WebElement getPlanningTab(WebDriver driver){
    	element = driver.findElement(By.xpath("//span[contains(text(),'Planning') and @class='tab_caption_text']"));
    	return element;
    }
    public static WebElement getConfigurationItemEdt1(WebDriver driver){
    	element = driver.findElement(By.id("sys_display.change_request.cmdb_ci"));
    	return element;
    }
    
    public static WebElement getAssignmentGrpEdt1(WebDriver driver){
    	element = driver.findElement(By.id("sys_display.change_request.assignment_group"));
    	return element;
    }
    
    public static WebElement getShortDescriptionEdt1(WebDriver driver){
    	element = driver.findElement(By.id("change_request.short_description"));
    	return element;
    }
    
    public static WebElement getDescriptionEdt1(WebDriver driver){
    	element = driver.findElement(By.id("change_request.description"));
    	return element;
    }
    public static WebElement getChangeNumberEdt(WebDriver driver){
    	element = driver.findElement(By.id("sys_readonly.change_request.number"));
    	return element;
    }
    public static WebElement getScheduleTab(WebDriver driver){
    	element = driver.findElement(By.xpath("//span[contains(text(),'Schedule')]"));
    	return element;
    }
    public static WebElement getRequestedByDateEdt(WebDriver driver){
    	element = driver.findElement(By.id("change_request.requested_by_date"));
    	return element;
    }
    public static WebElement getSubmitBtn1(WebDriver driver){
    	element = driver.findElement(By.id("sysverb_insert"));
    	return element;
    }
    
}


    

