package Modell;

import Modell.UnitConv;
import Modell.UnitDAO;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class UnitDAOImpl implements UnitDAO {
    Connection con ;
    ResultSet rs;
    Statement stmt;
    private final String SELECT_ALL ="SELECT mennyit, mirol, ans, mire FROM mertekegyseg";
    String connection_URL;

    public UnitDAOImpl() throws SQLException, ClassNotFoundException {
        try{
        Class.forName("org.sqlite.JDBC");
            Properties props= new Properties();
            props.load(getClass().getResourceAsStream("/db.properties"));
            connection_URL = props.getProperty("DBURL");
        this.con = DriverManager.getConnection(connection_URL);
            this.stmt = con.createStatement();
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<UnitConv> findAll() {
        List<UnitConv> lista = new ArrayList<>();
        try {
            rs =stmt.executeQuery(SELECT_ALL);
            while(rs.next()){
                UnitConv tmp = new UnitConv();
                tmp.setMennyit(rs.getFloat("mennyit"));
                tmp.setMirol(rs.getString("mirol"));
                tmp.setAns(rs.getFloat("ans"));
                tmp.setMire(rs.getString("mire"));
                lista.add(tmp);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return lista;
    }

    @Override
    public boolean addToDB(UnitConv uc) {

        try {
           if( stmt.executeUpdate("insert into mertekegyseg ( `mennyit`, `mirol`,`ans`,`mire`) values(" + uc.getMennyit() + ",'" + uc.getMirol() + "'," + uc.getAns() + ",'" + uc.getMire() + "' );")==1)
            {
                return true;
            }



        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
