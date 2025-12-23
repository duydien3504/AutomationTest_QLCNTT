package constants;

public class WorkoutPlanConstants {
    public static final String creatPlanButton = "//button//span[contains(text(), 'Tạo kế hoạch mới')]";

    public static final String namePlanInput = "input[name='plan_name']";
    public static final String startDateInput = "input[name='start_date']";
    public static final String endDateInput = "input[name='end_date']";
    public static final String descriptionInput = "textarea[name='description']";
    public static final String createButton = "//button[@type='submit']";

    public static final String messageSuccesful = "//div[@role='status' and contains(text(), 'Đã tạo kế hoạch thành công!')]";
}
