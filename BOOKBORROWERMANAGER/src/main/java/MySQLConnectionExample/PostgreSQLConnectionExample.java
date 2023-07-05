/*
package MySQLConnectionExample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQLConnectionExample {
    public static Connection getConection() {

         c =Connection null;
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            // Sử dụng connection để thao tác với cơ sở dữ liệu
            String url = "jdbc:mysql://localhost:3306/";
            String username = "root";
            String password = "giang123";

            c = DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }
    public static void closeConnection(Connection c){
      try {
          if(c!=null)
              c.close();
      }catch (Exception e)
      {
          e.printStackTrace();
      }
    }
}

*/
