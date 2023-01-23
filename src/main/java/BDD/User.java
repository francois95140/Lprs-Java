package BDD;

import javafx.scene.control.TextField;

import java.sql.*;

public class User {
    private String nom;

    private String prenom;

    private String mail;

    private String mot_de_passe;

    private boolean etatConnexion = false;

    private  Connection maConnexion;

    private String mysqlAdresse = "localhost:3306";

}
