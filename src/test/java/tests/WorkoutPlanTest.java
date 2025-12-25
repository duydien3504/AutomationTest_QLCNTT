package tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import constants.WorkoutPlanConstants;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.SigninForm;
import pages.SignupForm;
import pages.WorkoutPlanPage;
import utils.DateUtils;
import utils.ExtentTestManager;
import utils.TestConfig;

import static constants.WorkoutPlanConstants.*;
import static constants.WorkoutPlanConstants.muscleDay;

@Listeners(ExtentTestNGListener.class)
public class WorkoutPlanTest {
    private Playwright playwright;
    private Browser browser;
    private BrowserContext browserContext;
    private Page page;
    private SigninForm signinForm;
    private WorkoutPlanPage workoutPlanPage;

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
        workoutPlanPage = new WorkoutPlanPage(page);
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

    /*@Test
    public void tc_CreateWkPlanSuccesful() {
        ExtentTestManager.info("Truy cap website");
        signinForm.navigatetoWebsite();
        ExtentTestManager.info("Mo popup signin");
        signinForm.displayLoginForm();

        String email = TestConfig.getLoginEmail();
        String password = TestConfig.getLoginPassword();

        ExtentTestManager.info("Dang Nhap");
        signinForm.Signin(email,password);

        ExtentTestManager.info("Mo trang lich tap");
        workoutPlanPage.displayWkPlanPage();

        ExtentTestManager.info("Mo form tao ke hoach tap luyen");
        workoutPlanPage.displayCreatePlanForm();

        String namePlan = TestConfig.getNamePlan();
        String startDate = TestConfig.getStartDate();
        String endDate = TestConfig.getEndDate();
        String description = TestConfig.getDescription();

        ExtentTestManager.info("Tao ke hoach tap luyen");
        workoutPlanPage.CreateWorkoutPlan(namePlan, startDate, endDate, description);
        page.waitForTimeout(1500);

        Assert.assertTrue(workoutPlanPage.displayMessageSuccesful());
    }*/

    @Test
    public void tc_AddExintoSchedule() {
        ExtentTestManager.info("Truy cap website");
        signinForm.navigatetoWebsite();
        ExtentTestManager.info("Mo popup signin");
        signinForm.displayLoginForm();

        String email = TestConfig.getLoginEmail();
        String password = TestConfig.getLoginPassword();

        ExtentTestManager.info("Dang Nhap");
        signinForm.Signin(email,password);

        ExtentTestManager.info("Mo trang lich tap");
        workoutPlanPage.displayWkPlanPage();

        ExtentTestManager.info("Mo form tao ke hoach tap luyen");
        workoutPlanPage.displayCreatePlanForm();

        String namePlan = TestConfig.getNamePlan();
        String startDate = TestConfig.getStartDate();
        String endDate = TestConfig.getEndDate();
        String description = TestConfig.getDescription();

        ExtentTestManager.info("Tao ke hoach tap luyen");
        workoutPlanPage.CreateWorkoutPlan(namePlan, startDate, endDate, description);

        String day = TestConfig.getDay();
        String nameSchedule = TestConfig.getSchedule();
        String muscle = TestConfig.getMuscle();
        String exLevel = TestConfig.getExLevel();
        String sets = TestConfig.getSets();
        String reps = TestConfig.getReps();
        String rests = TestConfig.getRests();

        workoutPlanPage.AddExercise(day, nameSchedule, muscle, exLevel, sets, reps, rests);
        page.waitForTimeout(1000);

        workoutPlanPage.reloadPage();
        page.waitForTimeout(1500);

        String formatCalendar = DateUtils.formatDateRange(startDate, endDate);

        String titleTxt = page.locator(titlePlan).innerText();
        String descriptionTxt = page.locator(descriptionPlan).first().innerText();

        String calendarTxt = page.locator(calendar).innerText();
        String formatCalendarTxt = calendarTxt
                .replaceAll("\\s*-\\s*", " - ")
                .replaceAll("\\s+", " ")
                .trim();
        String muscleDayTxt = page.locator(muscleDay).innerText();
        String Sets = page.locator(WorkoutPlanConstants.sets).innerText();
        String Reps = page.locator(WorkoutPlanConstants.reps).innerText();
        String Rests = page.locator(WorkoutPlanConstants.rests).innerText().replaceAll("[^0-9]", "");;

        Assert.assertEquals(titleTxt, namePlan, "Title");
        Assert.assertEquals(descriptionTxt, description, "Mo ta");
        Assert.assertEquals(formatCalendarTxt, formatCalendar, "Lich");
        Assert.assertEquals(muscleDayTxt, nameSchedule, "Ngay tap");
        Assert.assertEquals(Sets, sets, "set");
        Assert.assertEquals(Reps, reps, "rep");
        Assert.assertEquals(Rests, rests, "rests");

    }
}
