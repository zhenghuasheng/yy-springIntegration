package com.etong.sms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etong.sms.dataServer.ConsumerServer;
import com.etong.sms.dataServer.MessageServer;
import com.etong.sms.model.Consumer;
import com.etong.sms.model.MessageTaskModel;
import com.etong.sms.utility.GetIpAddress;
import com.etong.sms.utility.SmsError;
import com.etong.sms.utility.SmsResult;

@Controller
public class MessageController {
	Logger logger=Logger.getLogger(MessageController.class);
	@Autowired
	private MessageServer messageServer;
	
	@Autowired
	private ConsumerServer consumerServer;
	
	@RequestMapping(value="/sms/message/send")
	@ResponseBody
	public Object smsSendRequest(String jsonString,String stime,String memberId,HttpServletRequest request){
		if (jsonString==null||jsonString.isEmpty()) {
			return new SmsResult(SmsError.SMS_ERROR_PARAMERROE,null,jsonString);
		}
		if (memberId==null||memberId.isEmpty()) {
			return new SmsResult(SmsError.SMS_ERROR_MEMBERIDERR,null,memberId);
		}
		
		String ipAddress=GetIpAddress.getIpAddr(request);
		logger.debug(ipAddress);
		
		SmsResult result=consumerServer.getConsumerRequest(null, memberId);
		if (!result.isSucceed()) {
			return new SmsResult(SmsError.SMS_ERROR_NOAUTHORITY,null,memberId);
		}
		
		Consumer consumer=(Consumer) result.getObject();
		String memberIp=consumer.getMemberIp();
		String[]ipStrings=memberIp.split(";");
		Boolean f=false;
		for (int i = 0; i < ipStrings.length; i++) {
			if(ipAddress.equals(ipStrings[i])){
				f=true;
				break;
			}
		}
		
		if (!f) {
			return new SmsResult(SmsError.SMS_ERROR_NOAUTHORITY,null,ipAddress);
		}
	
		return messageServer.commitMessage(new MessageTaskModel(jsonString, stime, memberId));//返回提交发送服务的队列结果
		
	}
	
	@ResponseBody
	@RequestMapping(value="/sms/message/list")
	public Object getMessageListRequest(String phone,String startDate,String endDate,String memberId,Integer state,
			@RequestParam(defaultValue="0",required=false)int start,@RequestParam(defaultValue="0",required=false)int count){
		SmsResult smsResult=messageServer.getMessageList(phone, startDate, endDate, memberId, state,start,count);
		return smsResult;
		
	}
}
