package com.r2dsolution.comein.cognito;

import java.util.ArrayList;
import java.util.List;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminUpdateUserAttributesRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AttributeType;
import software.amazon.awssdk.services.cognitoidentityprovider.model.CognitoIdentityProviderException;
import software.amazon.awssdk.services.cognitoidentityprovider.model.ListUsersRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.ListUsersResponse;

/**
 * To run this AWS code example, ensure that you have setup your development environment, including your AWS credentials.
 *
 * For information, see this documentation topic:
 *
 * https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/get-started.html
 */
public class MigrateUser {

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
	    
        listAllUsers(cognitoClient, userPoolId );
        cognitoClient.close();
    }

    // Shows how to list all users in the given user pool.
    public static void listAllUsers(CognitoIdentityProviderClient cognitoClient, String userPoolId ) {

        try {
            // List all users
            ListUsersRequest usersRequest = ListUsersRequest.builder()
                    .userPoolId(userPoolId)
                    .build();

            List<AttributeType> attrs = new ArrayList<>();
            attrs.add(AttributeType.builder().name("phone_number").value("+66891111111").build());
            attrs.add(AttributeType.builder().name("given_name").value("xxxx(first)").build());
            attrs.add(AttributeType.builder().name("family_name").value("yyyy(last)").build());
            attrs.add(AttributeType.builder().name("name").value("xxxx yyyy").build());
			
            ListUsersResponse response = cognitoClient.listUsers(usersRequest);
            response.users().forEach(user -> {
                        System.out.println("User " + user.username() + " Status " + user.userStatus() + " Created " + user.userCreateDate() );
                        
                        boolean hasAttr = false;
                        for (AttributeType userAttribute : user.attributes()) {
                        	if("phone_number".equals(userAttribute.name())){
                        		hasAttr = true;
                        	}
                		}
						if (!hasAttr) {
							System.out.println(
									"===User " + user.username() + " Status " + user.userStatus() + " hasAttrs " + hasAttr);
							AdminUpdateUserAttributesRequest req = AdminUpdateUserAttributesRequest.builder()
									.userPoolId(userPoolId)
									.username(user.username())
									.userAttributes(attrs)
									.build();
							cognitoClient.adminUpdateUserAttributes(req);
						}
                    }
            );

        } catch (CognitoIdentityProviderException e){
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
    }

}