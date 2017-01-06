package utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
	public static String readProperties(String propertyFile, String property) {

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(propertyFile);

			// load a properties file
			prop.load(input);

			// get the property value and return same
			String property_value= prop.getProperty(property);
			LogGenerator.info("Reading property: "+ property + " In property File "+propertyFile);
			return property_value;

		} catch (IOException ex) {
			ex.printStackTrace();
			LogGenerator.error("Properties file not found");
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return "Properties File not found";
	}
}
