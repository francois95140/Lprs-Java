package com.example.lprs.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import modele.Utilisateur;
import repository.UtilisateurRepository;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class Accueil implements Initializable {

    @FXML
    private TableView<Utilisateur> tbl;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnModif;

    private Utilisateur utilisateur;

    public Accueil(Utilisateur u) {
        this.utilisateur = u;
        System.out.println(this.utilisateur);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[][] colonnes = {
                {"Id user","idUtilisateur"},
                {"Nom","nom"},
                {"Prenom","prenom"},
                {"Mail","email"},
                {"Role","role"}
        };
        for (int i = 0 ; i < colonnes.length ; i ++){
            TableColumn<Utilisateur,String> myTable= new TableColumn<>(colonnes[i][0]);
            myTable.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>(colonnes[i][1]));
            tbl.getColumns().add(myTable);
        }
        UtilisateurRepository userRepository = new UtilisateurRepository();

        tbl.getItems().addAll(userRepository.getUtilisateurs());

        System.out.println(this.utilisateur.getRole());

        if (!this.utilisateur.getRole().equals("1")){
            btnAdd.setDisable(true);
        }
    }

    @FXML
    void AddAction(ActionEvent event) {

    }

    @FXML
    void delAction(ActionEvent event) throws SQLException {

    }

    @FXML
    void editAction(ActionEvent event) {

    }

    @FXML
    void onSelIntem(MouseEvent event) {
    }

    @FXML
    void menuAction(ActionEvent event) {
        System.out.println("hey !");
    }

    @FXML
    void deconnecterAction(ActionEvent event) {
    }
    @FXML
    void editPasswordAction(ActionEvent event) {
    }

    @FXML
    void editUserAction(ActionEvent event) {

    }
}
