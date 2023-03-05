package repository;

import BDD.Database;

import modele.RendezVous;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class RendezVousRepository {

    private Database coBdd;
    private String table = "RendezVous";

    public RendezVous inscription(RendezVous rendezVous) throws SQLException {
        String sql;
        PreparedStatement pstm;
        if (rendezVous.getId_RendezVous() > 0) {
            sql = "UPDATE `" + table + "` SET `heure`=?,`date`=?,`salle`=?,`ref_prof`=?,`ref_dossier`=? WHERE id_RendezVous=?";
            pstm = coBdd.getConnection().prepareStatement(sql);
            pstm.setString(1, rendezVous.getDate());
            pstm.setString(2, rendezVous.getHeure());
            pstm.setString(3, rendezVous.getSalle());
            pstm.setString(4, rendezVous.getref_prof());
            pstm.setInt(5, rendezVous.getref_dossier());
            pstm.executeUpdate();

        }
//insert
        else {
            sql = "INSERT INTO `" + table + "`( `heure`, `date`, `salle`,`ref_prof`,`ref_dossier`) VALUES (?,?,?,?,?)";

            pstm = coBdd.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, rendezVous.getDate());
            pstm.setString(2, rendezVous.getHeure());
            pstm.setString(3, rendezVous.getSalle());
            pstm.setString(4, rendezVous.getref_prof());
            pstm.setInt(5, rendezVous.getref_dossier());
            pstm.executeUpdate();
            ResultSet rs = pstm.getGeneratedKeys();
            if (rs.next()) {
                int last_inserted_id = rs.getInt(1);
                rendezVous.setId_RendezVous(last_inserted_id);
            }

        }
        return rendezVous;
    }
}