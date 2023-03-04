package com.example.lprs.admin;

import com.example.lprs.RunApplication;
import io.github.palexdev.materialfx.controls.*;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.IntegerFilter;
import io.github.palexdev.materialfx.filter.StringFilter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import modele.FicheEtudiant;
import modele.Logs;
import modele.Utilisateur;
import repository.FicheEtudiantRepository;
import repository.LogsRepository;
import repository.UtilisateurRepository;

import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

public class Accueil implements Initializable {

    private Utilisateur utilisateur;
    private Utilisateur seconduser;

    public Accueil (Utilisateur u) {
        this.utilisateur = u;
        System.out.println(this.utilisateur);
    }

    FicheEtudiantRepository ficherepo = new FicheEtudiantRepository();
    UtilisateurRepository userrepo = new UtilisateurRepository();
    LogsRepository logsrepo = new LogsRepository();

    @FXML
    private MenuItem deletUser;

    @FXML
    private Tab demande;

    @FXML
    private Tab dossier;

    @FXML
    private Tab fiche;

    @FXML
    private MFXTableView<?> listDossier;

    @FXML
    private MFXTableView<FicheEtudiant> listFiche;

    @FXML
    private MFXTableView<Utilisateur> listPersonnel;

    @FXML
    private MFXTableView<?> listStock;

    @FXML
    private MFXTableView<?> listeDemande;

    @FXML
    private MFXTableView<Logs> listLogs;


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
    void onClickDeletUser(ActionEvent event) {
        System.out.println("flag");
    }

    @FXML
    void modifMyAccont(ActionEvent event) {
        RunApplication.changeScene("/com/example/lprs/admin/creat-user",new CreatUser(utilisateur,utilisateur));
        System.out.println(utilisateur.getIdUtilisateur());
    }

    @FXML
    void onClickNewUser(ActionEvent event) {
        if (utilisateur.getRole() == 1 ){
            RunApplication.changeScene("/com/example/lprs/admin/creat-user",new CreatUser(utilisateur));
            System.out.println(utilisateur.getIdUtilisateur());
        }

    }

    @FXML
    void onLogoutButtonClick(ActionEvent event) {

    }

    @FXML
    void onRowClick(MouseEvent event) {
        System.out.println(listPersonnel.getSelectionModel().getSelection());
    }

