package krakentest;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;

import cucumber.api.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class stepDefinition {

	public WebDriver driver;
	public List<LogEntry> logs;

	@Given("^Setup browser \"([^\"]*)\"$")
	public void browserSetup(String browser) {		
		LoggingPreferences logs = new LoggingPreferences();
		logs.enable(LogType.BROWSER, Level.ALL);
		if(browser.equalsIgnoreCase("Firefox"))
		{			
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();
			options.setCapability(CapabilityType.LOGGING_PREFS, logs); 
			driver = new FirefoxDriver(options);
		}
		else if(browser.equalsIgnoreCase("Chrome"))
		{
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.setCapability(CapabilityType.LOGGING_PREFS, logs);
			driver = new ChromeDriver(options);
		}			
	}

	@Given("^Navigate to the Page \"([^\"]*)\"$")
	public void navigateToPage(String page) {
		driver.manage().window().maximize();
		driver.get(page);
	}

	@Then("^Verify console error on page$")
	public void validateConsoleError() {
		LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
		logs = logEntries.filter(Level.ALL);		
		if (logs.size() == 0)
			System.out.println("No console errors exists in this page");
		for (LogEntry logEntry : logs) {
			System.out.println("Errors" + logEntry);
			Assert.fail("Console errors exists in this page");
		}
		driver.quit();
	}

	@Then("^Verify response code \"([^\"]*)\" of the Page \"([^\"]*)\"$")
	public void validateResponsecode(int code, String page) {
		URL url;
		try {
			url = new URL(page);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestProperty("User-Agent", "Chrome");
			connection.setRequestMethod("GET");
			Assert.assertEquals("Response Code is not as expected",code, connection.getResponseCode());
		} catch (Exception e) {
			e.printStackTrace();
			driver.quit();
		}
		driver.quit();
	}

	@Then("^Verify broken links of the Page \"([^\"]*)\"$")
	public void validateBrokenLinks(String page) {
		URL url;
		try {
			url = new URL(page);
			driver.get(url.toString());
			List<WebElement> links = driver.findElements(By.tagName("a"));
			Iterator<WebElement> it = links.iterator();
			while (it.hasNext()) {
				String pagelinks = it.next().getAttribute("href");
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setRequestProperty("User-Agent", "Chrome");
				connection.setRequestMethod("HEAD");
				int respCode = connection.getResponseCode();
				if (respCode >= 400)
					Assert.fail(pagelinks + " is a broken link");
			}
		} catch (Exception e) {
			e.printStackTrace();
			driver.quit();
		}
		driver.quit();
	}

}
