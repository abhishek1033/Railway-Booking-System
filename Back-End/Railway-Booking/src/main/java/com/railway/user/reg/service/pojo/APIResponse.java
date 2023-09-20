package com.railway.user.reg.service.pojo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class APIResponse {
	
	private String status;

	private Set<String> errorMessage;

	private Map<String, Object> responseData;

	public APIResponse() {

		errorMessage = new HashSet<>();
		responseData = new HashMap<>();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<String> getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(Set<String> errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Map<String, Object> getResponseData() {
		return responseData;
	}

	public void setResponseData(Map<String, Object> responseData) {
		this.responseData = responseData;
	}
	
	
}
