package com.example.lprs.user;


import com.example.lprs.RunApplication;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.filter.StringFilter;
import io.github.palexdev.materialfx.utils.others.FunctionalStringConverter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import modele.Demande;
import modele.Fourniture;
import modele.Utilisateur;
import repository.DemandeRepository;
import repository.FournitureRepository;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;


public class DemandeFourniture implements Initializable {

    Utilisateur userconnect;

    Demande selectdemande;

    FournitureRepository fournirepo = new FournitureRepository();

    public DemandeFourniture(Utilisateur user) {
        this.userconnect = user;
    }
    public DemandeFourniture(Utilisateur user, Demande demande) {
        this.userconnect = user;
        this.selectdemande = demande;
    }

    @FXML
    private MFXFilterComboBox<Fourniture> fourniture;
    @FXML
    private Label Erreur;

    @FXML
    private MFXTextField raisons;

    @FXML
    private MFXTextField quantite;

    @FXML
    private MFXButton validez;

    @FXML
    void onClickValidez(ActionEvent event) {
        if(selectdemande==null){
            System.out.println("fourni");
            System.out.println(userconnect.getNom());
            if (fourniture.getSelectedItem().getNom().isBlank() || quantite.getText().isBlank() || raisons.getText().isBlank()){
                Erreur.setText("Un ou plusieur cham vide ");
            }else {
                try {
                    Demande newtdemande = new Demande(fourniture.getSelectedItem().getNom(),Integer.parseInt(quantite.getText()),userconnect.getIdUtilisateur(),raisons.getText());
                    DemandeRepository dm = new DemandeRepository();
                    dm.insert(newtdemande);
                    System.out.println(newtdemande.getRaison());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }

    @FXML
    void retoure(MouseEvent event) {
        RunApplication.changeScene("/com/example/lprs/user/accueilU", new AccueilU(userconnect));
    }

    public void setupFourniture() {

        fourniture.getItems().addAll(fournirepo.getfourniture());
        fourniture.setConverter(FunctionalStringConverter.to(fourniture1 -> (fourniture1 == null) ? "Nom" : fourniture1.getNom()));
        /*fourniture.getFilterList().add(new StringFilter<>("Nom",Fourniture::getNom));*/
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupFourniture();
    }
}
