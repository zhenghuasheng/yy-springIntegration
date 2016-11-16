package com.etong.sms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etong.sms.dataServer.UserServer;
import com.etong.sms.model.User;
import com.etong.sms.utility.SmsResult;


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
	
	

}
