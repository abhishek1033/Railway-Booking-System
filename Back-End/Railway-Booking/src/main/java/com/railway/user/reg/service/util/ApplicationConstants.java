package com.railway.user.reg.service.util;

public class ApplicationConstants {
	
	public static final String DD_MM_YYYY = "dd/MM/yyyy";
	public static final String DD_MM_YYYY_HH_MM_SS_AM_PM = "dd/MM/yyyy HH:mm:ss a";
	public static final String DD_MM_YYYY_HH_MM_SS = "dd/MM/yyyy HH:mm:ss";
	
	public static final String CONTENT_TYPE = "Content-type";
	public static final String CONTENT_TYPE_JSON = "application/json";
	public static final String TWO_DECIMAL_PATTERN = "0.00";
	public static final String UTF8 = "UTF-8";
	public static final String STATUS_LABEL = "status";
	public static final String SUCCESS_LABEL = "success";
	public static final String FAILED_LABEL = "failed";
	public static final String RESPONSESTRING_LABEL = "responseString";
	
	public static final String POST_METHOD = "POST";
	public static final String GET_METHOD = "GET";
	public static final String PUT_METHOD = "PUT";
	public static final String DELETE_METHOD = "DELETE";
	
	public static final String MESSAGES = "messages";
	public static final String BEARER = "Bearer ";
	public static final String AUTHORIZATION = "Authorization";
	public static final String UNAUTHORIZED = "Unauthorized";
	public static final String UNABLE_TO_GET_JWT_TOKEN = "Unable to get JWT Token";
	public static final String JWT_TOKEN_EXPIRED = "JWT token Expired";
	public static final String JWT_TOKEN_DOES_NOT_BEGIN_WITH_BEARER = "JWT Token Does not Begin with Bearer";
	public static final String USER_NOT_FOUND_WITH_USERNAME = "User not found with username: ";
	public static final String USER_NOT_FOUND_WITH_EMAIL = "User not found with email: ";
	public static final String DELETED = "deleted";
	public static final String INVALID_CREDENTIALS = "INVALID_CREDENTIALS";
	public static final String SOMETHING_BAD_HAPPEND_CONTACT_DEVELOPER = "SOMETHING BAD HAPPEND CONTACT DEVELOPER";
	public static final long JWT_TOKEN_VALIDITY = 60 * 60;

	public static final String ADMIN = "ADMIN";
	public static final String ACTIVE = "Active";
	public static final String INACTIVE = "InActive";
	public static final String PENDING = "Pending";
	public static final String TRUE = "True";
	public static final String FALSE = "False";
	public static final String SCHEDULAR = "Schedular";

	public static final String AUTH_URL_SKIP = "/auth/authenticate";
	public static final String SENT = "SENT";
	public static final String BODY = "Body";
	public static final String FROM = "From";
	public static final String TO = "To";
	public static final String SUBJECT = "Subject";
	public static final String AES_ECB = "AES/ECB/PKCS5PADDING";
	public static final String AES_ECB_OTHER = "AES/ECB/PKCS5Padding";
	public static final String SHA_1 = "SHA-1";
	public static final String AES = "AES";
	public static final String ACTIVE_LABEL = "Y";
	
	public static final String REGEX_FOR_DECIMALNUMBER = "^(-|[0-9]){1}([0-9])*\\.*([0-9])*$";

	public static final String REGEX_FOR_INTEGERNUMBER = "^(-|[0-9]){1}([0-9])*\\.*([0-9])*$";
	public static final String REGEX_FOR_ONLYNUMBER = "^([0-9])*$";
	public static final String REGEX_FOR_ONLYCHAR = "^([a-z]|[A-Z])*$";
	public static final String REGEX_FOR_ONLYSPACEANDCHAR = "^[a-zA-Z ]*$";

	public static final String REGEX_FOR_ALPHANUMERIC = "^[a-zA-Z0-9]*$";
	// REGEX_FOR_FULLNAME will accept dot, space and letter only
	public static final String REGEX_FOR_FULLNAME = "^(?!.*\\.\\.)(?!.*\\s\\s)[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$";

