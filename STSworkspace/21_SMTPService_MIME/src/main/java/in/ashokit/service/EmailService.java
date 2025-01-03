package in.ashokit.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmailWithAttachment(String toEmail,
                                        String subject,
                                        String body)
                                         throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.setText(body);
        helper.setFrom("krmauryaaman@gmail.com");

        // Add attachment
      //  FileSystemResource file = new FileSystemResource(new File("C:\\Users\\Aman\\Desktop\\motivation\\download.jpg"));
       
       // helper.addAttachment(file.getFilename(), file);
        Resource resource = new ClassPathResource("download.jpg");
        helper.addAttachment(resource.getFilename(), resource);


        mailSender.send(message);

        System.out.println("Mail sent successfully!");
    }
}
