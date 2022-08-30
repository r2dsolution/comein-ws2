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
public class Signup {

	static String ACCESS_KEY = "AKIARKRCXVNEIJRAB62J";
//	static String CLIENT_ID = "2acmubkda49vnv3mhm3k9qe05i";
	static String CLIENT_ID = "3m5pofbf6qmfppqdpspbvrsm48";
	
	static String SECRET_KEY = "a+sztiFt6n55xmV4u5IVQSjDjiobeiC68JT1YOpJ";

    static String userPoolId = "us-east-2_N4u6sVupy";
	
    public static void main(String[] args) {

	    AwsBasicCredentials awsCreds = AwsBasicCredentials.create(ACCESS_KEY,
		SECRET_KEY);
    	
	    CognitoIdentityProviderClient cognitoClient =
        CognitoIdentityProviderClient.builder()
                .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                .region(Region.US_EAST_2)
                .build();


	    String userName = "kh.jaroon@gmail.com";
	    String email = "kh.jaroon@gmail.com";
//	    String userName = "comein___testEmail@abc.com";
//	    String email = "comein___testEmail@abc.com";
	    
	    String password = "password";

        signupUser(cognitoClient, userPoolId, userName, email, password);
        cognitoClient.close();
    }

    public static void signupUser(CognitoIdentityProviderClient cognitoClient,
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
//                    .desiredDeliveryMediums(DeliveryMediumType.EMAIL)
//                    .forceAliasCreation(Boolean.FALSE)
                    .build() ;
            
//            SignUpRequest userRequest = SignUpRequest.builder()
//            		.clientId(CLIENT_ID)
////            		.clientId(userPoolId)
//            		.secretHash(SECRET_KEY)
//            		.username(name)
//            		.password(password)
//            		.userAttributes(userAttrs).build();
//
//            .withDesiredDeliveryMediums(DeliveryMediumType.EMAIL)
//            .withForceAliasCreation(Boolean.FALSE);
            
//            SignUpResponse response = cognitoClient.signUp(userRequest);
//            System.out.println("User " + response.userSub() + "is signup.");

            AdminCreateUserResponse response = cognitoClient.adminCreateUser(userRequest);
            System.out.println("User " + response.user().username() + "is created. Status: " + response.user().userStatus());

        } catch (CognitoIdentityProviderException e){
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
    }
}