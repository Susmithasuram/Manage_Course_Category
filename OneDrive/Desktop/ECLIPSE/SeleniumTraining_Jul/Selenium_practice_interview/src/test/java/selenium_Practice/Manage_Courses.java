package selenium_Practice;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import class_43_webdriverwait_fluentwait.webdriverwait_fortext_webdriver;
import helper.Utility;

public class Manage_Courses {

	public static void main(String[] args) throws InterruptedException {

		WebDriver driver=new ChromeDriver();
		driver.get("https://freelance-learn-automation.vercel.app/login");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		//login
		driver.findElement(By.xpath("//input[@placeholder='Enter Email']")).sendKeys("admin@email.com");
		driver.findElement(By.xpath("//input[@placeholder='Enter Password']")).sendKeys("admin@123");
		driver.findElement(By.xpath("//button[text()='Sign in']")).click();
		

		//Mousehover--click on manage to add the course
		Actions act=new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//span[text()='Manage']"))).perform();
		driver.findElement(By.xpath("//a[contains(@href,'course')]")).click();
		
		//Verifying number of courses before adding the course
		int no_of_courses= driver.findElements(By.xpath("//tbody//tr")).size();
		if(no_of_courses>0)
		{
			System.out.println("LOG INFO :--Validation 1 passed ---Total courses are greater than zero and count is "+no_of_courses);
		}
		else
		{
		  System.out.println("LOG INFO :--Validation 1 Failed --Please add the courses");	
		}
			
		//Adding the course
		driver.findElement(By.xpath("//button[text()='Add New Course ']")).click();
    	String coursename="WDIO";
    	driver.findElement(By.xpath("//input[@type='file']")).sendKeys("C:\\Users\\PRADE\\OneDrive\\Desktop\\PerformanceTesting2.jpeg");
		driver.findElement(By.xpath("//input[@name='name']")).sendKeys(coursename);
		driver.findElement(By.id("description")).sendKeys("SQL For Performance Testing");
		driver.findElement(By.id("instructorNameId")).sendKeys("Rihitha Kanchan");
		driver.findElement(By.id("price")).sendKeys("10000");
		driver.findElement(By.name("startDate")).click();
		//Thread.sleep(2000);
		List<WebElement> alldates=driver.findElements(By.xpath("//div[@role='option' and @aria-disabled='false' ]"));
     	for(WebElement ele :alldates)
		{
			String date=ele.getText();
			if (date.equals("30"))
			{
				ele.click();
				break;
			}
		}
	
		driver.findElement(By.name("endDate")).click();
		driver.findElement(By.xpath("//button[@aria-label='Next Month']")).click();
		List<WebElement> enddates= driver.findElements(By.xpath("//div[@role='option']"));
		
		for(WebElement ele :enddates)
		{
			String date=ele.getText();
			if (date.equals("25"))
			{
				ele.click();
				break;
			}
		}
		driver.findElement(By.xpath("//div[text()='Select Category']")).click();
		driver.findElement(By.xpath("//button[text()='Selenium']")).click();
		driver.findElement(By.xpath("//button[text()='Save']")).click();
		
		//waiting for the newly added course in the course list
		WebElement coursElement=driver.findElement(By.xpath("//tbody//td[normalize-space()='"+coursename+"']"));
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(coursElement));
		
		//Verying number of courses after adding the course
		int no_of_courses_NEW= driver.findElements(By.xpath("//tbody//tr")).size();
		if(no_of_courses_NEW==no_of_courses+1)
		{
			System.out.println("LOG INFO :--Validation 2 passed --New course added and count is  "+no_of_courses_NEW);
			
		}
		else
		{
			System.out.println("LOG INFO :--Validation 2 Failed, course is not added");
		}
		
		//verifying the presence of course in the dashboard
		driver.navigate().back();
		String courseText= driver.findElement(By.xpath("//h2[normalize-space()='"+coursename+"']")).getText();
		if(coursename.equals(courseText))
		{
			System.out.println("LOG INFO :--Validation 3:Passed --Course is added in the dashboard");
		}
		else {
			System.out.println("LOG INFO :--Validation 3 :Failed ---Course is not added in the dashboard");
		}
		driver.navigate().forward();
		
		//Deleting the course
		WebElement btnElement= driver.findElement(By.xpath("//td[text()='"+coursename+"']//following::button"));
		btnElement.click();

		//waiting for invisibility of the course after deleting in the course list.
		wait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.xpath("//tbody//tr"),no_of_courses_NEW));
		
		//Verifying the number of courses after deleting 
		int no_of_courses_after_del= driver.findElements(By.xpath("//tbody//tr")).size();
		if (no_of_courses_after_del==no_of_courses) 
		{
			System.out.println("LOG INFO :--Validation4 is passed course is deleted and count is "+no_of_courses_after_del);
		}
		else
		{
		System.out.println("LOG INFO :--Validation 4 is Failed course is not deleted");	
		}
		

		//driver.close();
	}

}
