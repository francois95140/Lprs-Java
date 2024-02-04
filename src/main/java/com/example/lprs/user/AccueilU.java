package com.example.lprs.user;

import com.example.lprs.RunApplication;
import com.example.lprs.admin.CreatUser;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.IntegerFilter;
import io.github.palexdev.materialfx.filter.StringFilter;
import io.github.palexdev.materialfx.utils.StringUtils;
import io.github.palexdev.materialfx.utils.others.FunctionalStringConverter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;
import modele.*;
import repository.DemandeRepository;
import repository.DossierInscriptionRepository;
import repository.FicheEtudiantRepository;
import repository.FournitureRepository;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.function.Predicate;

public class AccueilU implements Initializable {

    FicheEtudiantRepository ficherepo = new FicheEtudiantRepository();
    DemandeRepository demanderepo = new DemandeRepository();
    FournitureRepository fourniturerepo = new FournitureRepository();
    DossierInscriptionRepository dossierepo = new DossierInscriptionRepository();

    private Utilisateur utilisateur;
    private FicheEtudiant ficheEtudiant;

    public AccueilU(Utilisateur u) {
        this.utilisateur = u;
    }

    @FXML
    private MenuItem deletFiche;

    @FXML
    private MFXButton entretien;

    @FXML
    private MenuItem newFiche;

    @FXML
    private Tab demande;

    @FXML
    private Tab dossier;

    @FXML
    private Tab ficheEleve;

    @FXML
    private MFXButton modifierStock;

    @FXML
    private MFXButton supprimerStock;

    @FXML
    private MFXButton ajouterStock;

    @FXML
    private MFXButton creeDossier;

    @FXML
    private MFXTableView<Fourniture> listStock;

    @FXML
    private MFXTableView<Demande> listDemande;

    @FXML
    private MFXTableView<DossierInscripition> listDossier;


    @FXML
    private MFXTableView<FicheEtudiant> listEleve;

    @FXML
    private MFXButton modifMyAccont;

    @FXML
    private MenuItem newUser;

    @FXML
    private Button demandeFourniture;

    @FXML
    private MFXFilterComboBox<Demande> demandeavaliez;


    @FXML
    private Button prisecharge;

    @FXML
    private Tab personnel;

    @FXML
    private Label nom;

    @FXML
    private Label prenom;

    @FXML
    private Label email;

    @FXML
    private Label role;

    @FXML
    private Tab profil;

    @FXML
    private Tab stokck;

    @FXML
    void onClickLogout(MouseEvent event) {
        this.utilisateur=null;
        RunApplication.changeScene("/com/example/lprs/user/loging");
    }

    @FXML
    void modifMyAccont(ActionEvent event) {
        RunApplication.changeScene("/com/example/lprs/admin/creat-user",new CreatUser(utilisateur,utilisateur));
    }

    @FXML
    void fairDemande(ActionEvent event) {
        RunApplication.changeScene("/com/example/lprs/user/DemandeFourniture",new DemandeFourniture(utilisateur));
    }

    @FXML
    void onRowClick(MouseEvent event) {
        System.out.println(listEleve.getSelectionModel().getSelectedValues());
        System.out.println(ficheEtudiant.getEmail());

        /*this.ficheEtudiant = listEleve.getSelectionModel().getSelection();
        if (ficheEtudiant!=null && ficheEtudiant.getRef_dossier()==0){
            creeDossier.setVisible(true);
        }*/
    }

    @FXML
    void onClickDeletFiche(ActionEvent event) {

    }

    @FXML
    void onClikEntretien(ActionEvent event) {
        RunApplication.changeScene("/com/example/lprs/user/Rdv", new RendeVousControlleur(utilisateur));
    }
    @FXML
    void onClickFicheFourniture(ActionEvent event) {
        RunApplication.changeScene("/com/example/lprs/user/Fiche_fourniture",new CreatFicheFourniture(utilisateur));
    }
    @FXML
    void onClickNewFournisseur(ActionEvent event) {
        RunApplication.changeScene("/com/example/lprs/user/creat-fournisseur",new CreatFournisseur(utilisateur));
    }

    @FXML
    void onClickNewFiche(ActionEvent event) {
        RunApplication.changeScene("/com/example/lprs/user/FicheEtudiant",new FicheControler(utilisateur));
    }


    @FXML
    void onClickAjouterStock(ActionEvent event) {
        RunApplication.changeScene("/com/example/lprs/user/creat-fourniture",new CreatFourniture(utilisateur));
    }

    @FXML
    void onClickCreeDossier(ActionEvent event) {

    }


    @FXML
    void onClickModifierStock(ActionEvent event) {

    }

    @FXML
    void onClickSupprimerStock(ActionEvent event) {

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

        listEleve.getTableColumns().add(nomColumn);
        listEleve.getTableColumns().add(prenomColumn);
        listEleve.getTableColumns().add(emailColum);
        listEleve.getTableColumns().add(telColum);
        listEleve.getTableColumns().add(diplomeColum);
        listEleve.getTableColumns().add(adresseColum);

        listEleve.getFilters().add(new StringFilter<>("Nom", FicheEtudiant::getNom));
        listEleve.getFilters().add(new StringFilter<>("Prénom", FicheEtudiant::getPrenom));
        listEleve.getFilters().add(new StringFilter<>("Email", FicheEtudiant::getEmail));
        listEleve.getFilters().add(new IntegerFilter<>("Téléphone", FicheEtudiant::getTelephone));
        listEleve.getFilters().add(new IntegerFilter<>("Diplôme", FicheEtudiant::getTelephone));
        listEleve.getFilters().add(new StringFilter<>("Adresse", FicheEtudiant::getAdresse));

        listEleve.getItems().addAll(ficherepo.getFiche());



    }

