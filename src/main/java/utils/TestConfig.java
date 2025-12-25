package utils;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Properties;

public class TestConfig {
    private static Properties properties;
    private static final String CONFIG_FILE = "src/test/resources/config.properties";

    static {
        loadProperties();
    }

    private static void loadProperties() {
        properties = new Properties();
        try{
            FileInputStream input = new FileInputStream(CONFIG_FILE);
            properties.load(input);
            input.close();
        } catch (IOException e) {
            System.out.println("Error loading properties file: " + e.getMessage());
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static BrowserType getBrowserType(Playwright playwright) {
        String browserType = getProperty("browser");
        if(browserType.equals("chrome")) {
            return playwright.chromium();
        }
        return playwright.chromium();
    }

    public static Browser.NewContextOptions getNewContextOptions() {
        try{
            Files.createDirectories(Paths.get("videos"));
        } catch (IOException ignored) {

        }

        return new Browser.NewContextOptions()
                .setViewportSize(null)
                .setIgnoreHTTPSErrors(true)
                .setRecordVideoDir(Paths.get("videos"))
                .setRecordVideoSize(1280,720);
    }

    public static BrowserType.LaunchOptions getBrowserLaunchOptions() {
        return new BrowserType.LaunchOptions()
                .setChannel("chrome")
                .setHeadless(false)
                .setArgs(Arrays.asList("--start-maximized"));
    }

    public static String getBaseUrl() {
        return getProperty("base_url");
    }

    public static String getLoginEmail() {
        return getProperty("test.validlogin.email");
    }

    public static String getLoginPassword() {
        return getProperty("test.validlogin.password");
    }

    public static String getInvalidLoginEmail() {
        return getProperty("test.invalidlogin.email");
    }

    public static String getInvalidLoginPassword() {
        return getProperty("test.invalidlogin.password");
    }

    public static String getSignupEmail() {
        return getProperty("test.signup.email");
    }

    public static String getSignupPassword() {
        return getProperty("test.signup.password");
    }

    public static String getSignupFullname() {
        return getProperty("test.signup.fullname");
    }

    public static String getInvalidFormatEmail() {
        return getProperty("test.invalidformat.email");
    }

    public static String getShortFullname() {
        return getProperty("test.short.fullname");
    }

    public static String getOTP() {
        return getProperty("test.otp");
    }

    public static String getPassword() {
        return getProperty("test.password");
    }

    public static String getFullName() {
        return getProperty("test.fullname");
    }

    public static String getGender() {
        return getProperty("test.gender");
    }

    public static String getDate() {
        return getProperty("test.date");
    }

    public static String getGoal() {
        return getProperty("test.goal");
    }

    public static String getLevel() {
        return getProperty("test.level");
    }

    public static String getNamePlan() {
        return getProperty("test.nameplan");
    }

    public static String getStartDate() {
        return getProperty("test.startdate");
    }

    public static String getEndDate() {
        return getProperty("test.enddate");
    }

    public static String getDescription() {
        return getProperty("test.description");
    }

    public static String getRsEmail() {
        return getProperty("test.rs.email");
    }

    public static String getDay() {
        return getProperty("test.day");
    }

    public static String getSchedule() {
        return getProperty("test.schedule");
    }

    public static String getMuscle() {
        return getProperty("test.muscle");
    }

    public static String getExLevel() {
        return getProperty("test.exlevel");
    }

    public static String getSets() {
        return getProperty("test.sets");
    }

    public static String getReps() {
        return getProperty("test.reps");
    }

    public static String getRests() {
        return getProperty("test.rests");
    }


}
