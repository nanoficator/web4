package service;

import DAO.CarDao;
import DAO.CurrentReportDao;
import model.Car;
import model.CurrentReport;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.DBHelper;

import java.util.LinkedList;
import java.util.List;

public class CarService {

    private static CarService carService;

    private SessionFactory sessionFactory;

    private CarService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static CarService getInstance() {
        if (carService == null) {
            carService = new CarService(DBHelper.getSessionFactory());
        }
        return carService;
    }

    public boolean addCar(Car car) {
        if (new CarDao(sessionFactory.openSession()).carBrandAmount(car.getBrand()) < 10) {
            new CarDao(sessionFactory.openSession()).addData(car);
            return true;
        }
        return false;
    }

    public List<Car> getAllCars() {
        return new CarDao(sessionFactory.openSession()).getAllData();
    }

    public boolean sellCar (Car car) {
        Car carFromDB = new CarDao(sessionFactory.openSession()).findData(car);
        if (carFromDB.getId() != 0) {
            new CarDao(sessionFactory.openSession()).deleteData(carFromDB);
            CurrentReportService.getInstance().addSale(carFromDB);
            return true;
        }
        return false;
    }

    public boolean deleteAllCars() {
        new CarDao(sessionFactory.openSession()).deleteAllData();
        return true;
    }
}
