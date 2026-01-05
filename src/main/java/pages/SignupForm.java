package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import com.microsoft.playwright.options.WaitForSelectorState;

import static constants.HomepageConstants.*;
import static constants.SigninConstants.*;
import static constants.SignupConstants.fullNameField;
import static constants.SignupConstants.messageEmailExists;
import static constants.SignupConstants.messageWeakPass;
import static constants.SignupConstants.messagesucess;
import static constants.SignupConstants.registerButton;

public class SignupForm extends BasePage{
    public SignupForm(Page page) {
        super(page);
    }

    public void displaySignupForm() {
        clickElement(signUpButton);
    }

    public void fillEmail(String email) {
        fillElement(emailField, email);
    }

    public void fillPassword(String password) {
        fillElement(passwordField, password);
    }

    public void fillFullName(String fullname) {
        fillElement(fullNameField, fullname);
    }

    public void signup(String email, String password, String fullname) {
        fillEmail(email);
        fillPassword(password);
        fillFullName(fullname);
        clickElement(registerButton);
    }

    public boolean isDisplayMessageSuccess() {
        try {
            page.locator(messagesucess).first()
                    .waitFor(new Locator.WaitForOptions()
                            .setState(WaitForSelectorState.VISIBLE)
                            .setTimeout(1000));
            return true;
        } catch (PlaywrightException e) {
            return false;
        }
    }

    public boolean isDisplayMessageEmailExists() {
        try {
            page.locator(messageEmailExists)
                    .waitFor(new Locator.WaitForOptions()
                            .setState(WaitForSelectorState.VISIBLE)
                            .setTimeout(1000));
            return true;
        } catch (PlaywrightException e) {
            return false;
        }
    }

    public boolean isDisplayMessageWeakPass() {
        try {
            page.locator(messageWeakPass)
                    .waitFor(new Locator.WaitForOptions()
                            .setState(WaitForSelectorState.VISIBLE)
                            .setTimeout(1000));
            return true;
        }catch (PlaywrightException e) {
            return false;
        }
    }
}
