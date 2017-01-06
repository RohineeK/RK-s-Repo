package utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Initialiser {

	public static WebDriver driver;
	public static String configPropertyFile= "src\\config\\config.properties";

	public static void firefoxIinitialiser() {
		ProfilesIni profile = new ProfilesIni();
		FirefoxProfile FP = profile.getProfile("default");

		LogGenerator.info("============== Open Firefox browser ==============");
		driver = new FirefoxDriver(FP);
	}

	public static void iEIinitialiser() {
		String IEdriverPath = PropertiesReader.readProperties(configPropertyFile, "IEdriverPath");
		System.setProperty("webdriver.ie.driver", IEdriverPath);
		
		LogGenerator.info("============== Open Internet Explorer browser ==============");
		driver = new InternetExplorerDriver();
	}

	public static void chromeIinitialiser() {
		String ChromedriverPath = PropertiesReader.readProperties(configPropertyFile, "ChromedriverPath");
		System.setProperty("webdriver.chrome.driver", ChromedriverPath);

		LogGenerator.info("============== Open Chrome browser ==============");
		driver = new ChromeDriver();
		}

}
