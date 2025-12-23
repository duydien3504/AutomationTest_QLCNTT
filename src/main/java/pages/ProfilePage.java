package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import com.microsoft.playwright.options.WaitForSelectorState;

import java.nio.file.Paths;

import static constants.HomepageConstants.userAvatar;
import static constants.ProfileConstants.*;

public class ProfilePage extends BasePage{
    public ProfilePage(Page page) {
        super(page);
    }

    public void displayProfilePage() {
        clickElement(userAvatar);
    }

    public void displayEditProfileForm() {
        clickElement(editProfileButton);
    }

    public void uploadAvatar(String filePath) {
        page.locator("input[type='file']").setInputFiles(Paths.get(filePath));
    }

    public void selectGender(String gender) {
        page.locator("select[name='gender']").selectOption(gender);
    }

    public void selectBirtDate(String date) {
        page.locator("input[type='date']").fill(date);
    }

    public void EditProfile(String filePath, String fullname, String gender, String date, String goal, String level) {
        uploadAvatar(filePath);
        fillElement(fullnameInput,fullname);
        selectGender(gender);
        selectBirtDate(date);
        fillElement(goalInput, goal);
        fillElement(levelInput, level);
        clickElement(saveButton);
    }

    public boolean isDisplayMessageSuccess() {
        try {
            page.locator(messageSuccessfull)
                    .waitFor(new Locator.WaitForOptions()
                            .setState(WaitForSelectorState.VISIBLE)
                            .setTimeout(1000));
            return true;
        }catch (PlaywrightException e) {
            return false;
        }
    }
}
