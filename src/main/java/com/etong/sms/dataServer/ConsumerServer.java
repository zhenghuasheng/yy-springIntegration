package com.etong.sms.dataServer;

import java.util.List;
import java.util.Map;

import com.etong.sms.model.Consumer;
import com.etong.sms.utility.SmsResult;

public interface ConsumerServer {
	
	public SmsResult addConsumerRequset(Consumer consumer);
	
	public SmsResult getConsumerRequest(String memberName,String memberId);
	
	public SmsResult getConsumerBykey(int id);
	
	public SmsResult getConsumers(int start,int count);

}
