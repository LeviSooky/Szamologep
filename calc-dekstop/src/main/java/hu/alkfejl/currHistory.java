package hu.alkfejl;

import Modell.CurrencyConv;
import Modell.CurrencyDAO;
import Modell.CurrencyDAOImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class currHistory implements Initializable {


    public TableView<CurrencyConv> tabla;
    public TableColumn<CurrencyConv, Float> col_mennyit;
    public TableColumn<CurrencyConv, String> col_mirol;
    public TableColumn<CurrencyConv, Float> col_eredmeny;
    public TableColumn<CurrencyConv, String> col_mire;
    public ObservableList<CurrencyConv> list = FXCollections.observableArrayList();


    public void backToPrimary(ActionEvent actionEvent) throws IOException {

        App.setRoot("primary");

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        col_mennyit.setCellValueFactory(new PropertyValueFactory<>("mennyit"));
        col_mirol.setCellValueFactory(new PropertyValueFactory<>("mirol"));
        col_eredmeny.setCellValueFactory(new PropertyValueFactory<>("ans"));
        col_mire.setCellValueFactory(new PropertyValueFactory<>("mire"));


        try {

            CurrencyDAO dao = new CurrencyDAOImpl();
            list.addAll(dao.findAll());
            tabla.setItems(list);
        } catch (SQLException | ClassNotFoundException | IOException throwables) {
            throwables.printStackTrace();
        }


    }
}
