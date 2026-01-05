package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import com.microsoft.playwright.options.WaitForSelectorState;
import constants.ProfileConstants;

import java.nio.file.Paths;
import java.util.Objects;

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

    public void ClickSave() {
        selectGender("Other");
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

    public boolean checkInfoAfUpdate(String fullname, String gender, String birtday, String goal, String level) {
        String fullnameTxt = page.locator(fullName).innerText().trim();
        String genderTxt = page.locator(ProfileConstants.gender).innerText().trim().toLowerCase();
        String birtdayTxt = page.locator(birthday).innerText().trim();
        String goalTxt = page.locator(ProfileConstants.goal).innerText().trim();
        String levelTxt = page.locator(ProfileConstants.level).innerText().trim();

        return Objects.equals(fullnameTxt, fullname)
                && Objects.equals(genderTxt, gender)
                && Objects.equals(birtdayTxt, birtday)
                && Objects.equals(goalTxt, goal)
                && Objects.equals(levelTxt, level);
    }

    public boolean idDisplayUpdateBirthdate(String birtday) {
        String birtdayTxt = page.locator(birthday).innerText().trim();

        return Objects.equals(birtdayTxt, birtday);
    }
}
