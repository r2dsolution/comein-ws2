package com.r2dsolution.comein.cognito;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminSetUserPasswordRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminSetUserPasswordResponse;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AttributeType;
import software.amazon.awssdk.services.cognitoidentityprovider.model.CognitoIdentityProviderException;

/**
 * To run this Java V2 code example, ensure that you have setup your development environment, including your credentials.
 *
 * For information, see this documentation topic:
 *
 * https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/get-started.html
 */
public class ChangePassword {

	static String ACCESS_KEY = "AKIARKRCXVNEIJRAB62J";
	static String SECRET_KEY = "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";
	
    public static void main(String[] args) {

	    AwsBasicCredentials awsCreds = AwsBasicCredentials.create(ACCESS_KEY,
		SECRET_KEY);
    	
	    CognitoIdentityProviderClient cognitoClient =
        CognitoIdentityProviderClient.builder()
                .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                .region(Region.AP_SOUTHEAST_1)
                .build();


	    String userPoolId = "ap-southeast-1_6RA5il9qF";
	    String userName = "buk_joon@hotmail.com";
	    String email = "buk_joon@hotmail.com";
	    String password = "password";

        changePassword(cognitoClient, userPoolId, userName, email, password);
        cognitoClient.close();
    }

    public static void changePassword(CognitoIdentityProviderClient cognitoClient,
                                   String userPoolId,
                                   String name,
                                   String email,
                                   String password){

        try{

            AdminSetUserPasswordRequest userRequest = AdminSetUserPasswordRequest.builder()
            		.userPoolId(userPoolId)
                    .username(name)
                    .password(password)
                    .permanent(true)
                    .build() ;

            AdminSetUserPasswordResponse response = cognitoClient.adminSetUserPassword(userRequest);
            System.out.println("User " + name + "is change password. Response: " + response);

        } catch (CognitoIdentityProviderException e){
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
    }
}