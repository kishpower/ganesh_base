package testClasses;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pomClasses.RegisterPage;
import resources.Base;

/* test cases for Registration Page.
*
*
*
*
* @author- Ganesh Walunj
*/
@Listeners(CustomListenerForRegistration.class)
public class RegistrationTest extends Base {
	WebDriver driver;

	@BeforeTest()
	public void invokeBrowser() throws IOException {
		driver = initBrowser();
	}

	@Test(dataProvider = "getData")
	public void fillInfo(String first_name, String last_name, String email,
			String dob, String mob_no, String password, String confirm_password)
			throws IOException, HeadlessException, AWTException {
		driver.get(propFile.getProperty("baseURL"));
		RegisterPage Page = new RegisterPage(driver);

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions
				.elementToBeClickable(Page.getFirstName_field()))
				.sendKeys(first_name);
		Page.getLastName_field().sendKeys(last_name);
		Page.getMobile_field().sendKeys(mob_no);
		Page.getDob_field().sendKeys(dob);
		Page.getEmail_field().sendKeys(email);
		Page.getPassword_field().sendKeys(password);
		Page.getPasswordConfirm_field().sendKeys(confirm_password);
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].scrollIntoView(true);",
				Page.getRegister_btn());
		wait.until(
				ExpectedConditions.elementToBeClickable(Page.getRegister_btn()))
				.click();
		String expected = "MERALDA || HOME";

		String actualTitle = driver.getTitle();
		if (actualTitle.equals(expected)) {
			Assert.assertTrue(true);
		} else {

			wait.until(ExpectedConditions
					.elementToBeClickable(Page.getFirstName_field()));
			Assert.assertEquals(actualTitle, expected);
		}

	}

	@DataProvider()
	public Object[][] getData() throws IOException {
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "\\src\\main\\java\\resources\\TestData\\testDataForRegisterPage.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(0);
		int rowCount = sheet.getLastRowNum();
		short colCount = sheet.getRow(0).getLastCellNum();

		Object[][] table = new Object[rowCount][colCount];
		for (int i = 1; i <= rowCount; i++) {

			for (int j = 0; j < colCount; j++) {
				XSSFCell value = sheet.getRow(i).getCell(j);
				// System.out.println(value);
				table[i - 1][j] = value.toString();
				// System.out.println(table[i - 1][j]);
			}
		}

		return table;
	}
	@AfterTest
	public void closeBrowser() {
		driver.close();
	}

}
