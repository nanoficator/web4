package DAO;

import model.DailyReport;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.ResultSet;
import java.util.List;

public class DailyReportDao {

    private Session session;

    public DailyReportDao(Session session) {
        this.session = session;
    }

    public List<DailyReport> getAllDailyReport() {
        Transaction transaction = session.beginTransaction();
        List<DailyReport> dailyReports = session.createQuery("FROM DailyReport").list();
        transaction.commit();
        session.close();
        return dailyReports;
    }

    public DailyReport getLastReport() {
        Transaction transaction = session.beginTransaction();
        DailyReport lastDailyReport = (DailyReport) session.createQuery("FROM DailyReports WHERE").uniqueResult();
        transaction.commit();
        session.close();
        return lastDailyReport;
    }

    public void deleteAllReports() {
        Transaction transaction = session.beginTransaction();
        session.createQuery("delete DailyReport");
        transaction.commit();
        session.close();
    }

    public void createDailyReport() {

    }
}
