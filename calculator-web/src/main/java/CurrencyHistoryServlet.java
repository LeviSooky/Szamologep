import Modell.CurrencyConv;
import Modell.CurrencyDAO;
import Modell.CurrencyDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/CurrencyHistoryServlet")
public class CurrencyHistoryServlet extends HttpServlet {
    List<CurrencyConv> currLista = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html");




        try {
            CurrencyDAO dao = new CurrencyDAOImpl();
            currLista=dao.findAll();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        req.setAttribute("currLista",currLista);
    }

}
