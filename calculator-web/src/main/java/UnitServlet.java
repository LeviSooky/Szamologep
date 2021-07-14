import Modell.UnitConv;
import Modell.UnitDAO;
import Modell.UnitDAOImpl;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/UnitServlet")
public class UnitServlet  extends HttpServlet {

    String unitResult;
    StringBuilder builder = new StringBuilder();
    String ANS;
    String fromUnit;
    String toUnit;
    List<UnitConv> sessionUnitList= new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");
        if (req.getParameter("from") != null && req.getParameter("to") != null && req.getParameter("=") != null) {

            fromUnit = req.getParameter("from");
            toUnit = req.getParameter("to");

            if(!fromUnit.equals(toUnit)){
                if(fromUnit.equals("C°") && toUnit.equals("F°")){
                    builder= new StringBuilder(String.valueOf(Double.parseDouble(unitResult)*1.8+32));
                    ANS = builder.toString();
                }
                else if(fromUnit.equals("F°") && toUnit.equals("C°")){
                    builder= new StringBuilder(String.valueOf(Double.parseDouble(unitResult)/1.8-32));
                    ANS = builder.toString();
                }

                else if(fromUnit.equals("hex") && toUnit.equals("dec")){
                    builder= new StringBuilder(String.valueOf(getDecimal(unitResult)));
                }
                else if(fromUnit.equals("dec") && toUnit.equals("hex")){
                    try {
                        int szam = Integer.parseInt(unitResult);
                        builder= new StringBuilder((String.valueOf(getHexFromDec(szam))));

                    }catch (NumberFormatException e){

                    }
                }
                else if(fromUnit.equals("dec") && toUnit.equals("oct")){
                    try{

                        builder= new StringBuilder((String.valueOf(getDecimalFromOctal(Integer.parseInt(unitResult)))));
                    }catch (NumberFormatException e){

                    }
                }
                else if(fromUnit.equals("oct") && toUnit.equals("dec")){
                    try{
                        builder= new StringBuilder((String.valueOf(toOctalFromDec(Integer.parseInt(unitResult)))));
                    }catch (NumberFormatException e){

                    }
                }
                else if(fromUnit.equals("dec") && toUnit.equals("bin")){
                    try{
                        builder= new StringBuilder((Integer.toBinaryString(Integer.parseInt(unitResult))));

                    }catch (NumberFormatException e){
                        e.printStackTrace();

                    }
                }
                else if(fromUnit.equals("bin") && toUnit.equals("dec")){
                    try{
                        builder= new StringBuilder((String.valueOf(Integer.parseInt(unitResult,2))));

                    }catch (NumberFormatException e){
                        e.printStackTrace();

                    }
                }
                else if(fromUnit.equals("hex") && toUnit.equals("bin")){
                    try{
                        builder= new StringBuilder((Integer.toBinaryString(getDecimal(unitResult))));

                    }catch (NumberFormatException e){
                        e.printStackTrace();

                    }
                }
                else if(fromUnit.equals("hex") && toUnit.equals("bin")){
                    builder= new StringBuilder((Integer.toBinaryString(getDecimal(unitResult))));
                }
                else if(fromUnit.equals("hex") && toUnit.equals("oct")){
                    builder= new StringBuilder(toOctalFromDec(getDecimal(unitResult)));
                }
                else if(fromUnit.equals("oct") && toUnit.equals("hex")){
                    builder= new StringBuilder(getHexFromDec(getDecimalFromOctal(Integer.parseInt(unitResult))));
                }
                else if (fromUnit.equals("bin") && toUnit.equals("hex")){
                    builder= new StringBuilder(String.valueOf(getDecimal(String.valueOf(Integer.parseInt(unitResult,2)))));
                }
                else if(fromUnit.equals("bin") && toUnit.equals("oct")){
                    builder= new StringBuilder(String.valueOf(toOctalFromDec(Integer.parseInt(unitResult,2))));
                }
                else if(fromUnit.equals("oct") && toUnit.equals("bin")){
                    builder= new StringBuilder(Integer.toBinaryString(getDecimalFromOctal(Integer.parseInt(unitResult))));
                }
                else if((fromUnit.equals("mg") || fromUnit.equals("g") || fromUnit.equals("dkg")
                || fromUnit.equals("kg") || fromUnit.equals("t")) &&toUnit.equals("mg") || toUnit.equals("g") || toUnit.equals("dkg")
                        || toUnit.equals("kg") || toUnit.equals("t")){

                    builder= new StringBuilder(toFinalUnit(toUnit,toKg(fromUnit,unitResult)));


                }

            }
            try {
                UnitDAO dao = new UnitDAOImpl();
                UnitConv uc = new UnitConv();
                uc.setMennyit(Float.parseFloat(unitResult));
                uc.setMirol(fromUnit);
                uc.setAns(Float.parseFloat(builder.toString()));
                uc.setMire(toUnit);
                dao.addToDB(uc);
                sessionUnitList.add(uc);


            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }


        }
        else if (req.getParameter("0") != null) builder.append("0");
        else if (req.getParameter("1") != null) builder.append("1");
        else if (req.getParameter("2") != null) builder.append("2");
        else if (req.getParameter("3") != null) builder.append("3");
        else if (req.getParameter("4") != null) builder.append("4");
        else if (req.getParameter("5") != null) builder.append("5");
        else if (req.getParameter("6") != null) builder.append("6");
        else if (req.getParameter("7") != null) builder.append("7");
        else if (req.getParameter("8") != null) builder.append("8");
        else if (req.getParameter("9") != null) builder.append("9");
        else if (req.getParameter("C") != null) builder= new StringBuilder();
        else if(req.getParameter(".")!=null) builder.append(".");
        else if(req.getParameter("ANS")!=null) builder.append(ANS);
        else if(req.getParameter("DEL")!=null){
            if(builder.length()>=2){
                builder.deleteCharAt(builder.length()-1);
            }
            else if(builder.length()==1){
                builder= new StringBuilder();

            }

        }


        updateRes(builder);



        req.setAttribute("from", fromUnit);
        req.setAttribute("to", toUnit);
        req.setAttribute("unitResult", unitResult);
        req.getRequestDispatcher("unit.jsp").forward(req, resp);

    }


    public void updateRes(StringBuilder sb) {
        this.unitResult = sb.toString();
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
    public static int getDecimal(String hex) {
        String digits = "0123456789ABCDEF";
        hex = hex.toUpperCase();
        int val = 0;
        for (int i = 0; i < hex.length(); i++) {
            char c = hex.charAt(i);
            int d = digits.indexOf(c);
            val = 16 * val + d;
        }
        return val;
    }


    }

