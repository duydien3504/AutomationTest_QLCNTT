package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import com.microsoft.playwright.options.WaitForSelectorState;
import constants.WorkoutPlanConstants;

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

    public void AddExercise(String day, String nameDay, String muscle, String level, String sets, String reps, String rests) {
        clickElement(detailButton);
        clickElement(addButton);

        page.locator("//select[@name='day_of_week']").selectOption(day);

        fillElement(WorkoutPlanConstants.nameDay, nameDay);
        clickElement(addScheduleBtn);
        page.waitForTimeout(1000);

        clickElement(selectSchedule);

        clickElement(addExerciseBtn);

        String Muscle = selectMuscle;
        page.locator(Muscle).selectOption(muscle);
        String Level = selectLevel;
        page.locator(Level).selectOption(level);

        clickElement(exercise);

        Locator inputs = page.locator("//input[@type='number']");
        inputs.nth(0).fill(sets);
        inputs.nth(1).fill(reps);
        inputs.nth(2).fill(rests);

        clickElement(addExerciseSchedule);
    }

    public boolean isDisplayMessageaddSuccessful() {
        try{
            page.locator(messageAddExSuccessful)
                    .waitFor(new Locator.WaitForOptions()
                            .setState(WaitForSelectorState.VISIBLE)
                            .setTimeout(1000));
            return true;
        }catch (PlaywrightException e) {
            return false;
        }
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
