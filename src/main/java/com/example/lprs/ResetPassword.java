package com.example.lprs;

import com.example.lprs.admin.Accueil;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import modele.Passewordgenerator;
import modele.Utilisateur;
import repository.UtilisateurRepository;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

public class ResetPassword implements Initializable {

    private boolean isConnected;
    private Utilisateur userSelected;

    ResetPassword(Utilisateur userSelected, boolean isConnected) {
        this.userSelected = userSelected;
        this.isConnected = isConnected;
    }

    @FXML
    private MFXButton Validee;

    @FXML
    private MFXTextField code;

    @FXML
    private Label message;

    @FXML
    private Label titre;

    @FXML
    private MFXTextField userName;

    @FXML
    private MFXPasswordField confirPasseword = null;

    @FXML
    private MFXPasswordField passeword = null;

    @FXML
    void sendEmail(ActionEvent event) {
        UtilisateurRepository utilisateurRepository = new UtilisateurRepository();
        userSelected = utilisateurRepository.getUserByMail(userName.getText());
        System.out.println(userName.getText());
        System.out.println(userSelected);
        userSelected.setCode(Passewordgenerator.codeGenerate(4));
        System.out.println(userSelected.getEmail());
        System.out.println(userSelected.getCode());
        System.out.println("good");
        /*Email email = new Email();
        email.sendCode(userSelected.getEmail(),userSelected.getCode());
        final String fromEmail = "contactlprs.fr@gmail.com"; //requires valid gmail id
        final String password = "LprsGestion01"; // correct password for gmail id
        final String toEmail = userSelected.getEmail(); // can be any email id

        System.out.println("TLSEmail Start");
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
        //create Authenticator object to pass in Session.getInstance argument
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };
        Session session = Session.getInstance(props, auth);*/

        //sendEmail(session, toEmail,"TLSEmail Testing Subject", "TLSEmail Testing Body with this code : "+userSelected.getCode());

        userName.setVisible(false);
        passeword.setVisible(false);
        confirPasseword.setVisible(false);
        code.setVisible(true);
        message.setText("Rensegnez le code reçu par mail");
        titre.setText("Confirmation");

        Validee.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (code.getText().equals(userSelected.getCode())) {
                    System.out.println("reussi");
                    titre.setText("Nouveau Mot De Pesse");
                    message.setText("Rentreé votre nouveau mot de passe puis confirmez");
                    code.setVisible(false);
                    userName.setVisible(false);
                    passeword.setVisible(true);
                    confirPasseword.setVisible(true);
                    passeword.setFloatingText("Mot De Passe");
                    confirPasseword.setFloatingText("Confirmation");
                    Validee.setOnAction(new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent actionEvent) {

                            if (Objects.equals(passeword.getText(), confirPasseword.getText()) && passeword.getText() != null && confirPasseword.getText() != null) {
                                userSelected.setMdp(confirPasseword.getText());
                                try {
                                    utilisateurRepository.changepasseword(userSelected);
                                    if (!isConnected){
                                        //reconnexion auto
                                        Utilisateur u = utilisateurRepository.connexion(userSelected.getEmail(),userSelected.getMdp());
                                        if(u != null){
                                            if (u.getRole().equals("1")){
                                                RunApplication.changeScene("/com/example/lprs/admin/accueil",new Accueil(u));
                                                System.out.println("Admin connecte");
                                            } else {
                                                RunApplication.changeScene("/com/example/lprs/user/accueil",new Accueil(u));
                                                System.out.println("User connecte");
                                            }
                                        }
                                    }
                                    if (isConnected){
                                        RunApplication.changeScene("/com/example/lprs/admin/accueil");
                                    }
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }
                                System.out.println("Identic passeword ");
                            } else {
                                message.setText("Le mot de passe correspond pas a la confirmation");
                                message.setStyle("-fx-text-fill: red");
                                passeword.setStyle("-fx-border-color: red");
                                confirPasseword.setStyle("-fx-border-color: red;");
                            }
                        }
                    });
                } else {
                    message.setStyle("-fx-text-fill: red ");
                    message.setText("Code incorrect");
                } ;
            }
        });
    }


    private void sendEmail(Session session, String toEmail, String subject, String body){
        try
        {
            MimeMessage msg = new MimeMessage(session);
            //set message headers
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress("lemoine.sebastien15@gmail.com", "NoReply-JD"));

            msg.setReplyTo(InternetAddress.parse("lemoine.sebastien15@gmail.com", false));

            msg.setSubject(subject, "UTF-8");

            msg.setText(body, "UTF-8");

            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
            System.out.println("Message is ready");
            Transport.send(msg);

            System.out.println("EMail Sent Successfully!!");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        passeword.setVisible(false);
        confirPasseword.setVisible(false);
        code.setVisible(false);
        message.setText("Saisire votre adresse mail");
    }
}
