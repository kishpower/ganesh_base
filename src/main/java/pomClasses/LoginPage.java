package pomClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
/*Initializing Page Object Model for Login Page
 *
 *
 *
 * @author- Ganesh Walunj
*/
public class LoginPage {
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@name='username']")
	WebElement username_field;

	@FindBy(xpath = "//input[@name='password']")
	WebElement password_field;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement login_btn;

	public WebElement getLogin_btn() {
		return login_btn;
	}

	public WebElement getUsername_field() {
		return username_field;
	}

	public WebElement getPassword_field() {
		return password_field;
	}

}
