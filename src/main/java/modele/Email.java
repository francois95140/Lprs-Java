package modele;

import java.util.Properties;

public class Email {
    private String from;
    private String password;

    private String subject;

    public Email() {

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        String myAccountEmail = "contactlprs.fr@gmail.com";
        String password = "LprsGestion01";


    }

    /*public void sendCode(String to, String subject, String body) {

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
            message.setSubject(subject,"UTF-8");
            message.setText(body, "UTF-8");

            Transport.send(message);
            System.out.println("Message sent successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendUserInfo(){

    }*/

}