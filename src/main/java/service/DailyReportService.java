package service;

import DAO.DailyReportDao;
import model.CurrentReport;
import model.DailyReport;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.DBHelper;

import java.util.List;

public class DailyReportService {

    private static DailyReportService dailyReportService;

    private SessionFactory sessionFactory;

    private DailyReportService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static DailyReportService getInstance() {
        if (dailyReportService == null) {
            dailyReportService = new DailyReportService(DBHelper.getSessionFactory());
        }
        return dailyReportService;
    }

    public List<DailyReport> getAllData() {
        return new DailyReportDao(sessionFactory.openSession()).getAllData();
    }

    public DailyReport getLastReport() {
        return new DailyReportDao(sessionFactory.openSession()).getLastReport();
    }

    public void deleteAllReports() {
        new DailyReportDao(sessionFactory.openSession()).deleteAllData();
    }

    public void createDailyReport() {
        DailyReport dailyReport = CurrentReportService.getInstance().closeSalesSheet();
        new DailyReportDao(sessionFactory.openSession()).createDailyReport(dailyReport);
    }

}
