package applicationToTest.MercuryTours;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import utility.Initialiser;
import utility.LogGenerator;

public class FlightFinder {

	public static String findFlight()
	{
		try{
			
		Initialiser.driver.findElement(By.xpath("//input[@value='roundtrip']"));
		WebElement paxCount= Initialiser.driver.findElement(By.xpath("//select[@name='passCount']"));
		Select sel = new Select(paxCount);
		sel.selectByVisibleText("2");
		
		WebElement fromPort= Initialiser.driver.findElement(By.xpath("//select[@name='fromPort']"));
		sel= new Select(fromPort);
		sel.selectByVisibleText("San Francisco");
		
		WebElement fromMonth= Initialiser.driver.findElement(By.xpath("//select[@name='fromMonth']"));
		sel= new Select(fromMonth);
		sel.selectByVisibleText("December");

		WebElement fromDay= Initialiser.driver.findElement(By.xpath("//select[@name='fromDay']"));
		sel= new Select(fromDay);
		sel.selectByVisibleText("29");
		
		WebElement toPort= Initialiser.driver.findElement(By.xpath("//select[@name='toPort']"));
		sel= new Select(toPort);
		sel.selectByVisibleText("London");
		
		WebElement toMonth= Initialiser.driver.findElement(By.xpath("//select[@name='toMonth']"));
		sel= new Select(toMonth);
		sel.selectByVisibleText("December");

		WebElement toDay= Initialiser.driver.findElement(By.xpath("//select[@name='toDay']"));
		sel= new Select(toDay);
		sel.selectByVisibleText("31");
		
		Initialiser.driver.findElement(By.xpath("//input[@value='Coach']"));
		
		WebElement airline= Initialiser.driver.findElement(By.xpath("//select[@name='airline']"));
		sel= new Select(airline);
		sel.selectByVisibleText("Blue Skies Airlines");
		
		Initialiser.driver.findElement(By.name("findFlights")).click();
		
		String expectedTitle= "Select a Flight: Mercury Tours";
		
		String actulTitle= Initialiser.driver.getTitle();
		
		if(expectedTitle.equals(actulTitle))
			return "Pass";
		else
		return "Fail";
		

		}catch(Exception e)
		{
			e.printStackTrace();
			LogGenerator.error("=============== Inside || FlightFinder || class ===============\n" + e.getMessage());
			return "Fail";
		}
	}
}
