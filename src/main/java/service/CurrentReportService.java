package service;

import DAO.CurrentReportDao;
import model.Car;
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

    public boolean addSale(Car car) {
        new CurrentReportDao(sessionFactory.openSession()).addData(car);
        return true;
    }

    public DailyReport closeSalesSheet() {
        return null;
    }

}
