package com.example.lprs.user;

import com.example.lprs.RunApplication;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import modele.Fourniture;
import modele.Utilisateur;
import repository.FournitureRepository;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CreatFourniture implements Initializable {

    Utilisateur user;
    Fourniture fourniture;

    public CreatFourniture(Utilisateur user, Fourniture selecFourniture){
        this.user=user;
        this.fourniture=selecFourniture;
    }
    public CreatFourniture(Utilisateur user){
        this.user=user;
    }
    @FXML
    private Label Erreur;

    @FXML
    private MFXTextField nom;

    @FXML
    private MFXTextField quantite;

    @FXML
    private MFXButton validee;

    @FXML
    void validee(ActionEvent event)  {
        if (fourniture==null){
            if (nom.getText().isBlank()||quantite.getText().isBlank()){
                Erreur.setText("Un ou plusieur champ vide");
            }else {
                try {
                    Fourniture newfourniture = new  Fourniture(nom.getText(),Integer.parseInt(quantite.getText()));
                    FournitureRepository fourni = new FournitureRepository();
                    fourni.inscription(newfourniture);
                }catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }
        }else {
            if (nom.getText().isBlank()||quantite.getText().isBlank()){
                Erreur.setText("Un ou plusieur champ vide");
            }else {
                fourniture.setNom(nom.getText());
                fourniture.setStock(Integer.parseInt(quantite.getText()));
            }
        }

    }

    @FXML
    void retoure(MouseEvent event) {
        RunApplication.changeScene("/com/example/lprs/user/accueilU",new AccueilU(user));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (fourniture!=null){
            nom.setText(fourniture.getNom());
            quantite.setText(String.valueOf(fourniture.getStock()));
        }
    }
}
