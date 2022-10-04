package com.r2dsolution.comein.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.amazonaws.services.sqs.AmazonSQS;
import com.r2dsolution.comein.client.SimpleQueueServiceClient;
import com.r2dsolution.comein.dto.MailDto;
import com.r2dsolution.comein.exception.ServiceException;
import com.r2dsolution.comein.model.EmailRequest; 

@Service
@PropertySource("classpath:mail-hotel-admin.properties")
public class MailService {

	private static Logger log = LoggerFactory.getLogger(MailService.class);
	
	 static final String CONFIGSET = "ConfigSet";
	 
	@Autowired
	private SimpleQueueServiceClient client;
		
	 @Autowired
	 private TemplateEngine templateEngine;
	 
	 @Value("${email.owner}")
	 String emailOwner;
	 
	 @Value("${email.invite.url}")
	 String inviteUrl;
	
	 @Value("${email.success.url}")
	 String successUrl;
	 
	 @Value("${register.success}")
	 String mailTemplateRegisterSuccess;
	 
//	 public static void main(String[] args) {
//		 MailService app = new MailService();
//		 app.sendMail("tawatchr0009@gmail.com", "tawatchr0009@gmail.com", "Test send by app", "Hello world");
//	 }
//	 
//	
//	public void sendMail(String from,String to,String subject,String content) {
//		try {
//		      AmazonSimpleEmailService client = 
//		          AmazonSimpleEmailServiceClientBuilder.standard()
//		          .withCredentials(new ClasspathPropertiesFileCredentialsProvider("aws.properties")) 
//		          // Replace US_WEST_2 with the AWS Region you're using for
//		          // Amazon SES.
//		            .withRegion(Regions.AP_SOUTHEAST_1).build();
//		      SendEmailRequest request = new SendEmailRequest()
//		          .withDestination(
//		              new Destination().withToAddresses(to))
//		          .withMessage(new Message()
//		              .withBody(new Body()
//		                  .withHtml(new Content()
//		                      .withCharset("UTF-8").withData(content))
////		                  .withText(new Content()
////		                      .withCharset("UTF-8").withData(TEXTBODY))
//		                  )
//		              .withSubject(new Content()
//		                  .withCharset("UTF-8").withData(subject)))
//		          .withSource(from)
//		          // Comment or remove the next line if you are not using a
//		          // configuration set
//		         // .withConfigurationSetName(CONFIGSET);
//		          ;
//		      client.sendEmail(request);
//		      log.info("Email sent!");
//		    } catch (Exception ex) {
//		      log.error("The email was not sent. Error message: {}" 
//		          ,ex.getMessage());
//		      throw new ServiceException(ex);
//		    }
//	}
	 
	public void sendMail(EmailRequest req) {
		try {
			AmazonSQS sqsClient = client.initClient();
			String url = client.urlSendEmail(sqsClient);
			client.send(sqsClient, url, req);

			log.info("Email sent!");
		} catch (Exception ex) {
			log.error("The email was not sent. Error message: {}", ex.getMessage());
			throw new ServiceException(ex);
		}
	}
	 
	
	public void sendRegisterInvite(MailDto dto) {
		log.info(">>>> sendRegisterInvite email : {}", dto.getTo());
		
//		String template = generateInviteMailHtml(dto.getRefNo(), dto.getRole());
//
//		String subject = "Welcome to ComeIn - Invite";
//		sendMail(emailOwner, dto.getTo(), subject , template);
		EmailRequest req = generateInviteMailReq(dto.getTo(), dto.getRefNo(), dto.getRole());
		sendMail(req);
		
	}
	
//	public void sendRegisterHotelAdminVerify(MailDto dto) {
//		log.info(">>>> sendRegisterHotelAdminVerify email : {}", dto.getTo());
//		
//		String subject = "Welcome to ComeIn - Verify";
//		sendMail(emailOwner, dto.getTo(), subject , "OTP : 9 7 5 3 1 2");
//		
//	}
	
	public void sendRegisterHotelAdminSuccess(MailDto dto) {

		log.info(">>>> sendRegisterHotelAdminSuccess email : {}", dto.getTo());
		
//		String template = generateSuccessMailHtml(dto.getRole());
//
//		String subject = "Welcome to ComeIn - Success";
//		sendMail(emailOwner, dto.getTo(), subject , template);
		
		EmailRequest req = generateSuccessMailReq(dto.getTo(), dto.getRole());
		sendMail(req);
	}
	
	public void sendBookingSuccess(MailDto dto) {
		log.info(">>>> sendBookingSuccess email : {}", dto.getTo());
		
//		String template = generateBookingSuccessMailHtml(dto.getRole(), dto.getName(), dto.getSurname());
//
//		String subject = "Welcome to ComeIn - Booking Success";
//		sendMail(emailOwner, dto.getTo(), subject , template);
		
		EmailRequest req = generateBookingSuccessMailReq(dto.getTo(), dto.getRole(), dto.getName(), dto.getSurname());
		sendMail(req);
		
	}
	
    public String generateInviteMailHtml(String refNo, String role)
    {
    	String link = "";
    	if("admin".equals(role)) {
    		link = inviteUrl+refNo;
    	} else if("staff".equals(role)) {
    		link = inviteUrl+refNo+"&consent=false";
    	}
    	
    	Context context = new Context();
    	context.setVariable("role", role);
    	context.setVariable("link", link);

        final String templateFileName = "invite_mail"; //Name of the template file without extension
        String output = this.templateEngine.process(templateFileName, context);

        return output;
    }
    
    public EmailRequest generateInviteMailReq(String email, String refNo, String role)
    {
    	final String template = "invite_mail"; 
    	
    	String link = "";
    	if("admin".equals(role)) {
    		link = inviteUrl+refNo;
    	} else if("staff".equals(role)) {
    		link = inviteUrl+refNo+"&consent=false";
    	}
    	
		EmailRequest req = new EmailRequest();
		req.setTemplate(template);
		req.setEmail(email);
		req.getParams().put("role", role);	
		req.getParams().put("link", link);	
		return req;
    }
    
    public String generateSuccessMailHtml(String role)
    {
    	String link = successUrl;
    	Context context = new Context();
    	context.setVariable("role", role);
    	context.setVariable("link", link);
        
        final String templateFileName = "success_mail"; //Name of the template file without extension
        String output = this.templateEngine.process(templateFileName, context);

        return output;
    }
    
    public EmailRequest generateSuccessMailReq(String email, String role)
    {
        final String template = "success_mail";

        String link = successUrl;
        
		EmailRequest req = new EmailRequest();
		req.setTemplate(template);
		req.setEmail(email);
		req.getParams().put("role", role);	
		req.getParams().put("link", link);	
		return req;
    }
    
    public String generateBookingSuccessMailHtml(String role, String name, String surname)
    {
    	String link = "";
    	Context context = new Context();
    	context.setVariable("role", role);
    	context.setVariable("link", link);
    	context.setVariable("name", name);
    	context.setVariable("surname", surname);
        
        final String templateFileName = "booking_mail"; //Name of the template file without extension
        String output = this.templateEngine.process(templateFileName, context);

        return output;
    }
    
    public EmailRequest generateBookingSuccessMailReq(String email, String role, String name, String surname)
    {
    	final String template = "booking_mail";
    	
    	//TODO link not assign
    	String link = "";
        
		EmailRequest req = new EmailRequest();
		req.setTemplate(template);
		req.setEmail(email);
//		req.getParams().put("role", role);	
//		req.getParams().put("link", link);	
		req.getParams().put("name", name);	
		req.getParams().put("surname", surname);	
		return req;
		
    }
}
