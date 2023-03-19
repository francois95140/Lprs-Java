package repository;

import BDD.Database;
import modele.Demande;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DemandeRepository {

    private Database coBdd;
    private String table = "demande_fournitures";

    public DemandeRepository() {
        coBdd = new Database();
    }

    public Demande insert (Demande demande) throws SQLException {
        String sql;
        PreparedStatement pstm;
        if(demande.getIdDemande()>0 && demande.getrefGestionaire()>0) {
            sql = "UPDATE `"+table+"` SET `nom_fourniture`=?,`quantite_demander`=?,`raison`=?,`ref_gestionaire`=? WHERE `id_demande`=?";
            pstm = coBdd.getConnection().prepareStatement(sql);
            pstm.setString(1, demande.getNomFourniture());
            pstm.setInt(2, demande.getQuantiter());
            pstm.setString(3, demande.getRaison());
            pstm.setInt(4, demande.getRefGestionaire());
            pstm.setInt(5, demande.getIdDemande());
            pstm.executeUpdate();

        }
//insert
        else {
            sql = "INSERT INTO `"+table+"`(`nom_fourniture`,`quantite_demander`,`raison`,`ref_prof`) VALUES (?,?,?,?)";

            pstm = coBdd.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, demande.getNomFourniture());
            pstm.setInt(2, demande.getQuantiter());
            pstm.setString(3, demande.getRaison());
            pstm.setInt(4, demande.getRefProf());
            pstm.executeUpdate();
            ResultSet rs = pstm.getGeneratedKeys();
            if(rs.next())
            {
                int last_inserted_id = rs.getInt(1);
                System.out.println(last_inserted_id);
                demande.setIdDemande(last_inserted_id);
            }

        }
        return demande;
    }

    public ArrayList<Demande> getDemande(){
        ArrayList<Demande> Demandes = new ArrayList<Demande>();
        Demande demande;
        String sql = "SELECT `id_demande`,`nom_fourniture`,`quantite_demander`,`raison`,`nom` FROM " + table +" INNER JOIN utilisateur WHERE `ref_prof` = utilisateur.id_user ";
        PreparedStatement pstm;
        try {
            pstm=coBdd.getConnection().prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                demande = new Demande(rs.getInt("id_demande"),rs.getString("nom_fourniture"),rs.getInt("quantite_demander"),rs.getString("nom"),rs.getString("raison"));
                Demandes.add(demande);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Demandes;
    }

}
