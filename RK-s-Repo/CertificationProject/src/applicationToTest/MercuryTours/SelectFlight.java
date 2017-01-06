package applicationToTest.MercuryTours;

import org.openqa.selenium.By;

import utility.Initialiser;
import utility.LogGenerator;
import webUtils.MyActions;

public class SelectFlight {

	public static String selectFlight()
	{
		try {
			String result="FAIL";
			
			String actulDepartFromTo= "San Francisco to London";
			String expectedDepartFromTo= Initialiser.driver.findElement(By.xpath("//table[1]//tbody/tr[2]/td[1]/b/font")).getText();
			boolean flagDepart= MyActions.verifyValue(expectedDepartFromTo, actulDepartFromTo);
			if(flagDepart== true)
				result="PASS";
			
			String actulReturnFromTo= "London to San Francisco";
			String expectedReturnFromTo= Initialiser.driver.findElement(By.xpath("//table[2]//tbody/tr[2]/td[1]/b/font")).getText();
			boolean flagReturn= MyActions.verifyValue(expectedReturnFromTo, actulReturnFromTo);
			if(flagReturn== true)
				result=result + "PASS";
			else result=result + "FAIL";
			
			
			Initialiser.driver.findElement(By.xpath("//table[1]/tbody/tr[5]/td[1]/input")).click();
			
			Initialiser.driver.findElement(By.xpath("//table[2]/tbody/tr[9]/td[1]/input")).click();
			
			Initialiser.driver.findElement(By.name("reserveFlights")).click();
			
			String actulTitle= "Book a Flight: Mercury Tours";
			String expectedTitle= Initialiser.driver.getTitle();
			
			boolean flagTitle= actulTitle.equals(expectedTitle);
			
			if(flagTitle== true)
				result="PASS";
			else result=result + "FAIL";
			
			if(result.contains("FAIL"))
				result="FAIL";
			
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			LogGenerator.error("=============== Inside || SelectFlight || class ===============\n" + e.getMessage());
			return "Fail";
		}
	}
}
