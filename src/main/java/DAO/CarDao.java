package DAO;

import com.google.protobuf.DescriptorProtos;
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

    public List<Car> getAllCars() {
        Transaction transaction = session.beginTransaction();
        return session.createQuery("from Car").list();
    }

    public boolean isExistCar(Car car) {
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Car where brand = :brand and model = :model and licensePlate = :licensePlate");
        query.setParameter("brand", car.getBrand());
        query.setParameter("model", car.getModel());
        query.setParameter("licensePlate", car.getLicensePlate());
        Car carFromDB = (Car) query.uniqueResult();
        transaction.commit();
        session.close();
        return car.equals(carFromDB);
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

    public boolean deleteCar (Car car) {
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("delete from Car where brand = :brand and model = :model and licensePlate = :licensePlate");
        query.setParameter("brand", car.getBrand());
        query.setParameter("model", car.getModel());
        query.setParameter("licensePlate", car.getLicensePlate());
        query.executeUpdate();
        transaction.commit();
        session.close();
        return true;
    }

    public boolean deleteAllCars() {
        Transaction transaction = session.beginTransaction();
        session.createQuery("delete from Car").executeUpdate();
        transaction.commit();
        session.close();
        return true;
    }

}
