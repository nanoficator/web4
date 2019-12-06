package service;

import DAO.SoldCarDao;
import model.Car;
import model.DailyReport;
import model.SoldCar;
import org.hibernate.SessionFactory;
import util.DBHelper;

import java.util.Iterator;
import java.util.List;

public class SoldCarService {

    private static SoldCarService soldCarService;

    private SessionFactory sessionFactory;

    private SoldCarService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static SoldCarService getInstance() {
        if (soldCarService == null) {
            soldCarService = new SoldCarService(DBHelper.getSessionFactory());
        }
        return soldCarService;
    }

    public boolean addSoldCar(Car car) {
        if (car.getId() != 0) {
            new SoldCarDao(sessionFactory.openSession()).addData(car);
            return true;
        }
        return false;
    }

    public boolean deleteSoldCar(Car car) {
        if (car.getId() != 0) {
            new SoldCarDao(sessionFactory.openSession()).deleteData(car);
            return true;
        }
        return false;
    }

    public List<SoldCar> getAllSoldCars() {
        return new SoldCarDao(sessionFactory.openSession()).getAllData();
    }


    public boolean deleteAllSoldCars() {
        new SoldCarDao(sessionFactory.openSession()).deleteAllData();
        return true;
    }

    public DailyReport createDailyReport() {
        List<SoldCar> sellsSheet = new SoldCarDao(sessionFactory.openSession()).getAllData();
        Long earning = (long) 0;
        Long soldCars = (long) 0;
        Iterator<SoldCar> iterator = sellsSheet.iterator();
        while (iterator.hasNext()) {
            SoldCar soldCar = iterator.next();
            earning += soldCar.getPrice();
            soldCars += 1;
        }
        return new DailyReport(earning, soldCars);
    }

}
