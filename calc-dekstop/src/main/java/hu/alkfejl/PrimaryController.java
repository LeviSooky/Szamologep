package hu.alkfejl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Modell.Szamitas;
import Modell.SzamitasDAO;
import Modell.SzamitasDAOImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.mariuszgromada.math.mxparser.Expression;


import java.sql.*;



public class PrimaryController {
    @FXML
    private TextField inputText;
    @FXML
    private TextField outputText;
    @FXML
    Button showHistory;
    public  List<Szamitas> history= new ArrayList<>();

    public  List<Szamitas> getHistory() {
        return history;
    }
    private String ANS;

    public void setANS(String ANS) {
        this.ANS = ANS;
    }

    public PrimaryController() {
    }

     @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");

    }








    public void setOutputText(TextField outputText) {
        this.outputText = outputText;
    }

    public TextField getOutputText() {
        return outputText;
    }

    public void onMouseClick(MouseEvent mouseEvent) {
        Button button= (Button) mouseEvent.getSource();
        String input =button.getText();

        if(input.equals(",")){
            inputText.setText(inputText.getText()+".");
        }
        else if(input.length()==1 && (Character.isDigit(input.charAt(0)) )){
            inputText.setText(inputText.getText()+input);
        }
        else if(input.equals("÷")){
            inputText.setText(inputText.getText()+"/");
        }
        else if(input.equals("^")){
            inputText.setText(inputText.getText()+"^");
        }
        else if(input.equals(")")){
            inputText.setText(inputText.getText()+")");
        }
        else if(input.equals("(")){
            inputText.setText(inputText.getText()+"(");
        }
        else if(input.length()==1){
            inputText.setText(inputText.getText()+input);
        }

        else if(input.equals("1/x")){
            inputText.setText(inputText.getText()+"1/(");
        }
        else if(input.equals("sin")){
            inputText.setText(inputText.getText()+"sin(");
        }
        else if(input.equals("cos")){
            inputText.setText(inputText.getText()+"cos(");
        }
        else if(input.equals("tan")){
            inputText.setText(inputText.getText()+"tan(");
        }
        else if(input.equals("sqrt(x)")){
            inputText.setText(inputText.getText()+"sqrt(");
        }






    }

    public void getResult(MouseEvent mouseEvent) {

        Button btn= (Button) mouseEvent.getSource();
        String input=btn.getText();
        if(input.equals("=")) {


            Expression ex = new Expression(inputText.getText());
            Double res = ex.calculate();

            if(!res.isNaN()) {
                Szamitas tmp = new Szamitas(inputText.getText(), String.valueOf(res));
                inputText.setText("");
                history.add(tmp);
                setANS(tmp.getEredmeny());

                outputText.setText(String.valueOf(res));





                try {
                    SzamitasDAO dao = new SzamitasDAOImpl();
                    dao.addToDB(tmp);
                } catch (SQLException | ClassNotFoundException | IOException throwables) {
                    throwables.printStackTrace();
                }

            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Hibás szintaxis!");
                alert.showAndWait();

            }





        }

    }


    public void deleteLastChar(ActionEvent actionEvent) {
try {
    if(inputText.getText().length()==0);
    else if (inputText.getText().charAt(inputText.getText().length() - 1) == ' ') {
        inputText.setText(inputText.getText().substring(0, inputText.getText().length() - 3));
    } else {
        inputText.setText(inputText.getText().substring(0, inputText.getText().length() - 1));
    }
}
catch (Exception e){
 e.printStackTrace();
}
    }



    public void deleteInput(MouseEvent mouseEvent) {
        inputText.setText("");
    }



    public void showHis() throws IOException {
        App.setRoot("calcHistory");
       /* FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/calcHistory.fxml"));
        CalcHistory sc = loader.getController();
        sc.setSessionList(history);


        App.setRoot("calcHistory");



        */
        
    }


    public void switchToUnitConverter(ActionEvent actionEvent) {
        try {
            App.setRoot("unitConverter");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void putANS() {
        inputText.setText(inputText.getText()+ANS);
    }
}
