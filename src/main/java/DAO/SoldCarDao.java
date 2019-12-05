package DAO;

import model.Car;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class SoldCarDao {

    private Session session;

    public SoldCarDao(Session session) {
        this.session = session;
    }

    public List<Car> getAllData() {
        Transaction transaction = session.beginTransaction();
        List<Car> soldCars = session.createQuery("from SoldCar").list();
        transaction.commit();
        session.close();
        return soldCars;
    }

    public void deleteAllData() {
        Transaction transaction = session.beginTransaction();
        session.createQuery("delete from SoldCar").executeUpdate();
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
        Query query = session.createQuery("from SoldCar where brand = :brand and model = :model and licensePlate = :licensePlate");
        query.setParameter("brand", car.getBrand());
        query.setParameter("model", car.getModel());
        query.setParameter("licensePlate", car.getLicensePlate());
        Car carFromDB = (Car) query.uniqueResult();
        transaction.commit();
        session.close();
        return carFromDB;
    }

}