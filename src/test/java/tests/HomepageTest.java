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
import utils.ExtentTestManager;
import utils.TestConfig;

public class HomepageTest {
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
    public void tc_Logout() {
        ExtentTestManager.info("Truy cap website");
        signinForm.navigatetoWebsite();

        ExtentTestManager.info("Mo form Dang Nhap");
        signinForm.displayLoginForm();

        ExtentTestManager.info("Dang Nhap");
        String email = TestConfig.getLoginEmail();
        String password = TestConfig.getLoginPassword();
        signinForm.Signin(email,password);
        page.waitForTimeout(2000);

        ExtentTestManager.info("Dang Xuat");
        signinForm.logOut();

        Assert.assertTrue(signinForm.isDisplaySigninButton());
    }

}
