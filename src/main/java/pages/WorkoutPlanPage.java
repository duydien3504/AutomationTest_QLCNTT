package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import com.microsoft.playwright.options.WaitForSelectorState;

import static constants.HomepageConstants.lichtapItem;
import static constants.WorkoutPlanConstants.*;

public class WorkoutPlanPage extends BasePage{
    public WorkoutPlanPage(Page page) {
        super(page);
    }

    public void displayWkPlanPage() {
        clickElement(lichtapItem);
    }

    public void displayCreatePlanForm() {
        clickElement(creatPlanButton);
    }

    public void selectStartDate(String startDate) {
        page.locator("input[name='start_date']").fill(startDate);
    }

    public void selectEndDate(String endDate) {
        page.locator("input[name='end_date']").fill(endDate);
    }

    public void CreateWorkoutPlan(String namePlan, String startDate, String endDate, String description) {
        fillElement(namePlanInput, namePlan);
        selectStartDate(startDate);
        selectEndDate(endDate);
        fillElement(descriptionInput, description);
        clickElement(createButton);
    }

    public boolean displayMessageSuccesful() {
        try {
            page.locator(messageSuccesful).waitFor(new Locator.WaitForOptions()
                    .setState(WaitForSelectorState.VISIBLE)
                    .setTimeout(1000));
            return true;
        }catch (PlaywrightException e) {
            return false;
        }
    }
}
