package com.railway.user.reg.service.controler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.railway.user.reg.service.entity.UserDetails;
import com.railway.user.reg.service.pojo.APIResponse;
import com.railway.user.reg.service.pojo.UrlConstants;
import com.railway.user.reg.service.util.MessagePropertyKeyEnum;
import com.railway.user.reg.service.util.ResponseStatusEnum;

@RestController
@RequestMapping("/user/reg/api")
public class UserDetailsController extends BaseController {
	
	@PostMapping(value = UrlConstants.SAVE_USER)
	public ResponseEntity<?> saveUser(@RequestBody final UserDetails userDto) {

		final APIResponse response = new APIResponse();
		ResponseEntity<APIResponse> responseEntity = null;
		final Map<String, Object> map = new HashMap<>();

		try {

			if (null != userDto) {

				final List<String> errors = validateUserRequest.apply(userDto, "Y");
				if (!applicationUtils.isValid(errors)) {

					final UserDetails userResponseDto = serviceRegistry.getUserDetailsService().addNewUser(userDto);

					if (null != userResponseDto && null != userResponseDto.getUserId()) {
						map.put("UserDetails", userResponseDto);
						response.setStatus(ResponseStatusEnum.SUCCESS.status());
						response.setResponseData(map);
						responseEntity = new ResponseEntity<>(response, null, HttpStatus.OK);
					} else {
						response.setStatus(ResponseStatusEnum.FAILED.status());
						response.setErrorMessage(new LinkedHashSet<>(Arrays.asList(messageSource
								.getMessage(MessagePropertyKeyEnum.COMMON_ERROR_MESSAGE.getKey(), null, null))));

						responseEntity = new ResponseEntity<>(response, null, HttpStatus.INTERNAL_SERVER_ERROR);
					}

				} else {
					response.setStatus(ResponseStatusEnum.FAILED.status());
					response.setErrorMessage(new LinkedHashSet<>(errors));
					responseEntity = new ResponseEntity<>(response, null, HttpStatus.BAD_REQUEST);
				}
			} else {
				response.setStatus(ResponseStatusEnum.FAILED.status());
				response.setErrorMessage(new LinkedHashSet<>(Arrays.asList(messageSource
						.getMessage(MessagePropertyKeyEnum.COMMON_BAD_REQUEST_ERROR_MESSAGE.getKey(), null, null))));

				responseEntity = new ResponseEntity<>(response, null, HttpStatus.INTERNAL_SERVER_ERROR);
			}

		} catch (final Exception exception) {
			response.setStatus(ResponseStatusEnum.FAILED.status());
			response.setErrorMessage(new LinkedHashSet<>(Arrays.asList(
					messageSource.getMessage(MessagePropertyKeyEnum.COMMON_ERROR_MESSAGE.getKey(), null, null))));
			responseEntity = new ResponseEntity<>(response, null, HttpStatus.BAD_REQUEST);
		}
		return responseEntity;

	}
	
	
	private final Function<UserDetails, List<String>> validateAllUserDetails = userRequest -> {

		final List<String> errors = new ArrayList<>();

		if (applicationUtils.isEmpty(userRequest.getUserName())) {
			errors.add(messageSource.getMessage(MessagePropertyKeyEnum.USERNAME_NULL_MESSAGE.getKey(), null, Locale.getDefault()));
		}
		if (applicationUtils.isEmpty(userRequest.getPassword())) {
			errors.add(messageSource.getMessage(MessagePropertyKeyEnum.PASSWORD_NULL_MESSAGE.getKey(), null, Locale.getDefault()));
		}
		if (applicationUtils.isEmpty(userRequest.getPreferredLanguage())) {
			errors.add(messageSource.getMessage(MessagePropertyKeyEnum.PREFERRED_LANGUAGE_NULL_MESSAGE.getKey(), null, Locale.getDefault()));
		}
		if (applicationUtils.isEmpty(userRequest.getSecurityQuestion())) {
			errors.add(messageSource.getMessage(MessagePropertyKeyEnum.SECURITY_QUESTION_NULL_MESSAGE.getKey(), null, Locale.getDefault()));
		}
		if (applicationUtils.isEmpty(userRequest.getSecurityAnswer())) {
			errors.add(messageSource.getMessage(MessagePropertyKeyEnum.SECURITY_ANSWER_NULL_MESSAGE.getKey(), null, Locale.getDefault()));
		}
		if (applicationUtils.isEmpty(userRequest.getFirstName())) {
			errors.add(messageSource.getMessage(MessagePropertyKeyEnum.FIRSTNAME_NULL_MESSAGE.getKey(), null, Locale.getDefault()));
		}
		if (applicationUtils.isEmpty(userRequest.getMiddleName())) {
			errors.add(messageSource.getMessage(MessagePropertyKeyEnum.MIDDLENAME_NULL_MESSAGE.getKey(), null, Locale.getDefault()));
		}
		if (applicationUtils.isEmpty(userRequest.getLastName())) {
			errors.add(messageSource.getMessage(MessagePropertyKeyEnum.LASTNAME_NULL_MESSAGE.getKey(), null, Locale.getDefault()));
		}
		if (applicationUtils.isEmpty(userRequest.getOccupation())) {
			errors.add(messageSource.getMessage(MessagePropertyKeyEnum.OCCUPATION_NULL_MESSAGE.getKey(), null, Locale.getDefault()));
		}
		if (applicationUtils.isEmpty(userRequest.getDob())) {
			errors.add(messageSource.getMessage(MessagePropertyKeyEnum.DOB_NULL_MESSAGE.getKey(), null, Locale.getDefault()));
		}
		if (applicationUtils.isEmpty(userRequest.getMarritalStatus())) {
			errors.add(messageSource.getMessage(MessagePropertyKeyEnum.MARRITAL_STATUS_NULL_MESSAGE.getKey(), null, Locale.getDefault()));
		}
		if (applicationUtils.isEmpty(userRequest.getCountry())) {
			errors.add(messageSource.getMessage(MessagePropertyKeyEnum.COUNTRY_NULL_MESSAGE.getKey(), null, Locale.getDefault()));
		}
		if (applicationUtils.isEmpty(userRequest.getGender())) {
			errors.add(messageSource.getMessage(MessagePropertyKeyEnum.GENDER_NULL_MESSAGE.getKey(), null, Locale.getDefault()));
		}
		if (applicationUtils.isEmpty(userRequest.getEmailId())) {
			errors.add(messageSource.getMessage(MessagePropertyKeyEnum.EMAIL_NULL_MESSAGE.getKey(), null, Locale.getDefault()));
		}
		if (applicationUtils.isEmpty(userRequest.getMobileNo())) {
			errors.add(messageSource.getMessage(MessagePropertyKeyEnum.MOBILE_NO_NULL_MESSAGE.getKey(), null, Locale.getDefault()));
		}
		if (applicationUtils.isEmpty(userRequest.getNationality())) {
			errors.add(messageSource.getMessage(MessagePropertyKeyEnum.NATIONALITY_NULL_MESSAGE.getKey(), null, Locale.getDefault()));
		}
		if (applicationUtils.isEmpty(userRequest.getAddressNo())) {
			errors.add(messageSource.getMessage(MessagePropertyKeyEnum.ADDRESS_NULL_MESSAGE.getKey(), null, Locale.getDefault()));
		}
		if (applicationUtils.isEmpty(userRequest.getStreet())) {
			errors.add(messageSource.getMessage(MessagePropertyKeyEnum.STREET_NULL_MESSAGE.getKey(), null, Locale.getDefault()));
		}
		if (applicationUtils.isEmpty(userRequest.getArea())) {
			errors.add(messageSource.getMessage(MessagePropertyKeyEnum.AREA_NULL_MESSAGE.getKey(), null, Locale.getDefault()));
		}
		if (applicationUtils.isEmpty(userRequest.getPincode())) {
			errors.add(messageSource.getMessage(MessagePropertyKeyEnum.PINCODE_NULL_MESSAGE.getKey(), null, Locale.getDefault()));
		}
		if (applicationUtils.isEmpty(userRequest.getState())) {
			errors.add(messageSource.getMessage(MessagePropertyKeyEnum.STATE_NULL_MESSAGE.getKey(), null, Locale.getDefault()));
		}
		if (applicationUtils.isEmpty(userRequest.getCity())) {
			errors.add(messageSource.getMessage(MessagePropertyKeyEnum.CITY_NULL_MESSAGE.getKey(), null, Locale.getDefault()));
		}

		return errors;
	};
	
	
	private final BiFunction<UserDetails, String, List<String>> validateUserRequest = (userRequest, flag) -> {

		final List<String> errors = new ArrayList<>();

		errors.addAll(validateAllUserDetails.apply(userRequest));

		return errors;
	};
	
	
	@GetMapping(value = UrlConstants.USER_DETAILS_BY_Id)
	public ResponseEntity<APIResponse> getUserDetailsByUserId(@PathVariable(name = "userId") String userId) {

		final UserDetails userDetailsResponse;
		final APIResponse response = new APIResponse();
		ResponseEntity<APIResponse> responseEntity = null;
		final Map<String, Object> map = new HashMap<>();

		try {

			if (null != userId) {

				userDetailsResponse = serviceRegistry.getUserDetailsService().getUserDetailsByUserId(userId);

				if (null != userDetailsResponse && null != userDetailsResponse.getUserId()) {
					map.put("UserDetails", userDetailsResponse);
					response.setStatus(ResponseStatusEnum.SUCCESS.status());
					response.setResponseData(map);
					responseEntity = new ResponseEntity<>(response, null, HttpStatus.OK);
				} else {
					response.setStatus(ResponseStatusEnum.FAILED.status());
					response.setErrorMessage(new LinkedHashSet<>(Arrays.asList(messageSource
							.getMessage(MessagePropertyKeyEnum.DATA_NOT_FOUND_ERROR_MESSAGE.getKey(), null, null))));
					responseEntity = new ResponseEntity<>(response, null, HttpStatus.INTERNAL_SERVER_ERROR);
				}
			} else {
				response.setStatus(ResponseStatusEnum.FAILED.status());
				response.setErrorMessage(new LinkedHashSet<>(Arrays.asList(messageSource
						.getMessage(MessagePropertyKeyEnum.COMMON_BAD_REQUEST_ERROR_MESSAGE.getKey(), null, null))));
				responseEntity = new ResponseEntity<>(response, null, HttpStatus.INTERNAL_SERVER_ERROR);
			}

		} catch (final Exception exception) {

			response.setStatus(ResponseStatusEnum.FAILED.status());
			response.setErrorMessage(new LinkedHashSet<>(Arrays.asList(
					messageSource.getMessage(MessagePropertyKeyEnum.COMMON_ERROR_MESSAGE.getKey(), null, null))));
			responseEntity = new ResponseEntity<>(response, null, HttpStatus.BAD_REQUEST);
		}
		return responseEntity;

	}
	
	
	@GetMapping(value = UrlConstants.USER_DETAILS)
	public ResponseEntity<APIResponse> getAllUserDetails() {

		final List<UserDetails> userDetailsResponse;
		final APIResponse response = new APIResponse();
		ResponseEntity<APIResponse> responseEntity = null;
		final Map<String, Object> map = new HashMap<>();

		try {

				userDetailsResponse = serviceRegistry.getUserDetailsService().getAllUserDetails();

				if (null != userDetailsResponse &&  userDetailsResponse.size() > 0) {
					map.put("UserDetails", userDetailsResponse);
					response.setStatus(ResponseStatusEnum.SUCCESS.status());
					response.setResponseData(map);
					responseEntity = new ResponseEntity<>(response, null, HttpStatus.OK);
				} else {
					response.setStatus(ResponseStatusEnum.FAILED.status());
					response.setErrorMessage(new LinkedHashSet<>(Arrays.asList(messageSource
							.getMessage(MessagePropertyKeyEnum.DATA_NOT_FOUND_ERROR_MESSAGE.getKey(), null, null))));
					responseEntity = new ResponseEntity<>(response, null, HttpStatus.INTERNAL_SERVER_ERROR);
				}

		} catch (final Exception exception) {

			response.setStatus(ResponseStatusEnum.FAILED.status());
			response.setErrorMessage(new LinkedHashSet<>(Arrays.asList(
					messageSource.getMessage(MessagePropertyKeyEnum.COMMON_ERROR_MESSAGE.getKey(), null, null))));
			responseEntity = new ResponseEntity<>(response, null, HttpStatus.BAD_REQUEST);
		}
		return responseEntity;

	}
	
	
	@PostMapping(value = UrlConstants.UPDATE_USER)
	public ResponseEntity<?> updateUser(@RequestBody final UserDetails userDto) {

		final APIResponse response = new APIResponse();
		final UserDetails userDetailsForUpdate;
		ResponseEntity<APIResponse> responseEntity = null;
		final Map<String, Object> map = new HashMap<>();

		try {

			if (null != userDto && null != userDto.getUserId()) {

				final List<String> errors = validateUserRequest.apply(userDto, "Y");
				if (!applicationUtils.isValid(errors)) {
					
					userDetailsForUpdate = serviceRegistry.getUserDetailsService().getUserDetailsByUserId(userDto.getUserId());
					
					if (null != userDetailsForUpdate && null != userDetailsForUpdate.getUserId()) {
						
						final UserDetails userResponseDto = serviceRegistry.getUserDetailsService().updateUser(userDetailsForUpdate,userDto);

						if (null != userResponseDto && null != userResponseDto.getUserId()) {
							map.put("UserDetails", userResponseDto);
							response.setStatus(ResponseStatusEnum.SUCCESS.status());
							response.setResponseData(map);
							responseEntity = new ResponseEntity<>(response, null, HttpStatus.OK);
						} else {
							response.setStatus(ResponseStatusEnum.FAILED.status());
							response.setErrorMessage(new LinkedHashSet<>(Arrays.asList(messageSource
									.getMessage(MessagePropertyKeyEnum.COMMON_ERROR_MESSAGE.getKey(), null, null))));

							responseEntity = new ResponseEntity<>(response, null, HttpStatus.INTERNAL_SERVER_ERROR);
						}
						
					} else {
						response.setStatus(ResponseStatusEnum.FAILED.status());
						response.setErrorMessage(new LinkedHashSet<>(Arrays.asList(messageSource
								.getMessage(MessagePropertyKeyEnum.DATA_NOT_FOUND_ERROR_MESSAGE.getKey(), null, null))));
						responseEntity = new ResponseEntity<>(response, null, HttpStatus.INTERNAL_SERVER_ERROR);
					}
					
				} else {
					response.setStatus(ResponseStatusEnum.FAILED.status());
					response.setErrorMessage(new LinkedHashSet<>(errors));
					responseEntity = new ResponseEntity<>(response, null, HttpStatus.BAD_REQUEST);
				}
			} else {
				response.setStatus(ResponseStatusEnum.FAILED.status());
				response.setErrorMessage(new LinkedHashSet<>(Arrays.asList(messageSource
						.getMessage(MessagePropertyKeyEnum.COMMON_BAD_REQUEST_ERROR_MESSAGE.getKey(), null, null))));

				responseEntity = new ResponseEntity<>(response, null, HttpStatus.INTERNAL_SERVER_ERROR);
			}

		} catch (final Exception exception) {
			response.setStatus(ResponseStatusEnum.FAILED.status());
			response.setErrorMessage(new LinkedHashSet<>(Arrays.asList(
					messageSource.getMessage(MessagePropertyKeyEnum.COMMON_ERROR_MESSAGE.getKey(), null, null))));
			responseEntity = new ResponseEntity<>(response, null, HttpStatus.BAD_REQUEST);
		}
		return responseEntity;

	}
}
