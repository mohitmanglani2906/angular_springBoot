package com.mohit2906.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;

@Component
public class SendEmail 
{
   String SENDGRID_API_KEY = Constants.SENDGRID_API_KEY;

	public String sendEmailToUsers(String toEmail)
	{
		Personalization personalization = new Personalization();
		Mail mail =  new Mail();		
		String subject = "OnBoarding";
		personalization.addTo(new Email(Constants.SENDER_EMAIl));
		personalization.addTo(new Email(toEmail));
		
		mail.addPersonalization(personalization); // Customized Mail Sending For more than one users...
		
		Content content = new Content("text/html","<div class='container'>Welcome To Angular and Java Developement!<div>");
		
		mail.addContent(content);
		mail.setSubject(subject);
		mail.setFrom(new Email(Constants.SENDER_EMAIl));
		
		//Mail mail = new Mail(from, subject, to, content); // For Single user
		
		//SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
		  SendGrid sg = new SendGrid(SENDGRID_API_KEY) ;	
		
		Request request = new Request();
		
		try {
				request.setMethod(Method.POST);
				request.setEndpoint("mail/send");
				request.setBody(mail.build());	
				Response response = sg.api(request);
				
				System.out.println("Success");
				
			    return "Success";
		}
		
		catch(Exception e) {
			System.out.println("Failure" + e.getMessage());
			return "Failure";
		}
		
	}
	
}
