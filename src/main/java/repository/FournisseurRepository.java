package repository;
import BDD.Database;

import modele.Fournisseur;

import java.sql.*;
import java.util.ArrayList;

public class FournisseurRepository {
    private Database coBdd;
    private String table = "fournisseur";

    public FournisseurRepository() {
        coBdd = new Database();
    }

    public Fournisseur Insert (Fournisseur fournisseur) throws SQLException {
        String sql;
        PreparedStatement pstm;
        if (fournisseur.getId_fournisseur() > 0) {
            sql = "UPDATE`" + table + "`SET `nom_entreprise`=?, `rue`=?, `cp`=?, `ville`=? WHERE getId_fournisseur=? ";
            pstm = coBdd.getConnection().prepareStatement(sql);
            pstm.setString(1, fournisseur.getNom_entreprise());
            pstm.setString(2, fournisseur.getRue());
            pstm.setInt(3, fournisseur.getCp());
            pstm.setString(4, fournisseur.getVille());
            pstm.executeUpdate();
        } else {
            sql = "INSERT INTO`" + table + "`(`nom_entreprise`,`rue`,`cp`,`ville`) VALUES(?,?,?,?)";
            pstm = coBdd.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, fournisseur.getNom_entreprise());
            pstm.setString(2, fournisseur.getRue());
            pstm.setInt(3, fournisseur.getCp());
            pstm.setString(4, fournisseur.getVille());
            pstm.executeUpdate();
            ResultSet rs = pstm.getGeneratedKeys();
            if (rs.next()) {
                int last_inserted_id = rs.getInt(1);
                fournisseur.setId_fournisseur(last_inserted_id);
            }
        }
        return fournisseur;
    }

    public ArrayList<Fournisseur> getfournisseur(){
        ArrayList<Fournisseur> Fournisseurs = new ArrayList<Fournisseur>();
        Fournisseur fournisseur;
        String sql = "SELECT * FROM " + table;
        PreparedStatement pstm;
        try {
            pstm = coBdd.getConnection().prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                fournisseur = new Fournisseur(rs.getInt("id_fournisseur"),rs.getString("nom_entreprise"),rs.getString("rue"),rs.getInt("cp"),rs.getString("ville"));
                Fournisseurs.add(fournisseur);
            }

        }catch (SQLException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
        return Fournisseurs;
    }

}




