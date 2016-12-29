package ru.grinick.common;

public class RestResponse {

	private int success;
	private String message;
	private String uuid;
	
	public RestResponse() {
		super();
	}
	public int getSuccess() {
		return success;
	}
	public void setSuccess(int success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}	
}
