import Modell.CurrencyConv;
import Modell.CurrencyDAO;
import Modell.CurrencyDAOImpl;
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


@WebServlet("/CurrServlet")
public class CurrServlet extends HttpServlet {
    private final String API_KEY = "3914bdb7b2e07ae9016d869a";
    String currResult;
    StringBuilder builder = new StringBuilder();
    String ANS;
    String fromCurr;
    String toCurr;
    List<CurrencyConv> sessionCurrList = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");
        if (req.getParameter("from") != null && req.getParameter("to") != null && req.getParameter("=") != null) {

            fromCurr = req.getParameter("from");
            toCurr = req.getParameter("to");

            String urlString = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/pair/" + fromCurr
                    + "/" + toCurr + "/" + currResult;


            try {
                URL url = new URL(urlString);
                HttpsURLConnection request = (HttpsURLConnection) url.openConnection();
                request.connect();
                JsonParser jp = new JsonParser();
                JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
                JsonObject jsonObj = root.getAsJsonObject();
                builder = new StringBuilder((jsonObj.get("conversion_result").getAsString()));
                ANS = builder.toString();

                if (!builder.toString().isEmpty()) {
                    CurrencyDAO dao = new CurrencyDAOImpl();

                    CurrencyConv tmp = new CurrencyConv(Float.parseFloat(currResult),fromCurr,Float.parseFloat(ANS),toCurr);
                    dao.addToDB(tmp);
                    sessionCurrList.add(tmp);

                }


            } catch (IOException | SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else if (req.getParameter("0") != null) builder.append("0");
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

        req.setAttribute("from", fromCurr);
        req.setAttribute("to", toCurr);
        req.setAttribute("currResult", currResult);
        req.getRequestDispatcher("currency.jsp").forward(req, resp);

    }


    public void updateRes(StringBuilder sb) {
        this.currResult = sb.toString();
    }
}


