import java.sql.Connection;
import java.sql.DriverManager;

public class DBConn {
    private Connection con;
    private static DBConn dbc;
    private DBConn() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/contactdb","root", "");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static DBConn getDBConn() {
        if(dbc == null) {
            dbc = new DBConn();
        }
        return dbc;
    }

    public Connection getConnection() {
        return con;
    }
}
