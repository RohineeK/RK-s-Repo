package utility;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


public class Screenshot {

	 public static void takeScreenShot(String fileName) {

	        //Convert web driver object to TakeScreenshot

	        TakesScreenshot scrShot =((TakesScreenshot)Initialiser.driver);

	        //Call getScreenshotAs method to create image file

	                File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
	                String screenshotPath = PropertiesReader.readProperties(Initialiser.configPropertyFile, "ScreenShotPath");
	                try{
	    	            String fName[]= fileName.split("/");
	    	            String folderName=fName[0];
	    	            fileName=fName[1];

	    	        		DateFormat df= new SimpleDateFormat("yyyy_dd_MM");
	    	        		Date d = new Date();
	    	        		folderName= folderName+"_"+df.format(d);
	    	        		screenshotPath= screenshotPath+"/"+folderName+"/"+fileName;
	    	            
	    	        		df= new SimpleDateFormat("yyyy_dd_MM-hh_mm_ss");
	    	            String stamp =  fileName +"_"+df.format(d);
	    	            File dir = new File("" + screenshotPath);
	    	            dir.mkdirs();
	    	            File permfile = new File(dir, stamp + ".JPG");
	    	            permfile.createNewFile();
	    	            LogGenerator.info("============== File generated "+ permfile.getName()+" ==============");
	    	            //Copy file at destination

		                FileUtils.copyFile(SrcFile, permfile);
	    	            }
	    	            catch(Exception k)
	    	            { 
	    	            	k.printStackTrace();
	    	            	LogGenerator.error("============== screenshot not generated "+k.getMessage() + " ==============");
	    	            }
	                
	            //Move image file to new destination

	          //      File DestFile=new File(s);

	               

	            LogGenerator.info("============ Screenshot Taken ============");

	    }
}
