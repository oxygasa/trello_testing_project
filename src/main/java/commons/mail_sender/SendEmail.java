/** This Spring mail class works correctly
 * but overloads the memory on weak machines.
 * Go to register package and see https://temp-mail.io/ as a receiver
 * And https://anonymousemail.me/ as a sender service for the email.
 ***/

/*
package commons.mail_sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class SendEmail {

    /*** If you want to send email by this method in main() or any test
     * write this:
     * AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
     * SendEmail bean = context.getBean(SendEmail.class);
     * bean.sendEmail();
     ***/
/*
    @Autowired
    private MailSender mailSender;

    public void sendMail(String setMailTo, String setTitle, String setBodyText){

        SimpleMailMessage msg = new SimpleMailMessage();

        msg.setTo(setMailTo);
        msg.setFrom("trellou0@gmail.com");
        msg.setSubject(setTitle);
        msg.setText(setBodyText);

        mailSender.send(msg);
    }

}

*/