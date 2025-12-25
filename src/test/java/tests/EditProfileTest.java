package tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import constants.ProfileConstants;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.ProfilePage;
import pages.SigninForm;
import utils.ExtentTestManager;
import utils.RandomFileUtils;
import utils.TestConfig;

import static constants.ProfileConstants.birthday;
import static constants.ProfileConstants.fullName;

@Listeners(ExtentTestNGListener.class)
public class EditProfileTest {
    private Playwright playwright;
    private Browser browser;
    private BrowserContext browserContext;
    private Page page;
    private SigninForm signinForm;
    private ProfilePage profilePage;

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
        profilePage = new ProfilePage(page);
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
    public void tc_EditProfile() {
        ExtentTestManager.info("Truy cap website");
        signinForm.navigatetoWebsite();
        ExtentTestManager.info("Mo popup signin");
        signinForm.displayLoginForm();

        String email = TestConfig.getLoginEmail();
        String password = TestConfig.getLoginPassword();

        ExtentTestManager.info("Dang Nhap");
        signinForm.Signin(email,password);

        ExtentTestManager.info("Truy cap trang Profile");
        profilePage.displayProfilePage();

        ExtentTestManager.info("Mo form chinh sua Profile");
        profilePage.displayEditProfileForm();

        String imageFolder = "D:\\";
        String randomAvatar = RandomFileUtils.getRandomJpg(imageFolder);
        String fullname = TestConfig.getFullName();
        String gender = TestConfig.getGender();
        String date = TestConfig.getDate();
        String goal = TestConfig.getGoal();
        String level = TestConfig.getLevel();

        ExtentTestManager.info("Thuc hien cap nhat thong tin");
        profilePage.EditProfile(randomAvatar, fullname, gender, date, goal, level);
        page.waitForTimeout(5000);


        //So sanh message
        Assert.assertTrue(profilePage.isDisplayMessageSuccess());
        //So sanh value
        Assert.assertTrue(profilePage.checkInfoAfUpdate(fullname, gender.toLowerCase(), date, goal, level));
    }
}
