package constants;

public class WorkoutPlanConstants {
    public static final String creatPlanButton = "//button//span[contains(text(), 'Tạo kế hoạch mới')]";

    public static final String namePlanInput = "input[name='plan_name']";
    public static final String startDateInput = "input[name='start_date']";
    public static final String endDateInput = "input[name='end_date']";
    public static final String descriptionInput = "textarea[name='description']";
    public static final String createButton = "//button[@type='submit']";

    public static final String messageSuccesful = "//div[@role='status' and contains(text(), 'Đã tạo kế hoạch thành công!')]";

    public static final String detailButton = "//button[contains(text(), 'Xem chi tiết')]";
    public static final String addButton = "//span[contains(text(), 'add_circle')]";
    public static final String selectDay = "//select[@name='day_of_week']";
    public static final String nameDay = "input[name='title']";
    public static final String addScheduleBtn = "//button[@type='submit' and contains(text(), 'Thêm lịch trình')]";

    public static final String selectSchedule = "//h3[contains(text(), 'Lịch trình tuần')]/following-sibling::div[1]/div[1]";

    public static final String addExerciseBtn = "//button[contains(text(), 'Thêm bài tập')]";
    public static final String selectMuscle = "//select[@class='px-3 py-2 bg-moss-surface border border-moss-border rounded-lg text-moss-text focus:outline-none focus:ring-2 focus:ring-primary text-sm'][1]";
    public static final String selectLevel = "//select[@class='px-3 py-2 bg-moss-surface border border-moss-border rounded-lg text-moss-text focus:outline-none focus:ring-2 focus:ring-primary text-sm'][2]";
    public static final String exercise = "//h4[contains(text(), 'Bench Press')]";
    public static final String numberic = "//input[@type='number']";
    public static final String addExerciseSchedule = "//button[@type='submit' and contains(text(), 'Thêm vào lịch')]";

    public static final String messageAddExSuccessful = "//div[@role='status' and contains(text(), 'Đã thêm bài tập vào lịch thành công!')]";
}
