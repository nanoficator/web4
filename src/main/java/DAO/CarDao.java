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
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Car where brand = :brand and model = :model and licensePlate = :licensePlate");
        query.setParameter("brand", brand);
        query.setParameter("model", model);
        query.setParameter("licensePlate", licensePlate);
        int result = query.getFirstResult();
        return result == 1;
    }

    public int carBrandAmount(String brand) {
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM Car WHERE brand = :brand");
        query.setParameter("brand", brand);
        List<Car> cars = query.list();
        int amount = cars.size();
        transaction.commit();
        session.close();
        return amount;
    }

    public boolean addCar(Car car) {
        session.save(car);
        session.close();
        return true;
    }

}
