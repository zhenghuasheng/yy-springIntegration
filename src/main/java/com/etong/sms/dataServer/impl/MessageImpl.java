package com.etong.sms.dataServer.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.InitBinder;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.etong.sms.dao.MessageMapper;
import com.etong.sms.dataServer.MessageServer;
import com.etong.sms.model.Message;
import com.etong.sms.model.MessageTaskModel;
import com.etong.sms.utility.ClientFactory;
import com.etong.sms.utility.DateUtil;
import com.etong.sms.utility.SendStatus;
import com.etong.sms.utility.SmsError;
import com.etong.sms.utility.SmsResult;
import com.etong.sms.utility.StringUtil;
import com.etong.sms.utility.SystemConstant;

@Service("messageServer")
public class MessageImpl implements MessageServer {
	
	private Queue<MessageTaskModel> queue=new LinkedList<MessageTaskModel>();
	
	@PostConstruct
	private void Init(){
		queue.add(new MessageTaskModel("1", "1", "10000"));
		queue.add(new MessageTaskModel("2","2","10001"));
		queue.add(new MessageTaskModel("3","3","10002"));
		queue.add(new MessageTaskModel("4","4","10003"));
		queue.add(new MessageTaskModel("5","5","10004"));
	}
	

	private MessageMapper messageMapper;

	public MessageMapper getMessageMapper() {
		return messageMapper;
	}

	@Autowired
	public void setMessageMapper(MessageMapper messageMapper) {
		this.messageMapper = messageMapper;
	}

	@Override
	public SmsResult addMessageRequest(Message message) {
		try {
			int result = messageMapper.addMessageRequest(message);
			if (result < 1) {
				return new SmsResult(SmsError.SMS_ERROR_SUBMIT, null, null);
			}
			return new SmsResult(SmsError.SMS_ERROR_SENDSUCC, null, message);
		} catch (Exception e) {
			return new SmsResult(SmsError.SMS_ERROR_DB, e.getMessage(), null);
		}
	}

	@Override
	public SmsResult getMessageList(String phone, String startDate,
			String endDate, String memberId, Integer state, int start, int count) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("phone", phone);
			map.put("startDate", startDate);
			map.put("endDate", endDate);
			map.put("memberId", memberId);
			map.put("state", state);
			map.put("start", start);
			map.put("count", count);
			List<Message> messages = messageMapper.getMessageList(map);

			if (messages.isEmpty()) {
				return new SmsResult(SmsError.SMS_ERROR_NODATA, null, null);
			}
			return new SmsResult(SmsError.SMS_ERROR_SENDSUCC, null, messages);
		} catch (Exception e) {
			e.printStackTrace();
			return new SmsResult(SmsError.SMS_ERROR_DB, e.getMessage(), null);
		}
	}

	@Override
	public SmsResult deleteMessage(Long id) {
		return null;
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public SmsResult sendMessages(String jsonString, String stime,
			String memberId) {

		String rrid = DateUtil.getDateStr();

		JSONArray jsonArray = JSON.parseArray(jsonString);
		List<String> mobileList = new ArrayList<String>();
		List<String> contentList = new ArrayList<String>();

		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			String mobile = jsonObject.getString("mobile");
			String content = jsonObject.getString("content");
			mobileList.add(mobile);
			contentList.add(content + SystemConstant.MARK);
		}

		String mobileString = StringUtil.listString(mobileList,
				SystemConstant.SPLIT);
		/******************************************************** 短信发送 ****************************************************************/
		Client client = ClientFactory.getClient();
		String result = null;
		if (StringUtil.checkContent(contentList)) {// 内容相同
			String messageContent = contentList.get(0);
			result = client.mt(mobileString, messageContent,
					SystemConstant.EXT, stime, rrid);

		} else {
			String messageContent = StringUtil.listCharSetString(contentList,
					SystemConstant.SPLIT, SystemConstant.CHARSET);
			result = client.gxmt(mobileString, messageContent,
					SystemConstant.EXT, stime, rrid);
		}
		/********************************************************** 短信存储 ***********************************************************************/
		if (stime == null || stime.isEmpty()) {
			stime = DateUtil.getCurrentDate();
		}
		int state = SendStatus.SEND_SENDING.getCode();
		if (null == result || result.startsWith("-") || result.isEmpty()) {
			state = SendStatus.SEND_FAIL.getCode();
		} else {
			state = SendStatus.SEND_SUCC.getCode();
		}
		for (int i = 0; i < mobileList.size(); i++) {
			Message message = new Message();
			message.setStime(stime);
			message.setContent(contentList.get(i));
			message.setMobile(mobileList.get(i));
			message.setRrid(rrid);
			message.setState(state);
			message.setExt(SystemConstant.EXT);
			message.setMemberId(memberId);
			SmsResult smsResult = addMessageRequest(message);
			if (!smsResult.isSucceed()) {
				continue;
			}
		}
		
		if (state==SendStatus.SEND_FAIL.getCode()) {
			return new SmsResult(SmsError.SMS_ERROR_SENDFAIL,null, null);
		}else {
			return new SmsResult(SmsError.SMS_ERROR_SENDSUCC,null, null);
		}
		
	}

	@Override
	public SmsResult commitMessage(MessageTaskModel record) {
	
		boolean result=queue.offer(record);
		if (!result) {
			return new SmsResult(SmsError.SMS_ERROR_SUBMIT,null,null);
		}
		return new SmsResult(SmsError.SMS_ERROR_SENDSUCC,null,null);
	}
	
	public void jobInitTask(){
		if (queue.isEmpty()) {
			return;
		}
		System.out.println(queue.poll().getJsonString());
		System.out.println("执行---------------------------------------");
	}

}
