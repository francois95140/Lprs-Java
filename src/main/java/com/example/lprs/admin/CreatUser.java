package com.example.lprs.admin;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import modele.Passewordgenerator;
import modele.Utilisateur;

import java.net.URL;
import java.util.ResourceBundle;

public class CreatUser implements Initializable {

    private int utilisateur;

    public CreatUser (int u) {
        this.utilisateur = u;
        System.out.println(this.utilisateur);
    }


    @FXML
    private Label Erreur;

    @FXML
    private MFXTextField email;

    @FXML
    private MFXTextField nom;

    @FXML
    private MFXTextField prenom;

    @FXML
    private MFXComboBox<String> role;

    @FXML
    private MFXButton valiez;

    @FXML
    void validez(ActionEvent event) {
        System.out.println(Passewordgenerator.codeGenerate(8));
        System.out.println(role.getItems().indexOf(role.getText())+1);
    }

    private final String[] roles = {
            "Administrateur","Professeur","Secretaire","Gestionair de stock"
    };

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        role.getItems().addAll(roles);
    }
}

