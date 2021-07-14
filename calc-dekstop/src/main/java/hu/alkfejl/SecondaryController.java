package hu.alkfejl;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.SQLException;

import Modell.CurrencyConv;
import Modell.CurrencyDAO;
import Modell.CurrencyDAOImpl;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import javax.net.ssl.HttpsURLConnection;

public class SecondaryController {

    private final String API_KEY="3914bdb7b2e07ae9016d869a";
    public TextField valueField;
    public TextField resultField;
    private String ANS;
    public ObservableList<String> currencies = FXCollections.observableArrayList("eur","huf","usd","gbp"
            ,"cad","bgn","egp","cny","chf","jpy","mxn","aed",
            "afn","all","amd","ang","aoa","ars","aud","awg","azn"
    ,"bam","bbd","bdt","czk","sek","rsd","rub","pln","ron","dkk","try");
    @FXML
    public ComboBox <String>from;

    @FXML
    public ComboBox <String> to;






    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");


    }

    public void onMouseClick(MouseEvent mouseEvent) {
        Button button= (Button) mouseEvent.getSource();
        String input =button.getText();

        if(Character.isDigit(input.charAt(0)) || input.equals(".") ){
            valueField.setText(valueField.getText()+input);
        }




    }


    public void getResult() {
        if(from.getValue()!=null && to.getValue()!=null && !valueField.getText().isEmpty()){

            String urlString= "https://v6.exchangerate-api.com/v6/"+API_KEY+"/pair/"+from.getValue()
                    +"/"+to.getValue()+"/"+valueField.getText();


            try {
                URL url = new URL(urlString);
                HttpsURLConnection request = (HttpsURLConnection) url.openConnection();
                request.connect();
                JsonParser jp = new JsonParser();
                JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
                JsonObject jsonObj = root.getAsJsonObject();
                if(!jsonObj.get("result").getAsString().equals("success")){
                    Alert alert = new Alert(Alert.AlertType.WARNING,"Kérem ellenőrizze az internet kapcsolatát!");
                    alert.showAndWait();
                    return;
                }
                resultField.setText((jsonObj.get("conversion_result").getAsString()));
                ANS=resultField.getText();

                if(!resultField.getText().equals("")) {
                    CurrencyConv cc= new CurrencyConv(Float.parseFloat(valueField.getText()),from.getValue(),Float.parseFloat(resultField.getText()),to.getValue());
                    CurrencyDAO dao = new CurrencyDAOImpl();
                    dao.addToDB(cc);
                }


            } catch (IOException | SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }


        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Hiányos kitöltés!");
            alert.showAndWait();
        }

    }

    public void setValue() {
        from.setItems(currencies);
        to.setItems(currencies);
    }

    public void ansOnMouseClick() {
        valueField.setText(ANS);
    }

    public void clear() {
        valueField.setText("");
        resultField.setText("");
        from.setValue("");
        to.setValue("");
    }

    public void deleteLastChar() {
        valueField.setText(valueField.getText().substring(0,valueField.getText().length()-1));
    }

    public void toCurrHistory(ActionEvent actionEvent) throws IOException {
        App.setRoot("/currHistory");
    }
}