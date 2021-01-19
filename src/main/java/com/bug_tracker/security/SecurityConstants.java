package com.bug_tracker.security;

public class SecurityConstants {

    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer ";
    public static final String ID = "id";
    public static final String ROLE = "role";

    public static final String LOGIN_URL = "/login";
    public static final String EMAIL_CONFIRM_URL = "/users/registration/confirm_email";
    public static final String TOKEN_PARAM = "token";
    public static final String VERIFIC_MAIL_SUBJECT = "Registration Confirmation";
    public static final long VERIFICATION_DURATION =24;

    public static final String DEFAULT_ROLE = "ROLE_GUEST";


    //Temporary for testing
    public static final String BASE_URL = "http://localhost:8080";

    private SecurityConstants() {
    }
}
