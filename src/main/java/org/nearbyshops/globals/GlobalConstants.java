package org.nearbyshops.globals;

/**
 * Created by sumeet on 22/3/17.
 */
public class GlobalConstants {
    private GlobalConstants() {
    }

    public static String BASE_URI = "http://0.0.0.0:5500";

	public static String POSTGRES_CONNECTION_URL;

	public static String POSTGRES_USERNAME;

	public static String POSTGRES_PASSWORD;

	public static String ADMIN_USERNAME;

	public static String ADMIN_PASSWORD;

    public static String SMTP_SERVER_URL;

    public static int SMTP_PORT = 587;

    public static String SMTP_USERNAME;

    public static String SMTP_PASSWORD;

    public static String EMAIL_SENDER_NAME;

    public static String EMAIL_ADDRESS_FOR_SENDER;

    public static String ONE_SIGNAL_API_KEY_ADMIN_APP;

    public static String ONE_SIGNAL_APP_ID_ADMIN_APP;

    public static String ONE_SIGNAL_API_KEY_SHOP_OWNER_APP;

    public static String ONE_SIGNAL_APP_ID_SHOP_OWNER_APP;

    public static String ONE_SIGNAL_API_KEY_END_USER_APP;

    public static String ONE_SIGNAL_APP_ID_END_USER_APP;

    public static String style_url_for_maps;

    public static String faqs_url_for_end_user;

    public static String tos_url_for_end_user;

    public static String privacy_policy_url_for_end_user;

    public static String faqs_url_for_shop_owner;

    public static String tos_url_for_shop_owner;

    public static String privacy_policy_url_for_shop_owner;

    public static String MSG91_SMS_SERVICE_API_KEY = null;

    public static String default_country_code_value;

    public static String sender_id_for_sms_value;

    public static String service_name_for_sms_value;

    public static boolean enable_login_using_otp_value;

    public static String[] trusted_market_aggregators_value;

    public static String url_for_notification_icon_value;

    public static int delivery_range_for_shop_max_value;

    public static int delivery_range_for_shop_min_value;

    public static int app_service_charge_pick_for_shop_value;

    public static int app_service_charge_home_delivery_value;

    public static int min_account_balance_for_shop;

    public static int REFERRAL_CREDIT_FOR_SHOP_OWNER_REGISTRATION;

    public static int REFERRAL_CREDIT_FOR_END_USER_REGISTRATION;

    public static int joining_credit_for_end_user_value;

    public static int joining_credit_for_shop_owner_value;

    public static int TOKEN_DURATION_MINUTES;

    public static int EMAIL_VERIFICATION_CODE_EXPIRY_MINUTES;

    public static int PHONE_OTP_EXPIRY_MINUTES;

    public static int PASSWORD_RESET_CODE_EXPIRY_MINUTES;

    public static int max_limit;

    public static final int ROLE_ADMIN_CODE = 1;

    public static final int ROLE_STAFF_CODE = 2;

    public static final int ROLE_DELIVERY_GUY_CODE = 3;

    public static final int ROLE_SHOP_ADMIN_CODE = 4;

    public static final int ROLE_SHOP_STAFF_CODE = 5;

    public static final int ROLE_DELIVERY_GUY_SELF_CODE = 6;

    public static final int ROLE_END_USER_CODE = 7;

    public static final String ROLE_ADMIN = "ADMIN";

    public static final String ROLE_STAFF = "STAFF";

    public static final String ROLE_DELIVERY_GUY = "ROLE_DELIVERY_GUY";

    public static final String ROLE_SHOP_ADMIN = "ROLE_SHOP_ADMIN";

    public static final String ROLE_SHOP_STAFF = "SHOP_STAFF";

    public static final String ROLE_DELIVERY_GUY_SELF = "ROLE_DELIVERY_GUY_SELF";

    public static final String ROLE_END_USER = "END_USER";
}
