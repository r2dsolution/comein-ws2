package com.r2dsolution.comein.cognito;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cognitoidentity.CognitoIdentityClient;
import software.amazon.awssdk.services.cognitoidentity.model.GetIdRequest;
import software.amazon.awssdk.services.cognitoidentity.model.GetIdResponse;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.CognitoIdentityProviderException;

/**
 * To run this AWS code example, ensure that you have setup your development environment, including your AWS credentials.
 *
 * For information, see this documentation topic:
 *
 * https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/get-started.html
 */

public class GetId {

	static String ACCESS_KEY = "AKIARKRCXVNEIJRAB62J";
	static String SECRET_KEY = "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";
	
    public static void main(String[] args) {

	    AwsBasicCredentials awsCreds = AwsBasicCredentials.create(ACCESS_KEY,
		SECRET_KEY);
    	
//	    CognitoIdentityProviderClient cognitoClient =
//        CognitoIdentityProviderClient.builder()
//                .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
//                .region(Region.US_EAST_2)
//                .build();

	    //
//	    String identityPoolId = "us-east-2_N4u6sVupy";
	    String identityPoolId = "us-east-2:21f70e64-12b0-4ff4-8e4e-07f736c90946";

        CognitoIdentityClient cognitoClient = CognitoIdentityClient.builder()
        		.credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                .region(Region.AP_SOUTHEAST_1)
                .build();

        getClientID(cognitoClient, identityPoolId);
        cognitoClient.close();
    }

    public static void getClientID(CognitoIdentityClient cognitoClient, String identityPoolId){
        try {

            GetIdRequest request = GetIdRequest.builder()
                    .identityPoolId(identityPoolId)
                    .build();

            GetIdResponse response = cognitoClient.getId(request);
            System.out.println("Identity ID " + response.identityId());

        } catch (CognitoIdentityProviderException e){
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
    }
}
