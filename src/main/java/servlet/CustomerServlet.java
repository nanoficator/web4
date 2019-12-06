package servlet;

import com.google.gson.Gson;
import model.Car;
import service.CarService;
import service.SoldCarService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        String json = gson.toJson(CarService.getInstance().getAllCars());
        resp.getWriter().println(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Car carForSelling = new Car();
        carForSelling.setBrand(req.getParameter("brand"));
        carForSelling.setModel(req.getParameter("model"));
        carForSelling.setLicensePlate(req.getParameter("licensePlate"));
        Car carFromDB = CarService.getInstance().findCar(carForSelling);
        if (carFromDB != null) {
            SoldCarService.getInstance().addSoldCar(carFromDB);
            CarService.getInstance().sellCar(carFromDB);
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}
