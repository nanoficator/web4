package DAO;

import model.Car;
import model.SoldCar;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class SoldCarDao {

    private Session session;

    public SoldCarDao(Session session) {
        this.session = session;
    }

    public List<SoldCar> getAllData() {
        Transaction transaction = session.beginTransaction();
        List<SoldCar> soldCars = session.createQuery("from SoldCar").list();
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
        Query query = session.createQuery("insert into SoldCar (id, brand, model, licensePlate, price) select id, brand, model, licensePlate, price from Car where id = :id");
        query.setParameter("id", car.getId());
        query.executeUpdate();
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
