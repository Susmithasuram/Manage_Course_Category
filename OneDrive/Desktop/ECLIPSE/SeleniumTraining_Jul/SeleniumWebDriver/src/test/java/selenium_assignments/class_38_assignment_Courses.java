package selenium_assignments;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import helper.Utility;

public class class_38_assignment_Courses {

	public static void main(String[] args) throws InterruptedException {

		WebDriver driver=Utility.startBrowser("Chrome","https://freelance-learn-automation.vercel.app/login");
		driver.findElement(By.xpath("//input[@placeholder='Enter Email']")).sendKeys("admin@email.com");
		driver.findElement(By.xpath("//input[@placeholder='Enter Password']")).sendKeys("admin@123");
		driver.findElement(By.xpath("//button[text()='Sign in']")).click();
		

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		Actions act=new Actions(driver);
		
		act.moveToElement(driver.findElement(By.xpath("//span[text()='Manage']"))).perform();
		//span[text()='Manage']
		driver.findElement(By.xpath("//a[contains(@href,'course')]")).click();
		Thread.sleep(3000);
		int no_of_courses= driver.findElements(By.xpath("//tbody//tr")).size();
		System.out.println("Total Courses "+no_of_courses);
		if(no_of_courses>0)
		{
			System.out.println("LOG : INFO --Validation 1 passed ---Total courses are greater than zero which is "+no_of_courses);
		}
		else
		{
		  System.out.println("LOG INFO : Validation 1 Failed --Please add the courses");	
		}
		driver.findElement(By.xpath("//html")).click();
	    driver.findElement(By.xpath("//button[text()='Add New Course ']")).click();
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys("C:\\Users\\PRADE\\OneDrive\\Desktop\\PerformanceTesting2.jpeg");
				
		String coursename="SQL_oct";
		driver.findElement(By.xpath("//input[@name='name']")).sendKeys(coursename);
		driver.findElement(By.id("description")).sendKeys("SQL For Performance Testing");
		driver.findElement(By.id("instructorNameId")).sendKeys("test_oct");
		driver.findElement(By.id("price")).sendKeys("2000");
		driver.findElement(By.name("startDate")).click();
		Thread.sleep(2000);
		////div[(contains(@class,'datepicker')) and @aria-disabled='false']
		List<WebElement> alldates=driver.findElements(By.xpath("//div[@role='option' and @aria-disabled='false' ]"));
		for(WebElement ele :alldates)
		{
			String date=ele.getText();
			if (date.equals("24"))
			{
				ele.click();
				break;
			}
		}
	
		driver.findElement(By.name("endDate")).click();
		List<WebElement> enddates= driver.findElements(By.xpath("//div[@role='option']"));
		
		for(WebElement ele :enddates)
		{
			String date=ele.getText();
			if (date.equals("3"))
			{
				ele.click();
				break;
			}
		}
		driver.findElement(By.xpath("//div[text()='Select Category']")).click();
		driver.findElement(By.xpath("//button[text()='Selenium']")).click();
		driver.findElement(By.xpath("//button[text()='Save']")).click();
		Thread.sleep(3000);
		
		int no_of_courses_NEW= driver.findElements(By.xpath("//tbody//tr")).size();
		System.out.println("Total Courses after adding "+no_of_courses_NEW);
		if(no_of_courses_NEW==no_of_courses+1)
		{
			System.out.println("LOG :INFO :--Validation 2 passed --New courses count "+no_of_courses_NEW);
			
		}
		else
		{
			System.out.println("LOG INFO : Validation 2 Failed");
		}
		WebElement btnElement= driver.findElement(By.xpath("//td[text()='SQL_oct']//following::button"));
		act.scrollToElement(btnElement).perform();
		btnElement.click();
		
		//Thread.sleep(2000);
		
		int no_of_courses_after_del= driver.findElements(By.xpath("//tbody//tr")).size();
		if (no_of_courses_after_del==no_of_courses) 
		{
			System.out.println("LOG INFO : Validation3 is passed course is deleted");
		}
		else
		{
		System.out.println("LOG INFO : --Validation 3 is Failed course is not deleted");	
		}
		
		Utility.closeBrowser(driver);
	
	}

}
