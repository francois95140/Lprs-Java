package repository;
import BDD.Database;
import modele.FicheEtudiant;
import modele.FicheFourniture;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
public class FicheFournitureRepository {
    private Database coBDD;
    private String table = "fiche_fourniture";

    public FicheFournitureRepository(){coBDD = new Database();}

    public FicheFourniture fiche_fourniture(FicheFourniture fiche_fourniture) throws SQLException{
        String sql;
        PreparedStatement pstm;
        sql = "INSERT INTO `"+table+"`(`nom`, `prix`, `quantite`) VALUES(?,?,?)";
        pstm = coBDD.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        pstm.setString(1, fiche_fourniture.getnom());
        pstm.setDouble(2, fiche_fourniture.getprix());
        pstm.setInt(3, fiche_fourniture.getquantite());
        pstm.executeUpdate();
        ResultSet rs =pstm.getGeneratedKeys();
        if(rs.next()){
            int last_inserted_id = rs.getInt(1);
            fiche_fourniture.setId_fiche_fourniture(last_inserted_id);
        }
        return fiche_fourniture;
    }
        public ArrayList<FicheFourniture> getfiche_fourniture(){
        ArrayList<FicheFourniture> fichefournitures = new ArrayList<FicheFourniture>();

        FicheFourniture ficheFourniture;
        String sql = "SELECT * FROM"+table;
        PreparedStatement pstm = null;
        try{
            ResultSet rs = pstm.executeQuery();
            pstm = coBDD.getConnection().prepareStatement(sql);
            pstm.setInt(1, id_fiche_fourniture);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        }



}
