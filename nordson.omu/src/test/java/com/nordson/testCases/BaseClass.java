package com.nordson.testCases;

import org.apache.commons.mail.EmailException;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.nordson.utilities.ActionMethods;
import com.nordson.utilities.ReadConfig;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	ReadConfig readconfig = new ReadConfig();

	public String PrepodURL = readconfig.getPreprodAppURL();
	public String QAURL = readconfig.getQAAppURL();
	public String PRODURL = readconfig.getProdAppURL();
	public String SuperUsername = readconfig.getSuperAdminUsername();
	public String SuperPassword = readconfig.getSuperAdminPassword();
	String expectedTitle = "BBconnCloud";
	public static WebDriver driver;
	public static Logger log;

	// Set Up Before Class Testng Annotations launching of the Web application
	@Parameters({ "browser", "environment" })
	@BeforeClass
	public void setUp(String br, String env) {

		log = Logger.getLogger("nordson");
		PropertyConfigurator.configure("Log4j.properties");

		// Launch the Chrome Browser with Preprod Environment
		if (br.equalsIgnoreCase("chrome") && env.equalsIgnoreCase("PreProd")) {

			// Webdriver Manager Set up Path of chromedriver
			WebDriverManager.chromedriver().setup();

			// Instantiate a Chromedriver class.
			driver = new ChromeDriver();
			log.info("New Chrome driver Instantiated");

			// Preprod URL is luanched on chrome browser
			try

			{

				driver.get(PrepodURL);

			} catch (Exception e) {

				e.printStackTrace();
				log.info("Error in launching Preprod URL on chrome browser");
			}

		}

		// Launch the Firefox Browser
		else if (br.equalsIgnoreCase("firefox") && env.equalsIgnoreCase("PreProd")) {

			// Defining System Property for the FireFoxDriver
			// System.setProperty("webdriver.gecko.driver", readconfig.getFirepath());
			WebDriverManager.firefoxdriver().setup();

			// Instantiate a FireFox class.
			driver = new FirefoxDriver();
			log.info("New Firefox driver Instantiated");

			try

			{

				driver.get(PrepodURL);

			} catch (Exception e) {

				e.printStackTrace();
				log.info("Error in launching Preprod URL on firefox browser");
			}

		}

		else if (br.equalsIgnoreCase("EDGE") && env.equalsIgnoreCase("PreProd")) {

			// Defining System Property for the IEDriver
			// System.setProperty("webdriver.ie.driver", readconfig.getIEpath());

			// DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
			// caps.setCapability("ignoreZoomSetting", true);
			// driver = new InternetExplorerDriver(caps);
			WebDriverManager.edgedriver().setup();

			// Instantiate a IEDriver class.
			driver = new EdgeDriver();
			try

			{

				driver.get(PrepodURL);

			} catch (Exception e) {

				e.printStackTrace();
				log.info("Error in launching Preprod URL on IE browser");
			}

		}

		// Launch the chrome Browser in QA
		else if (br.equalsIgnoreCase("chrome") && env.equalsIgnoreCase("QA")) {

			// Defining System Property for the FireFoxDriver
			// System.setProperty("webdriver.gecko.driver", readconfig.getFirepath());
			WebDriverManager.chromedriver().setup();

			// Instantiate a FireFox class.
			driver = new ChromeDriver();
			log.info("New ChromeDriver driver Instantiated");

			try

			{

				driver.get(QAURL);

			} catch (Exception e) {

				e.printStackTrace();
				log.info("Error in launching QA URL on chrome browser");
			}

		}

		else if (br.equalsIgnoreCase("firefox") && env.equalsIgnoreCase("QA")) {

			// Defining System Property for the FireFoxDriver
			// System.setProperty("webdriver.gecko.driver", readconfig.getFirepath());
			WebDriverManager.firefoxdriver().setup();

			// Instantiate a FireFox class.
			driver = new FirefoxDriver();
			log.info("New Firefox driver Instantiated");

			try

			{

				driver.get(QAURL);

			} catch (Exception e) {

				e.printStackTrace();
				log.info("Error in launching QA URL on firefox browser");
			}

		}

		else if (br.equalsIgnoreCase("EDGE") && env.equalsIgnoreCase("QA")) {

			// Defining System Property for the IEDriver
			// System.setProperty("webdriver.ie.driver", readconfig.getIEpath());

			// DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
			// caps.setCapability("ignoreZoomSetting", true);
			// driver = new InternetExplorerDriver(caps);
			WebDriverManager.edgedriver().setup();

			// Instantiate a IEDriver class.
			driver = new EdgeDriver();

			try

			{

				driver.get(QAURL);

			} catch (Exception e) {

				e.printStackTrace();
				log.info("Error in launching QA URL on EDGE browser");
			}

		}

		// Launch the chrome Browser in QA
		else if (br.equalsIgnoreCase("chrome") && env.equalsIgnoreCase("Prod")) {

			// Defining System Property for the FireFoxDriver
			// System.setProperty("webdriver.gecko.driver", readconfig.getFirepath());
			WebDriverManager.chromedriver().setup();

			// Instantiate a FireFox class.
			driver = new ChromeDriver();
			log.info("New ChromeDriver driver Instantiated");

			try

			{

				driver.get(PRODURL);

			} catch (Exception e) {

				e.printStackTrace();
				log.info("Error in launching production URL on chrome browser");
			}

		}

		else if (br.equalsIgnoreCase("firefox") && env.equalsIgnoreCase("Prod")) {

			// Defining System Property for the FireFoxDriver
			// System.setProperty("webdriver.gecko.driver", readconfig.getFirepath());
			WebDriverManager.firefoxdriver().setup();

			// Instantiate a FireFox class.
			driver = new FirefoxDriver();
			log.info("New Firefox driver Instantiated");

			try

			{

				driver.get(PRODURL);

			} catch (Exception e) {

				e.printStackTrace();
				log.info("Error in launching production URL on firefox browser");
			}

		}

		else if (br.equalsIgnoreCase("EDGE") && env.equalsIgnoreCase("Prod")) {

			// Defining System Property for the IEDriver
			// System.setProperty("webdriver.ie.driver", readconfig.getIEpath());

			// DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
			// caps.setCapability("ignoreZoomSetting", true);
			// driver = new InternetExplorerDriver(caps);
			WebDriverManager.edgedriver().setup();

			// Instantiate a IEDriver class.
			driver = new EdgeDriver();

			try

			{

				driver.get(PRODURL);

			} catch (Exception e) {

				e.printStackTrace();
				log.info("Error in launching production URL on EDGE browser");
			}

		}

		else

		{
			System.out.println("Invalid Browser input");
			log.info("Invalid browser input in testng.xml");
		}

	}

	@AfterClass
	public void tearDown() throws EmailException {
		ActionMethods.sendEmail();
		driver.quit();
		log.info("All the tabs are closed");

	}

}