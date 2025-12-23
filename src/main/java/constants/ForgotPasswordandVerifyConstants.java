package constants;

public class ForgotPasswordandVerifyConstants {
    //FGPW Form
    public static final String emailInput = "input[name='email']";
    public static final String fgPasswordButton = "//button[@type='submit']";
    public static final String titleVerifyForm = "//h2[contains(text(), 'OTP')]";

    //VerifyOTP Form
    public static final String otpCodeInput = "input[name='otp_code']";
    public static final String newPasswordInput = "input[name='new_password']";
    public static final String confirmPasswordInput = "input[name='confirmPassword']";
    public static final String resetPasswordButton = "//button[@type='submit']";
}
