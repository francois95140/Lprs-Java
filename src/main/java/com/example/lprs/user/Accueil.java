package com.example.lprs.user;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;

public class Accueil {

    @FXML
    private Label accountLabel;

    @FXML
    private Tab demande;

    @FXML
    private MFXTableView<?> eleveListe;

    @FXML
    private MFXTextField listDesField1;

    @FXML
    private Label listLabel1;

    @FXML
    private MFXTextField listLibelleField1;

    @FXML
    private MFXTableView<?> listStock;

    @FXML
    private MFXTableView<?> listeDemande;

    @FXML
    private Tab listeEleve;

    @FXML
    private Tab profil;

    @FXML
    private Label stateLabel;

    @FXML
    private MFXTextField stateLibelleField;

    @FXML
    private Tab stokck;

    @FXML
    private Label typeLabel;

    @FXML
    private MFXTextField typeLibelleField;

    @FXML
    private MFXComboBox<?> typeParentTypeComboBox;

    @FXML
    private MFXButton typeSubmitButton;

    @FXML
    void onListSubmitButtonClick(ActionEvent event) {

    }

    @FXML
    void onListUserSubmitButtonClick(ActionEvent event) {

    }

    @FXML
    void onLogoutButtonClick(ActionEvent event) {

    }

    @FXML
    void onRowClick(MouseEvent event) {

    }

    @FXML
    void onStateSubmitButtonClick(ActionEvent event) {

    }

    @FXML
    void onTypeSubmitButtonClick(ActionEvent event) {

    }

    @FXML
    void onTypeSwitchButtonClick(ActionEvent event) {

    }

}