package service;

import model.CurrentReport;
import model.DailyReport;
import org.hibernate.SessionFactory;
import util.DBHelper;

public class CurrentReportService {

    private static CurrentReportService currentReportService;

    private SessionFactory sessionFactory;

    private CurrentReportService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static CurrentReportService getInstance() {
        if (currentReportService == null) {
            currentReportService = new CurrentReportService(DBHelper.getSessionFactory());
        }
        return currentReportService;
    }

    public DailyReport closeSalesSheet() {
        return null;
    }

}
