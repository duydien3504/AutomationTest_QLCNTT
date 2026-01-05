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
import pages.MusclePage;
import pages.SigninForm;
import utils.ExtentTestManager;
import utils.TestConfig;

public class MuscleTest {
    private Playwright playwright;
    private Browser browser;
    private BrowserContext browserContext;
    private Page page;
    private SigninForm signinForm;
    private MusclePage musclePage;

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
        musclePage = new MusclePage(page);
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
    public void tc_CreateNewMuscle() {
        ExtentTestManager.info("Truy cap website");
        signinForm.navigatetoWebsite();
        ExtentTestManager.info("Mo popup signin");
        signinForm.displayLoginForm();

        String email = TestConfig.getLoginEmail();
        String password = TestConfig.getLoginPassword();

        ExtentTestManager.info("Dang Nhap");
        signinForm.Signin(email,password);

        ExtentTestManager.info("Mo dashboard");
        musclePage.displayAdminPage();

        ExtentTestManager.info("Mo form tao nhom co");
        musclePage.displayeCreateMuscleForm();

        String muscleName = TestConfig.getNewMucle();
        String description = TestConfig.getDescriptionMuscle();
        String url = TestConfig.getThumbnailUrl();

        ExtentTestManager.info("Tao nhom co");
        musclePage.createNewMuscle(muscleName,description,url);

        page.waitForTimeout(1000);

        Assert.assertTrue(musclePage.checkInfoMuscle(muscleName, muscleName));
    }

    @Test
    public void tc_CreateNewMusclewithName() {
        ExtentTestManager.info("Truy cap website");
        signinForm.navigatetoWebsite();
        ExtentTestManager.info("Mo popup signin");
        signinForm.displayLoginForm();

        String email = TestConfig.getLoginEmail();
        String password = TestConfig.getLoginPassword();

        ExtentTestManager.info("Dang Nhap");
        signinForm.Signin(email,password);

        ExtentTestManager.info("Mo dashboard");
        musclePage.displayAdminPage();

        ExtentTestManager.info("Mo form tao nhom co");
        musclePage.displayeCreateMuscleForm();

        ExtentTestManager.info("Tao nhom co");
        String muscleName = TestConfig.getMuscle1();
        musclePage.fillNameMuscle(muscleName);

        musclePage.clickCreate();

        page.waitForTimeout(1000);

        Assert.assertTrue(musclePage.checkInfoMuscle(muscleName, muscleName));
    }
}