    private void setupFiche() {
        MFXTableColumn<FicheEtudiant> nomColumn = new MFXTableColumn<>("Nom", true, Comparator.comparing(FicheEtudiant::getNom));
        MFXTableColumn<FicheEtudiant> prenomColumn = new MFXTableColumn<>("Prénom", true, Comparator.comparing(FicheEtudiant::getPrenom));
        MFXTableColumn<FicheEtudiant> emailColum = new MFXTableColumn<>("Email",true, Comparator.comparing(FicheEtudiant::getEmail));
        MFXTableColumn<FicheEtudiant> telColum = new MFXTableColumn<>("Téléphone", true, Comparator.comparing(FicheEtudiant::getTelephone));
        MFXTableColumn<FicheEtudiant> diplomeColum = new MFXTableColumn<>("Doplôme", true, Comparator.comparing(FicheEtudiant::getDiplome));
        MFXTableColumn<FicheEtudiant> adresseColum = new MFXTableColumn<>("Adresse", true, Comparator.comparing(FicheEtudiant::getAdresse));


        nomColumn.setRowCellFactory(list -> new MFXTableRowCell<>(FicheEtudiant::getNom));
        prenomColumn.setRowCellFactory(list -> new MFXTableRowCell<>(FicheEtudiant::getPrenom));
        emailColum.setRowCellFactory(list -> new MFXTableRowCell<>(FicheEtudiant::getEmail));
        telColum.setRowCellFactory(list -> new MFXTableRowCell<>(FicheEtudiant::getTelephone));
        diplomeColum.setRowCellFactory(list -> new MFXTableRowCell<>(FicheEtudiant::getDiplome));
        adresseColum.setRowCellFactory(list -> new MFXTableRowCell<>(FicheEtudiant::getAdresse));

        nomColumn.setStyle("-fx-pref-width: 100");
        prenomColumn.setStyle("-fx-pref-width: 100");
        emailColum.setStyle("-fx-pref-width: 150");
        telColum.setStyle("-fx-pref-width: 100");
        diplomeColum.setStyle("-fx-pref-width: 100");
        adresseColum.setStyle("-fx-pref-width: 200");

        listFiche.getTableColumns().add(nomColumn);
        listFiche.getTableColumns().add(prenomColumn);
        listFiche.getTableColumns().add(emailColum);
        listFiche.getTableColumns().add(telColum);
        listFiche.getTableColumns().add(diplomeColum);
        listFiche.getTableColumns().add(adresseColum);

        listFiche.getFilters().add(new StringFilter<>("Nom", FicheEtudiant::getNom));
        listFiche.getFilters().add(new StringFilter<>("Prénom", FicheEtudiant::getPrenom));
        listFiche.getFilters().add(new StringFilter<>("Email", FicheEtudiant::getEmail));
        listFiche.getFilters().add(new IntegerFilter<>("Téléphone", FicheEtudiant::getTelephone));
        listFiche.getFilters().add(new IntegerFilter<>("Diplôme", FicheEtudiant::getTelephone));
        listFiche.getFilters().add(new StringFilter<>("Adresse", FicheEtudiant::getAdresse));

        listFiche.getItems().addAll(ficherepo.getFiche());

    }
    private void setupPersonnel() {
        MFXTableColumn<Utilisateur> nomColumn = new MFXTableColumn<>("Nom", true, Comparator.comparing(Utilisateur::getNom));
        MFXTableColumn<Utilisateur> prenomColumn = new MFXTableColumn<>("Prénom", true, Comparator.comparing(Utilisateur::getPrenom));
        MFXTableColumn<Utilisateur> emailColum = new MFXTableColumn<>("Email",true, Comparator.comparing(Utilisateur::getEmail));
        MFXTableColumn<Utilisateur> roleColum = new MFXTableColumn<>("Role", true, Comparator.comparing(Utilisateur::getRole));


        nomColumn.setRowCellFactory(list -> new MFXTableRowCell<>(Utilisateur::getNom));
        prenomColumn.setRowCellFactory(list -> new MFXTableRowCell<>(Utilisateur::getPrenom));
        emailColum.setRowCellFactory(list -> new MFXTableRowCell<>(Utilisateur::getEmail));
        roleColum.setRowCellFactory(list -> new MFXTableRowCell<>(Utilisateur::getRole));

        nomColumn.setStyle("-fx-pref-width: 100");
        prenomColumn.setStyle("-fx-pref-width: 100");
        emailColum.setStyle("-fx-pref-width: 150");

        listPersonnel.getTableColumns().add(nomColumn);
        listPersonnel.getTableColumns().add(prenomColumn);
        listPersonnel.getTableColumns().add(emailColum);
        listPersonnel.getTableColumns().add(roleColum);

        listPersonnel.getFilters().add(new StringFilter<>("Nom", Utilisateur::getNom));
        listPersonnel.getFilters().add(new StringFilter<>("Prénom", Utilisateur::getPrenom));
        listPersonnel.getFilters().add(new StringFilter<>("Email", Utilisateur::getEmail));
        listPersonnel.getFilters().add(new IntegerFilter<>("Role", Utilisateur::getRole));

        listPersonnel.getItems().addAll(userrepo.getUtilisateurs());

    }
    private void setupLogs() {
        MFXTableColumn<Logs> idlogsColumn = new MFXTableColumn<>("Numerot", true, Comparator.comparing(Logs::getId_logs));
        MFXTableColumn<Logs> refComptColumn = new MFXTableColumn<>("Numerot Compte", true, Comparator.comparing(Logs::getRef_compte));
        MFXTableColumn<Logs> dateColum = new MFXTableColumn<>("Date",true, Comparator.comparing(Logs::getDate));
        MFXTableColumn<Logs> adresseIpColum = new MFXTableColumn<>("Adresse Ip", true, Comparator.comparing(Logs::getAdresse_ip));


        idlogsColumn.setRowCellFactory(list -> new MFXTableRowCell<>(Logs::getId_logs));
        refComptColumn.setRowCellFactory(list -> new MFXTableRowCell<>(Logs::getRef_compte));
        dateColum.setRowCellFactory(list -> new MFXTableRowCell<>(Logs::getDate));
        adresseIpColum.setRowCellFactory(list -> new MFXTableRowCell<>(Logs::getAdresse_ip));


        adresseIpColum.setStyle("-fx-pref-width: 150");
        dateColum.setStyle("-fx-pref-width: 150");

        listLogs.getTableColumns().add(idlogsColumn);
        listLogs.getTableColumns().add(refComptColumn);
        listLogs.getTableColumns().add(dateColum);
        listLogs.getTableColumns().add(adresseIpColum);

        listLogs.getFilters().add(new IntegerFilter<>("Numerot", Logs::getId_logs));
        listLogs.getFilters().add(new IntegerFilter<>("Numerot Compte", Logs::getRef_compte));
        listLogs.getFilters().add(new StringFilter<>("Date", Logs::getDate));
        listLogs.getFilters().add(new StringFilter<>("Adresse Ip", Logs::getAdresse_ip));

        listLogs.getItems().addAll(logsrepo.getLogs());

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupPersonnel();
        setupFiche();
        setupLogs();
    }

}
