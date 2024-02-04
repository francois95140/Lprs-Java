package com.example.lprs.user;


import com.example.lprs.RunApplication;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.fxml.Initializable;
import modele.FicheEtudiant;
import modele.Utilisateur;
import repository.FicheEtudiantRepository;

import java.net.URL;
import java.util.ResourceBundle;

public class FicheControler implements Initializable {
    private Utilisateur userconect;
    FicheEtudiant ficheselect;
    public FicheControler(Utilisateur utilisateur) {
        userconect=utilisateur;
    }
    public FicheControler(Utilisateur utilisateur, FicheEtudiant fiche) {
        userconect=utilisateur;
        ficheselect=fiche;
    }

    @FXML
    private Label Erreur;

    @FXML
    private MFXTextField rue;

    @FXML
    private MFXTextField cp;

    @FXML
    private MFXTextField diplome;

    @FXML
    private MFXTextField email;

    @FXML
    private MFXTextField nom;

    @FXML
    private MFXTextField prenom;

    @FXML
    private MFXTextField tel;

    @FXML
    private MFXButton validee;

    @FXML
    private MFXTextField ville;

    @FXML
    void onClickBack(MouseEvent event) {
        RunApplication.changeScene("/com/example/lprs/user/accueilU",new AccueilU(userconect));
    }

    @FXML
    void validee(ActionEvent event) {
        if(ficheselect==null){
            if (!nom.getText().isBlank() && !prenom.getText().isBlank()  && !email.getText().isBlank() && !diplome.getText().isBlank() && !tel.getText().isBlank() && !rue.getText().isBlank() && !cp.getText().isBlank() && !ville.getText().isBlank()){
                FicheEtudiant newfiche = new FicheEtudiant(nom.getText(), prenom.getText(), email.getText(), diplome.getText(), Integer.parseInt(tel.getText()), rue.getText(),Integer.parseInt(cp.getText()), ville.getText(),userconect.getIdUtilisateur());
                try {
                    FicheEtudiantRepository fiche = new FicheEtudiantRepository();
                    FicheEtudiant u = fiche.save(newfiche);
                    System.out.println(u.getIdFiche());
                    RunApplication.changeScene("/com/example/lprs/user/Dossierinscription",new CreatDossier(u,userconect));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }else {
                System.out.println("vide");
                Erreur.setText("Un ou plusieur champ sons vide ");
            }
        }else {
            if (!nom.getText().isBlank() && !prenom.getText().isBlank()  && !email.getText().isBlank() && !diplome.getText().isBlank() && !tel.getText().isBlank() && !rue.getText().isBlank() && !cp.getText().isBlank() && !ville.getText().isBlank()){
                ficheselect.setNom(nom.getText());
                ficheselect.setPrenom(prenom.getText());
                ficheselect.setEmail(email.getText());
                ficheselect.setDiplome(diplome.getText());
                ficheselect.setTelephone(Integer.parseInt(tel.getText()));
                ficheselect.setRue(rue.getText());
                ficheselect.setCp(Integer.parseInt(cp.getText()));
                ficheselect.setVille(ville.getText());
                ficheselect.setRef_utilisateur(userconect.getIdUtilisateur());
                try {
                    FicheEtudiantRepository fiche = new FicheEtudiantRepository();
                    fiche.save(ficheselect);
                    RunApplication.changeScene("/com/example/lprs/user/accueil",new AccueilU(userconect));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }else {
                System.out.println("vide");
                Erreur.setText("Un ou plusieur champ sons vide ");
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (ficheselect!=null){
            nom.setText(ficheselect.getNom());
            prenom.setText(ficheselect.getPrenom());
            email.setText(ficheselect.getEmail());
            diplome.setText(ficheselect.getDiplome());
            tel.setText(String.valueOf(ficheselect.getTelephone()));
            rue.setText(ficheselect.getRue());
            cp.setText(String.valueOf(ficheselect.getCp()));
            ville.setText(ficheselect.getVille());
        }
    }
}
