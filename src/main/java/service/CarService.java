package service;

import DAO.CarDao;
import model.Car;
import org.hibernate.SessionFactory;
import util.DBHelper;
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
        if (car.getId() != 0) {
            new CarDao(sessionFactory.openSession()).deleteData(car);
        }
        return false;
    }

    public boolean deleteAllCars() {
        new CarDao(sessionFactory.openSession()).deleteAllData();
        return true;
    }

    public Car findCar(Car car) {
        Car carFromDB = new CarDao(sessionFactory.openSession()).findData(car);
        if (carFromDB.getId() != 0) {
            return carFromDB;
        }
        return null;
    }
}
