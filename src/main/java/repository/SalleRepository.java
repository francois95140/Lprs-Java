package repository;

import BDD.Database;
import modele.Salle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SalleRepository {
    private Database coBdd;
    private String table = "salle";

    public SalleRepository() {
        coBdd = new Database();
    }

    public ArrayList<Salle> getSalle(){
        ArrayList<Salle> Salles = new ArrayList<Salle>();
        Salle salle;
        String sql = "SELECT * FROM " + table;
        PreparedStatement pstm;
        try {
            pstm = coBdd.getConnection().prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                salle = new Salle(rs.getInt("id_salle"), rs.getInt("numero"), rs.getString("batiment"));
                Salles.add(salle);
            }
        } catch (SQLException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
        return Salles;
    };
}
