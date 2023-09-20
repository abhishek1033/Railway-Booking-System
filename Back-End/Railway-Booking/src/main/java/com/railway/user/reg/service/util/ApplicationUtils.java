package com.railway.user.reg.service.util;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.FileNameMap;
import java.net.URI;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;

import lombok.extern.slf4j.Slf4j;

@Component
public class ApplicationUtils {

	private static SecretKeySpec secretKey;
	private static byte[] key;

	/**
	 * The method <code>isValidDate</code> checks whether given field has valid date
	 * or not.
	 *
	 * @param dateToValidate
	 * @param dateFromat
	 * @return
	 */
	public boolean isValidDate(final String dateToValidate, final String dateFromat) {

		if (dateToValidate == null) {
			return false;
		}

		final SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
		sdf.setLenient(false);

		try {

			// if not valid, it will throw ParseException
			final Date date = sdf.parse(dateToValidate);
		} catch (final ParseException e) {
			return false;
		}

		return true;
	}

	/**
	 * The method <code>isValidDate</code> is check whether given field is valid
	 * date or not.
	 *
	 * @param dateToValidate
	 * @return
	 */
	public boolean isValidDate(final String dateToValidate) {

		if (dateToValidate == null) {
			return false;
		}

		final SimpleDateFormat sdf = new SimpleDateFormat(ApplicationConstants.DD_MM_YYYY);
		sdf.setLenient(false);

		try {
			// if not valid, it will throw ParseException
			sdf.parse(dateToValidate);
		} catch (final ParseException e) {
			return false;
		}
		return true;
	}

	/**
	 * The method <code>isFutureDate</code> is check whether given date is future
	 * date or not.
	 *
	 * @param date
	 * @return
	 *
	 */
	public boolean isFutureDate(final String date) {

		// today date
		final Date current = new Date();

		// create date object
		Date next;

		try {
			next = new SimpleDateFormat(ApplicationConstants.DD_MM_YYYY).parse(date);
			if (next.after(current)) {
				return true;
			}
		} catch (final ParseException e) {
		}
		// compare both dates
		return false;
	}

	/**
	 * The method <code>isPastDate</code> is check whether given date is past date
	 * or not.
	 *
	 * @param date
	 * @return
	 */
	public boolean isPastDate(final String date) {

		// today date
		final Date currentDate = new Date();
		// create date object
		Date beforeDate;

		try {
			beforeDate = new SimpleDateFormat(ApplicationConstants.DD_MM_YYYY).parse(date);
			if (beforeDate.before(currentDate)) {
				return true;
			}
		} catch (final ParseException e) {
		}
		// compare both dates
		return false;

	}

