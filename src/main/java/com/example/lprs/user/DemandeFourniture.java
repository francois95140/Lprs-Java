package com.example.lprs.user;


import com.example.lprs.RunApplication;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.filter.StringFilter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import modele.Fourniture;
import modele.Utilisateur;
import repository.FournitureRepository;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class DemandeFourniture implements Initializable {

    Utilisateur userconnect;

    FournitureRepository fournirepo = new FournitureRepository();

    public DemandeFourniture(Utilisateur user) {
        this.userconnect=user;
    }

    @FXML
    private MFXFilterComboBox<Fourniture> fourniture;
    @FXML
    private Label Erreur;

    @FXML
    private MFXTextField quantite;

    @FXML
    private MFXButton validee;

    @FXML
    void retoure(MouseEvent event) {
        RunApplication.changeScene("/com/example/lprs/user/accueilU",new AccueilU(userconnect));
    }

    @FXML
    void validee(ActionEvent event) {

    }

    public void setupFourniture(){
        ArrayList<Fourniture> fournitures = fournirepo.getfourniture();

        fourniture.getItems().clear();


        fourniture.getItems().addAll(fournitures);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
