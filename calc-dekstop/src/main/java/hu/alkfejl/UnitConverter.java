package hu.alkfejl;

import Modell.UnitConv;
import Modell.UnitDAO;
import Modell.UnitDAOImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.SQLException;

public class UnitConverter {

    public TextField valueField;
    public TextField resultField;
    public ComboBox<String> from;
    public ComboBox<String> to;
    String ANS;

    public void setANS(String ANS) {
        this.ANS = ANS;
    }

    public ObservableList<String> units = FXCollections.observableArrayList("C°","F°","mg","g","dkg"
            ,"kg","t","hex","dec","bin","oct");

    public void switchToPrimary(ActionEvent actionEvent) throws IOException {
        App.setRoot("primary");
    }


    public void setValue(MouseEvent mouseEvent) {
        from.setItems(units);
        to.setItems(units);

    }


    public void onMouseClick(MouseEvent mouseEvent) {
        Button button= (Button) mouseEvent.getSource();
        String input =button.getText();

        if(Character.isDigit(input.charAt(0)) || input.equals(".") ){
            valueField.setText(valueField.getText()+input);
        }

    }

    public void clear(MouseEvent mouseEvent) {
        valueField.setText("");
        resultField.setText("");
        from.setValue("");
        to.setValue("");
    }

    public void ansOnMouseClick(MouseEvent mouseEvent) {
        valueField.setText(valueField.getText()+ANS);
    }

