package storage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DBConnection {
    public static Connection getConnection() throws SQLException, IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("db.properties");
        prop.load(fis);
        
        String url = prop.getProperty("db.url");
        String user = prop.getProperty("db.user");
        String password = prop.getProperty("db.password");
        
        return DriverManager.getConnection(url, user, password);
    }
}