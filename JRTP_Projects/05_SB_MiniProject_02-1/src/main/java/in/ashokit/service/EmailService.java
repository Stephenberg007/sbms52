package in.ashokit.service;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import in.ashokit.entity.UserEntity;
import in.ashokit.repo.UserRepo;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;
    
    @Autowired
    private UserRepo userRepo;

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
    }/*CHARACTERS.length() gives you the total number of possible characters.
	  random.nextInt(CHARACTERS.length()) generates a random integer between 0 (inclusive) and CHARACTERS.length() (exclusive). 
	  So, if CHARACTERS has 62 characters, this will give you a random number from 0 to 61. 
	  This random number is used as an index into the CHARACTERS string.
    
    *
    */


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
        mailSender.send(message);

       // System.out.println("Email sent successfully to " + toEmail + " with password: " + randomPassword);
       
        // saving password in Repo
        
        UserEntity user = userRepo.findByUserEmail(toEmail);
        user.setUserPwd(randomPassword);
        //user.setPwdUpdated("No");// I can do it directly in entity i.e saving the default value as NO there itself
        userRepo.save(user);
        
    }
    
    
}
