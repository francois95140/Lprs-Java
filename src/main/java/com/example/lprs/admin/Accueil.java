package com.example.lprs.admin;

import com.example.lprs.RunApplication;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import modele.Passewordgenerator;
import modele.Utilisateur;
import repository.UtilisateurRepository;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Accueil implements Initializable {

    private Utilisateur utilisateur;

    public Accueil (Utilisateur u) {
        this.utilisateur = u;
        System.out.println(this.utilisateur);
    }

    @FXML
    private MenuItem deletUser;

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
    private MenuItem newUser;

    @FXML
    private Tab personnel;

    @FXML
    private Tab profil;

    @FXML
    private Tab stokck;

    @FXML
    void onClickDeletUser(ActionEvent event) {
        Passewordgenerator passe =new Passewordgenerator();
        System.out.println("flag");
    }

    @FXML
    void onClickNewUser(ActionEvent event) {
        if (utilisateur.getRole().equals("1")){
            RunApplication.changeScene("/com/example/lprs/admin/creat-user",new CreatUser(utilisateur.getIdUtilisateur()));
            System.out.println(utilisateur.getIdUtilisateur());
        }

    }

    @FXML
    void onLogoutButtonClick(ActionEvent event) {

    }

    @FXML
    void onRowClick(MouseEvent event) {

    }

    @FXML
    private MFXComboBox<String> select;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
