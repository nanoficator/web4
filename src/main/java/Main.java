import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlet.CustomerServlet;
import servlet.DailyReportServlet;
import servlet.NewDayServlet;
import servlet.ProducerServlet;

public class Main {
    public static void main(String[] args) throws Exception {

        CustomerServlet customerServlet = new CustomerServlet();
        DailyReportServlet dailyReportServlet = new DailyReportServlet();
        NewDayServlet newDayServlet = new NewDayServlet();
        ProducerServlet producerServlet = new ProducerServlet();

        ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        servletContextHandler.addServlet(new ServletHolder(customerServlet), "/customer");
        servletContextHandler.addServlet(new ServletHolder(dailyReportServlet), "/report");
        servletContextHandler.addServlet(new ServletHolder(dailyReportServlet), "/report/*");
        servletContextHandler.addServlet(new ServletHolder(newDayServlet), "/newday");
        servletContextHandler.addServlet(new ServletHolder(producerServlet), "/producer");

        Server server = new Server(8080);
        server.setHandler(servletContextHandler);

        server.start();
        server.join();
    }
}
