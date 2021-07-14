package Modell;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CurrencyDAOImpl implements CurrencyDAO {

    Connection con ;
    ResultSet rs;
    Statement stmt;
    private final String SELECT_ALL ="SELECT mennyit, mirol, ans, mire FROM ARFOLYAM ";
    String connection_URL;



    public CurrencyDAOImpl() throws SQLException, ClassNotFoundException, IOException {
        Class.forName("org.sqlite.JDBC");
        Properties props= new Properties();
        props.load(getClass().getResourceAsStream("/db.properties"));
        connection_URL = props.getProperty("DBURL");
        this.con = DriverManager.getConnection(connection_URL);
        this.stmt = con.createStatement();

    }

    @Override
    public List<CurrencyConv> findAll() {
        List<CurrencyConv> lista = new ArrayList<>();
        try {

            rs = stmt.executeQuery(SELECT_ALL);
            while (rs.next()) {
                lista.add(new CurrencyConv(rs.getFloat(1), rs.getString(2), rs.getFloat(3),rs.getString(4)));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return lista;

    }

    @Override
    public boolean addToDB(CurrencyConv sz) {
        try {


            if(stmt.executeUpdate("insert into arfolyam ( `mennyit`, `mirol`,`ans`,`mire`) values(" + sz.getMennyit() + ",'" + sz.getMirol() + "'," + sz.getAns() + ",'" + sz.getMire() + "' );")
==1){
                return true;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;

    }
    }

