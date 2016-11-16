package com.etong.sms.dataServer.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etong.sms.dao.UserMapper;
import com.etong.sms.dataServer.UserServer;
import com.etong.sms.model.User;
import com.etong.sms.utility.SmsError;
import com.etong.sms.utility.SmsResult;

@Service("UserServer")
public class UserServerImpl implements UserServer {
	private UserMapper userMapper;
	public UserMapper getUserMapper() {
		return userMapper;
	}
	@Autowired
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}


	@Override
	public SmsResult insertUser(User user) {
		int result=userMapper.insertUser(user);
		if (result<1) {
			return new SmsResult(SmsError.SMS_ERROR_SUBMIT,"用户添加失败",null);
		}
		return new SmsResult(SmsError.SMS_ERROR_SENDSUCC, null, user.getUsername());
		
	}
	@Override
	public SmsResult getSingleUser(int id) {
		User user=userMapper.getSingleUser(id);
		if (user==null) {
			return new SmsResult(SmsError.SMS_ERROR_NODATA,null,null);
		}
		return new SmsResult(SmsError.SMS_ERROR_SENDSUCC, null, user);
		
	}
	@Override
	public SmsResult getUserList(int start, int count) {
		Map<String, Object>objMap=new HashMap<String, Object>();
		objMap.put("start", start);
		objMap.put("count", count);
		List<User>users=userMapper.getUserList(objMap);
		if (users.isEmpty()) {
			return new SmsResult(SmsError.SMS_ERROR_NODATA,null,null);
		}
		return new SmsResult(SmsError.SMS_ERROR_SENDSUCC,null,users);
	}

}
