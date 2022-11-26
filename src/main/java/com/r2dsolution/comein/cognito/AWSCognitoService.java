package com.r2dsolution.comein.cognito;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.r2dsolution.comein.dto.PersonalDto;
import com.r2dsolution.comein.util.DateUtils;
import com.r2dsolution.comein.util.StringUtils;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminCreateUserRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminCreateUserResponse;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminDeleteUserRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminDeleteUserResponse;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminSetUserPasswordRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminSetUserPasswordResponse;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminUpdateUserAttributesRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminUpdateUserAttributesResponse;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AttributeType;
import software.amazon.awssdk.services.cognitoidentityprovider.model.CognitoIdentityProviderException;
import software.amazon.awssdk.services.cognitoidentityprovider.model.ListUsersRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.ListUsersResponse;

@Service
@PropertySource("classpath:aws.properties")
public class AWSCognitoService {

	private static Logger log = LoggerFactory.getLogger(AWSCognitoService.class);
	
	@Value("${accessKey}")
	String accessKey;
	
	@Value("${secretKey}")
	String secretKey;
	
	@Value("${userPoolId}")
	String userPoolId;
	
	public static String POST_CREATE_USER = "createUser";
	public static String POST_UPDATE_USER = "updateUser";
	
	public static String GET_USER = "getUser";
	public static String GET_LIST_USER = "listUser";
	
	
	private CognitoIdentityProviderClient init() {
	    AwsBasicCredentials awsCreds = AwsBasicCredentials.create(accessKey,
		secretKey);
		
	    CognitoIdentityProviderClient cognitoClient =
        CognitoIdentityProviderClient.builder()
                .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                .region(Region.AP_SOUTHEAST_1)
                .build();
	    
	    return cognitoClient;		
	}
	
	public String post(String method, PersonalDto param) {
		CognitoIdentityProviderClient cognitoClient = init();
		
		String ownerId = null;
		if(POST_CREATE_USER.equals(method)) {
			ownerId = createNewUser(cognitoClient, param);
		} else if(POST_UPDATE_USER.equals(method)) {
			ownerId = updateUser(cognitoClient, param);
		}
		cognitoClient.close();
		
		return ownerId;
	}
	
	public List<PersonalDto> get(String method, PersonalDto param) {
		CognitoIdentityProviderClient cognitoClient = init();
		
		List<PersonalDto> results = null;
		if(GET_USER.equals(method)) {
			results = listUsersFilter(cognitoClient, param);
		} else if(GET_LIST_USER.equals(method)) {
			results = listAllUsers(cognitoClient);
		}
		
		cognitoClient.close();
		
		return results;
	}
	
	public void delete(String email) {
		CognitoIdentityProviderClient cognitoClient = init();
		
		deleteUser(cognitoClient, email);
		
		cognitoClient.close();
	}
	
	public void changePassword(String email, String password) {
		CognitoIdentityProviderClient cognitoClient = init();
		
		AdminSetUserPasswordRequest userRequest = AdminSetUserPasswordRequest.builder()
	        		.userPoolId(userPoolId)
	                .username(email)
	                .password(password)
	                .permanent(true)
	                .build() ;
	
	    AdminSetUserPasswordResponse response = cognitoClient.adminSetUserPassword(userRequest);

		log.info("changePassword successfully.");
		
		cognitoClient.close();
	}
	
	
	private String createNewUser(CognitoIdentityProviderClient cognitoClient, PersonalDto personalDto) {

		String ownerId = null;
		try {
			String email = personalDto.getEmail();
			String name = email;
			String mobileNo = personalDto.getMobileNo();
			String firstName = personalDto.getFirstName();
			String lastName = personalDto.getLastName();
			String referenceName = personalDto.getReferenceName();
			String password = "P@ssw0rd";
			

            List<AttributeType> attrs = new ArrayList<>();
            attrs.add(AttributeType.builder().name("phone_number").value(mobileNo).build());
            attrs.add(AttributeType.builder().name("given_name").value(firstName).build());
            attrs.add(AttributeType.builder().name("family_name").value(lastName).build());
            attrs.add(AttributeType.builder().name("name").value(referenceName).build());

			AdminCreateUserRequest userRequest = AdminCreateUserRequest.builder().userPoolId(this.userPoolId).username(name)
					.temporaryPassword(password).userAttributes(attrs).messageAction("SUPPRESS").build();

			AdminCreateUserResponse response = cognitoClient.adminCreateUser(userRequest);
			log.info(
					"User " + response.user().username() + "is created. Status: " + response.user().userStatus());

			ownerId = response.user().username();
		} catch (CognitoIdentityProviderException e) {
			System.err.println(e.awsErrorDetails().errorMessage());
		}
		return ownerId;
	}
	
