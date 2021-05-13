package com.nordson.testCases;

import java.io.File;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.nordson.utilities.ReadConfig;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	ReadConfig readconfig = new ReadConfig();

	public String baseURL = readconfig.getAppURL();
	public String SuperUsername = readconfig.getSuperAdminUsername();
	public String SuperPassword = readconfig.getSuperAdminPassword();
	String expectedTitle = "BBconnCloud";
	public static WebDriver driver;
	public static Logger log;

	// Set Up Before Class Testng Annotations launching of the Web application
	@Parameters("browser")
	@BeforeClass
	public void setUp(String br) {

		log = Logger.getLogger("nordson");
		PropertyConfigurator.configure("Log4j.properties");

		// Launch the Chrome Browser
		if (br.equalsIgnoreCase("chrome")) {



			 String downloadNorPath=System.getProperty("user.dir")+"\\src\\test\\java\\com\\nordson\\testData\\Norfiles\\";
            File downloadFolder=new File(downloadNorPath);
            downloadFolder.mkdir();
          
             HashMap<String,Object> chromeprefs=new HashMap<String,Object>();
             chromeprefs.put("download.default_directory",downloadNorPath);
           
             ChromeOptions options= new ChromeOptions();
             options.setExperimentalOption("prefs", chromeprefs);
             System.setProperty("webdriver.chrome.driver", readconfig.getChromepath());
          // Instantiate a FireFox class.

			// ChromeOptions chromeOptions = new ChromeOptions();
			// ChromeOptions chromeOptions = new ChromeOptions();
			//
			// Defining System Property for the ChromeDriver
			// System.setProperty("webdriver.chrome.driver", readconfig.getChromepath());
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);

			// Instantiate a FireFox class.
			// driver = new ChromeDriver();
		}

		// Launch the Firefox Browser
		else if (br.equalsIgnoreCase("firefox")) {

			// Defining System Property for the FireFoxDriver
			// System.setProperty("webdriver.gecko.driver", readconfig.getFirepath());
			WebDriverManager.firefoxdriver().setup();
			// Instantiate a FireFox class.
			driver = new FirefoxDriver();
			log.info("New Firefox driver Instantiated");

		}

		else if (br.equalsIgnoreCase("IE")) {

			// Defining System Property for the IEDriver
			// System.setProperty("webdriver.ie.driver", readconfig.getIEpath());

			// DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
			// caps.setCapability("ignoreZoomSetting", true);
			// driver = new InternetExplorerDriver(caps);
			WebDriverManager.iedriver().setup();

			// Instantiate a IEDriver class.
			driver = new InternetExplorerDriver();

		}

		else {
			System.out.println("Invalid Browser input");
			log.info("Invalid browser input in testng.xml");
		}

		try

		{
			driver.get(baseURL);

		} catch (Exception e) {

			e.printStackTrace();
			log.info("Error in launching the page");
		}
	}

	// Tear Down Method Before Class Testng Annotations launching of the Web
	// application
	@AfterClass
	public void tearDown() {

		driver.quit();
		log.info("All the tabs are closed");

	}

}
