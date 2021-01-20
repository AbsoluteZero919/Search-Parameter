package SeleniumAuto;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class AutoTest {

	public static void main(String[] args) throws Exception {
		
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\Skanda N Kashyap\\Downloads\\geckodriver-v0.29.0-win64\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.get("http://localhost:8080/SEARCH_PARA/get_values.jsp");
		System.out.println("Website opened successfully");
		driver.manage().window().maximize();
		
		//Searching for the word 'acoustic'
		driver.findElement(By.name("s_query")).sendKeys("arctic");
		Thread.sleep(3000);
		
		//Searching for 'acoustic' under 'Genres' category from the drop-down
		WebElement cat_dropdown = driver.findElement(By.name("categ"));		
		Select cat_dd = new Select(cat_dropdown);
		cat_dd.selectByVisibleText("Singers");
		Thread.sleep(3000);
		
		//Click the Search button
		driver.findElement(By.id("sub")).click();
		Thread.sleep(4500);
		
		driver.close();

//		System.out.println("Searching for " + s_query + " under " + category);
		
	}

}
