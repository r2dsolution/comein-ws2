package com.r2dsolution.comein.aws;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;
 
@Service
@PropertySource("classpath:aws.properties")
public class S3Service {
    private static final String BUCKET = "tawatchai3e231dfcd1b74f2bb33615888fa0fc48213041-dev";
     
	@Value("${accessKey}")
	String accessKey;
	
	@Value("${secretKey}")
	String secretKey;
	
    public void uploadFile(String module, String fileName, String contentType, InputStream inputStream)
            throws S3Exception, AwsServiceException, SdkClientException, IOException {
    	
	    AwsBasicCredentials awsCreds = AwsBasicCredentials.create(accessKey, secretKey);
	    
        S3Client client = S3Client.builder()
                .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                .region(Region.AP_SOUTHEAST_1)
        		.build();
        
        String path = "public/" + module + "/";
        
        PutObjectRequest request = PutObjectRequest.builder()
                            .bucket(BUCKET)
                            .key(path+fileName)
                            .acl("public-read")
                            .contentType(contentType)
                            .build();
         
        client.putObject(request,
                RequestBody.fromInputStream(inputStream, inputStream.available()));
    }
    
    public void deleteFile(String module, String fileName)
            throws S3Exception, AwsServiceException, SdkClientException, IOException {
    	
	    AwsBasicCredentials awsCreds = AwsBasicCredentials.create(accessKey, secretKey);
	    
        S3Client client = S3Client.builder()
                .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                .region(Region.AP_SOUTHEAST_1)
        		.build();
        
        String path = "public/" + module + "/";
        
        DeleteObjectRequest request = DeleteObjectRequest.builder()
                            .bucket(BUCKET)
                            .key(path+fileName)
                            .build();
         
        client.deleteObject(request);
    }
}
