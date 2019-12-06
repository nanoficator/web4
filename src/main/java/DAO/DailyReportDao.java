package DAO;

import model.DailyReport;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
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
        session.createQuery("delete DailyReport").executeUpdate();
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
        Query query = session.createQuery("from DailyReport where earnings = :earnings and soldCars = :soldCars");
        query.setParameter("earnings", dailyReport.getEarnings());
        query.setParameter("soldCars", dailyReport.getSoldCars());
        DailyReport dailyReportFromDB = (DailyReport) query.uniqueResult();
        transaction.commit();
        session.close();
        return dailyReportFromDB;
    }

    public DailyReport findDataById(Long id) {
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from DailyReport where id = :id");
        query.setParameter("id", id);
        DailyReport dailyReport = (DailyReport) query.uniqueResult();
        transaction.commit();
        session.close();
        return dailyReport;
    }

    public DailyReport getLastData() {
        Transaction transaction = session.beginTransaction();
        List<DailyReport> dailyReportList = session.createQuery("from DailyReport order by id desc").list();
        DailyReport lastDailyReport = dailyReportList.get(0);
        transaction.commit();
        session.close();
        return lastDailyReport;
    }

}
