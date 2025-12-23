package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import com.microsoft.playwright.options.WaitForSelectorState;

import static constants.ForgotPasswordandVerifyConstants.*;
import static constants.HomepageConstants.signInButton;
import static constants.SignupConstants.textForgotPW;

public class ForgotPasswordandVerifyForm extends BasePage{
    public ForgotPasswordandVerifyForm(Page page) {
        super(page);
    }

    public void displayLoginForm() {
        clickElement(signInButton);
    }

    public void displayForgotPWForm() {
        clickElement(textForgotPW);
    }

    public void forgotPassword(String email) {
        fillElement(emailInput, email);
        clickElement(fgPasswordButton);
    }

    public void verifyAndUpdatePassword(String otp, String password) {
        fillElement(otpCodeInput, otp);
        fillElement(newPasswordInput, password);
        fillElement(confirmPasswordInput, password);
        clickElement(resetPasswordButton);
    }

    public boolean isDisplayVerifyForm() {
        try {
            page.locator(titleVerifyForm)
                    .waitFor(new Locator.WaitForOptions()
                            .setState(WaitForSelectorState.VISIBLE)
                            .setTimeout(5000));
            return true;
        } catch (PlaywrightException e) {
            return false;
        }
    }


}
