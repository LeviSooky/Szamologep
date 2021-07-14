import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Modell.Szamitas;
import Modell.SzamitasDAO;
import Modell.SzamitasDAOImpl;


@WebServlet("/SzamitasHistoryServlet")
public class SzamitasHistoryServlet extends HttpServlet {


    List<Szamitas> lista = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html");





        try {
            Class.forName("org.sqlite.JDBC");
           SzamitasDAO dao = new SzamitasDAOImpl();
            lista = dao.findAll();
            req.setAttribute("lista", lista);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }


}
