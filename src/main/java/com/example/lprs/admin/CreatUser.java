package com.example.lprs.admin;

import com.example.lprs.RunApplication;
import com.example.lprs.user.AccueilU;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import modele.Passewordgenerator;
import modele.Utilisateur;
import repository.UtilisateurRepository;

import java.net.URL;
import java.util.ResourceBundle;

public class CreatUser implements Initializable {

    private Utilisateur userconect;

    private Utilisateur newuser;

    public CreatUser (Utilisateur user,Utilisateur newuser) {
        this.userconect = user;
        this.newuser= newuser;
    }
    public CreatUser (Utilisateur user) {
        this.userconect = user;

    }

    @FXML
    private Label Erreur;

    @FXML
    private MFXTextField email;

    @FXML
    private MFXTextField nom;

    @FXML
    private MFXTextField prenom;

    @FXML
    private MFXComboBox<String> role;

    @FXML
    private MFXButton valiez;

    @FXML
    void validez(ActionEvent event) {

        if(newuser==null){
            if (!nom.getText().isBlank() && !prenom.getText().isBlank() && !email.getText().isBlank() && role.getItems().indexOf(role.getText())+1 > 0){
                newuser = new Utilisateur(nom.getText(),prenom.getText(),email.getText(),Passewordgenerator.codeGenerate(8),role.getItems().indexOf(role.getText())+1,userconect.getIdUtilisateur());
                try {
                    UtilisateurRepository userRepository = new UtilisateurRepository();
                    userRepository.inscription(newuser);
                    if (userconect.getRole() == 1){
                        RunApplication.changeScene("/com/example/lprs/admin/accueil",new Accueil(userconect));
                    }else {
                        RunApplication.changeScene("/com/example/lprs/user/accueil",new AccueilU(userconect));
                    }

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                System.out.println(role.getItems().indexOf(role.getText())+1);
            }else {
                Erreur.setText("Un ou plusieur champ sons vide ");
            }

        }else {
            newuser.setNom(nom.getText());
            newuser.setPrenom(prenom.getText());
            newuser.setEmail(email.getText());
            System.out.println(newuser.getIdUtilisateur());
            try {
                UtilisateurRepository userRepository = new UtilisateurRepository();
                userRepository.inscription(newuser);
                RunApplication.changeScene("/com/example/lprs/admin/accueil",new Accueil(userconect));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void onClickBack(ActionEvent event) {
        if (userconect.getRole() == 1){
            RunApplication.changeScene("/com/example/lprs/admin/accueil", new Accueil(newuser));
        }else {
            RunApplication.changeScene("/com/example/lprs/user/accueil", new AccueilU(newuser));
        }
    }

    private final String[] roles = {
            "Administrateur","Professeur","Secretaire","Gestionnaire de stock"
    };

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        role.getItems().addAll(roles);
        if (newuser!=null){
            nom.setText(newuser.getNom());
            prenom.setText(newuser.getPrenom());
            email.setText(newuser.getEmail());
            role.setText(String.valueOf(newuser.getRole()));
            role.setDisable(true);
            if (newuser.getRole()==1){
                role.setDisable(false);
                //role.getItems(String.valueOf(role.getItems().indexOf(newuser.getRole().toString())));
                //role.setText(String.valueOf(newuser.getRole()));
                valiez.setText("Modifier");
            }

        }
    }
}

