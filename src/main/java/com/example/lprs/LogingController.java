package com.example.lprs;

import com.example.lprs.admin.Accueil;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import modele.Utilisateur;
import repository.UtilisateurRepository;


public class LogingController {

    @FXML
    private Label Erreur;

    @FXML
    private Hyperlink PassewordForget;

    @FXML
    private MFXPasswordField passeword;

    @FXML
    private MFXButton seConnecter;

    @FXML
    private MFXTextField userName;

    @FXML
    void PassewordForget(ActionEvent event) {
        RunApplication.changeScene("/com/example/lprs/user/reset-password");
        System.out.println("User passeword forget");
    }

    @FXML
    void seConnecter(ActionEvent event) {
        System.out.println(userName.getText());
        System.out.println(passeword.getText());
        UtilisateurRepository utilisateurRepository = new UtilisateurRepository();
        Utilisateur u = utilisateurRepository.connexion(userName.getText(),passeword.getText());
        if(u != null){
            if (u.getRole().equals("1")){
                RunApplication.changeScene("/com/example/lprs/admin/accueil",new Accueil(u));
                System.out.println("Admin connecte");
            } else {
                RunApplication.changeScene("/com/example/lprs/resetpassword");
                System.out.println("User connecte");
            }

        }else{
            System.out.println("erreur");
            Erreur.setText("Mot de passe ou email incorrect");
        }
    }


}
