package tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.SigninForm;
import pages.SignupForm;
import utils.ExtentTestManager;
import utils.TestConfig;

@Listeners(ExtentTestNGListener.class)
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

        Assert.assertTrue(signupForm.isDisplayMessageSuccess());
    }

    @Test
    public void tc_SignupFailWithInvalidFormatEmail() {
        ExtentTestManager.info("Truy cap website");
        signupForm.navigatetoWebsite();

        ExtentTestManager.info("Mo form dang ky");
        signupForm.displaySignupForm();

        String email = TestConfig.getInvalidFormatEmail();
        String password = TestConfig.getSignupPassword();
        String fullname = TestConfig.getSignupFullname();

        ExtentTestManager.info("Dang ky");
        signupForm.signup(email, password, fullname);

        Assert.assertFalse(signupForm.isDisplayMessageSuccess());
    }

    @Test
    public void tc_SignupWithEmailExists() {
        ExtentTestManager.info("Truy cap website");
        signupForm.navigatetoWebsite();

        ExtentTestManager.info("Mo form dang ky");
        signupForm.displaySignupForm();

        String email = TestConfig.getLoginEmail();
        String password = TestConfig.getSignupPassword();
        String fullname = TestConfig.getSignupFullname();

        ExtentTestManager.info("Dang ky");
        signupForm.signup(email, password, fullname);

        Assert.assertTrue(signupForm.isDisplayMessageEmailExists());
    }

    @Test
    public void tc_SignupWithShortFullname() {
        ExtentTestManager.info("Truy cap website");
        signupForm.navigatetoWebsite();

        ExtentTestManager.info("Mo form dang ky");
        signupForm.displaySignupForm();

        String email = TestConfig.getEmail();
        String password = TestConfig.getSignupPassword();
        String fullname = TestConfig.getShortFullname();

        ExtentTestManager.info("Dang ky");
        signupForm.signup(email, password, fullname);
        page.waitForTimeout(1500);

        Assert.assertFalse(signupForm.isDisplayMessageSuccess());
    }

    @Test
    public void tc_SignupWithWeakPassword() {
        ExtentTestManager.info("Truy cap website");
        signupForm.navigatetoWebsite();

        ExtentTestManager.info("Mo form dang ky");
        signupForm.displaySignupForm();

        String email = TestConfig.getEmail1();
        String password = TestConfig.getWeakPass();
        String fullname = TestConfig.getFullName();

        ExtentTestManager.info("Dang ky");
        signupForm.signup(email, password, fullname);
        page.waitForTimeout(1500);

        Assert.assertFalse(signupForm.isDisplayMessageSuccess() && signupForm.isDisplayMessageWeakPass());
    }
}
