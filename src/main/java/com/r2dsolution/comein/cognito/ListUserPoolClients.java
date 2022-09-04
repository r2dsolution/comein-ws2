package com.r2dsolution.comein.cognito;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.CognitoIdentityProviderException;
import software.amazon.awssdk.services.cognitoidentityprovider.model.ListUserPoolClientsRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.ListUserPoolClientsResponse;

/**
 * To run this AWS code example, ensure that you have setup your development environment, including your AWS credentials.
 *
 * For information, see this documentation topic:
 *
 * https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/get-started.html
 */

public class ListUserPoolClients {

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
	    
        listAllUserPoolClients(cognitoClient, userPoolId ) ;
        cognitoClient.close();
    }

    public static void listAllUserPoolClients(CognitoIdentityProviderClient cognitoClient, String userPoolId) {

        try {
            ListUserPoolClientsResponse response = cognitoClient.listUserPoolClients(ListUserPoolClientsRequest.builder()
                    .userPoolId(userPoolId)
                    .build());

            response.userPoolClients().forEach(userPoolClient -> {
                System.out.println("User pool client " + userPoolClient.clientName() + ", Pool ID " + userPoolClient.userPoolId() + ", Client ID " + userPoolClient.clientId() );
                    }
            );

        } catch (CognitoIdentityProviderException e){
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
    }
}