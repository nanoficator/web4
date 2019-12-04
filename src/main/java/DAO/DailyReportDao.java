package DAO;

import model.Car;
import model.CurrentReport;
import model.DailyReport;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.ResultSet;
import java.util.Iterator;
import java.util.List;

public class DailyReportDao {

    private Session session;

    public DailyReportDao(Session session) {
        this.session = session;
    }

    public List<DailyReport> getAllData() {
        Transaction transaction = session.beginTransaction();
        List<DailyReport> dailyReports = session.createQuery("from DailyReport").list();
        transaction.commit();
        session.close();
        return dailyReports;
    }

    public void deleteAllData() {
        Transaction transaction = session.beginTransaction();
        session.createQuery("delete from DailyReport").executeUpdate();
        transaction.commit();
        session.close();
    }

    public void addData(DailyReport dailyReport) {
        Transaction transaction = session.beginTransaction();
        session.save(dailyReport);
        transaction.commit();
        session.close();
    }

    public void deleteData(DailyReport dailyReport) {
        Transaction transaction = session.beginTransaction();
        session.delete(dailyReport);
        transaction.commit();
        session.close();
    }

    public DailyReport findData(DailyReport dailyReport) {
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from CurrentReport where earnings = :earnings and soldCars = :soldCars");
        query.setParameter("earnings", dailyReport.getEarnings());
        query.setParameter("soldCars", dailyReport.getSoldCars());
        DailyReport dailyReportFromDB = (DailyReport) query.uniqueResult();
        transaction.commit();
        session.close();
        return dailyReportFromDB;
    }

    public void createDailyReport(DailyReport dailyReport) {
        Transaction transaction = session.beginTransaction();
        session.save(dailyReport);
        transaction.commit();
        session.close();
    }

    public DailyReport getLastReport() {
        Transaction transaction = session.beginTransaction();
        DailyReport lastDailyReport = (DailyReport) session.createQuery("from DailyReports where id order by desc").uniqueResult();
        transaction.commit();
        session.close();
        return lastDailyReport;
    }

}
