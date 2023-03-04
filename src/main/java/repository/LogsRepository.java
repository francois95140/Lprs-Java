package repository;

import BDD.Database;
import modele.Logs;
import modele.Utilisateur;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LogsRepository {

    private Database coBdd;
    private String table = "logs";

    public LogsRepository() {
        coBdd = new Database();
    }

    public Logs Insert (int ref_compte, String adresse) throws SQLException {
        String sql;
        PreparedStatement pstm;
        System.out.println(ref_compte + adresse);
        sql = "INSERT INTO `"+table+"`(`ref_compte`,`adresse_ip`) VALUES (?,?)";
        pstm = coBdd.getConnection().prepareStatement(sql);
        pstm.setInt(1,ref_compte);
        pstm.setString(2,adresse);
        pstm.executeUpdate();
        return null;
    }

    public ArrayList<Logs> getLogs() {
        ArrayList<Logs> Logs = new ArrayList<Logs>();
        Logs logs;
        String sql = "SELECT * FROM " + table +" ORDER BY " +table+".`id_logs` DESC";
        PreparedStatement pstm;
        try {
            pstm = coBdd.getConnection().prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                logs = new Logs(rs.getInt("id_logs"), rs.getInt("ref_compte"), rs.getString("adresse_ip"), rs.getString("date") );
                Logs.add(logs);
            }
        } catch (SQLException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
        return Logs;
    }

}