    public void getResult(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if(!from.getValue().equals("") && !to.getValue().equals("") && !valueField.getText().equals("")){

            if(from.getValue().equals(to.getValue())){
                resultField.setText(valueField.getText());
                ANS = resultField.getText();
            }
            else if(from.getValue().equals("C°") && to.getValue().equals("F°")){
                resultField.setText(String.valueOf(Double.parseDouble(valueField.getText())*1.8+32));
                ANS = resultField.getText();
            }
            else if(from.getValue().equals("F°") && to.getValue().equals("C°")){
                resultField.setText(String.valueOf(Double.parseDouble(valueField.getText())/1.8-32));

                ANS = resultField.getText();
            }
            else if(from.getValue().equals("hex") && to.getValue().equals("dec")){
                resultField.setText(String.valueOf(getDecimal(valueField.getText())));
            }
            else if(from.getValue().equals("dec") && to.getValue().equals("hex")){
             try {
                 int szam = Integer.parseInt(valueField.getText());
                 resultField.setText(String.valueOf(getHexFromDec(szam)));

             }catch (NumberFormatException e){
                 Alert a = new Alert(Alert.AlertType.ERROR,"Csak egész számot lehet váltani!");
                 a.showAndWait();
             }
            }
            else if(from.getValue().equals("dec") && to.getValue().equals("oct")){
                try{

                resultField.setText(String.valueOf(getDecimalFromOctal(Integer.parseInt(valueField.getText()))));
            }catch (NumberFormatException e){
                Alert a = new Alert(Alert.AlertType.ERROR,"Csak egész számot lehet váltani!");
                a.showAndWait();
            }
            }
            else if(from.getValue().equals("oct") && to.getValue().equals("dec")){
                try{
                resultField.setText(String.valueOf(toOctalFromDec(Integer.parseInt(valueField.getText()))));
            }catch (NumberFormatException e){
                Alert a = new Alert(Alert.AlertType.ERROR,"Csak egész számot lehet váltani!");
                a.showAndWait();
            }
            }
            else if(from.getValue().equals("dec") && to.getValue().equals("bin")){
                try{
                    resultField.setText((Integer.toBinaryString(Integer.parseInt(valueField.getText()))));

                }catch (NumberFormatException e){
                    e.printStackTrace();
                    Alert a = new Alert(Alert.AlertType.ERROR,"Csak egész számot lehet váltani!");
                    a.showAndWait();
                }
            }
            else if(from.getValue().equals("bin") && to.getValue().equals("dec")){
                try{
                    resultField.setText(String.valueOf(Integer.parseInt(valueField.getText(),2)));

                }catch (NumberFormatException e){
                    e.printStackTrace();
                    Alert a = new Alert(Alert.AlertType.ERROR,"Nem lehet váltani!");
                    a.showAndWait();
                }
            }
            else if(from.getValue().equals("hex") && to.getValue().equals("bin")){
                try{
                   resultField.setText(Integer.toBinaryString(getDecimal(valueField.getText())));

                }catch (NumberFormatException e){
                    e.printStackTrace();
                    Alert a = new Alert(Alert.AlertType.ERROR,"Nem lehet váltani!");
                    a.showAndWait();
                }
            }
            else if(from.getValue().equals("hex") && to.getValue().equals("bin")){
                resultField.setText(Integer.toBinaryString(getDecimal(from.getValue())));
            }
            else if(from.getValue().equals("hex") && to.getValue().equals("oct")){
                resultField.setText(toOctalFromDec(getDecimal(from.getValue())));
            }
            else if(from.getValue().equals("oct") && to.getValue().equals("hex")){
                resultField.setText(getHexFromDec(getDecimalFromOctal(Integer.parseInt(valueField.getText()))));
            }
            else if (from.getValue().equals("bin") && to.getValue().equals("hex")){
                resultField.setText(String.valueOf(getDecimal(String.valueOf(Integer.parseInt(valueField.getText(),2)))));
            }
            else if(from.getValue().equals("bin") && to.getValue().equals("oct")){
                resultField.setText(String.valueOf(toOctalFromDec(Integer.parseInt(valueField.getText(),2))));
            }
            else if(from.getValue().equals("oct") && to.getValue().equals("bin")){
                resultField.setText(Integer.toBinaryString(getDecimalFromOctal(Integer.parseInt(valueField.getText()))));
            }




            else if(from.getValue().equals("g") ||from.getValue().equals("dkg") ||from.getValue().equals("kg")
            || from.getValue().equals("t") || from.getValue().equals("mg")){
                resultField.setText(toFinalUnit(to.getValue(),toKg(from.getValue(),valueField.getText())));
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("A megadott mértékegységet nem konvertálhatóak!");
                alert.showAndWait();

            }
            UnitDAO dao = new UnitDAOImpl();
            if(!resultField.getText().equals("")) {
                UnitConv tmp = new UnitConv();
                tmp.setMirol(from.getValue());
                tmp.setMire(to.getValue());
                tmp.setMennyit(Float.parseFloat(valueField.getText()));
                tmp.setAns(Float.parseFloat(resultField.getText()));
                dao.addToDB(tmp);
                setANS(resultField.getText());

            }


        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Hiányos kitöltés!");
            alert.showAndWait();
        }
    }
    public static String toOctalFromDec(int decimal){
        int rem;
        String octal="";

        char octalchars[]={'0','1','2','3','4','5','6','7'};

        while(decimal>0)
        {
            rem=decimal%8;
            octal=octalchars[rem]+octal;
            decimal=decimal/8;
        }
        return octal;
    }
    public static int getDecimalFromOctal(int octal){

        int decimal = 0;

        int n = 0;

        while(true){
            if(octal == 0){
                break;
            } else {
                int temp = octal%10;
                decimal += temp*Math.pow(8, n);
                octal = octal/10;
                n++;
            }
        }
        return decimal;
    }
    public static String getHexFromDec(int decimal){
        int rem;
        String hex="";
        char hexchars[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        while(decimal>0)
        {
            rem=decimal%16;
            hex=hexchars[rem]+hex;
            decimal=decimal/16;
        }
        return hex;
    }
    public float toKg(String from,String mennyiseg){

        switch (from) {
            case "mg":
                return Float.parseFloat(mennyiseg) / 10000;
            case "g":
                return Float.parseFloat(mennyiseg) / 1000;
            case "dkg":
                return Float.parseFloat(mennyiseg) / 100;
            case "kg":
                return Float.parseFloat(mennyiseg);
            default:
                return Float.parseFloat(mennyiseg) * 1000;
        }
    }
    public static int getDecimal(String hex){
        String digits = "0123456789ABCDEF";
        hex = hex.toUpperCase();
        int val = 0;
        for (int i = 0; i < hex.length(); i++)
        {
            char c = hex.charAt(i);
            int d = digits.indexOf(c);
            val = 16*val + d;
        }
        return val;
    }

    public String toFinalUnit(String to,float inKg){
        switch (to) {
            case "mg":
                return String.valueOf(inKg * 10000);
            case "g":
                return String.valueOf(inKg * 1000);
            case "dkg":
                return String.valueOf(inKg * 100);
            case "kg":
                return String.valueOf(inKg);
            default:
                return String.valueOf(inKg / 1000);
        }

    }

    public void deleteLastChar(MouseEvent mouseEvent) {
        valueField.setText(valueField.getText().substring(0,valueField.getText().length()-1));
    }

    public void toUnitHistory() throws IOException {

            App.setRoot("unitHistory");

    }
}
