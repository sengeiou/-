package com.informanage.sendinfo.service.Impl;

import java.util.HashMap;
import java.util.Map;

import com.informanage.common.MyException;
import com.informanage.common.ResultCodeEnum;
import com.informanage.sendinfo.service.MsmService;
import com.informanage.sendinfo.utils.SendMail;
import com.informanage.sendinfo.utils.SmsProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class MsmServiceImpl implements MsmService {
@Autowired
private SendMail sendMail;
	 @Autowired
	    private SmsProperties smsProperties;

	    @Override
	    public void send(String mobile, String checkCode){

	        //创建配置对象
	        DefaultProfile profile = DefaultProfile.getProfile(
	                smsProperties.getRegionId(),
	                smsProperties.getKeyId(),
	                smsProperties.getKeySecret());
	        //创建client对象
	        IAcsClient client = new DefaultAcsClient(profile);

	        //创建参数对象
	        CommonRequest request = new CommonRequest();
	        //组装参数对象
	        request.setSysMethod(MethodType.POST);
	        request.setSysDomain("dysmsapi.aliyuncs.com");
	        request.setSysVersion("2017-05-25");
	        request.setSysAction("SendSms");
	        request.putQueryParameter("RegionId", smsProperties.getRegionId());
	        request.putQueryParameter("PhoneNumbers", mobile);
	        request.putQueryParameter("SignName", smsProperties.getSignName());
	        request.putQueryParameter("TemplateCode", smsProperties.getTemplateCode());

	        Map<String, String> param = new HashMap<>();
	        param.put("code", checkCode);
	        Gson gson = new Gson();
	        String json = gson.toJson(param);

	        request.putQueryParameter("TemplateParam", json);

	        //发送短信
	        CommonResponse response;
	        String data="";
			try {
				response = client.getCommonResponse(request);
				data = response.getData();
			} catch (ServerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (com.aliyuncs.exceptions.ClientException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

//	      response.getHttpResponse().isSuccess()//注意：此处不能通过http的响应结果判断短信是否发送成功
	        //得到就送字符串格式的返回结果


	        //解析响应结果
	        HashMap<String, String> map = gson.fromJson(data, HashMap.class);
	        String code = map.get("Code");
	        String message = map.get("Message");

	        if("isv.BUSINESS_LIMIT_CONTROL".equals(code)){
	            log.error("发送短信过于频繁：" + "code-" + code + ", message-" + message);
	            throw new MyException(ResultCodeEnum.SMS_SEND_ERROR_BUSINESS_LIMIT_CONTROL);
	        }

	        if(!"OK".equals(code)){
	            log.error("短信发送失败：" + "code-" + code + ", message-" + message);
	            throw new MyException(ResultCodeEnum.SMS_SEND_ERROR);
	        }
	    }

	@Override
	public void sendEmail(String to, String subject, String content, String Cc) {
	    	try {
		sendMail.sendSimpleMail(to, subject, content, Cc);
			}catch (Exception e){
	    		throw new MyException(ResultCodeEnum.EMAIL_SEND_ERROR);
			}
	}

}