    private void setupFourniture(){
        MFXTableColumn<Fourniture> numerotColumn = new MFXTableColumn<>("Numerot", true, Comparator.comparing(Fourniture::getIdFourniture));
        MFXTableColumn<Fourniture> nomColumn = new MFXTableColumn<>("Nom", true, Comparator.comparing(Fourniture::getNom));
        MFXTableColumn<Fourniture> quantiteColum = new MFXTableColumn<>("Qantité",true, Comparator.comparing(Fourniture::getStock));

        numerotColumn.setRowCellFactory(list -> new MFXTableRowCell<>(Fourniture::getIdFourniture));
        nomColumn.setRowCellFactory(list -> new MFXTableRowCell<>(Fourniture::getNom));
        quantiteColum.setRowCellFactory(list -> new MFXTableRowCell<>(Fourniture::getStock));


        numerotColumn.setStyle("-fx-pref-width: 100");
        nomColumn.setStyle("-fx-pref-width: 100");
        quantiteColum.setStyle("-fx-pref-width: 150");


        listStock.getTableColumns().add(numerotColumn);
        listStock.getTableColumns().add(nomColumn);
        listStock.getTableColumns().add(quantiteColum);

        listStock.getFilters().add(new IntegerFilter<>("Numerot", Fourniture::getIdFourniture));
        listStock.getFilters().add(new StringFilter<>("Nom", Fourniture::getNom));
        listStock.getFilters().add(new IntegerFilter<>("Qantité", Fourniture::getStock));

        listStock.getItems().addAll(fourniturerepo.getfourniture());
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


        listDemande.getTableColumns().add(numerotColumn);
        listDemande.getTableColumns().add(nomColumn);
        listDemande.getTableColumns().add(quantiteColum);
        listDemande.getTableColumns().add(demanderColum);
        listDemande.getTableColumns().add(raisonColum);


        listDemande.getFilters().add(new IntegerFilter<>("Numerot", Demande::getIdDemande));
        listDemande.getFilters().add(new StringFilter<>("Fourniture", Demande::getNomFourniture));
        listDemande.getFilters().add(new IntegerFilter<>("Qantité", Demande::getQuantiter));
        listDemande.getFilters().add(new StringFilter<>("Demandeur", Demande::getNomDemandeur));
        listDemande.getFilters().add(new StringFilter<>("Raisoin", Demande::getRaison));

        listDemande.getItems().addAll(demanderepo.getDemande());

        listDemande.getSelectionModel().getSelection();
    }

    @FXML
    void prisecharge(ActionEvent event) throws SQLException {
        demanderepo.setStatus(demandeavaliez.getSelectedItem(),utilisateur);
        fourniturerepo.setStock(demandeavaliez.getSelectedItem().getNomFourniture(),demandeavaliez.getSelectedItem().getQuantiter());
        Alert co = new Alert(Alert.AlertType.INFORMATION);
        co.setTitle("Prise en charge");
        co.setContentText("Vous aviez pris en charge la demande de "+demandeavaliez.getSelectedItem().getQuantiter()+" "+demandeavaliez.getSelectedItem().getNomFourniture()+" pour Mr/Mme" +demandeavaliez.getSelectedItem().getNomDemandeur());
        co.showAndWait();
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
        ArrayList<DossierInscripition> rog = dossierepo.getdossier();
    }

    public void setUpNonValide (){
        demandeavaliez.setVisible(true);
        demandeavaliez.getItems().addAll(demanderepo.getDemande());
        demandeavaliez.setConverter(converter);
        demandeavaliez.setFilterFunction(filterFunction);
    }

    private void userprofile(){
        nom.setText("Nom : "+utilisateur.getNom() );
        prenom.setText("Prenom : "+utilisateur.getPrenom() );
        email.setText("Email : "+utilisateur.getEmail() );
        role.setText("Role : "+utilisateur.getNom() );
    };

    StringConverter<Demande> converter = FunctionalStringConverter.to((demandec) -> {
        return demandec == null ? "" : demandec.getQuantiter()+" "+demandec.getNomFourniture();
    });

    Function<String, Predicate<Demande>> filterFunction = (s) -> {
        return (demandeb) -> {
            return StringUtils.containsIgnoreCase(converter.toString(demandeb), s);
        };
    };
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (utilisateur.getRole()==2){
            creeDossier.setVisible(false);
            demande.setDisable(true);
            stokck.setDisable(true);
            entretien.setVisible(false);
            setupFiche();
            setupDossier();
        } else if (utilisateur.getRole()==3) {
            ficheEleve.setDisable(true);
            stokck.setDisable(true);
            setupDossier();
            entretien.setVisible(true);
        } else if (utilisateur.getRole()==4) {
           ficheEleve.setDisable(true);
           dossier.setDisable(true);
           supprimerStock.setVisible(false);
           modifierStock.setVisible(false);
           entretien.setVisible(false);
           setUpNonValide();
           if (demandeavaliez.getItems()!=null && !demandeavaliez.getItems().isEmpty()){
               prisecharge.setVisible(false);
           }
           setupFourniture();
           setupDemnde();
        }
        userprofile();
    }
}
