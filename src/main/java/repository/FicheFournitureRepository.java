package repository;
import BDD.Database;

import modele.FicheFourniture;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class FicheFournitureRepository {
    private Database coBDD;
    private String table = "fiche_fourniture";

    public FicheFournitureRepository() {
        coBDD = new Database();
    }

    public FicheFourniture Insert(FicheFourniture fiche_fourniture) throws SQLException {
        String sql;
        PreparedStatement pstm;
        if (fiche_fourniture.getId_fiche_fourniture() > 0) {
            sql = "UPDATE `" + table + "` SET `ref_fournisseur`=?,`prix`=?,`ref_fourniture`=? WHERE getId_fiche_fourniture=?";
            pstm = coBDD.getConnection().prepareStatement(sql);
            pstm.setInt(1, fiche_fourniture.getRef_fourniture());
            pstm.setDouble(2, fiche_fourniture.getPrix());
            pstm.setInt(3, fiche_fourniture.getRef_fournisseur());

            pstm.executeUpdate();

        }
//insert
        else {
            sql = "INSERT INTO `" + table + "`(`ref_fournisseur`, `prix`, `ref_fourniture`, `ref_utilisateur`) VALUES(?,?,?,?)";
            pstm = coBDD.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstm.setInt(1, fiche_fourniture.getRef_fourniture());
            pstm.setDouble(2, fiche_fourniture.getPrix());
            pstm.setInt(3, fiche_fourniture.getRef_fournisseur());
            pstm.setInt(4, fiche_fourniture.getRef_utilisateur());
            pstm.executeUpdate();
            ResultSet rs = pstm.getGeneratedKeys();
            if (rs.next()) {
                int last_inserted_id = rs.getInt(1);
                fiche_fourniture.setId_fiche_fourniture(last_inserted_id);
            }
        }
            return fiche_fourniture;
        }


        public ArrayList<FicheFourniture> getfiche_fourniture(){
            ArrayList<FicheFourniture> ficheFournitures = new ArrayList<FicheFourniture>();
            FicheFourniture fiche_fourniture;
            String sql = "SELECT * FROM " + table;
            PreparedStatement pstm;
            try {
                pstm = coBDD.getConnection().prepareStatement(sql);
                ResultSet rs = pstm.executeQuery();
                while (rs.next()) {
                    fiche_fourniture = new FicheFourniture(rs.getInt("id_fiche_fourniture"), rs.getInt("ref_fournisseur"), rs.getDouble("prix"), rs.getInt("ref_fourniture"), rs.getInt("ref_utilisateur"));
                    ficheFournitures.add(fiche_fourniture);
                }
            } catch (SQLException e) {
// TODO Auto-generated catch block
                e.printStackTrace();
            }
            return ficheFournitures;
        }


    }


