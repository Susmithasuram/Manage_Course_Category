package selenium_Practice;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Manage_Category_End_to_End 
{
	static WebDriver driver;
	public static void signUp()
	{
		driver.get("https://freelance-learn-automation.vercel.app/");
		driver.findElement(By.xpath("//img[contains(@src,'menu')]")).click();
		driver.findElement(By.xpath("//button[normalize-space()='Log in']")).click();
		driver.findElement(By.xpath("//a[text()='New user? Signup']")).click();
		
	}
	
	public static void login()
	{
		driver.findElement(By.xpath("//input[@placeholder='Enter Email']")).sendKeys("admin@email.com");
		driver.findElement(By.xpath("//input[@placeholder='Enter Password']")).sendKeys("admin@123");
		driver.findElement(By.xpath("//button[text()='Sign in']")).click();
	}
	
	public static void signOut()
	{
		driver.findElement(By.xpath("//img[contains(@src,'menu')]")).click();
		driver.findElement(By.xpath("//button[normalize-space()='Sign out']")).click();
	}

	public static void main(String[] args) throws InterruptedException
	{

		driver=new ChromeDriver();
		//driver.get("https://freelance-learn-automation.vercel.app/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		//clicking on side menu then login and then on new user?Signup
		signUp();
//		driver.findElement(By.xpath("//img[contains(@src,'menu')]")).click();
//		driver.findElement(By.xpath("//button[normalize-space()='Log in']")).click();
//		driver.findElement(By.xpath("//a[text()='New user? Signup']")).click();
		
		//verifying the number of check boxes
		int checkBox_count=driver.findElements(By.xpath("//input[@type='checkbox']")).size();
		
		if(checkBox_count>5)
		{
			System.out.println("LOG INFO :--Validation 1 passed number of check boxes is greater than 5 and count is "+checkBox_count);
		}
		else {
			System.out.println("LOG INFO :--number of check boxes are less than 5 and count is "+checkBox_count);
		}
		
		//navigate back and login with valid credentials
		driver.navigate().back();
		login();
//		driver.findElement(By.xpath("//input[@placeholder='Enter Email']")).sendKeys("admin@email.com");
//		driver.findElement(By.xpath("//input[@placeholder='Enter Password']")).sendKeys("admin@123");
//		driver.findElement(By.xpath("//button[text()='Sign in']")).click();
		
		//Mouse hover to manage and click on Manage category
		Actions act=new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//span[normalize-space()='Manage']"))).perform();
		driver.findElement(By.xpath("//a[normalize-space()='Manage Categories']")).click();
		
		//switching to Category page 
		String parent=driver.getWindowHandle();
		Set<String> allWindowHandles= driver.getWindowHandles();
		System.out.println(driver.getTitle());
		Iterator<String> itr=allWindowHandles.iterator();
		while (itr.hasNext())
		{
			String child= itr.next();
			if(!(child.equals(parent)))
			{
				driver.switchTo().window(child);
				break;
				
			}
		 }
		
		//add new category
		driver.findElement(By.xpath("//button[normalize-space()='Add New Category']")).click();
		
		//switch to alert
		String categoryName="ManualTesting";
		Alert alt= driver.switchTo().alert();
		alt.sendKeys(categoryName);
		alt.accept();
		
		
		//Verifying the category added in the list
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		
		//act.scrollToElement(categoryElement);
		//act.scrollByAmount(0, 100);
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(arguments[0], arguments[1])",0,1000);
		
		WebElement categoryElement= driver.findElement(By.xpath("//tbody//td[contains(text(),'"+categoryName+"')]"));
		
		wait.until(ExpectedConditions.visibilityOf(categoryElement));
		
		if(categoryElement.getText().equals(categoryName))
		{
			System.out.println("LOG INFO :--Validation 2 passed Category is added to the list");
		}
		else 
		{
			System.out.println("LOG INFO :--Validation 2 Failed Category is not added");
		}
		
		//click on side menu and sign Out
//		driver.findElement(By.xpath("//img[contains(@src,'menu')]")).click();
//		driver.findElement(By.xpath("//button[normalize-space()='Sign out']")).click();
		signOut();
		
		//navigating signUp page
		signUp();
		
		//verify newly added category is present as a checkbox
		WebElement category_checkBox=driver.findElement(By.xpath("//input[@type='checkbox']//following::label[contains(text(),'"+categoryName+"')]"));
		System.out.println(category_checkBox.getText());
		if (category_checkBox.getText().equals(categoryName)) 
		{
			System.out.println("LOG INFO:--Validation 3 passed category is present as a checkbox");
		}
		else
		{
			System.out.println("LOG INFO:--Validation 3 Failed category is not added as a checkbox");
		}
		
		driver.navigate().back();
		login();
		
		//mouse hover and click on Manage Category
		act.moveToElement(driver.findElement(By.xpath("//span[normalize-space()='Manage']"))).perform();
		driver.findElement(By.xpath("//a[normalize-space()='Manage Categories']")).click();
		//switching to newly opened window
		Set<String> allWindows_new= driver.getWindowHandles();
		ArrayList<String> list_windows=new ArrayList<>(allWindows_new);
		driver.switchTo().window(list_windows.get(list_windows.size()-1));
	
		//Deleting the category added
		js.executeScript("window.scrollBy(arguments[0], arguments[1])",0,2000);
		driver.findElement(By.xpath("//tbody//td[contains(text(),'"+categoryName+"')]//following::button[1]")).click();
		driver.findElement(By.xpath("//button[text()='Delete']")).click();
		if(wait.until(ExpectedConditions.invisibilityOf(categoryElement)))
		{
			System.out.println("LOG INFO:--Validation 4 Category is deleted from list");
		}
		else {
			System.out.println("LOG INFO:--Validation 4 Category is not deleted");
		}
		
		//click on side menu and sign out
		signOut();
		
		//Open freelanceapp and click on side menu for signup page
		signUp();
		try {
			if(category_checkBox.isDisplayed())
			{
				System.out.println("LOG INFO:--Validation 5 failed category check is removed");
			}
			
			
		} catch (NoSuchElementException e)
		{
           System.out.println("LOG INFO:--Validation 5 passed category check is removed");
		}
		
		
	}

}
