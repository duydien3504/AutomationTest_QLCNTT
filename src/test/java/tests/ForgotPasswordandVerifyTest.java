package tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.ForgotPasswordandVerifyForm;
import pages.SigninForm;
import pages.SignupForm;
import utils.ExtentTestManager;
import utils.TestConfig;

@Listeners(ExtentTestNGListener.class)
public class ForgotPasswordandVerifyTest {
    private Playwright playwright;
    private Browser browser;
    private BrowserContext browserContext;
    private Page page;
    private SigninForm signinForm;
    private ForgotPasswordandVerifyForm forgotPasswordandVerifyForm;

    @BeforeClass
    public void setupClass() {
        playwright = Playwright.create();
        browser = TestConfig.getBrowserType(playwright).launch(TestConfig.getBrowserLaunchOptions());
    }

    @BeforeMethod
    public void setupTest() {
        browserContext = browser.newContext(TestConfig.getNewContextOptions());
        page = browserContext.newPage();
        signinForm = new SigninForm(page);
        forgotPasswordandVerifyForm = new ForgotPasswordandVerifyForm(page);
    }

    @AfterClass
    public void tearDownClass() {
        if(browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }

    @Test
    public void tc_RqForgotPasswordSuccessful() {
        ExtentTestManager.info("Truy cap website");
        signinForm.navigatetoWebsite();

        ExtentTestManager.info("Mo form login");
        signinForm.displayLoginForm();

        ExtentTestManager.info("Mo form Quen mat khau");
        forgotPasswordandVerifyForm.displayForgotPWForm();

        String email = TestConfig.getRsEmail();
        ExtentTestManager.info("Yeu cau quen mat khau");
        forgotPasswordandVerifyForm.forgotPassword(email);

        page.waitForTimeout(5000);

        Assert.assertTrue(forgotPasswordandVerifyForm.isDisplayVerifyForm());
    }

    @Test
    public void tc_VerifyandUpdatePassword() {
        ExtentTestManager.info("Truy cap website");
        signinForm.navigatetoWebsite();

        ExtentTestManager.info("Mo form login");
        signinForm.displayLoginForm();

        ExtentTestManager.info("Mo form Quen mat khau");
        forgotPasswordandVerifyForm.displayForgotPWForm();

        String email = TestConfig.getRsEmail();
        ExtentTestManager.info("Yeu cau quen mat khau");
        forgotPasswordandVerifyForm.forgotPassword(email);

        String otp = TestConfig.getOTP();
        String password = TestConfig.getPassword();
        ExtentTestManager.info("Xac thuc va cap nhat mat khau");
        forgotPasswordandVerifyForm.verifyAndUpdatePassword(otp,password);
        page.waitForTimeout(1500);

        Assert.assertTrue(signinForm.isDisplaySigninButton());
    }
}
