import Modell.UnitConv;
import Modell.UnitDAO;
import Modell.UnitDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/UnitHistoryServlet")
public class UnitHistoryServlet extends HttpServlet {
    List<UnitConv> unitLista = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html");



        try {
            UnitDAO dao = new UnitDAOImpl();
            unitLista=dao.findAll();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        req.setAttribute("unitLista",unitLista);
    }
}
