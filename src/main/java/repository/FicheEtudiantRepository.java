package repository;
import BDD.Database;
import modele.FicheEtudiant;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class  FicheEtudiantRepository
{
    private Database coBdd;
    private String table = "fiche_etudiant";


    public FicheEtudiantRepository() {
        coBdd = new Database();
    }
    public FicheEtudiant inscription(FicheEtudiant etudiant)  throws SQLException{

      String sql;
      PreparedStatement pstm;

        sql = "INSERT INTO` "+table+" `(`nom`, `prenom`,`diplome`, `telephone`, `rue`, `cp`, `ville`) VALUES (?,?,?,?,?,?,?)";
        pstm = coBdd.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        pstm.setString(1, etudiant.getNom());
        pstm.setString(2, etudiant.getPrenom());
        pstm.setString(3, etudiant.getDiplome());
        pstm.setInt(4, etudiant.getTelephone());
        pstm.setString(5, etudiant.getRue());
        pstm.setInt(6, etudiant.getCp());
        pstm.setString(7, etudiant.getVille());
        pstm.executeUpdate();
        ResultSet rs = pstm.getGeneratedKeys();
        if (rs.next()){
            int last_inserted_id = rs.getInt(1);
            etudiant.setIdFiche(last_inserted_id);
        }
        return etudiant;
    }
}
