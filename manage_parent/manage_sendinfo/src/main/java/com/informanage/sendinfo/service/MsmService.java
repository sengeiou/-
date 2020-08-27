package com.informanage.sendinfo.service;

public interface MsmService {

	void send(String mobile, String checkCode);
	void sendEmail(String to,String subject,String content,String Cc);
}
