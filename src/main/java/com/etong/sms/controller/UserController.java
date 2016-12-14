package com.etong.sms.controller;

import com.etong.sms.dataServer.UserServer;
import com.etong.sms.model.User;
import com.etong.sms.utility.SmsError;
import com.etong.sms.utility.SmsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
public class UserController {
	
	@Autowired
	private UserServer userServer;
	
	@RequestMapping(value="/sms/user/detail")
	@ResponseBody
	public SmsResult getUserDetail(int id){		
		return userServer.getSingleUser(id);
	}
	
	@RequestMapping(value="/sms/user/add")
	@ResponseBody
	public SmsResult addUserRequest(User user){	
		return userServer.insertUser(user);
	}
	
	
	@RequestMapping(value="/sms/user/list")
	@ResponseBody
	public SmsResult getUserListRequest(@RequestParam(required=false,defaultValue="0")int start,
			@RequestParam(defaultValue="0",required=false)int count){
		return userServer.getUserList(start, count);
		
	}
	@RequestMapping(value = "/sms/users")
	@ResponseBody
	public SmsResult getListUser(@RequestParam("users") List<User> users){

		return new SmsResult(SmsError.SMS_ERROR_SENDSUCC,null,users);

	}

	@ResponseBody
	@RequestMapping(value = "/sms/userinfo")
	public SmsResult addUserInfo(@RequestBody User user){

		System.out.println(user);
		return new SmsResult(SmsError.SMS_ERROR_SENDSUCC,null,user);
	}



}
