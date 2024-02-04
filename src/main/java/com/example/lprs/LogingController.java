package com.example.lprs;

import com.example.lprs.admin.Accueil;
import com.example.lprs.user.AccueilU;
import com.example.lprs.user.RendeVousControlleur;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.util.Duration;
import modele.Logs;
import modele.Timer;
import modele.Utilisateur;
import repository.LogsRepository;
import repository.UtilisateurRepository;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;


public class LogingController  {
    Timeline timeline;
    Timer timer = new Timer();
    public LogingController(){
       timeline  = new Timeline(new KeyFrame(Duration.millis(10), actionEvent -> timer.update()));
       timeline.setCycleCount(Animation.INDEFINITE);
    }



    int echec;

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
        RunApplication.changeScene("/com/example/lprs/user/reset-password",new ResetPassword(null,false));
        System.out.println("User passeword forget");
    }

    @FXML
    void seConnecter(ActionEvent event) throws UnknownHostException, SQLException {
        System.out.println(userName.getText());
        System.out.println(passeword.getText());
        UtilisateurRepository utilisateurRepository = new UtilisateurRepository();
        Utilisateur u = utilisateurRepository.connexion(userName.getText(),passeword.getText());
        if(u != null){
            InetAddress ip = InetAddress.getLocalHost();
            LogsRepository logs = new LogsRepository();
            Logs l = logs.Insert(u.getIdUtilisateur(),ip.getHostAddress());
            if (u.getRole()==1){
                RunApplication.changeScene("/com/example/lprs/admin/accueil",new Accueil(u));
            } else {
                RunApplication.changeScene("/com/example/lprs/user/accueilU", new AccueilU(u));
            }
            Alert co = new Alert(Alert.AlertType.INFORMATION);
            co.setTitle("Connexion");
            co.setContentText("Bonjour "+u.getPrenom()+", vous ete connectez");
            co.showAndWait();
        }else{
            System.out.println("erreur");
            Erreur.setText("Mot de passe ou email incorrect");
            echec = echec+1;
            if (echec==3){
                timeline.play();
                Erreur.textProperty().bind(Bindings.format("%02d:%02d:%d%d",timer.mm,timer.ss,timer.th,timer.hd));
            }
        }
    }



}
