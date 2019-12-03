package service;

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

//    Количество машин определенной марки
    public int carBrandAmount(String brand) {
        return 0;
    }

//    Добавить машину в базу
    public boolean addCar(Car car) {
        return false;
    }

//    Получить все машины из базы
    public List<Car> getAllCars() {
        List<Car> listCars = new LinkedList<>();
        return listCars;
    }
}
