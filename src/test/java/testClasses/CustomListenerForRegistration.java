package testClasses;

import java.awt.HeadlessException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import resources.Base;
/*Using Listeners for getting screenshots of
 * failed test cases for Registration Page.
 *
*
*
*
* @author- Ganesh Walunj
*/
public class CustomListenerForRegistration extends Base
		implements
			ITestListener {

	@Override
	public void onTestFailure(ITestResult result) {
		WebDriver driver = null;
		System.out.println(result.getMethod().getMethodName());
		try {
			driver = (WebDriver) result.getTestClass().getRealClass()
					.getDeclaredField("driver").get(result.getInstance());
			getScreenshot(driver, result.getMethod().getMethodName(),
					result.getParameters()[2].toString());
		} catch (IllegalArgumentException | SecurityException | IOException
				| HeadlessException | IllegalAccessException
				| NoSuchFieldException e) {

			e.printStackTrace();
		}

	}
}
