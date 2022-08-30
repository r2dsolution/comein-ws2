package com.r2dsolution.comein.service;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;

public class SMSService {
	
	
	public static void  main(String[] args) {
		SMSService service = new SMSService();
		service.sendSMS("Hello Message", "0968237000");
	}

	public void sendSMS(String message,String mobile) {
		AmazonSNSClient snsClient = new AmazonSNSClient(new ClasspathPropertiesFileCredentialsProvider("aws.properties"));
        //String message = "My SMS message";
        String phoneNumber = "+66"+mobile;
        Map<String, MessageAttributeValue> smsAttributes = 
                new HashMap<String, MessageAttributeValue>();
        PublishResult result = snsClient.publish(new PublishRequest()
                .withMessage(message)
                .withPhoneNumber(phoneNumber)
                .withMessageAttributes(smsAttributes));
        	System.out.println(result); // Prints the message ID.
	}
}
