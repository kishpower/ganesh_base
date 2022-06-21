package testClasses;

import java.awt.HeadlessException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import resources.Base;
/*Using Listeners for getting screenshots of
 * failed test cases for Login Page.
*
*
*
*
* @author- Ganesh Walunj
*/
public class CustomListenerForLogin extends Base implements ITestListener {

	@Override
	public void onTestFailure(ITestResult result) {
		WebDriver driver = null;
		try {
			driver = (WebDriver) result.getTestClass().getRealClass()
					.getDeclaredField("driver").get(result.getInstance());
			System.out.println(result.getMethod().getMethodName());
			getScreenshot(driver, result.getMethod().getMethodName(),
					result.getParameters()[0].toString());
		} catch (IllegalArgumentException | SecurityException | IOException
				| HeadlessException | IllegalAccessException
				| NoSuchFieldException e) {

			e.printStackTrace();
		}

	}
}
