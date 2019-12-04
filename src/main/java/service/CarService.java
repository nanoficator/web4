package service;

import DAO.CarDao;
import DAO.CurrentReportDao;
import model.Car;
import org.hibernate.SessionFactory;
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
            return new CarDao(sessionFactory.openSession()).addCar(car);
        }
        return false;
    }

    public List<Car> getAllCars() {
        return new CarDao(sessionFactory.openSession()).getAllCars();
    }

    public boolean sellCar (Car car) {
        Car carFromDB = new CarDao(sessionFactory.openSession()).findCarFromDB(car);
        if (car.equals(carFromDB)) {
            new
        }
        return deleteCar(car);
        return false;
    }

    public boolean deleteCar(Car car) {
        return new CarDao(sessionFactory.openSession()).deleteCar(car);
    }

    public boolean deleteAllCars() {
        return new CarDao(sessionFactory.openSession()).deleteAllCars();
    }
}
