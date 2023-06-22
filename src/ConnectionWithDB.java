import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionWithDB {
    static Connection con;
    public static Connection createCon() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");

        con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila","root","mysql");
        return con;
    }
}
