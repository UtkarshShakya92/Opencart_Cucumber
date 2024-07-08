package factory;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseClass {

	public static WebDriver driver;
	public static Properties p;
	public static Logger logger;

	public static WebDriver initializeBrowser() throws IOException {
		p = getProperties();

		String executionEnv = p.getProperty("execution_env");

		String browser = p.getProperty("browser").toLowerCase();

		String os = p.getProperty("os").toLowerCase();

		if (executionEnv.equalsIgnoreCase("remote")) {
			DesiredCapabilities capabilities = new DesiredCapabilities();

			// os
			switch (os) {
			case "windows":
				capabilities.setPlatform(Platform.WINDOWS);
				break;

			case "Linux":
				capabilities.setPlatform(Platform.LINUX);
				break;

			case "mac":
				capabilities.setPlatform(Platform.MAC);
				break;

			default:
				System.out.println("Invalid OS");
				return null;
			}

			// browser

			switch (browser) {
			case "chrome":
				driver = new ChromeDriver();
				break;
			case "edge":
				driver = new EdgeDriver();
				break;
			case "firefox":
				driver = new FirefoxDriver();
				break;
			default:
				System.out.println("Invalid Browser");
				return null;

			}

			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
		}

		else if (executionEnv.equalsIgnoreCase("local")) {
			// browser

			switch (browser) {
			case "chrome":
				driver = new ChromeDriver();
				break;
			case "edge":
				driver = new EdgeDriver();
				break;
			case "firefox":
				driver = new FirefoxDriver();
				break;
			default:
				System.out.println("Invalid Browser");
				driver=null;

			}
			
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));

		return driver;
	}

	public static WebDriver getDriver() {

		return driver;
	}

	public static Properties getProperties() throws IOException {
		FileReader file = new FileReader(".//src//test//resources//config.properties");

		p = new Properties();
		p.load(file);
		return p;
	}

	public static Logger getLogger() {
		logger = LogManager.getLogger();
		return logger;
	}
	
	
	public static String randomString()
	{
		String generatedString = RandomStringUtils.randomAlphabetic(8);
		return generatedString;
	}

	public static String randomEmail()
	{
		String generatedEmail = RandomStringUtils.randomAlphabetic(5)+"@gmail.com";
		return generatedEmail;
	}
	
	public static String randomNumber()
	{
		String generatedNumber = RandomStringUtils.randomNumeric(10);
		return randomNumber();
	}
	
	public static String randomPaswword()
	{
		String generatedPassword = RandomStringUtils.randomAlphanumeric(6);
		return randomPaswword();
	}
}
