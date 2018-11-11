package zz.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ClearListener implements ServletContextListener{
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        try {
            DriverManager.deregisterDriver(DriverManager.getDriver("com.mysql.jdbc.Driver"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
