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

//    Проверка наличия запрашиваемой машины в базе
    public boolean isExistCar (String brand, String model, String licensePlate) {
        return false;
    }

//    Получить ID по Бренду, Марке и Номеру
    public Long getId(String brand, String model, String licensePlate) {
        Long id = new Long(0);
        return id;
    }

//    Продать машину по ID
    public Car sellCarByID (Long id) {
        Car soldCar = new Car();
        return soldCar;
    }
}