	/**
	 * The method <code>isNumeric</code> checks whether given value is numeric or
	 * not.
	 *
	 * @param str
	 * @return
	 */
	public boolean isNumeric(final String str) {

		try {
			Double.parseDouble(str);
		} catch (final NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	/**
	 * This method <code>isNotEmpty</code> is used for check the String whether is
	 * not empty or not null.
	 *
	 * @param s
	 * @return
	 */
	public boolean isNotEmpty(final String s) {
		return s != null && !s.isEmpty();
	}

	/**
	 * This method <code>isValid</code> is used for check the Collection whether is
	 * not empty or not null.
	 *
	 * @param collection
	 * @return
	 */
	public boolean isValid(final Collection<?> collection) {
		return collection != null && !collection.isEmpty();
	}

	/**
	 * This method <code>isValid</code> is used for check the Map whether is not
	 * empty or not null.
	 *
	 * @param collection
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public boolean isValid(final Map map) {
		return map != null && !map.isEmpty();
	}

	/**
	 * This method <code>toDate</code> is used for convert String Date to Date
	 * Object format
	 *
	 * @param strDate
	 * @param format
	 * @return
	 */
	public Date toDate(final String strDate, final String format) {

		final SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		Date date = null;

		try {

			date = dateFormat.parse(strDate);

		} catch (final Exception e) {
			e.printStackTrace();
		}

		return date;
	}

	/**
	 * This method <code>dateToString</code> is used for convert the Date object to
	 * String Date
	 *
	 * @param date
	 * @param format
	 * @return
	 */
	public String dateToString(final Date date, final String format) {

		String stringDate = null;

		try {

			final SimpleDateFormat sdf = new SimpleDateFormat(format);
			stringDate = sdf.format(date);

		} catch (final Exception e) {
			e.printStackTrace();
		}

		return stringDate;
	}

	/**
	 * This method <code>currentDate</code> is used for get current date in string
	 * format
	 *
	 * @return
	 */
	public String getCurrentDate() {

		return getDateTimeFormat(ApplicationConstants.DD_MM_YYYY_HH_MM_SS_AM_PM).format(new Date());
	}

	/**
	 * This method <code>getCurrentTimeStamp</code> is used for get current time
	 * stamp in string.
	 *
	 * @return
	 */
	public String getCurrentTimeStamp() {

		return getDateTimeFormat(ApplicationConstants.DD_MM_YYYY_HH_MM_SS).format(new Date());
	}

	/**
	 * This method <code>currentDate</code> is used for get current time stamp base
	 * on give format in string.
	 *
	 * @param dateFormat
	 * @return
	 */
	public String getCurrentTimeStamp(final String dateFormat) {

		final SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		return format.format(new Date());
	}

	/**
	 * This method <code>generateJSONFromObject</code> is responsible to generate
	 * JSON from a given object.
	 *
	 * @param object
	 * @return
	 */
	public String generateJSONFromObject(final Object object) {

		final ObjectMapper objectMapper = new ObjectMapper();

		String jsonString = null;

		try {
			objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

			/**
			 * Store JSON in to jsonString
			 */
			jsonString = objectMapper.writeValueAsString(object);

		} catch (final JsonGenerationException e) {
			e.printStackTrace();
		} catch (final JsonMappingException e) {
			e.printStackTrace();
		} catch (final IOException e) {
			e.printStackTrace();
		}

		return jsonString;
	}

	/**
	 * This method <code>generateObjectFromJSON</code> is responsible to generate
	 * object from a given JSON.
	 *
	 * @param jsonString
	 * @param clazz
	 * @param isList
	 * @return
	 */
	public <T> T generateObjectFromJSON(final String jsonString, final Class<T> clazz, final boolean isList) {

		T object = null;

		if (!isEmpty(jsonString)) {

			try {

				final ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				if (isList) {

					object = objectMapper.readValue(jsonString,
							TypeFactory.defaultInstance().constructCollectionType(List.class, clazz));
				} else {
					object = clazz.getDeclaredConstructor().newInstance();
					object = objectMapper.readValue(jsonString, clazz);
				}

			} catch (final JsonParseException e) {
				e.printStackTrace();
			} catch (final JsonMappingException e) {
				e.printStackTrace();
			} catch (final IOException e) {
				e.printStackTrace();
			} catch (final Exception e) {
				e.printStackTrace();
			}
		}

		return object;
	}

	public <T> List<T> generateObjectListFromJSON(final String jsonString, final Class<T> clazz) {

		List<T> objects = null;

		if (!isEmpty(jsonString)) {

			try {

				final ObjectMapper objectMapper = new ObjectMapper();
				objects = objectMapper.readValue(jsonString,
						TypeFactory.defaultInstance().constructCollectionType(ArrayList.class, clazz));

			} catch (final JsonParseException e) {
				e.printStackTrace();
			} catch (final JsonMappingException e) {
				e.printStackTrace();
			} catch (final IOException e) {
				e.printStackTrace();
			} catch (final Exception e) {
				e.printStackTrace();
			}
		}

		return objects;
	}

	/**
	 * This <code>isEmpty</code> method is responsible to check whether the String
	 * value passed is empty or not.
	 *
	 * @param param
	 * @return
	 */
	public boolean isEmpty(final String param) {

		final boolean error = false;

		if (param == null || param.trim().length() <= 0) {
			return true;
		}
		return error;
	}

	/**
	 * This <code>isEmpty</code> method is responsible to check whether the Long
	 * value passed is empty or not.
	 *
	 * @param param
	 * @return
	 */
	public boolean isEmpty(final Long param) {

		final boolean error = false;

		if (param == null || param <= 0L) {
			return true;
		}
		return error;
	}

	/**
	 * This <code>isEmpty</code> method is responsible to check whether the Integer
	 * value passed is empty or not.
	 *
	 * @param param
	 * @return
	 */
	public boolean isEmpty(final Integer param) {

		final boolean error = false;

		if (param == null || param <= 0) {
			return true;
		}
		return error;
	}

	/**
	 * This <code>isEmpty</code> method is responsible to check whether the Double
	 * value passed is empty or not.
	 *
	 * @param param
	 * @return
	 */
	public boolean isEmpty(final Double param) {

		final boolean error = false;

		if (param == null || param <= 0) {
			return true;
		}
		return error;
	}

	/**
	 * This <code>isEmpty</code> method is responsible to check whether the String
	 * Array passed is empty or not.
	 *
	 * @param param
	 * @return
	 */
	public boolean isEmpty(final String[] param) {

		final boolean error = false;

		if (param == null || param.length == 0) {
			return true;
		}
		return error;
	}

	/**
	 * This <code>isDecimalNumber</code> method is responsible to check whether
	 * given value valid decimal or double number
	 *
	 * @param value
	 * @return
	 */
	public Boolean isDecimalNumber(final String value) {

		return validateValueForRegex(ApplicationConstants.REGEX_FOR_DECIMALNUMBER, value);
	}

	/**
	 * This <code>isIntegerNumber</code> method is responsible to check whether
	 * given value valid integer number.
	 *
	 * @param value
	 * @return
	 */
	public Boolean isIntegerNumber(final String value) {

		return validateValueForRegex(ApplicationConstants.REGEX_FOR_INTEGERNUMBER, value);
	}

	/**
	 * This <code>isOnlyNumber</code> method is responsible to check whether given
	 * value valid digit.
	 *
	 * @param value
	 * @return
	 */
	public Boolean isOnlyNumber(final String value) {

		return validateValueForRegex(ApplicationConstants.REGEX_FOR_ONLYNUMBER, value);
	}

	/**
	 * This <code>isOnlyAlphabet</code> method is responsible to check whether given
	 * value valid alphabet.
	 *
	 * @param value
	 * @return
	 */
	public Boolean isOnlyAlphabet(final String value) {

		return validateValueForRegex(ApplicationConstants.REGEX_FOR_ONLYCHAR, value);
	}

	/**
	 * This <code>isFullName</code> method is responsible to check whether given
	 * value valid full name.
	 *
	 * @param value
	 * @return
	 */
	public Boolean isFullName(final String value) {

		return validateValueForRegex(ApplicationConstants.REGEX_FOR_FULLNAME, value);
	}

	/**
	 * This <code>isEmail</code> method is responsible to check whether given value
	 * valid email.
	 *
	 * @param value
	 * @return
	 */
	public Boolean isEmail(final String value) {

		return validateValueForRegex(ApplicationConstants.REGEX_FOR_EMAIL, value);
	}

	/**
	 * This <code>isPhoneNumber</code> method is responsible to check whether given
	 * value valid phone number.
	 *
	 * @param value
	 * @return
	 */
	public Boolean isPhoneNumber(final String value) {

		return validateValueForRegex(ApplicationConstants.REGEX_FOR_PHONENUMBER, value);
	}

	/**
	 * This <code>isAlphaNumeric</code> method is responsible to check whether given
	 * value valid alphabet numeric.
	 *
	 * @param value
	 * @return
	 */
	public Boolean isAlphaNumeric(final String value) {

		return validateValueForRegex(ApplicationConstants.REGEX_FOR_ALPHANUMERIC, value);
	}

	public Boolean isValidPostalCode(final String value) {

		return validateValueForRegex(ApplicationConstants.REGEX_FOR_VALIDPOSTALCODE, value);
	}

	/**
	 * This <code>isAlphaNumericSpaceAndDot</code> method is responsible to check
	 * whether given value contains only alphabet, space, numeric and dot.
	 *
	 * @param value
	 * @return
	 */
	public Boolean isAlphaNumericSpaceAndDot(final String value) {

		return validateValueForRegex(ApplicationConstants.REGEX_FOR_ONLYSPACE_ALPHA_NUMERIC_DOT, value);
	}

	/**
	 * This <code>isAlphaCommaAndSpace</code> method is responsible to check whether
	 * given value contains only alphabet, space and comma.
	 *
	 * @param value
	 * @return
	 */
	public Boolean isAlphaCommaAndSpace(final String value) {

		return validateValueForRegex(ApplicationConstants.REGEX_FOR_ONLYSPACE_ALPHA_COMMA, value);
	}

	/**
	 * This <code>isOnlyAlphaAndSpace</code> method is responsible to check whether
	 * given value valid alphabet and white space.
	 *
	 * @param value
	 * @return
	 */
	public Boolean isOnlyAlphaAndSpace(final String value) {

		return validateValueForRegex(ApplicationConstants.REGEX_FOR_ONLYSPACEANDCHAR, value.trim());
	}

	/**
	 * This <code>isValidAmount</code> method is responsible to check whether the
	 * given value contains a valid value for Amount.
	 *
	 * @param value
	 * @return
	 */
	public Boolean isValidAmount(final String value) {

		return validateValueForRegex(ApplicationConstants.REGEX_FOR_AMOUNT, value);
	}

	/**
	 * This <code>isValidCurrency</code> method is responsible to check whether the
	 * given value contains a valid value for Currency.
	 *
	 * @param value
	 * @return
	 */
	public Boolean isCapitalAlpha(final String value) {

		return validateValueForRegex(ApplicationConstants.REGEX_FOR_ONLY_CAPITAL_CHAR, value);
	}

	/**
	 * This <code>isValidCardNumber</code> method is responsible to check whether
	 * the given value contains a valid value for Card Number.
	 *
	 * @param value
	 * @return
	 */
	public Boolean isValidCardNumber(final String value) {

		return validateValueForRegex(ApplicationConstants.REGEX_FOR_CARDNUMBER, value);
	}

	/**
	 * This <code>isValidBrowserUserAgent</code> method is responsible to check
	 * whether the given value contains a valid value for browserUserAgent.
	 *
	 * @param value
	 * @return
	 */
	public Boolean isValidBrowserUserAgent(final String value) {

		return validateValueForRegex(ApplicationConstants.REGEX_FOR_BROWSER_USER_AGENT, value);
	}

	/**
	 * This <code>isOnlyDate</code> method is responsible to check whether the given
	 * value contains a valid value for date.
	 *
	 * @param value
	 * @return
	 */
	public Boolean isOnlyDate(final String value) {

		return validateValueForRegex(ApplicationConstants.REGEX_FOR_DATE, value);
	}

	/**
	 * This <code>isValidCustomField</code> method is responsible to check whether
	 * the given value contains a valid value for custom fields.
	 *
	 * @param value
	 * @return
	 */
	public Boolean isValidCustomField(final String value) {

		return validateValueForRegex(ApplicationConstants.REGEX_FOR_CUSTOM_FIELDS, value);
	}

	/**
	 * This <code>isValidPassengerName</code> method is responsible to check whether
	 * the given value contains a valid value for passenger name.
	 *
	 * @param value
	 * @return
	 */
	public Boolean isValidPassengerName(final String value) {

		return validateValueForRegex(ApplicationConstants.REGEX_FOR_NAVITAIRE_FULLNAME, value);
	}

	/**
	 * This <code>isValidExpiryMonth</code> method is responsible to check whether
	 * the given value contains a valid value for expiry month.
	 *
	 * @param value
	 * @return
	 */
	public Boolean isValidExpiryMonth(final String value) {

		return validateValueForRegex(ApplicationConstants.REGEX_FOR_EXPIRY_MONTH, value);
	}

	/**
	 * This <code>isDoubleNumber</code> method is responsible to check whether the
	 * given value contains a valid value for exchange rate.
	 *
	 * @param value
	 * @return
	 */
	public Boolean isDoubleNumber(final String value) {

		return validateValueForRegex(ApplicationConstants.REGEX_FOR_DOUBLENUMBER, value);
	}

	/**
	 * This <code>isValidIpAddress</code> method is responsible to check whether the
	 * given value contains a valid value for IP Address.
	 *
	 * @param value
	 * @return
	 */
	public Boolean isValidIpAddress(final String value) {

		return validateValueForRegex(ApplicationConstants.REGEX_FOR_IP_ADDRESS, value.trim());
	}

	/**
	 * This <code>isValidAddress</code> method is responsible to check whether the
	 * given value contains a valid value for address.
	 *
	 * @param value
	 * @return
	 */
	public Boolean isValidAddress(final String value) {

		return validateValueForRegex(ApplicationConstants.REGEX_FOR_ADDRESS, value);
	}

	/**
	 * This <code>isValidEmail</code> method is responsible to check whether the
	 * given value contains a valid value for email.
	 *
	 * @param value
	 * @return
	 */
	public Boolean isValidEmailNavitaire(final String value) {

		return validateValueForRegex(ApplicationConstants.REGEX_FOR_NAVITAIRE_EMAIL, value);
	}

	/**
	 * This <code>isValidPhoneNumber</code> method is responsible to check whether
	 * the given value contains a valid value for Card-Holder Phone.
	 *
	 * @param value
	 * @return
	 */
	public Boolean isValidPhoneNumber(final String value) {

		return validateValueForRegex(ApplicationConstants.REGEX_FOR_CARDHOLDERPHONE, value.trim());
	}

	/**
	 * This <code>isValidContactNumber</code> method is responsible to check whether
	 * the given value contains a valid value for user contact number.
	 *
	 * @param value
	 * @return
	 */

	public boolean isValidContactNumber(final String value) {

		return validateValueForRegex(ApplicationConstants.REGEX_FOR_CONTACTNUMBER, value.trim());

	}

	/**
	 * This <code>isValidStrongPassword</code> method is responsible to check
	 * whether the given password is strong or not.
	 *
	 * @param value
	 * @return
	 */

	public boolean isValidStrongPassword(final String value) {

		return validateValueForRegex(ApplicationConstants.REGEX_FOR_STRONGPASSOWORD, value);
	}

	/**
	 * This <code>isValidCompanyName</code> method is responsible to check whether
	 * the given company name is valid or not.
	 *
	 * @param value
	 * @return
	 */
	public boolean isValidCompanyName(final String value) {
		return validateValueForRegex(ApplicationConstants.REGEX_FOR_VALIDCOMPANYNAME, value.trim());
	}

	/**
	 * This method <code>isSemicolonSeparaterCharacter</code> is used for validate
	 * the semicolon separated character or not.
	 *
	 * @param value
	 * @return
	 */
	public Boolean isSemicolonSeparaterCharacter(final String value) {
		return validateValueForRegex(ApplicationConstants.REGEX_FOR_SEMICOLON_SEPARATED_VALUES, value.trim());
	}

	/**
	 * This <code>isDoubleOnly</code> method is responsible to check whether given
	 * value consists 0 at first place.
	 *
	 * @param value
	 * @return
	 */
	public Boolean isDoubleOnly(final String value) {
		return validateValueForRegex(ApplicationConstants.REGEX_NOT_ACCEPT_ZERO_AT_FIRSTPLACE, value);
	}

	/**
	 * This <code>isValidImageType</code> is used for validate the image type like,
	 * jpg,png and jpeg format
	 *
	 * @param value
	 * @return
	 */
	public Boolean isValidImageType(final String value) {
		return validateValueForRegex(ApplicationConstants.REGEX_FOR_ACCEPT_IMAGE_TYPE, value);
	}

	/**
	 * This <code>validateValueForRegex</code> method is responsible to validate the
	 * given value with the given regex.
	 *
	 * @param regex
	 * @param value
	 * @return
	 */
	public Boolean validateValueForRegex(final String regex, final String value) {
		return Pattern.compile(regex).matcher(value).matches();
	}

	/**
	 * The <code></code> method is used for call the get request and get the
	 * response.
	 *
	 * @param url
	 * @param headers
	 * @param parameters
	 * @return
	 */
	public HttpResponse getRequest(final String url, final Map<String, String> headers) {

		HttpResponse httpResponse = null;

		try {

			final HttpGet httpGet = new HttpGet(url);

			if (headers != null && !headers.isEmpty()) {

				for (final Map.Entry<String, String> entry : headers.entrySet()) {
					httpGet.addHeader(entry.getKey(), entry.getValue());
				}
			}

			httpResponse = HttpClientBuilder.create().build().execute(httpGet);

		} catch (final IOException e) {
			e.printStackTrace();
		}

		return httpResponse;
	}

	/**
	 * This method <code>getCommonElements</code> is used for get the common element
	 * from list of collection.
	 *
	 * @param collections
	 * @return
	 */
	public <T> Set<T> getCommonElements(final Collection<? extends Collection<T>> collections) {

		final Set<T> common = new LinkedHashSet<>();

		if (!collections.isEmpty()) {
			final Iterator<? extends Collection<T>> iterator = collections.iterator();
			common.addAll(iterator.next());
			while (iterator.hasNext()) {
				common.retainAll(iterator.next());
			}
		}

		return common;
	}

	/**
	 * This <code>isOnlyAlphaSpaceAndSpecialCharacter</code> method is responsible
	 * to check whether given value valid alphabet and white space and some special
	 * characters.
	 *
	 * @param value
	 * @return
	 */
	public Boolean isOnlyAlphaSpaceAndSpecialCharacter(final String value) {

		return validateValueForRegex(ApplicationConstants.REGEX_FOR_ONLYSPACE_CHAR_SPECIALCHARCTERS, value.trim());
	}

	/**
	 * This <code>isOnlyAlphaNumericSpaceAndSomeSpecialCharacters</code> method is
	 * responsible to check whether given value valid alphanumeric and white space
	 * and some special characters.
	 *
	 * @param value
	 * @return
	 */
	public Boolean isOnlyAlphaNumericSpaceAndSomeSpecialCharacters(final String value) {

		return validateValueForRegex(ApplicationConstants.REGEX_FOR_ONLYSPACE_ALPHANUMERIC_SOME_SPECIALCHARCTERS,
				value.trim());
	}

	/**
	 * This <code>isOnlyAlphaSpaceAndUnderScore</code> method is responsible to
	 * check whether given value valid alphabet and white space and under score.
	 *
	 * @param value
	 * @return
	 */
	public Boolean isOnlyAlphaSpaceAndUnderScore(final String value) {

		return validateValueForRegex(ApplicationConstants.REGEX_FOR_ALPHA_SPACE_UNDERSCORE, value.trim());
	}

	/**
	 * This <code>isOnlyAllSpecialCharactersSpaceAndAlphaNumeric</code> method is
	 * responsible to check whether given value valid alphanumeric and all special
	 * characters.
	 *
	 * @param value
	 * @return
	 */
	public Boolean isOnlyAllSpecialCharactersSpaceAndAlphaNumeric(final String value) {

		return validateValueForRegex(ApplicationConstants.REGEX_FOR_ALL_SPECIALCHARACTERS_ALPHANUMERIC_SPACE,
				value.trim());
	}

	/**
	 * This <code>isOnlyNaviatireNumber</code> method is responsible to check
	 * whether given value valid number or not.
	 *
	 * @param value
	 * @return
	 */
	public Boolean isOnlyNaviatireNumber(final String value) {

		return validateValueForRegex(ApplicationConstants.REGEX_FOR_NAVIATIRE_ONLYNUMBER, value.trim());
	}

	/**
	 * This <code>isOnlyAlphaNumericSpaceAndUnderScore</code> method is responsible
	 * to check whether given value valid alphanumeric and white space and
	 * underscore.
	 *
	 * @param value
	 * @return
	 */
	public Boolean isOnlyAlphaNumericSpaceAndUnderScore(final String value) {

		return validateValueForRegex(ApplicationConstants.REGEX_FOR_ALPHANUMERIC_SPACE_UNDERSCORE, value.trim());
	}

	/**
	 * This <code>isOnlyNumberAndSomeSpecialCharacters</code> method is responsible
	 * to check whether given value valid number and some special characters.
	 *
	 * @param value
	 * @return
	 */
	public Boolean isOnlyNumberAndSomeSpecialCharacters(final String value) {

		return validateValueForRegex(ApplicationConstants.REGEX_FOR_NUMBER_SOME_SPECIALCHARACTERS, value.trim());
	}

	/**
	 * This <code>isOnlyAlphaNumericAndLineBreak</code> method is responsible to
	 * check whether given value valid alphanumeric and line break(new Line).
	 *
	 * @param value
	 * @return
	 */
	public Boolean isOnlyAlphaNumericAndLineBreak(final String value) {

		return validateValueForRegex(ApplicationConstants.REGEX_FOR_ALPHANUMERIC_LINE_BREAK, value.trim());
	}

	/**
	 * This <code>isValidUrl</code> method is responsible to check whether given
	 * value valid url.
	 *
	 * @param value
	 * @return
	 */
	public Boolean isValidUrl(final String value) {

		return validateValueForRegex(ApplicationConstants.REGEX_FOR_URL, value.trim());
	}

	/**
	 * This <code>isOnlyAlphaSpaceAndDot</code> method is responsible to check
	 * whether given value valid alphabet and white space and Dot.
	 *
	 * @param value
	 * @return
	 */
	public Boolean isOnlyAlphaSpaceAndDot(final String value) {

		return validateValueForRegex(ApplicationConstants.REGEX_FOR_ALPHA_SPACE_DOT, value.trim());
	}

	/**
	 * This <code>isValidCity</code> method is responsible to check whether given
	 * value valid alphabet and white space and some special characters.
	 *
	 * @param value
	 * @return
	 */
	public Boolean isValidCity(final String value) {

		return validateValueForRegex(ApplicationConstants.REGEX_FOR_NAVITAIRE_CITY, value.trim());
	}

	/**
	 * This <code>isValidPassword</code> method is responsible to check whether
	 * given password is proper or not
	 *
	 * @param value
	 * @return
	 */
	public Boolean isValidPassword(final String value) {

		return validateValueForRegex(ApplicationConstants.REGEX_FOR_PASS_WORD, value.trim());
	}

	/**
	 * This <code>isOnlyNumberAndHyphen</code> method is responsible to check
	 * whether given value is valid or not.
	 *
	 * @param value
	 * @return
	 */
	public Boolean isOnlyNumberAndHyphen(final String value) {

		return validateValueForRegex(ApplicationConstants.REGEX_FOR_ONLY_NUMBER_HYPHEN, value.trim());
	}

	/**
	 * This method <code>convertObjectToXML</code> is responsible to generate XML
	 * from a given object.
	 *
	 * @param object
	 * @return
	 */
	public String convertObjectToXML(final Object object) {

		try {

			final StringWriter stringWriter = new StringWriter();
			final JAXBContext context = JAXBContext.newInstance(object.getClass());
			final Marshaller marshaller = context.createMarshaller();

			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.marshal(object, stringWriter);

			return stringWriter.toString();

		} catch (final JAXBException e) {

			e.printStackTrace();
		}

		return null;
	}

	/**
	 * This method <code>isValidLong</code> is responsible to check whether provided
	 * value is valid Long value or not.
	 *
	 * @param value
	 * @return
	 */
	public boolean isValidLong(final String value) {

		try {
			Long.parseLong(value);
			return true;
		} catch (final Exception e) {
			return false;
		}

	}

	/**
	 * This method <code>isValidDouble</code> is responsible to check whether
	 * provided value is valid Double value or not.
	 *
	 * @param value
	 * @return
	 */
	public boolean isValidDouble(final String value) {

		try {
			Double.parseDouble(value);
			return true;
		} catch (final Exception e) {
			return false;
		}

	}

	/**
	 * The <code>getContextURL</code> is used to retrieve application full context
	 * path based on request object.
	 *
	 * @param request
	 * @return
	 */
	public String getContextURL(final HttpServletRequest request) {

		final String scheme = request.getScheme(); // http
		final String serverName = request.getServerName(); // hostname.com
		final int serverPort = request.getServerPort(); // 80
		final String contextPath = request.getContextPath(); // /mywebapp

		// Reconstruct original requesting URL
		final StringBuilder url = new StringBuilder();
		url.append(scheme).append("://").append(serverName);

		if (serverPort != 80 && serverPort != 443) {
			url.append(":").append(serverPort);
		}

		url.append(contextPath);

		return url.toString();
	}

	/**
	 * The <code>sendJsonResponse</code> method used to send JSON response.
	 *
	 * @param jsonDataAsString
	 * @param response
	 */
	public void sendJsonResponse(final String jsonDataAsString, final HttpServletResponse response) {

		try {

			response.setContentType(ApplicationConstants.CONTENT_TYPE_JSON);
			response.getOutputStream().write(jsonDataAsString.getBytes());

		} catch (final IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * This method <Code>convertToTwoDecimal</Code> is used for populate the Amount
	 * upto two decimal.
	 *
	 * @param amount
	 * @return
	 */
	public String convertToTwoDecimal(final String amount) {

		if (!isEmpty(amount)) {

			final double doubleAmount = Double.parseDouble(amount);

			final DecimalFormat df = new DecimalFormat(ApplicationConstants.TWO_DECIMAL_PATTERN);
			final String formattedValue = df.format(doubleAmount);

			return formattedValue;
		}
		return null;

	}

	/**
	 * This <code>putHTTPRequest</code> is used for put request.
	 *
	 * @param url
	 * @param headers
	 * @param jsonDataAsString
	 * @return
	 */
	public HttpResponse putHTTPRequest(final String url, final Map<String, String> headers,
			final String jsonDataAsString) {

		HttpResponse httpResponse = null;

		try {

			final HttpPut httpPut = new HttpPut(url);

			if (headers != null && !headers.isEmpty()) {

				for (final Map.Entry<String, String> entry : headers.entrySet()) {
					httpPut.addHeader(entry.getKey(), entry.getValue());
				}
			}

			// Posting JSON Data as String to the Payments URL
			httpPut.setEntity(new StringEntity(jsonDataAsString));

			final HttpClient httpclient = HttpClientBuilder.create().setRedirectStrategy(new LaxRedirectStrategy())
					.build();
			httpResponse = httpclient.execute(httpPut);

		} catch (final Exception e) {
			e.printStackTrace();
		}

		return httpResponse;
	}

	/**
	 * This <code>addDaysInCurrentDate</code> method is used to add given days into
	 * current date.
	 *
	 * @param days
	 * @return
	 */
	public Date addDaysInCurrentDate(final Integer days) {

		return Date.from(LocalDate.now().plusDays(days).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

	}

	/**
	 * This <code>getResponseStringFromHttpResponse</code> method is used for get
	 * response string from httpResponse object.
	 *
	 * @param httpResponse
	 * @return
	 */
	public String getResponseStringFromHttpResponse(final HttpResponse httpResponse) throws IOException {

		if (httpResponse != null) {

			final HttpEntity entity = httpResponse.getEntity();
			try {
				return EntityUtils.toString(entity, ApplicationConstants.UTF8);
			} catch (final IOException e) {
				e.printStackTrace();
				throw new UnknownError(e.getMessage());
			}
		}
		return "";
	}

	/**
	 *
	 * This <code>getKeyValueFromJsonObject</code> method is used for find json
	 * value by its key .
	 *
	 * @param jsonObject
	 * @param findKey
	 * @return
	 * @throws JSONException
	 */
	public static synchronized String getKeyValueFromJsonObject(final JSONObject jsonObject, final String findKey)
			throws JSONException {

		String finalValue = "";

		if (jsonObject == null) {
			return "";
		}

		final Iterator<String> jsonObjectIterator = jsonObject.keys();
		final Map<String, Object> map = new HashMap<>();

		while (jsonObjectIterator.hasNext()) {

			final String key = jsonObjectIterator.next();
			map.put(key, jsonObject.get(key));
		}

		for (final Map.Entry<String, Object> entry : map.entrySet()) {

			final String key = entry.getKey();
			if (key.equalsIgnoreCase(findKey)) {
				return jsonObject.getString(findKey);
			}

			// read value
			final Object value = jsonObject.get(key);

			if (value instanceof JSONObject) {
				finalValue = getKeyValueFromJsonObject((JSONObject) value, findKey);
			}
		}
		return finalValue;
	}

	/**
	 * This method <code>currentDate</code> is used for get current date in string
	 * format
	 *
	 * @return
	 * @throws ParseException
	 */
	public Date getCurrentTimeStampDate() {

		Date date = null;
		final String currentDateTime = getDateTimeFormat(ApplicationConstants.DD_MM_YYYY_HH_MM_SS_AM_PM)
				.format(new Date());

		try {

			final DateFormat dateFormat = new SimpleDateFormat(ApplicationConstants.DD_MM_YYYY_HH_MM_SS_AM_PM);
			date = dateFormat.parse(currentDateTime);

		} catch (final Exception e) {
			e.printStackTrace();
		}

		return date;
	}

	/**
	 * This <code>isValidZipCode</code> method is responsible to check whether given
	 * value is valid zip code or not.
	 *
	 * @param value
	 * @return
	 */
	public Boolean isValidZipCode(final String value) {

		return validateValueForRegex(ApplicationConstants.REGEX_FOR_ZIPCODE, value);
	}

	/**
	 * This method is used to get DateFormat instance
	 *
	 * @param dateFormat
	 * @return
	 */
	public final DateFormat getDateTimeFormat(final String dateFormat) {

		return new SimpleDateFormat(dateFormat);
	}

	/**
	 * This method <code>sendJSONRequest</code> is used for sending the JSON Request
	 * via HTTP POST Call or GET Call.
	 *
	 * @param url
	 * @param requestHeader
	 * @param jsonServiceRequestData
	 * @param methodType
	 * @param supportJson
	 * @param nonJsonMap
	 * @param files
	 * @return
	 * @throws IOException
	 */
	public Map<String, Object> sendJSONRequest(final String url, final Map<String, String> requestHeader,
			final String jsonServiceRequestData, final String methodType, final String supportJson,
			final Map<String, String> nonJsonMap, final Map<String, File> files) {

		HttpResponse httpResponse = null;
		String responseString = null;
		final Map<String, Object> responseMap = new HashMap<>();
		HttpUriRequest httpRequest = null;
		try {

			if (!requestHeader.isEmpty()) {
				httpRequest = checkForRequestType(url, jsonServiceRequestData, methodType);

				if (supportJson.equalsIgnoreCase(ApplicationConstants.ACTIVE_LABEL)) {
					httpResponse = processJsonRequest(requestHeader, httpRequest);
				} else {

					httpResponse = processNonJsonRequest(requestHeader, methodType, nonJsonMap, files, httpRequest);
				}

				responseString = getResponseStringFromHttpResponse(httpResponse);
			}

		} catch (final Exception e) {

			e.printStackTrace();

		} finally {
			if (httpRequest != null) {
				if (httpResponse != null && httpResponse.getStatusLine().getStatusCode() == 200) {
					responseMap.put(ApplicationConstants.STATUS_LABEL, ApplicationConstants.SUCCESS_LABEL);
					responseMap.put(ApplicationConstants.RESPONSESTRING_LABEL, responseString);
				}
				((HttpRequestBase) httpRequest).releaseConnection();
			}
		}

		return responseMap;
	}

	/**
	 * This method is used to create HttpUriRequest based on HTTP request type.
	 *
	 * @param url
	 * @param jsonServiceRequestData
	 * @param methodType
	 * @param httpRequest
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws UnknownError
	 */
	private HttpUriRequest checkForRequestType(final String url, final String jsonServiceRequestData,
			final String methodType) throws UnsupportedEncodingException, UnknownError {

		HttpUriRequest httpRequest = null;
		if (ApplicationConstants.POST_METHOD.equalsIgnoreCase(methodType)) {
			httpRequest = new HttpPost(url);
			((HttpPost) httpRequest).setEntity(new StringEntity(jsonServiceRequestData));
		} else if (ApplicationConstants.GET_METHOD.equalsIgnoreCase(methodType)) {
			httpRequest = new HttpGet(url);
		} else if (ApplicationConstants.PUT_METHOD.equalsIgnoreCase(methodType)) {
			httpRequest = new HttpPut(url);
			((HttpPut) httpRequest).setEntity(new StringEntity(jsonServiceRequestData));
		} else if (ApplicationConstants.DELETE_METHOD.equalsIgnoreCase(methodType)) {
			httpRequest = new HttpDelete(url);
		} else {
			throw new UnknownError("There is no implementation for requested methodType : " + methodType);
		}
		return httpRequest;
	}

	/**
	 * This method is used to process JSON request.
	 *
	 * @param requestHeader
	 * @param httpRequest
	 * @return
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	private HttpResponse processJsonRequest(final Map<String, String> requestHeader, final HttpUriRequest httpRequest)
			throws IOException {

		HttpResponse httpResponse;
		for (final Map.Entry<String, String> entry : requestHeader.entrySet()) {
			httpRequest.addHeader(entry.getKey(), entry.getValue());
		}
		httpResponse = HttpClientBuilder.create().setRedirectStrategy(new LaxRedirectStrategy()).build()
				.execute(httpRequest);
		return httpResponse;
	}

	/**
	 * This method is used to process non JSON request such as MultiPart files.
	 *
	 * @param requestHeader
	 * @param methodType
	 * @param nonJsonMap
	 * @param files
	 * @param httpRequest
	 * @return
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	private HttpResponse processNonJsonRequest(final Map<String, String> requestHeader, final String methodType,
			final Map<String, String> nonJsonMap, final Map<String, File> files, final HttpUriRequest httpRequest)
			throws IOException {

		HttpResponse httpResponse;
		requestHeader.remove(ApplicationConstants.CONTENT_TYPE);

		for (final Map.Entry<String, String> entry : requestHeader.entrySet()) {
			httpRequest.addHeader(entry.getKey(), entry.getValue());
		}

		final MultipartEntityBuilder multiPartEntity = MultipartEntityBuilder.create();
		multiPartEntity.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

		if (files != null && !files.isEmpty()) {
			for (final Map.Entry<String, File> entry : files.entrySet()) {
				final FileNameMap fileNameMap = URLConnection.getFileNameMap();

				multiPartEntity.addBinaryBody(entry.getKey(), entry.getValue(),
						ContentType.create(fileNameMap.getContentTypeFor(entry.getValue().getName())),
						entry.getValue().getName());
			}
		}

		if (nonJsonMap != null && !nonJsonMap.isEmpty()) {
			for (final Map.Entry<String, String> entry : nonJsonMap.entrySet()) {
				multiPartEntity.addTextBody(entry.getKey(), entry.getValue(), ContentType.APPLICATION_FORM_URLENCODED);

			}
		}

		if (ApplicationConstants.POST_METHOD.equalsIgnoreCase(methodType)) {
			((HttpPost) httpRequest).setEntity(multiPartEntity.build());
		}

		httpResponse = HttpClientBuilder.create().build().execute(httpRequest);

		return httpResponse;
	}

	/**
	 * It will check the String is JSON or not.
	 *
	 * @param test
	 * @return
	 */
	public boolean isJSONValid(final String test) {

		try {
			new JSONObject(test);
		} catch (final JSONException ex) {
			try {
				new JSONArray(test);
			} catch (final JSONException ex1) {
				return false;
			}
		}
		return true;
	}

	/**
	 * The <code>getWebUrl</code> is used to retrieve application web url based on
	 * request object.
	 *
	 * @param request
	 * @return
	 */
	public String getWebUrl(final HttpServletRequest request) {

		final StringBuffer url = request.getRequestURL();
		final String uri = request.getRequestURI();
		final String base = url.substring(0, url.length() - uri.length()) + "/";
		return base.toString();
	}

	/**
	 * The <code>processNonJsonUrlEncodedRequest</code> is used to process non-json
	 * xwwwformurlencodedRequest
	 *
	 * @param requestData
	 * @param url
	 * @param requestHeader
	 * @return
	 */
	public HttpResponse processNonJsonUrlEncodedRequest(final Map<String, String> requestData, final String url,
			final Map<String, String> requestHeader) {

		HttpResponse httpResponse = null;
		final HttpClient client;
		try {

			client = HttpClients.createDefault();

			final HttpUriRequest httppost = RequestBuilder.post().setUri(new URI(url))
					.addHeader(ApplicationConstants.CONTENT_TYPE, requestHeader.get(ApplicationConstants.CONTENT_TYPE))
					.addHeader(ApplicationConstants.AUTHORIZATION,
							requestHeader.get(ApplicationConstants.AUTHORIZATION))
					.addParameter(ApplicationConstants.BODY, requestData.get(ApplicationConstants.BODY))
					.addParameter(ApplicationConstants.FROM, requestData.get(ApplicationConstants.FROM))
					.addParameter(ApplicationConstants.TO, requestData.get(ApplicationConstants.TO)).build();

			httpResponse = client.execute(httppost);

		} catch (final Exception e) {
			e.printStackTrace();
		}
		return httpResponse;

	}

	public String decrypt(final String strToDecrypt, final String secret) {

		try {
			setKey(secret);
			Cipher cipher = Cipher.getInstance(ApplicationConstants.AES_ECB);
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String encrypt(final String strToEncrypt, final String secret) {

		try {
			setKey(secret);
			Cipher cipher = Cipher.getInstance(ApplicationConstants.AES_ECB_OTHER);
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes(ApplicationConstants.UTF8)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void setKey(final String myKey) {

		MessageDigest sha = null;
		try {
			key = myKey.getBytes(ApplicationConstants.UTF8);
			sha = MessageDigest.getInstance(ApplicationConstants.SHA_1);
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16);
			secretKey = new SecretKeySpec(key, ApplicationConstants.AES);
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
