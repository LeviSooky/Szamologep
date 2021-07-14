import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Modell.Szamitas;
import Modell.SzamitasDAO;
import Modell.SzamitasDAOImpl;
import org.mariuszgromada.math.mxparser.*;



@WebServlet("/CalcServlet")
public class CalcServlet extends HttpServlet {

    StringBuilder builder = new StringBuilder();
    String result;
    String ANS;
    List<Szamitas> sessionList = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");

        if(req.getParameter("0")!=null) builder.append("0") ;
        else if(req.getParameter("1")!=null) builder.append("1");
        else if(req.getParameter("2")!=null) builder.append("2");
        else if(req.getParameter("3")!=null) builder.append("3");
        else if(req.getParameter("4")!=null) builder.append("4");
        else if(req.getParameter("5")!=null) builder.append("5");
        else if(req.getParameter("6")!=null) builder.append("6");
        else if(req.getParameter("7")!=null) builder.append("7");
        else if(req.getParameter("8")!=null) builder.append("8");
        else if(req.getParameter("9")!=null) builder.append("9");
        else if(req.getParameter("-")!=null) builder.append(" - ");
        else if(req.getParameter("+")!=null) builder.append(" + ");
        else if(req.getParameter("%")!=null) builder.append(" % ");
        else if(req.getParameter("/")!=null) builder.append(" / ");
        else if(req.getParameter("*")!=null) builder.append(" * ");
        else if(req.getParameter(")")!=null) builder.append(")");
        else if(req.getParameter("(")!=null) builder.append("(");
        else if(req.getParameter("cos")!=null) builder.append("cos(");
        else if(req.getParameter("tan")!=null) builder.append("tan(");
        else if(req.getParameter("sin")!=null) builder.append("sin(");
        else if(req.getParameter("sqrt")!=null) builder.append("sqrt(");
        else if(req.getParameter(".")!=null) builder.append(".");
        else if(req.getParameter("1/x")!=null) builder.append("1/(");
        else if(req.getParameter("DEL")!=null){
            if(builder.length()>=2){
                builder.deleteCharAt(builder.length()-1);
            }
            else if(builder.length()==1){
                builder= new StringBuilder();

            }

        }
        else if(req.getParameter("^")!=null) builder.append("^");
        else if(req.getParameter("%")!=null) builder.append(" % ");
        else if(req.getParameter("ANS")!=null) builder.append(ANS);
        else if(req.getParameter("C")!=null) builder =new StringBuilder();
        else if(req.getParameter("=")!=null){


            Expression ex = new Expression(builder.toString());
            String newres = String.valueOf(ex.calculate());
            if(Double.isNaN(Double.parseDouble(newres))){
                builder= new StringBuilder("Hibás szintaxis!");

            }
            else {
                Szamitas tmp = new Szamitas(result,newres);
                builder=new StringBuilder(newres);
                ANS= newres;
                sessionList.add(tmp);

                try {
                    SzamitasDAO dao = new SzamitasDAOImpl();
                    dao.addToDB(tmp);
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }


            }


        }


        updateRes(builder);

        req.setAttribute("result", result);
        req.getRequestDispatcher("index.jsp").forward(req, resp);

    }
    public void updateRes(StringBuilder sb){
        this.result=sb.toString();
    }
}
