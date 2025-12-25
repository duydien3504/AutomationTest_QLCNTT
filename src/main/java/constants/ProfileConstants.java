package constants;

public class ProfileConstants {
    public static final String editProfileButton = "//button[contains(text(), 'Edit')]";

    public static final String buttonChooseIMG = "input[type='file']";
    public static final String fullnameInput = "input[name='full_name']";
    public static final String dropdownGender = "select[name='gender']";
    public static final String birthDate = "input[type='date']";
    public static final String goalInput = "input[name='fitness_goal']";
    public static final String levelInput = "input[name='activity_level']";
    public static final String saveButton = "//button[@type='submit']";
    public static final String messageSuccessfull = "//div[@role='status' and contains(text(), 'Cập nhật')]";


    //Check
    public static final String fullName = "//label[contains(text(),'Full Name')]/following-sibling::div";
    public static final String gender = "//label[contains(text(),'Gender')]/following-sibling::div";
    public static final String birthday = "//label[contains(text(),'Date of Birth')]/following-sibling::div";
    public static final String goal = "//label[contains(text(),'Fitness Goal')]/following-sibling::div";
    public static final String level = "//label[contains(text(),'Activity Level')]/following-sibling::div";
}
