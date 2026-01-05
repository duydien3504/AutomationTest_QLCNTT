package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.Objects;

import static constants.HomepageConstants.adminItem;
import static constants.MuscleConstants.*;

public class MusclePage extends BasePage{
    public MusclePage(Page page) {
        super(page);
    }

    public void displayAdminPage() {
        clickElement(adminItem);
    }

    public void displayeCreateMuscleForm() {
        clickElement(createMuscleBtn);
    }

    public void fillNameMuscle(String muscleName) {
        fillElement(muscleNameInput,muscleName);
    }

    public void fillDescription(String description) {
        fillElement(descriptionInput, description);
    }

    public void fillThumbnailUrl(String url) {
        fillElement(thumbnail, url);
    }

    public void clickCreate() {
        clickElement(createBtn);
    }

    public void createNewMuscle(String muscleName, String description, String url) {
        fillNameMuscle(muscleName);
        fillDescription(description);
        fillThumbnailUrl(url);
        clickCreate();
    }

    public boolean checkInfoMuscle(String name, String muscleName) {
        //String name = "Erector Spinae";

        Locator row = page.locator(
                "//tr[td[2]//div[normalize-space(.)='" + name + "']]"
        );
        String actualName = row.locator("td:nth-child(2) div").innerText().trim();

        return Objects.equals(actualName, muscleName);
    }
}
