package applicationToTest.MercuryTours;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import utility.Initialiser;
import utility.LogGenerator;

public class BookFlight {

	public static String bookFlight()
	{	
		try {
			Initialiser.driver.findElement(By.name("passFirst0")).sendKeys("Rohinee");
			Initialiser.driver.findElement(By.name("passLast0")).sendKeys("Kadam");
			WebElement meal1= Initialiser.driver.findElement(By.name("pass.0.meal"));
			Select sel= null;
			sel= new Select(meal1);
			sel.selectByVisibleText("Hindu");
			
			Initialiser.driver.findElement(By.name("passFirst1")).sendKeys("Prakash");
			Initialiser.driver.findElement(By.name("passLast1")).sendKeys("Yadav");
			WebElement meal2= Initialiser.driver.findElement(By.name("pass.1.meal"));
			sel= new Select(meal2);
			sel.selectByVisibleText("Low Calorie");
			
			WebElement creditCardType= Initialiser.driver.findElement(By.name("creditCard"));
			sel= new Select(creditCardType);
			sel.selectByVisibleText("Discover");
			
			Initialiser.driver.findElement(By.name("creditnumber")).sendKeys("89755321");
			
			WebElement cardExpiryMonth= Initialiser.driver.findElement(By.name("cc_exp_dt_mn"));
			sel= new Select(cardExpiryMonth);
			sel.selectByVisibleText("01");
			
			WebElement cardExpiryYear= Initialiser.driver.findElement(By.name("cc_exp_dt_yr"));
			sel= new Select(cardExpiryYear);
			sel.selectByVisibleText("2010");
			
			Initialiser.driver.findElement(By.xpath("//font[contains(text(), 'Ticketless')]/preceding-sibling::input[@name='ticketLess']")).click();
			
			Initialiser.driver.findElement(By.name("billAddress1")).sendKeys("#234 Sai Vastu, Near BEML complex");
			Initialiser.driver.findElement(By.name("billAddress2")).sendKeys("Rajrajeshwari Nagar, Bangalore");
			Initialiser.driver.findElement(By.name("billCity")).sendKeys("Bangalore");
			
			WebElement billCountry= Initialiser.driver.findElement(By.xpath("//select[@name='billCountry']"));
			sel= new Select(billCountry);
			sel.selectByVisibleText("INDIA");
			
			Initialiser.driver.findElement(By.xpath("//font[contains(text(), 'Same as')]/preceding-sibling::input[@name='ticketLess']")).click();
			
			Initialiser.driver.findElement(By.name("buyFlights")).click();
			
			String actualTitle= "Flight Confirmation: Mercury Tours";
			String expectedTitle= Initialiser.driver.getTitle();
			if(expectedTitle.equals(actualTitle))
				return "Pass";
			else
			return "Fail";
		} catch (Exception e) {
			e.printStackTrace();
			LogGenerator.error("=============== Inside || BookFlight || class ===============\n" + e.getMessage());
			return "Fail";
		}
	}
}
