package resources;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.imageio.ImageIO;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

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
		Dimension d = new Dimension(400, 400);
		driver.manage().window().setSize(d);;

		return driver;
	}

	// public void getScreenshot(WebDriver driver, String methodName, String
	// email)
	// throws IOException {
	//
	//
	// TakesScreenshot ts = (TakesScreenshot) driver;
	// File src = ts.getScreenshotAs(OutputType.FILE);
	// String destination = BASE_DIR + "\\reports\\" + methodName + "-" + email
	// + ".jpg";
	// FileHandler.copy(src, new File(destination));
	// }

	public void getScreenshot(String methodName, String email)
			throws IOException, HeadlessException, AWTException {

		BufferedImage image = new Robot().createScreenCapture(
				new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		ImageIO.write(image, "jpg", new File(
				BASE_DIR + "\\reports\\" + methodName + "-" + email + ".jpg"));
	};

}
