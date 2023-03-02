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
    private String table = "DossierIncription";

    public DossierInscripition inscription(DossierInscripition dossierinscription) throws SQLException {
        String sql;
        PreparedStatement pstm;
        if (dossierinscription.getId_dossier() > 0) {
            sql = "UPDATE `" + table + "` SET `date`=?,`heure`=?,`filiere`=?,`motivation`=?,`ref_fiche`=? WHERE getId_dossier=?";
            pstm = coBdd.getConnection().prepareStatement(sql);
            pstm.setString(1, dossierinscription.getDate());
            pstm.setString(2, dossierinscription.getHeure());
            pstm.setString(3, dossierinscription.getFiliere());
            pstm.setString(4, dossierinscription.getMotivation());
            pstm.setString(5, dossierinscription.getRef_fiche());

            pstm.executeUpdate();

        }
//insert
        else {
            sql = "INSERT INTO `" + table + "`( `date`, `heure`, `filiere`, `motivation`, `ref_fiche`) VALUES (?,?,?,?,?)";

            pstm = coBdd.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, dossierinscription.getDate());
            pstm.setString(2, dossierinscription.getHeure());
            pstm.setString(3, dossierinscription.getFiliere());
            pstm.setString(4, dossierinscription.getMotivation());
            pstm.setString(5, dossierinscription.getRef_fiche());
            pstm.executeUpdate();
            ResultSet rs = pstm.getGeneratedKeys();
            if (rs.next()) {
                int last_inserted_id = rs.getInt(1);
                dossierinscription.setId_dossier(last_inserted_id);
            }

        }
        return dossierinscription;
    }

    public ArrayList<DossierInscripition> getId_dossier(){
        ArrayList<DossierInscripition> DossierInscripitions = new ArrayList<DossierInscripition>();
        DossierInscripition dossierinscription;
        String sql = "SELECT * FROM " + table;
        PreparedStatement pstm;
        try {
            pstm = coBdd.getConnection().prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                dossierinscription = new DossierInscripition(rs.getInt("id_dossier"), rs.getString("date"), rs.getString("heure"), rs.getString("filiere"), rs.getString("motivation"), rs.getString("ref_fiche"));
                DossierInscripitions.add(dossierinscription);
            }
        } catch (SQLException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
        return DossierInscripitions;
    }

}


