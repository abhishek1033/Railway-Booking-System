package com.railway.user.reg.service.util;

public enum MessagePropertyKeyEnum {
	
	USERNAME_NULL_MESSAGE("username.null.message"), 
	PASSWORD_NULL_MESSAGE("password.null.message"), 
	PREFERRED_LANGUAGE_NULL_MESSAGE("preferred.language.null.message"),
	SECURITY_QUESTION_NULL_MESSAGE("security.question.null.message"), 
	SECURITY_ANSWER_NULL_MESSAGE("security.answer.null.message"), 
	FIRSTNAME_NULL_MESSAGE("firstname.null.message"),
	MIDDLENAME_NULL_MESSAGE("middlename.null.message"), 
	LASTNAME_NULL_MESSAGE("lastname.null.message"), 
	OCCUPATION_NULL_MESSAGE("occupation.null.message"), 
	DOB_NULL_MESSAGE("dob.null.message"),
	MARRITAL_STATUS_NULL_MESSAGE("marrital.status.null.message"), 
	COUNTRY_NULL_MESSAGE("country.null.message"), 
	GENDER_NULL_MESSAGE("gender.null.message"), 
	EMAIL_NULL_MESSAGE("email.null.message"),
	MOBILE_NO_NULL_MESSAGE("mobile.no.null.message"),
	NATIONALITY_NULL_MESSAGE("nationality.null.message"),
	ADDRESS_NULL_MESSAGE("address.null.message"), 
	STREET_NULL_MESSAGE("street.null.message"),
	AREA_NULL_MESSAGE("area.null.message"), 
	PINCODE_NULL_MESSAGE("pincode.null.message"), 
	STATE_NULL_MESSAGE("state.null.message"), 
	CITY_NULL_MESSAGE("city.null.message"), 
	COMMON_ERROR_MESSAGE("common.Error.Message"),
	COMMON_BAD_REQUEST_ERROR_MESSAGE("common.BadRequest.Error.Message"), 
	DATA_NOT_FOUND_ERROR_MESSAGE("data.not.found");
	
	MessagePropertyKeyEnum(final String key) {
		this.key = key;
	}

	private String key;

	public String getKey() {
		return key;
	}
}
