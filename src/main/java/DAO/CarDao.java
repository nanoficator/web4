package DAO;

import model.Car;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CarDao {

    private Session session;

    public CarDao(Session session) {
        this.session = session;
    }

    public List<Car> getAllData() {
        Transaction transaction = session.beginTransaction();
        List<Car> cars = session.createQuery("from Car").list();
        transaction.commit();
        session.close();
        return cars;
    }

    public void deleteAllData() {
        Transaction transaction = session.beginTransaction();
        session.createQuery("delete from Car").executeUpdate();
        transaction.commit();
        session.close();
    }

    public void addData(Car car) {
        Transaction transaction = session.beginTransaction();
        session.save(car);
        transaction.commit();
        session.close();
    }

    public void deleteData(Car car) {
        Transaction transaction = session.beginTransaction();
        session.delete(car);
        transaction.commit();
        session.close();
    }

    public Car findData(Car car) {
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Car where brand = :brand and model = :model and licensePlate = :licensePlate");
        query.setParameter("brand", car.getBrand());
        query.setParameter("model", car.getModel());
        query.setParameter("licensePlate", car.getLicensePlate());
        Car carFromDB = (Car) query.uniqueResult();
        transaction.commit();
        session.close();
        return carFromDB;
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

}
