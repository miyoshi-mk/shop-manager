package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DbManager {
    public static Connection getConnection(String env) throws Exception {
        Properties props = new Properties();

        // クラスパス直下のdb.propertiesを読み込み
        try (var in = DbManager.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (in == null) {
                throw new RuntimeException("db.properties が見つかりません。クラスパスを確認してください。");
            }
            props.load(in);
            System.out.println("Loaded properties: " + props);
        }

        String prefix = env.equals("mysql") ? "mysql" : "h2";
        String url = props.getProperty(prefix + ".url");
        String user = props.getProperty(prefix + ".user");
        String password = props.getProperty(prefix + ".password");
        String driver = props.getProperty(prefix + ".driver");

        Class.forName(driver);
        return DriverManager.getConnection(url, user, password);

    }
}