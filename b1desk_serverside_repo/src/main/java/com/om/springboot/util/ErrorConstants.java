package com.om.springboot.util;

public enum ErrorConstants {
    /* user series- 100
     * admin series- 120
     */
    E100("Server Error. Your Request cannot be processed to register user."),
    E101("Entered email is seems to be already registered. Please login."),
    E102("Server error. OTP is not generated for given email. "),
    E103("Invalid OTP. please enter a valid otp."),
    E104("Email seem to be new ."),
    E105("You are already entered pressed resend otp button two times. " +
            "Please fill once again all fields in registration page. "),
    E106("Country has no list of city."),
    E107("Country is seems to be new. "),
    E108("Invalid password is entered. "),
    E109("You are already entered resend otp button two times. Please go back to the page."),
    E110(" Demo request is not placed."),
    E111(" Selected date time slots is already booked. Please choose another date."),
    E112("Unable to get currency. Entered Country code is unvailable. "),
    E120("You are not seems to be admin. "),
    E121(" Invalid Credentials."),
    E122("There is no user available. ");

    private String value;

    private ErrorConstants(String value) {
        this.value = value;
    }
}
