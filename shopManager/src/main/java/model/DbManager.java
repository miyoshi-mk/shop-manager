package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DbManager {
    public static Connection getConnection(String env) throws Exception {
        Properties props = new Properties();
        props.load(DbManager.class.getClassLoader().getResourceAsStream("db.properties"));

        String prefix = env.equals("mysql") ? "mysql" : "h2";

        String url = props.getProperty(prefix + ".url");
        String user = props.getProperty(prefix + ".user");
        String password = props.getProperty(prefix + ".password");
        String driver = props.getProperty(prefix + ".driver");

        Class.forName(driver);
        return DriverManager.getConnection(url, user, password);
    }
}
