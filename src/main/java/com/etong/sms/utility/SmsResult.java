package com.etong.sms.utility;

public class SmsResult {
	
	 private SmsError smsError;
	 
	 private String description;
	 
	 private Object object;

	public SmsError getSmsError() {
		return smsError;
	}

	public void setSmsError(SmsError smsError) {
		this.smsError = smsError;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}
	
	public boolean isSucceed(){
		
		return this.smsError==SmsError.SMS_ERROR_SENDSUCC;
		
	}

	public SmsResult(SmsError smsError, String description, Object object) {
		super();
		this.smsError = smsError;
		this.description = description;
		this.object = object;
	}

	public SmsResult() {
		super();
	}
	 
	 

}