	private String updateUser(CognitoIdentityProviderClient cognitoClient, PersonalDto personalDto) {

		String ownerId = personalDto.getOwnerId();
		try {
			String mobileNo = personalDto.getMobileNo();
			String firstName = personalDto.getFirstName();
			String lastName = personalDto.getLastName();
			String referenceName = personalDto.getReferenceName();
			
            List<AttributeType> attrs = new ArrayList<>();
            if(!StringUtils.isEmpty(mobileNo))
            	attrs.add(AttributeType.builder().name("phone_number").value(mobileNo).build());
            attrs.add(AttributeType.builder().name("given_name").value(firstName).build());
            attrs.add(AttributeType.builder().name("family_name").value(lastName).build());
            attrs.add(AttributeType.builder().name("name").value(referenceName).build());

            AdminUpdateUserAttributesRequest userRequest = AdminUpdateUserAttributesRequest.builder()
            		.userPoolId(this.userPoolId)
            		.username(ownerId)
            		.userAttributes(attrs)
            		.build();
            AdminUpdateUserAttributesResponse response = cognitoClient.adminUpdateUserAttributes(userRequest);
            log.info(
					"User " + ownerId + "is update attrs. Status: " + response);

		} catch (CognitoIdentityProviderException e) {
			System.err.println(e.awsErrorDetails().errorMessage());
		}
		return ownerId;
	}
	
    private List<PersonalDto> listAllUsers(CognitoIdentityProviderClient cognitoClient) {

    	List<PersonalDto> results = new ArrayList<>();
        try {
            // List all users
            ListUsersRequest usersRequest = ListUsersRequest.builder()
                    .userPoolId(userPoolId)
                    .build();

            ListUsersResponse response = cognitoClient.listUsers(usersRequest);
            response.users().forEach(user -> {
                        log.info("User " + user.username() + " Status " + user.userStatus() + " Created " + user.userCreateDate() );
                        PersonalDto personalDto = new PersonalDto();
            			String name = null;
            			String value = null;
                        log.info("User with filter applied " + user.username() + " Status " + user.userStatus() + " Created " + user.userCreateDate() );
                        for (AttributeType userAttribute : user.attributes()) {
                        	name = userAttribute.name();
                        	value = userAttribute.value();
							log.info(">> name : " + name + " value : " + value);
                        	if("sub".equals(name)) {
								personalDto.setOwnerId(value);
							} else if("email".equals(name)) {
								personalDto.setEmail(value);
							} else if("phone_number".equals(name)) {
								personalDto.setMobileNo(value);
							} else if("given_name".equals(name)) {
								personalDto.setFirstName(value);
							} else if("family_name".equals(name)) {
								personalDto.setLastName(value);
							} else if("name".equals(name)) {
								personalDto.setReferenceName(value);
							}
                		}
                        results.add(personalDto);
                    }
            );

        } catch (CognitoIdentityProviderException e){
            System.err.println(e.awsErrorDetails().errorMessage());
        }
        return results;
    }
    
    private List<PersonalDto> listUsersFilter(CognitoIdentityProviderClient cognitoClient, PersonalDto param) {

    	List<PersonalDto> results = new ArrayList<>();
        try {
        	String filter = "";
        	if(!StringUtils.isEmpty(param.getOwnerId())){
        		filter += "sub = \""+ param.getOwnerId() +"\"";	
        	}
        	if(!StringUtils.isEmpty(param.getEmail())){
        		filter += "email = \""+ param.getEmail() +"\"";	
        	}
        	if(!StringUtils.isEmpty(param.getComeinId())){
        		filter += "preferred_username = \""+ param.getComeinId() +"\"";	
        	}

            ListUsersRequest usersRequest = ListUsersRequest.builder()
                    .userPoolId(userPoolId)
                    .filter(filter)
                    .build();

            ListUsersResponse response = cognitoClient.listUsers(usersRequest);
            response.users().forEach(user -> {
            			PersonalDto personalDto = new PersonalDto();
            			String name = null;
            			String value = null;
                        log.info("User with filter applied " + user.username() + " Status " + user.userStatus() + " Created " + user.userCreateDate() );
                        for (AttributeType userAttribute : user.attributes()) {
                        	name = userAttribute.name();
                        	value = userAttribute.value();
							log.info(">> name : " + name + " value : " + value);
                        	if("sub".equals(name)) {
								personalDto.setOwnerId(value);
							} else if("email".equals(name)) {
								personalDto.setEmail(value);
							} else if("phone_number".equals(name)) {
								personalDto.setMobileNo(value);
							} else if("given_name".equals(name)) {
								personalDto.setFirstName(value);
							} else if("family_name".equals(name)) {
								personalDto.setLastName(value);
							} else if("name".equals(name)) {
								personalDto.setReferenceName(value);
							} else if("birthdate".equals(name)) {
								personalDto.setBirthDate(DateUtils.toLocalDate(value, null));
							} else if("custom:card_expire_date".equals(name)) {
								personalDto.setExpireDate(DateUtils.toLocalDate(value, null));
							} else if("custom:card_id".equals(name)) {
								personalDto.setIdNo(value);
							}
                		}
                        results.add(personalDto);
                    }
            );

        } catch (CognitoIdentityProviderException e){
            System.err.println(e.awsErrorDetails().errorMessage());
        }
        return results;
    }
    
	private void deleteUser(CognitoIdentityProviderClient cognitoClient, String email) {

		try {

			AdminDeleteUserRequest userRequest = AdminDeleteUserRequest.builder().userPoolId(this.userPoolId).username(email)
					.build();

			AdminDeleteUserResponse response = cognitoClient.adminDeleteUser(userRequest);
			log.info("User " + email + "is deleted. response : " + response);

		} catch (CognitoIdentityProviderException e) {
			System.err.println(e.awsErrorDetails().errorMessage());
		}
	}
}
