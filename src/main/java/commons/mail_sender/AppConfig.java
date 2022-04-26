/** This Spring mail method works correctly
 * but overloads the memory on weak machines.
 * Go to "register" package and see https://temp-mail.io/ as a receiver
 * and https://anonymousemail.me/ as a sender service for the tests within email.
 ***/

/* Dependencies.
  Gradle
 implementation group: 'com.sun.mail', name: 'javax.mail', version: '1.6.2'
 implementation group: 'javax.mail', name: 'javax.mail-api', version: '1.6.2'
 implementation group: 'org.springframework', name: 'spring-context-support', version: '5.3.19'

  Maven
 <dependency>
 <groupId>javax.mail</groupId>
 <artifactId>javax.mail-api</artifactId>
 <version>1.6.2</version>
 </dependency>
 <dependency>
 <groupId>com.sun.mail</groupId>
 <artifactId>javax.mail</artifactId>
 <version>1.6.2</version>
 </dependency>
 <dependency>
 <groupId>org.springframework</groupId>
 <artifactId>spring-context-support</artifactId>
 <version>5.3.19</version>
 </dependency>

package commons.mail_sender;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@ComponentScan("commons.mail_sender")
@Configuration
public class AppConfig {
    @Bean
    private MailSender mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("trellou0@gmail.com");
        mailSender.setPassword("jagzsxlhxtfjiaer"); //this is not an account password. This is accounts.google.com
                                                    //> security > application password.

        Properties properties = new Properties();
        properties.put("mail.transport.protocol","smtp");
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.debug","true");

        mailSender.setJavaMailProperties(properties);

        return mailSender;
    }
}



 */