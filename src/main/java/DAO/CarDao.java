package DAO;

import model.Car;
import model.DailyReport;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CarDao {

    private Session session;

    public CarDao(Session session) {
        this.session = session;
    }

    public boolean isExistCar(String brand, String model, String licensePlate) {
        return true;
    }

    public int carBrandAmount(String brand) {
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Car where brand = :brand");
        query.setParameter("brand", brand);
        List<Car> cars = query.list();
        transaction.commit();
        session.close();
        return cars.size();
    }

    public boolean addCar(Car car) {
        Transaction transaction = session.beginTransaction();
        session.save(car);
        transaction.commit();
        session.close();
        return true;
    }

    public void deleteAllCars() {
        Transaction transaction = session.beginTransaction();
        session.createQuery("delete Car");
        transaction.commit();
        session.close();
    }

}
