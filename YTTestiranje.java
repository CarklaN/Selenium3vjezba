package zadacaSelenium03;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class YTTestiranje {
	
	WebDriver driver;

	@BeforeClass
	public void pocetak() {
		System.setProperty("webdriver.chrome.driver", "C:\\drajver\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
	}
	@Test
	public void testiranje() {
		driver.get("https://www.youtube.com/");
		WebElement search=driver.findElement(By.id("search"));
		search.sendKeys("Rickey Astley Never gonna give you up");
		Robot r;
		try {
			r = new Robot();
				r.keyPress(KeyEvent.VK_ENTER);
		        r.keyRelease(KeyEvent.VK_ENTER);
		
		} catch (AWTException e) {
			e.printStackTrace();
		}
		WebElement song=driver.findElement(By.linkText("Rick Astley - Never Gonna Give You Up (Video)"));
		song.click();
		WebElement adv=driver.findElement(By.xpath("//*[@id=\"ad-image:5\"]"));
		if(adv.isDisplayed()) {
			try {
				Thread.sleep(6000);
				adv.click();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		WebElement song2=driver.findElement(By.xpath("//*[@id=\"video-title\"]"));
		song2.click();
		String actual=driver.getCurrentUrl();
		String expected="https://www.youtube.com/watch?v=U2g2AUaf5l8";
		Assert.assertEquals(actual, expected);
		driver.close();
	}

}
