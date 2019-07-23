package com.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


//adding to git

public class IncidentTask {
	private static WebElement element=null;
	public static WebElement getroutingsituationbtn(WebDriver driver)
	{
		element = driver.findElement(By.xpath("//button[@id='routing_situation']"));
		return element;
	}
	public static WebElement getroutingoption(WebDriver driver)
	{
		//element = driver.findElement(By.xpath("//*[@id='u_task_route.u_routing_option']//following::select[1]"));
		element = driver.findElement(By.xpath("//div[@class='col-xs-10 col-md-9 col-lg-8 form-field input_controls']//select[@id='u_task_route.u_routing_option']"));
		
		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.until(ExpectedConditions.visibilityOf(element)); 
		wait.until(ExpectedConditions.elementToBeClickable(element));
		
		
		return element;
	}
	
	public static WebElement Incidnettaskmsg(WebDriver driver)
	{
		
	element = driver.findElement(By.xpath("//span[@class='outputmsg_text']//parent:div[1]"));
	String text = element.getText();
	System.out.println("Text obtained is"+text);
	if(text.contains("Expected text"))
	{
		System.out.println("Expected text is obtained");
	}
	else
	{
		System.out.println("Expected text is not obtained");}
			
		
	
	
	return element;
	}
	public static WebElement ClickNext(WebDriver driver)
	{
		element = driver.findElement(By.id("process_route"));
		return element;
	}
}
