package Modell;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SzamitasDAOImpl implements SzamitasDAO {

    Connection con ;
    ResultSet rs;
    Statement stmt;
    private final String SELECT_ALL ="SELECT szamitas, eredmeny, idopont  FROM SZAMITASOK";
    private final String ADD_TO_DB="INSERT INTO szamitasok (szamitas, eredmeny, idopont) VALUES (?, ?, ?)";
    String connection_URL;

    public SzamitasDAOImpl() throws SQLException, ClassNotFoundException, IOException {
        Class.forName("org.sqlite.JDBC");
        Properties props= new Properties();
        props.load(getClass().getResourceAsStream("/db.properties"));
        connection_URL = props.getProperty("DBURL");
        this.con = DriverManager.getConnection(connection_URL);
        this.stmt = con.createStatement();



    }

    @Override
    public List<Szamitas> findAll()  {

        List<Szamitas> lista = new ArrayList<>();
        try {

            rs = stmt.executeQuery(SELECT_ALL);
            while (rs.next()) {
                lista.add(new Szamitas(rs.getString(1), rs.getString(2), rs.getString(3)));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public boolean addToDB(Szamitas szamitas) {
        try {


            if(stmt.executeUpdate("insert into szamitasok ( `szamitas`, `eredmeny`,`idopont`) values('" + szamitas.getSzamitas() + "','" + szamitas.getEredmeny() + "','" + szamitas.getIdopont() + "' );")
        ==1){
             return true;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;

    }
}
