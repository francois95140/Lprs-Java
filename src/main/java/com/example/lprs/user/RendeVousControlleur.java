package com.example.lprs.user;
import com.example.lprs.RunApplication;
import com.example.lprs.admin.Accueil;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

import io.github.palexdev.materialfx.utils.StringUtils;
import io.github.palexdev.materialfx.utils.others.FunctionalStringConverter;

import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;
import modele.DossierInscripition;
import modele.RendezVous;
import modele.Salle;
import modele.Utilisateur;
import repository.DossierInscriptionRepository;
import repository.RendezVousRepository;
import repository.SalleRepository;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.function.Predicate;

public class RendeVousControlleur implements Initializable {

    private final Utilisateur userconect;

    public RendeVousControlleur (Utilisateur user) {
        this.userconect = user;

    }

    DossierInscriptionRepository dossierripo = new DossierInscriptionRepository();
    SalleRepository salleripo = new SalleRepository();
    @FXML
    private Label Erreur;

    @FXML
    private MFXDatePicker date;

    @FXML
    private MFXFilterComboBox<DossierInscripition> eleve;

    @FXML
    private MFXComboBox<String> horaire;

    @FXML
    private MFXFilterComboBox<Salle> salle;

    @FXML
    private MFXButton valider;

    @FXML
    void retoure(MouseEvent event) {
        RunApplication.changeScene("/com/example/lprs/user/accueilU",new AccueilU(userconect));
    }

    @FXML
    void onClickValider(ActionEvent event) throws SQLException {
        if (date.getText()!=null && salle.getSelectedItem()!=null && eleve.getSelectedItem() != null && horaire.getText() != null) {
            RendezVous newrdv = new RendezVous(date.getText(),horaire.getText(),salle.getSelectedItem().getIdSalle(),userconect.getIdUtilisateur(),eleve.getSelectedItem().getId_dossier());
            RendezVousRepository rdv = new RendezVousRepository();
            rdv.inscription(newrdv);
            eleve.getSelectedItem().setEntretien(true);
            DossierInscriptionRepository dossierInscriptionRepository = new DossierInscriptionRepository();
            dossierInscriptionRepository.setEntretien(eleve.getSelectedItem());
            RunApplication.changeScene("/com/example/lprs/user/accueilU", new AccueilU(userconect));
            Alert co = new Alert(Alert.AlertType.INFORMATION);
            co.setTitle("Rendez-Vous");
            co.setContentText("Rendez-vous le "+date.getText()+" "+horaire.getText()+" avec "+eleve.getSelectedItem().getEleve()+" en "+salle.getText());
            co.showAndWait();
        }
    }

    StringConverter<DossierInscripition> converter = FunctionalStringConverter.to((doss) -> {
        return doss == null ? "" : doss.getEleve();
    });
    StringConverter<Salle> convertere = FunctionalStringConverter.to((salle) -> {
        return salle == null ? "" : "Batiment "+salle.getBatiment()+" Salle "+salle.getNumerot();
    });

    Function<String, Predicate<DossierInscripition>> filterFunction = (s) -> {
        return (dossier) -> {
            return StringUtils.containsIgnoreCase(converter.toString(dossier), s);
        };
    };

    public void setupEleve() {

        eleve.getItems().addAll(dossierripo.getdossierrdv());
        eleve.setFilterFunction(filterFunction);
        eleve.setConverter(converter);

    }
    public void setupSalle() {
        salle.getItems().addAll(salleripo.getSalle());
        salle.setConverter(convertere);

    }

    private final String[] horaires = {
            "Matin","Aprés-midi"};
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.setupEleve();
        this.setupSalle();
        horaire.getItems().addAll(horaires);
    }
}
