package hu.alkfejl;

import Modell.UnitConv;
import Modell.UnitDAO;
import Modell.UnitDAOImpl;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UnitHistoryController implements Initializable {


    public TableView<UnitConv> tabla;
    public TableColumn<UnitConv, FloatProperty> col_mennyit;
    public TableColumn<UnitConv, StringProperty> col_mirol;
    public TableColumn<UnitConv,FloatProperty> col_eredmeny;
    public TableColumn<UnitConv,StringProperty> col_mire;
    ObservableList<UnitConv> lista= FXCollections.observableArrayList();




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        col_mennyit.setCellValueFactory(new PropertyValueFactory<>("mennyit"));
        col_mirol.setCellValueFactory(new PropertyValueFactory<>("mirol"));
        col_eredmeny.setCellValueFactory(new PropertyValueFactory<>("ans"));
        col_mire.setCellValueFactory(new PropertyValueFactory<>("mire"));
        try {
            UnitDAO dao = new UnitDAOImpl();
            lista.addAll(dao.findAll());
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        tabla.setItems(lista);


    }

    public void backToPrimary(ActionEvent actionEvent) {
        try {
            App.setRoot("primary");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
