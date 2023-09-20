package com.railway.user.reg.service.util;

public enum ResponseStatusEnum {

	SUCCESS("SUCCESS"), FAILED("FAILED"), ACCEPT("ACCEPT"), ABORT("ABORT"), PROCESSING("PROCESSING");

	private String status;

	ResponseStatusEnum(final String status) {

		this.status = status;
	}

	public String status() {

		return status;
	}
}
