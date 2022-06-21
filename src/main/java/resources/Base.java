package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;

/*Base class for invoking browser,creating screen shots,
 * reading properties file
*
*
*
* @author- Ganesh Walunj
*/
public class Base {

	WebDriver driver;
	protected static Properties propFile;
	protected static final String BASE_DIR = System.getProperty("user.dir");

	public WebDriver initBrowser() throws IOException {
		FileInputStream FIS = new FileInputStream(
				BASE_DIR + "\\src\\main\\java\\resources\\data.properties");
		propFile = new Properties();
		propFile.load(FIS);

		String browserName = propFile.getProperty("browser");
		if (browserName.equalsIgnoreCase("firefox")) {
			if (System.getProperty("os.arch").contains("64")) {
				System.setProperty("webdriver.gecko.driver", BASE_DIR
						+ "\\\\src\\\\main\\\\java\\\\resources\\\\BrowserDrivers\\\\FireFox\\\\geckodriver64.exe");
				driver = new FirefoxDriver();
			} else {
				System.setProperty("webdriver.gecko.driver", BASE_DIR
						+ "\\\\src\\\\main\\\\java\\\\resources\\\\BrowserDrivers\\\\FireFox\\\\geckodriver.exe");
				driver = new FirefoxDriver();
			}

		} else if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", BASE_DIR
					+ "\\src\\main\\java\\resources\\BrowserDrivers\\Chrome\\chromedriver.exe");
			driver = new ChromeDriver();
		}

		driver.manage().window().maximize();

		return driver;
	}

	public void getScreenshot(WebDriver driver, String methodName, String email)
			throws IOException {
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("window.scrollTo(0,0);");

		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		String destination = BASE_DIR + "\\reports\\" + methodName + "-" + email
				+ ".jpg";
		FileHandler.copy(src, new File(destination));
	}

	// public void getScreenshot(String methodName, String email)
	// throws IOException, HeadlessException, AWTException {
	//
	// BufferedImage image = new Robot().createScreenCapture(
	// new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
	// ImageIO.write(image, "jpg", new File(
	// BASE_DIR + "\\reports\\" + methodName + "-" + email + ".jpg"));
	// };

}
