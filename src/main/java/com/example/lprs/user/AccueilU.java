package com.example.lprs.user;

import com.example.lprs.RunApplication;
import com.example.lprs.admin.CreatUser;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTableView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;
import modele.FicheEtudiant;
import modele.Utilisateur;

import java.net.URL;
import java.util.ResourceBundle;

public class AccueilU implements Initializable {

    private Utilisateur utilisateur;
    private FicheEtudiant ficheEtudiant;

    public AccueilU(Utilisateur u) {
        this.utilisateur = u;
    }

    @FXML
    private MenuItem deletFiche;

    @FXML
    private Tab demande;

    @FXML
    private Tab dossier;

    @FXML
    private MFXTableView<?> eleveListe;

    @FXML
    private MFXTableView<?> eleveListe1;

    @FXML
    private MFXTableView<?> listStock;

    @FXML
    private MFXTableView<?> listeDemande;

    @FXML
    private Tab listeEleve;

    @FXML
    private MFXButton modifMyAccont;

    @FXML
    private MenuItem newFiche;

    @FXML
    private Tab profil;

    @FXML
    private Tab stokck;

    @FXML
    void modifMyAccont(ActionEvent event) {
        RunApplication.changeScene("/com/example/lprs/admin/creat-user",new CreatUser(utilisateur,utilisateur));
    }

    @FXML
    void onClickDeletFiche(ActionEvent event) {
        System.out.println("no");
    }

    @FXML
    void onClickNewFiche(ActionEvent event) {
        if(utilisateur.getRole()==3){
            RunApplication.changeScene("/com/example/lprs/user/FicheEtudiant",new FicheControler(utilisateur));
        }
    }

    @FXML
    void onLogoutButtonClick(ActionEvent event) {

    }

    @FXML
    void onRowClick(MouseEvent event) {

    }

    @FXML
    void onClickDossierInscription(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(utilisateur.getRole()==3){
            demande.setDisable(true);
            stokck.setDisable(true);

        }
        else if (utilisateur.getRole()==4){
            listeEleve.setDisable(true);
            dossier.setDisable(true);
        }
        else if(utilisateur.getRole()==2){
            listeEleve.setDisable(true);
            stokck.setDisable(true);
            deletFiche.setText("Demande");
            deletFiche.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {

                }
            });
        }

    }
}