package com.etong.sms.dao;

import java.util.List;
import java.util.Map;

import com.etong.sms.model.Message;

public interface MessageMapper {
	public int addMessageRequest(Message message);
	
	/**String phone,String startDate,String endDate,String memberId,int state*/
	public List<Message> getMessageList(Map<String, Object>map);
	
	public int deleteMessage(Long id);

}
