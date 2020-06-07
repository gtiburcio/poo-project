package application.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Objects;

public class MySingletonConnection {

    public Connection connection;

    public static MySingletonConnection db;

    private MySingletonConnection() {
        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "clinica";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "gna";
        String password = "123456";
        try {
            Class.forName(driver).newInstance();
            this.connection = DriverManager.getConnection(url.concat(dbName), userName, password);
        } catch (Exception sqle) {
            sqle.printStackTrace();
        }
    }

    public static synchronized MySingletonConnection getInstance() {
        if (Objects.isNull(db)) {
            db = new MySingletonConnection();
        }
        return db;
    }
}
