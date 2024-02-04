package repository;

import BDD.Database;
import modele.DossierInscripition;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DossierInscriptionRepository {
    private Database coBdd;
    private String table = "dossier_inscription";

    public DossierInscriptionRepository() {
        coBdd = new Database();
    }

    public void inscription(DossierInscripition dossierinscription) throws SQLException {
        String sql;
        PreparedStatement pstm;
        if (dossierinscription.getId_dossier() > 0) {
            sql = "UPDATE `" + table + "` SET `date`=?,`heure`=?,`filiere`=?,`motivation`=?,`ref_fiche`=? WHERE id_dossier=?";
            pstm = coBdd.getConnection().prepareStatement(sql);
            pstm.setString(1, dossierinscription.getDate());
            pstm.setString(2, dossierinscription.getHeure());
            pstm.setString(3, dossierinscription.getFiliere());
            pstm.setString(4, dossierinscription.getMotivation());
            pstm.setString(5, String.valueOf(dossierinscription.getRef_fiche()));

            pstm.executeUpdate();

        }
//insert
        else {
            sql = "INSERT INTO `" + table + "`( `filiere`, `motivation`, `ref_fiche`) VALUES (?,?,?)";
            pstm = coBdd.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, dossierinscription.getFiliere());
            pstm.setString(2, dossierinscription.getMotivation());
            pstm.setInt(3, dossierinscription.getRef_fiche());
            pstm.executeUpdate();
            ResultSet rs = pstm.getGeneratedKeys();
            if (rs.next()) {
                int last_inserted_id = rs.getInt(1);
                dossierinscription.setId_dossier(last_inserted_id);
            }

        }
    }

    public ArrayList<DossierInscripition> getdossier(){
        ArrayList<DossierInscripition> DossierInscripitions = new ArrayList<DossierInscripition>();
        DossierInscripition dossierinscription;
        String sql = "SELECT *,`nom`,`prenom` FROM " + table+" INNER JOIN fiche_etudiant WHERE dossier_inscription.ref_fiche = fiche_etudiant.id_fiche";
        PreparedStatement pstm;
        try {
            pstm = coBdd.getConnection().prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                System.out.println(rs.getInt("id_dossier"));
                dossierinscription = new DossierInscripition(rs.getInt("id_dossier"),rs.getString("date"),rs.getString("heure"),rs.getString("filiere"),rs.getString("motivation"),rs.getInt("ref_fiche"),rs.getBoolean("entretient"),rs.getString("nom"),rs.getString("prenom"));
                DossierInscripitions.add(dossierinscription);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return DossierInscripitions;
    }
    public ArrayList<DossierInscripition> getdossierrdv(){
        ArrayList<DossierInscripition> DossierInscripitions = new ArrayList<DossierInscripition>();
        DossierInscripition dossierinscription;
        String sql = "SELECT *,`nom`,`prenom` FROM " + table+" INNER JOIN fiche_etudiant WHERE dossier_inscription.ref_fiche = fiche_etudiant.id_fiche AND entretient = 0";
        PreparedStatement pstm;
        try {
            pstm = coBdd.getConnection().prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                System.out.println(rs.getInt("id_dossier"));
                dossierinscription = new DossierInscripition(rs.getInt("id_dossier"),rs.getString("date"),rs.getString("heure"),rs.getString("filiere"),rs.getString("motivation"),rs.getInt("ref_fiche"),rs.getBoolean("entretient"),rs.getString("nom"),rs.getString("prenom"));
                DossierInscripitions.add(dossierinscription);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return DossierInscripitions;
    }

    public void setEntretien(DossierInscripition doss) throws SQLException {
        String sql;
        PreparedStatement pstm;

        sql = "UPDATE `" + table + "` SET `entretient`=? WHERE id_dossier=?";
        pstm = coBdd.getConnection().prepareStatement(sql);
        pstm.setBoolean(1, doss.isEntretien());
        pstm.setInt(2, doss.getId_dossier());

        pstm.executeUpdate();

    }
}


