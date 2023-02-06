package com.example.lprs;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

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
    private MFXPasswordField confirPasseword;

    @FXML
    private MFXPasswordField passeword;

    @FXML
    void sendEmail(ActionEvent event) {
        Validee.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                userName.setVisible(false);
                passeword.setVisible(false);
                confirPasseword.setVisible(false);
                code.setVisible(true);
                message.setText("Rensegnez le code reçu par mail");
                titre.setText("Confirmation");

                Validee.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        titre.setText("Nouveau Mot De Pesse");
                        message.setText("Rentreé votre nouveau mot de passe puis confirmez");
                        code.setVisible(false);
                        userName.setVisible(false);
                        passeword.setVisible(true);
                        confirPasseword.setVisible(true);
                        passeword.setFloatingText("Mot De Passe");
                        confirPasseword.setFloatingText("Confirmation");
                    }
                });
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