	public static final String REGEX_FOR_ONLYSPACE_ALPHA_COMMA = "^(?!.*\\s\\s)(?!.*,,)[A-Za-z]{1,}[A-Za-z\\s,]*$";
	public static final String REGEX_FOR_EMAIL = "^(([\\w-\\+]+\\.)+[\\w-\\+]+|([a-zA-Z]{1}|[\\w-\\+]{2,}))@"
			+ "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
			+ "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
			+ "([a-zA-Z0-9]+[\\w-]+\\.)+[a-zA-Z]{1}[a-zA-Z0-9-]{1,23})$";

	// REGEX_FOR_PHONENUMBER will accept + as start and 15 maximum number and 10 minimum number
	public static final String REGEX_FOR_PHONENUMBER = "^(\\+){0,1}([0-9 \\-()]){10,15}$";

	/*
	 * REGEX_FOR_WEBSITE_URL will accept these type of URL only www.test.com, http://www.test.com and https://www.test.com
	 */
	public static final String REGEX_FOR_WEBSITE_URL = "^(?!.*\\.\\.)(https?:\\/\\/)?www\\.[\\w.\\-]+(\\.[a-zA-Z]{2,3})+(\\/[\\w.?%#&=\\/\\-]*)?$";

	public static final String REGEX_FOR_ONLYSPACE_ALPHA_NUMERIC_DOT = "^[A-Za-z0-9.\\-_+\\/ ]*$";
	public static final String REGEX_FOR_ADDRESS = "^[^`~!@$%^*+=\\{}<>?]{0,64}$";
	public static final String REGEX_FOR_IP_ADDRESS = "^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$";
	public static final String REGEX_FOR_WEBSITE_DOMAIN = "^(([a-zA-Z0-9]|[a-zA-Z0-9][a-zA-Z0-9\\-]*[a-zA-Z0-9])\\.)*([A-Za-z0-9]|[A-Za-z0-9][A-Za-z0-9\\-]*[A-Za-z0-9])$";
	// REGEX FOR CONTROLLER METHOD URL
	public static final String METHOD_URL = "^(/)[a-zA-Z][A-Za-z0-9/?/&/=]+$";

	public static final String REGEX_FOR_COLONCOMMA_PARAMETER = "^([^:]+):([^,]+)+$";
	public static final String REGEX_FOR_ONLY_CAPITAL_CHAR = "^[A-Z]*$";

	public static final String REGEX_FOR_DATE = "^(\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2})$";
	public static final String REGEX_FOR_CUSTOM_FIELDS = "^[ A-Za-z0-9_@.\\/#$&%+-]{0,200}$";
	public static final String REGEX_FOR_AMOUNT = "^((\\d{1,4})(((\\.)(\\d{0,2})){0,1}))$";
	public static final String REGEX_FOR_CARDNUMBER = "^(?:4[0-9]{12}(?:[0-9]{3})?|(?:5[1-5][0-9]{2})| 222[1-9]|22[3-9][0-9]|2[3-6][0-9]{2}|27[01][0-9]|2720[0-9]{12}"
			+ "|3[47][0-9]{13}|3(?:0[0-5]|[68][0-9])[0-9]{11}|6(?:011|5[0-9]{2})[0-9]{12}|(?:2131|1800|35\\d{3})\\d{11}|([0-9 ]{19})|([0-9]{16}))$";
	public static final String REGEX_FOR_BROWSER_USER_AGENT = "^[a-zA-Z0-9 .#&+-=?_:;]*$";

	public static final String REGEX_FOR_EXPIRY_MONTH = "^(0[1-9]|[0-9]|1[0-2])$";

	public static final String REGEX_FOR_DOUBLENUMBER = "[0-9]{1,33}(\\.[0-9]*)?";
	public static final String REGEX_FOR_NAVITAIRE_FULLNAME = "^\\p{L}*[0-9\\p{L}\\s]*[\\p{L}0-9]$";

