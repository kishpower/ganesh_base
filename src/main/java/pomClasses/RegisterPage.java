package pomClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
/*Initializing Page Object Model for Registration Page
*
*
*
* @author- Ganesh Walunj
*/
public class RegisterPage {

	public RegisterPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@name='first_name']")
	WebElement firstName_field;

	@FindBy(xpath = "//input[@name='last_name']")
	WebElement lastName_field;

	@FindBy(xpath = "//input[@name='email']")
	WebElement email_field;

	@FindBy(xpath = "//input[@name='mobile']")
	WebElement mobile_field;

	@FindBy(xpath = "//input[@name='date']")
	WebElement dob_field;

	@FindBy(xpath = "//input[@name='password']")
	WebElement password_field;

	@FindBy(xpath = "//input[@name='password_confirmation']")
	WebElement passwordConfirm_field;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement register_btn;

	@FindBy(xpath = "//a[text()='Login']")
	WebElement login_btn;

	public WebElement getFirstName_field() {
		return firstName_field;
	}

	public WebElement getLastName_field() {
		return lastName_field;
	}

	public WebElement getEmail_field() {
		return email_field;
	}

	public WebElement getMobile_field() {
		return mobile_field;
	}

	public WebElement getPassword_field() {
		return password_field;
	}

	public WebElement getPasswordConfirm_field() {
		return passwordConfirm_field;
	}

	public WebElement getDob_field() {
		return dob_field;
	}

	public WebElement getRegister_btn() {
		return register_btn;
	}
	public WebElement getLogin_btn() {
		return login_btn;
	}

}
