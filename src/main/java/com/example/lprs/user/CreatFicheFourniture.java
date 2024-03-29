package com.example.lprs.user;

import com.example.lprs.RunApplication;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.utils.StringUtils;
import io.github.palexdev.materialfx.utils.others.FunctionalStringConverter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;
import modele.FicheFourniture;
import modele.Fournisseur;
import modele.Fourniture;
import modele.Utilisateur;
import repository.FicheFournitureRepository;
import repository.FournisseurRepository;
import repository.FournitureRepository;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.function.Predicate;

public class CreatFicheFourniture implements Initializable {

    FournitureRepository fournirepo = new FournitureRepository();
    FournisseurRepository fournisseurrepo = new FournisseurRepository();
    Utilisateur userconnect;

    public CreatFicheFourniture(Utilisateur user){
        this.userconnect = user;
    }

    @FXML
    private Label Erreur;

    @FXML
    private MFXFilterComboBox<Fournisseur> fourniseur;

    @FXML
    private MFXFilterComboBox<Fourniture> fourniture;

    @FXML
    private MFXTextField prix;

    @FXML
    private MFXButton validee;

    @FXML
    void retoure(MouseEvent event) {
        RunApplication.changeScene("/com/example/lprs/user/accueilU",new AccueilU(userconnect));
    }

    @FXML
    void validee(ActionEvent event) {
        if (!prix.getText().isBlank() || fourniseur.getSelectedItem() != null || !fourniture.getSelectedItem().getNom().isBlank()){
            try {
                FicheFournitureRepository firepo = new FicheFournitureRepository();
                FicheFourniture newfiche = new FicheFourniture(fourniture.getSelectedItem().getIdFourniture(),fourniseur.getSelectedItem().getId_fournisseur(),Double.parseDouble(prix.getText()),userconnect.getIdUtilisateur());
                firepo.Insert(newfiche);
                RunApplication.changeScene("/com/example/lprs/user/accueilU",new AccueilU(userconnect));

            }catch (Exception e) {
                throw new RuntimeException(e);
            }

        }else {
            Erreur.setText("Un ou plusieur champ vide ");
        }
    }

    StringConverter<Fourniture> converter = FunctionalStringConverter.to((fourniture) -> {
        return fourniture == null ? "" : fourniture.getNom();
    });
    StringConverter<Fournisseur> convertere = FunctionalStringConverter.to((fourniseur) -> {
        return fourniseur == null ? "" : fourniseur.getNom_entreprise();
    });

    Function<String, Predicate<Fourniture>> filterFourniture = (s) -> {
        return (fourniture) -> {
            return StringUtils.containsIgnoreCase(converter.toString(fourniture), s);
        };
    };
    Function<String, Predicate<Fournisseur>> filterFournisseur = (s) -> {
        return (fournisseur) -> {
            return StringUtils.containsIgnoreCase(convertere.toString(fournisseur), s);
        };
    };

    public void setupFournissaur() {
        fourniseur.getItems().addAll(fournisseurrepo.getfournisseur());
        /*fourniseur.setConverter(FunctionalStringConverter.to(fourniture1 -> (fourniture1 == null) ? "Nom" : fourniture1.getNom_entreprise()));*/
        fourniseur.setConverter(convertere);
        fourniseur.setFilterFunction(filterFournisseur);
    }

    public void setupFourniture() {
        fourniture.getItems().addAll(fournirepo.getfourniture());
        fourniture.setConverter(converter);
        /*fourniture.getFilterList().add(new StringFilter<>("Nom",Fourniture::getNom));*/
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupFournissaur();
        setupFourniture();
    }
}
