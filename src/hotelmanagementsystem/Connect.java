package hotelmanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect {
    Connection c;
    Statement s;

    public Connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelmanagementsystem?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true",
                    "root", "1234");
            s = c.createStatement();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver not found", e);
        } catch (SQLException e) {
            throw new RuntimeException("Connection failed: " + e.getMessage(), e);
        }
    }
}