package com.example.lprs.user;

import com.example.lprs.RunApplication;
import javafx.fxml.FXML;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import modele.DossierInscripition;
import modele.FicheEtudiant;
import modele.Utilisateur;
import repository.DossierInscriptionRepository;

import java.sql.SQLException;

public class CreatDossier {
    DossierInscriptionRepository dosrypo = new DossierInscriptionRepository();
    int ref;
    Utilisateur userconect;
    public CreatDossier (FicheEtudiant fiche, Utilisateur user){
        this.ref = fiche.getIdFiche();
        this.userconect = user;
    }
    @FXML
    private Label Erreur;

    @FXML
    private MFXTextField filiere;

    @FXML
    private MFXTextField motivation;

    @FXML
    private MFXButton validee;

    @FXML
    void validee(ActionEvent event) throws SQLException {
        System.out.println(ref);
        if(!filiere.getText().isEmpty() || !motivation.getText().isEmpty()){
            DossierInscripition newdos = new DossierInscripition(filiere.getText(),motivation.getText(),ref);
            dosrypo.inscription(newdos);
            RunApplication.changeScene("/com/example/lprs/user/AccueilU",new AccueilU(userconect));
        }else {
            Erreur.setText("Un ou plusieur champ vide");
        }
    }
}
