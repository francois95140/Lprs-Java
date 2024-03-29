package repository;

import BDD.Database;
import modele.Demande;
import modele.Fourniture;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class FournitureRepository {


    private Database coBdd;
    private String table = "Fourniture";

    public FournitureRepository(){
        coBdd = new Database();
    }

    public Fourniture inscription(Fourniture fourniture) throws SQLException {
        String sql;
        PreparedStatement pstm;
        if (fourniture.getIdFourniture() > 0) {
            sql = "UPDATE `" + table + "` SET `nom`=?,`stock`=?,`fourniseur`=? WHERE getIdFourniture=?";
            pstm = coBdd.getConnection().prepareStatement(sql);
            pstm.setString(1, fourniture.getNom());
            pstm.setInt(2, fourniture.getStock());
            pstm.setString(3, fourniture.getFournisseur());

            pstm.executeUpdate();

        }
//insert
        else {
            sql = "INSERT INTO `" + table + "`( `nom`, `stock`,`ref_utilisateur`) VALUES (?,?,?)";

            pstm = coBdd.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, fourniture.getNom());
            pstm.setInt(2, fourniture.getStock());
            pstm.setInt(3, fourniture.getRef_utilisateur());
            pstm.executeUpdate();
            ResultSet rs = pstm.getGeneratedKeys();
            if (rs.next()) {
                int last_inserted_id = rs.getInt(1);
                fourniture.setIdFourniture(last_inserted_id);
            }

        }
        return fourniture;
    }

    public ArrayList<Fourniture> getfourniture(){
        ArrayList<Fourniture> Fournitures = new ArrayList<Fourniture>();
        Fourniture fourniture;
        String sql = "SELECT * FROM " + table;
        PreparedStatement pstm;
        try {
            pstm = coBdd.getConnection().prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                fourniture = new Fourniture(rs.getInt("id_fourniture"), rs.getString("nom"), rs.getInt("stock"));
                Fournitures.add(fourniture);
            }
        } catch (SQLException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
        return Fournitures;
    }

    public void setStock(String fourniture, int qte) throws SQLException {
        String sql;
        PreparedStatement pstm;
        sql = "UPDATE `" + table + "` SET `stock`= fourniture.stock - ? WHERE `nom`=?";
        pstm = coBdd.getConnection().prepareStatement(sql);
        pstm.setInt(1, qte);
        pstm.setString(2, fourniture);
        pstm.executeUpdate();
    }

}