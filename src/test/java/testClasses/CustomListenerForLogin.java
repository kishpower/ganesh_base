package testClasses;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.io.IOException;

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

		try {
			System.out.println(result.getMethod().getMethodName());
			getScreenshot(result.getMethod().getMethodName(),
					result.getParameters()[0].toString());
		} catch (IllegalArgumentException | SecurityException | IOException
				| HeadlessException | AWTException e) {

			e.printStackTrace();
		}

	}
}
