package applicationToTest.MercuryTours;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import utility.Initialiser;
import utility.LogGenerator;
import webUtils.MyActions;

public class WelcomePage {
	
	public static String login(String URL)
	{
		try {
			String result= "FAIL";
			
			LogGenerator.info("============== implicit wait in login method ==============");
			Initialiser.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Initialiser.driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
			
			Initialiser.driver.get(URL);
			Initialiser.driver.findElement(By.xpath("//input [@name='userName']")).sendKeys("mercury");
			Initialiser.driver.findElement(By.xpath("//input [@name='password']")).sendKeys("mercury");
			Initialiser.driver.findElement(By.xpath("//input [@name='login']")).click();
			
			String actualValue= MyActions.getTitle();
			boolean b = MyActions.verifyValue("Find a Flight: Mercury Tours:", actualValue);
			if (b == true)
			{
				LogGenerator.info("============== Title [ " + actualValue + " ] maches with Expected value ==============");
				result = "PASS";
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			LogGenerator.error("=============== Inside || WelcomePage || class ===============\n" + e.getMessage());
			return "Fail";
		}
	}

}
