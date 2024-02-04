package com.example.lprs.admin;

import com.example.lprs.RunApplication;
import com.example.lprs.user.AccueilU;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialog;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialogBuilder;
import io.github.palexdev.materialfx.dialogs.MFXStageDialog;
import io.github.palexdev.materialfx.enums.ScrimPriority;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modele.Passewordgenerator;
import modele.Utilisateur;
import repository.UtilisateurRepository;

import java.net.URL;
import java.util.Map;
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

    private MFXGenericDialog dialogContent;
    private MFXStageDialog dialog;
    @FXML
    private AnchorPane ancho;

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
                        RunApplication.changeScene("/com/example/lprs/user/accueilU",new AccueilU(userconect));
                    }
                    Alert co = new Alert(Alert.AlertType.INFORMATION);
                    co.setTitle("Creation d'utilisateur");
                    co.setContentText("L'utiisateur "+nom.getText()+" "+prenom.getText()+" a été créer en tant que "+role.getText());
                    co.showAndWait();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                System.out.println(role.getItems().indexOf(role.getText())+1);
            }else {
                Erreur.setText("Un ou plusieurs champ sons vide ");
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
    void onClickBack(MouseEvent event) {
        DialogsController(null);
        if (userconect.getRole() == 1){
            RunApplication.changeScene("/com/example/lprs/admin/accueil", new Accueil(newuser));
        }else {
            RunApplication.changeScene("/com/example/lprs/user/accueilU", new AccueilU(newuser));
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

    public void DialogsController(Stage stage) {
        Platform.runLater(() -> {
            this.dialogContent = MFXGenericDialogBuilder.build().setContentText("Lorem Ipsum is simply dummy text of the printing and typesetting industry.\nLorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.\nIt has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.\nIt was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n").makeScrollable(true).get();
            this.dialog = MFXGenericDialogBuilder.build(this.dialogContent).toStageDialogBuilder().initOwner(stage).initModality(Modality.APPLICATION_MODAL).setDraggable(true).setTitle("Dialogs Preview").setOwnerNode(this.ancho).setScrimPriority(ScrimPriority.WINDOW).setScrimOwner(true).get();
            this.dialogContent.addActions(Map.entry(new MFXButton("Confirm"), (event) -> {
            }), Map.entry(new MFXButton("Cancel"), (event) -> {
                this.dialog.close();
            }));
            this.dialogContent.setMaxSize(400.0, 200.0);
        });
        MFXFontIcon infoIcon = new MFXFontIcon("mfx-info-circle-filled", 18.0);
        this.dialogContent.setHeaderIcon(infoIcon);
        this.dialogContent.setHeaderText("This is a generic info dialog");
        this.dialog.showDialog();
    }
}

