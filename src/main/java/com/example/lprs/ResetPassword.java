package com.example.lprs;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.paint.Paint;
import modele.Passewordgenerator;
import modele.Utilisateur;
import repository.UtilisateurRepository;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.function.Function;

public class ResetPassword implements Initializable {

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

    private Utilisateur userSelected;

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        passeword.setVisible(false);
        confirPasseword.setVisible(false);
        code.setVisible(false);
        message.setText("Saisire votre adresse mail");
    }
}
