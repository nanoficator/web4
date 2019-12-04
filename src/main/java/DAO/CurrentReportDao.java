package DAO;

import model.Car;
import model.CurrentReport;
import model.DailyReport;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Iterator;
import java.util.List;

public class CurrentReportDao {

    private Session session;

    public CurrentReportDao(Session session) {
        this.session = session;
    }

    public List<CurrentReport> getAllData() {
        Transaction transaction = session.beginTransaction();
        List<CurrentReport> salesSheet = session.createQuery("from CurrentReport").list();
        transaction.commit();
        session.close();
        return salesSheet;
    }

    public void deleteAllData() {
        Transaction transaction = session.beginTransaction();
        session.createQuery("delete from CurrentReport").executeUpdate();
        transaction.commit();
        session.close();
    }

    public void addData(Car car) {
        CurrentReport sale = new CurrentReport();
        sale.setCar(car);
        sale.setPrice(car.getPrice());
        sale.setId(car.getId());
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
        Query query = session.createQuery("from CurrentReport where brand = :brand and model = :model and licensePlate = :licensePlate");
        query.setParameter("brand", car.getBrand());
        query.setParameter("model", car.getModel());
        query.setParameter("licensePlate", car.getLicensePlate());
        Car carFromDB = (Car) query.uniqueResult();
        transaction.commit();
        session.close();
        return carFromDB;
    }

    public DailyReport createDailyReport() {
        Iterator<CurrentReport> iterator = getAllData().iterator();
        Long earning = (long) 0;
        Long soldCars = (long) 0;
        while (iterator.hasNext()) {
            CurrentReport sale = iterator.next();
            earning += iterator.next().getPrice();
            soldCars += 1;
        }
        return new DailyReport(earning, soldCars);
    }

}
