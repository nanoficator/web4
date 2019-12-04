package servlet;

import com.google.gson.Gson;
import service.CarService;
import service.DailyReportService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DailyReportServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        if (req.getPathInfo().contains("all")) {
            String json = gson.toJson(DailyReportService.getInstance().getAllDailyReports());
            System.out.println(json);
            resp.setStatus(HttpServletResponse.SC_OK);
        } else if (req.getPathInfo().contains("last")) {
            String json = gson.toJson(DailyReportService.getInstance().getLastReport());
            System.out.println(json);
            resp.setStatus(HttpServletResponse.SC_OK);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DailyReportService.getInstance().dellAllReports();
        CarService.getInstance().dellAllCars();
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
