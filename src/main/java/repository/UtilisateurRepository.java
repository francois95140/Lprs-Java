package repository;

import BDD.Database;
import modele.Utilisateur;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UtilisateurRepository {
    private Database coBdd;
    private String table = "Utilisateur";

    public UtilisateurRepository() {
        coBdd = new Database();
    }

    public Utilisateur inscription (Utilisateur utilisateur) throws SQLException{
        String sql;
        PreparedStatement pstm;
        if(utilisateur.getIdUtilisateur()>0) {
            sql = "UPDATE `"+table+"` SET `nom`=?,`prenom`=?,`email`=?,`mdp`=?,`role`=? WHERE id_user=?";
            pstm = coBdd.getConnection().prepareStatement(sql);
            pstm.setString(1, utilisateur.getNom());
            pstm.setString(2, utilisateur.getPrenom());
            pstm.setString(3, utilisateur.getEmail());
            pstm.setString(4, utilisateur.getMdp());
            pstm.setString(5, String.valueOf(utilisateur.getRole()));
            pstm.setInt(6, utilisateur.getIdUtilisateur());
            pstm.executeUpdate();

        }
//insert
        else {
            sql = "INSERT INTO `"+table+"`( `nom`, `prenom`, `email`,`mdp`,`role`) VALUES (?,?,?,md5(?),?)";

            pstm = coBdd.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, utilisateur.getNom());
            pstm.setString(2, utilisateur.getPrenom());
            pstm.setString(3, utilisateur.getEmail());
            pstm.setString(4, utilisateur.getMdp());
            pstm.setString(5, String.valueOf(utilisateur.getRole()));
            pstm.executeUpdate();
            ResultSet rs = pstm.getGeneratedKeys();
            if(rs.next())
            {
                int last_inserted_id = rs.getInt(1);
                System.out.println(last_inserted_id);
                utilisateur.setIdUtilisateur(last_inserted_id);
            }

        }
        return utilisateur;
    }


    public Utilisateur connexion(String email, String motDePasse) {
        Utilisateur Utilisateur = null;
        String sql = "SELECT * FROM " + table + " WHERE email=? and mdp=md5(?)";
        PreparedStatement pstm;
        try {
            pstm = coBdd.getConnection().prepareStatement(sql);
            pstm.setString(1, email);
            pstm.setString(2, motDePasse);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {

                Utilisateur = new Utilisateur(rs.getInt("id_user"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("mdp"), rs.getInt("role"));
            }
        } catch (SQLException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }

        return Utilisateur;

    }

    public Utilisateur getUserByMail(String email) {
        Utilisateur utilisateur = null;
        String sql = "SELECT * FROM " + table + " WHERE email=?";
        PreparedStatement pstm;
        try {
            pstm = coBdd.getConnection().prepareStatement(sql);
            pstm.setString(1, email);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                System.out.println(rs.getString("nom"));
                utilisateur = new Utilisateur(rs.getInt("id_user"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("mdp"), rs.getInt("role"));
            }
        } catch (SQLException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }

        return utilisateur;
    }

    public ArrayList<Utilisateur> getUtilisateurs() {
        ArrayList<Utilisateur> Utilisateurs = new ArrayList<Utilisateur>();
        Utilisateur utilisateur;
        String sql = "SELECT * FROM " + table;
        PreparedStatement pstm;
        try {
            pstm = coBdd.getConnection().prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                utilisateur = new Utilisateur(rs.getInt("id_user"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getInt("role"));
                Utilisateurs.add(utilisateur);
            }
        } catch (SQLException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
        return Utilisateurs;
    }

    public void changepasseword(Utilisateur utilisateur) throws SQLException {
        System.out.println(utilisateur.getMdp());
        String sql;
        PreparedStatement pstm;
        sql = "UPDATE `" + table + "` SET `mdp`=md5(?) WHERE id_user=?";
        pstm = coBdd.getConnection().prepareStatement(sql);
        System.out.println(utilisateur.getMdp());
        pstm.setString(1, utilisateur.getMdp());
        pstm.setInt(2, utilisateur.getIdUtilisateur());
        System.out.println(utilisateur.getIdUtilisateur());
        System.out.println("good");
        pstm.executeUpdate();
    }

}
