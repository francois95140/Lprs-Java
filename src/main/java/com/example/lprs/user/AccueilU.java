package com.example.lprs.user;

import com.example.lprs.RunApplication;
import com.example.lprs.admin.CreatUser;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTableView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
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
    public AccueilU(Utilisateur u,FicheEtudiant fiche) {
        this.utilisateur = u;
        this.ficheEtudiant = fiche;
    }

    @FXML
    private MenuItem deletFiche;

    @FXML
    private MenuItem newFiche;

    @FXML
    private Tab demande;

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
    private MenuItem newUser;

    @FXML
    private Tab personnel;

    @FXML
    private Tab profil;

    @FXML
    private Tab stokck;

    @FXML
    void modifMyAccont(ActionEvent event) {
        RunApplication.changeScene("/com/example/lprs/admin/creat-user",new CreatUser(utilisateur,utilisateur));
    }

    @FXML
    void onRowClick(MouseEvent event) {

    }

    @FXML
    void onClickDeletFiche(ActionEvent event) {

    }

    @FXML
    void onClickNewFiche(ActionEvent event) {
        RunApplication.changeScene("/com/example/lprs/user/FicheEtudiant",new FicheControler(utilisateur));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
