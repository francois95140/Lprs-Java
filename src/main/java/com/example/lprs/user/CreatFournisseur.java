package com.example.lprs.user;
import com.example.lprs.RunApplication;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import modele.Fournisseur;
import modele.Utilisateur;
import repository.FournisseurRepository;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CreatFournisseur implements Initializable {

    Utilisateur userconnect;

    public CreatFournisseur(Utilisateur user){
        this.userconnect = user;
    }

    @FXML
    private Label Erreur;

    @FXML
    private MFXTextField cp;

    @FXML
    private MFXTextField nom;

    @FXML
    private MFXTextField rue;

    @FXML
    private MFXButton validee;

    @FXML
    private MFXTextField ville;

    @FXML
    void retoure(MouseEvent event) {
        RunApplication.changeScene("/com/example/lprs/user/accueilU", new AccueilU(userconnect));
    }

    @FXML
    void validee(ActionEvent event) throws SQLException {
        if (!nom.getText().isBlank() || !rue.getText().isBlank() || !cp.getText().isBlank() || !ville.getText().isBlank() ){
            FournisseurRepository fnrepo = new FournisseurRepository();
            Fournisseur newfn = new Fournisseur(nom.getText(),rue.getText(),Integer.parseInt(cp.getText()),ville.getText());
            fnrepo.Insert(newfn);
            RunApplication.changeScene("/com/example/lprs/user/accueilU", new AccueilU(userconnect));
        }else {
            Erreur.setText("Un ou plusieur champ vide");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
