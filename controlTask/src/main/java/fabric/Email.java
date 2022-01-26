package fabric;

import model.Check;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class Email {

    public static void sendingMail() {

        Properties properties = new Properties();
        Session mailSession = Session.getDefaultInstance(properties);
        Transport transport = null;
        try {
            properties.put("mail.transport.protocol", "smtps");
            properties.put("mail.smtps.auth", "true");
            properties.put("mail.smtps.host", "smtp.gmail.com");
            properties.put("mail.smtps.user", "petr27611@gmail.com");

            MimeMessage message = new MimeMessage(mailSession);
            message.setFrom(new InternetAddress("petr27611@gmail.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("7176451@mail.ru"));
            message.setSubject("My Check!!!");
            message.setText(String.format("%s%.2f\n%s%.2f\n%s%.2f\n%s",
                    "Total amount without discount: ", Check.getSumTotal(),
                    "Dscont: ", Check.getDiscontSum(), "TOTAL: ", Check.getFinalAmount(),
                    new Date()));
            transport = mailSession.getTransport();
            transport.connect(null, "rgdbgbn$6745");
            transport.sendMessage(message, message.getAllRecipients());
        } catch (MessagingException e) {
            System.out.println("Not connected!!!");
        } catch (NullPointerException e) {
            System.out.println("Not properties!!!");
        }  finally {
            if (transport != null) {
                try {
                    transport.close();
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
