package in.ashokit.service;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import in.ashokit.entity.Customer;
import in.ashokit.repo.CustomerRepo;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;
    
    @Autowired
    private CustomerRepo custRepo;
    
    @Autowired
    @Lazy
    BCryptPasswordEncoder pwdEncoder;
    
   
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int PASSWORD_LENGTH = 8;

   
    private String generateRandomPassword() {
        SecureRandom random = new SecureRandom(); // Step 1: Initialize a SecureRandom instance
        StringBuilder password = new StringBuilder(PASSWORD_LENGTH); // Step 2: Prepare a container for the password

        for (int i = 0; i < PASSWORD_LENGTH; i++) { // Step 3: Loop to generate `PASSWORD_LENGTH` characters
            int index = random.nextInt(CHARACTERS.length()); // Step 4: Randomly pick an index within the CHARACTERS string
            password.append(CHARACTERS.charAt(index)); // Step 5: Add the character at the random index to the password
        }

        return password.toString(); // Step 6: Convert the StringBuilder to a String and return it
    }


    public void sendEmailWithRandomPassword(String toEmail) {
        // Generate random password
        String randomPassword = generateRandomPassword();

        // Prepare email content
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Welcome! Your Registration Password");
        message.setText("Dear User,\n\nYour account has been successfully created.\n" +
                        "Here is your temporary password: " + randomPassword + "\n\n" +
                        "Please change your password after your first login.\n\n" +
                        "Regards,\nYour Team");

        // Send email
        try {
        mailSender.send(message);
        }catch(Exception e){
        	e.printStackTrace();
        }
       // System.out.println("Email sent successfully to " + toEmail + " with password: " + randomPassword);
       
        // saving password in Repo
        
       Customer customer = custRepo.findByCustEmail(toEmail);
       String encodedPwd= pwdEncoder.encode(randomPassword);
       customer.setPwd(encodedPwd);
       customer.setPwdUpdated("no");
       custRepo.save(customer);
        
    }
    
    public void sendEmailForgotPwd(String toEmail) {
        
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Forgot Password");
        message.setText("Dear User,\n\nWe recieved a Request to set a new Password.\n" +
                        "Here is your  password reset Link " + "" + "\n\n" +
                        "In case You have Not sent the request . Please ignore the Message . No changes have been Done till now.\n\n" +
                        "Regards,\nYour Team");

        // Send email
        try {
            mailSender.send(message);
            }catch(Exception e){
            	e.printStackTrace();
            }

    }    
    
}
