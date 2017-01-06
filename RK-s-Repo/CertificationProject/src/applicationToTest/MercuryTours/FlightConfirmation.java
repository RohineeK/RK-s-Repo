package applicationToTest.MercuryTours;

import org.openqa.selenium.By;

import utility.Initialiser;
import utility.LogGenerator;

public class FlightConfirmation {

	public static String flightConfirm() {
		try {
			String result = "FAIL";
			String expectedValue = "Your  itinerary has been booked!";
			String actualValue = Initialiser.driver.findElement(By.xpath("//font[contains(text(), 'booked')]")).getText();

			if (actualValue.equals(expectedValue))
				result = "PASS";

			Initialiser.driver.findElement(By.xpath("//img[@src='/images/forms/Logout.gif']")).click();

			String expectedTitle = "Sign-on: Mercury Tours";
			String actualTitle = Initialiser.driver.getTitle();

			boolean flagTitle = actualTitle.equals(expectedTitle);

			if (flagTitle == true)
				result = "PASS";
			else
				result = result + "FAIL";

			if (result.contains("FAIL"))
				result = "FAIL";

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			LogGenerator.error("=============== Inside || FlightConfirmation || class ===============\n" + e.getMessage());
			return "Fail";
		}
	}
}
