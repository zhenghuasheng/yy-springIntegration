package com.etong.sms.utility;

public enum SmsError {
	
	SMS_ERROR_MEMBERUNEXIT("001","memberId未注册"),
	SMS_ERROR_IPERROR("002","IP错误"),
	SMS_ERROR_PARAMERROE("003","参数错误"),
	SMS_ERROR_SENDSUCC("004","发送成功"),
	SMS_ERROR_SENDFAIL("005","发送失败"),
	SMS_ERROR_SUBMIT("006","提交失败"),
	SMS_ERROR_DB("007","数据库执行错误"),
	SMS_ERROR_NODATA("008","无数据"),
	SMS_ERROR_MEMBERIDERR("009","memberId错误或者为空"),
	SMS_ERROR_NOAUTHORITY("010","无权限操作");
	
	
	
	private String code;
	private String message;
	
	
	
	
	
	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	private SmsError(String code, String message) {
		this.code = code;
		this.message = message;
	}


	private SmsError() {
	}


}
