package tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.SigninForm;
import pages.SignupForm;
import utils.ExtentTestManager;
import utils.TestConfig;

public class SignupTest {
    private Playwright playwright;
    private Browser browser;
    private BrowserContext browserContext;
    private Page page;
    private SignupForm signupForm;

    @BeforeClass
    public void setupClass() {
        playwright = Playwright.create();
        browser = TestConfig.getBrowserType(playwright).launch(TestConfig.getBrowserLaunchOptions());
    }

    @BeforeMethod
    public void setupTest() {
        browserContext = browser.newContext(TestConfig.getNewContextOptions());
        page = browserContext.newPage();
        signupForm = new SignupForm(page);
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
    public void tc_SignupSuccessful() {
        ExtentTestManager.info("Truy cap website");
        signupForm.navigatetoWebsite();

        ExtentTestManager.info("Mo form dang ky");
        signupForm.displaySignupForm();

        String email = TestConfig.getSignupEmail();
        String password = TestConfig.getSignupPassword();
        String fullname = TestConfig.getSignupFullname();

        ExtentTestManager.info("Dang ky");
        signupForm.signup(email,password,fullname);
        page.waitForTimeout(1500);

        Assert.assertFalse(signupForm.isDisplaySignupButton());
    }
}
