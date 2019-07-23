package com.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IntakePage {

	private static WebElement element;
    private static List<WebElement> elements=null;
    public static WebElement getsourceEdt(WebDriver driver)
    {
    	element = driver.findElement(By.id("ticket.contact_type"));
    	return element;
    }
    public static WebElement getopenedbygroup(WebDriver driver)
    {
    	element = driver.findElement(By.xpath("//input[@id='sys_display.ticket.u_opened_by_group']"));
    	return element;
    }
    public static WebElement getrequestedbyEdt(WebDriver driver){
        element = driver.findElement(By.xpath("//input[@id='sys_display.ticket.u_requested_by']"));
        WebDriverWait wait = new WebDriverWait(driver, 10);
    	wait.until(ExpectedConditions.visibilityOf(element));
    	wait.until(ExpectedConditions.elementToBeClickable(element));
        
        return element;
        
    }
    public static WebElement getrequestedforedt(WebDriver driver) {
    	element = driver.findElement(By.id("sys_display.ticket.u_requested_for"));
    	return element;
    }
    public static WebElement getlocationEdt(WebDriver driver)
    {
    	element = driver.findElement(By.xpath("//input[@id='sys_display.ticket.location']"));
    	return element;
    }
    public static WebElement getbusinessServiceCatalogEdt(WebDriver driver)
    {
    	element = driver.findElement(By.xpath("//input[@id='sys_display.ticket.u_business_service_catalog']"));
    	return element;
    }
    public static WebElement getsituationEdt(WebDriver driver)
    {
    	element = driver.findElement(By.xpath("//input[@id='sys_display.ticket.u_symptom_map']"));
    	return element;
    }
    public static WebElement getnextbtn(WebDriver driver) {
    	element = driver.findElement(By.id("3a00cb9e0c1425405ac65c2573079a51"));
    	return element;}
  public static WebElement getshortdescEdt(WebDriver driver)
  {
  	element = driver.findElement(By.id("ticket.short_description"));
  	WebDriverWait wait = new WebDriverWait(driver, 10);
  	wait.until(ExpectedConditions.visibilityOf(element));
  	wait.until(ExpectedConditions.elementToBeClickable(element));
  	
  	return element;
  }
	public static WebElement getordernowbtn(WebDriver driver)
	{
		element = driver.findElement(By.id("order_now"));
		return element;
		
	}
	public static WebElement getgenericlink(WebDriver driver)
	{
		element = driver.findElement(By.xpath("/html/body/div[2]/table[1]/tbody[1]/tr/td[1]/div[1]/a"));
		return element;
	}
	
	public static WebElement getdescEdt(WebDriver driver)
    {
    	element = driver.findElement(By.xpath("//*[@id='ticket.description']"));
    	WebDriverWait wait = new WebDriverWait(driver, 10);
    	wait.until(ExpectedConditions.visibilityOf(element));
    	wait.until(ExpectedConditions.elementToBeClickable(element));
    	return element;
    }
	
	public static WebElement getsearchmodule(WebDriver driver)
	{
		element = driver.findElement(By.id("filter"));
		return element;
	}
	/*public static WebElement gethammenu(WebDriver driver)
	{
		element = driver.findElement(By.xpath("/html/body/div[1]/span/span/nav/div/div[1]/button[2]"));
		return element;
	}*/
	}
    
    


