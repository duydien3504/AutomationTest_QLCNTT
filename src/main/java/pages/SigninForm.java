package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import com.microsoft.playwright.options.WaitForSelectorState;

import static constants.HomepageConstants.*;
import static constants.SigninConstants.*;

public class SigninForm extends BasePage{
    public SigninForm(Page page) {
        super(page);
    }

    public void displayLoginForm() {
        clickElement(signInButton);
    }

    public void fillEmail(String email) {
        fillElement(emailField, email);
    }

    public void fillPassword(String password) {
        fillElement(passwordField, password);
    }

    public void Signin(String email, String password) {
        fillEmail(email);
        fillPassword(password);
        clickElement(loginButton);
    }

    public void logOut() {
        clickElement(logoutButton);
    }

    public boolean isDisplayLogoutButton() {
        try {
            page.locator(logoutButton)
                    .waitFor(new Locator.WaitForOptions()
                            .setState(WaitForSelectorState.VISIBLE)
                            .setTimeout(1000));
            return true;
        } catch (PlaywrightException e) {
            return false;
        }
    }

    public boolean isDisplaySigninButton() {
        try {
            page.locator(signInButton)
                    .waitFor(new Locator.WaitForOptions()
                            .setState(WaitForSelectorState.VISIBLE)
                            .setTimeout(1000));
            return true;
        } catch (PlaywrightException e) {
            return false;
        }
    }
}
