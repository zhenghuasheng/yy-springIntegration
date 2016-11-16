package com.etong.sms.dataServer;


import com.etong.sms.model.Message;
import com.etong.sms.model.MessageTaskModel;
import com.etong.sms.utility.SmsResult;

public interface MessageServer {
	
	public SmsResult addMessageRequest(Message message);
	
	/**Map<String, Object>map传入数据层**/
	public SmsResult getMessageList(String phone,String startDate,String endDate,String memberId,Integer state,int start,int count);
	
	public SmsResult deleteMessage(Long id);
	
	public SmsResult sendMessages(String jsonString,String stime,String memberId);
	
	public SmsResult commitMessage(MessageTaskModel record);
	
	

}
