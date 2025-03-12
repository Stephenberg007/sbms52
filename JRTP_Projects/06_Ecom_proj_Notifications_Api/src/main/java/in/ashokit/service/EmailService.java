package in.ashokit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import in.ashokit.entity.Customer;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	 public void sendEmail(String toEmail,String orderTrackingNum) {
	       
	        // Prepare email content
	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setTo(toEmail);
	        message.setSubject("Order Placed Successfully ");
	        message.setText("Dear User,\n\nYour Order has been successfully Placed.\n" +
	                        "Here is your Order tracking Number "+orderTrackingNum+ "\n\n" +
	                        "Please use it to Track updates for Ur order.\n\n" +
	                        "Regards,\nYour Team");

	        // Send email
	        try {
	        mailSender.send(message);
	        }catch(Exception e){
	        	e.printStackTrace();
	        }
	       
	        
	      
	    }
}