	public static final String REGEX_FOR_NAVITAIRE_EMAIL = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}$";
	// REGEX_FOR_CARDHOLDERPHONE will accept - and space including number and maximum length is 10
	public static final String REGEX_FOR_CARDHOLDERPHONE = "^[\\d -]{0,10}$";

	// REGEX FOR NON US NUMBER
	public static final String REGEX_FOR_CONTACTNUMBER = "^[+]?[1-9][0-9]{11}$";// "^(?:(?:\\+|0{0,2})91(\\s*[\\-]\\s*)?|[0]?)?[0-9]\\d{9}$";
	// public static Predicate<String> REGEX_FOR_CONTACTNUMBER = i -> i.matches("^(?:(?:\\+|0{0,2})91(\\s*[\\-]\\s*)?|[0]?)?[0-9]\\d{9}$");
	// REGEX FOR STRONG PASSWORD (INCLUDES MINIMUM 1 SMALL, 1 CAPITAL , 1 NUMBER AND 1 SPECIAL CHARACTER with MINIMUM length 8 )
	public static final String REGEX_FOR_STRONGPASSOWORD = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";

	// REGEX FOR VALID COMPANY NAME
	public static final String REGEX_FOR_VALIDCOMPANYNAME = "^(?![0-9]*$)[a-zA-Z0-9\\s]+$";

	public static final String REGEX_FOR_ONLYSPACE_CHAR_SPECIALCHARCTERS = "^[a-zA-Z .,\\-]*$";
	public static final String REGEX_FOR_ONLYSPACE_ALPHANUMERIC_SOME_SPECIALCHARCTERS = "^[A-Za-z0-9.\\-_+\\/ ]*$";
	public static final String REGEX_FOR_ALPHA_SPACE_UNDERSCORE = "^[0-9a-zA-Z _]*$";
	public static final String REGEX_FOR_ALPHA_SPACE_DOT = "^[a-zA-Z .]*$";
	public static final String REGEX_FOR_ALL_SPECIALCHARACTERS_ALPHANUMERIC_SPACE = "^[A-Za-z0-9_@.\\/#$&%+\\-!^*()=;:',?\\Â©|\\[{}\\]\\`~\\>< ]*$";
	public static final String REGEX_FOR_NAVIATIRE_ONLYNUMBER = "^(\\d)*$";
	public static final String REGEX_FOR_ALPHANUMERIC_SPACE_UNDERSCORE = "^[a-zA-Z0-9\\-_ ]*$";
	public static final String REGEX_FOR_NUMBER_SOME_SPECIALCHARACTERS = "^[0-9\\-,]*$";
	public static final String REGEX_FOR_ALPHANUMERIC_LINE_BREAK = "^[a-zA-Z0-9,.\\r\\n]*$";
	public static final String REGEX_FOR_URL = "(https?:\\/\\/(?:www\\.|(?!www))[a-zA-Z0-9][a-zA-Z0-9-]*\\.[^\\s]{2,}|www\\.[a-zA-Z0-9][a-zA-Z0-9-]*\\.[^\\s]{2,}|https?:\\/\\/(?:www\\.|(?!www))[a-zA-Z0-9]\\.[^\\s]{2,}|www\\.[a-zA-Z0-9]\\.[^\\s]{2,})";
	public static final String REGEX_FOR_NAVITAIRE_CITY = "^[a-zA-Z .,\\-+]*$";
	public static final String REGEX_FOR_PASS_WORD = "^.{8,128}$";
	public static final String REGEX_FOR_ONLY_NUMBER_HYPHEN = "^[\\d -]*$";
	public static final String REGEX_FOR_SEMICOLON_SEPARATED_VALUES = "^[\\u0400-\\u04FFa-zA-Z ]+(;[\\u0400-\\u04FFa-zA-Z ]+)*$";

	public static final String REGEX_NOT_ACCEPT_ZERO_AT_FIRSTPLACE = "^\\s*(?=.*[1-9])\\d{1,4}(?:\\.\\d{1,2})?\\s*$";

	public static final String REGEX_FOR_ACCEPT_IMAGE_TYPE = "([^\\s]+(\\.(?i)(jpg|jpeg|png))$)";
	public static final String REGEX_FOR_VALIDPOSTALCODE = "(^[0-9]{5}(?:-[0-9]{4})?$)";

	public static final String REGEX_FOR_ZIPCODE = "^([A-Za-z0-9]){2,10}$";
}
