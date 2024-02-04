package modele;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Emailer {
    private String mail;
    private String code;

    public void MailerLost(String mail,String code){
        this.mail = mail;
        this.code=code;
    }
    public void main() {

        // Configuration des propriétés du serveur de courrier sortant
        Properties properties = System.getProperties();
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.user", "YourMail@gmail.com");
        properties.put("mail.smtp.password", "YourPassword");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");

        // Obtention d'une session de messagerie
        Session session = Session.getDefaultInstance(properties);

        try {
            // Création d'un nouveau message
            MimeMessage message = new MimeMessage(session);

            // Définition de l'expéditeur et du destinataire
            message.setFrom(new InternetAddress("YourMail@gmail.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(this.mail));

            // Définition du sujet et du corps du message
            message.setSubject("Code vérification");
            message.setText("Ceci est un mail automatique \n Vous avez demandé une réinitialisation de votre mot de passe \n \n Voici le code automatique : " + this.code);

            // Envoi du message
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", "YourMail@gmail.com", "Code Application GMAIL");
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            System.out.println("Ceci est un mail automatique \n Vous avez demandé une réinitialisation de votre mot de passe \n \n Voici le code automatique : " + this.code);
        } catch (AddressException ae) {
            ae.printStackTrace();
        } catch (MessagingException me) {
            me.printStackTrace();
        }
    }

}
