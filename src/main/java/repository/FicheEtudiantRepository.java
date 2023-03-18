package repository;
import BDD.Database;
import modele.FicheEtudiant;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class FicheEtudiantRepository {
    private Database coBdd;
    private String table = "fiche_etudiant";


    public FicheEtudiantRepository() {
        coBdd = new Database();
    }

    public FicheEtudiant save(FicheEtudiant etudiant) throws SQLException {
        String sql;
        PreparedStatement pstm;
        if (etudiant.getIdFiche() > 0) {
            sql = "UPDATE `" + table + "` SET `nom`=?,`prenom`=?,`diplome`=?,`email`=?,`telephone`=?, `rue`=?, `cp`=?, `ville`=?,`ref_utilisateur`=?,`ref_dossier`=? WHERE id_fiche=?";
            pstm = coBdd.getConnection().prepareStatement(sql);

            pstm.setString(1, etudiant.getNom());
            pstm.setString(2, etudiant.getPrenom());
            pstm.setString(3, etudiant.getDiplome());
            pstm.setString(4, etudiant.getEmail());
            pstm.setInt(5, etudiant.getTelephone());
            pstm.setString(6, etudiant.getRue());
            pstm.setInt(7, etudiant.getCp());
            pstm.setString(8, etudiant.getVille());
            pstm.setInt(9, etudiant.getRef_utilisateur());
            pstm.setInt(10, etudiant.getIdFiche());
            pstm.setInt(11, etudiant.getRef_dossier());
            pstm.executeUpdate();
        } else {
            sql = "INSERT INTO `" + table + "`(`nom`, `prenom`,`diplome`, `email`, `telephone`, `rue`, `cp`, `ville`,`ref_utilisateur`) VALUES (?,?,?,?,?,?,?,?,?)";
            pstm = coBdd.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, etudiant.getNom());
            pstm.setString(2, etudiant.getPrenom());
            pstm.setString(3, etudiant.getDiplome());
            pstm.setString(4, etudiant.getEmail());
            pstm.setInt(5, etudiant.getTelephone());
            pstm.setString(6, etudiant.getRue());
            pstm.setInt(7, etudiant.getCp());
            pstm.setString(8, etudiant.getVille());
            pstm.setInt(9, etudiant.getRef_utilisateur());
            pstm.executeUpdate();
            ResultSet rs = pstm.getGeneratedKeys();
            if (rs.next()) {
                int last_inserted_id = rs.getInt(1);
                etudiant.setIdFiche(last_inserted_id);
            }
        }
        return etudiant;
    }

    public ArrayList<FicheEtudiant> getFiche() {
        ArrayList<FicheEtudiant> FicheEtudiant = new ArrayList<FicheEtudiant>();
        FicheEtudiant fiche;
        String sql = "SELECT * FROM " + table;
        PreparedStatement pstm;
        try {
            pstm = coBdd.getConnection().prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                fiche = new FicheEtudiant(rs.getInt("id_fiche"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("diplome"), rs.getInt("telephone"), rs.getString("rue"), rs.getInt("cp"), rs.getString("ville"),rs.getInt("ref_utilisateur"));
                FicheEtudiant.add(fiche);
            }
        } catch (SQLException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
        return FicheEtudiant;
    }
}
