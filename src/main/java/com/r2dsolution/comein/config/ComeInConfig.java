package com.r2dsolution.comein.config;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.r2dsolution.comein.util.SecretManagerUtils;


@Configuration
@PropertySource("classpath:application.properties")
@PropertySource("classpath:aws.properties")
@ComponentScan({ "com.r2dsolution.comein.service","com.r2dsolution.comein.business","com.r2dsolution.comein.client"})
public class ComeInConfig {   
	
	
	@Value( "${comein.mode}" )
	public String mode;
	
	@Value( "${accessKey}" )
	public String accessKey;
	
	@Value( "${secretKey}" )
	public String secretKey;
	
	@Value( "${region}" )
	public String region;
	
	@Bean
	String loginAccessToken(AWSSecretsManager secretManager) {
		Map<String,String> awsSecrets = SecretManagerUtils.getSecret(secretManager, mode+"/api/comein/login");
		String token = awsSecrets.get("accessToken");
		return token;
	}
    
    @Bean
    AmazonSQSClientBuilder intAmazonSQSClientBuilder(AWSSecretsManager secretManager) {
    	Map<String,String> awsSecrets = SecretManagerUtils.getSecret(secretManager, mode+"/sqs/comein");
    	String accessKey = awsSecrets.get("accessKey");
		String secretKey = awsSecrets.get("secretKey");
		
		
		
		BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey,secretKey);
    	
    	return  AmazonSQSClientBuilder.standard()
		          .withCredentials(new AWSStaticCredentialsProvider(awsCreds)) ;
    }
    
    @Bean
    AmazonSimpleEmailServiceClientBuilder initAmazonSimpleEmailServiceClientBuilder(AWSSecretsManager secretManager) {
    	Map<String,String> awsSecrets = SecretManagerUtils.getSecret(secretManager, mode+"/ses/comein");
    	String accessKey = awsSecrets.get("accessKey");
		String secretKey = awsSecrets.get("secretKey");
		
		
		
		BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey,secretKey);
    	
    	return  AmazonSimpleEmailServiceClientBuilder.standard()
		          .withCredentials(new AWSStaticCredentialsProvider(awsCreds)) ;
    
    }
    
    @Bean
    public AWSCredentialsProvider initCredentialsProvider() {
    	
    	BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey,secretKey);
    	
    	
    	return new AWSStaticCredentialsProvider(awsCreds);
    }

    
    @Bean 
    public AWSSecretsManager initAWSSecretsManager() {
    	Regions region = Regions.AP_SOUTHEAST_1;
    	 AWSSecretsManager client  = AWSSecretsManagerClientBuilder.standard()
					.withCredentials(initCredentialsProvider()) 
                 .withRegion(region)
                 .build();
    	 return client;
    }
    
    
   
}