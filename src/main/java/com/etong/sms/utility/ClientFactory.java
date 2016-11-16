package com.etong.sms.utility;

import java.io.UnsupportedEncodingException;
import com.etong.sms.dataServer.impl.Client;

public class ClientFactory {
	public static Client getClient(){
		Client C=null;
		try {
			 C=new Client(SystemConstant.SN, SystemConstant.PWD);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return C;
	}
}
