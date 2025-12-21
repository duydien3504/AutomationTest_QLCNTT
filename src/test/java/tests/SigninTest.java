package tests;


import com.aventstack.extentreports.ExtentTest;
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
import utils.ExtentTestManager;
import utils.TestConfig;

public class SigninTest {
    private Playwright playwright;
    private Browser browser;
    private BrowserContext browserContext;
    private Page page;
    private SigninForm signinForm;

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
    public void tc_SigninSuccessful() {
        ExtentTestManager.info("Truy cap website");
        signinForm.navigatetoWebsite();
        ExtentTestManager.info("Mo popup signin");
        signinForm.displayLoginForm();

        String email = TestConfig.getLoginEmail();
        String password = TestConfig.getLoginPassword();

        ExtentTestManager.info("Dang Nhap");
        signinForm.Signin(email,password);

        Assert.assertTrue(signinForm.isDisplayLogoutButton());
    }

    @Test
    public void tc_SigninFailwithInvalidAccount() {
        ExtentTestManager.info("Truy cap website");
        signinForm.navigatetoWebsite();
        ExtentTestManager.info("Mo popup signin");
        signinForm.displayLoginForm();

        String email = TestConfig.getInvalidLoginEmail();
        String password = TestConfig.getInvalidLoginPassword();

        ExtentTestManager.info("Dang Nhap");
        signinForm.Signin(email,password);

        Assert.assertFalse(signinForm.isDisplayLogoutButton());
    }
}
