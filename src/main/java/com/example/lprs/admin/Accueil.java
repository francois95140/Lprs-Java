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
import modele.*;
import org.kordamp.ikonli.javafx.FontIcon;
import repository.*;

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
    DemandeRepository demanderepo = new DemandeRepository();
    FicheEtudiantRepository ficherepo = new FicheEtudiantRepository();
    FournitureRepository fourniturerepo = new FournitureRepository();
    UtilisateurRepository userrepo = new UtilisateurRepository();
    LogsRepository logsrepo = new LogsRepository();
    DossierInscriptionRepository dossierepo = new DossierInscriptionRepository();

    @FXML
    private MenuItem deletUser;

    @FXML
    private FontIcon logout;

    @FXML
    private Label nom;

    @FXML
    private Label prenom;

    @FXML
    private Label email;

    @FXML
    private Label role;

    @FXML
    private Tab demande;

    @FXML
    private Tab dossier;

    @FXML
    private Tab fiche;

    @FXML
    private MFXTableView<DossierInscripition> listDossier;

    @FXML
    private MFXTableView<FicheEtudiant> listFiche;

    @FXML
    private MFXTableView<Utilisateur> listPersonnel;

    @FXML
    private MFXTableView<Fourniture> listStock;

    @FXML
    private MFXTableView<Demande> listeDemande;

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
    void onClickLogout(MouseEvent event) {
        this.utilisateur = null;
        RunApplication.changeScene("/com/example/lprs/user/loging");
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
        MFXTableColumn<Utilisateur> numerotColumn = new MFXTableColumn<>("Numérot", true, Comparator.comparing(Utilisateur::getIdUtilisateur));
        MFXTableColumn<Utilisateur> nomColumn = new MFXTableColumn<>("Nom", true, Comparator.comparing(Utilisateur::getNom));
        MFXTableColumn<Utilisateur> prenomColumn = new MFXTableColumn<>("Prénom", true, Comparator.comparing(Utilisateur::getPrenom));
        MFXTableColumn<Utilisateur> emailColum = new MFXTableColumn<>("Email",true, Comparator.comparing(Utilisateur::getEmail));
        MFXTableColumn<Utilisateur> roleColum = new MFXTableColumn<>("Role", true, Comparator.comparing(Utilisateur::getRole));


        numerotColumn.setRowCellFactory(list -> new MFXTableRowCell<>(Utilisateur::getIdUtilisateur));
        nomColumn.setRowCellFactory(list -> new MFXTableRowCell<>(Utilisateur::getNom));
        prenomColumn.setRowCellFactory(list -> new MFXTableRowCell<>(Utilisateur::getPrenom));
        emailColum.setRowCellFactory(list -> new MFXTableRowCell<>(Utilisateur::getEmail));
        roleColum.setRowCellFactory(list -> new MFXTableRowCell<>(Utilisateur::getRole));

        nomColumn.setStyle("-fx-pref-width: 100");
        prenomColumn.setStyle("-fx-pref-width: 100");
        emailColum.setStyle("-fx-pref-width: 150");

        listPersonnel.getTableColumns().add(numerotColumn);
        listPersonnel.getTableColumns().add(nomColumn);
        listPersonnel.getTableColumns().add(prenomColumn);
        listPersonnel.getTableColumns().add(emailColum);
        listPersonnel.getTableColumns().add(roleColum);

        listPersonnel.getFilters().add(new IntegerFilter<>("Numérot", Utilisateur::getIdUtilisateur));
        listPersonnel.getFilters().add(new StringFilter<>("Nom", Utilisateur::getNom));
        listPersonnel.getFilters().add(new StringFilter<>("Prénom", Utilisateur::getPrenom));
        listPersonnel.getFilters().add(new StringFilter<>("Email", Utilisateur::getEmail));
        listPersonnel.getFilters().add(new IntegerFilter<>("Role", Utilisateur::getRole));

        listPersonnel.getItems().addAll(userrepo.getUtilisateurs());

    }
    private void setupDossier(){
        MFXTableColumn <DossierInscripition> numerotDossierColum = new MFXTableColumn<>("Numérot",true,Comparator.comparing(DossierInscripition::getId_dossier));
        MFXTableColumn <DossierInscripition> dateColum = new MFXTableColumn<>("Date",true,Comparator.comparing(DossierInscripition::getDate));
        MFXTableColumn <DossierInscripition> filiereColum = new MFXTableColumn<>("Filiere",true,Comparator.comparing(DossierInscripition::getFiliere));
        MFXTableColumn <DossierInscripition> motivationColum = new MFXTableColumn<>("Motivation",true,Comparator.comparing(DossierInscripition::getMotivation));
        MFXTableColumn <DossierInscripition> eleveColum = new MFXTableColumn<>("Nom Eléve",true,Comparator.comparing(DossierInscripition::getEleve));

        numerotDossierColum.setRowCellFactory(list -> new MFXTableRowCell<>(DossierInscripition::getId_dossier));
        dateColum.setRowCellFactory(list -> new MFXTableRowCell<>(DossierInscripition::getDate));
        filiereColum.setRowCellFactory(list -> new MFXTableRowCell<>(DossierInscripition::getFiliere));
        motivationColum.setRowCellFactory(list -> new MFXTableRowCell<>(DossierInscripition::getMotivation));
        eleveColum.setRowCellFactory(list -> new MFXTableRowCell<>(DossierInscripition::getEleve));

        numerotDossierColum.setStyle("-fx-pref-width: 10");
        dateColum.setStyle("-fx-pref-width: 200");
        motivationColum.setStyle("-fx-pref-width: 300");
        eleveColum.setStyle("-fx-pref-width: 150");

        listDossier.getTableColumns().add(numerotDossierColum);
        listDossier.getTableColumns().add(dateColum);
        listDossier.getTableColumns().add(filiereColum);
        listDossier.getTableColumns().add(motivationColum);
        listDossier.getTableColumns().add(eleveColum);


        listDossier.getFilters().add(new IntegerFilter<>("Numérot",DossierInscripition::getId_dossier));
        listDossier.getFilters().add(new StringFilter<>("Date",DossierInscripition::getDate));
        listDossier.getFilters().add(new StringFilter<>("Filiere",DossierInscripition::getFiliere));
        listDossier.getFilters().add(new StringFilter<>("Motivation",DossierInscripition::getMotivation));
        listDossier.getFilters().add(new StringFilter<>("Nom Eléve",DossierInscripition::getEleve));

        listDossier.getItems().addAll(dossierepo.getdossier());
    }
    private void setupDemnde(){
        MFXTableColumn<Demande> numerotColumn = new MFXTableColumn<>("Numerot", true, Comparator.comparing(Demande::getIdDemande));
        MFXTableColumn<Demande> nomColumn = new MFXTableColumn<>("Fourniture", true, Comparator.comparing(Demande::getNomFourniture));
        MFXTableColumn<Demande> quantiteColum = new MFXTableColumn<>("Qantité",true, Comparator.comparing(Demande::getQuantiter));
        MFXTableColumn<Demande> demanderColum = new MFXTableColumn<>("Demandeur",true, Comparator.comparing(Demande::getNomDemandeur));
        MFXTableColumn<Demande> raisonColum = new MFXTableColumn<>("Raisoin",true, Comparator.comparing(Demande::getRaison));

        numerotColumn.setRowCellFactory(list -> new MFXTableRowCell<>(Demande::getIdDemande));
        nomColumn.setRowCellFactory(list -> new MFXTableRowCell<>(Demande::getNomFourniture));
        quantiteColum.setRowCellFactory(list -> new MFXTableRowCell<>(Demande::getQuantiter));
        demanderColum.setRowCellFactory(list -> new MFXTableRowCell<>(Demande::getNomDemandeur));
        raisonColum.setRowCellFactory(list -> new MFXTableRowCell<>(Demande::getRaison));


        numerotColumn.setStyle("-fx-pref-width: 100");
        nomColumn.setStyle("-fx-pref-width: 100");
        quantiteColum.setStyle("-fx-pref-width: 150");
        demanderColum.setStyle("-fx-pref-width: 120");
        raisonColum.setStyle("-fx-pref-width: 200");


        listeDemande.getTableColumns().add(numerotColumn);
        listeDemande.getTableColumns().add(nomColumn);
        listeDemande.getTableColumns().add(quantiteColum);
        listeDemande.getTableColumns().add(demanderColum);
        listeDemande.getTableColumns().add(raisonColum);

        listeDemande.getFilters().add(new IntegerFilter<>("Numerot", Demande::getIdDemande));
        listeDemande.getFilters().add(new StringFilter<>("Fourniture", Demande::getNomFourniture));
        listeDemande.getFilters().add(new IntegerFilter<>("Qantité", Demande::getQuantiter));
        listeDemande.getFilters().add(new StringFilter<>("Demandeur", Demande::getNomDemandeur));
        listeDemande.getFilters().add(new StringFilter<>("Raisoin", Demande::getRaison));

        listeDemande.getItems().addAll(demanderepo.getDemande());

        listeDemande.getSelectionModel().getSelection();
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
    private void setupFourniture(){
        MFXTableColumn<Fourniture> numerotColumn = new MFXTableColumn<>("Numérot", true, Comparator.comparing(Fourniture::getIdFourniture));
        MFXTableColumn<Fourniture> nomColumn = new MFXTableColumn<>("Nom", true, Comparator.comparing(Fourniture::getNom));
        MFXTableColumn<Fourniture> quantiteColum = new MFXTableColumn<>("Quantiter",true, Comparator.comparing(Fourniture::getStock));

        numerotColumn.setRowCellFactory(list -> new MFXTableRowCell<>(Fourniture::getIdFourniture));
        nomColumn.setRowCellFactory(list -> new MFXTableRowCell<>(Fourniture::getNom));
        quantiteColum.setRowCellFactory(list -> new MFXTableRowCell<>(Fourniture::getStock));


        numerotColumn.setStyle("-fx-pref-width: 100");
        nomColumn.setStyle("-fx-pref-width: 100");
        quantiteColum.setStyle("-fx-pref-width: 150");


        listStock.getTableColumns().add(numerotColumn);
        listStock.getTableColumns().add(nomColumn);
        listStock.getTableColumns().add(quantiteColum);

        listStock.getFilters().add(new IntegerFilter<>("Numérot", Fourniture::getIdFourniture));
        listStock.getFilters().add(new StringFilter<>("Nom", Fourniture::getNom));
        listStock.getFilters().add(new IntegerFilter<>("Quantiter", Fourniture::getStock));

        listStock.getItems().addAll(fourniturerepo.getfourniture());
    }
    private void setupprofil (){
        nom.setText("Nom : "+utilisateur.getNom() );
        prenom.setText("Prenom : "+utilisateur.getPrenom() );
        email.setText("Email : "+utilisateur.getEmail() );
        role.setText("Role : "+utilisateur.getNom() );
    };

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listPersonnel.getSelectionModel().getSelectedValues();
        setupDossier();
        setupDemnde();
        setupFourniture();
        setupPersonnel();
        setupFiche();
        setupLogs();
        setupprofil();
    }

}
