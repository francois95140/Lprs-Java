package repository;

import BDD.Database;

import modele.RendezVous;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class RendezVousRepository {

    private Database coBdd;
    private String table = "rendez-vous";

    public RendezVousRepository() {
        coBdd = new Database();
    }

    public RendezVous inscription(RendezVous rendezVous) throws SQLException {
        String sql;
        PreparedStatement pstm;
        if (rendezVous.getId_RendezVous() > 0) {
            sql = "UPDATE `" + table + "` SET `heure`=?,`date`=?,`ref_salle`=?,`ref_prof`=?,`ref_dossier`=? WHERE id_RendezVous=?";
            pstm = coBdd.getConnection().prepareStatement(sql);
            pstm.setString(1, rendezVous.getDate());
            pstm.setString(2, rendezVous.getHeure());
            pstm.setInt(3, rendezVous.getSalle());
            pstm.setInt(4, rendezVous.getRef_prof());
            pstm.setInt(5, rendezVous.getRef_dossier());
            pstm.executeUpdate();

        }
//insert
        else {
            sql = "INSERT INTO `" + table + "`(`date`, `heure`,`ref_salle`,`ref_prof`,`ref_dossier`) VALUES (?,?,?,?,?)";
            System.out.println(rendezVous.getHeure()+" "+rendezVous.getDate()+" "+rendezVous.getSalle());
            pstm = coBdd.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, rendezVous.getDate());
            pstm.setString(2, rendezVous.getHeure());
            pstm.setInt(3, rendezVous.getSalle());
            pstm.setInt(4, rendezVous.getRef_prof());
            pstm.setInt(5, rendezVous.getRef_dossier());
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