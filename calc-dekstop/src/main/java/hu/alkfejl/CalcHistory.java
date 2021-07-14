package hu.alkfejl;

import Modell.Szamitas;
import Modell.SzamitasDAO;
import Modell.SzamitasDAOImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class CalcHistory implements Initializable {
    public TableView<Szamitas> tabla;
    public TableColumn<Szamitas,String> col_szamitas;
    public TableColumn<Szamitas,String> col_eredmeny;
    public TableColumn<Szamitas,String> col_idopont;
    public ObservableList<Szamitas> list = FXCollections.observableArrayList();
    public List<Szamitas> sessionList = new ArrayList<>();

    public void setSessionList(List<Szamitas> sessionList) {
        this.sessionList = sessionList;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        col_szamitas.setCellValueFactory(new PropertyValueFactory<>("szamitas"));
        col_eredmeny.setCellValueFactory(new PropertyValueFactory<>("eredmeny"));
        col_idopont.setCellValueFactory(new PropertyValueFactory<>("idopont"));

        try {

            SzamitasDAO dao = new SzamitasDAOImpl();
            list.setAll( dao.findAll());
        } catch (SQLException | ClassNotFoundException | IOException throwables) {
            throwables.printStackTrace();
        }
        tabla.setItems(list);





    }


    public void backToPrimary(ActionEvent actionEvent) {
        try {
            App.setRoot("primary");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public void fillFromStack() {

       /*
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/fxml/primary.fxml"));
        PrimaryController prim=loader.getController();
        list.clear();
        setSessionList(prim.history);
        list.setAll( sessionList);
        tabla.setItems(list);
        tabla.refresh();
        */
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("Sikertelen megvalósítás, elnézést!");
        alert.showAndWait();
    }

}
