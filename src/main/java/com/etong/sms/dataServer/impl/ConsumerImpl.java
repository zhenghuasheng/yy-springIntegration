package com.etong.sms.dataServer.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etong.sms.dao.ConsumerMapper;
import com.etong.sms.dataServer.ConsumerServer;
import com.etong.sms.model.Consumer;
import com.etong.sms.utility.SmsError;
import com.etong.sms.utility.SmsResult;

@Service("ConsumerServer")
public class ConsumerImpl implements ConsumerServer {
	
	private ConsumerMapper consumerMapper;

	public ConsumerMapper getConsumerMapper() {
		return consumerMapper;
	}

	@Autowired
	public void setConsumerMapper(ConsumerMapper consumerMapper) {
		this.consumerMapper = consumerMapper;
	}

	@Override
	public SmsResult addConsumerRequset(Consumer consumer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SmsResult getConsumerRequest(String memberName, String memberId) {
		try {
			Map<String, Object>objMap=new HashMap<String, Object>();
			objMap.put("memberName", memberName);
			objMap.put("memberId", memberId);
			
			Consumer consumer=consumerMapper.getConsumerRequest(objMap);
			if (consumer==null) {
				return new SmsResult(SmsError.SMS_ERROR_NODATA,null,null);
			}
			return new SmsResult(SmsError.SMS_ERROR_SENDSUCC,null,consumer);
		} catch (Exception e) {
			e.printStackTrace();
			return new SmsResult(SmsError.SMS_ERROR_DB,e.getMessage(),null);
		}
	}

	@Override
	public SmsResult getConsumerBykey(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SmsResult getConsumers(int start, int count) {
		// TODO Auto-generated method stub
		return null;
	}

}
