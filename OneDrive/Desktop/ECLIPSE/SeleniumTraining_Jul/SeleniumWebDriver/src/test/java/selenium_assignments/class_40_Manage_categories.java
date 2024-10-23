package selenium_assignments;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import helper.Utility;

public class class_40_Manage_categories {

	public static void main(String[] args) throws InterruptedException {

		WebDriver driver=Utility.startBrowser("Chrome","https://freelance-learn-automation.vercel.app/login");
		driver.findElement(By.xpath("//input[@placeholder='Enter Email']")).sendKeys("admin@email.com");
		driver.findElement(By.xpath("//input[@placeholder='Enter Password']")).sendKeys("admin@123");
		driver.findElement(By.xpath("//button[text()='Sign in']")).click();
		String parentwindow=driver.getWindowHandle();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		//Thread.sleep(5000);
        Actions act=new Actions(driver);
		
		act.moveToElement(driver.findElement(By.xpath("//span[text()='Manage']"))).perform();
		//a[contains(@href,'category')]
		driver.findElement(By.xpath("//a[text()='Manage Categories']")).click();
		//driver.findElement(By.xpath("//a[contains(@href,'category')]")).click();
		//Thread.sleep(2000);
		Set<String> allwindows= driver.getWindowHandles();
		Iterator<String> itr= allwindows.iterator();
		while(itr.hasNext())
		{
			String child= itr.next();
			if(!child.equals(parentwindow))
			{
			driver.switchTo().window(child)	;
			}
		}
		
		String url=driver.getCurrentUrl();
		System.out.println("Current url is "+url);
		if (url.contains("manage"))
		{
			System.out.println("LOG INFO --Validation 1 passed --url contains manage");
		}
		else
		{
			System.out.println("LOG INFO--Validation1 Failed");
		}
		Thread.sleep(6000);
		List<WebElement> allCategories= driver.findElements(By.xpath("//tbody//tr//td[1]"));
		System.out.println(allCategories.get(0).getText());
		int count_Categories=allCategories.size();
		System.out.println("count_Categories "+count_Categories);
		if (count_Categories>0)
		{
			System.out.println("LOG INFO --Validation 2 passed Count_Categories = "+count_Categories);
		}
		else 
		{
		System.out.println("LOG INFO --Validation 2 failed");	
		}
		driver.findElement(By.xpath("//button[text()='Add New Category ']")).click();
		Alert altAlert= driver.switchTo().alert();
		altAlert.sendKeys("RA");
		altAlert.accept();
		Thread.sleep(5000);
		List<WebElement> allCategories_after= driver.findElements(By.xpath("//tbody//tr//td[1]"));
		System.out.println("List size after adding RA "+allCategories.size());
		boolean status=false;
		for(WebElement ele:allCategories_after)
		{
			String category=ele.getText();
			System.out.println(category);
			if(category.equals("RA"))
			{
			status=true;
			break;
			}
		
		}
		if(status)
		{
			System.out.println("LOG INFO --Validation 3 passed RA is added");
		}
		else
		{
			System.out.println("LOG INFO --Validation 3 Failed RA is not added");
		}
		//window.scrollBy(0,1003)
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(arguments[0], arguments[1])",0,1204);
		driver.findElement(By.xpath("//td[text()='RA']//following::button[2]")).click();
		Thread.sleep(3000);
		Alert alt2=driver.switchTo().alert();
		alt2.sendKeys("Apache JM");
		alt2.accept();
		Thread.sleep(3000);
		List<WebElement> allCategories_NEW= driver.findElements(By.xpath("//tbody//tr//td[1]"));
		boolean new_status=false;
		for(WebElement ele:allCategories_NEW
				)
		{
			String category=ele.getText();
			//System.out.println(category);
			if(category.equals("Apache JM"))
			{
			new_status=true;
			break;
			}
		
		}
		if(new_status)
		{
			System.out.println("LOG INFO --Validation 4 passed Apache JM is updated");
		}
		else
		{
			System.out.println("LOG INFO --Validation 4 Failed Apache JM is not updated");
		}
		driver.findElement(By.xpath("//td[text()='Apache JM']//following::button[1]")).click();
		Thread.sleep(2000);
		//Alert alt3=driver.switchTo().alert();
		//alt3.accept();
		driver.findElement(By.xpath("//button[text()='Delete']")).click();
		Thread.sleep(5000);
		List<WebElement> allCategories_NEW_after_del= driver.findElements(By.xpath("//tbody//tr//td[1]"));
		boolean new_status_del=true;
		for(WebElement ele:allCategories_NEW_after_del)
				
		{
			String category=ele.getText();
			//System.out.println(category);
			if(category.equals("Apache JM"))
			{
			new_status=false;
			break;
			}
		
		
		}
		if(new_status_del)
		{
			System.out.println("LOG INFO --Validation 5 passed Apache JM is deleted");
		}
		else
		{
			System.out.println("LOG INFO --Validation 5 Failed Apache JM is not deleted");
		}
		
		
		driver.switchTo().window(parentwindow);
		
		//utility.closeBrowser(driver);
		
	}

}
