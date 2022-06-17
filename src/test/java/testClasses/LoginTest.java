package testClasses;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pomClasses.LoginPage;
import pomClasses.RegisterPage;
import resources.Base;

/* test cases for Login Page.
 *
*
*
*
* @author- Ganesh Walunj
*/
@Listeners(CustomListenerForLogin.class)
public class LoginTest extends Base {
	WebDriver driver;

	RegisterPage rPage;
	String LoginURL;

	@BeforeTest()
	public void invokeBrowser() throws IOException {
		driver = initBrowser();
	}

	@Test()
	public void openLoginPage() {
		driver.get(propFile.getProperty("baseURL"));
		RegisterPage rPage = new RegisterPage(driver);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(
				ExpectedConditions.elementToBeClickable(rPage.getLogin_btn()))
				.click();
		LoginURL = driver.getCurrentUrl();

	}

	@Test(dependsOnMethods = {
			"openLoginPage"}, dataProvider = "getDataForLogin")
	public void fillCredentials(String email, String mobile, String password) {
		driver.get(LoginURL);
		LoginPage lPage = new LoginPage(driver);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions
				.elementToBeClickable(lPage.getUsername_field()))
				.sendKeys(email);
		lPage.getPassword_field().sendKeys(password);
		lPage.getLogin_btn().click();
		String expected = "MERALDA || HOME";
		String actualTitle = driver.getTitle();
		if (actualTitle.equals(expected)) {
			Assert.assertTrue(true);
		} else {

			wait.until(ExpectedConditions
					.elementToBeClickable(lPage.getUsername_field()));
			Assert.assertEquals(actualTitle, expected);
		}

	}

	@DataProvider()
	public Object[][] getDataForLogin() throws IOException {
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "\\src\\main\\java\\resources\\TestData\\testDataForLoginPage.xlsx");
		try (XSSFWorkbook wb = new XSSFWorkbook(fis)) {
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

	}

	@AfterTest
	public void closeTab() {
		driver.close();
	}
}
