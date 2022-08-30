package com.r2dsolution.comein.cognito;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminCreateUserRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminCreateUserResponse;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AttributeType;
import software.amazon.awssdk.services.cognitoidentityprovider.model.CognitoIdentityProviderException;

/**
 * To run this Java V2 code example, ensure that you have setup your development environment, including your credentials.
 *
 * For information, see this documentation topic:
 *
 * https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/get-started.html
 */
public class CreateUser {

	static String ACCESS_KEY = "AKIARKRCXVNEIJRAB62J";
	static String SECRET_KEY = "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";
	
    public static void main(String[] args) {

	    AwsBasicCredentials awsCreds = AwsBasicCredentials.create(ACCESS_KEY,
		SECRET_KEY);
    	
	    CognitoIdentityProviderClient cognitoClient =
        CognitoIdentityProviderClient.builder()
                .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                .region(Region.US_EAST_2)
                .build();


	    String userPoolId = "us-east-2_N4u6sVupy";
	    String userName = "testEmail@abc.com";
	    String email = "testEmail@abc.com";
	    String password = "password";

        createNewUser(cognitoClient, userPoolId, userName, email, password);
        cognitoClient.close();
    }

    public static void createNewUser(CognitoIdentityProviderClient cognitoClient,
                                   String userPoolId,
                                   String name,
                                   String email,
                                   String password){

        try{

            AttributeType userAttrs = AttributeType.builder()
                    .name("email")
                    .value(email)
                    .build();

            AdminCreateUserRequest userRequest = AdminCreateUserRequest.builder()
                    .userPoolId(userPoolId)
                    .username(name)
                    .temporaryPassword(password)
                    .userAttributes(userAttrs)
                    .messageAction("SUPPRESS")
                    .build() ;

            AdminCreateUserResponse response = cognitoClient.adminCreateUser(userRequest);
            System.out.println("User " + response.user().username() + "is created. Status: " + response.user().userStatus());

        } catch (CognitoIdentityProviderException e){
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
    }
}